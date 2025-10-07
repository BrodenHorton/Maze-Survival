package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class MobDropListener implements Listener {

    public final Main plugin;

    public MobDropListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void customMobDrops(EntityDeathEvent e) {
        if(!e.getEntity().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_MOB))
            return;

        String customMobName = e.getEntity().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING).toLowerCase().replace('-', '_');
        CustomMob customMob = Registry.CUSTOM_MOB.get(customMobName);
        int lootingLevel = 0;
        Player killer = e.getEntity().getKiller();
        if(killer != null && killer.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.LOOTING))
            lootingLevel = killer.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOTING);

        e.getDrops().clear();
        List<ItemStack> drops = customMob.getDropTable().getDrops(lootingLevel);
        for(int i = 0; i < drops.size(); i++)
            e.getDrops().add(drops.get(i));
    }

}
