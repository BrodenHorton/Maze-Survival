package me.brody.mazesurvival.listener.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class SoulBoundEnchantmentListener implements Listener {
    private static Map<UUID, List<ItemStack>> soulBoundItemsByUuid = new HashMap<>();

    private final Main plugin;

    public SoulBoundEnchantmentListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void saveSoulBoundItems(PlayerDeathEvent e) {
        List<ItemStack> soulBoundItems = new ArrayList<>();
        ItemStack[] inventoryItems = e.getEntity().getInventory().getContents();
        for(int i = inventoryItems.length - 1; i >= 0; i--) {
            if(inventoryItems[i] == null || !inventoryItems[i].getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS))
                continue;

            EnchantmentList enchantmentList = inventoryItems[i].getItemMeta().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
            for(int j = 0; j < enchantmentList.getEnchantmentEntries().size(); j++) {
                if(enchantmentList.getEnchantmentEntries().get(j).getEnchantmentName().equals("Soul Bound")) {
                    soulBoundItems.add(inventoryItems[i]);
                    e.getDrops().remove(inventoryItems[i]);
                    break;
                }
            }
        }

        plugin.getLogger().info("Number of Soul Bound Items on " + e.getEntity().getDisplayName() + ": " + soulBoundItems.size());
        soulBoundItemsByUuid.put(e.getEntity().getUniqueId(), soulBoundItems);
    }

    @EventHandler
    public void restoreSoulBoundItems(PlayerRespawnEvent e) {
        if(!soulBoundItemsByUuid.containsKey(e.getPlayer().getUniqueId()))
            return;
        List<ItemStack> soulBoundItems = soulBoundItemsByUuid.get(e.getPlayer().getUniqueId());
        if(soulBoundItems == null)
            return;

        for(int i = 0; i < soulBoundItems.size(); i++)
            e.getPlayer().getInventory().addItem(soulBoundItems.get(i));
        soulBoundItemsByUuid.remove(e.getPlayer().getUniqueId());
    }
}
