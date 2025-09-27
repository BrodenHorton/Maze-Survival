package me.brody.mazesurvival.loot;

import me.brody.mazesurvival.utils.WeightedList;
import me.brody.mazesurvival.utils.WeightedEntry;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class TradeTable {
    public static final TradeTable STRONGHOLD_TRADER;

    private WeightedList<MerchantRecipe> weightedTrades;

    static {
        WeightedList<MerchantRecipe> strongholdTrades = new WeightedList<>();

        MerchantRecipe strongholdRecipe1 = new MerchantRecipe(new ItemStack(Material.BIRCH_LOG), 999);
        strongholdRecipe1.addIngredient(new ItemStack(Material.OAK_LOG, 14));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe1, 10));

        MerchantRecipe strongholdRecipe2 = new MerchantRecipe(new ItemStack(Material.SPRUCE_LOG), 999);
        strongholdRecipe2.addIngredient(new ItemStack(Material.COBBLESTONE, 12));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe2, 10));

        MerchantRecipe strongholdRecipe3 = new MerchantRecipe(new ItemStack(Material.AMETHYST_SHARD), 999);
        strongholdRecipe3.addIngredient(new ItemStack(Material.COBBLESTONE, 8));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe3, 10));

        MerchantRecipe strongholdRecipe4 = new MerchantRecipe(new ItemStack(Material.MELON_SLICE), 999);
        strongholdRecipe4.addIngredient(new ItemStack(Material.OAK_LOG, 10));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe4, 10));

        MerchantRecipe strongholdRecipe5 = new MerchantRecipe(new ItemStack(Material.LEATHER_HELMET), 999);
        strongholdRecipe5.addIngredient(new ItemStack(Material.WHEAT_SEEDS, 4));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe5, 10));

        MerchantRecipe strongholdRecipe6 = new MerchantRecipe(new ItemStack(Material.LEATHER_CHESTPLATE), 999);
        strongholdRecipe6.addIngredient(new ItemStack(Material.LEATHER, 18));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe6, 10));

        MerchantRecipe strongholdRecipe7 = new MerchantRecipe(new ItemStack(Material.LEATHER_LEGGINGS), 999);
        strongholdRecipe7.addIngredient(new ItemStack(Material.LEATHER, 9));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe7, 10));

        MerchantRecipe strongholdRecipe8 = new MerchantRecipe(new ItemStack(Material.LEATHER_BOOTS), 999);
        strongholdRecipe8.addIngredient(new ItemStack(Material.WHEAT_SEEDS, 2));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe8, 10));

        MerchantRecipe strongholdRecipe9 = new MerchantRecipe(new ItemStack(Material.WOODEN_SWORD), 999);
        strongholdRecipe9.addIngredient(new ItemStack(Material.OAK_LOG, 15));
        strongholdTrades.getWeightedEntries().add(new WeightedEntry(strongholdRecipe9, 10));


        STRONGHOLD_TRADER = new TradeTable(strongholdTrades);
    }

    private TradeTable() {
        weightedTrades = new WeightedList<>();
    }

    private TradeTable(WeightedList<MerchantRecipe> weightedTrades) {
        this.weightedTrades = weightedTrades;
    }

    public WeightedList<MerchantRecipe> getWeightedTrades() {
        return weightedTrades;
    }
}
