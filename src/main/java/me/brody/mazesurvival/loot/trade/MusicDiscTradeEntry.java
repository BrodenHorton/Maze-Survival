package me.brody.mazesurvival.loot.trade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class MusicDiscTradeEntry implements Supplier<MerchantRecipe> {
    private static final Random RNG = new Random();
    private static final List<ItemStack> MUSIC_DISCS;

    private List<ItemStack> ingredients;
    private int maxUses;

    static {
        MUSIC_DISCS = new ArrayList<>();
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_5));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_11));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_13));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_CAT));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_FAR));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_MALL));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_CHIRP));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_BLOCKS));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_CREATOR));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_STAL));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_MELLOHI));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_WAIT));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_WARD));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_CREATOR_MUSIC_BOX));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_PIGSTEP));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_OTHERSIDE));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_PRECIPICE));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_RELIC));
        MUSIC_DISCS.add(new ItemStack(Material.MUSIC_DISC_STRAD));
    }

    public MusicDiscTradeEntry(ItemStack ingredient, int tradeUses) {
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        this.maxUses = tradeUses;
    }

    public MusicDiscTradeEntry(List<ItemStack> ingredients, int tradeUses) {
        this.ingredients = ingredients;
        this.maxUses = tradeUses;
    }

    @Override
    public MerchantRecipe get() {
        MerchantRecipe tradeRecipe = new MerchantRecipe(MUSIC_DISCS.get(RNG.nextInt(0, MUSIC_DISCS.size())), maxUses);
        for(int i = 0; i < ingredients.size(); i++)
            tradeRecipe.addIngredient(ingredients.get(i));

        return tradeRecipe;
    }
}
