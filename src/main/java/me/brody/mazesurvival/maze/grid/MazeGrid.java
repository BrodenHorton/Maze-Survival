package me.brody.mazesurvival.maze.grid;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.LocationUtils;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.Vector2Int;
import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.*;

public class MazeGrid {
	private final Main plugin;

	private MazeGridBase gridBase;
	private Location gridOrigin;
	private List<MazeRegion> regions;
	private Map<MazeRegion, Vector2Int> regionOriginByRegion;
	private Map<Vector2Int, MazeRegion> regionByGridCell;
	private Vector2Int startingCell;

	public MazeGrid(Main plugin, MazeGridBase gridBase, Location gridOrigin) {
		this.plugin = plugin;
		this.gridBase = gridBase;
		this.gridOrigin = gridOrigin;
		regions = new ArrayList<>();
		regionOriginByRegion = new HashMap<>();
		regionByGridCell = new HashMap<>();
		this.startingCell = Vector2Int.Zero();
	}
	
	public void addMazeRegion(MazeRegion region, Vector2Int regionGridOrigin) {
		Vector2Int regionDimensions = region.getRegionDimensions();
		for(int i = 0; i < regionDimensions.y; i++) {
			for(int j = 0; j < regionDimensions.x; j++) {
				if(regionByGridCell.containsKey(new Vector2Int(regionGridOrigin.x + j, regionGridOrigin.y - i))) {
					plugin.getLogger().warning("MazeGrid.addMazeRegion: Attempted to add region when region already" +
							"exists at x = " + (regionGridOrigin.x + j) + ", y = " + (regionGridOrigin.y - i));
					return;
				}
			}
		}

		regions.add(region);
		regionOriginByRegion.put(region, regionGridOrigin);
		for(int i = 0; i < regionDimensions.y; i++) {
			for(int j = 0; j < regionDimensions.x; j++)
				regionByGridCell.put(new Vector2Int(regionGridOrigin.x + j, regionGridOrigin.y - i), region);
		}
	}

	public void generateRegionMazes() {
		for(int i = 0; i < regions.size(); i++)
			regions.get(i).generateMaze(this);
	}

	public Vector2Int getGridCellAt(Location location) {
		int column = (int)(location.getX() - gridOrigin.getX()) / getGridCellSizeInBlocks();
		if(location.getX() < gridOrigin.getX())
			column--;

		int row = -(int)(location.getZ() - gridOrigin.getZ()) / getGridCellSizeInBlocks();
		if(location.getZ() < gridOrigin.getZ())
			row++;

		return new Vector2Int(column, row);
	}

	public int getGridCellSizeInBlocks() {
		return getGridCellSize() * getRegionCellSize();
	}

	public MazeRegion getRegionAt(Location location) {
		Vector2Int gridCell = getGridCellAt(location);
		if(!regionByGridCell.containsKey(gridCell))
			return null;

		return regionByGridCell.get(gridCell);
	}

	public Location getGridCellWorldOrigin(Vector2Int cell) {
		return new Location(
				gridOrigin.getWorld(),
				gridOrigin.getX() + (cell.x * (gridBase.getGridCellSize() * gridBase.getRegionCellSize())),
				gridOrigin.getY(),
				gridOrigin.getZ() - (cell.y * (gridBase.getGridCellSize() * gridBase.getRegionCellSize()))
		);
	}

	public Location getRegionWorldOrigin(MazeRegion region) {
		if(!regionOriginByRegion.containsKey(region))
			return null;

		return new Location(
				gridOrigin.getWorld(),
				gridOrigin.getX() + (regionOriginByRegion.get(region).x * (gridBase.getGridCellSize() * gridBase.getRegionCellSize())),
				gridOrigin.getY(),
				gridOrigin.getZ() - (regionOriginByRegion.get(region).y * (gridBase.getGridCellSize() * gridBase.getRegionCellSize()))
		);
	}

	public Location getRegionCellWorldOrigin(MazeRegion region, Vector2Int cellCoords) {
		return getRegionCellWorldOrigin(region, cellCoords.y, cellCoords.x);
	}

	public Location getRegionCellWorldOrigin(MazeRegion region, int row, int column) {
		if(!regionOriginByRegion.containsKey(region))
			return null;
		if(region.getMazeCells().length < row || region.getMazeCells()[0].length < column)
			return null;

		Location regionWorldOrigin = getRegionWorldOrigin(region);
		return new Location(
				regionWorldOrigin.getWorld(),
				regionWorldOrigin.getX() + getMarginInBlocks() + (column * getRegionCellSize()),
				regionWorldOrigin.getY(),
				regionWorldOrigin.getZ() + getMarginInBlocks() + (row * getRegionCellSize())
		);
	}

	public Location getRegionCellWorldCenter(MazeRegion region, Vector2Int cellCoords) {
		return getRegionCellWorldCenter(region, cellCoords.y, cellCoords.x);
	}

	public Location getRegionCellWorldCenter(MazeRegion region, int row, int column) {
		if(!regionOriginByRegion.containsKey(region))
			return null;
		if(region.getMazeCells().length <= row || region.getMazeCells()[0].length <= column)
			return null;

		Location cellCenter = getRegionCellWorldOrigin(region, row, column);
		cellCenter.setX(cellCenter.getX() + (getRegionCellSize() - getWallWidth()) / 2);
		cellCenter.setY(cellCenter.getY());
		cellCenter.setZ(cellCenter.getZ() + (getRegionCellSize() - getWallWidth()) / 2);

		return cellCenter;
	}

	public Vector2Int getRegionCellAt(MazeRegion region, Location location) {
		Location regionFirstCellOrigin = getRegionCellWorldOrigin(region, 0, 0);
		if(regionFirstCellOrigin == null)
			return null;
		if(location.getX() < regionFirstCellOrigin.getX() || location.getZ() < regionFirstCellOrigin.getZ())
			return null;

		Vector2Int cellCoords = null;
		int row = (int)(Math.ceil(location.getZ() - regionFirstCellOrigin.getZ()) / gridBase.getRegionCellSize());
		int column = (int)(Math.ceil(location.getX() - regionFirstCellOrigin.getX()) / gridBase.getRegionCellSize());
		if(region.getMazeCells().length > row && region.getMazeCells()[0].length > column)
			cellCoords = new Vector2Int(column, row);

		return cellCoords;
	}

	public Location getGladeWorldOrigin() {
		Location gladeOrigin = getGridCellWorldOrigin(startingCell);
		gladeOrigin.setX(gladeOrigin.getX() + ((getGridCellSize() - getGladeSize()) / 2) * getRegionCellSize());
		gladeOrigin.setZ(gladeOrigin.getZ() + ((getGridCellSize() - getGladeSize()) / 2) * getRegionCellSize());

		return gladeOrigin;
	}

	public Location getGladeWorldCenter() {
		Location gladeCenter = getGladeWorldOrigin();
		gladeCenter.setX(gladeCenter.getX() + (gridBase.getGladeSize() * gridBase.getRegionCellSize() / 2) - 1);
		gladeCenter.setZ(gladeCenter.getZ() + (gridBase.getGladeSize() * gridBase.getRegionCellSize() / 2) - 1);
		return gladeCenter;
	}

	public Location getGladeRespawnLocation() {
		Location respawnLocation = getGladeWorldCenter();
		respawnLocation.setX(respawnLocation.getX() + 0.5);
		respawnLocation.setY(respawnLocation.getY() + 1);
		respawnLocation.setZ(respawnLocation.getZ() + 0.5);
		return respawnLocation;
	}

	public MazeRegion getRegionByUuid(UUID uuid) {
		for(MazeRegion region : regions) {
			if(region.getUuid().equals(uuid))
				return region;
		}

		return null;
	}

	public Location getRegionHavenWorldOrigin(MazeRegion region) {
		if(region == null)
			return null;

		return getRegionCellExtensionWorldOrigin(region, region.getHaven());
	}

	public Location getRegionBossRoomWorldOrigin(MazeRegion region) {
		if(region == null)
			return null;

		return getRegionCellExtensionWorldOrigin(region, region.getBossRoom());
	}

	public Location getRegionBossRoomWorldCenter(MazeRegion region) {
		if(region == null)
			return null;
		Location bossRoomOrigin = getRegionBossRoomWorldOrigin(region);
		if(bossRoomOrigin == null)
			return null;

		Vector3Int bossRoomCenterOffset = new Vector3Int(0, 0, -getMarginInBlocks());
		bossRoomCenterOffset.rotateY(region.getBossRoom().getDirection().id * -90);
		System.out.println("boss room center offset: " + bossRoomCenterOffset);

		return LocationUtils.shift(bossRoomOrigin, bossRoomCenterOffset);
	}

	// TODO: Refactor to use vector rotation instead of checking each direction
	private Location getRegionCellExtensionWorldOrigin(MazeRegion region, CellExtension cellExtension) {
		if(regionOriginByRegion.get(region) == null)
			return null;
		if(region.getHaven() == null)
			return null;

		Vector2Int cellExtensionGridCell = new Vector2Int(regionOriginByRegion.get(region).x + cellExtension.getCellPosition().x, regionOriginByRegion.get(region).y + cellExtension.getCellPosition().y);
		Location cellExtensionGridCellOrigin = getGridCellWorldOrigin(cellExtensionGridCell);
		Location cellExtensionOrigin = LocationUtils.copy(cellExtensionGridCellOrigin);
		if(cellExtension.getDirection() == Direction.NORTH) {
			cellExtensionOrigin.setX(cellExtensionGridCellOrigin.getX() + ((getGridCellSize() / 2) * getRegionCellSize()) + (getRegionCellSize() - getWallWidth()) / 2);
			cellExtensionOrigin.setZ(cellExtensionGridCellOrigin.getZ() + getMarginInBlocks() + (getRegionCellSize() - getWallWidth()) / 2);
		}
		else if(cellExtension.getDirection() == Direction.EAST) {
			cellExtensionOrigin.setX(cellExtensionGridCellOrigin.getX() + getGridCellSize() * getRegionCellSize() - getMarginInBlocks() - Math.ceil(getRegionCellSize() / 2f) - 1);
			cellExtensionOrigin.setZ(cellExtensionGridCellOrigin.getZ() + ((getGridCellSize() / 2) * getRegionCellSize()) + ((getRegionCellSize() - getWallWidth()) / 2));
		}
		else if(cellExtension.getDirection() == Direction.SOUTH) {
			cellExtensionOrigin.setX(cellExtensionGridCellOrigin.getX() + ((getGridCellSize() / 2) * getRegionCellSize()) + (getRegionCellSize() - getWallWidth()) / 2);
			cellExtensionOrigin.setZ(cellExtensionGridCellOrigin.getZ() + getGridCellSize() * getRegionCellSize() - getMarginInBlocks() - Math.ceil(getRegionCellSize() / 2f) - 1);
		}
		else if(cellExtension.getDirection() == Direction.WEST) {
			cellExtensionOrigin.setX(cellExtensionGridCellOrigin.getX() + getMarginInBlocks() + (getRegionCellSize() - getWallWidth()) / 2);
			cellExtensionOrigin.setZ(cellExtensionGridCellOrigin.getZ() + ((getGridCellSize() / 2) * getRegionCellSize()) + (getRegionCellSize() - getWallWidth()) / 2);
		}

		return cellExtensionOrigin;
	}

	public Location getRegionHavenWorldCenter(MazeRegion region) {
		if(region == null)
			return null;
		Location havenOrigin = getRegionHavenWorldOrigin(region);
		if(havenOrigin == null)
			return null;

		Vector3Int havenCenterOffset = new Vector3Int(0, 0, -getMarginInBlocks());
		havenCenterOffset.rotateY(region.getHaven().getDirection().id * -90);
		System.out.println("haven center offset: " + havenCenterOffset);

		return LocationUtils.shift(havenOrigin, havenCenterOffset);
	}

	public World getWorld() {
		return gridOrigin.getWorld();
	}

	public int getMarginInBlocks() {
		return gridBase.getMarginInBlocks();
	}

	public Location getGridOrigin() {
		return gridOrigin;
	}

	public List<MazeRegion> getRegions() {
		return  regions;
	}

	public Map<MazeRegion, Vector2Int> getRegionOriginByRegion() {
		return regionOriginByRegion;
	}

	public Map<Vector2Int, MazeRegion> getRegionByGridCell() {
		return regionByGridCell;
	}

	public Vector2Int getStartingCell() {
		return startingCell;
	}

	public void setStartingCell(Vector2Int value) {
		startingCell = value;
	}

	public int getRegionCellSize() {
		return gridBase.getRegionCellSize();
	}

	public int getWallHeight() {
		return gridBase.getWallHeight();
	}

	public int getWallWidth() {
		return gridBase.getWallWidth();
	}

	public int getGladeSize() {
		return gridBase.getGladeSize();
	}

	public int getGridCellSize() {
		return gridBase.getGridCellSize();
	}

	public int getGridCellMargins() {
		return gridBase.getGridCellMargins();
	}

	public MazeSchematic getGladeSchema() {
		return gridBase.getGladeSchema();
	}
}

