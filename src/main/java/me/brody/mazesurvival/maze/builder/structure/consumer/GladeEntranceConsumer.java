package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.SerializableConsumer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GladeEntranceConsumer implements SerializableConsumer<Player> {
    private transient final Main plugin;
    private transient Location teleportLocation;

    public GladeEntranceConsumer(Main plugin, Location teleportLocation) {
        this.plugin = plugin;
        this.teleportLocation = teleportLocation;
    }

    @Override
    public void accept(Player p) {
        plugin.getRespawnManager().setPlayerRespawnLocation(p, teleportLocation);
    }
}
