package me.brody.mazesurvival.item.recipe;

import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public interface CustomRecipe extends Serializable {
    boolean isMatchingRecipe(ItemStack[] matrix);

    ItemStack getResult();

    ItemStack[][] getRecipeDisplay();

    String getId();
}
