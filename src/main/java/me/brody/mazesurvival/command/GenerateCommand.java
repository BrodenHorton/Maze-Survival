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

	private List<String> tabList;

	public GenerateCommand() {
		tabList = new ArrayList<>();
		tabList.addAll(Registry.GRID_BASE.keySet());
	}

	@Override
	public void executeCommand(Main plugin, Player p, String[] args) {
		if(args.length < 2 || args.length > 4) {
			ChatUtils.msg(p, String.format("%sInvalid args\nCommand Parameters: /ms generate <grid base> [debug]", ERROR_COLOR));
			return;
		}
		if(plugin.isGameRunning()) {
			boolean hasConfirm = false;
			for(int i = 2; i < args.length; i++) {
				if(args[i].equalsIgnoreCase("confirm")) {
					hasConfirm = true;
					break;
				}
			}
			if(!hasConfirm) {
				ChatUtils.msg(p, ERROR_COLOR + "If you generate a new maze you will override " +
						"the current maze save file. If you want to override the previous save file " +
						"use the command:\n&e/ms generate <grid base> &7[debug] &econfirm");
				return;
			}
		}

		boolean shouldEnableDebugMode = false;
		for(int i = 2; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("debug")) {
				shouldEnableDebugMode = true;
				break;
			}
		}

		Location gridOrigin = LocationUtils.copy(p.getLocation());
		gridOrigin.setX((int)gridOrigin.getX());
		gridOrigin.setY((int)gridOrigin.getY() - 1);
		gridOrigin.setZ((int)gridOrigin.getZ());

		plugin.getGameManager().startGame(Registry.GRID_BASE.get(args[1]), gridOrigin, shouldEnableDebugMode);
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
