package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class HealthBoostListener implements Listener {
    public static final int PLAYER_BASE_HEALTH = 6;

    public final Main plugin;

    public HealthBoostListener(Main plugin) {
        this.plugin = plugin;
    }

    public void updatePlayerHealth(Player player) {
        ItemStack[] equipment =  player.getInventory().getArmorContents();
        int healthBoost = 0;
        for(int i = 0; i < equipment.length; i++) {
            if(equipment[i] != null && equipment[i].getItemMeta().getPersistentDataContainer().has(NamespacedKeys.ARMOR_HEALTH_BOOST)) {
                healthBoost += equipment[i].getItemMeta().getPersistentDataContainer().get(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER);
            }
        }

        player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(healthBoost + PLAYER_BASE_HEALTH);
    }

    @EventHandler
    public void updatePlayerHealth() {

    }

}
