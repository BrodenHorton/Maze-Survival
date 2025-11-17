package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.SerializableConsumer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.UUID;

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
