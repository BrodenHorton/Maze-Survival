package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RespawnManager implements Listener, Serializable {
    private transient final Main plugin;
    private transient Map<UUID, Location> respawnLocationByPlayer;

    public RespawnManager(Main plugin) {
        this.plugin = plugin;
        respawnLocationByPlayer = new HashMap<>();
    }

    public void setPlayerRespawnLocation(Player p, Location location) {
        respawnLocationByPlayer.put(p.getUniqueId(), location);
    }

    @EventHandler
    public void setPlayerRespawnLocationEvent(PlayerRespawnEvent e) {
        if(!respawnLocationByPlayer.containsKey(e.getPlayer().getUniqueId()))
            return;

        e.setRespawnLocation(respawnLocationByPlayer.get(e.getPlayer().getUniqueId()));
    }
}
