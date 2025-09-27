package me.brody.mazesurvival.enchantment;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TableEnchantment {
    private MazeEnchantment enchantment;
    private ItemStack icon;
    private List<Integer> enchantmentLevelXpCosts;

    public TableEnchantment(MazeEnchantment enchantment, ItemStack icon, List<Integer> enchantmentLevelXpCosts) {
        this.enchantment = enchantment;
        this.icon = icon;
        this.enchantmentLevelXpCosts = enchantmentLevelXpCosts;
    }

    public MazeEnchantment getEnchantment() {
        return enchantment;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<Integer> getEnchantmentLevelXpCosts() {
        return enchantmentLevelXpCosts;
    }
}
