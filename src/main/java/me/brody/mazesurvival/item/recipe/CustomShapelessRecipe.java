package me.brody.mazesurvival.item.recipe;

import me.brody.mazesurvival.utils.MathUtils;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomShapelessRecipe implements CustomRecipe {
    private String id;
    private List<ItemStack> ingredients;
    private ItemStack result;

    public CustomShapelessRecipe(ItemStack ingredient, ItemStack result, String id) {
        this(new ArrayList<>(Arrays.asList(ingredient)), result, id);
    }

    public CustomShapelessRecipe(List<ItemStack> ingredients, ItemStack result, String id) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
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

    @Override
    public ItemStack getResult() {
        return result.clone();
    }

    @Override
    public ItemStack[][] getRecipeDisplay() {
        if(ingredients.isEmpty())
            return new ItemStack[0][0];

        int rows = ingredients.size() / 3 + 1;
        int columns = MathUtils.clamp(ingredients.size(), 0, 3);
        ItemStack[][] result = new ItemStack[rows][columns];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[i][j] = ingredients.get(i * result.length + j);
            }
        }

        return result;
    }

    @Override
    public String getId() {
        return id;
    }
}
