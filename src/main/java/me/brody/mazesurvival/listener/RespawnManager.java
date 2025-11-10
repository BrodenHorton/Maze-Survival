package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;
import java.util.Map;

public class RespawnManager implements Listener {
    private final Main plugin;
    private Map<Player, Location> respawnLocationByPlayer;

    public RespawnManager(Main plugin) {
        this.plugin = plugin;
        respawnLocationByPlayer = new HashMap<>();
    }

    public void setPlayerRespawnLocation(Player p, Location location) {
        respawnLocationByPlayer.put(p, location);
    }

    @EventHandler
    public void setPlayerRespawnLocationEvent(PlayerRespawnEvent e) {
        if(!respawnLocationByPlayer.containsKey(e.getPlayer()))
            return;

        e.setRespawnLocation(respawnLocationByPlayer.get(e.getPlayer()));
    }
}
