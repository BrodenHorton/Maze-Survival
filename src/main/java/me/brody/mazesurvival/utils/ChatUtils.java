package me.brody.mazesurvival.utils;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
	public static void msg(Player p, String msg) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
}
