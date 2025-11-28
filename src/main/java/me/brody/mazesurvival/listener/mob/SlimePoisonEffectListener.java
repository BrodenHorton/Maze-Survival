package me.brody.mazesurvival.listener.mob;

import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SlimePoisonEffectListener implements Listener {

    public SlimePoisonEffectListener() {}

    @EventHandler
    public void slimeOnHitPoison(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(!e.getDamager().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING))
            return;

        String mobName = e.getDamager().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING);
        if(mobName.equalsIgnoreCase(CustomMob.OOZE.getMobName()) || mobName.equalsIgnoreCase(CustomMob.BIG_OOZE.getMobName())
                || mobName.equalsIgnoreCase(CustomMob.MEGA_OOZE.getMobName()) || mobName.equalsIgnoreCase(CustomMob.ULTRA_OOZE.getMobName())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0));
        }
    }
}
