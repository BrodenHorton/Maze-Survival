package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SlimeSplitEvent;

public class SlimeSplitListener implements Listener {

    public SlimeSplitListener() {}

    @EventHandler
    public void cancelCustomMobSplit(SlimeSplitEvent e) {
        if(!e.getEntity().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_MOB))
            return;

        e.setCancelled(true);
    }
}
