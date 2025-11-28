package me.brody.mazesurvival.listener.mob;

import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class MagmaCubeFireEffectListener implements Listener {

    public MagmaCubeFireEffectListener() {}

    @EventHandler
    public void magmaCubeOnHitFire(EntityDamageByEntityEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(!e.getDamager().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING))
            return;

        String mobName = e.getDamager().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING);
        if(mobName.equalsIgnoreCase(CustomMob.MAGMA_OOZE.getMobId())
                || mobName.equalsIgnoreCase(CustomMob.BIG_MAGMA_OOZE.getMobId())
                || mobName.equalsIgnoreCase(CustomMob.MEGA_MAGMA_OOZE.getMobId())
                || mobName.equalsIgnoreCase(CustomMob.ULTRA_MAGMA_OOZE.getMobId())) {
            player.setFireTicks(80);
        }
    }
}
