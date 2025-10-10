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

    public void generateTriggers() {
        for(MazeRegion region : grid.getRegions()) {
            generateHavenTriggers(region);

            if(region.getBossRoom() != null) {
                CellExtension bossRoom = region.getBossRoom();
                Location bossRoomLocation = grid.getRegionBossRoomWorldOrigin(region);

            }
        }
    }

    private void generateHavenTriggers(MazeRegion region) {
        if(region.getHaven() == null)
            return;

        // TODO: Shrink bounding boxes to 1 depth
        BoundsInt meridionalTrigger = new BoundsInt(new Vector3Int(-3, 0, -1), new Vector3Int(3, 9, 1));
        BoundsInt zonalTrigger = new BoundsInt(new Vector3Int(-1, 0, -3), new Vector3Int(1, 9, 3));
        int distanceBetweenDoorCellCenters = (grid.getMarginInBlocks() * 2) + grid.getRegionCellSize();

        if(region.getHaven().getDirection() == Direction.WEST) {
            Location havenCellExtensionOrigin = grid.getRegionHavenWorldOrigin(region);
            BoundsInt primaryEntrance = zonalTrigger.clone();
            primaryEntrance.shift(havenCellExtensionOrigin);
            primaryEntrance.shiftX(-((grid.getRegionCellSize() - grid.getWallWidth()) / 2) - 1);
            plugin.getCollisionManager().addTriggerBounds(primaryEntrance, (p) -> {
                ChatUtils.msg(p, "&aHaven's primary door entrance triggered!");
            });

            BoundsInt primaryExit = primaryEntrance.clone();
            primaryExit.shiftX(-2);
            plugin.getCollisionManager().addTriggerBounds(primaryExit, (p) -> {
                ChatUtils.msg(p, "&aHaven's primary door exit trigger!");
            });

            BoundsInt secondaryEntrance = zonalTrigger.clone();
            secondaryEntrance.shift(havenCellExtensionOrigin);
            secondaryEntrance.shiftX(-distanceBetweenDoorCellCenters + ((grid.getRegionCellSize() - grid.getWallWidth()) / 2) + 1);
            plugin.getCollisionManager().addTriggerBounds(secondaryEntrance, (p) -> {
                ChatUtils.msg(p, "&aHaven's secondary door entrance trigger!");
            });

            BoundsInt secondaryExit = secondaryEntrance.clone();
            secondaryExit.shiftX(2);
            plugin.getCollisionManager().addTriggerBounds(secondaryExit, (p) -> {
                ChatUtils.msg(p, "&aHaven's secondary door exit trigger!");
            });
        }
    }

}