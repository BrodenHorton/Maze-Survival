package me.brody.mazesurvival.loot.trade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BannerPatternTradeEntry implements Supplier<MerchantRecipe> {
    private static final Random RNG = new Random();
    private static final List<ItemStack> BANNER_PATTERNS;

    private List<ItemStack> ingredients;
    private int maxUses;

    static {
        BANNER_PATTERNS = new ArrayList<>();
        BANNER_PATTERNS.add(new ItemStack(Material.CREEPER_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.FLOW_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.FLOWER_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.GLOBE_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.GUSTER_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.PIGLIN_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.MOJANG_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.SKULL_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.FIELD_MASONED_BANNER_PATTERN));
        BANNER_PATTERNS.add(new ItemStack(Material.BORDURE_INDENTED_BANNER_PATTERN));
    }

    public BannerPatternTradeEntry(ItemStack ingredient, int tradeUses) {
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        this.maxUses = tradeUses;
    }

    public BannerPatternTradeEntry(List<ItemStack> ingredients, int tradeUses) {
        this.ingredients = ingredients;
        this.maxUses = tradeUses;
    }

    @Override
    public MerchantRecipe get() {
        MerchantRecipe tradeRecipe = new MerchantRecipe(BANNER_PATTERNS.get(RNG.nextInt(0, BANNER_PATTERNS.size())), maxUses);
        for(int i = 0; i < ingredients.size(); i++)
            tradeRecipe.addIngredient(ingredients.get(i));

        return tradeRecipe;
    }
}
