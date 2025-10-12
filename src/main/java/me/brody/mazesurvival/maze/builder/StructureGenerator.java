package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.builder.structure.MazeStructure;
import me.brody.mazesurvival.maze.builder.structure.MazeStructureGenerator;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.LocationCopier;
import me.brody.mazesurvival.utils.Vector3Int;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class StructureGenerator {
    private static final long GENERATION_DELAY = 25L;

    public Event<EventArgs> onStructureGenerationFinished;

    private final Main plugin;

    private MazeGrid grid;
    private Queue<MazeStructureGenerator> structures;
    private boolean isRunning;

    public StructureGenerator(Main plugin, MazeGrid grid) {
        onStructureGenerationFinished = new Event<>();
        this.plugin = plugin;
        this.grid = grid;
        structures = new ArrayDeque<>();
        isRunning = false;
    }

    public void start() {
        if(grid == null || isRunning)
            return;

        isRunning = true;
        initStructureQueue();
    }

    private void initStructureQueue() {
        Location gladeOrigin = grid.getGladeWorldOrigin();
        gladeOrigin.setY(gladeOrigin.getY() + 1);
        structures.add(new MazeStructure(grid.getGladeSchema(), gladeOrigin, 0));
        for(MazeRegion region : grid.getRegions()) {
            if(region.getHaven() != null && grid.getRegionHavenWorldOrigin(region) != null) {
                Location havenLocation = grid.getRegionHavenWorldOrigin(region);
                structures.add(new MazeStructure(region.getHaven().getSchematic(), havenLocation, region.getHaven().getDirection().rotation));
            }
            if(region.getBossRoom() != null && grid.getRegionBossRoomWorldOrigin(region) != null) {
                Location bossRoomLocation = grid.getRegionBossRoomWorldOrigin(region);
                structures.add(new MazeStructure(region.getBossRoom().getSchematic(), bossRoomLocation, region.getBossRoom().getDirection().rotation));
            }
        }

        processStructureQueue();
    }

    private void processStructureQueue() {
        if(structures.isEmpty())
            return;

        MazeStructureGenerator structure = structures.poll();
        structure.generateStructure();

        if(!structures.isEmpty())
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::processStructureQueue, GENERATION_DELAY);
        else {
            generateTriggers();
            isRunning = false;
            onStructureGenerationFinished.invoke(this, EventArgs.EMPTY);
        }
    }

    // The MazeStructure generate method could be refactored to include generating these triggers
    public void generateTriggers() {
        for(MazeRegion region : grid.getRegions()) {
            generateHavenTriggers(region);
            generateBossRoomTriggers(region);
        }
    }

    private void generateHavenTriggers(MazeRegion region) {
        CellExtension haven = region.getHaven();
        if(haven == null)
            return;

        final BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-3, 0, 0), new Vector3Int(3, 9, 0));
        final BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(0, 0, -3), new Vector3Int(0, 9, 3));
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
        Location primaryEntranceTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(primaryEntrance, getHavenEntranceTriggerConsumer(primaryEntranceTeleportLocation));

        BoundsInt primaryExit = primaryEntrance.clone();
        float cellCenterToPrimaryExitTeleport = ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset;
        Location primaryExitTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(primaryExit, getHavenExitTriggerConsumer(primaryExitTeleportLocation));

        BoundsInt secondaryEntrance = startingTrigger.clone();
        secondaryEntrance.shift(cellExtensionOrigin);
        int secondaryEntranceShift = (doorCellCentersDistance * directionFactor) + (distanceToCellCenter * (-directionFactor));
        float cellCenterToSecondaryEntranceTeleport = (secondaryEntranceShift - (teleportDistance * directionFactor)) + halfBlockOffset;
        Location secondaryEntranceTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(secondaryEntrance, getHavenEntranceTriggerConsumer(secondaryEntranceTeleportLocation));

        BoundsInt secondaryExit = secondaryEntrance.clone();
        float cellCenterToSecondaryExitTeleport = (secondaryEntranceShift + (3 * directionFactor)) + halfBlockOffset;
        Location secondaryExitTeleportLocation = LocationCopier.copy(cellExtensionOrigin);
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
        plugin.getCollisionManager().addTriggerBounds(secondaryExit, getHavenExitTriggerConsumer(secondaryExitTeleportLocation));
    }

    public void generateBossRoomTriggers(MazeRegion region) {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

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

    private Consumer<Player> getHavenEntranceTriggerConsumer(Location teleportLocation) {
        return (p) -> {
            if(plugin.getDayNightCycle().isDay()) {
                p.teleport(teleportLocation);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
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