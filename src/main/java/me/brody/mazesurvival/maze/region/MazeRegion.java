package me.brody.mazesurvival.maze.region;

import me.brody.mazesurvival.loot.LootTable;
import me.brody.mazesurvival.loot.TradeTable;
import me.brody.mazesurvival.maze.BlockPalette;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.mob.MobSpawnPool;
import me.brody.mazesurvival.utils.Vector2Int;

import java.util.*;

public class MazeRegion {
	private static final Random RNG = new Random();

	private MazeRegionBase regionBase;
	private UUID uuid;
	private Vector2Int regionDimensions;
	private MazeCell[][] mazeCells;
	private CellExtension haven;
	private CellExtension bossRoom;
	private Map<Vector2Int, Vector2Int> tileTeleportationMap;
	private Map<Vector2Int, Vector2Int> trapChestTeleportationMap;

	public MazeRegion(MazeRegionBase regionBase, Vector2Int regionDimensions, CellExtension haven, CellExtension bossRoom) {
		this.regionBase = regionBase;
		uuid = UUID.randomUUID();
		this.regionDimensions = regionDimensions;
		this.haven = haven;
		this.bossRoom = bossRoom;
		tileTeleportationMap = new HashMap<>();
		trapChestTeleportationMap = new HashMap<>();
	}

	public void generateMaze(MazeGrid grid) {
		int mWidth = (regionDimensions.x * grid.getGridCellSize()) - (grid.getGridCellMargins() * 2);
		int mHeight = (regionDimensions.y * grid.getGridCellSize()) - (grid.getGridCellMargins() * 2);

		Stack<Vector2Int> mStack = new Stack<>();
		mazeCells = new MazeCell[mHeight][mWidth];

		int totalMazeCells = initMazeCells(mWidth, mHeight, grid);

		mStack.push(Vector2Int.Zero());
		int nVisitedCells = 1;
		mazeCells[0][0].visited = true;

		// While the number of visited cells is less than the total cells in the maze, keep checking
		// for unvisited cells to connect to the maze
		while (nVisitedCells < totalMazeCells) {
			List<Direction> neighbours = new ArrayList<>();
			if (mStack.peek().y > 0 && mazeCells[mStack.peek().y - 1][mStack.peek().x] != null && mazeCells[mStack.peek().y - 1][mStack.peek().x].visited == false)
				neighbours.add(Direction.NORTH);
			if (mStack.peek().x < mWidth - 1 && mazeCells[mStack.peek().y][mStack.peek().x + 1] != null && mazeCells[mStack.peek().y][mStack.peek().x + 1].visited == false)
				neighbours.add(Direction.EAST);
			if (mStack.peek().y < mHeight - 1 && mazeCells[mStack.peek().y + 1][mStack.peek().x] != null && mazeCells[mStack.peek().y + 1][mStack.peek().x].visited == false)
				neighbours.add(Direction.SOUTH);
			if (mStack.peek().x > 0 && mazeCells[mStack.peek().y][mStack.peek().x - 1] != null && mazeCells[mStack.peek().y][mStack.peek().x - 1].visited == false)
				neighbours.add(Direction.WEST);

			// If an adjacent mazeCell that has not been visited was found, pick at random
			// from the unvisited neighbouring cells and travel in that direction
			if (!neighbours.isEmpty()) {
				Direction nextCellDir = neighbours.get(RNG.nextInt(Integer.MAX_VALUE) % neighbours.size());
				mazeCells[mStack.peek().y][mStack.peek().x].walls[nextCellDir.id] = false;
				mazeCells[mStack.peek().y + nextCellDir.yDirOffset][mStack.peek().x + nextCellDir.xDirOffset].walls[(nextCellDir.id + 2) % 4] = false;
				mStack.push(new Vector2Int(mStack.peek().x + nextCellDir.xDirOffset, mStack.peek().y + nextCellDir.yDirOffset));

				// Set the mazeCell you have moved to as visited and increment the total visited cells
				mazeCells[mStack.peek().y][mStack.peek().x].visited = true;
				nVisitedCells++;
			}
			// If all adjacent cells have been visited, pop from the stack and check the new adjacent cells
			else
				mStack.pop();
		}
	}

	// Returns total maze cells
	private int initMazeCells(int mWidth, int mHeight, MazeGrid grid) {
		int totalMazeCells = mWidth * mHeight;
		for(int i = 0; i < mHeight; i++) {
			for(int j = 0; j < mWidth; j++)
				mazeCells[i][j] = new MazeCell();
		}

		if(grid.getRegionByGridCell().get(grid.getStartingCell()) != null && grid.getRegionByGridCell().get(grid.getStartingCell()).equals(this)) {
			Vector2Int regionOrigin = grid.getRegionOriginByRegion().get(this);
			Vector2Int cellOffset = new Vector2Int(grid.getStartingCell().x - regionOrigin.x, grid.getStartingCell().y - regionOrigin.y);
			int upperX = (grid.getGridCellSize() - ((grid.getGridCellSize() - grid.getGladeSize()) / 2)) + (cellOffset.x * grid.getGridCellSize()) - grid.getGridCellMargins() - 1;
			int lowerX = ((grid.getGridCellSize() - grid.getGladeSize()) / 2) + (cellOffset.x * grid.getGridCellSize()) - grid.getGridCellMargins();
			int upperY = (grid.getGridCellSize() - ((grid.getGridCellSize() - grid.getGladeSize()) / 2)) - (cellOffset.y * grid.getGridCellSize()) - grid.getGridCellMargins() - 1;
			int lowerY = ((grid.getGridCellSize() - grid.getGladeSize()) / 2) - (cellOffset.y * grid.getGridCellSize()) - grid.getGridCellMargins();

			for(int i = 0; i < mHeight; i++) {
				for(int j = 0; j < mWidth; j++) {
					if(i <= upperY && i >= lowerY && j <= upperX && j >= lowerX) {
						mazeCells[i][j] = null;
						totalMazeCells--;
					}
				}
			}
		}

		return totalMazeCells;
	}

	public Vector2Int getTileTeleportationDestinationCell(Vector2Int sourceCell) {
		Vector2Int destinationCell = null;
		if(tileTeleportationMap.containsKey(sourceCell)) {
			destinationCell = tileTeleportationMap.get(sourceCell);
		}
		else {
			Random rand = new Random();
			int loopLimit = 0;
			while(loopLimit < 10) {
				int rowIndex = rand.nextInt(0, mazeCells.length);
				int columnIndex = rand.nextInt(0, mazeCells[0].length);
				if(mazeCells[rowIndex][columnIndex] != null) {
					destinationCell = new Vector2Int(columnIndex, rowIndex);
					tileTeleportationMap.put(sourceCell, destinationCell);
					break;
				}
				loopLimit++;
			}
		}

		return destinationCell;
	}

	public Vector2Int getTrapChestTeleportationDestinationCell(Vector2Int sourceCell) {
		Vector2Int destinationCell = null;
		if(trapChestTeleportationMap.containsKey(sourceCell)) {
			destinationCell = trapChestTeleportationMap.get(sourceCell);
		}
		else {
			Random rand = new Random();
			int loopLimit = 0;
			while(loopLimit < 10) {
				int rowIndex = rand.nextInt(0, mazeCells.length);
				int columnIndex = rand.nextInt(0, mazeCells[0].length);
				if(mazeCells[rowIndex][columnIndex] != null) {
					destinationCell = new Vector2Int(columnIndex, rowIndex);
					trapChestTeleportationMap.put(sourceCell, destinationCell);
					break;
				}
				loopLimit++;
			}
		}

		return destinationCell;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Vector2Int getRegionDimensions() {
		return regionDimensions;
	}

	public MazeCell[][] getMazeCells() {
		return mazeCells;
	}

	public void setMazeCells(MazeCell[][] mazeCells) {
		this.mazeCells = mazeCells;
	}

	public CellExtension getHaven() {
		return haven;
	}

	public void setHaven(CellExtension haven) {
		this.haven = haven;
	}

	public CellExtension getBossRoom() {
		return bossRoom;
	}

	public void setBossRoom(CellExtension bossRoom) {
		this.bossRoom = bossRoom;
	}

	public Map<Vector2Int, Vector2Int> getTileTeleportationMap() {
		return tileTeleportationMap;
	}

	public Map<Vector2Int, Vector2Int> getTrapChestTeleportationMap() {
		return trapChestTeleportationMap;
	}

	public BlockPalette getFloorPalette() {
		return regionBase.getFloorPalette();
	}

	public BlockPalette getWallPalette() {
		return regionBase.getWallPalette();
	}

	public RegionDecoration getDecoration() {
		return regionBase.getDecoration();
	}

	public LootTable getLootTable() {
		return regionBase.getMazeChestLootTable();
	}

	public TradeTable getTradeTable() {
		return regionBase.getTradeTable();
	}

	public MobSpawnPool getDayMobs() {
		return regionBase.getDayMobs();
	}

	public MobSpawnPool getNightMobs() {
		return regionBase.getNightMobs();
	}
}
