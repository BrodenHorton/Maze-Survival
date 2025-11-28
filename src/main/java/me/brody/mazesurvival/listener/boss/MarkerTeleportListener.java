package me.brody.mazesurvival.listener.boss;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.LocationUtils;
import me.brody.mazesurvival.utils.PlayerRotationUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class MarkerTeleportListener implements Listener {
    private final Main plugin;

    public MarkerTeleportListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void havenTeleportInteraction(PlayerInteractAtEntityEvent e) {
        if(!(e.getRightClicked() instanceof ArmorStand armorStand))
            return;
        if(!armorStand.getPersistentDataContainer().has(NamespacedKeys.HAVEN_TELEPORT_MARKER, PersistentDataType.STRING))
            return;
        MazeGrid grid = plugin.getMazeManager().getGrid();
        String regionId = armorStand.getPersistentDataContainer().get(NamespacedKeys.HAVEN_TELEPORT_MARKER, PersistentDataType.STRING);
        MazeRegion region = grid.getRegionByUuid(UUID.fromString(regionId));
        if(region == null)
            return;

        Location havenCenter = grid.getRegionHavenWorldCenter(region);
        havenCenter = LocationUtils.centerOnBlock(havenCenter);
        havenCenter.setY(havenCenter.getY() + 1);
        havenCenter.setYaw(PlayerRotationUtils.getYaw(region.getHaven().getDirection()));
        e.getPlayer().teleport(havenCenter);
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        ChatUtils.msg(e.getPlayer(), "&6Teleported back to region haven");
    }
}
