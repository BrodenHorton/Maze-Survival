package me.brody.mazesurvival.item.builder;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Map;

public abstract class CustomItemBuilder<T> {
    protected ItemStack itemStack;
    protected String customItemName;

    public CustomItemBuilder(ItemStack itemStack, String customItemName) {
        this.itemStack = itemStack;
        this.customItemName = customItemName;
        withPersistentData(NamespacedKeys.CUSTOM_ITEM, PersistentDataType.STRING, customItemName);
    }

    public T withDisplayName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return link();
    }

    public T withLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return link();
    }

    public T withCustomEnchantments(Map<MazeEnchantment, Integer> enchantments) {
        for(Map.Entry<MazeEnchantment, Integer> entry : enchantments.entrySet())
            entry.getKey().enchantItem(itemStack, entry.getValue());

        return link();
    }

    public <P, C> T withPersistentData(NamespacedKey namespacedKey, PersistentDataType<P, C> persistentDataType, C value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, persistentDataType, value);
        itemStack.setItemMeta(itemMeta);
        return link();
    }

    public abstract T link();

    public CustomItem build() {
        return new CustomItem(itemStack, customItemName);
    }
}
