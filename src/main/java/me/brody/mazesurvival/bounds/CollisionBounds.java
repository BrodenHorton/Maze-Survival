package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.utils.SerializableConsumer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.function.Consumer;

public class CollisionBounds implements Serializable {
    private BoundsInt bounds;
    private SerializableConsumer<Player> triggerEnterConsumer;
    private SerializableConsumer<Player> triggerExitConsumer;

    public CollisionBounds(BoundsInt bounds, SerializableConsumer<Player> triggerEnterConsumer, SerializableConsumer<Player> triggerExitConsumer) {
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

    public void setTriggerEnterConsumer(SerializableConsumer<Player> consumer) {
        this.triggerEnterConsumer = consumer;
    }

    public void setTriggerExitConsumer(SerializableConsumer<Player> consumer) {
        this.triggerExitConsumer = consumer;
    }
}
