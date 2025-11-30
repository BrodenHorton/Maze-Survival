package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataType;

public class DurabilityListener implements Listener {

    public DurabilityListener() {}

    @EventHandler
    public void decrementCustomDurability(PlayerItemDamageEvent e) {
        if(!(e.getItem().getItemMeta() instanceof Damageable meta))
            return;
        if(!meta.getPersistentDataContainer().has(NamespacedKeys.BASE_DURABILITY))
            return;
        if(!meta.getPersistentDataContainer().has(NamespacedKeys.CURRENT_DURABILITY))
            return;

        int currentDurability = meta.getPersistentDataContainer().get(NamespacedKeys.CURRENT_DURABILITY, PersistentDataType.INTEGER);
        currentDurability--;
        meta.getPersistentDataContainer().set(NamespacedKeys.CURRENT_DURABILITY, PersistentDataType.INTEGER, currentDurability);
        if(currentDurability <= 0)
            meta.setDamage(e.getItem().getType().getMaxDurability());
        else {
            e.setCancelled(true);
            int baseDurability = meta.getPersistentDataContainer().get(NamespacedKeys.BASE_DURABILITY, PersistentDataType.INTEGER);
            float remainingDurability = currentDurability / (float)baseDurability;
            meta.setDamage((int)(e.getItem().getType().getMaxDurability() * (1 - remainingDurability)));
        }
        e.getItem().setItemMeta(meta);
    }

}
