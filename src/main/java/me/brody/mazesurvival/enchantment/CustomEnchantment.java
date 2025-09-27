package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.persistentdata.*;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.RomanNumeralUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;

public class CustomEnchantment extends MazeEnchantment {

    public CustomEnchantment(Main plugin, String enchantmentName, EnchantmentCategory category) {
        super(plugin, enchantmentName, category);
    }

    @Override
    public void enchantScript(ItemStack itemStack, int level) {
        if(itemStack == null)
            return;

        level = level > 0 ? level : 1;

        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();
        lore.add(0, ChatColor.YELLOW + "" + enchantmentName + " " + RomanNumeralUtils.romanNumeralOf(level));
        meta.setLore(lore);
        Script script = meta.getPersistentDataContainer().get(NamespacedKeys.SCRIPT, new ScriptDataType());
        script.getEnchantmentEntries().add(new EnchantmentEntry(enchantmentName, level));
        meta.getPersistentDataContainer().set(NamespacedKeys.SCRIPT, new ScriptDataType(), script);
        itemStack.setItemMeta(meta);
    }

    @Override
    public void enchantItem(ItemStack itemStack, int level) {
        if(itemStack == null || !category.contains(itemStack.getType()))
            return;

        level = level > 0 ? level : 1;

        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();
        lore.add(0, ChatColor.YELLOW + "" + enchantmentName + " " + RomanNumeralUtils.romanNumeralOf(level));
        meta.setLore(lore);
        EnchantmentList enchantmentList;
        if(meta.getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS))
            enchantmentList = meta.getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
        else
            enchantmentList = new EnchantmentList();
        enchantmentList.getEnchantmentEntries().add(new EnchantmentEntry(enchantmentName, level));
        meta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType(), enchantmentList);
        itemStack.setItemMeta(meta);
    }

    @Override
    public boolean containsEnchant(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.SCRIPT))
            return false;

        Script script = meta.getPersistentDataContainer().get(NamespacedKeys.SCRIPT, new ScriptDataType());

        for(int i = 0; i < script.getEnchantmentEntries().size(); i++) {
            if(script.getEnchantmentEntries().get(i).getEnchantmentName().equals(enchantmentName))
                return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CustomEnchantment))
            return false;

        CustomEnchantment other = (CustomEnchantment) obj;
        return other.getEnchantmentName().equals(enchantmentName);
    }
}
