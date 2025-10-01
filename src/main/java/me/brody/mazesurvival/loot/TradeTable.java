package me.brody.mazesurvival.loot;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;

public class TradeTable extends WeightedList<MerchantRecipe> {
    private static final int MAX_TRADE_USES = 20;
    public static final TradeTable STRONGHOLD_TRADER;

    static {
        STRONGHOLD_TRADER = new TradeTable();
        STRONGHOLD_TRADER.addRecipeToTradeTable(new ItemStack(Material.OAK_LOG, 10), new ItemStack(Material.BIRCH_LOG, 10), 10);


    }

    private void addRecipeToTradeTable(ItemStack ingredient, ItemStack result, int weight) {
        addRecipeToTradeTable(List.of(ingredient), result, weight);
    }

    private void addRecipeToTradeTable(List<ItemStack> ingredients, ItemStack result, int weight) {
        MerchantRecipe tradeRecipe = new MerchantRecipe(result, MAX_TRADE_USES);
        for(int i = 0; i < ingredients.size(); i++)
            tradeRecipe.addIngredient(ingredients.get(i));
        add(tradeRecipe, weight);
    }

    private TradeTable() {}

}
