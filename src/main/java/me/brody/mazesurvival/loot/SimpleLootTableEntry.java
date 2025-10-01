package me.brody.mazesurvival.loot;

import org.bukkit.inventory.ItemStack;

public class SimpleLootTableEntry implements LootTableEntry {
    private ItemStack itemStack;

    public SimpleLootTableEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack obtain() {
        return itemStack;
    }
}
