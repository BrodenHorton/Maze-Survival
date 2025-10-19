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
import me.brody.mazesurvival.utils.LocationUtils;
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

public class HavenStructure implements MazeStructureGenerator {
    private final Main plugin;
    private MazeSchematic schematic;
    private Location origin;
    private double rotation;
    private MazeRegion region;

    public HavenStructure(Main plugin, MazeSchematic schematic, Location origin, double rotation, MazeRegion region) {
        this.plugin = plugin;
        this.schematic = schematic;
        this.origin = origin;
        this.rotation = rotation;
        this.region = region;
    }

    @Override
    public void generateStructure() {
        SchematicPaster.paste(origin, rotation, true, schematic.getSchematicInputStream());
        generateHavenTriggers();
        generateProtectionBounds();
    }

    private void generateHavenTriggers() {
        CellExtension haven = region.getHaven();
        if(haven == null)
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int triggerWidth = 7;
        final int triggerHeight = 10;
        final BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2 + 1, triggerHeight, 1));
        final BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(0, 0, -(triggerWidth / 2)), new Vector3Int(1, triggerHeight, triggerWidth / 2 + 1));
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int entranceToExitDistance = 4;
        final int doorCellCentersDistance = (grid.getMarginInBlocks() * 2) + grid.getRegionCellSize();
        final int teleportDistance = 7;
        final float primaryDirectionYaw = (haven.getDirection().id - 2) * 90;
        final float secondaryDirectionYaw = ((haven.getDirection().id + 2 % 4) - 2) * 90;
        final float halfBlockOffset = 0.5f;

        boolean isMeridional = haven.getDirection().id % 2 == 0;
        int directionFactor = haven.getDirection().id == 1 || haven.getDirection().id == 2 ? 1 : -1;
        BoundsInt startingTrigger = isMeridional ? meridionalTrigger : zonalTrigger;

        Location cellExtensionOrigin = grid.getRegionHavenWorldOrigin(region);
        BoundsInt primaryEntrance = startingTrigger.clone();
        primaryEntrance.shift(cellExtensionOrigin);
        float cellCenterToPrimaryEntranceTeleport = ((distanceToCellCenter + teleportDistance) * directionFactor) + halfBlockOffset;
        Location primaryEntranceTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
        if(isMeridional) {
            primaryEntrance.shiftZ(distanceToCellCenter * directionFactor);
            primaryEntranceTeleportLocation.setX(primaryEntranceTeleportLocation.getX() + halfBlockOffset);
            primaryEntranceTeleportLocation.setZ(primaryEntranceTeleportLocation.getZ() + cellCenterToPrimaryEntranceTeleport);
        }
        else {
            primaryEntrance.shiftX(distanceToCellCenter * directionFactor);
            primaryEntranceTeleportLocation.setX(primaryEntranceTeleportLocation.getX() + cellCenterToPrimaryEntranceTeleport);
            primaryEntranceTeleportLocation.setZ(primaryEntranceTeleportLocation.getZ() + halfBlockOffset);
        }
        primaryEntranceTeleportLocation.setY(primaryEntranceTeleportLocation.getY() + 1);
        primaryEntranceTeleportLocation.setYaw((primaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(primaryEntrance, getHavenEntranceTriggerConsumer(primaryEntranceTeleportLocation), null));

        BoundsInt primaryExit = primaryEntrance.clone();
        float cellCenterToPrimaryExitTeleport = ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset;
        Location primaryExitTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
        if(isMeridional) {
            primaryExit.shiftZ(entranceToExitDistance * directionFactor);
            primaryExitTeleportLocation.setX(primaryExitTeleportLocation.getX() + halfBlockOffset);
            primaryExitTeleportLocation.setZ(primaryExitTeleportLocation.getZ() + cellCenterToPrimaryExitTeleport);
        }
        else {
            primaryExit.shiftX(entranceToExitDistance * directionFactor);
            primaryExitTeleportLocation.setX(primaryExitTeleportLocation.getX() + cellCenterToPrimaryExitTeleport);
            primaryExitTeleportLocation.setZ(primaryExitTeleportLocation.getZ() + halfBlockOffset);
        }
        primaryExitTeleportLocation.setY(primaryExitTeleportLocation.getY() + 1);
        primaryExitTeleportLocation.setYaw((secondaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(primaryExit, getHavenExitTriggerConsumer(primaryExitTeleportLocation), null));

        BoundsInt secondaryEntrance = startingTrigger.clone();
        secondaryEntrance.shift(cellExtensionOrigin);
        int secondaryEntranceShift = (doorCellCentersDistance * directionFactor) + (distanceToCellCenter * (-directionFactor));
        float cellCenterToSecondaryEntranceTeleport = (secondaryEntranceShift - (teleportDistance * directionFactor)) + halfBlockOffset;
        Location secondaryEntranceTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
        if(isMeridional) {
            secondaryEntrance.shiftZ(secondaryEntranceShift);
            secondaryEntranceTeleportLocation.setX(secondaryEntranceTeleportLocation.getX() + halfBlockOffset);
            secondaryEntranceTeleportLocation.setZ(secondaryEntranceTeleportLocation.getZ() + cellCenterToSecondaryEntranceTeleport);
        }
        else {
            secondaryEntrance.shiftX(secondaryEntranceShift);
            secondaryEntranceTeleportLocation.setX(secondaryEntranceTeleportLocation.getX() + cellCenterToSecondaryEntranceTeleport);
            secondaryEntranceTeleportLocation.setZ(secondaryEntranceTeleportLocation.getZ() + halfBlockOffset);
        }
        secondaryEntranceTeleportLocation.setY(secondaryEntranceTeleportLocation.getY() + 1);
        secondaryEntranceTeleportLocation.setYaw((secondaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(secondaryEntrance, getHavenEntranceTriggerConsumer(secondaryEntranceTeleportLocation), null));

        BoundsInt secondaryExit = secondaryEntrance.clone();
        float cellCenterToSecondaryExitTeleport = (secondaryEntranceShift + (3 * directionFactor)) + halfBlockOffset;
        Location secondaryExitTeleportLocation = LocationUtils.copy(cellExtensionOrigin);
        if(isMeridional) {
            secondaryExit.shiftZ(entranceToExitDistance * (-directionFactor));
            secondaryExitTeleportLocation.setX(secondaryExitTeleportLocation.getX() + halfBlockOffset);
            secondaryExitTeleportLocation.setZ(secondaryExitTeleportLocation.getZ() + cellCenterToSecondaryExitTeleport);
        }
        else {
            secondaryExit.shiftX(entranceToExitDistance * (-directionFactor));
            secondaryExitTeleportLocation.setX(secondaryExitTeleportLocation.getX() + cellCenterToSecondaryExitTeleport);
            secondaryExitTeleportLocation.setZ(secondaryExitTeleportLocation.getZ() + halfBlockOffset);
        }
        secondaryExitTeleportLocation.setY(secondaryExitTeleportLocation.getY() + 1);
        secondaryExitTeleportLocation.setYaw((primaryDirectionYaw));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(secondaryExit, getHavenExitTriggerConsumer(secondaryExitTeleportLocation), null));
    }

    private Consumer<Player> getHavenEntranceTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            if(plugin.getDayNightCycle().isDay()) {
                p.teleport(teleportLocation);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
                plugin.getRespawnManager().setPlayerRespawnLocation(p, teleportLocation);
            }
            else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot enter a Haven at night")));
            }
        };
    }

    private Consumer<Player> getHavenExitTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            if(plugin.getDayNightCycle().isDay()) {
                p.teleport(teleportLocation);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            }
            else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot exit a Haven at night")));
            }
        };
    }

    private void generateProtectionBounds() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int havenWidth = 25;
        final int havenDepth = 4;
        final int havenWallWidth = 1;
        final int havenLength = grid.getMarginInBlocks() * 2 - grid.getWallWidth() - (havenWallWidth * 2);
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int cellOriginToProtectionBounds = distanceToCellCenter + grid.getWallWidth() + havenWallWidth;
        CellExtension haven = region.getHaven();
        Location havenOrigin = grid.getRegionHavenWorldOrigin(region);
        BoundsInt havenBounds;
        if(haven.getDirection() == Direction.NORTH)
            havenBounds = new BoundsInt(new Vector3Int(-(havenWidth / 2), -havenDepth, -cellOriginToProtectionBounds - havenLength), new Vector3Int(havenWidth / 2 + 1, grid.getWallHeight(), -cellOriginToProtectionBounds));
        else if(haven.getDirection() == Direction.EAST)
            havenBounds = new BoundsInt(new Vector3Int(cellOriginToProtectionBounds + 1, -havenDepth, -(havenWidth / 2)), new Vector3Int(cellOriginToProtectionBounds + havenLength + 1, grid.getWallHeight(), havenWidth / 2 + 1));
        else if(haven.getDirection() == Direction.SOUTH)
            havenBounds = new BoundsInt(new Vector3Int(-(havenWidth / 2), -havenDepth, cellOriginToProtectionBounds + 1), new Vector3Int(havenWidth / 2 + 1, grid.getWallHeight(), cellOriginToProtectionBounds + havenLength + 1));
        else
            havenBounds = new BoundsInt(new Vector3Int(-cellOriginToProtectionBounds - havenLength, -havenDepth, -(havenWidth / 2)), new Vector3Int(-cellOriginToProtectionBounds, grid.getWallHeight(), havenWidth / 2 + 1));
        havenBounds.shift(havenOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, havenBounds, ProtectionType.BUILDABLE));

        final int doorWidth = 7;
        final int doorLength = 3;
        final int doorHeight = 20;
        final BoundsInt meridionalBounds = new BoundsInt(new Vector3Int(-doorWidth / 2, 0, -doorLength / 2), new Vector3Int(doorWidth / 2 + 1, doorHeight, doorLength / 2 + 1));
        final BoundsInt zonalBounds = new BoundsInt(new Vector3Int(-doorLength / 2, 0, -doorWidth / 2), new Vector3Int(doorLength / 2 + 1, doorHeight, doorWidth / 2 + 1));
        final boolean isMeridional = haven.getDirection().id % 2 == 0;
        final int directionFactor = haven.getDirection().id == 1 || haven.getDirection().id == 2 ? 1 : -1;
        final int cellCenterToDoorBoundsCenter = distanceToCellCenter + grid.getWallWidth() + havenWallWidth + 1;
        final int doorCellCentersDistance = (grid.getMarginInBlocks() * 2) + grid.getRegionCellSize();
        BoundsInt primaryDoorBounds = isMeridional ? meridionalBounds.clone() : zonalBounds.clone();
        primaryDoorBounds.shift(havenOrigin);
        primaryDoorBounds.shiftY(1);
        BoundsInt secondaryDoorBounds = primaryDoorBounds.clone();
        if(isMeridional) {
            primaryDoorBounds.shiftZ(cellCenterToDoorBoundsCenter * directionFactor);
            secondaryDoorBounds.shiftZ(doorCellCentersDistance * directionFactor);
            secondaryDoorBounds.shiftZ(cellCenterToDoorBoundsCenter * (-directionFactor));
        }
        else {
            primaryDoorBounds.shiftX(cellCenterToDoorBoundsCenter * directionFactor);
            secondaryDoorBounds.shiftX(doorCellCentersDistance * directionFactor);
            secondaryDoorBounds.shiftX(cellCenterToDoorBoundsCenter * (-directionFactor));
        }
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, primaryDoorBounds, ProtectionType.PROTECTED));
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, secondaryDoorBounds, ProtectionType.PROTECTED));
    }
}
