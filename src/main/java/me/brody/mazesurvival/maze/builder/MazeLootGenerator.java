package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.Vector2Int;
import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class MazeLootGenerator {
    private static final Random RNG = new Random();
    private static final int GENERATION_BATCH_SIZE = 50;
    private static final long GENERATION_DELAY = 15L;
    private static final double CHEST_DENSITY = 0.45;
    private static final double TRAP_CHEST_CHANCE = 0.15;
    private static final double POT_DENSITY = 0.03;

    public Event<EventArgs> onLootGenerationFinished;

    private final Main plugin;

    private MazeGrid grid;
    private Queue<LootNode> lootNodes;
    private Map<Direction, BlockFace> chestFacingByWallDir;
    private WeightedList<String> trapChestWeights;
    private boolean isRunning;

    private static class LootNode {
        public Material material;
        public Location location;
        public BlockFace facing;

        public LootNode(Material material, Location location, BlockFace facing) {
            this.material = material;
            this.location = location;
            this.facing = facing;
        }
    }

    public MazeLootGenerator(Main plugin, MazeGrid grid) {
        onLootGenerationFinished = new Event<>();
        this.plugin = plugin;
        this.grid = grid;
        lootNodes = new ArrayDeque<>();
        isRunning = false;

        chestFacingByWallDir = new HashMap<>();
        chestFacingByWallDir.put(Direction.NORTH, BlockFace.SOUTH);
        chestFacingByWallDir.put(Direction.EAST, BlockFace.WEST);
        chestFacingByWallDir.put(Direction.SOUTH, BlockFace.NORTH);
        chestFacingByWallDir.put(Direction.WEST, BlockFace.EAST);

        trapChestWeights = new WeightedList<>();
        trapChestWeights.add("splash-potion", 2);
        trapChestWeights.add("tnt", 2);
        trapChestWeights.add("ambush", 2);
        trapChestWeights.add("teleportation", 1);
    }

    public void start() {
        if(grid == null || isRunning)
            return;

        isRunning = true;
        initializeLoot();
    }

    private void initializeLoot() {
        for(int regionIndex = grid.getRegions().size() -1; regionIndex >= 0; regionIndex--) {
            MazeRegion region = grid.getRegions().get(regionIndex);
            List<Vector2Int> deadEndIndices = new ArrayList<>();
            List<Vector2Int> corridorIndices = new ArrayList<>();
            for(int i = 0; i < region.getMazeCells().length; i++) {
                for(int j = 0; j < region.getMazeCells()[0].length; j++) {
                    if(region.getMazeCells()[i][j] == null)
                        continue;

                    int wallCount = getNumberOfStandingWalls(region.getMazeCells()[i][j]);
                    if(wallCount > 2)
                        deadEndIndices.add(new Vector2Int(j, i));
                    else if(wallCount > 0)
                        corridorIndices.add(new Vector2Int(j, i));
                }
            }

            FillLootNodeQueue(region, deadEndIndices, CHEST_DENSITY, Material.CHEST);
            FillLootNodeQueue(region, corridorIndices, POT_DENSITY, Material.DECORATED_POT);
        }

        processLootQueue();
    }

    private void processLootQueue() {
        for(int i = 0; i < GENERATION_BATCH_SIZE; i++) {
            if(lootNodes.isEmpty())
                break;

            LootNode lootNode = lootNodes.poll();
            if(lootNode.material == Material.CHEST && RNG.nextDouble(0, 1) <= TRAP_CHEST_CHANCE)
                lootNode.material = Material.TRAPPED_CHEST;
            lootNode.location.getBlock().setType(lootNode.material);
            if((lootNode.location.getBlock().getType() == Material.CHEST || lootNode.location.getBlock().getType() == Material.DECORATED_POT) && lootNode.location.getBlock().getState() instanceof TileState tileState) {
                tileState.getPersistentDataContainer().set(NamespacedKeys.GENERATE_LOOT, PersistentDataType.INTEGER, 1);
                if(lootNode.location.getBlock().getType() == Material.CHEST) {
                    tileState.getPersistentDataContainer().set(NamespacedKeys.BREAK_ON_CLOSE, PersistentDataType.INTEGER, 1);
                }
                tileState.update();
            }
            else if(lootNode.location.getBlock().getType() == Material.TRAPPED_CHEST && lootNode.location.getBlock().getState() instanceof TileState tileState) {
                String trapType = trapChestWeights.getWeightedValue();
                tileState.getPersistentDataContainer().set(NamespacedKeys.TRAP_CHEST, PersistentDataType.STRING, trapType);
                if(trapType.equals("tnt") || trapType.equals("ambush")) {
                    tileState.getPersistentDataContainer().set(NamespacedKeys.BREAK_ON_OPEN, PersistentDataType.INTEGER, 1);
                }
                tileState.update();
            }
            BlockData blockData = lootNode.location.getBlock().getBlockData();
            if(lootNode.facing != null && blockData instanceof Directional directional) {
                directional.setFacing(lootNode.facing);
                lootNode.location.getBlock().setBlockData(directional);
            }
        }

        if(!lootNodes.isEmpty())
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::processLootQueue, GENERATION_DELAY);
        else {
            isRunning = false;
            onLootGenerationFinished.invoke(this, EventArgs.EMPTY);
        }
    }

    private void FillLootNodeQueue(MazeRegion region, List<Vector2Int> cellIndices, double lootDensity, Material material) {
        int lootCount = (int)Math.ceil(cellIndices.size() * lootDensity);
        plugin.getLogger().info(material + " count: " + lootCount);
        Collections.shuffle(cellIndices);
        for(int i = 0; i < lootCount; i++) {
            Location cellOrigin = grid.getRegionCellWorldOrigin(region, cellIndices.get(i));
            Direction wallDir = getRandomDirWithWall(region.getMazeCells()[cellIndices.get(i).y][cellIndices.get(i).x]);
            Location lootLocation = getRandomCellPerimeterLocation(cellOrigin, wallDir);

            lootNodes.add(new LootNode(material, lootLocation, chestFacingByWallDir.get(wallDir)));
        }
    }

    private Location getRandomCellPerimeterLocation(Location cellOrigin, Direction wallDir) {
        Location lootLocation = new Location(cellOrigin.getWorld(), cellOrigin.getX(), cellOrigin.getY() + 1, cellOrigin.getZ());
        if (wallDir == Direction.NORTH) {
            lootLocation.setX(lootLocation.getX() + RNG.nextInt(0, grid.getRegionCellSize() - grid.getWallWidth()));
        } else if (wallDir == Direction.EAST) {
            lootLocation.setX(lootLocation.getX() + grid.getRegionCellSize() - grid.getWallWidth() - 1);
            lootLocation.setZ(lootLocation.getZ() + RNG.nextInt(0, grid.getRegionCellSize() - grid.getWallWidth()));
        } else if (wallDir == Direction.SOUTH) {
            lootLocation.setX(lootLocation.getX() + RNG.nextInt(0, grid.getRegionCellSize() - grid.getWallWidth()));
            lootLocation.setZ(lootLocation.getZ() + grid.getRegionCellSize() - grid.getWallWidth() - 1);
        } else {
            lootLocation.setZ(lootLocation.getZ() + RNG.nextInt(0, grid.getRegionCellSize() - grid.getWallWidth()));
        }

        return lootLocation;
    }

    private int getNumberOfStandingWalls(MazeCell cell) {
        int count = 0;
        for(int i = 0; i < cell.walls.length; i++) {
            if(cell.walls[i])
                count++;
        }

        return count;
    }

    private Direction getRandomDirWithWall(MazeCell cell) {
        List<Direction> dirs = new ArrayList<>();
        if(cell.walls[0])
            dirs.add(Direction.NORTH);
        if(cell.walls[1])
            dirs.add(Direction.EAST);
        if(cell.walls[2])
            dirs.add(Direction.SOUTH);
        if(cell.walls[3])
            dirs.add(Direction.WEST);

        Random rand = new Random();
        return dirs != null ? dirs.get(rand.nextInt(0, dirs.size())) : null;
    }

}