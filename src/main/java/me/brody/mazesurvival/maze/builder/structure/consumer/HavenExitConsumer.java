package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.SerializableConsumer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HavenExitConsumer implements SerializableConsumer<Player> {
    private transient final Main plugin;
    private transient Location teleportLocation;

    public HavenExitConsumer(Main plugin, Location teleportLocation) {
        this.plugin = plugin;
        this.teleportLocation = teleportLocation;
    }

    @Override
    public void accept(Player p) {
        if(plugin.getDayNightCycle().isDay()) {
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        }
        else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot exit a Haven at night")));
        }
    }
}
