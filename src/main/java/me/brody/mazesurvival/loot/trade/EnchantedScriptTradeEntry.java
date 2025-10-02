package me.brody.mazesurvival.loot.trade;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class EnchantedScriptTradeEntry implements Supplier<MerchantRecipe> {
    private static final Random RNG = new Random();

    private List<ItemStack> ingredients;
    private Map<MazeEnchantment, Integer> enchantments;
    private boolean isDoubleEnchant;
    private int maxUses;

    public EnchantedScriptTradeEntry(ItemStack ingredient, Map<MazeEnchantment, Integer> enchantments, boolean isDoubleEnchant, int maxUses) {
        ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        this.enchantments = enchantments;
        this.isDoubleEnchant = isDoubleEnchant;
        this.maxUses = maxUses;
    }

    public EnchantedScriptTradeEntry(List<ItemStack> ingredients, Map<MazeEnchantment, Integer> enchantments, boolean isDoubleEnchant, int maxUses) {
        this.ingredients = ingredients;
        this.enchantments = enchantments;
        this.isDoubleEnchant = isDoubleEnchant;
        this.maxUses = maxUses;
    }

    @Override
    public MerchantRecipe get() {
        ItemStack itemStack = isDoubleEnchant ? CustomItem.SCRIPTING_TOME.getItemStack() : CustomItem.SCRIPTING_PAPER.getItemStack();
        int enchantCount = isDoubleEnchant ? 2 : 1;
        List<Map.Entry<MazeEnchantment, Integer>> enchantmentList = new ArrayList<>(enchantments.entrySet());
        for(int i = 0; i < enchantCount; i++) {
            Map.Entry<MazeEnchantment, Integer> enchantment = enchantmentList.get(RNG.nextInt(0, enchantmentList.size()));
            enchantment.getKey().enchantScript(itemStack, enchantment.getValue());
        }

        MerchantRecipe tradeRecipe = new MerchantRecipe(itemStack, maxUses);
        for(int i = 0; i < ingredients.size(); i++)
            tradeRecipe.addIngredient(ingredients.get(i));

        return tradeRecipe;
    }
}
