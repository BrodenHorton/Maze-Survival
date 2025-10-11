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
        if(isMeridional) {
            primaryEntrance.shiftZ(distanceToCellCenter * directionFactor);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + ((distanceToCellCenter + teleportDistance) * directionFactor) + halfBlockOffset);
            teleportLocation.setYaw((primaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(primaryEntrance, getHavenEntranceTriggerConsumer(teleportLocation));
        }
        else {
            primaryEntrance.shiftX(distanceToCellCenter * directionFactor);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + ((distanceToCellCenter + teleportDistance) * directionFactor) + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + halfBlockOffset);
            teleportLocation.setYaw((primaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(primaryEntrance, getHavenEntranceTriggerConsumer(teleportLocation));
        }

        BoundsInt primaryExit = primaryEntrance.clone();
        if(isMeridional) {
            primaryExit.shiftZ(entranceToExitDistance * directionFactor);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset);
            teleportLocation.setYaw((secondaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(primaryExit, getHavenExitTriggerConsumer(teleportLocation));
        }
        else {
            primaryExit.shiftX(entranceToExitDistance * directionFactor);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + ((distanceToCellCenter - 3) * directionFactor) + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + halfBlockOffset);
            teleportLocation.setYaw((secondaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(primaryExit, getHavenExitTriggerConsumer(teleportLocation));
        }

        BoundsInt secondaryEntrance = startingTrigger.clone();
        secondaryEntrance.shift(cellExtensionOrigin);
        int secondaryEntranceShift = (doorCellCentersDistance * directionFactor) + (distanceToCellCenter * (-directionFactor));
        if(isMeridional) {
            secondaryEntrance.shiftZ(secondaryEntranceShift);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + (secondaryEntranceShift - (teleportDistance * directionFactor)) + halfBlockOffset);
            teleportLocation.setYaw((secondaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(secondaryEntrance, getHavenEntranceTriggerConsumer(teleportLocation));
        }
        else {
            secondaryEntrance.shiftX(secondaryEntranceShift);
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + (secondaryEntranceShift - (teleportDistance * directionFactor)) + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + halfBlockOffset);
            teleportLocation.setYaw((secondaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(secondaryEntrance, getHavenEntranceTriggerConsumer(teleportLocation));
        }

        BoundsInt secondaryExit = secondaryEntrance.clone();
        if(isMeridional) {
            secondaryExit.shiftZ(entranceToExitDistance * (-directionFactor));
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + (secondaryEntranceShift + (3 * directionFactor)) + halfBlockOffset);
            teleportLocation.setYaw((primaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(secondaryExit, getHavenExitTriggerConsumer(teleportLocation));
        }
        else {
            secondaryExit.shiftX(entranceToExitDistance * (-directionFactor));
            Location teleportLocation = LocationCopier.copy(cellExtensionOrigin);
            teleportLocation.setX(teleportLocation.getX() + (secondaryEntranceShift + (3 * directionFactor)) + halfBlockOffset);
            teleportLocation.setY(teleportLocation.getY() + 1);
            teleportLocation.setZ(teleportLocation.getZ() + halfBlockOffset);
            teleportLocation.setYaw((primaryDirectionYaw));
            plugin.getCollisionManager().addTriggerBounds(secondaryExit, getHavenExitTriggerConsumer(teleportLocation));
        }
    }

    public void generateBossRoomTriggers(MazeRegion region) {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        final BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-3, 0, 0), new Vector3Int(3, 9, 0));
        final BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(0, 0, -3), new Vector3Int(0, 9, 3));
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int entranceToExitDistance = 4;

        boolean isMeridional = bossRoom.getDirection().id % 2 == 0;
        int directionFactor = bossRoom.getDirection().id == 1 || bossRoom.getDirection().id == 2 ? 1 : -1;
        BoundsInt startingTrigger = isMeridional ? meridionalTrigger : zonalTrigger;

        Location cellExtensionOrigin = grid.getRegionBossRoomWorldOrigin(region);
        BoundsInt entrance = startingTrigger.clone();
        entrance.shift(cellExtensionOrigin);
        if(isMeridional)
            entrance.shiftZ(distanceToCellCenter * directionFactor);
        else
            entrance.shiftX(distanceToCellCenter * directionFactor);
        plugin.getCollisionManager().addTriggerBounds(entrance, (p) -> {
            ChatUtils.msg(p, "&aBoss Room entrance triggered!");
        });

        BoundsInt exit = entrance.clone();
        if(isMeridional)
            exit.shiftZ(entranceToExitDistance * directionFactor);
        else
            exit.shiftX(entranceToExitDistance * directionFactor);
        plugin.getCollisionManager().addTriggerBounds(exit, (p) -> {
            ChatUtils.msg(p, "&aBoss Room exit trigger!");
        });
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
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        };
    }
}