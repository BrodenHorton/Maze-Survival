package me.brody.mazesurvival.mob;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.player.PlayerProfile;
import me.brody.mazesurvival.utils.LocationCopier;
import me.brody.mazesurvival.utils.MathUtils;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class MobManager implements Listener {
    private static final Random RNG = new Random();

    private final Main plugin;

    private MobSpawnConfig daySpawnConfig;
    private MobSpawnConfig nightSpawnConfig;
    private MobSpawnConfig currentSpawnConfig;
    private List<UUID> mobs;
    private boolean isDay;
    private Map<UUID, List<Integer>> mobSpawningCooldownByPlayerUuid;

    public MobManager(Main plugin, DayNightCycle dayNightCycle) {
        this.plugin = plugin;
        daySpawnConfig = MobSpawnConfig.DAY_SPAWN_CONFIG;
        nightSpawnConfig = MobSpawnConfig.NIGHT_SPAWN_CONFIG;
        currentSpawnConfig = daySpawnConfig;
        mobs = new ArrayList<>();
        isDay = true;
        mobSpawningCooldownByPlayerUuid = new HashMap<>();

        dayNightCycle.onStartOfDay.addListener((sender, args) -> {
            isDay = true;
            currentSpawnConfig = daySpawnConfig;
            despawnAllMobs();
        });
        dayNightCycle.onStartOfNight.addListener((sender, args) -> {
            isDay = false;
            currentSpawnConfig = nightSpawnConfig;
            despawnAllMobs();
        });
        plugin.getMazeManager().onMazeConstructionFinished.addListener(this::initMobSpawning);
    }

    public void initMobSpawning(Object sender, EventArgs args) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::despawnOutOfRangeMobs, 0L, MobSpawnConfig.MOB_SPAWNING_UPDATE_IN_TICKS);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            decrementPlayerMobSpawningCooldowns((int)(MobSpawnConfig.MOB_SPAWNING_UPDATE_IN_TICKS / 20L));
            spawnMazeMobs();
        }, 0L, MobSpawnConfig.MOB_SPAWNING_UPDATE_IN_TICKS);
    }

    public void spawnMazeMobs() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
            if(profile == null)
                continue;
            MazeRegion region = grid.getRegionAt(player.getLocation());
            if(region == null)
                continue;
            if(grid.getRegionCellAt(region, player.getLocation()) == null)
                continue;
            int nearMobs = 0;
            for(Entity entity : player.getNearbyEntities(currentSpawnConfig.getDespawnRadius(), currentSpawnConfig.getDespawnRadius(), currentSpawnConfig.getDespawnRadius())) {
                if(mobs.contains(entity.getUniqueId()))
                    nearMobs++;
            }
            if(nearMobs >= currentSpawnConfig.getMaxMobsNearPlayer())
                continue;
            List<Location> spawnLocations = floodFillCellSpawnLocations(region, player);
            if(spawnLocations.isEmpty())
                continue;
            int playerMobRespawnCooldownCount = 0;
            if(mobSpawningCooldownByPlayerUuid.containsKey(player.getUniqueId()))
                playerMobRespawnCooldownCount = mobSpawningCooldownByPlayerUuid.get(player.getUniqueId()).size();
            int maxSpawnCount = MathUtils.clamp(currentSpawnConfig.getMaxMobsNearPlayer() - nearMobs - playerMobRespawnCooldownCount, 0, currentSpawnConfig.getMaxConcurrentSpawnCount());
            if(maxSpawnCount <= 0)
                continue;

            int spawnCount = RNG.nextInt(1, maxSpawnCount + 1);
            for(int i = 0; i < spawnCount; i++) {
                Location spawnLocation = LocationCopier.copy(spawnLocations.get(RNG.nextInt(0, spawnLocations.size())));
                int maxSpawnOffset = grid.getRegionCellSize() / 2 - 1;
                int xOffset = RNG.nextInt(-maxSpawnOffset, maxSpawnOffset + 1);
                int zOffset = RNG.nextInt(-3, 4);
                float blockCenterOffset = 0.5f;
                spawnLocation.setX(spawnLocation.getX() + xOffset + blockCenterOffset);
                spawnLocation.setY(spawnLocation.getY() + 1);
                spawnLocation.setZ(spawnLocation.getZ() + zOffset + blockCenterOffset);
                LivingEntity entity;
                if(isDay)
                    entity = region.getDayMobs().getWeightedItems().getWeightedValue().summon(spawnLocation);
                else
                    entity = region.getNightMobs().getWeightedItems().getWeightedValue().summon(spawnLocation);
                // Temp Glowing effect
                entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000, 0, false));
                mobs.add(entity.getUniqueId());
                plugin.getLogger().info("Mob spawned at: " + spawnLocation.getX() + ", " + spawnLocation.getZ());
            }
        }
    }

    public void despawnOutOfRangeMobs() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        List<UUID> mobsInRange = new ArrayList<>();
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            if(plugin.getProfileManager().getProfileOf(player) == null)
                continue;
            MazeRegion region = grid.getRegionAt(player.getLocation());
            if(region == null)
                continue;
            Vector2Int cellCoords = grid.getRegionCellAt(region, player.getLocation());
            if(cellCoords == null || region.getMazeCells()[cellCoords.y][cellCoords.x] == null)
                continue;

            for(Entity entity : player.getNearbyEntities(currentSpawnConfig.getDespawnRadius(), currentSpawnConfig.getDespawnRadius(), currentSpawnConfig.getDespawnRadius())) {
                if(mobs.contains(entity.getUniqueId()))
                    mobsInRange.add(entity.getUniqueId());
            }
        }

        World world = grid.getGridOrigin().getWorld();
        for(int i = mobs.size() - 1; i >= 0; i--) {
            if(mobsInRange.contains(mobs.get(i)))
                continue;

            for(Entity entity : plugin.getServer().getWorld(world.getUID()).getEntities()) {
                if(entity.getUniqueId().equals(mobs.get(i))) {
                    entity.remove();
                    break;
                }
            }
            mobs.remove(i);
        }
    }

    public void despawnAllMobs() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        World world = grid.getGridOrigin().getWorld();
        for(int i = mobs.size() - 1; i >= 0; i--) {
            for(Entity entity : plugin.getServer().getWorld(world.getUID()).getEntities()) {
                if(entity.getUniqueId().equals(mobs.get(i))) {
                    entity.remove();
                    mobs.remove(i);
                    break;
                }
            }
        }
    }

    private List<Location> floodFillCellSpawnLocations(MazeRegion region, Player player) {
        Vector2Int playerCellCoords = plugin.getMazeManager().getGrid().getRegionCellAt(region, player.getLocation());
        if(region.getMazeCells()[playerCellCoords.y][playerCellCoords.x] == null)
            return new ArrayList<>();

        return floodFillCellSpawnLocations(new ArrayList<>(), new ArrayList<>(), region, playerCellCoords.y, playerCellCoords.x, 0);
    }

    private List<Location> floodFillCellSpawnLocations(List<Location> spawnLocations, List<Vector2Int> traversedCells, MazeRegion region, int row, int column, int cellDistance) {
        cellDistance++;
        traversedCells.add(new Vector2Int(column, row));
        if(cellDistance > currentSpawnConfig.getMaxCellSpawnDistance())
            return spawnLocations;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        if(cellDistance >= currentSpawnConfig.getMinCellSpawnDistance())
            spawnLocations.add(grid.getRegionCellWorldCenter(region, row, column));

        // North cell check
        if(row - 1 >= 0 && region.getMazeCells()[row - 1][column] != null && !region.getMazeCells()[row - 1][column].walls[2] && !traversedCells.contains(new Vector2Int(column, row - 1)))
            floodFillCellSpawnLocations(spawnLocations, traversedCells, region, row - 1, column, cellDistance);
        // East cell check
        if(column + 1 < region.getMazeCells()[0].length && region.getMazeCells()[row][column + 1] != null && !region.getMazeCells()[row][column + 1].walls[3] && !traversedCells.contains(new Vector2Int(column + 1, row)))
            floodFillCellSpawnLocations(spawnLocations, traversedCells, region, row, column + 1, cellDistance);
        // South cell check
        if(row + 1 < region.getMazeCells().length && region.getMazeCells()[row + 1][column] != null && !region.getMazeCells()[row + 1][column].walls[0] && !traversedCells.contains(new Vector2Int(column, row + 1)))
            floodFillCellSpawnLocations(spawnLocations, traversedCells, region, row + 1, column, cellDistance);
        // West cell check
        if(column - 1 >= 0 && region.getMazeCells()[row][column - 1] != null && !region.getMazeCells()[row][column - 1].walls[1] && !traversedCells.contains(new Vector2Int(column - 1, row)))
            floodFillCellSpawnLocations(spawnLocations, traversedCells, region, row, column - 1, cellDistance);

        return spawnLocations;
    }

    private void decrementPlayerMobSpawningCooldowns(int decrementInSeconds) {
        for(Map.Entry<UUID, List<Integer>> entry : mobSpawningCooldownByPlayerUuid.entrySet()) {
            for(int i = entry.getValue().size() - 1; i >= 0; i--) {
                if(entry.getValue().get(i) <= decrementInSeconds)
                    entry.getValue().remove(i);
                else
                    entry.getValue().set(i, entry.getValue().get(i) - decrementInSeconds);
            }
        }
    }

    @EventHandler
    public void addPlayerMobSpawningCooldown(EntityDeathEvent e) {
        if(!mobs.contains(e.getEntity().getUniqueId()))
            return;
        if(!(e.getEntity().getKiller() instanceof Player))
            return;
        Player player = e.getEntity().getKiller();
        PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
        if(profile == null)
            return;

        if(mobSpawningCooldownByPlayerUuid.containsKey(player.getUniqueId()))
            mobSpawningCooldownByPlayerUuid.get(player.getUniqueId()).add(currentSpawnConfig.getMobRespawnCooldownInSeconds());
        else
            mobSpawningCooldownByPlayerUuid.put(player.getUniqueId(), new ArrayList<>(currentSpawnConfig.getMobRespawnCooldownInSeconds()));

        plugin.getLogger().info(player.getDisplayName() + "'s Mob resapwn cooldowns: ");
        for(int i = 0; i < mobSpawningCooldownByPlayerUuid.get(player.getUniqueId()).size(); i++)
            plugin.getLogger().info(i + ": " + mobSpawningCooldownByPlayerUuid.get(player.getUniqueId()).get(i));
    }

}
