package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
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
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int entranceToExitDistance = 4;
        final int doorCellCentersDistance = (grid.getMarginInBlocks() * 2) + grid.getRegionCellSize();
        final int teleportDistance = 7;
        final float primaryDirectionYaw = (haven.getDirection().id - 2) * 90;
        final float secondaryDirectionYaw = ((haven.getDirection().id + 2 % 4) - 2) * 90;
        Location havenOrigin = grid.getRegionHavenWorldOrigin(region);

        BoundsInt doorBounds =  new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2, triggerHeight, 0));

        BoundsInt primaryEntrance = doorBounds.clone();
        primaryEntrance.shiftZ(-distanceToCellCenter);
        primaryEntrance.rotateY(-haven.getDirection().rotation);
        primaryEntrance.shift(havenOrigin);
        float cellCenterToPrimaryEntranceTeleport = distanceToCellCenter + teleportDistance;
        Location primaryEntranceTeleportLocation = new Location(grid.getWorld(), 0, 1, -cellCenterToPrimaryEntranceTeleport, primaryDirectionYaw, 0);
        primaryEntranceTeleportLocation = LocationUtils.rotate(primaryEntranceTeleportLocation, -haven.getDirection().rotation);
        primaryEntranceTeleportLocation = LocationUtils.shift(primaryEntranceTeleportLocation, havenOrigin);
        primaryEntranceTeleportLocation = LocationUtils.centerOnBlock(primaryEntranceTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(primaryEntrance, getHavenEntranceTriggerConsumer(primaryEntranceTeleportLocation), null));

        BoundsInt primaryExit = doorBounds.clone();
        primaryExit.shiftZ(-distanceToCellCenter - entranceToExitDistance);
        primaryExit.rotateY(-haven.getDirection().rotation);
        primaryExit.shift(havenOrigin);
        float cellCenterToPrimaryExitTeleport = distanceToCellCenter - 3;
        Location primaryExitTeleportLocation = new Location(grid.getWorld(), 0, 1, -cellCenterToPrimaryExitTeleport, secondaryDirectionYaw, 0);
        primaryExitTeleportLocation = LocationUtils.rotate(primaryExitTeleportLocation, -haven.getDirection().rotation);
        primaryExitTeleportLocation = LocationUtils.shift(primaryExitTeleportLocation, havenOrigin);
        primaryExitTeleportLocation = LocationUtils.centerOnBlock(primaryExitTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(primaryExit, getHavenExitTriggerConsumer(primaryExitTeleportLocation), null));

        final int secondaryEntranceShift = doorCellCentersDistance - distanceToCellCenter;
        BoundsInt secondaryEntrance = doorBounds.clone();
        secondaryEntrance.shiftZ(-secondaryEntranceShift);
        secondaryEntrance.rotateY(-haven.getDirection().rotation);
        secondaryEntrance.shift(havenOrigin);
        final float cellCenterToSecondaryEntranceTeleport = secondaryEntranceShift - teleportDistance;
        Location secondaryEntranceTeleportLocation = new Location(grid.getWorld(), 0, 1, -cellCenterToSecondaryEntranceTeleport, secondaryDirectionYaw, 0);
        secondaryEntranceTeleportLocation = LocationUtils.rotate(secondaryEntranceTeleportLocation, -haven.getDirection().rotation);
        secondaryEntranceTeleportLocation = LocationUtils.shift(secondaryEntranceTeleportLocation, havenOrigin);
        secondaryEntranceTeleportLocation = LocationUtils.centerOnBlock(secondaryEntranceTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(secondaryEntrance, getHavenEntranceTriggerConsumer(secondaryEntranceTeleportLocation), null));

        BoundsInt secondaryExit = doorBounds.clone();
        secondaryExit.shiftZ(-secondaryEntranceShift + entranceToExitDistance);
        secondaryExit.rotateY(-haven.getDirection().rotation);
        secondaryExit.shift(havenOrigin);
        final float cellCenterToSecondaryExitTeleport = secondaryEntranceShift + 3;
        Location secondaryExitTeleportLocation = new Location(grid.getWorld(), 0, 1, -cellCenterToSecondaryExitTeleport, primaryDirectionYaw, 0);
        secondaryExitTeleportLocation = LocationUtils.rotate(secondaryExitTeleportLocation, -haven.getDirection().rotation);
        secondaryExitTeleportLocation = LocationUtils.shift(secondaryExitTeleportLocation, havenOrigin);
        secondaryExitTeleportLocation = LocationUtils.centerOnBlock(secondaryExitTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(secondaryExit, getHavenExitTriggerConsumer(secondaryExitTeleportLocation), null));
    }

    private Consumer<Player> getHavenEntranceTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            if(region.getRegionLevelRequirement() > plugin.getGameState().getClearedRegions().size()) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cUnable to enter haven. Required region level is too high.")));
                return;
            }

            if(plugin.getDayNightCycle().isDay()) {
                if(!plugin.getGameState().getDiscoveredRegions().contains(region.getUuid())) {
                    plugin.getGameState().addDiscoveredRegion(region);
                    for(Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.2f, 1f);
                        ChatUtils.msg(onlinePlayer, "&dNew Region discovered!");
                        ChatUtils.msg(onlinePlayer, "&dNew recipes have been unlocked. View these new recipes with &e/ms recipes");
                    }
                }
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

        BoundsInt buildableBounds = new BoundsInt(new Vector3Int(-(havenWidth / 2), -havenDepth, -havenLength), new Vector3Int(havenWidth / 2, grid.getWallHeight(), -1));
        buildableBounds.shiftZ(-cellOriginToProtectionBounds);
        buildableBounds.rotateY(-haven.getDirection().rotation);
        buildableBounds.shift(havenOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, buildableBounds, ProtectionType.BUILDABLE));

        final int doorWidth = 7;
        final int doorLength = 3;
        final int doorHeight = 20;
        final int cellCenterToDoorBoundsCenter = distanceToCellCenter + grid.getWallWidth() + havenWallWidth + 1;
        final int doorCellCentersDistance = (grid.getMarginInBlocks() * 2) + grid.getRegionCellSize();

        BoundsInt doorBounds = new BoundsInt(new Vector3Int(-doorWidth / 2, 1, -doorLength / 2), new Vector3Int(doorWidth / 2, doorHeight, doorLength / 2));
        BoundsInt primaryDoorBounds = doorBounds.clone();
        primaryDoorBounds.shiftZ(-cellCenterToDoorBoundsCenter);
        primaryDoorBounds.rotateY(-haven.getDirection().rotation);
        primaryDoorBounds.shift(havenOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, primaryDoorBounds, ProtectionType.PROTECTED));

        BoundsInt secondaryDoorBounds = doorBounds.clone();
        secondaryDoorBounds.shiftZ(-doorCellCentersDistance + cellCenterToDoorBoundsCenter);
        secondaryDoorBounds.rotateY(-haven.getDirection().rotation);
        secondaryDoorBounds.shift(havenOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, secondaryDoorBounds, ProtectionType.PROTECTED));
    }
}
