package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.persistentdata.*;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.EnchantmentUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleEnchantment extends MazeEnchantment {
    private Enchantment enchantment;

    public SimpleEnchantment(Main plugin, Enchantment enchantment, EnchantmentCategory category) {
        super(plugin, EnchantmentUtils.getNameOf(enchantment), category);
        this.enchantment = enchantment;
    }

    @Override
    public void enchantScript(ItemStack itemStack, int level) {
        if(itemStack == null || enchantment == null)
            return;

        level = level > 0 ? level : 1;

        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(enchantment, level, true);
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
        meta.addEnchant(enchantment, level, true);
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
        if(itemStack == null)
            return false;

        return itemStack.getItemMeta().getEnchants().containsKey(enchantment);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SimpleEnchantment))
            return false;

        SimpleEnchantment other = (SimpleEnchantment)obj;
        return other.getEnchantmentName().equals(enchantmentName);
    }
}
