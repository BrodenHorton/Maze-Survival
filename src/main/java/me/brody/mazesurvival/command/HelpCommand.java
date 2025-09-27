package me.brody.mazesurvival.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import me.brody.mazesurvival.utils.*;
import me.brody.mazesurvival.Main;

public class HelpCommand implements SubCommand {
	private static String NAME = "help";
	private static String DESCRIPTION = "Lists all MazeSurvival commands";
	private static final int COMMANDS_PER_PAGE = 7;
	private static final String COMMAND_COLOR = "&7";
	private static final String DESCRIPTION_COLOR = "&f";
	
	List<String> helpList;
	
	public HelpCommand() {
		helpList = new ArrayList<>();
	}

	public void init(Map<String, SubCommand> subCommandByName) {
		for(Map.Entry<String, SubCommand> entry : subCommandByName.entrySet()) {
			if(entry.getValue() != this)
				helpList.add(String.format("%s/ms %2s %3s%4s", COMMAND_COLOR, entry.getKey(), DESCRIPTION_COLOR, entry.getValue().getDescription()));
		}
	}

	@Override
	public void executeCommand(Main plugin, Player p, String[] args) {
		// If there are no arguments after "/ms help" then display the help menu's first page
		if(args.length <= 1) {
			HelpMenuPage(p, 1);
			return;
		}

		try {
			int pageNum = Integer.parseInt(args[1]);
			HelpMenuPage(p, pageNum);
		}
		catch (Exception e) {
			ChatUtils.msg(p, String.format("%sInvalid args", ERROR_COLOR));
			ChatUtils.msg(p, String.format("%sCommand parameters: /ms help <Page Number>", ERROR_COLOR));
		}
	}
	
	private void HelpMenuPage(Player p, int pageNum)
	{
		if(pageNum <= 1 || (pageNum - 1) * COMMANDS_PER_PAGE >= helpList.size())
			pageNum = 1;
		
		int totalPages = (helpList.size() % COMMANDS_PER_PAGE == 0) ? helpList.size() / COMMANDS_PER_PAGE : helpList.size() / COMMANDS_PER_PAGE + 1;
		ChatUtils.msg(p, String.format("%s=== Maze Survival Help Menu ===", COMMAND_COLOR));
		ChatUtils.msg(p, String.format("%s========== Page %d/%2d ==========", COMMAND_COLOR, pageNum, totalPages));
		for(int i = 0; i < COMMANDS_PER_PAGE; i++) {
			int commandIndex = (pageNum - 1) * COMMANDS_PER_PAGE + i;
			if(commandIndex >= helpList.size())
				break;
			
			ChatUtils.msg(p, helpList.get(commandIndex));
		}
		
		if(pageNum * COMMANDS_PER_PAGE < helpList.size())
			ChatUtils.msg(p, String.format("%sType %2s/ms help %d %2sto view the next page", COMMAND_COLOR, DESCRIPTION_COLOR, pageNum + 1));
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
		return new ArrayList<>();
	}
}
