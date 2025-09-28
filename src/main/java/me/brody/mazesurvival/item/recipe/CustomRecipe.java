package me.brody.mazesurvival.item.recipe;

import org.bukkit.inventory.ItemStack;

public interface CustomRecipe {
    boolean isMatchingRecipe(ItemStack[] matrix);

    ItemStack getResult();
}
