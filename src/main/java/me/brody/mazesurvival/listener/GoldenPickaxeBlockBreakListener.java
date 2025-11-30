package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class GoldenPickaxeBlockBreakListener implements Listener {

    public GoldenPickaxeBlockBreakListener() {}

    @EventHandler(priority = EventPriority.HIGHEST)
    public void goldPickaxeBlockBreak(BlockBreakEvent e) {
        ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
        if(itemStack.getType() != Material.GOLDEN_PICKAXE)
            return;
        if(!itemStack.getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ITEM))
            return;

        e.getBlock().breakNaturally(new ItemStack(Material.IRON_PICKAXE));
        PlayerItemDamageEvent itemDamageEvent = new PlayerItemDamageEvent(e.getPlayer(), itemStack, 1);
        Bukkit.getPluginManager().callEvent(itemDamageEvent);
    }
}
