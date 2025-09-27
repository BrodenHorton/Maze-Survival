package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.builder.structure.*;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellOrientation;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Location;

import java.util.*;

public class DecoratorGenerator {
    private static final int GENERATION_BATCH_SIZE = 20;
    private static final long GENERATION_DELAY = 15L;
    private static final double DECORATION_DENSITY = 0.08;
    private static final double TRAP_DENSITY = 0.05;

    public Event<EventArgs> onMazeDecoratorFinished;

    private final Main plugin;
    private final Random RNG;

    private MazeGrid grid;
    private Queue<MazeStructureGenerator> decorations;
    private Map<String, Integer> trapWeights;
    private boolean isRunning;

    public DecoratorGenerator(Main plugin, MazeGrid grid) {
        onMazeDecoratorFinished = new Event<>();
        this.plugin = plugin;
        RNG = new Random();
        this.grid = grid;
        decorations = new ArrayDeque<>();
        trapWeights = new HashMap<>();
        trapWeights.put("blindness", 2);
        trapWeights.put("slowness", 2);
        trapWeights.put("teleportation", 1);
        isRunning = false;
    }

    public void start() {
        if(grid == null || isRunning)
            return;

        isRunning = true;
        initDecorationsQueue();
        processDecorationQueue();
    }

    private void initDecorationsQueue() {
        for(int regionIndex = grid.getRegions().size() - 1; regionIndex >= 0; regionIndex--) {
            MazeRegion region = grid.getRegions().get(regionIndex);
            List<Vector2Int> cellCoords = new ArrayList<>();
            for(int i = 0; i < region.getMazeCells().length; i++) {
                for(int j = 0; j < region.getMazeCells()[0].length; j++) {
                    if(region.getMazeCells()[i][j] == null)
                        continue;

                    cellCoords.add(new Vector2Int(j, i));
                }
            }

            Collections.shuffle(cellCoords);
            int decorationCount = (int)Math.ceil(cellCoords.size() * DECORATION_DENSITY);
            for(int i = 0; i < decorationCount; i++) {
                MazeCell cell = region.getMazeCells()[cellCoords.get(i).y][cellCoords.get(i).x];
                CellOrientation orientation = cell.getCellOrientation();
                MazeSchematic decoration = region.getDecoration().getRandomDecoration(orientation);
                if(decoration == null)
                    continue;

                Location cellCenter = grid.getRegionCellWorldCenter(region, cellCoords.get(i).y, cellCoords.get(i).x);
                decorations.add(new CellDecoration(decoration, cell, cellCenter));
            }

            HashMap<CellOrientation, List<MazeSchematic>> uniqueDecorationsByOrientation = new HashMap<>();
            for(Map.Entry<CellOrientation, List<MazeSchematic>> entry : region.getDecoration().getUniqueDecorations().entrySet()) {
                uniqueDecorationsByOrientation.put(entry.getKey(), new ArrayList<>());
                for(int i = 0; i < entry.getValue().size(); i++)
                    uniqueDecorationsByOrientation.get(entry.getKey()).add(entry.getValue().get(i));
            }

            for(int i = cellCoords.size() - 1; i >= 0 && !uniqueDecorationsByOrientation.isEmpty(); i--) {
                MazeCell cell = region.getMazeCells()[cellCoords.get(i).y][cellCoords.get(i).x];
                CellOrientation orientation = cell.getCellOrientation();
                if(!uniqueDecorationsByOrientation.containsKey(orientation))
                    continue;
                if(uniqueDecorationsByOrientation.get(orientation).isEmpty()) {
                    continue;
                }

                Location cellCenter = grid.getRegionCellWorldCenter(region, cellCoords.get(i).y, cellCoords.get(i).x);
                decorations.add(new CellDecoration(uniqueDecorationsByOrientation.get(orientation).get(0), cell, cellCenter));

                // Remove the cell coords that the unique cell was placed in so when we add trap cells they
                // don't spawn in the unique cells
                cellCoords.remove(i);
                uniqueDecorationsByOrientation.get(orientation).remove(0);
            }

            Collections.shuffle(cellCoords);
            int trapCount = (int)Math.ceil(cellCoords.size() * TRAP_DENSITY);
            for(int i = 0; i < trapCount; i++) {
                Location cellCenter = grid.getRegionCellWorldCenter(region, cellCoords.get(i).y, cellCoords.get(i).x);
                CellTrap cellTrap = getRandomTrapByWeight(cellCenter);
                if(cellTrap != null)
                    decorations.add(cellTrap);
            }
        }

    }

    private void processDecorationQueue() {
        for(int i = 0; i < GENERATION_BATCH_SIZE; i++) {
            if(decorations.isEmpty())
                break;

            MazeStructureGenerator decoration = decorations.poll();
            decoration.generateStructure();
        }

        if(!decorations.isEmpty())
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::processDecorationQueue, GENERATION_DELAY);
        else {
            isRunning = false;
            onMazeDecoratorFinished.invoke(this, EventArgs.EMPTY);
        }
    }

    private CellTrap getRandomTrapByWeight(Location cellCenter) {
        if(trapWeights == null || trapWeights.isEmpty())
            return null;

        int totalWeight = 0;
        for(Map.Entry<String, Integer> entry : trapWeights.entrySet())
            totalWeight += entry.getValue();

        String resultStr = null;
        int weightValue = RNG.nextInt(0, totalWeight + 1);
        for(Map.Entry<String, Integer> entry : trapWeights.entrySet()) {
            if(weightValue <= entry.getValue()) {
                resultStr = entry.getKey();
                break;
            }

            weightValue -= entry.getValue();
        }

        CellTrap cellTrap = null;
        if(resultStr != null) {
            switch(resultStr) {
                case "blindness":
                    cellTrap = new BlindnessCellTrap(cellCenter);
                    break;
                case "slowness":
                    cellTrap = new SlownessCellTrap(cellCenter);
                    break;
                case "teleportation":
                    cellTrap = new TeleportationCellTrap(cellCenter);
                    break;
                default:
                    break;
            }
        }

        return cellTrap;
    }

}
