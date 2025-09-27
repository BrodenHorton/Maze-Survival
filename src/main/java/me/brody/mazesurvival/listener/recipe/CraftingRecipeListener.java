package me.brody.mazesurvival.listener.recipe;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.item.recipe.CustomShapedRecipe;
import me.brody.mazesurvival.item.recipe.CustomShapelessRecipe;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CraftingRecipeListener implements Listener {
    private static Map<ItemStack, ItemStack> twoByTwoRecipeByIngredient;
    private static List<CustomShapelessRecipe> shapelessRecipes;

    private final Main plugin;

    static {
        twoByTwoRecipeByIngredient = new HashMap<>();
        addTwoByTwoRecipeGrades(CustomItem.LOG.getItemStack());
        addTwoByTwoRecipeGrades(new ItemStack(Material.LEATHER));
        addTwoByTwoRecipeGrades(new ItemStack(Material.COBBLESTONE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.DEEPSLATE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.RED_SAND));
        addTwoByTwoRecipeGrades(new ItemStack(Material.SOUL_SAND));
        addTwoByTwoRecipeGrades(new ItemStack(Material.FEATHER));
        addTwoByTwoRecipeGrades(new ItemStack(Material.CACTUS));
        addTwoByTwoRecipeGrades(new ItemStack(Material.BROWN_MUSHROOM));
        addTwoByTwoRecipeGrades(new ItemStack(Material.RED_MUSHROOM));
        addTwoByTwoRecipeGrades(new ItemStack(Material.WHEAT));
        addTwoByTwoRecipeGrades(new ItemStack(Material.CARROT));
        addTwoByTwoRecipeGrades(new ItemStack(Material.POTATO));
        addTwoByTwoRecipeGrades(new ItemStack(Material.BEETROOT));
        addTwoByTwoRecipeGrades(new ItemStack(Material.STRING));
        addTwoByTwoRecipeGrades(new ItemStack(Material.SPIDER_EYE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.HONEYCOMB));
        addTwoByTwoRecipeGrades(new ItemStack(Material.INK_SAC));
        addTwoByTwoRecipeGrades(new ItemStack(Material.SLIME_BALL));
        addTwoByTwoRecipeGrades(new ItemStack(Material.BLAZE_ROD));
        addTwoByTwoRecipeGrades(new ItemStack(Material.BREEZE_ROD));
        addTwoByTwoRecipeGrades(new ItemStack(Material.ECHO_SHARD));
        addTwoByTwoRecipeGrades(new ItemStack(Material.NETHER_WART));
        addTwoByTwoRecipeGrades(new ItemStack(Material.GLOWSTONE_DUST));
        addTwoByTwoRecipeGrades(new ItemStack(Material.SUGAR));
        addTwoByTwoRecipeGrades(new ItemStack(Material.GUNPOWDER));
        addTwoByTwoRecipeGrades(new ItemStack(Material.ROTTEN_FLESH));
        addTwoByTwoRecipeGrades(new ItemStack(Material.GHAST_TEAR));
        addTwoByTwoRecipeGrades(new ItemStack(Material.BLUE_DYE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.RED_DYE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.YELLOW_DYE));
        addTwoByTwoRecipeGrades(new ItemStack(Material.PURPLE_DYE));
        addTwoByTwoRecipeGrades(CustomItem.TIN.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.BRONZE_INGOT.getItemStack());
        addTwoByTwoRecipeGrades(new ItemStack(Material.IRON_INGOT));
        addTwoByTwoRecipeGrades(CustomItem.SUN_GOLD_NUGGET.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.SUN_GOLD_INGOT.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.ORICHALCUM.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.CORRUPTED_DIAMOND.getItemStack());
        addTwoByTwoRecipeGrades(new ItemStack(Material.AMETHYST_SHARD));
        addTwoByTwoRecipeGrades(new ItemStack(Material.LAPIS_LAZULI));
        addTwoByTwoRecipeGrades(CustomItem.MISTSTEEL_INGOT.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.MITHRIL.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.SOLARIUM.getItemStack());
        addTwoByTwoRecipeGrades(CustomItem.ADAMANTITE_INGOT.getItemStack());

        shapelessRecipes = new ArrayList<>();
        List<ItemStack> bronzeIngotIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.COPPER_INGOT),
                CustomItem.TIN.getItemStack()));
        shapelessRecipes.add(new CustomShapelessRecipe(bronzeIngotIngredients, CustomItem.BRONZE_INGOT.getItemStack()));

        List<ItemStack> miststeelIngotIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.LAPIS_LAZULI)));
        shapelessRecipes.add(new CustomShapelessRecipe(miststeelIngotIngredients, CustomItem.MISTSTEEL_INGOT.getItemStack()));

        List<ItemStack> sunGoldIngotIngredients = new ArrayList<>(List.of(
                CustomItem.SUN_GOLD_NUGGET.getItemStack(),
                CustomItem.BRONZE_INGOT.getItemStack()));
        shapelessRecipes.add(new CustomShapelessRecipe(sunGoldIngotIngredients, CustomItem.SUN_GOLD_INGOT.getItemStack()));

        List<ItemStack> mithrilIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.QUARTZ),
                new ItemStack(Material.LAPIS_LAZULI)));
        shapelessRecipes.add(new CustomShapelessRecipe(mithrilIngredients, CustomItem.MITHRIL.getItemStack()));

        List<ItemStack> orichalcumIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.EMERALD),
                CustomItem.BRONZE_INGOT.getItemStack()));
        shapelessRecipes.add(new CustomShapelessRecipe(orichalcumIngredients, CustomItem.ORICHALCUM.getItemStack()));

        ItemStack echoShardGoldGrade = new ItemStack(Material.ECHO_SHARD);
        ItemGradeUtils.setItemGrade(echoShardGoldGrade, ItemGrade.GOLD);
        List<ItemStack> corruptedDiamondIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.DIAMOND),
                echoShardGoldGrade));
        shapelessRecipes.add(new CustomShapelessRecipe(corruptedDiamondIngredients, CustomItem.CORRUPTED_DIAMOND.getItemStack()));
    }

    public CraftingRecipeListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
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
    }

    @EventHandler
    public void cancelInvalidCraftingItem(CraftItemEvent e) {
        CraftingInventory craftingInventory = e.getInventory();
        if(craftingInventory.getResult().getType() == Material.BARRIER) {
            if(e.getWhoClicked() instanceof Player player)
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 0.5f, 1f);
            e.setCancelled(true);
        }
    }

    private static void addTwoByTwoRecipeGrades(ItemStack ingredient) {
        ItemStack ingredientIronGrade = new ItemStack(ingredient);
        ItemGradeUtils.setItemGrade(ingredientIronGrade, ItemGrade.IRON);
        ItemStack ingredientGoldGrade = new ItemStack(ingredient);
        ItemGradeUtils.setItemGrade(ingredientGoldGrade, ItemGrade.GOLD);
        ItemStack ingredientTitaniumGrade = new ItemStack(ingredient);
        ItemGradeUtils.setItemGrade(ingredientTitaniumGrade, ItemGrade.TITANIUM);
        twoByTwoRecipeByIngredient.put(ingredient, ingredientIronGrade);
        twoByTwoRecipeByIngredient.put(ingredientIronGrade, ingredientGoldGrade);
        twoByTwoRecipeByIngredient.put(ingredientGoldGrade, ingredientTitaniumGrade);
    }

    private boolean containsTwoByTwoOf(ItemStack[] matrix, ItemStack item) {
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
    }

}
