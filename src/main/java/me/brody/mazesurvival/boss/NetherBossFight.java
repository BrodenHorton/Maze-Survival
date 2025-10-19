package me.brody.mazesurvival.boss;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.persistence.PersistentDataType;

public class NetherBossFight implements BossFight {
    private final Main plugin;

    private BossType bossType;

    public NetherBossFight(Main plugin) {
        this.plugin = plugin;
        bossType = BossType.NETHER;
    }

    @Override
    public void start(MazeRegion region) {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        Location bossRoomCenter = grid.getRegionBossRoomWorldCenter(region);
        final float yaw = bossRoom.getDirection().id * 90;
        Location bossSpawnLocation = new Location(bossRoomCenter.getWorld(), 0, 1, -5, yaw, 0);
        bossSpawnLocation = LocationUtils.shift(bossSpawnLocation, bossRoomCenter);
        Location enemySpawnLocation1 = new Location(bossRoomCenter.getWorld(), -3, 1, -3, yaw, 0);
        enemySpawnLocation1 = LocationUtils.rotate(enemySpawnLocation1, bossRoom.getDirection().id * 90);
        enemySpawnLocation1 = LocationUtils.shift(enemySpawnLocation1, bossRoomCenter);
        Location enemySpawnLocation2 = new Location(bossRoomCenter.getWorld(), 3, 1, -3, yaw, 0);
        enemySpawnLocation2 = LocationUtils.rotate(enemySpawnLocation2, bossRoom.getDirection().id * 90);
        enemySpawnLocation2 = LocationUtils.shift(enemySpawnLocation2, bossRoomCenter);

        plugin.getLogger().info("=== Boss Mobs being summoned ===");
        plugin.getLogger().info("Location: " + bossSpawnLocation.getX() + ", " + bossSpawnLocation.getY() + ", " + bossSpawnLocation.getZ());
        CustomMob.WRAITH.summon(bossSpawnLocation).getPersistentDataContainer().set(NamespacedKeys.BOSS, PersistentDataType.STRING, bossType.getBossId());
        CustomMob.MAZE_GHAST.summon(enemySpawnLocation1).getPersistentDataContainer().set(NamespacedKeys.BOSS, PersistentDataType.STRING, bossType.getBossId());
        CustomMob.MAZE_GHAST.summon(enemySpawnLocation2).getPersistentDataContainer().set(NamespacedKeys.BOSS, PersistentDataType.STRING, bossType.getBossId());
    }
}
