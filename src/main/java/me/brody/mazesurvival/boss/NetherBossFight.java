package me.brody.mazesurvival.boss;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;

public class NetherBossFight implements BossFight {
    private transient Main plugin;

    public NetherBossFight(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void start(MazeRegion region) {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        clearExistingBossMobs(region);

        MazeGrid grid = plugin.getMazeManager().getGrid();
        Location bossRoomCenter = LocationUtils.centerOnBlock(grid.getRegionBossRoomWorldCenter(region));
        final float yaw = bossRoom.getDirection().id * 90;
        Location bossSpawnLocation = new Location(bossRoomCenter.getWorld(), 0, 1, -5, yaw, 0);
        bossSpawnLocation = LocationUtils.rotate(bossSpawnLocation, bossRoom.getDirection().id * -90);
        bossSpawnLocation = LocationUtils.shift(bossSpawnLocation, bossRoomCenter);
        Location enemySpawnLocation1 = new Location(bossRoomCenter.getWorld(), -3, 1, -3, yaw, 0);
        enemySpawnLocation1 = LocationUtils.rotate(enemySpawnLocation1, bossRoom.getDirection().id * -90);
        enemySpawnLocation1 = LocationUtils.shift(enemySpawnLocation1, bossRoomCenter);
        Location enemySpawnLocation2 = new Location(bossRoomCenter.getWorld(), 3, 1, -3, yaw, 0);
        enemySpawnLocation2 = LocationUtils.rotate(enemySpawnLocation2, bossRoom.getDirection().id * -90);
        enemySpawnLocation2 = LocationUtils.shift(enemySpawnLocation2, bossRoomCenter);

        LivingEntity boss = CustomMob.WRAITH.summon(bossSpawnLocation);
        boss.getPersistentDataContainer().set(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING, region.getUuid().toString());
        LivingEntity mob1 = CustomMob.MAZE_GHAST.summon(enemySpawnLocation1);
        mob1.getPersistentDataContainer().set(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING, region.getUuid().toString());
        LivingEntity mob2 = CustomMob.MAZE_GHAST.summon(enemySpawnLocation2);
        mob2.getPersistentDataContainer().set(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING, region.getUuid().toString());
    }

    private void clearExistingBossMobs(MazeRegion region) {
        for(Entity entity : plugin.getMazeManager().getGrid().getWorld().getEntities()) {
            if(entity.getPersistentDataContainer().has(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING)
                    && entity.getPersistentDataContainer().get(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING).equals(region.getUuid().toString())) {
                entity.remove();
                Bukkit.broadcastMessage("&eBoss mob removed!");
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        plugin = JavaPlugin.getPlugin(Main.class);
    }
}
