package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.SerializableConsumer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HavenEntranceConsumer implements SerializableConsumer<Player> {
    private transient final Main plugin;
    private MazeRegion region;
    private transient Location teleportLocation;

    public HavenEntranceConsumer(Main plugin, MazeRegion region, Location teleportLocation) {
        this.plugin = plugin;
        this.region = region;
        this.teleportLocation = teleportLocation;
    }

    @Override
    public void accept(Player p) {
        if(region.getRegionLevelRequirement() > plugin.getGameState().getClearedRegions().size()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cUnable to enter haven. Required region level is too high.")));
            return;
        }

        if(plugin.getDayNightCycle().isDay()) {
            if(!plugin.getGameState().getDiscoveredRegions().contains(region.getUuid())) {
                plugin.getGameState().addDiscoveredRegion(region);
                for(Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.2f, 1f);
                    ChatUtils.msg(onlinePlayer, "&dNew Region discovered!");
                    ChatUtils.msg(onlinePlayer, "&dNew recipes have been unlocked. View these new recipes with &e/ms recipes");
                }
            }
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            plugin.getRespawnManager().setPlayerRespawnLocation(p, teleportLocation);
        }
        else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot enter a Haven at night")));
        }
    }
}
