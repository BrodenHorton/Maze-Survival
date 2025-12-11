package me.brody.mazesurvival.listener.boss;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class BossListener implements Listener {
    private final Main plugin;

    public BossListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void defeatBoss(EntityDeathEvent e) {
        if(!e.getEntity().getPersistentDataContainer().has(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING))
            return;

        Bukkit.broadcastMessage(ChatColor.GREEN + "Boss mob defeated!");
        MazeGrid grid = plugin.getMazeManager().getGrid();
        String regionId = e.getEntity().getPersistentDataContainer().get(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING);
        for(Entity entity : grid.getWorld().getEntities()) {
            if(entity.getPersistentDataContainer().has(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING)
                    && entity.getPersistentDataContainer().get(NamespacedKeys.REGION_BOSS, PersistentDataType.STRING).equals(regionId)) {
                return;
            }
        }

        plugin.getGameState().addClearedRegion(grid.getRegionByUuid(UUID.fromString(regionId)));
        for(Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
            onlinePlayer.sendTitle(ChatColor.GREEN + "Region Cleared!", "", 10, 40, 20);
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            ChatUtils.msg(onlinePlayer, "&dRegion level has increased!");
        }
        plugin.getLogger().info("region level is now: " + plugin.getGameState().getClearedRegions().size());

        MazeRegion region = grid.getRegionByUuid(UUID.fromString(regionId));
        Location bossRoomOrigin = LocationUtils.centerOnBlock(grid.getRegionBossRoomWorldOrigin(region));
        final float yaw = region.getBossRoom().getDirection().id * 90;
        Location markerLocation = new Location(bossRoomOrigin.getWorld(), 0, 1, -24, yaw, 0);
        markerLocation = LocationUtils.rotate(markerLocation, region.getBossRoom().getDirection().id * -90);
        markerLocation = LocationUtils.shift(markerLocation, bossRoomOrigin);
        ArmorStand havenTeleportMarker = (ArmorStand)grid.getWorld().spawnEntity(markerLocation, EntityType.ARMOR_STAND);
        havenTeleportMarker.setInvisible(true);
        havenTeleportMarker.setGravity(false);
        havenTeleportMarker.setCustomName(ChatColor.AQUA + "Teleport to Haven");
        havenTeleportMarker.setCustomNameVisible(true);
        havenTeleportMarker.getPersistentDataContainer().set(NamespacedKeys.HAVEN_TELEPORT_MARKER, PersistentDataType.STRING, regionId);
    }
}
