package me.brody.mazesurvival.item.recipe;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomShapelessRecipe implements CustomRecipe {
    private List<ItemStack> ingredients;
    private ItemStack result;

    public CustomShapelessRecipe(List<ItemStack> ingredients, ItemStack result) {
        this.ingredients = ingredients;
        this.result = result;
    }

    public boolean isMatchingRecipe(ItemStack[] matrix) {
        List<ItemStack> remainingIngredients = new ArrayList<>(ingredients);
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i] == null)
                continue;

            boolean hasMatchingIngredient = false;
            for(int j = 0; j < remainingIngredients.size(); j++) {
                if(matrix[i].isSimilar(remainingIngredients.get(j))) {
                    remainingIngredients.remove(j);
                    hasMatchingIngredient = true;
                    break;
                }
            }
            if(!hasMatchingIngredient)
                return false;
        }

        return remainingIngredients.isEmpty();
    }

    public ItemStack getResult() {
        return result.clone();
    }
}
