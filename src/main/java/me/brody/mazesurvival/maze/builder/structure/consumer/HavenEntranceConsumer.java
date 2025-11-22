package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.SerializableConsumer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.UUID;

public class HavenEntranceConsumer implements SerializableConsumer<Player> {
    private transient Main plugin;
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

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(teleportLocation.getWorld().getUID());
        oos.writeDouble(teleportLocation.getX());
        oos.writeDouble(teleportLocation.getY());
        oos.writeDouble(teleportLocation.getZ());
        oos.writeFloat(teleportLocation.getYaw());
        oos.writeFloat(teleportLocation.getPitch());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        plugin = JavaPlugin.getPlugin(Main.class);
        ois.defaultReadObject();
        UUID worldUuid = (UUID) ois.readObject();
        double x = ois.readDouble();
        double y = ois.readDouble();
        double z = ois.readDouble();
        float yaw = ois.readFloat();
        float pitch = ois.readFloat();
        teleportLocation = new Location(Bukkit.getWorld(worldUuid), x, y, z, yaw, pitch);
    }
}
