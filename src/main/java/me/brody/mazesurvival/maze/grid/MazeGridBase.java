package me.brody.mazesurvival.maze.grid;

import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.MazeSchematic;

public class MazeGridBase {
	private int regionCellSize;
	private int wallHeight;
	private int wallWidth;
	private int gladeSize;
	private int gridCellSize;
	private int gridCellMargins;
	private MazeSchematic gladeSchema;

	private MazeGridBase(int regionCellSize, int wallHeight, int wallWidth, int gladeSize, int gridCellSize, int gridCellMargins, MazeSchematic gladeSchema) {
		this.regionCellSize = regionCellSize;
		this.wallHeight = wallHeight;
		this.wallWidth = wallWidth;
		this.gladeSize = gladeSize;
		this.gridCellSize = gridCellSize;
		this.gridCellMargins = gridCellMargins;
		this.gladeSchema = gladeSchema;
	}

	public static void register() {
		Registry.GRID_BASE.register("standard", new MazeGridBase(9, 30, 2, 7, 25, 2, MazeSchematic.GLADE));
		Registry.GRID_BASE.register("small", new MazeGridBase(9, 6, 2, 7, 9, 2, MazeSchematic.GLADE));
	}

	public int getMarginInBlocks() {
		return gridCellMargins * regionCellSize;
	}

	public int getRegionCellSize() {
		return regionCellSize;
	}

	public int getWallHeight() {
		return wallHeight;
	}

	public int getWallWidth() {
		return wallWidth;
	}

	public int getGladeSize() {
		return gladeSize;
	}

	public int getGridCellSize() {
		return gridCellSize;
	}

	public int getGridCellMargins() {
		return gridCellMargins;
	}

	public MazeSchematic getGladeSchema() {
		return gladeSchema;
	}
}
