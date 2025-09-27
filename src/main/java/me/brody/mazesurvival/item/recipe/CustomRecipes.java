package me.brody.mazesurvival.item.recipe;

import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomRecipes {
    private static final List<Material> removedRecipeMaterials;
    private static final ItemStack invalidRecipeItem;

    static {
        removedRecipeMaterials = new ArrayList<>();
        removedRecipeMaterials.add(Material.OAK_WOOD);
        removedRecipeMaterials.add(Material.BIRCH_WOOD);
        removedRecipeMaterials.add(Material.SPRUCE_WOOD);
        removedRecipeMaterials.add(Material.DARK_OAK_WOOD);
        removedRecipeMaterials.add(Material.JUNGLE_WOOD);
        removedRecipeMaterials.add(Material.ACACIA_WOOD);
        removedRecipeMaterials.add(Material.CHERRY_WOOD);
        removedRecipeMaterials.add(Material.PALE_OAK_WOOD);
        removedRecipeMaterials.add(Material.MANGROVE_WOOD);
        removedRecipeMaterials.add(Material.CRIMSON_HYPHAE);
        removedRecipeMaterials.add(Material.WARPED_HYPHAE);
        removedRecipeMaterials.add(Material.WHITE_WOOL);
        removedRecipeMaterials.add(Material.GLOWSTONE);
        removedRecipeMaterials.add(Material.IRON_NUGGET);
        removedRecipeMaterials.add(Material.GOLD_NUGGET);

        invalidRecipeItem = new ItemStack(Material.BARRIER);
        ItemMeta meta = invalidRecipeItem.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Invalid Recipe");
        invalidRecipeItem.setItemMeta(meta);
    }

    private CustomRecipes() {}

    public static void register() {
        // Removing unwanted vanilla recipes
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if(recipe != null && removedRecipeMaterials.contains(recipe.getResult().getType()))
                recipeIterator.remove();
        }

        // Custom recipes
        ItemStack logIronGrade = CustomItem.LOG.getItemStack();
        ItemGradeUtils.setItemGrade(logIronGrade, ItemGrade.IRON);
        ShapedRecipe logIronGradeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "log-iron-grade-recipe"), logIronGrade);
        logIronGradeRecipe.shape("LL", "LL");
        RecipeChoice.MaterialChoice logMaterialChoice = new RecipeChoice.MaterialChoice(
                Material.OAK_LOG,
                Material.BIRCH_LOG,
                Material.SPRUCE_LOG,
                Material.DARK_OAK_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_LOG,
                Material.CHERRY_LOG,
                Material.PALE_OAK_LOG,
                Material.MANGROVE_LOG,
                Material.CRIMSON_STEM,
                Material.WARPED_STEM);
        logIronGradeRecipe.setIngredient('L', logMaterialChoice);
        Bukkit.addRecipe(logIronGradeRecipe);

        registerTwoByTwoRecipe(Material.LEATHER);
        registerTwoByTwoRecipe(Material.COBBLESTONE);
        registerTwoByTwoRecipe(Material.DEEPSLATE);
        registerTwoByTwoRecipe(Material.RED_SAND);
        registerTwoByTwoRecipe(Material.SOUL_SAND);
        registerTwoByTwoRecipe(Material.FEATHER);
        registerTwoByTwoRecipe(Material.CACTUS);
        registerTwoByTwoRecipe(Material.BROWN_MUSHROOM);
        registerTwoByTwoRecipe(Material.RED_MUSHROOM);
        registerTwoByTwoRecipe(Material.WHEAT);
        registerTwoByTwoRecipe(Material.CARROT);
        registerTwoByTwoRecipe(Material.POTATO);
        registerTwoByTwoRecipe(Material.BEETROOT);
        registerTwoByTwoRecipe(Material.STRING);
        registerTwoByTwoRecipe(Material.SPIDER_EYE);
        registerTwoByTwoRecipe(Material.HONEYCOMB);
        registerTwoByTwoRecipe(Material.INK_SAC);
        registerTwoByTwoRecipe(Material.SLIME_BALL);
        registerTwoByTwoRecipe(Material.BLAZE_ROD);
        registerTwoByTwoRecipe(Material.BREEZE_ROD);
        registerTwoByTwoRecipe(Material.ECHO_SHARD);
        registerTwoByTwoRecipe(Material.NETHER_WART);
        registerTwoByTwoRecipe(Material.GLOWSTONE_DUST);
        registerTwoByTwoRecipe(Material.SUGAR);
        registerTwoByTwoRecipe(Material.GUNPOWDER);
        registerTwoByTwoRecipe(Material.ROTTEN_FLESH);
        registerTwoByTwoRecipe(Material.GHAST_TEAR);
        registerTwoByTwoRecipe(Material.BLUE_DYE);
        registerTwoByTwoRecipe(Material.RED_DYE);
        registerTwoByTwoRecipe(Material.YELLOW_DYE);

        registerShapelessRecipe(List.of(Material.COPPER_INGOT, Material.IRON_NUGGET));
        registerShapelessRecipe(List.of(Material.IRON_INGOT, Material.LAPIS_LAZULI));
        registerShapelessRecipe(List.of(Material.GOLD_NUGGET, Material.COPPER_INGOT));
        registerShapelessRecipe(List.of(Material.QUARTZ, Material.LAPIS_LAZULI));
        registerShapelessRecipe(List.of(Material.EMERALD, Material.COPPER_INGOT));
        registerShapelessRecipe(List.of(Material.DIAMOND, Material.ECHO_SHARD));

        ItemStack wool = new ItemStack(Material.WHITE_WOOL);
        ShapedRecipe woolRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "white-wool-recipe"), wool);
        woolRecipe.shape("LLL", "LLL", "LLL");
        woolRecipe.setIngredient('L', Material.STRING);
        Bukkit.addRecipe(woolRecipe);

        ItemStack glowstone = new ItemStack(Material.GLOWSTONE);
        ShapedRecipe glowstoneRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "glowstone-recipe"), glowstone);
        glowstoneRecipe.shape("LLL", "LLL", "LLL");
        glowstoneRecipe.setIngredient('L', Material.GLOWSTONE_DUST);
        Bukkit.addRecipe(glowstoneRecipe);
    }

    private static void registerTwoByTwoRecipe(Material material) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-custom-recipe"), invalidRecipeItem);
        recipe.shape("LL", "LL");
        recipe.setIngredient('L', material);
        Bukkit.addRecipe(recipe);
    }

    private static void registerShapelessRecipe(List<Material> materials) {
        StringBuilder sb = new StringBuilder();
        for(Material mat : materials)
            sb.append(mat.name().toLowerCase().replace("_", "-") + "-");
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, sb + "custom-recipe"), invalidRecipeItem);
        for(Material mat : materials)
            recipe.addIngredient(mat);
        Bukkit.addRecipe(recipe);
    }

}
