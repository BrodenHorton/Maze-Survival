package me.brody.mazesurvival.mob;

import org.bukkit.inventory.ItemStack;

public class MobDropEntry {
    private ItemStack itemStack;
    private int maxQuantity;
    private double dropRate;

    public MobDropEntry(ItemStack itemStack, int maxQuantity, double dropRate) {
        this.itemStack = itemStack;
        this.maxQuantity = maxQuantity;
        this.dropRate = dropRate;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public double getDropRate() {
        return dropRate;
    }
}
