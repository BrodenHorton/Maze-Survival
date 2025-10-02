package me.brody.mazesurvival.loot.chest;

import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class SimpleLootTableEntry implements Supplier<ItemStack> {
    private ItemStack itemStack;

    public SimpleLootTableEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack get() {
        return itemStack;
    }
}
