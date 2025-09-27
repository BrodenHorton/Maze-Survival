package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class AdamantiteSetBonusListener implements Listener {
    private final Main plugin;

    public AdamantiteSetBonusListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelDurabilityDamage(PlayerItemDamageEvent e) {
        if(!CustomItem.ADAMANTITE_HELMET.compareItem(e.getItem()) && !CustomItem.ADAMANTITE_CHESTPLATE.compareItem(e.getItem())
                && !CustomItem.ADAMANTITE_LEGGINGS.compareItem(e.getItem()) && !CustomItem.ADAMANTITE_BOOTS.compareItem(e.getItem()))
            return;
        if(!CustomItem.ADAMANTITE_HELMET.compareItem(e.getPlayer().getInventory().getHelmet()) || !CustomItem.ADAMANTITE_CHESTPLATE.compareItem(e.getPlayer().getInventory().getChestplate())
                || !CustomItem.ADAMANTITE_LEGGINGS.compareItem(e.getPlayer().getInventory().getLeggings()) || !CustomItem.ADAMANTITE_BOOTS.compareItem(e.getPlayer().getInventory().getBoots()))
            return;

        e.setCancelled(true);
    }
}
