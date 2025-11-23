package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;

import me.brody.mazesurvival.Main;

import java.io.Serializable;

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
				if(plugin.getGameManager().isDebugModeEnabled() && x == 0 && z == 0)
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
		Location originCornerLoc = LocationUtils.copy(cellCorner);
		originCornerLoc.setX(originCornerLoc.getX() - grid.getWallWidth());
		originCornerLoc.setZ(originCornerLoc.getZ() - grid.getWallWidth());

		for(int y = 0; y < grid.getWallHeight() + 1; y++) {
			for(int z = 0; z < grid.getWallWidth(); z++) {
				for(int x = 0; x < grid.getWallWidth(); x++) {
					Location loc = LocationUtils.copy(originCornerLoc);
					loc.setX(loc.getX() + x);
					loc.setY(loc.getY() + y);
					loc.setZ(loc.getZ() + z);
					if(y == 0)
						loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
					else
						loc.getBlock().setType(region.getWallPalette().pickBlockFromPalette());
				}
			}
		}
	}

	private void placeSouthEastCornerPillar(MazeRegion region, Location cellCorner) {
		for(int y = 0; y < grid.getWallHeight(); y++) {
			for(int z = 0; z < grid.getWallWidth(); z++) {
				for(int x = 0; x < grid.getWallWidth(); x++) {
					Location loc = LocationUtils.copy(cellCorner);
					loc.setX(loc.getX() + grid.getRegionCellSize() - grid.getWallWidth() + x);
					loc.setY(loc.getY() + y + 1);
					loc.setZ(loc.getZ() + grid.getRegionCellSize() - grid.getWallWidth() + z);
					loc.getBlock().setType(region.getWallPalette().pickBlockFromPalette());
				}
			}
		}
	}

	private void placeOuterWallFloorStrips(MazeRegion region) {
		for(int i = 0; i < region.getMazeCells().length; i++) {
			for(int j = 0; j < region.getMazeCells()[0].length; j++) {
				if(region.getMazeCells()[i][j] == null)
					continue;

				if(i - 1 < 0 || region.getMazeCells()[i - 1][j] == null) {
					placeFloorNorthOfCell(region, i, j);
				}
				if(j - 1 < 0 || region.getMazeCells()[i][j - 1] == null) {
					placeFloorWestOfCell(region, i, j);
				}
			}
		}

	}

	private void placeFloorNorthOfCell(MazeRegion region, int row, int column) {
		Location northFloorOrigin = grid.getRegionCellWorldOrigin(region, row, column);
		northFloorOrigin.setZ(northFloorOrigin.getZ() - grid.getWallWidth());
		for(int y = 0; y < grid.getWallWidth(); y++) {
			for(int x = 0; x < grid.getRegionCellSize(); x++) {
				Location loc = LocationUtils.copy(northFloorOrigin);
				loc.setX(loc.getX() + x);
				loc.setZ(loc.getZ() + y);
				loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
			}
		}
	}

	private void placeFloorWestOfCell(MazeRegion region, int row, int column) {
		Location westFloorOrigin = grid.getRegionCellWorldOrigin(region, row, column);
		westFloorOrigin.setX(westFloorOrigin.getX() - grid.getWallWidth());
		for(int y = 0; y < grid.getRegionCellSize(); y++) {
			for(int x = 0; x < grid.getWallWidth(); x++) {
				Location loc = LocationUtils.copy(westFloorOrigin);
				loc.setX(loc.getX() + x);
				loc.setZ(loc.getZ() + y);
				loc.getBlock().setType(region.getFloorPalette().pickBlockFromPalette());
			}
		}
	}

}
