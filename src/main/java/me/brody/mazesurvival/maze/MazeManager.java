package me.brody.mazesurvival.maze;

import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.builder.MazeBuilder;
import me.brody.mazesurvival.maze.builder.DecoratorGenerator;
import me.brody.mazesurvival.maze.builder.MazeLootGenerator;
import me.brody.mazesurvival.maze.builder.StructureGenerator;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.grid.MazeGridBase;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.maze.region.MazeRegionBase;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Location;

import me.brody.mazesurvival.Main;

public class MazeManager {
	public Event<EventArgs> onMazeConstructionFinished;

	private final Main plugin;

	private MazeGrid grid;
	private MazeBuilder mazeBuilder;
	private MazeLootGenerator lootGenerator;
	private DecoratorGenerator mazeDecorator;
	private StructureGenerator structureGenerator;
	
	public MazeManager(Main plugin) {
		this.plugin = plugin;
		onMazeConstructionFinished = new Event<>();
	}
	
	public void generateMaze(MazeGridBase gridBase, Location gridOrigin) {
		grid = new MazeGrid(plugin, gridBase, gridOrigin);

		MazeRegion central = new MazeRegion(MazeRegionBase.STRONGHOLD, new Vector2Int(3, 3), null, null);
		grid.addMazeRegion(central, new Vector2Int(-1, 1));

		CellExtension desertHaven = new CellExtension(new Vector2Int(0, 0), Direction.WEST, MazeSchematic.DESERT_HAVEN);
		CellExtension desertBossRoom = new CellExtension(new Vector2Int(1, 0), Direction.EAST, MazeSchematic.DESERT_BOSS_ROOM);
		MazeRegion desert = new MazeRegion(MazeRegionBase.DESERT, new Vector2Int(2, 1), desertHaven, desertBossRoom);
		grid.addMazeRegion(desert, new Vector2Int(2, 0));

		CellExtension swampHaven = new CellExtension(new Vector2Int(1, 0), Direction.EAST, MazeSchematic.SWAMP_HAVEN);
		CellExtension swampBossRoom = new CellExtension(new Vector2Int(0, 0), Direction.NORTH, MazeSchematic.SWAMP_BOSS_ROOM);
		MazeRegion swamp = new MazeRegion(MazeRegionBase.SWAMP, new Vector2Int(2, 2), swampHaven, swampBossRoom);
		grid.addMazeRegion(swamp, new Vector2Int(-3, 1));

		CellExtension netherHaven = new CellExtension(new Vector2Int(0, 0), Direction.NORTH, MazeSchematic.NETHER_HAVEN);
		CellExtension netherBossRoom = new CellExtension(new Vector2Int(0, 0), Direction.WEST, MazeSchematic.NETHER_BOSS_ROOM);
		MazeRegion nether = new MazeRegion(MazeRegionBase.NETHER, new Vector2Int(1, 2), netherHaven, netherBossRoom);
		grid.addMazeRegion(nether, new Vector2Int(0, -2));

		CellExtension deepDarkHaven = new CellExtension(new Vector2Int(1, 0), Direction.EAST, MazeSchematic.DEEP_DARK_HAVEN);
		CellExtension deepDarkBossRoom = new CellExtension(new Vector2Int(0, -1), Direction.SOUTH, MazeSchematic.DEEP_DARK_BOSS_ROOM);
		MazeRegion deepDark = new MazeRegion(MazeRegionBase.DEEP_DARK, new Vector2Int(2, 2), deepDarkHaven, deepDarkBossRoom);
		grid.addMazeRegion(deepDark, new Vector2Int(-5, 0));

		grid.generateRegionMazes();

		mazeBuilder = new MazeBuilder(plugin, grid);
		lootGenerator = new MazeLootGenerator(plugin, grid);
		mazeDecorator = new DecoratorGenerator(plugin, grid);
		structureGenerator = new StructureGenerator(plugin, grid);
		mazeBuilder.onMazeBuilt.addListener((sender, args) -> lootGenerator.start());
		lootGenerator.onLootGenerationFinished.addListener((sender, args) -> mazeDecorator.start());
		mazeDecorator.onMazeDecoratorFinished.addListener((sender, args) -> structureGenerator.start());
		structureGenerator.onStructureGenerationFinished.addListener((sender, args) -> onMazeConstructionFinished.invoke(this, EventArgs.EMPTY));

		mazeBuilder.build();
	}

	public MazeGrid getGrid() {
		return grid;
	}
}
