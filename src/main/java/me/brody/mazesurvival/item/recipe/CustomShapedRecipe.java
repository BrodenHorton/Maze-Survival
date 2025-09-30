package me.brody.mazesurvival.item.recipe;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomShapedRecipe implements CustomRecipe {
    private static final char BLANK_SLOT = ' ';

    private List<String> recipe;
    private Map<Character, ItemStack> ingredientByChar;
    private ItemStack result;

    public CustomShapedRecipe(String s1, ItemStack result) {
        recipe = new ArrayList<>();
        processRecipeLine(s1);
        ingredientByChar = new HashMap<>();
        this.result = result;
    }

    public CustomShapedRecipe(String s1, String s2, ItemStack result) {
        recipe = new ArrayList<>();
        processRecipeLine(s1);
        processRecipeLine(s2);
        ingredientByChar = new HashMap<>();
        this.result = result;
    }

    public CustomShapedRecipe(String s1, String s2, String s3, ItemStack result) {
        recipe = new ArrayList<>();
        processRecipeLine(s1);
        processRecipeLine(s2);
        processRecipeLine(s3);
        ingredientByChar = new HashMap<>();
        this.result = result;
    }

    private void processRecipeLine(String str) {
        if(str != null && !str.isEmpty()) {
            String recipeLine =  str.length() > 3 ? str.substring(0, 3) : str;
            recipe.add(recipeLine);
        }
    }

    public void addIngredient(char ingredientCharacter, ItemStack ingredient) {
        ingredientByChar.put(ingredientCharacter, ingredient);
    }

    public boolean isMatchingRecipe(ItemStack[] matrix) {
        if(matrix == null)
            return false;
        if(!hasMatchingIngredients(matrix))
            return false;

        System.out.println("Matching ingredients found!");
        int rowLength = (int)Math.sqrt(matrix.length);
        int recipeHeight = recipe.size();
        int recipeWidth = 0;
        for(String recipeLine : recipe) {
            if(recipeLine.length() > recipeWidth)
                recipeWidth = recipeLine.length();
        }
        System.out.println("Crafting matrix row length: " + rowLength);
        System.out.println("Recipe width: " + recipeWidth);
        System.out.println("Recipe height: " + recipeHeight);
        for(int i = 0; i < rowLength - recipeHeight + 1; i++) {
            for(int j = 0; j < rowLength - recipeWidth + 1; j++) {
                if(isMatchingRecipeAtIndex(matrix, i, j))
                    return true;
            }
        }

        return false;
    }

    private List<ItemStack> getIngredients() {
        List<ItemStack> ingredients = new ArrayList<>();
        for(int i = 0; i < recipe.size(); i++) {
            for(int j = 0; j < recipe.get(i).length(); j++) {
                if(recipe.get(i).charAt(j) == ' ')
                    continue;

                ItemStack ingredient = ingredientByChar.get(recipe.get(i).charAt(j));
                if(ingredient != null)
                    ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    private boolean hasMatchingIngredients(ItemStack[] matrix) {
        List<ItemStack> ingredients = getIngredients();
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i] == null)
                continue;
            boolean hasMatchingIngredient = false;
            for(ItemStack item : ingredients) {
                if(item.isSimilar(matrix[i])) {
                    hasMatchingIngredient = true;
                    ingredients.remove(item);
                    break;
                }
            }
            if(!hasMatchingIngredient)
                return false;
        }

        return ingredients.isEmpty();
    }

    private boolean isMatchingRecipeAtIndex(ItemStack[] matrix, int row, int column) {
        int rowLength = (int)Math.sqrt(matrix.length);
        for(int i = 0; i < recipe.size(); i++) {
            for(int j = 0; j < recipe.get(i).length(); j++) {
                int craftingSlotIndex = (row + i) * rowLength + (column + j);
                if(recipe.get(i).charAt(j) == BLANK_SLOT) {
                    if(matrix[craftingSlotIndex] != null)
                        return false;
                }
                else {
                    ItemStack item = ingredientByChar.get(recipe.get(i).charAt(j));
                    if(!item.isSimilar(matrix[craftingSlotIndex]))
                        return false;
                }

            }
        }

        return true;
    }

    public ItemStack getResult() {
        return result.clone();
    }
}
