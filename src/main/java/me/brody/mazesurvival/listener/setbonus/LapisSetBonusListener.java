package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;

public class LapisSetBonusListener implements Listener {

    private final Main plugin;

    public LapisSetBonusListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void negatePoisonAndWitherDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(e.getCause() != DamageCause.POISON && e.getCause() != DamageCause.WITHER)
            return;
        if(!CustomItem.LAPIS_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.LAPIS_CHESTPLATE.compareItem(player.getInventory().getChestplate())
                || !CustomItem.LAPIS_LEGGINGS.compareItem(player.getInventory().getLeggings()) || !CustomItem.LAPIS_BOOTS.compareItem(player.getInventory().getBoots()))
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void negateHunger(EntityPotionEffectEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(e.getModifiedType() != PotionEffectType.HUNGER)
            return;
        if(!CustomItem.LAPIS_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.LAPIS_HELMET.compareItem(player.getInventory().getChestplate())
                || !CustomItem.LAPIS_HELMET.compareItem(player.getInventory().getLeggings()) || !CustomItem.LAPIS_HELMET.compareItem(player.getInventory().getBoots()))
            return;

        e.setCancelled(true);
    }

}
