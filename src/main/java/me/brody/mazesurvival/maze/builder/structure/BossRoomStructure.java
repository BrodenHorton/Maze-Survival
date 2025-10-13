package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.LocationCopier;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import me.brody.mazesurvival.utils.Vector3Int;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class BossRoomStructure implements MazeStructureGenerator {
    private final Main plugin;
    private MazeSchematic schematic;
    private Location origin;
    private double rotation;
    private MazeRegion region;

    public BossRoomStructure(Main plugin, MazeSchematic schematic, Location origin, double rotation, MazeRegion region) {
        this.plugin = plugin;
        this.schematic = schematic;
        this.origin = origin;
        this.rotation = rotation;
        this.region = region;
    }

    @Override
    public void generateStructure() {
        SchematicPaster.paste(origin, rotation, schematic.getSchematicInputStream());
        generateBossRoomTriggers();
    }

    public void generateBossRoomTriggers() {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        final BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-3, 0, 0), new Vector3Int(3, 9, 0));
        final BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(0, 0, -3), new Vector3Int(0, 9, 3));
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int entranceToExitDistance = 4;
        final int teleportDistance = 7;
        final float primaryDirectionYaw = (bossRoom.getDirection().id - 2) * 90;
        final float secondaryDirectionYaw = ((bossRoom.getDirection().id + 2 % 4) - 2) * 90;
        final float halfBlockOffset = 0.5f;

        boolean isMeridional = bossRoom.getDirection().id % 2 == 0;
        int directionFactor = bossRoom.getDirection().id == 1 || bossRoom.getDirection().id == 2 ? 1 : -1;
        BoundsInt startingTrigger = isMeridional ? meridionalTrigger : zonalTrigger;

        Location cellExtensionOrigin = grid.getRegionBossRoomWorldOrigin(region);
        BoundsInt entrance = startingTrigger.clone();
        entrance.shift(cellExtensionOrigin);
        float cellCenterToEntranceTeleport = ((distanceToCellCenter + teleportDistance) * directionFactor) + halfBlockOffset;
        Location entranceTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
        if(isMeridional) {
            entrance.shiftZ(distanceToCellCenter * directionFactor);
            entranceTeleportLocation.setX(entranceTeleportLocation.getX() + halfBlockOffset);
            entranceTeleportLocation.setZ(entranceTeleportLocation.getZ() + cellCenterToEntranceTeleport);
        }
        else {
            entrance.shiftX(distanceToCellCenter * directionFactor);
            entranceTeleportLocation.setX(entranceTeleportLocation.getX() + cellCenterToEntranceTeleport);
            entranceTeleportLocation.setZ(entranceTeleportLocation.getZ() + halfBlockOffset);
        }
        entranceTeleportLocation.setY(entranceTeleportLocation.getY() + 1);
        entranceTeleportLocation.setYaw((primaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(entrance, getBossRoomEntranceTriggerConsumer(entranceTeleportLocation));

        BoundsInt primaryExit = entrance.clone();
        float cellCenterToPrimaryExitTeleport = ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset;
        Location exitTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
        if(isMeridional) {
            primaryExit.shiftZ(entranceToExitDistance * directionFactor);
            exitTeleportLocation.setX(exitTeleportLocation.getX() + halfBlockOffset);
            exitTeleportLocation.setZ(exitTeleportLocation.getZ() + cellCenterToPrimaryExitTeleport);
        }
        else {
            primaryExit.shiftX(entranceToExitDistance * directionFactor);
            exitTeleportLocation.setX(exitTeleportLocation.getX() + cellCenterToPrimaryExitTeleport);
            exitTeleportLocation.setZ(exitTeleportLocation.getZ() + halfBlockOffset);
        }
        exitTeleportLocation.setY(exitTeleportLocation.getY() + 1);
        exitTeleportLocation.setYaw((secondaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(primaryExit, getBossRoomExitTriggerConsumer(exitTeleportLocation));
    }

    private Consumer<Player> getBossRoomEntranceTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        };
    }

    private Consumer<Player> getBossRoomExitTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            if(false) {
                p.teleport(teleportLocation);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            }
            else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot exit until the boss is defeated")));
            }
        };
    }
}
