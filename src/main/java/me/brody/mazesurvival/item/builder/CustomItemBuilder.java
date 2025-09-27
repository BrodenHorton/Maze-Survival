package me.brody.mazesurvival.item.builder;

import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public abstract class CustomItemBuilder<T> {
    protected ItemStack itemStack;
    protected ItemMeta itemMeta;
    protected String customItemName;

    public CustomItemBuilder(ItemStack itemStack, String customItemName) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.customItemName = customItemName;
        withPersistentData(NamespacedKeys.CUSTOM_ITEM, PersistentDataType.STRING, customItemName);
    }

    public T withDisplayName(String name) {
        itemMeta.setDisplayName(name);
        return link();
    }

    public T withLore(List<String> lore) {
        itemMeta.setLore(lore);
        return link();
    }

    public T withCustomEnchantments(List<EnchantmentEntry> enchantmentEntries) {
        EnchantmentList enchantmentList = new EnchantmentList(enchantmentEntries);
        withPersistentData(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType(), enchantmentList);
        return link();
    }

    public <P, C> T withPersistentData(NamespacedKey namespacedKey, PersistentDataType<P, C> persistentDataType, C value) {
        itemMeta.getPersistentDataContainer().set(namespacedKey, persistentDataType, value);
        return link();
    }

    public abstract T link();

    public CustomItem build() {
        itemStack.setItemMeta(itemMeta);
        return new CustomItem(itemStack, customItemName);
    }
}
