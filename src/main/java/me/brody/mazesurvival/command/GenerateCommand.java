package me.brody.mazesurvival.command;

import me.brody.mazesurvival.maze.grid.MazeGridBase;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.ChatUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateCommand implements SubCommand {
	private static final String NAME = "generate";
	private static final String DESCRIPTION = "Generates a maze based on arguments";
	private static final String ERROR_MSG =
			String.format("%sInvalid args\nCommand Parameters: /ms generate <Grid Base>", ERROR_COLOR);

	private Map<String, MazeGridBase> gridBaseByName;
	private List<String> tabList;

	public GenerateCommand() {
		gridBaseByName = new HashMap<>();
		gridBaseByName.put("standard", Registry.GRID_BASE.get("standard"));
		gridBaseByName.put("small", Registry.GRID_BASE.get("small"));

		tabList = new ArrayList<>();
		tabList.add("standard");
		tabList.add("small");
	}

	@Override
	public void executeCommand(Main plugin, Player p, String[] args) {
		if(args.length != 2 || !gridBaseByName.containsKey(args[1].toLowerCase())) {
			ChatUtils.msg(p, ERROR_MSG);
			return;
		}

		Location gridOrigin = LocationUtils.copy(p.getLocation());
		gridOrigin.setX((int)gridOrigin.getX());
		gridOrigin.setY((int)gridOrigin.getY() - 1);
		gridOrigin.setZ((int)gridOrigin.getZ());

		plugin.getMazeManager().generateMaze(gridBaseByName.get(args[1]), gridOrigin);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public List<String> getTabCompletionList() {
		return tabList;
	}
}
