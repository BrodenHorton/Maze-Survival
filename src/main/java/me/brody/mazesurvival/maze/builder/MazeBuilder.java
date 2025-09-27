package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import org.bukkit.Location;
import org.bukkit.Material;

import me.brody.mazesurvival.Main;

public class MazeBuilder {
	private static final int MAZE_CELL_GENERATION_LIMIT = 30;

	public Event<EventArgs> onMazeBuilt;

	private final Main plugin;
	private MazeGrid grid;
	private int regionIndex;
	private int cellIndex;
	private boolean isRunning;
	
	public MazeBuilder(Main plugin, MazeGrid grid) {
		onMazeBuilt = new Event<>();
		this.plugin = plugin;
		this.grid = grid;
		regionIndex = 0;
		cellIndex = 0;
		isRunning = false;
	}
	
	public void build() {
		if(grid.getRegions().isEmpty() || isRunning)
			return;

		isRunning = true;
		constructMaze();
	}

	private void constructMaze() {
		MazeRegion region = grid.getRegions().get(regionIndex);
		Location regionWorldOrigin = grid.getRegionWorldOrigin(region);

		int startingRowIndex = cellIndex / region.getMazeCells()[0].length;
		int startingColumnIndex = cellIndex % region.getMazeCells()[0].length;
		int cellCounter = 0;
		for(int i = startingRowIndex; i < region.getMazeCells().length; i++) {
			for(int j = (i == startingRowIndex) ? startingColumnIndex : 0; j < region.getMazeCells()[0].length; j++) {
				if(cellCounter >= MAZE_CELL_GENERATION_LIMIT) {
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::constructMaze, 15L);
					return;
				}

				Location cellCorner = new Location(regionWorldOrigin.getWorld(),
						regionWorldOrigin.getX() + grid.getMarginInBlocks() + (grid.getRegionCellSize() * j),
						regionWorldOrigin.getY(),
						regionWorldOrigin.getZ() + grid.getMarginInBlocks() + (grid.getRegionCellSize() * i));

				if(region.getMazeCells()[i][j] != null) {
					buildCell(region, cellCorner, i, j);
					cellCounter++;
					cellIndex++;
				}
			}
		}

		placeOuterWallFloorStrips(region);

		if(grid.getRegions().size() > regionIndex + 1) {
			regionIndex++;
			cellIndex = 0;
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::constructMaze, 15L);
		}
		else {
			regionIndex = 0;
			cellIndex = 0;
			isRunning = false;
			onMazeBuilt.invoke(this, EventArgs.EMPTY);
		}
	}

	private void buildCell(MazeRegion region, Location cellCorner, int cellRowIndex, int cellColumnIndex) {
		MazeCell cell = region.getMazeCells()[cellRowIndex][cellColumnIndex];
		if(cell == null)
			return;

		placeMazeCellFloor(region, cellCorner);

		if(cell.walls[1])
			placeMazeCellWall(region, cellCorner, Direction.EAST);
		if(cell.walls[2])
			placeMazeCellWall(region, cellCorner, Direction.SOUTH);
		// Place the north wall to the cell if it is in the first row of cells or if the cell above is null
		if(cellRowIndex == 0 || region.getMazeCells()[cellRowIndex - 1][cellColumnIndex] == null)
			placeMazeCellWall(region, cellCorner, Direction.NORTH);
		// Place the west wall to the cell if it is in the first column of cells or if the cell to the left is null
		if(cellColumnIndex == 0 || region.getMazeCells()[cellRowIndex][cellColumnIndex - 1] == null)
			placeMazeCellWall(region, cellCorner, Direction.WEST);

		// Place corner pillars
		if(cellRowIndex == 0 && cellColumnIndex == 0)
			placeOriginCornerPillar(region, cellCorner);
		if(!cell.walls[1] && !cell.walls[2])
			placeSouthEastCornerPillar(region, cellCorner);
	}

	private void placeMazeCellFloor(MazeRegion region, Location cellCorner) {
		for(int z = 0; z < grid.getRegionCellSize(); z++) {
			for(int x = 0; x < grid.getRegionCellSize(); x++) {
				Location loc = new Location(cellCorner.getWorld(),
						cellCorner.getX() + x, cellCorner.getY(), cellCorner.getZ() + z);
				if(x == 0 && z == 0)
					loc.getBlock().setType(Material.DIAMOND_BLOCK);
				else
					loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
			}
		}
	}

	private void placeMazeCellWall(MazeRegion region, Location cellCorner, Direction dir) {
		int wallWidth = dir.id % 2 == 0 ? grid.getRegionCellSize() : grid.getWallWidth();
		int wallLength = dir.id % 2 == 0 ? grid.getWallWidth() : grid.getRegionCellSize();

		for(int y = 0; y < grid.getWallHeight(); y++) {
			for(int z = 0; z < wallLength; z++) {
				for(int x = 0; x < wallWidth; x++) {
					int xOffset = x;
					int zOffset = z;
					if(dir == Direction.NORTH)
						zOffset = -(z + 1);
					else if(dir == Direction.EAST)
						xOffset = x + grid.getRegionCellSize() - grid.getWallWidth();
					else if(dir == Direction.SOUTH)
						zOffset = z + grid.getRegionCellSize() - grid.getWallWidth();
					else
						xOffset = -(x + 1);

					Location loc = new Location(cellCorner.getWorld(),
							cellCorner.getX() + xOffset,
							cellCorner.getY() + y + 1,
							cellCorner.getZ() + zOffset);
					loc.getBlock().setType(region.getWallPalette().pickBlockFromPalette());
				}
			}
		}
	}

	private void placeOriginCornerPillar(MazeRegion region, Location cellCorner) {
		Location originCornerLoc = new Location(cellCorner.getWorld(),
				cellCorner.getX() - grid.getWallWidth(),
				cellCorner.getY(),
				cellCorner.getZ() - grid.getWallWidth());

		for(int y = 0; y <= grid.getWallHeight(); y++) {
			for(int z = 0; z < grid.getWallWidth(); z++) {
				for(int x = 0; x < grid.getWallWidth(); x++) {
					Location loc = new Location(originCornerLoc.getWorld(),
							originCornerLoc.getX() + x,
							originCornerLoc.getY() + y + 1,
							originCornerLoc.getZ() + z);
					loc.getBlock().setType(region.getWallPalette().pickBlockFromPalette());
				}
			}
		}
	}

	private void placeSouthEastCornerPillar(MazeRegion region, Location cellCorner) {
		for(int y = 0; y < grid.getWallHeight(); y++) {
			for(int z = 0; z < grid.getWallWidth(); z++) {
				for(int x = 0; x < grid.getWallWidth(); x++) {
					Location loc = new Location(cellCorner.getWorld(),
							cellCorner.getX() + grid.getRegionCellSize() - grid.getWallWidth() + x,
							cellCorner.getY() + y + 1,
							cellCorner.getZ() + grid.getRegionCellSize() - grid.getWallWidth() + z);
					loc.getBlock().setType(region.getWallPalette().pickBlockFromPalette());
				}
			}
		}
	}

	private void placeOuterWallFloorStrips(MazeRegion region) {
		Location cellOuterWallOrigin = grid.getRegionCellWorldOrigin(region, 0, 0);
		cellOuterWallOrigin.setX(cellOuterWallOrigin.getX() - grid.getWallWidth());
		cellOuterWallOrigin.setZ(cellOuterWallOrigin.getZ() - grid.getWallWidth());

		for(int i = 0; i < region.getMazeCells()[0].length * grid.getRegionCellSize() + grid.getWallWidth(); i++) {
			for(int j = 0; j < grid.getWallWidth(); j++) {
				Location loc = new Location(
						cellOuterWallOrigin.getWorld(),
						cellOuterWallOrigin.getX() + i,
						cellOuterWallOrigin.getY(),
						cellOuterWallOrigin.getZ() + j
				);
				loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
			}
		}
		for(int i = 0; i < region.getMazeCells().length * grid.getRegionCellSize() + grid.getWallWidth(); i++) {
			for(int j = 0; j < grid.getWallWidth(); j++) {
				Location loc = new Location(
						cellOuterWallOrigin.getWorld(),
						cellOuterWallOrigin.getX() + j,
						cellOuterWallOrigin.getY(),
						cellOuterWallOrigin.getZ() + i
				);
				loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
			}
		}
	}

}
