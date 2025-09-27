package me.brody.mazesurvival.command;

import org.bukkit.entity.Player;

import me.brody.mazesurvival.Main;

import java.util.List;

public interface SubCommand {
	String ERROR_COLOR = "&c";

	void executeCommand(Main plugin, Player p, String args[]);

	String getName();
	
	String getDescription();

	List<String> getTabCompletionList();
}
