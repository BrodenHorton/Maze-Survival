package me.brody.mazesurvival.item.builder;

import org.bukkit.inventory.ItemStack;

public class SimpleCustomItemBuilder extends CustomItemBuilder<SimpleCustomItemBuilder> {

    public SimpleCustomItemBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    @Override
    public SimpleCustomItemBuilder link() {
        return this;
    }
}
