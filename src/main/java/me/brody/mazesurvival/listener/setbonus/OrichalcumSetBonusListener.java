package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class OrichalcumSetBonusListener implements Listener {
    private final Main plugin;

    public OrichalcumSetBonusListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void negateFireLikeDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(e.getCause() != DamageCause.FIRE && e.getCause() != DamageCause.FIRE_TICK && e.getCause() != DamageCause.LAVA && e.getCause() != DamageCause.HOT_FLOOR)
            return;
        if(!CustomItem.ORICHALCUM_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.ORICHALCUM_CHESTPLATE.compareItem(player.getInventory().getChestplate())
                || !CustomItem.ORICHALCUM_LEGGINGS.compareItem(player.getInventory().getLeggings()) || !CustomItem.ORICHALCUM_BOOTS.compareItem(player.getInventory().getBoots()))
            return;

        e.setCancelled(true);
    }

}
