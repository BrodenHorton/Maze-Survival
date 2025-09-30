package me.brody.mazesurvival.listener.recipe;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.recipe.CustomRecipe;
import me.brody.mazesurvival.item.recipe.CustomRecipes;
import me.brody.mazesurvival.item.recipe.CustomShapelessRecipe;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftingRecipeListener implements Listener {
    private final Main plugin;

    public CraftingRecipeListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void customCraftingRecipe(PrepareItemCraftEvent e) {
        CraftingInventory craftingInventory = e.getInventory();
        ItemStack[] matrix = e.getInventory().getMatrix();
        for(CustomRecipe recipe : CustomRecipes.getRecipes()) {
            if(recipe.isMatchingRecipe(matrix))
                craftingInventory.setResult(recipe.getResult());
        }
    }

    /*@EventHandler
    public void customTwoByTwoCraftingRecipe(PrepareItemCraftEvent e) {
        CraftingInventory craftingInventory = e.getInventory();
        ItemStack[] matrix = e.getInventory().getMatrix();
        for(ItemStack ingredient : twoByTwoRecipeByIngredient.keySet()) {
            if(containsTwoByTwoOf(matrix, ingredient))
                craftingInventory.setResult(twoByTwoRecipeByIngredient.get(ingredient).clone());
        }
    }

    @EventHandler
    public void customShapelessCraftingRecipe(PrepareItemCraftEvent e) {
        CraftingInventory craftingInventory = e.getInventory();
        ItemStack[] matrix = e.getInventory().getMatrix();
        for(CustomShapelessRecipe recipe : shapelessRecipes) {
            if(recipe.isMatchingRecipe(matrix))
                craftingInventory.setResult(recipe.getResult());
        }
    }*/

    @EventHandler
    public void cancelInvalidCraftingItem(CraftItemEvent e) {
        CraftingInventory craftingInventory = e.getInventory();
        if(craftingInventory.getResult().getType() == Material.BARRIER) {
            if(e.getWhoClicked() instanceof Player player)
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.5f, 1f);
            e.setCancelled(true);
        }
    }

    /*private boolean containsTwoByTwoOf(ItemStack[] matrix, ItemStack item) {
        if(matrix == null)
            return false;
        int ingredientCount = 0;
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i] != null && matrix[i].isSimilar(item))
                ingredientCount++;
        }
        if(ingredientCount != 4)
            return false;

        int rowLength = (int)Math.sqrt(matrix.length);
        for(int i = 0; i < rowLength - 1; i++) {
            for(int j = 0; j < rowLength - 1; j++) {
                if(item.isSimilar(matrix[i * rowLength + j]) && item.isSimilar(matrix[i * rowLength + j + 1])
                        && item.isSimilar(matrix[(i + 1) * rowLength + j]) && item.isSimilar(matrix[(i + 1) * rowLength + j + 1])) {
                    return true;
                }
            }
        }

        return false;
    }*/

}
