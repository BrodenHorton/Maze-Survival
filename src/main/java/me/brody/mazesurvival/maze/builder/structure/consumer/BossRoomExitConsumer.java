package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.SerializableConsumer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BossRoomExitConsumer implements SerializableConsumer<Player> {
    private transient final Main plugin;
    private MazeRegion region;
    private transient Location teleportLocation;

    public BossRoomExitConsumer(Main plugin, MazeRegion region, Location teleportLocation) {
        this.plugin = plugin;
        this.region = region;
        this.teleportLocation = teleportLocation;
    }

    @Override
    public void accept(Player p) {
        if(plugin.getGameState().getClearedRegions().contains(region.getUuid())) {
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
        }
        else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot exit until the boss is defeated")));
        }
    }
}
