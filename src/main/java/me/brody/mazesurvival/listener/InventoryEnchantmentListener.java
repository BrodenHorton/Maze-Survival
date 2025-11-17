package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.*;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.player.PlayerProfile;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class InventoryEnchantmentListener implements Listener {
    private final Main plugin;

    public InventoryEnchantmentListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void addInventoryEnchantment(InventoryClickEvent e) {
        if(e.getAction() != InventoryAction.SWAP_WITH_CURSOR)
            return;
        if(!(e.getWhoClicked() instanceof Player))
            return;
        Player player = (Player)e.getWhoClicked();
        PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
        if(profile == null)
            return;
        if(e.getCursor() == null)
            return;
        PersistentDataContainer cursorDataContainer = e.getCursor().getItemMeta().getPersistentDataContainer();
        if(!cursorDataContainer.has(NamespacedKeys.SCRIPT))
            return;
        Script script = cursorDataContainer.get(NamespacedKeys.SCRIPT, new ScriptDataType());
        if(script.getEnchantmentEntries().isEmpty())
            return;
        if(e.getCurrentItem() == null)
            return;
        if(e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS)) {
            player.playSound(player, Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
            e.setCancelled(true);
            return;
        }
        Map<MazeEnchantment, Integer> compatibleEnchantments = new HashMap<>();
        for(int i = 0; i < script.getEnchantmentEntries().size(); i++) {
            MazeEnchantment mazeEnchantment = Registry.ENCHANTMENT.get(script.getEnchantmentEntries().get(i).getEnchantmentName());
            if(mazeEnchantment != null && mazeEnchantment.getCategory().contains(e.getCurrentItem().getType()))
                compatibleEnchantments.put(mazeEnchantment, script.getEnchantmentEntries().get(i).getLevel());
        }
        if(compatibleEnchantments.isEmpty()) {
            player.playSound(player, Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
            e.setCancelled(true);
            return;
        }

        for(Map.Entry<MazeEnchantment, Integer> mazeEnchantment : compatibleEnchantments.entrySet())
            mazeEnchantment.getKey().enchantItem(e.getCurrentItem(), mazeEnchantment.getValue());
        if(e.getCursor().getAmount() <= 1)
            e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
        else
            e.getCursor().setAmount(e.getCursor().getAmount() - 1);
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        e.setCancelled(true);
    }

}
