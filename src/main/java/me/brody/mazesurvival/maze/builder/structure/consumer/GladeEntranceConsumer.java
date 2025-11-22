package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.SerializableConsumer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.UUID;

public class GladeEntranceConsumer implements SerializableConsumer<Player> {
    private transient Main plugin;
    private transient Location teleportLocation;

    public GladeEntranceConsumer(Main plugin, Location teleportLocation) {
        this.plugin = plugin;
        this.teleportLocation = teleportLocation;
    }

    @Override
    public void accept(Player p) {
        plugin.getRespawnManager().setPlayerRespawnLocation(p, teleportLocation);
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
