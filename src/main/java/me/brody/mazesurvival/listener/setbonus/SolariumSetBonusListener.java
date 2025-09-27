package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class SolariumSetBonusListener implements Listener {
    private final Main plugin;

    public SolariumSetBonusListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void negateFireLikeDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(e.getCause() != DamageCause.FIRE && e.getCause() != DamageCause.FIRE_TICK && e.getCause() != DamageCause.LAVA && e.getCause() != DamageCause.HOT_FLOOR)
            return;
        if(!CustomItem.SOLARIUM_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.SOLARIUM_CHESTPLATE.compareItem(player.getInventory().getChestplate())
                || !CustomItem.SOLARIUM_LEGGINGS.compareItem(player.getInventory().getLeggings()) || !CustomItem.SOLARIUM_BOOTS.compareItem(player.getInventory().getBoots()))
            return;

        e.setCancelled(true);
    }

}
