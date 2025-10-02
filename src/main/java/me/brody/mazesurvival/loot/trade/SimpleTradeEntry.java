package me.brody.mazesurvival.loot.trade;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SimpleTradeEntry implements Supplier<MerchantRecipe> {
    private List<ItemStack> ingredients;
    private ItemStack result;
    private int maxUses;

    public SimpleTradeEntry(ItemStack ingredient, ItemStack result, int tradeUses) {
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        this.result = result;
        this.maxUses = tradeUses;
    }

    public SimpleTradeEntry(List<ItemStack> ingredients, ItemStack result, int tradeUses) {
        this.ingredients = ingredients;
        this.result = result;
        this.maxUses = tradeUses;
    }

    @Override
    public MerchantRecipe get() {
        MerchantRecipe tradeRecipe = new MerchantRecipe(result, maxUses);
        for(int i = 0; i < ingredients.size(); i++)
            tradeRecipe.addIngredient(ingredients.get(i));
        return tradeRecipe;
    }
}
