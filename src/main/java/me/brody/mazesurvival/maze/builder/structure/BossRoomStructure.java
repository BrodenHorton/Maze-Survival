package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.*;
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
        SchematicPaster.paste(origin, rotation, true, schematic.getSchematicInputStream());
        generateBossRoomTriggers();
        generateProtectionBounds();
    }

    public void generateBossRoomTriggers() {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int triggerWidth = 7;
        final int triggerHeight = 10;
        final BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2 + 1, triggerHeight, 1));
        final BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(0, 0, -(triggerWidth / 2)), new Vector3Int(1, triggerHeight, triggerWidth / 2 + 1));
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
        Location entranceTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(entrance, getBossRoomEntranceConsumer(entranceTeleportLocation), null));

        BoundsInt primaryExit = entrance.clone();
        float cellCenterToPrimaryExitTeleport = ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset;
        Location exitTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(primaryExit, getBossRoomExitConsumer(exitTeleportLocation), null));
    }

    private Consumer<Player> getBossRoomEntranceConsumer(Location teleportLocation) {
        return (p) -> {
            if(region.getBossFight() != null)
                region.getBossFight().start(region);
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            ChatUtils.msg(p, "&aYou have triggered the boss fight!");
        };
    }

    private Consumer<Player> getBossRoomExitConsumer(Location teleportLocation) {
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

    private void generateProtectionBounds() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int bossRoomWidth = 25;
        final int bossRoomWallWidth = 1;
        final int bossRoomLength = grid.getMarginInBlocks() * 2 - grid.getWallWidth() - (bossRoomWallWidth * 2);
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int cellOriginToProtectionBounds = distanceToCellCenter + grid.getWallWidth() + bossRoomWallWidth;
        CellExtension bossRoom = region.getBossRoom();
        Location bossRoomOrigin = grid.getRegionBossRoomWorldOrigin(region);
        BoundsInt bossRoomBounds;
        if(bossRoom.getDirection() == Direction.NORTH)
            bossRoomBounds = new BoundsInt(new Vector3Int(-(bossRoomWidth / 2), 0, -cellOriginToProtectionBounds - bossRoomLength), new Vector3Int(bossRoomWidth / 2 + 1, grid.getWallHeight(), -cellOriginToProtectionBounds));
        else if(bossRoom.getDirection() == Direction.EAST)
            bossRoomBounds = new BoundsInt(new Vector3Int(cellOriginToProtectionBounds + 1, 0, -(bossRoomWidth / 2)), new Vector3Int(cellOriginToProtectionBounds + bossRoomLength + 1, grid.getWallHeight(), bossRoomWidth / 2 + 1));
        else if(bossRoom.getDirection() == Direction.SOUTH)
            bossRoomBounds = new BoundsInt(new Vector3Int(-(bossRoomWidth / 2), 0, cellOriginToProtectionBounds + 1), new Vector3Int(bossRoomWidth / 2 + 1, grid.getWallHeight(), cellOriginToProtectionBounds + bossRoomLength + 1));
        else
            bossRoomBounds = new BoundsInt(new Vector3Int(-cellOriginToProtectionBounds - bossRoomLength, 0, -(bossRoomWidth / 2)), new Vector3Int(-cellOriginToProtectionBounds, grid.getWallHeight(), bossRoomWidth / 2 + 1));
        bossRoomBounds.shift(bossRoomOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, bossRoomBounds, ProtectionType.PROTECTED));
    }
}
