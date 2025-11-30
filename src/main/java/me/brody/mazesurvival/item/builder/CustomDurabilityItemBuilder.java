package me.brody.mazesurvival.item.builder;

import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public abstract class CustomDurabilityItemBuilder<T> extends CustomItemBuilder<T> {
    protected int baseDurability;
    protected int currentDurability;

    public CustomDurabilityItemBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
        baseDurability = 1;
        currentDurability = 1;
    }

    public T withBaseDurability(int baseDurability) {
        if(baseDurability <= 0)
            return link();

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.BASE_DURABILITY, PersistentDataType.INTEGER, baseDurability);
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.CURRENT_DURABILITY, PersistentDataType.INTEGER, baseDurability);
        itemStack.setItemMeta(itemMeta);
        return link();
    }
}
