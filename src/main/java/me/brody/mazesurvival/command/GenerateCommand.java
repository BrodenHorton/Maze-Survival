package me.brody.mazesurvival.command;

import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class GenerateCommand implements SubCommand {
	private static final String NAME = "generate";
	private static final String DESCRIPTION = "Generates a maze based on arguments";
	private static final String ERROR_MSG =
			String.format("%sInvalid args\nCommand Parameters: /ms generate <Grid Base>", ERROR_COLOR);

	private List<String> tabList;

	public GenerateCommand() {
		tabList = new ArrayList<>();
		tabList.addAll(Registry.GRID_BASE.keySet());
	}

	@Override
	public void executeCommand(Main plugin, Player p, String[] args) {
		if(args.length != 2 || !Registry.GRID_BASE.containsKey(args[1].toLowerCase())) {
			ChatUtils.msg(p, ERROR_MSG);
			return;
		}

		Location gridOrigin = LocationUtils.copy(p.getLocation());
		gridOrigin.setX((int)gridOrigin.getX());
		gridOrigin.setY((int)gridOrigin.getY() - 1);
		gridOrigin.setZ((int)gridOrigin.getZ());

		plugin.getMazeManager().generateMaze(Registry.GRID_BASE.get(args[1]), gridOrigin);
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
