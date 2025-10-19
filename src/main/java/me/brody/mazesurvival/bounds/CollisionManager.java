package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager implements Listener {
    private final Main plugin;
    private List<CollisionBounds> collisionBoundsList;

    public CollisionManager(Main plugin) {
        this.plugin = plugin;
        collisionBoundsList = new ArrayList<>();
    }

    public void addTriggerBounds(CollisionBounds collisionBounds) {
        collisionBoundsList.add(collisionBounds);
    }

    @EventHandler
    public void onTriggerEnter(PlayerMoveEvent e) {
        for(CollisionBounds entry : collisionBoundsList) {
            if(!entry.getBounds().containsLocation(e.getFrom()) && entry.getBounds().containsLocation(e.getTo()))
                entry.onTriggerEnter(e.getPlayer());
        }
    }

    @EventHandler
    public void onTriggerExit(PlayerMoveEvent e) {
        for(CollisionBounds entry : collisionBoundsList) {
            if(!entry.getBounds().containsLocation(e.getTo()) && entry.getBounds().containsLocation(e.getFrom()))
                entry.onTriggerExit(e.getPlayer());
        }
    }

}
