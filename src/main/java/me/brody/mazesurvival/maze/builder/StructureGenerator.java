package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.builder.structure.MazeStructure;
import me.brody.mazesurvival.maze.builder.structure.MazeStructureGenerator;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;

import java.util.ArrayDeque;
import java.util.Queue;

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

        boolean isMeridional = haven.getDirection().id % 2 == 0;
        int directionFactor = haven.getDirection().id == 1 || haven.getDirection().id == 2 ? 1 : -1;
        BoundsInt startingTrigger = isMeridional ? meridionalTrigger : zonalTrigger;

        Location cellExtensionOrigin = grid.getRegionHavenWorldOrigin(region);
        BoundsInt primaryEntrance = startingTrigger.clone();
        primaryEntrance.shift(cellExtensionOrigin);
        if(isMeridional)
            primaryEntrance.shiftZ(distanceToCellCenter * directionFactor);
        else
            primaryEntrance.shiftX(distanceToCellCenter * directionFactor);
        plugin.getCollisionManager().addTriggerBounds(primaryEntrance, (p) -> {
            ChatUtils.msg(p, "&aHaven's primary door entrance triggered!");
        });

        BoundsInt primaryExit = primaryEntrance.clone();
        if(isMeridional)
            primaryExit.shiftZ(entranceToExitDistance * directionFactor);
        else
            primaryExit.shiftX(entranceToExitDistance * directionFactor);
        plugin.getCollisionManager().addTriggerBounds(primaryExit, (p) -> {
            ChatUtils.msg(p, "&aHaven's primary door exit trigger!");
        });

        BoundsInt secondaryEntrance = startingTrigger.clone();
        secondaryEntrance.shift(cellExtensionOrigin);
        int secondaryEntranceShift = (doorCellCentersDistance * directionFactor) + (distanceToCellCenter * (-directionFactor));
        if(isMeridional)
            secondaryEntrance.shiftZ(secondaryEntranceShift);
        else
            secondaryEntrance.shiftX(secondaryEntranceShift);
        plugin.getCollisionManager().addTriggerBounds(secondaryEntrance, (p) -> {
            ChatUtils.msg(p, "&aHaven's secondary door entrance trigger!");
        });

        BoundsInt secondaryExit = secondaryEntrance.clone();
        if(isMeridional)
            secondaryExit.shiftZ(entranceToExitDistance * (-directionFactor));
        else
            secondaryExit.shiftX(entranceToExitDistance * (-directionFactor));
        plugin.getCollisionManager().addTriggerBounds(secondaryExit, (p) -> {
            ChatUtils.msg(p, "&aHaven's secondary door exit trigger!");
        });
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
}