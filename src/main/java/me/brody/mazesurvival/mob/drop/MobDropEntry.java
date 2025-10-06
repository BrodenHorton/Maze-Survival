package me.brody.mazesurvival.mob.drop;

import org.bukkit.inventory.ItemStack;

public class MobDropEntry {
    private ItemStack itemStack;
    private int maxQuantity;

    public MobDropEntry(ItemStack itemStack, int maxQuantity) {
        this.itemStack = itemStack;
        this.maxQuantity = maxQuantity;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
}
