package me.brody.mazesurvival.bounds;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class CollisionBounds {
    private BoundsInt bounds;
    private Consumer<Player> triggerEnterConsumer;
    private Consumer<Player> triggerExitConsumer;

    public CollisionBounds(BoundsInt bounds) {
        this.bounds = bounds;
        triggerEnterConsumer = null;
        triggerExitConsumer = null;
    }

    public CollisionBounds(BoundsInt bounds, Consumer<Player> triggerEnterConsumer, Consumer<Player> triggerExitConsumer) {
        this.bounds = bounds;
        this.triggerEnterConsumer = triggerEnterConsumer;
        this.triggerExitConsumer = triggerExitConsumer;
    }

    public BoundsInt getBounds() {
        return bounds;
    }

    public void onTriggerEnter(Player p) {
        if(triggerEnterConsumer == null)
            return;

        triggerEnterConsumer.accept(p);
    }

    public void onTriggerExit(Player p) {
        if(triggerExitConsumer == null)
            return;

        triggerExitConsumer.accept(p);
    }

    public void setTriggerEnterConsumer(Consumer<Player> consumer) {
        this.triggerEnterConsumer = consumer;
    }

    public void setTriggerExitConsumer(Consumer<Player> consumer) {
        this.triggerExitConsumer = consumer;
    }
}
