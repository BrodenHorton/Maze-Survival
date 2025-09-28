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

public class VanillaRecipeManager {
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
        removedRecipeMaterials.add(Material.LEATHER_HELMET);
        removedRecipeMaterials.add(Material.LEATHER_CHESTPLATE);
        removedRecipeMaterials.add(Material.LEATHER_LEGGINGS);
        removedRecipeMaterials.add(Material.LEATHER_BOOTS);
        removedRecipeMaterials.add(Material.GOLDEN_HELMET);
        removedRecipeMaterials.add(Material.GOLDEN_CHESTPLATE);
        removedRecipeMaterials.add(Material.GOLDEN_LEGGINGS);
        removedRecipeMaterials.add(Material.GOLDEN_BOOTS);
        removedRecipeMaterials.add(Material.CHAINMAIL_HELMET);
        removedRecipeMaterials.add(Material.CHAINMAIL_CHESTPLATE);
        removedRecipeMaterials.add(Material.CHAINMAIL_LEGGINGS);
        removedRecipeMaterials.add(Material.CHAINMAIL_BOOTS);
        removedRecipeMaterials.add(Material.IRON_HELMET);
        removedRecipeMaterials.add(Material.IRON_CHESTPLATE);
        removedRecipeMaterials.add(Material.IRON_LEGGINGS);
        removedRecipeMaterials.add(Material.IRON_BOOTS);
        removedRecipeMaterials.add(Material.DIAMOND_HELMET);
        removedRecipeMaterials.add(Material.DIAMOND_CHESTPLATE);
        removedRecipeMaterials.add(Material.DIAMOND_LEGGINGS);
        removedRecipeMaterials.add(Material.DIAMOND_BOOTS);
        removedRecipeMaterials.add(Material.NETHERITE_HELMET);
        removedRecipeMaterials.add(Material.NETHERITE_CHESTPLATE);
        removedRecipeMaterials.add(Material.NETHERITE_LEGGINGS);
        removedRecipeMaterials.add(Material.NETHERITE_BOOTS);
        removedRecipeMaterials.add(Material.WOODEN_SWORD);
        removedRecipeMaterials.add(Material.WOODEN_AXE);
        removedRecipeMaterials.add(Material.WOODEN_SHOVEL);
        removedRecipeMaterials.add(Material.WOODEN_HOE);
        removedRecipeMaterials.add(Material.WOODEN_PICKAXE);
        removedRecipeMaterials.add(Material.STONE_SWORD);
        removedRecipeMaterials.add(Material.STONE_AXE);
        removedRecipeMaterials.add(Material.STONE_SHOVEL);
        removedRecipeMaterials.add(Material.STONE_HOE);
        removedRecipeMaterials.add(Material.STONE_PICKAXE);
        removedRecipeMaterials.add(Material.GOLDEN_SWORD);
        removedRecipeMaterials.add(Material.GOLDEN_AXE);
        removedRecipeMaterials.add(Material.GOLDEN_SHOVEL);
        removedRecipeMaterials.add(Material.GOLDEN_HOE);
        removedRecipeMaterials.add(Material.GOLDEN_PICKAXE);
        removedRecipeMaterials.add(Material.IRON_SWORD);
        removedRecipeMaterials.add(Material.IRON_AXE);
        removedRecipeMaterials.add(Material.IRON_SHOVEL);
        removedRecipeMaterials.add(Material.IRON_HOE);
        removedRecipeMaterials.add(Material.IRON_PICKAXE);
        removedRecipeMaterials.add(Material.DIAMOND_SWORD);
        removedRecipeMaterials.add(Material.DIAMOND_AXE);
        removedRecipeMaterials.add(Material.DIAMOND_SHOVEL);
        removedRecipeMaterials.add(Material.DIAMOND_HOE);
        removedRecipeMaterials.add(Material.DIAMOND_PICKAXE);
        removedRecipeMaterials.add(Material.NETHERITE_SWORD);
        removedRecipeMaterials.add(Material.NETHERITE_AXE);
        removedRecipeMaterials.add(Material.NETHERITE_SHOVEL);
        removedRecipeMaterials.add(Material.NETHERITE_HOE);
        removedRecipeMaterials.add(Material.NETHERITE_PICKAXE);

        invalidRecipeItem = new ItemStack(Material.BARRIER);
        ItemMeta meta = invalidRecipeItem.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Invalid Recipe");
        invalidRecipeItem.setItemMeta(meta);
    }

    private VanillaRecipeManager() {}

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

        registerArmorRecipes(Material.LEATHER);
        registerArmorRecipes(Material.COPPER_INGOT);
        registerArmorRecipes(Material.IRON_INGOT);
        registerArmorRecipes(Material.GOLD_INGOT);
        registerArmorRecipes(Material.EMERALD);
        registerArmorRecipes(Material.DIAMOND);
        registerArmorRecipes(Material.AMETHYST_SHARD);
        registerArmorRecipes(Material.LAPIS_LAZULI);
        registerArmorRecipes(Material.PRISMARINE_SHARD);
        registerArmorRecipes(Material.REDSTONE);
        registerArmorRecipes(Material.NETHERITE_INGOT);

        registerToolRecipes(Material.OAK_PLANKS);
        registerToolRecipes(Material.BIRCH_PLANKS);
        registerToolRecipes(Material.SPRUCE_PLANKS);
        registerToolRecipes(Material.DARK_OAK_PLANKS);
        registerToolRecipes(Material.JUNGLE_PLANKS);
        registerToolRecipes(Material.ACACIA_PLANKS);
        registerToolRecipes(Material.CHERRY_PLANKS);
        registerToolRecipes(Material.PALE_OAK_PLANKS);
        registerToolRecipes(Material.MANGROVE_PLANKS);
        registerToolRecipes(Material.CRIMSON_PLANKS);
        registerToolRecipes(Material.WARPED_PLANKS);
        registerToolRecipes(Material.COBBLESTONE);
        registerToolRecipes(Material.COPPER_INGOT);
        registerToolRecipes(Material.IRON_INGOT);
        registerToolRecipes(Material.GOLD_INGOT);
        registerToolRecipes(Material.EMERALD);
        registerToolRecipes(Material.DIAMOND);
        registerToolRecipes(Material.NETHERITE_INGOT);

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

        registerShapelessRecipe(List.of(Material.COPPER_INGOT, Material.IRON_NUGGET));
        registerShapelessRecipe(List.of(Material.IRON_INGOT, Material.LAPIS_LAZULI));
        registerShapelessRecipe(List.of(Material.GOLD_NUGGET, Material.COPPER_INGOT));
        registerShapelessRecipe(List.of(Material.QUARTZ, Material.LAPIS_LAZULI));
        registerShapelessRecipe(List.of(Material.EMERALD, Material.COPPER_INGOT));
        registerShapelessRecipe(List.of(Material.DIAMOND, Material.ECHO_SHARD));
    }

    private static void registerTwoByTwoRecipe(Material material) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-custom-recipe"), invalidRecipeItem);
        recipe.shape("LL", "LL");
        recipe.setIngredient('L', material);
        Bukkit.addRecipe(recipe);
    }

    private static void registerArmorRecipes(Material material) {
        ShapedRecipe helmetRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-helmet-custom-recipe"), invalidRecipeItem);
        helmetRecipe.shape("LLL", "L L");
        helmetRecipe.setIngredient('L', material);
        Bukkit.addRecipe(helmetRecipe);

        ShapedRecipe chestplateRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-chestplate-custom-recipe"), invalidRecipeItem);
        chestplateRecipe.shape("L L", "LLL", "LLL");
        chestplateRecipe.setIngredient('L', material);
        Bukkit.addRecipe(chestplateRecipe);

        ShapedRecipe leggingsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-leggings-custom-recipe"), invalidRecipeItem);
        leggingsRecipe.shape("LLL", "L L", "L L");
        leggingsRecipe.setIngredient('L', material);
        Bukkit.addRecipe(leggingsRecipe);

        ShapedRecipe bootsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "-boots-custom-recipe"), invalidRecipeItem);
        bootsRecipe.shape("L L", "L L");
        bootsRecipe.setIngredient('L', material);
        Bukkit.addRecipe(bootsRecipe);
    }

    private static void registerToolRecipes(Material material) {
        ShapedRecipe swordRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "sword-custom-recipe"), invalidRecipeItem);
        swordRecipe.shape("L", "L", "S");
        swordRecipe.setIngredient('L', material);
        swordRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(swordRecipe);

        ShapedRecipe axeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "axe-custom-recipe"), invalidRecipeItem);
        axeRecipe.shape("LL", "LS", " S");
        axeRecipe.setIngredient('L', material);
        axeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(axeRecipe);

        ShapedRecipe shovelRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "shovel-custom-recipe"), invalidRecipeItem);
        shovelRecipe.shape("L", "S", "S");
        shovelRecipe.setIngredient('L', material);
        shovelRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(shovelRecipe);

        ShapedRecipe hoeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "hoe-custom-recipe"), invalidRecipeItem);
        hoeRecipe.shape("LL", " S", " S");
        hoeRecipe.setIngredient('L', material);
        hoeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hoeRecipe);

        ShapedRecipe pickaxeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, material.name().toLowerCase().replace("_", "-") + "pickaxe-custom-recipe"), invalidRecipeItem);
        pickaxeRecipe.shape("LLL", " S", " S");
        pickaxeRecipe.setIngredient('L', material);
        pickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(pickaxeRecipe);
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
