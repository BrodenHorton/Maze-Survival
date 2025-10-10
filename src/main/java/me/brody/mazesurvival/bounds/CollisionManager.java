package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CollisionManager implements Listener {
    private final Main plugin;
    private Map<BoundsInt, Consumer<Player>> playerTriggerBounds;

    public CollisionManager(Main plugin) {
        this.plugin = plugin;
        playerTriggerBounds = new HashMap<>();
    }

    public void addTriggerBounds(BoundsInt bounds, Consumer<Player> consumer) {
        playerTriggerBounds.put(bounds, consumer);
    }

    @EventHandler
    public void onTriggerEnter(PlayerMoveEvent e) {
        for(Map.Entry<BoundsInt, Consumer<Player>> entry : playerTriggerBounds.entrySet()) {
            if(!entry.getKey().containsLocation(e.getFrom()) && entry.getKey().containsLocation(e.getTo())) {
                entry.getValue().accept(e.getPlayer());
            }
        }
    }

}
