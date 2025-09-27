package me.brody.mazesurvival.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.*;

public class CommandManager implements CommandExecutor, TabCompleter {
	public static final String ERROR_COLOR = "&c";

	private final Main plugin;
	private Map<String, SubCommand> subCommandByName;
	
	public CommandManager(Main plugin) {
		this.plugin = plugin;
		subCommandByName = new HashMap<>();
		
		// Add all String, SubCommand pairs to the command map
		GenerateCommand generateCommand = new GenerateCommand();
		subCommandByName.put(generateCommand.getName().toLowerCase(), generateCommand);
		GiveCommand giveCommand = new GiveCommand();
		subCommandByName.put(giveCommand.getName().toLowerCase(), giveCommand);
		SummonCommand summonCommand = new SummonCommand();
		subCommandByName.put(summonCommand.getName().toLowerCase(), summonCommand);
		GradeCommand gradeCommand = new GradeCommand();
		subCommandByName.put(gradeCommand.getName().toLowerCase(), gradeCommand);
		HelpCommand helpCommand = new HelpCommand();
		subCommandByName.put(helpCommand.getName().toLowerCase(), helpCommand);

		helpCommand.init(subCommandByName);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can use MazeSurvival commands");
			return false;
		}
		Player p = (Player)sender;
		// If no arguments are added to /ms, bring up the /ms help menu
		if(args.length == 0) {
			subCommandByName.get("help").executeCommand(plugin, p, args);
			return false;
		}
		// If the sub-command entered is not a registered sub-command
		if(!subCommandByName.containsKey(args[0].toLowerCase())) {
			ChatUtils.msg(p, String.format("%sInvalid Command", ERROR_COLOR));
			ChatUtils.msg(p, String.format("%sUse /ms Help to see a list of MazeSurvival commands", ERROR_COLOR));
			return false;
		}

		subCommandByName.get(args[0].toLowerCase()).executeCommand(plugin, p, args);

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> tabList = new ArrayList<>();

		if(args.length == 1) {
			for(Map.Entry<String, SubCommand> cmdEntry : subCommandByName.entrySet())
				tabList.add(cmdEntry.getKey().toLowerCase());
		}
		else if(args.length == 2) {
			for(Map.Entry<String, SubCommand> cmdEntry : subCommandByName.entrySet()) {
				if(cmdEntry.getKey().equals(args[0])) {
					tabList = cmdEntry.getValue().getTabCompletionList();
					break;
				}
			}
		}

		return tabList;
	}
}
