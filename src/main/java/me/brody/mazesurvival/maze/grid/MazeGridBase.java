package me.brody.mazesurvival.maze.grid;

import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.MazeSchematic;

import java.io.Serializable;

public class MazeGridBase implements Serializable {
	public static final MazeGridBase SMALL;
	public static final MazeGridBase STANDARD;

	private int regionCellSize;
	private int wallHeight;
	private int wallWidth;
	private int gladeSize;
	private int gridCellSize;
	private int gridCellMargins;
	private MazeSchematic gladeSchema;

	static {
		SMALL = new MazeGridBase(9, 6, 2, 7, 9, 2, MazeSchematic.GLADE);
		STANDARD = new MazeGridBase(9, 30, 2, 7, 25, 2, MazeSchematic.GLADE);
	}

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
		Registry.GRID_BASE.register("small", SMALL);
		Registry.GRID_BASE.register("standard", STANDARD);
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
