package me.brody.mazesurvival.item.recipe;

import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.registry.Registry;
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
import java.util.UUID;

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
        REMOVED_RECIPE_MATERIALS.add(Material.FURNACE);
        REMOVED_RECIPE_MATERIALS.add(Material.BLAST_FURNACE);
        REMOVED_RECIPE_MATERIALS.add(Material.SMOKER);
        REMOVED_RECIPE_MATERIALS.add(Material.LOOM);
        REMOVED_RECIPE_MATERIALS.add(Material.COMPOSTER);
        REMOVED_RECIPE_MATERIALS.add(Material.ENCHANTING_TABLE);
        REMOVED_RECIPE_MATERIALS.add(Material.BREWING_STAND);
        REMOVED_RECIPE_MATERIALS.add(Material.ANVIL);
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
        REMOVED_RECIPE_MATERIALS.add(Material.BOW);

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

        recipes = new ArrayList<>();
        registerTwoByTwoRecipe(new ItemStack(Material.OAK_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "oak_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.BIRCH_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "birch_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.SPRUCE_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "spruce_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.DARK_OAK_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "dark_oak_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.JUNGLE_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "jungle_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.ACACIA_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "acacia_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.CHERRY_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "cherry_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.PALE_OAK_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "pale_oak_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.MANGROVE_LOG), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "mangrove_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.CRIMSON_STEM), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "crimson_stem_to_log_iron_grade");
        registerTwoByTwoRecipe(new ItemStack(Material.WARPED_STEM), ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON), "warped_stem_to_log_iron_grade");

        registerThreeByThreeRecipe(new ItemStack(Material.STRING), new ItemStack(Material.WHITE_WOOL), "wool");
        registerThreeByThreeRecipe(new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.GLOWSTONE), "glowstone");

        registerTwoByTwoGradedRecipes(CustomItem.LOG.getItemStack(), "custom_log");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.LEATHER), "leather");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.COBBLESTONE), "cobblestone");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.DEEPSLATE), "deepslate");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.APPLE), "apple");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.WHEAT), "wheat");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.CARROT), "carrot");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.POTATO), "potato");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.CACTUS), "cactus");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SUGAR_CANE), "sugar_cane");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BEETROOT), "beetroot");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BROWN_MUSHROOM), "brown_mushroom");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_MUSHROOM), "red_mushroom");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.PORKCHOP), "porkchop");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_SAND), "red_sand");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SOUL_SAND), "soul_sand");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.FEATHER), "feather");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.STRING), "string");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SPIDER_EYE), "spider_eye");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.HONEYCOMB), "honeycomb");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GLOW_INK_SAC), "glow_ink_sac");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SLIME_BALL), "slime_ball");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BLAZE_ROD), "blaze_rod");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BREEZE_ROD), "breeze_rod");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.ECHO_SHARD), "echo_shard");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.NETHER_WART), "nether_wart");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GLOWSTONE_DUST), "glowstone_dust");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.SUGAR), "sugar");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GUNPOWDER), "gunpowder");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.ROTTEN_FLESH), "rotten_flesh");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.GHAST_TEAR), "ghast_tear");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.BLUE_DYE), "blue_dye");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.RED_DYE), "red_dye");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.YELLOW_DYE), "yellow_dye");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.PURPLE_DYE), "purple_dye");
        registerTwoByTwoGradedRecipes(CustomItem.BRONZE_INGOT.getItemStack(), "bronze_ingot");
        registerTwoByTwoGradedRecipes(CustomItem.SUN_GOLD_NUGGET.getItemStack(), "sun_gold_nugget");
        registerTwoByTwoGradedRecipes(CustomItem.SUN_GOLD_INGOT.getItemStack(), "sun_gold_ingot");
        registerTwoByTwoGradedRecipes(CustomItem.ORICHALCUM.getItemStack(), "orichalcum");
        registerTwoByTwoGradedRecipes(CustomItem.CORRUPTED_DIAMOND.getItemStack(), "corrupted_diamond");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.AMETHYST_SHARD), "amethyst_shard");
        registerTwoByTwoGradedRecipes(new ItemStack(Material.LAPIS_LAZULI), "lapis");
        registerTwoByTwoGradedRecipes(CustomItem.MISTSTEEL_INGOT.getItemStack(), "miststeel_ingot");
        registerTwoByTwoGradedRecipes(CustomItem.MITHRIL.getItemStack(), "mithril");
        registerTwoByTwoGradedRecipes(CustomItem.SOLARIUM.getItemStack(), "solarium");
        registerTwoByTwoGradedRecipes(CustomItem.ADAMANTITE_INGOT.getItemStack(), "adamantite_ingot");

        // Armor
        ItemStack leatherGoldGrade = ItemGradeUtils.createGradedItem(new ItemStack(Material.LEATHER), ItemGrade.IRON);
        registerHelmetRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_HELMET.getItemStack(), "hard_leather_helmet");
        registerChestplateRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_CHESTPLATE.getItemStack(), "hard_leather_chestplate");
        registerLeggingsRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_LEGGIGNS.getItemStack(), "hard_leather_leggings");
        registerBootsRecipe(leatherGoldGrade, CustomItem.HARD_LEATHER_BOOTS.getItemStack(), "hard_leather_boots");

        ItemStack bronzeIngotGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_HELMET.getItemStack(), "bronze_helmet");
        registerChestplateRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_CHESTPLATE.getItemStack(), "bronze_chestplate");
        registerLeggingsRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_LEGGINGS.getItemStack(), "bronze_leggings");
        registerBootsRecipe(bronzeIngotGoldGrade, CustomItem.BRONZE_BOOTS.getItemStack(), "bronze_boots");

        ItemStack miststeelIngotGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(miststeelIngotGoldGrade, CustomItem.MISTSTEEL_HELMET.getItemStack(), "miststeel_helmet");
        registerChestplateRecipe(miststeelIngotGoldGrade, CustomItem.MISTSTEEL_CHESTPLATE.getItemStack(), "miststeel_chestplate");
        registerLeggingsRecipe(miststeelIngotGoldGrade, CustomItem.MISTSTEEL_LEGGINGS.getItemStack(), "miststeel_leggings");
        registerBootsRecipe(miststeelIngotGoldGrade, CustomItem.MISTSTEEL_BOOTS.getItemStack(), "miststeel_boots");

        ItemStack sunGoldIngotGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_INGOT.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(sunGoldIngotGoldGrade, CustomItem.SUN_GOLD_HELMET.getItemStack(), "sun_gold_helmet");
        registerChestplateRecipe(sunGoldIngotGoldGrade, CustomItem.SUN_GOLD_CHESTPLATE.getItemStack(), "sun_gold_chestplate");
        registerLeggingsRecipe(sunGoldIngotGoldGrade, CustomItem.SUN_GOLD_LEGGINGS.getItemStack(), "sun_gold_leggings");
        registerBootsRecipe(sunGoldIngotGoldGrade, CustomItem.SUN_GOLD_BOOTS.getItemStack(), "sun_gold_boots");

        ItemStack corruptedDiamondGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.CORRUPTED_DIAMOND.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(corruptedDiamondGoldGrade, CustomItem.CORRUPTED_DIAMOND_HELMET.getItemStack(), "corrupted_diamond_helmet");
        registerChestplateRecipe(corruptedDiamondGoldGrade, CustomItem.CORRUPTED_DIAMOND_CHESTPLATE.getItemStack(), "corrupted_diamond_chestplate");
        registerLeggingsRecipe(corruptedDiamondGoldGrade, CustomItem.CORRUPTED_DIAMOND_LEGGINGS.getItemStack(), "corrupted_diamond_leggings");
        registerBootsRecipe(corruptedDiamondGoldGrade, CustomItem.CORRUPTED_DIAMOND_BOOTS.getItemStack(), "corrupted_diamond_boots");

        ItemStack amethystGoldGrade = ItemGradeUtils.createGradedItem(new ItemStack(Material.AMETHYST_SHARD), ItemGrade.IRON);
        registerHelmetRecipe(amethystGoldGrade, CustomItem.AMETHYST_HELMET.getItemStack(), "amethyst_helmet");
        registerChestplateRecipe(amethystGoldGrade, CustomItem.AMETHYST_CHESTPLATE.getItemStack(), "amethyst_chestplate");
        registerLeggingsRecipe(amethystGoldGrade, CustomItem.AMETHYST_LEGGINGS.getItemStack(), "amethyst_leggings");
        registerBootsRecipe(amethystGoldGrade, CustomItem.AMETHYST_BOOTS.getItemStack(), "amethyst_boots");

        ItemStack lapisGoldGrade = ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.IRON);
        registerHelmetRecipe(lapisGoldGrade, CustomItem.LAPIS_HELMET.getItemStack(), "lapis_helmet");
        registerChestplateRecipe(lapisGoldGrade, CustomItem.LAPIS_CHESTPLATE.getItemStack(), "lapis_chestplate");
        registerLeggingsRecipe(lapisGoldGrade, CustomItem.LAPIS_LEGGINGS.getItemStack(), "lapis_leggings");
        registerBootsRecipe(lapisGoldGrade, CustomItem.LAPIS_BOOTS.getItemStack(), "lapis_boots");

        ItemStack orichalcumGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.ORICHALCUM.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(orichalcumGoldGrade, CustomItem.ORICHALCUM_HELMET.getItemStack(), "orichalcum_helmet");
        registerChestplateRecipe(orichalcumGoldGrade, CustomItem.ORICHALCUM_CHESTPLATE.getItemStack(), "orichalcum_chestplate");
        registerLeggingsRecipe(orichalcumGoldGrade, CustomItem.ORICHALCUM_LEGGINGS.getItemStack(), "orichalcum_leggings");
        registerBootsRecipe(orichalcumGoldGrade, CustomItem.ORICHALCUM_BOOTS.getItemStack(), "orichalcum_boots");

        ItemStack mithrilGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.MITHRIL.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(mithrilGoldGrade, CustomItem.MITHRIL_HELMET.getItemStack(), "mithril_helmet");
        registerChestplateRecipe(mithrilGoldGrade, CustomItem.MITHRIL_CHESTPLATE.getItemStack(), "mithril_chestplate");
        registerLeggingsRecipe(mithrilGoldGrade, CustomItem.MITHRIL_LEGGINGS.getItemStack(), "mithril_leggings");
        registerBootsRecipe(mithrilGoldGrade, CustomItem.MITHRIL_BOOTS.getItemStack(), "mithril_boots");

        ItemStack solariumGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.SOLARIUM.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(solariumGoldGrade, CustomItem.SOLARIUM_HELMET.getItemStack(), "solarium_helmet");
        registerChestplateRecipe(solariumGoldGrade, CustomItem.SOLARIUM_CHESTPLATE.getItemStack(), "solarium_chestplate");
        registerLeggingsRecipe(solariumGoldGrade, CustomItem.SOLARIUM_LEGGINGS.getItemStack(), "solarium_leggings");
        registerBootsRecipe(solariumGoldGrade, CustomItem.SOLARIUM_BOOTS.getItemStack(), "solarium_boots");

        ItemStack adamantiteGoldGrade = ItemGradeUtils.createGradedItem(CustomItem.ADAMANTITE_INGOT.getItemStack(), ItemGrade.IRON);
        registerHelmetRecipe(adamantiteGoldGrade, CustomItem.ADAMANTITE_HELMET.getItemStack(), "adamantite_helmet");
        registerChestplateRecipe(adamantiteGoldGrade, CustomItem.ADAMANTITE_CHESTPLATE.getItemStack(), "adamantite_chestplate");
        registerLeggingsRecipe(adamantiteGoldGrade, CustomItem.ADAMANTITE_LEGGINGS.getItemStack(), "adamantite_leggings");
        registerBootsRecipe(adamantiteGoldGrade, CustomItem.ADAMANTITE_BOOTS.getItemStack(), "adamantite_boots");

        // Tools
        ItemStack logIronGrade = ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.IRON);
        registerSwordRecipe(logIronGrade, CustomItem.WOODEN_SHORT_SWORD.getItemStack(), "wooden_short_sword");
        registerAxeRecipe(logIronGrade, CustomItem.WOODEN_HATCHET.getItemStack(), "wooden_hatchet");
        registerShovelRecipe(logIronGrade, CustomItem.WOODEN_SPADE.getItemStack(), "wooden_spade");
        registerHoeRecipe(logIronGrade, CustomItem.WOODEN_SCYTHE.getItemStack(), "wooden_scythe");
        registerPickaxeRecipe(logIronGrade, CustomItem.WORN_WOODEN_PICKAXE.getItemStack(), "worn_wooden_pickaxe");

        ItemStack cobblestoneTitaniumGrade = ItemGradeUtils.createGradedItem(new ItemStack(Material.COBBLESTONE), ItemGrade.GOLD);
        registerSwordRecipe(cobblestoneTitaniumGrade, CustomItem.BASTARD_SWORD.getItemStack(), "bastard_sword");
        registerAxeRecipe(cobblestoneTitaniumGrade, CustomItem.BATTLE_AXE.getItemStack(), "battle_axe");
        registerShovelRecipe(cobblestoneTitaniumGrade, CustomItem.MACE.getItemStack(), "mace");
        registerPickaxeRecipe(cobblestoneTitaniumGrade, CustomItem.COBBLESTONE_PICKAXE.getItemStack(), "cobblestone_pickaxe");

        ItemStack bronzeIngotTitaniumGrade = ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(), ItemGrade.GOLD);
        registerSwordRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_SWORD.getItemStack(), "bronze_sword");
        registerAxeRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_AXE.getItemStack(), "bronze_axe");
        registerShovelRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_MACE.getItemStack(), "bronze_mace");
        registerPickaxeRecipe(bronzeIngotTitaniumGrade, CustomItem.BRONZE_PICKAXE.getItemStack(), "bronze_pickaxe");

        ItemStack miststeelIngotTitaniumGrade = ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.GOLD);
        registerSwordRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_SWORD.getItemStack(), "miststeel_sword");
        registerAxeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_AXE.getItemStack(), "miststeel_axe");
        registerShovelRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_MACE.getItemStack(), "miststeel_mace");
        registerHoeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_SCYTHE.getItemStack(), "miststeel_scythe");
        registerPickaxeRecipe(miststeelIngotTitaniumGrade, CustomItem.MISTSTEEL_PICKAXE.getItemStack(), "miststeel_pickaxe");

        ItemStack sunGoldIngotTitaniumGrade = ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_INGOT.getItemStack(), ItemGrade.GOLD);
        registerSwordRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_SWORD.getItemStack(), "sun_gold_sword");
        registerAxeRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_AXE.getItemStack(), "sun_gold_axe");
        registerShovelRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_MACE.getItemStack(), "sun_gold_mace");
        registerPickaxeRecipe(sunGoldIngotTitaniumGrade, CustomItem.SUN_GOLD_PICKAXE.getItemStack(), "sun_gold_pickaxe");

        ItemStack corruptedDiamondTitaniumGrade = ItemGradeUtils.createGradedItem(CustomItem.CORRUPTED_DIAMOND.getItemStack(), ItemGrade.GOLD);
        registerSwordRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_SWORD.getItemStack(), "corrupted_diamond_sword");
        registerAxeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_AXE.getItemStack(), "corrupted_diamond_axe");
        registerShovelRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_MACE.getItemStack(), "corrupted_diamond_mace");
        registerHoeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_SCYTHE.getItemStack(), "corrupted_diamond_scythe");
        registerPickaxeRecipe(corruptedDiamondTitaniumGrade, CustomItem.CORRUPTED_DIAMOND_PICKAXE.getItemStack(), "corrupted_diamond_pickaxe");

        ItemStack adamantiteIngotTitaniumGrade = ItemGradeUtils.createGradedItem(CustomItem.ADAMANTITE_INGOT.getItemStack(), ItemGrade.GOLD);
        registerSwordRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_EDGE.getItemStack(), "abyssal_edge");
        registerAxeRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_CLEAVER.getItemStack(), "abyssal_cleaver");
        registerShovelRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_GRAVEDIGGER.getItemStack(), "abyssal_gravedigger");
        registerHoeRecipe(adamantiteIngotTitaniumGrade, CustomItem.ABYSSAL_REAPER.getItemStack(), "abyssal_reaper");

        ItemStack shortBow = CustomItem.SHORT_BOW.getItemStack();
        CustomShapedRecipe customShortBowRecipe = new CustomShapedRecipe(" LS", "L S", " LS", shortBow, "short_bow");
        customShortBowRecipe.addIngredient('L', ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(), ItemGrade.GOLD));
        customShortBowRecipe.addIngredient('S', ItemGradeUtils.createGradedItem(new ItemStack(Material.STRING), ItemGrade.TITANIUM));
        recipes.add(customShortBowRecipe);
        Registry.CUSTOM_RECIPE.register(customShortBowRecipe.getId(), customShortBowRecipe);
        ShapedRecipe shortBowRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        shortBowRecipe.shape(" LS", "L S", " LS");
        shortBowRecipe.setIngredient('L', Material.OAK_LOG);
        shortBowRecipe.setIngredient('S', Material.STRING);
        Bukkit.addRecipe(shortBowRecipe);

        ItemStack furnace = new ItemStack(Material.FURNACE);
        CustomShapedRecipe customFurnaceRecipe = new CustomShapedRecipe("CCC", "C C", "CCC", furnace, "furnace");
        customFurnaceRecipe.addIngredient('C', ItemGradeUtils.createGradedItem(new ItemStack(Material.COBBLESTONE), ItemGrade.IRON));
        recipes.add(customFurnaceRecipe);
        Registry.CUSTOM_RECIPE.register(customFurnaceRecipe.getId(), customFurnaceRecipe);
        ShapedRecipe furnaceRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        furnaceRecipe.shape("CCC", "C C", "CCC");
        furnaceRecipe.setIngredient('C', Material.COBBLESTONE);
        Bukkit.addRecipe(furnaceRecipe);

        ItemStack enchantingTable = new ItemStack(Material.ENCHANTING_TABLE);
        CustomShapedRecipe customEnchantingTableRecipe = new CustomShapedRecipe("LCL", "CMC", "LCL", enchantingTable, "enchanting_table");
        customEnchantingTableRecipe.addIngredient('L', ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.GOLD));
        customEnchantingTableRecipe.addIngredient('C', ItemGradeUtils.createGradedItem(new ItemStack(Material.COBBLESTONE), ItemGrade.TITANIUM));
        customEnchantingTableRecipe.addIngredient('M', ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.TITANIUM));
        recipes.add(customEnchantingTableRecipe);
        Registry.CUSTOM_RECIPE.register(customEnchantingTableRecipe.getId(), customEnchantingTableRecipe);
        ShapedRecipe enchantingTableRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        enchantingTableRecipe.shape("LCL", "CMC", "LCL");
        enchantingTableRecipe.setIngredient('L', Material.LAPIS_LAZULI);
        enchantingTableRecipe.setIngredient('C', Material.COBBLESTONE);
        enchantingTableRecipe.setIngredient('M', Material.IRON_INGOT);
        Bukkit.addRecipe(enchantingTableRecipe);

        ItemStack brewingStand = new ItemStack(Material.BREWING_STAND);
        CustomShapedRecipe customBrewingStandRecipe = new CustomShapedRecipe(" B ", "MMM", brewingStand, "brewing_stand");
        customBrewingStandRecipe.addIngredient('B', ItemGradeUtils.createGradedItem(new ItemStack(Material.BLAZE_ROD), ItemGrade.GOLD));
        customBrewingStandRecipe.addIngredient('M', ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.GOLD));
        recipes.add(customBrewingStandRecipe);
        Registry.CUSTOM_RECIPE.register(customBrewingStandRecipe.getId(), customBrewingStandRecipe);
        ShapedRecipe brewingStandRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        brewingStandRecipe.shape(" B ", "MMM");
        brewingStandRecipe.setIngredient('B', Material.BLAZE_ROD);
        brewingStandRecipe.setIngredient('M', Material.IRON_INGOT);
        Bukkit.addRecipe(brewingStandRecipe);

        List<ItemStack> bronzeIngotIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.COPPER_INGOT),
                CustomItem.TIN.getItemStack()));
        registerShapelessRecipe(bronzeIngotIngredients, CustomItem.BRONZE_INGOT.getItemStack(), "bronze_ingot");

        List<ItemStack> sunGoldIngotIngredients = new ArrayList<>(List.of(
                CustomItem.SUN_GOLD_NUGGET.getItemStack(),
                CustomItem.BRONZE_INGOT.getItemStack()));
        registerShapelessRecipe(sunGoldIngotIngredients, CustomItem.SUN_GOLD_INGOT.getItemStack(), "sun_gold_ingot");

        List<ItemStack> mithrilIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.QUARTZ),
                new ItemStack(Material.LAPIS_LAZULI)));
        registerShapelessRecipe(mithrilIngredients, CustomItem.MITHRIL.getItemStack(), "mithril");

        ItemStack echoShardGoldGrade = ItemGradeUtils.createGradedItem(new ItemStack(Material.ECHO_SHARD), ItemGrade.GOLD);
        List<ItemStack> corruptedDiamondIngredients = new ArrayList<>(List.of(
                new ItemStack(Material.DIAMOND),
                echoShardGoldGrade));
        registerShapelessRecipe(corruptedDiamondIngredients, CustomItem.CORRUPTED_DIAMOND.getItemStack(), "corrupted_diamond");

        FurnaceRecipe copperIngotFurnaceRecipe = new FurnaceRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "furnace-recipe-" + UUID.randomUUID()), new ItemStack(Material.COPPER_INGOT), Material.RAW_COPPER, 3f, 2000);
        Bukkit.addRecipe(copperIngotFurnaceRecipe);
        BlastingRecipe copperIngotBastingRecipe = new BlastingRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "blasting-recipe-" + UUID.randomUUID()), new ItemStack(Material.COPPER_INGOT), Material.RAW_COPPER, 3f, 1000);
        Bukkit.addRecipe(copperIngotBastingRecipe);

        FurnaceRecipe miststeelIngotFurnaceRecipe = new FurnaceRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "furnace-recipe-" + UUID.randomUUID()), CustomItem.MISTSTEEL_INGOT.getItemStack(), Material.RAW_IRON, 3f, 2000);
        Bukkit.addRecipe(miststeelIngotFurnaceRecipe);
        BlastingRecipe miststeelIngotBastingRecipe = new BlastingRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "blasting-recipe-" + UUID.randomUUID()), CustomItem.MISTSTEEL_INGOT.getItemStack(), Material.RAW_IRON, 3f, 1000);
        Bukkit.addRecipe(miststeelIngotBastingRecipe);

        FurnaceRecipe adamantiteIngotRecipe = new FurnaceRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "furnace-recipe-" + UUID.randomUUID()), CustomItem.ADAMANTITE_INGOT.getItemStack(), Material.ANCIENT_DEBRIS, 3f, 2000);
        Bukkit.addRecipe(adamantiteIngotRecipe);
        BlastingRecipe adamantiteIngotBastingRecipe = new BlastingRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "blasting-recipe-" + UUID.randomUUID()), CustomItem.ADAMANTITE_INGOT.getItemStack(), Material.ANCIENT_DEBRIS, 3f, 1000);
        Bukkit.addRecipe(adamantiteIngotBastingRecipe);
    }
    
    private static void registerTwoByTwoRecipe(ItemStack item, ItemStack result, String recipeId) {
        CustomShapedRecipe customRecipe = new CustomShapedRecipe("LL", "LL", result, recipeId);
        customRecipe.addIngredient('L', item);
        recipes.add(customRecipe);
        Registry.CUSTOM_RECIPE.register(customRecipe.getId(), customRecipe);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        recipe.shape("LL", "LL");
        recipe.setIngredient('L', item.getType());
        Bukkit.addRecipe(recipe);
    }

    private static void registerThreeByThreeRecipe(ItemStack item, ItemStack result, String recipeId) {
        CustomShapedRecipe customRecipe = new CustomShapedRecipe("LLL", "LLL", "LLL", result, recipeId);
        customRecipe.addIngredient('L', item);
        recipes.add(customRecipe);
        Registry.CUSTOM_RECIPE.register(customRecipe.getId(), customRecipe);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        recipe.shape("LLL", "LLL", "LLL");
        recipe.setIngredient('L', item.getType());
        Bukkit.addRecipe(recipe);
    }

    private static void registerTwoByTwoGradedRecipes(ItemStack item, String recipeId) {
        ItemStack itemIronGrade = ItemGradeUtils.createGradedItem(item.clone(), ItemGrade.IRON);
        ItemStack itemGoldGrade = ItemGradeUtils.createGradedItem(item.clone(), ItemGrade.GOLD);
        ItemStack itemTitaniumGrade = ItemGradeUtils.createGradedItem(item.clone(), ItemGrade.TITANIUM);
        CustomShapedRecipe ironGradeRecipe = new CustomShapedRecipe("LL", "LL", itemIronGrade, recipeId + "_iron_grade");
        ironGradeRecipe.addIngredient('L', item);
        recipes.add(ironGradeRecipe);
        Registry.CUSTOM_RECIPE.register(ironGradeRecipe.getId(), ironGradeRecipe);
        CustomShapedRecipe goldGradeRecipe = new CustomShapedRecipe("LL", "LL", itemGoldGrade, recipeId + "_gold_grade");
        goldGradeRecipe.addIngredient('L', itemIronGrade);
        recipes.add(goldGradeRecipe);
        Registry.CUSTOM_RECIPE.register(goldGradeRecipe.getId(), ironGradeRecipe);
        CustomShapedRecipe titaniumGradeRecipe = new CustomShapedRecipe("LL", "LL", itemTitaniumGrade, recipeId + "_titanium_grade");
        titaniumGradeRecipe.addIngredient('L', itemGoldGrade);
        recipes.add(titaniumGradeRecipe);
        Registry.CUSTOM_RECIPE.register(titaniumGradeRecipe.getId(), ironGradeRecipe);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        recipe.shape("LL", "LL");
        recipe.setIngredient('L', item.getType());
        Bukkit.addRecipe(recipe);

        ItemStack reverseIronGradeResult = itemIronGrade.clone();
        reverseIronGradeResult.setAmount(4);
        ItemStack reverseGoldGradeResult = itemGoldGrade.clone();
        reverseGoldGradeResult.setAmount(4);
        CustomShapelessRecipe reverseBaseRecipe = new CustomShapelessRecipe(itemIronGrade, new ItemStack(item.getType(), 4), recipeId + "_reverse_base");
        recipes.add(reverseBaseRecipe);
        Registry.CUSTOM_RECIPE.register(reverseBaseRecipe.getId(), reverseBaseRecipe);
        CustomShapelessRecipe reverseIronGradeRecipe = new CustomShapelessRecipe(itemGoldGrade, reverseIronGradeResult, recipeId + "_reverse_iron_grade");
        recipes.add(reverseIronGradeRecipe);
        Registry.CUSTOM_RECIPE.register(reverseIronGradeRecipe.getId(), reverseIronGradeRecipe);
        CustomShapelessRecipe reverseGoldGradeRecipe = new CustomShapelessRecipe(itemTitaniumGrade, reverseGoldGradeResult, recipeId + "_reverse_gold_grade");
        recipes.add(reverseGoldGradeRecipe);
        Registry.CUSTOM_RECIPE.register(reverseGoldGradeRecipe.getId(), reverseGoldGradeRecipe);

        ShapelessRecipe reverseRecipe = new ShapelessRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        reverseRecipe.addIngredient(item.getType());
        Bukkit.addRecipe(reverseRecipe);
    }

    private static void registerHelmetRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customHelmetRecipe = new CustomShapedRecipe("LLL", "L L", result, recipeId);
        customHelmetRecipe.addIngredient('L', mineral);
        recipes.add(customHelmetRecipe);
        Registry.CUSTOM_RECIPE.register(customHelmetRecipe.getId(), customHelmetRecipe);

        ShapedRecipe helmetRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        helmetRecipe.shape("LLL", "L L");
        helmetRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(helmetRecipe);
    }

    private static void registerChestplateRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customChestplateRecipe = new CustomShapedRecipe("L L", "LLL", "LLL", result, recipeId);
        customChestplateRecipe.addIngredient('L', mineral);
        recipes.add(customChestplateRecipe);
        Registry.CUSTOM_RECIPE.register(customChestplateRecipe.getId(), customChestplateRecipe);

        ShapedRecipe chestplateRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        chestplateRecipe.shape("L L", "LLL", "LLL");
        chestplateRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(chestplateRecipe);
    }

    private static void registerLeggingsRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customLeggingsRecipe = new CustomShapedRecipe("LLL", "L L", "L L", result, recipeId);
        customLeggingsRecipe.addIngredient('L', mineral);
        recipes.add(customLeggingsRecipe);
        Registry.CUSTOM_RECIPE.register(customLeggingsRecipe.getId(), customLeggingsRecipe);

        ShapedRecipe leggingsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        leggingsRecipe.shape("LLL", "L L", "L L");
        leggingsRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(leggingsRecipe);
    }

    private static void registerBootsRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customBootsRecipe = new CustomShapedRecipe("L L", "L L", result, recipeId);
        customBootsRecipe.addIngredient('L', mineral);
        recipes.add(customBootsRecipe);
        Registry.CUSTOM_RECIPE.register(customBootsRecipe.getId(), customBootsRecipe);

        ShapedRecipe bootsRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        bootsRecipe.shape("L L", "L L");
        bootsRecipe.setIngredient('L', mineral.getType());
        Bukkit.addRecipe(bootsRecipe);
    }

    private static void registerSwordRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customSwordRecipe = new CustomShapedRecipe("L", "L", "S", result, recipeId);
        customSwordRecipe.addIngredient('L', mineral);
        customSwordRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customSwordRecipe);
        Registry.CUSTOM_RECIPE.register(customSwordRecipe.getId(), customSwordRecipe);

        ShapedRecipe swordRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        swordRecipe.shape("L", "L", "S");
        swordRecipe.setIngredient('L', mineral.getType());
        swordRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(swordRecipe);
    }

    private static void registerAxeRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customAxeRecipe = new CustomShapedRecipe("LL", "LS", " S", result, recipeId);
        customAxeRecipe.addIngredient('L', mineral);
        customAxeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customAxeRecipe);
        Registry.CUSTOM_RECIPE.register(customAxeRecipe.getId(), customAxeRecipe);

        ShapedRecipe axeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        axeRecipe.shape("LL", "LS", " S");
        axeRecipe.setIngredient('L', mineral.getType());
        axeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(axeRecipe);
    }

    private static void registerShovelRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customShovelRecipe = new CustomShapedRecipe("L", "S", "S", result, recipeId);
        customShovelRecipe.addIngredient('L', mineral);
        customShovelRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customShovelRecipe);
        Registry.CUSTOM_RECIPE.register(customShovelRecipe.getId(), customShovelRecipe);

        ShapedRecipe shovelRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        shovelRecipe.shape("L", "S", "S");
        shovelRecipe.setIngredient('L', mineral.getType());
        shovelRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(shovelRecipe);
    }

    private static void registerHoeRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customHoeRecipe = new CustomShapedRecipe("LL", " S", " S", result, recipeId);
        customHoeRecipe.addIngredient('L', mineral);
        customHoeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customHoeRecipe);
        Registry.CUSTOM_RECIPE.register(customHoeRecipe.getId(), customHoeRecipe);

        ShapedRecipe hoeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        hoeRecipe.shape("LL", " S", " S");
        hoeRecipe.setIngredient('L', mineral.getType());
        hoeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hoeRecipe);
    }

    private static void registerPickaxeRecipe(ItemStack mineral, ItemStack result, String recipeId) {
        CustomShapedRecipe customPickaxeRecipe = new CustomShapedRecipe("LLL", " S ", " S ", result, recipeId);
        customPickaxeRecipe.addIngredient('L', mineral);
        customPickaxeRecipe.addIngredient('S', new ItemStack(Material.STICK));
        recipes.add(customPickaxeRecipe);
        Registry.CUSTOM_RECIPE.register(customPickaxeRecipe.getId(), customPickaxeRecipe);

        ShapedRecipe pickaxeRecipe = new ShapedRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        pickaxeRecipe.shape("LLL", " S ", " S ");
        pickaxeRecipe.setIngredient('L', mineral.getType());
        pickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(pickaxeRecipe);
    }

    private static void registerShapelessRecipe(List<ItemStack> ingredients, ItemStack result, String recipeId) {
        CustomShapelessRecipe customShapelessRecipe = new CustomShapelessRecipe(ingredients, result, recipeId);
        recipes.add(customShapelessRecipe);
        Registry.CUSTOM_RECIPE.register(customShapelessRecipe.getId(), customShapelessRecipe);

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(NamespacedKeys.PLUGIN_NAMESPACE, "custom-recipe-" + UUID.randomUUID()), INVALID_RECIPE_ITEM);
        for(ItemStack ingredient : ingredients)
            recipe.addIngredient(ingredient.getType());
        Bukkit.addRecipe(recipe);
    }

    public static List<CustomRecipe> getRecipes() {
        return recipes;
    }
}
