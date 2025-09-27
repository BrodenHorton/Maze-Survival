package me.brody.mazesurvival.listener.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.CustomEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CrusaderEnchantmentListener implements Listener {
    private static final List<Double> levelModifiers = List.of(1.30, 1.70, 2.0);

    private final Main plugin;

    public CrusaderEnchantmentListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void crusaderEffect(EntityDeathEvent e) {
        if(e.getEntity().getKiller() == null)
            return;
        Player player = e.getEntity().getKiller();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if(!mainHandItem.getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS))
            return;
        EnchantmentList enchantmentList = mainHandItem.getItemMeta().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
        EnchantmentEntry crusaderEnchantment = null;
        for(EnchantmentEntry enchantment : enchantmentList.getEnchantmentEntries()) {
            if(enchantment.getEnchantmentName().equals(CustomEnchantment.CRUSADER.getEnchantmentName())) {
                crusaderEnchantment = enchantment;
                break;
            }
        }
        if(crusaderEnchantment == null)
            return;
        if(crusaderEnchantment.getLevel() > levelModifiers.size())
            return;

        e.getDrops().clear();
        e.setDroppedExp((int)Math.round(e.getDroppedExp() * levelModifiers.get(crusaderEnchantment.getLevel() - 1)));
    }
}
