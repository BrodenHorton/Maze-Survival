package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockLightningFireListener implements Listener {
    private final Main plugin;

    public BlockLightningFireListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockLightningFire(BlockIgniteEvent e) {
        if(e.getCause() != BlockIgniteEvent.IgniteCause.LIGHTNING)
            return;

        e.setCancelled(true);
    }

}
