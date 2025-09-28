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
    private static final List<Material> REMOVED_RECIPE_MATERIALS;
    private static final ItemStack INVALID_RECIPE_ITEM;

    private static List<CustomRecipe> recipes;

    static {
        REMOVED_RECIPE_MATERIALS = new ArrayList<>();
        REMOVED_RECIPE_MATERIALS.add(Material.OAK_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.BIRCH_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.SPRUCE_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.DARK_OAK_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.JUNGLE_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.ACACIA_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.CHERRY_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.PALE_OAK_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.MANGROVE_WOOD);
        REMOVED_RECIPE_MATERIALS.add(Material.CRIMSON_HYPHAE);
        REMOVED_RECIPE_MATERIALS.add(Material.WARPED_HYPHAE);
        REMOVED_RECIPE_MATERIALS.add(Material.WHITE_WOOL);
        REMOVED_RECIPE_MATERIALS.add(Material.GLOWSTONE);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_NUGGET);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLD_NUGGET);
        REMOVED_RECIPE_MATERIALS.add(Material.LEATHER_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.LEATHER_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.LEATHER_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.LEATHER_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.CHAINMAIL_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.CHAINMAIL_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.CHAINMAIL_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.CHAINMAIL_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_HELMET);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_CHESTPLATE);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_LEGGINGS);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_BOOTS);
        REMOVED_RECIPE_MATERIALS.add(Material.WOODEN_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.WOODEN_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.WOODEN_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.WOODEN_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.WOODEN_PICKAXE);
        REMOVED_RECIPE_MATERIALS.add(Material.STONE_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.STONE_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.STONE_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.STONE_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.STONE_PICKAXE);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.GOLDEN_PICKAXE);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.IRON_PICKAXE);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.DIAMOND_PICKAXE);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_SWORD);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_AXE);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_SHOVEL);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_HOE);
        REMOVED_RECIPE_MATERIALS.add(Material.NETHERITE_PICKAXE);

        INVALID_RECIPE_ITEM = new ItemStack(Material.BARRIER);
        ItemMeta meta = INVALID_RECIPE_ITEM.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Invalid Recipe");
        INVALID_RECIPE_ITEM.setItemMeta(meta);
    }

    private CustomRecipes() {}

    public static void register() {
        // Removing unwanted vanilla recipes
        Iterator<Recipe> recipeIterator = Bukkit.recipeIterator();
        while(recipeIterator.hasNext()) {
            Recipe recipe = recipeIterator.next();
            if(recipe != null && REMOVED_RECIPE_MATERIALS.contains(recipe.getResult().getType()))
                recipeIterator.remove();
        }

        // Custom recipes
        recipes = new ArrayList<>();
        registerTwoByTwoGradedRecipes(new ItemStack(Material.LEATHER));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.COBBLESTONE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.DEEPSLATE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_SAND));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SOUL_SAND));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.FEATHER));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.CACTUS));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BROWN_MUSHROOM));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_MUSHROOM));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.WHEAT));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.CARROT));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.POTATO));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BEETROOT));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.STRING));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SPIDER_EYE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.HONEYCOMB));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.INK_SAC));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SLIME_BALL));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BLAZE_ROD));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BREEZE_ROD));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.ECHO_SHARD));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.NETHER_WART));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GLOWSTONE_DUST));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SUGAR));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GUNPOWDER));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.ROTTEN_FLESH));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GHAST_TEAR));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BLUE_DYE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_DYE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.YELLOW_DYE));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.PURPLE_DYE));
        registerTwoByTwoGradedRecipes(CustomItem.TIN.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.BRONZE_INGOT.getItemStack());
        registerTwoByTwoGradedRecipes(new ItemStack(Material.IRON_INGOT));
        registerTwoByTwoGradedRecipes(CustomItem.SUN_GOLD_NUGGET.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.SUN_GOLD_INGOT.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.ORICHALCUM.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.CORRUPTED_DIAMOND.getItemStack());
        registerTwoByTwoGradedRecipes(new ItemStack(Material.AMETHYST_SHARD));
        registerTwoByTwoGradedRecipes(new ItemStack(Material.LAPIS_LAZULI));
        registerTwoByTwoGradedRecipes(CustomItem.MISTSTEEL_INGOT.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.MITHRIL.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.SOLARIUM.getItemStack());
        registerTwoByTwoGradedRecipes(CustomItem.ADAMANTITE_INGOT.getItemStack());

        // Armor
        ItemStack leatherGoldGrade = new ItemStack(Material.LEATHER);
        ItemGradeUtils.setItemGrade(leatherGoldGrade, ItemGrade.GOLD);
        registerSwordRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_HELMET.getItemStack());
        registerAxeRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_CHESTPLATE.getItemStack());
        registerShovelRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_LEGGIGNS.getItemStack());
        registerHoeRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_BOOTS.getItemStack());

        ItemStack bronzeIngotGoldGrade = CustomItem.BRONZE_INGOT.getItemStack();
        ItemGradeUtils.setItemGrade(bronzeIngotGoldGrade, ItemGrade.GOLD);
        registerSwordRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_HELMET.getItemStack());
        registerAxeRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_CHESTPLATE.getItemStack());
        registerShovelRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_LEGGINGS.getItemStack());
        registerHoeRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_BOOTS.getItemStack());

        // TODO: Add remaining armor recipes


        // Tools
        ItemStack logTitaniumGrade = CustomItem.LOG.getItemStack();
        ItemGradeUtils.setItemGrade(logTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(logTitaniumGrade, CustomItem.WOODEN_SHORT_SWORD.getItemStack());
        registerAxeRecipe(logTitaniumGrade, CustomItem.WOODEN_HATCHET.getItemStack());
        registerShovelRecipe(logTitaniumGrade, CustomItem.WOODEN_SPADE.getItemStack());
        registerHoeRecipe(logTitaniumGrade, CustomItem.WOODEN_SCYTHE.getItemStack());
        registerPickaxeRecipe(logTitaniumGrade, CustomItem.WORN_WOODEN_PICKAXE.getItemStack());

        ItemStack cobblestoneTitaniumGrade = new ItemStack(Material.COBBLESTONE);
        ItemGradeUtils.setItemGrade(cobblestoneTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(cobblestoneTitaniumGrade, CustomItem.BASTARD_SWORD.getItemStack());
        registerAxeRecipe(cobblestoneTitaniumGrade, CustomItem.BATTLE_AXE.getItemStack());
        registerShovelRecipe(cobblestoneTitaniumGrade, CustomItem.MACE.getItemStack());
        registerPickaxeRecipe(cobblestoneTitaniumGrade, CustomItem.COBBLESTONE_PICKAXE.getItemStack());

        ItemStack bronzeIngotTitaniumGrade = CustomItem.BRONZE_INGOT.getItemStack();
        ItemGradeUtils.setItemGrade(bronzeIngotTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_SWORD.getItemStack());
        registerAxeRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_AXE.getItemStack());
        registerShovelRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_MACE.getItemStack());
        registerPickaxeRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_PICKAXE.getItemStack());

        ItemStack miststeelIngotTitaniumGrade = CustomItem.MISTSTEEL_INGOT.getItemStack();
        ItemGradeUtils.setItemGrade(miststeelIngotTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_SWORD.getItemStack());
        registerAxeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_AXE.getItemStack());
        registerShovelRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_MACE.getItemStack());
        registerHoeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_SCYTHE.getItemStack());
        registerPickaxeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_PICKAXE.getItemStack());

        ItemStack sunGoldIngotTitaniumGrade = CustomItem.SUN_GOLD_INGOT.getItemStack();
        ItemGradeUtils.setItemGrade(sunGoldIngotTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_SWORD.getItemStack());
        registerAxeRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_AXE.getItemStack());
        registerShovelRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_MACE.getItemStack());
        registerPickaxeRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_PICKAXE.getItemStack());

        ItemStack orichalcumTitaniumGrade = CustomItem.ORICHALCUM.getItemStack();
        ItemGradeUtils.setItemGrade(orichalcumTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(orichalcumTitaniumGrade, CustomItem.ORICHALCUM_SWORD.getItemStack());
        registerAxeRecipe(orichalcumTitaniumGrade, CustomItem.ORICHALCUM_AXE.getItemStack());
        registerShovelRecipe(orichalcumTitaniumGrade, CustomItem.ORICHALCUM_MACE.getItemStack());
        registerPickaxeRecipe(orichalcumTitaniumGrade, CustomItem.ORICHALCUM_PICKAXE.getItemStack());

        ItemStack corruptedDiamondTitaniumGrade = CustomItem.CORRUPTED_DIAMOND.getItemStack();
        ItemGradeUtils.setItemGrade(corruptedDiamondTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_SWORD.getItemStack());
        registerAxeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_AXE.getItemStack());
        registerShovelRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_MACE.getItemStack());
        registerHoeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_SCYTHE.getItemStack());
        registerPickaxeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_PICKAXE.getItemStack());

        ItemStack adamantiteIngotTitaniumGrade = CustomItem.ADAMANTITE_INGOT.getItemStack();
        ItemGradeUtils.setItemGrade(adamantiteIngotTitaniumGrade, ItemGrade.TITANIUM);
        registerSwordRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_EDGE.getItemStack());
        registerAxeRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_CLEAVER.getItemStack());
        registerShovelRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_GRAVEDIGGER.getItemStack());
        registerHoeRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_REAPER.getItemStack());

        List<ItemStack> bronzeIngotIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.COPPER_INGOT),
                CustomItem.TIN.getItemStack()));
        registerShapelessRecipe(bronzeIngotIngredients, CustomItem.BRONZE_INGOT.getItemStack());

        List<ItemStack> miststeelIngotIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.LAPIS_LAZULI)));
        registerShapelessRecipe(miststeelIngotIngredients, CustomItem.MISTSTEEL_INGOT.getItemStack());

        List<ItemStack> sunGoldIngotIngredients = new ArrayList<>(List.of(
                CustomItem.SUN_GOLD_NUGGET.getItemStack(),
                CustomItem.BRONZE_INGOT.getItemStack()));
        registerShapelessRecipe(sunGoldIngotIngredients, CustomItem.SUN_GOLD_INGOT.getItemStack());

        List<ItemStack> mithrilIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.QUARTZ),
                new ItemStack(Material.LAPIS_LAZULI)));
        registerShapelessRecipe(mithrilIngredients, CustomItem.MITHRIL.getItemStack());

        List<ItemStack> orichalcumIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.EMERALD),
                CustomItem.BRONZE_INGOT.getItemStack()));
        registerShapelessRecipe(orichalcumIngredients, CustomItem.ORICHALCUM.getItemStack());

        ItemStack echoShardGoldGrade = new ItemStack(Material.ECHO_SHARD);
        ItemGradeUtils.setItemGrade(echoShardGoldGrade, ItemGrade.GOLD);
        List<ItemStack> corruptedDiamondIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.DIAMOND),
                echoShardGoldGrade));
        registerShapelessRecipe(corruptedDiamondIngredients, CustomItem.CORRUPTED_DIAMOND.getItemStack());

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

    private static void registerTwoByTwoGradedRecipes(ItemStack item) {
        ItemStack itemIronGrade = item.clone();
        ItemGradeUtils.setItemGrade(itemIronGrade, ItemGrade.IRON);
        ItemStack itemGoldGrade = item.clone();
        ItemGradeUtils.setItemGrade(itemGoldGrade, ItemGrade.GOLD);
        ItemStack itemTitaniumGrade = item.clone();
        ItemGradeUtils.setItemGrade(itemTitaniumGrade, ItemGrade.TITANIUM);

        CustomShapedRecipe ironGradeRecipe = new CustomShapedRecipe("LL", "LL", itemIronGrade);
        ironGradeRecipe.addIngredient('L', item);
        recipes.add(ironGradeRecipe);
        CustomShapedRecipe goldGradeRecipe = new CustomShapedRecipe("LL", "LL", itemGoldGrade);
        goldGradeRecipe.addIngredient('L', itemIronGrade);
        recipes.add(goldGradeRecipe);
        CustomShapedRecipe titaniumGradeRecipe = new CustomShapedRecipe("LL", "LL", itemTitaniumGrade);
        titaniumGradeRecipe.addIngredient('L', itemGoldGrade);
        recipes.add(titaniumGradeRecipe);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, item.getType().name().toLowerCase().replace("_", "-") + "-custom-recipe"), INVALID_RECIPE_ITEM);
        recipe.shape("LL", "LL");
        recipe.setIngredient('L', item.getType());
        Bukkit.addRecipe(recipe);
    }

    private static void registerHelmetRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customHelmetRecipe = new CustomShapedRecipe("LLL", "L L", result);
        customHelmetRecipe.addIngredient('L', mineral);
        recipes.add(customHelmetRecipe);

        ShapedRecipe helmetRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-helmet-custom-recipe"), INVALID_RECIPE_ITEM);
        helmetRecipe.shape("LLL", "L L");
        helmetRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(helmetRecipe);
    }

    private static void registerChestplateRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customChestplateRecipe = new CustomShapedRecipe("L L", "LLL", "LLL", result);
        customChestplateRecipe.addIngredient('L', mineral);
        recipes.add(customChestplateRecipe);

        ShapedRecipe chestplateRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-chestplate-custom-recipe"), INVALID_RECIPE_ITEM);
        chestplateRecipe.shape("L L", "LLL", "LLL");
        chestplateRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(chestplateRecipe);
    }

    private static void registerLeggingsRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customLeggingsRecipe = new CustomShapedRecipe("LLL", "L L", "L L", result);
        customLeggingsRecipe.addIngredient('L', mineral);
        recipes.add(customLeggingsRecipe);

        ShapedRecipe leggingsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-leggings-custom-recipe"), INVALID_RECIPE_ITEM);
        leggingsRecipe.shape("LLL", "L L", "L L");
        leggingsRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(leggingsRecipe);
    }

    private static void registerBootsRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customBootsRecipe = new CustomShapedRecipe("L L", "L L", result);
        customBootsRecipe.addIngredient('L', mineral);
        recipes.add(customBootsRecipe);

        ShapedRecipe bootsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-boots-custom-recipe"), INVALID_RECIPE_ITEM);
        bootsRecipe.shape("L L", "L L");
        bootsRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(bootsRecipe);
    }

    private static void registerSwordRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customSwordRecipe = new CustomShapedRecipe("L", "L", "S", result);
        customSwordRecipe.addIngredient('L', mineral);
        customSwordRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customSwordRecipe);

        ShapedRecipe swordRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-sword-custom-recipe"), INVALID_RECIPE_ITEM);
        swordRecipe.shape("L", "L", "S");
        swordRecipe.setIngredient('L', mineral.getType());
        swordRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(swordRecipe);
    }

    private static void registerAxeRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customAxeRecipe = new CustomShapedRecipe("LL", "LS", "S", result);
        customAxeRecipe.addIngredient('L', mineral);
        customAxeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customAxeRecipe);

        ShapedRecipe axeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-axe-custom-recipe"), INVALID_RECIPE_ITEM);
        axeRecipe.shape("LL", "LS", "S");
        axeRecipe.setIngredient('L', mineral.getType());
        axeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(axeRecipe);
    }

    private static void registerShovelRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customShovelRecipe = new CustomShapedRecipe("L", "S", "S", result);
        customShovelRecipe.addIngredient('L', mineral);
        customShovelRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customShovelRecipe);

        ShapedRecipe shovelRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-shovel-custom-recipe"), INVALID_RECIPE_ITEM);
        shovelRecipe.shape("L", "S", "S");
        shovelRecipe.setIngredient('L', mineral.getType());
        shovelRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(shovelRecipe);
    }

    private static void registerHoeRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customHoeRecipe = new CustomShapedRecipe("LL", "S", "S", result);
        customHoeRecipe.addIngredient('L', mineral);
        customHoeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customHoeRecipe);

        ShapedRecipe hoeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-hoe-custom-recipe"), INVALID_RECIPE_ITEM);
        hoeRecipe.shape("LL", "S", "S");
        hoeRecipe.setIngredient('L', mineral.getType());
        hoeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hoeRecipe);
    }

    private static void registerPickaxeRecipe(ItemStack mineral, ItemStack result) {
        CustomShapedRecipe customPickaxeRecipe = new CustomShapedRecipe("LLL", "S", "S", result);
        customPickaxeRecipe.addIngredient('L', mineral);
        customPickaxeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customPickaxeRecipe);

        ShapedRecipe pickaxeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, mineral.getType().name().toLowerCase().replace("_", "-") + "-pickaxe-custom-recipe"), INVALID_RECIPE_ITEM);
        pickaxeRecipe.shape("LLL", "S", "S");
        pickaxeRecipe.setIngredient('L', mineral.getType());
        pickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(pickaxeRecipe);
    }

    private static void registerShapelessRecipe(List<ItemStack> ingredients, ItemStack result) {
        recipes.add(new CustomShapelessRecipe(ingredients, result));

        StringBuilder sb = new StringBuilder();
        for(ItemStack ingredient : ingredients)
            sb.append(ingredient.getType().name().toLowerCase().replace("_", "-") + "-");
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, sb + "custom-recipe"), INVALID_RECIPE_ITEM);
        for(ItemStack ingredient : ingredients)
            recipe.addIngredient(ingredient.getType());
        Bukkit.addRecipe(recipe);
    }
}
