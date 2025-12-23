package me.brody.mazesurvival.item;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.*;
import me.brody.mazesurvival.item.builder.CustomArmorBuilder;
import me.brody.mazesurvival.item.builder.CustomToolBuilder;
import me.brody.mazesurvival.item.builder.SimpleCustomItemBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.item.builder.*;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomItem {
    // === Resources ===
    public static final CustomItem SCRIPTING_PAPER;
    public static final CustomItem SCRIPTING_TOME;
    public static final CustomItem LOG;
    public static final CustomItem TIN;
    public static final CustomItem BRONZE_INGOT;
    public static final CustomItem MISTSTEEL_INGOT;
    public static final CustomItem SUN_GOLD_NUGGET;
    public static final CustomItem SUN_GOLD_INGOT;
    public static final CustomItem MITHRIL;
    public static final CustomItem ORICHALCUM;
    public static final CustomItem SOLARIUM;
    public static final CustomItem CORRUPTED_DIAMOND;
    public static final CustomItem ADAMANTITE_INGOT;
    // === Armor ===
    public static final CustomItem HARD_LEATHER_HELMET;
    public static final CustomItem HARD_LEATHER_CHESTPLATE;
    public static final CustomItem HARD_LEATHER_LEGGIGNS;
    public static final CustomItem HARD_LEATHER_BOOTS;
    public static final CustomItem BRONZE_HELMET;
    public static final CustomItem BRONZE_CHESTPLATE;
    public static final CustomItem BRONZE_LEGGINGS;
    public static final CustomItem BRONZE_BOOTS;
    public static final CustomItem MISTSTEEL_HELMET;
    public static final CustomItem MISTSTEEL_CHESTPLATE;
    public static final CustomItem MISTSTEEL_LEGGINGS;
    public static final CustomItem MISTSTEEL_BOOTS;
    public static final CustomItem SUN_GOLD_HELMET;
    public static final CustomItem SUN_GOLD_CHESTPLATE;
    public static final CustomItem SUN_GOLD_LEGGINGS;
    public static final CustomItem SUN_GOLD_BOOTS;
    public static final CustomItem ORICHALCUM_HELMET;
    public static final CustomItem ORICHALCUM_CHESTPLATE;
    public static final CustomItem ORICHALCUM_LEGGINGS;
    public static final CustomItem ORICHALCUM_BOOTS;
    public static final CustomItem CORRUPTED_DIAMOND_HELMET;
    public static final CustomItem CORRUPTED_DIAMOND_CHESTPLATE;
    public static final CustomItem CORRUPTED_DIAMOND_LEGGINGS;
    public static final CustomItem CORRUPTED_DIAMOND_BOOTS;
    public static final CustomItem AMETHYST_HELMET;
    public static final CustomItem AMETHYST_CHESTPLATE;
    public static final CustomItem AMETHYST_LEGGINGS;
    public static final CustomItem AMETHYST_BOOTS;
    public static final CustomItem LAPIS_HELMET;
    public static final CustomItem LAPIS_CHESTPLATE;
    public static final CustomItem LAPIS_LEGGINGS;
    public static final CustomItem LAPIS_BOOTS;
    public static final CustomItem MITHRIL_HELMET;
    public static final CustomItem MITHRIL_CHESTPLATE;
    public static final CustomItem MITHRIL_LEGGINGS;
    public static final CustomItem MITHRIL_BOOTS;
    public static final CustomItem SOLARIUM_HELMET;
    public static final CustomItem SOLARIUM_CHESTPLATE;
    public static final CustomItem SOLARIUM_LEGGINGS;
    public static final CustomItem SOLARIUM_BOOTS;
    public static final CustomItem ADAMANTITE_HELMET;
    public static final CustomItem ADAMANTITE_CHESTPLATE;
    public static final CustomItem ADAMANTITE_LEGGINGS;
    public static final CustomItem ADAMANTITE_BOOTS;
    public static final CustomItem WORN_HELMET;
    public static final CustomItem TATTERED_CHESTPLATE;
    public static final CustomItem LEGIONARY_HELMET;
    public static final CustomItem LEGIONARY_CHESTPLATE;
    public static final CustomItem FORTIFIED_HELMET;
    public static final CustomItem FORTIFIED_CHESTPLATE;
    public static final CustomItem FORTIFIED_LEGGINGS;
    public static final CustomItem FORTIFIED_BOOTS;
    public static final CustomItem WRAITH_HELMET;
    public static final CustomItem WRAITH_CHESTPLATE;
    public static final CustomItem WRAITH_LEGGINGS;
    public static final CustomItem WRAITH_BOOTS;
    public static final CustomItem MAZE_RUNNER_BOOTS;
    // === Tools ===
    public static final CustomItem WOODEN_SHORT_SWORD;
    public static final CustomItem WOODEN_HATCHET;
    public static final CustomItem WOODEN_SPADE;
    public static final CustomItem WOODEN_SCYTHE;
    public static final CustomItem WORN_WOODEN_PICKAXE;
    public static final CustomItem BASTARD_SWORD;
    public static final CustomItem BATTLE_AXE;
    public static final CustomItem MACE;
    public static final CustomItem COBBLESTONE_PICKAXE;
    public static final CustomItem BRONZE_SWORD;
    public static final CustomItem BRONZE_AXE;
    public static final CustomItem BRONZE_MACE;
    public static final CustomItem BRONZE_PICKAXE;
    public static final CustomItem MISTSTEEL_SWORD;
    public static final CustomItem MISTSTEEL_AXE;
    public static final CustomItem MISTSTEEL_MACE;
    public static final CustomItem MISTSTEEL_SCYTHE;
    public static final CustomItem MISTSTEEL_PICKAXE;
    public static final CustomItem SUN_GOLD_SWORD;
    public static final CustomItem SUN_GOLD_AXE;
    public static final CustomItem SUN_GOLD_MACE;
    public static final CustomItem SUN_GOLD_PICKAXE;
    public static final CustomItem CORRUPTED_DIAMOND_SWORD;
    public static final CustomItem CORRUPTED_DIAMOND_AXE;
    public static final CustomItem CORRUPTED_DIAMOND_MACE;
    public static final CustomItem CORRUPTED_DIAMOND_SCYTHE;
    public static final CustomItem CORRUPTED_DIAMOND_PICKAXE;
    public static final CustomItem ABYSSAL_EDGE;
    public static final CustomItem ABYSSAL_CLEAVER;
    public static final CustomItem ABYSSAL_GRAVEDIGGER;
    public static final CustomItem ABYSSAL_REAPER;
    public static final CustomItem SPLINTERED_SWORD;
    public static final CustomItem ARISEN_REVENANT_SWORD;
    public static final CustomItem GRAVEDIGGER;
    public static final CustomItem WITHERED_SCYTHE;
    public static final CustomItem FORSAKEN_SCYTHE;
    public static final CustomItem SHORT_BOW;
    public static final CustomItem DECAYED_BOW;
    public static final CustomItem LEGIONARY_BOW;
    public static final CustomItem WORM_WOOD_BOW;
    public static final CustomItem EXECUTIONER_CROSSBOW;
    // === Potions ===
    public static final CustomItem SWIFTNESS;
    public static final CustomItem SWIFTNESS_EXTENDED;
    public static final CustomItem SWIFTNESS_II;
    public static final CustomItem SWIFTNESS_II_EXTENDED;
    public static final CustomItem SWIFTNESS_III;
    public static final CustomItem SWIFTNESS_III_EXTENDED;
    public static final CustomItem SWIFTNESS_IV;
    public static final CustomItem SWIFTNESS_IV_EXTENDED;
    public static final CustomItem SWIFTNESS_V;
    public static final CustomItem SWIFTNESS_V_EXTENDED;
    public static final CustomItem SWIFTNESS_VI;
    public static final CustomItem STRENGTH;
    public static final CustomItem STRENGTH_EXTENDED;
    public static final CustomItem STRENGTH_II;
    public static final CustomItem STRENGTH_II_EXTENDED;
    public static final CustomItem STRENGTH_III;
    public static final CustomItem REGENERATION;
    public static final CustomItem REGENERATION_EXTENDED;
    public static final CustomItem REGENERATION_II;
    public static final CustomItem ABSORPTION;
    public static final CustomItem ABSORPTION_II;
    public static final CustomItem ABSORPTION_III;
    public static final CustomItem SLOWNESS;
    public static final CustomItem SLOWNESS_II;
    public static final CustomItem SLOWNESS_III;
    public static final CustomItem WEAKNESS;
    public static final CustomItem WEAKNESS_II;
    public static final CustomItem WEAKNESS_III;
    public static final CustomItem POISON;
    public static final CustomItem POISON_II;
    public static final CustomItem POISON_III;

    private ItemStack itemStack;
    private String itemName;

    static {
        // === Resources ===
        SCRIPTING_PAPER = new SimpleCustomItemBuilder(new ItemStack(Material.PAPER), "scripting_paper")
                .withDisplayName(ChatColor.WHITE + "Scripting Paper")
                .withLore(List.of(ChatColor.GOLD + "A piece of paper that can be",
                        ChatColor.GOLD + "used to hold a single enchantment",
                        "",
                        ChatColor.GOLD + "Click an item in your inventory",
                        ChatColor.GOLD + "to apply the enchantment"))
                .withPersistentData(NamespacedKeys.SCRIPT, new ScriptDataType(), new Script(1))
                .build();

        SCRIPTING_TOME = new SimpleCustomItemBuilder(new ItemStack(Material.BOOK), "scripting_tome")
                .withDisplayName(ChatColor.AQUA + "Scripting Tome")
                .withLore(List.of(ChatColor.GOLD + "A powerful tome that can be used",
                        ChatColor.GOLD + "to hold two enchantments",
                        "",
                        ChatColor.GOLD + "Click an item in your inventory to",
                        ChatColor.GOLD + "apply the enchantments"))
                .withPersistentData(NamespacedKeys.SCRIPT, new ScriptDataType(), new Script(2))
                .build();

        LOG = new SimpleCustomItemBuilder(new ItemStack(Material.OAK_LOG), "log")
                .withDisplayName(ChatColor.WHITE + "Log")
                .build();

        TIN = new SimpleCustomItemBuilder(new ItemStack(Material.IRON_NUGGET), "tin")
                .withDisplayName(ChatColor.WHITE + "Tin")
                .build();

        BRONZE_INGOT = new SimpleCustomItemBuilder(new ItemStack(Material.COPPER_INGOT), "bronze_ingot")
                .withDisplayName(ChatColor.WHITE + "Bronze Ingot")
                .build();

        MISTSTEEL_INGOT = new SimpleCustomItemBuilder(new ItemStack(Material.IRON_INGOT), "miststeel_ingot")
                .withDisplayName(ChatColor.WHITE + "Miststeel Ingot")
                .build();

        SUN_GOLD_NUGGET = new SimpleCustomItemBuilder(new ItemStack(Material.GOLD_NUGGET), "sun_gold_nugget")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Nugget")
                .build();

        SUN_GOLD_INGOT = new SimpleCustomItemBuilder(new ItemStack(Material.GOLD_INGOT), "sun_gold_ingot")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Ingot")
                .build();

        ORICHALCUM = new SimpleCustomItemBuilder(new ItemStack(Material.EMERALD), "orichalcum")
                .withDisplayName(ChatColor.WHITE + "Orichalcum")
                .build();

        MITHRIL = new SimpleCustomItemBuilder(new ItemStack(Material.PRISMARINE_SHARD), "mithril")
                .withDisplayName(ChatColor.WHITE + "Mithril")
                .build();

        CORRUPTED_DIAMOND = new SimpleCustomItemBuilder(new ItemStack(Material.DIAMOND), "corrupted_diamond")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond")
                .build();

        SOLARIUM = new SimpleCustomItemBuilder(new ItemStack(Material.REDSTONE), "solarium")
                .withDisplayName(ChatColor.WHITE + "Solarium")
                .build();

        ADAMANTITE_INGOT = new SimpleCustomItemBuilder(new ItemStack(Material.NETHERITE_INGOT), "adamantite_ingot")
                .withDisplayName(ChatColor.WHITE + "Adamantite Ingot")
                .build();

        // === Armor ===
        float helmetHealthMultiplier = 0.2f;
        float chestplateHealthMultiplier = 0.4f;
        float leggingsHealthMultiplier = 0.3f;
        float bootsHealthMultiplier = 0.1f;
        float helmetDurabilityMultiplier = 0.6f;
        float chestplateDurabilityMultiplier = 1f;
        float leggingsDurabilityMultiplier = 0.85f;
        float bootsDurabilityMultiplier = 0.7f;

        Color hardLeatherColor = Color.fromRGB(128, 91, 57);
        int hardLeatherHealthBoost = 6;
        int hardLeatherBaseDurability = 200;
        HARD_LEATHER_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "hard_leather_helmet")
                .withDisplayName(ChatColor.WHITE + "Hard Leather Helmet")
                .withColor(hardLeatherColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(hardLeatherHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(hardLeatherBaseDurability * helmetDurabilityMultiplier))
                .build();

        HARD_LEATHER_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "hard_leather_chestplate")
                .withDisplayName(ChatColor.WHITE + "Hard Leather Chestplate")
                .withColor(hardLeatherColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(hardLeatherHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(hardLeatherBaseDurability * chestplateDurabilityMultiplier))
                .build();

        HARD_LEATHER_LEGGIGNS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "hard_leather_leggings")
                .withDisplayName(ChatColor.WHITE + "Hard Leather Leggings")
                .withColor(hardLeatherColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(hardLeatherHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(hardLeatherBaseDurability * leggingsDurabilityMultiplier))
                .build();

        HARD_LEATHER_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "hard_leather_boots")
                .withDisplayName(ChatColor.WHITE + "Hard Leather Boots")
                .withColor(hardLeatherColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(hardLeatherHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(hardLeatherBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color bronzeColor = Color.fromRGB(211, 169, 55);
        int bronzeHealthBoost = 10;
        int bronzeBaseDurability = 350;
        BRONZE_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "bronze_helmet")
                .withDisplayName(ChatColor.WHITE + "Bronze Helmet")
                .withColor(bronzeColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(bronzeHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(bronzeBaseDurability * helmetDurabilityMultiplier))
                .build();

        BRONZE_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "bronze_chestplate")
                .withDisplayName(ChatColor.WHITE + "Bronze Chestplate")
                .withColor(bronzeColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(bronzeHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(bronzeBaseDurability * chestplateDurabilityMultiplier))
                .build();

        BRONZE_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "bronze_leggings")
                .withDisplayName(ChatColor.WHITE + "Bronze Leggings")
                .withColor(bronzeColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(bronzeHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(bronzeBaseDurability * leggingsDurabilityMultiplier))
                .build();

        BRONZE_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "bronze_boots")
                .withDisplayName(ChatColor.WHITE + "Bronze Boots")
                .withColor(bronzeColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(bronzeHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(bronzeBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color miststeelColor = Color.fromRGB(216, 246, 247);
        int miststeelHealthBoost = 18;
        int miststeelBaseDurability = 400;
        MISTSTEEL_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "miststeel_helmet")
                .withDisplayName(ChatColor.WHITE + "Miststeel Helmet")
                .withColor(miststeelColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(miststeelHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(miststeelBaseDurability * helmetDurabilityMultiplier))
                .build();

        MISTSTEEL_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "miststeel_chestplate")
                .withDisplayName(ChatColor.WHITE + "Miststeel Chestplate")
                .withColor(miststeelColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(miststeelHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(miststeelBaseDurability * chestplateDurabilityMultiplier))
                .build();

        MISTSTEEL_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "miststeel_leggings")
                .withDisplayName(ChatColor.WHITE + "Miststeel Leggings")
                .withColor(miststeelColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(miststeelHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(miststeelBaseDurability * leggingsDurabilityMultiplier))
                .build();

        MISTSTEEL_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "miststeel_boots")
                .withDisplayName(ChatColor.WHITE + "Miststeel Boots")
                .withColor(miststeelColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(miststeelHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(miststeelBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color sunGoldColor = Color.fromRGB(255, 186, 0);
        int sunGoldHealthBoost = 24;
        int sunGoldBaseDurability = 450;
        SUN_GOLD_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "sun_gold_helmet")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Helmet")
                .withColor(sunGoldColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(sunGoldHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(sunGoldBaseDurability * helmetDurabilityMultiplier))
                .build();

        SUN_GOLD_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "sun_gold_chestplate")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Chestplate")
                .withColor(sunGoldColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(sunGoldHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(sunGoldBaseDurability * chestplateDurabilityMultiplier))
                .build();

        SUN_GOLD_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "sun_gold_leggings")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Leggings")
                .withColor(sunGoldColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(sunGoldHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(sunGoldBaseDurability * leggingsDurabilityMultiplier))
                .build();

        SUN_GOLD_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "sun_gold_boots")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Boots")
                .withColor(sunGoldColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(sunGoldHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(sunGoldBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color corruptedDiamondColor = Color.fromRGB(3, 129, 171);
        int corruptedDiamondHealthBoost = 44;
        int corruptedDiamondBaseDurability = 500;
        CORRUPTED_DIAMOND_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "corrupted_diamond_helmet")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Helmet")
                .withColor(corruptedDiamondColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(corruptedDiamondHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(corruptedDiamondBaseDurability * helmetDurabilityMultiplier))
                .build();

        CORRUPTED_DIAMOND_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "corrupted_diamond_chestplate")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Chestplate")
                .withColor(corruptedDiamondColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(corruptedDiamondHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(corruptedDiamondBaseDurability * chestplateDurabilityMultiplier))
                .build();

        CORRUPTED_DIAMOND_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "corrupted_diamond_leggings")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Leggings")
                .withColor(corruptedDiamondColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(corruptedDiamondHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(corruptedDiamondBaseDurability * leggingsDurabilityMultiplier))
                .build();

        CORRUPTED_DIAMOND_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "corrupted_diamond_boots")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Boots")
                .withColor(corruptedDiamondColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(corruptedDiamondHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(corruptedDiamondBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> amethystArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Glintstone Tempo",
                ChatColor.GRAY + "Gain a speed boost when in",
                ChatColor.GRAY + "the maze.");
        Color amethystColor = Color.fromRGB(239, 151, 249);
        int amethystHealthBoost = 6;
        int amethystBaseDurability = 300;
        AMETHYST_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "amethyst_helmet")
                .withDisplayName(ChatColor.WHITE + "Amethyst Helmet")
                .withLore(amethystArmorLore)
                .withColor(amethystColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(amethystHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(amethystBaseDurability * helmetDurabilityMultiplier))
                .build();

        AMETHYST_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "amethyst_chestplate")
                .withDisplayName(ChatColor.WHITE + "Amethyst Chestplate")
                .withLore(amethystArmorLore)
                .withColor(amethystColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(amethystHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(amethystBaseDurability * chestplateDurabilityMultiplier))
                .build();

        AMETHYST_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "amethyst_leggings")
                .withDisplayName(ChatColor.WHITE + "Amethyst Leggings")
                .withLore(amethystArmorLore)
                .withColor(amethystColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(amethystHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(amethystBaseDurability * leggingsDurabilityMultiplier))
                .build();

        AMETHYST_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "amethyst_boots")
                .withDisplayName(ChatColor.WHITE + "Amethyst Boots")
                .withLore(amethystArmorLore)
                .withColor(amethystColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(amethystHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(amethystBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> lapisArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Purification",
                ChatColor.GRAY + "Immune to poison, wither, and hunger.");
        Color lapisColor = Color.fromRGB(41, 76, 255);
        int lapisHealthBoost = 12;
        int lapisBaseDurability = 350;
        LAPIS_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "lapis_helmet")
                .withDisplayName(ChatColor.WHITE + "Lapis Helmet")
                .withLore(lapisArmorLore)
                .withColor(lapisColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(lapisHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(lapisBaseDurability * helmetDurabilityMultiplier))
                .build();

        LAPIS_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "lapis_chestplate")
                .withDisplayName(ChatColor.WHITE + "Lapis Chestplate")
                .withLore(lapisArmorLore)
                .withColor(lapisColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(lapisHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(lapisBaseDurability * chestplateDurabilityMultiplier))
                .build();

        LAPIS_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "lapis_leggings")
                .withDisplayName(ChatColor.WHITE + "Lapis Leggings")
                .withLore(lapisArmorLore)
                .withColor(lapisColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(lapisHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(lapisBaseDurability * leggingsDurabilityMultiplier))
                .build();

        LAPIS_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "lapis_boots")
                .withDisplayName(ChatColor.WHITE + "Lapis Boots")
                .withLore(lapisArmorLore)
                .withColor(lapisColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(lapisHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(lapisBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> orichalcumArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Fireproof",
                ChatColor.GRAY + "Negate all damage from fire, lava,",
                ChatColor.GRAY + "and magma blocks.");
        Color orichalcumColor = Color.fromRGB(118, 176, 142);
        int orichalcumHealthBoost = 20;
        int orichalcumBaseDurability = 400;
        ORICHALCUM_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "orichalcum_helmet")
                .withDisplayName(ChatColor.WHITE + "Orichalcum Helmet")
                .withLore(orichalcumArmorLore)
                .withColor(orichalcumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(orichalcumHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(orichalcumBaseDurability * helmetDurabilityMultiplier))
                .build();

        ORICHALCUM_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "orichalcum_chestplate")
                .withDisplayName(ChatColor.WHITE + "Orichalcum Chestplate")
                .withLore(orichalcumArmorLore)
                .withColor(orichalcumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(orichalcumHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(orichalcumBaseDurability * chestplateDurabilityMultiplier))
                .build();

        ORICHALCUM_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "orichalcum_leggings")
                .withDisplayName(ChatColor.WHITE + "Orichalcum Leggings")
                .withLore(orichalcumArmorLore)
                .withColor(orichalcumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(orichalcumHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(orichalcumBaseDurability * leggingsDurabilityMultiplier))
                .build();

        ORICHALCUM_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "orichalcum_boots")
                .withDisplayName(ChatColor.WHITE + "Orichalcum Boots")
                .withLore(orichalcumArmorLore)
                .withColor(orichalcumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(orichalcumHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(orichalcumBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> mithrilArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Evasion",
                ChatColor.GRAY + "Small chance of negating all incoming",
                ChatColor.GRAY + "damage.");
        Color mithrilColor = Color.fromRGB(158, 232, 216);
        int mithrilHealthBoost = 30;
        int mithrilBaseDurability = 450;
        MITHRIL_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "mithril_helmet")
                .withDisplayName(ChatColor.WHITE + "Mithril Helmet")
                .withLore(mithrilArmorLore)
                .withColor(mithrilColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(mithrilHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(mithrilBaseDurability * helmetDurabilityMultiplier))
                .build();

        MITHRIL_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "mithril_chestplate")
                .withDisplayName(ChatColor.WHITE + "Mithril Chestplate")
                .withLore(mithrilArmorLore)
                .withColor(mithrilColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(mithrilHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(mithrilBaseDurability * chestplateDurabilityMultiplier))
                .build();

        MITHRIL_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "mithril_leggings")
                .withDisplayName(ChatColor.WHITE + "Mithril Leggings")
                .withLore(mithrilArmorLore)
                .withColor(mithrilColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(mithrilHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(mithrilBaseDurability * leggingsDurabilityMultiplier))
                .build();

        MITHRIL_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "mithril_boots")
                .withDisplayName(ChatColor.WHITE + "Mithril Boots")
                .withLore(mithrilArmorLore)
                .withColor(mithrilColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(mithrilHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(mithrilBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> solariumArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Fireproof",
                ChatColor.GRAY + "Negate all damage from fire, lava,",
                ChatColor.GRAY + "and magma blocks.");
        Color solariumColor = Color.fromRGB(240, 26, 76);
        int solariumHealthBoost = 40;
        int solariumBaseDurability = 500;
        SOLARIUM_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "solarium_helmet")
                .withDisplayName(ChatColor.WHITE + "Solarium Helmet")
                .withLore(solariumArmorLore)
                .withColor(solariumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(solariumHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(solariumBaseDurability * helmetDurabilityMultiplier))
                .build();

        SOLARIUM_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "solarium_chestplate")
                .withDisplayName(ChatColor.WHITE + "Solarium Chestplate")
                .withLore(solariumArmorLore)
                .withColor(solariumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(solariumHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(solariumBaseDurability * chestplateDurabilityMultiplier))
                .build();

        SOLARIUM_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "solarium_leggings")
                .withDisplayName(ChatColor.WHITE + "Solarium Leggings")
                .withLore(solariumArmorLore)
                .withColor(solariumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(solariumHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(solariumBaseDurability * leggingsDurabilityMultiplier))
                .build();

        SOLARIUM_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "solarium_boots")
                .withDisplayName(ChatColor.WHITE + "Solarium Boots")
                .withLore(solariumArmorLore)
                .withColor(solariumColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(solariumHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(solariumBaseDurability * bootsDurabilityMultiplier))
                .build();

        List<String> adamantiteArmorLore = List.of(
                ChatColor.WHITE + "Set Bonus: " + ChatColor.GOLD + "Indestructible",
                ChatColor.GRAY + "Armor takes no durability damage.");
        Color adamantiteColor = Color.fromRGB(43, 8, 64);
        int adamantiteHealthBoost = 54;
        int adamantiteBaseDurability = 550;
        ADAMANTITE_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "adamantite_helmet")
                .withDisplayName(ChatColor.WHITE + "Adamantite Helmet")
                .withLore(adamantiteArmorLore)
                .withColor(adamantiteColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(adamantiteHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(adamantiteBaseDurability * helmetDurabilityMultiplier))
                .build();

        ADAMANTITE_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "adamantite_chestplate")
                .withDisplayName(ChatColor.WHITE + "Adamantite Chestplate")
                .withLore(adamantiteArmorLore)
                .withColor(adamantiteColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(adamantiteHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(adamantiteBaseDurability * chestplateDurabilityMultiplier))
                .build();

        ADAMANTITE_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "adamantite_leggings")
                .withDisplayName(ChatColor.WHITE + "Adamantite Leggings")
                .withLore(adamantiteArmorLore)
                .withColor(adamantiteColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(adamantiteHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(adamantiteBaseDurability * leggingsDurabilityMultiplier))
                .build();

        ADAMANTITE_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "adamantite_boots")
                .withDisplayName(ChatColor.WHITE + "Adamantite Boots")
                .withLore(adamantiteArmorLore)
                .withColor(adamantiteColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(adamantiteHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(adamantiteBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color wornColor = Color.fromRGB(153, 153, 153);
        int wornHelmetHealthBoost = 2;
        int wornSetBaseDurability = 128;
        WORN_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "worn_helmet")
                .withDisplayName(ChatColor.WHITE + "Worn Helmet")
                .withColor(wornColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, wornHelmetHealthBoost)
                .withBaseDurability((int)(wornSetBaseDurability * helmetDurabilityMultiplier))
                .build();

        int tatteredChestplateHealthBoost = 4;
        TATTERED_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "tattered_chestplate")
                .withDisplayName(ChatColor.WHITE + "Tattered Chestplate")
                .withColor(wornColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, tatteredChestplateHealthBoost)
                .withBaseDurability((int)(wornSetBaseDurability * chestplateDurabilityMultiplier))
                .build();

        Color legionaryColor = Color.fromRGB(230, 220, 149);
        int legionaryHelmetHealthBoost = 8;
        int legionarySetBaseDurability = 250;
        LEGIONARY_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "legionary_helmet")
                .withDisplayName(ChatColor.WHITE + "Legionary Helmet")
                .withColor(legionaryColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, legionaryHelmetHealthBoost)
                .withBaseDurability((int)(legionarySetBaseDurability * helmetDurabilityMultiplier))
                .build();

        int legionaryChestplateHealthBoost = 12;
        LEGIONARY_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "legionary_chestplate")
                .withDisplayName(ChatColor.WHITE + "Legionary Chestplate")
                .withColor(legionaryColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, legionaryChestplateHealthBoost)
                .withBaseDurability((int)(legionarySetBaseDurability * chestplateDurabilityMultiplier))
                .build();

        Color fortifiedColor = Color.fromRGB(247, 247, 247);
        int fortifiedHealthBoost = 24;
        int fortifiedBaseDurability = 320;
        FORTIFIED_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "fortified_helmet")
                .withDisplayName(ChatColor.WHITE + "Fortified Helmet")
                .withColor(fortifiedColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(fortifiedHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(fortifiedBaseDurability * helmetDurabilityMultiplier))
                .build();

        FORTIFIED_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "fortified_chestplate")
                .withDisplayName(ChatColor.WHITE + "Fortified Chestplate")
                .withColor(fortifiedColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(fortifiedHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(fortifiedBaseDurability * chestplateDurabilityMultiplier))
                .build();

        FORTIFIED_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "fortified_leggings")
                .withDisplayName(ChatColor.WHITE + "Fortified Leggings")
                .withColor(fortifiedColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(fortifiedHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(fortifiedBaseDurability * leggingsDurabilityMultiplier))
                .build();

        FORTIFIED_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "fortified_boots")
                .withDisplayName(ChatColor.WHITE + "Fortified Boots")
                .withColor(fortifiedColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(fortifiedHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(fortifiedBaseDurability * bootsDurabilityMultiplier))
                .build();

        Color wraithColor = Color.fromRGB(36, 36, 36);
        int wraithHealthBoost = 40;
        int wraithBaseDurability = 400;
        WRAITH_HELMET = new CustomArmorBuilder(new ItemStack(Material.LEATHER_HELMET), "wraith_helmet")
                .withDisplayName(ChatColor.WHITE + "Wraith Helmet")
                .withColor(wraithColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(wraithHealthBoost * helmetHealthMultiplier))
                .withBaseDurability((int)(wraithBaseDurability * helmetDurabilityMultiplier))
                .build();

        WRAITH_CHESTPLATE = new CustomArmorBuilder(new ItemStack(Material.LEATHER_CHESTPLATE), "wraith_chestplate")
                .withDisplayName(ChatColor.WHITE + "Wraith Chestplate")
                .withColor(wraithColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(wraithHealthBoost * chestplateHealthMultiplier))
                .withBaseDurability((int)(wraithBaseDurability * chestplateDurabilityMultiplier))
                .build();

        WRAITH_LEGGINGS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_LEGGINGS), "wraith_leggings")
                .withDisplayName(ChatColor.WHITE + "Wraith Leggings")
                .withColor(wraithColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(wraithHealthBoost * leggingsHealthMultiplier))
                .withBaseDurability((int)(wraithBaseDurability * leggingsDurabilityMultiplier))
                .build();

        WRAITH_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "wraith_boots")
                .withDisplayName(ChatColor.WHITE + "Wraith Boots")
                .withColor(wraithColor)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, Math.round(wraithHealthBoost * bootsHealthMultiplier))
                .withBaseDurability((int)(wraithBaseDurability * bootsDurabilityMultiplier))
                .build();

        int mazeRunnerBootsHealthBoost = 3;
        int mazeRunnerBaseDurability = 250;
        Map<MazeEnchantment, Integer> mazeRunnerEnchantment = new HashMap<>();
        mazeRunnerEnchantment.put(MazeEnchantment.MAZE_RUNNER, 1);
        MAZE_RUNNER_BOOTS = new CustomArmorBuilder(new ItemStack(Material.LEATHER_BOOTS), "maze_runner_boots")
                .withDisplayName(ChatColor.AQUA + "Maze Runner Boots")
                .withColor(125, 20, 114)
                .withCustomEnchantments(mazeRunnerEnchantment)
                .withPersistentData(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER, mazeRunnerBootsHealthBoost)
                .withBaseDurability((int)(mazeRunnerBaseDurability * bootsDurabilityMultiplier))
                .build();

        // === Weapons ===
        float swordAttackMultiplier = 1f;
        float axeAttackMultiplier = 1.3f;
        float shovelAttackMultiplier = 0.75f;
        float hoeAttackMultiplier = 0.6f;
        float pickaxeAttackMultiplier = 0.2f;

        int woodenBaseAttack = 3;
        int woodenToolBaseDurability = 120;
        WOODEN_SHORT_SWORD = new CustomToolBuilder(new ItemStack(Material.WOODEN_SWORD), "wooden_short_sword")
                .withDisplayName(ChatColor.WHITE + "Wooden Short Sword")
                .withBaseDamage(Math.round(woodenBaseAttack * swordAttackMultiplier))
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        WOODEN_HATCHET = new CustomToolBuilder(new ItemStack(Material.WOODEN_AXE), "wooden_hatchet")
                .withDisplayName(ChatColor.WHITE + "Wooden Hatchet")
                .withBaseDamage(Math.round(woodenBaseAttack * axeAttackMultiplier))
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        WOODEN_SPADE = new CustomToolBuilder(new ItemStack(Material.WOODEN_SHOVEL), "wooden_spade")
                .withDisplayName(ChatColor.WHITE + "Wooden Spade")
                .withBaseDamage(Math.round(woodenBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        WOODEN_SCYTHE = new CustomToolBuilder(new ItemStack(Material.WOODEN_HOE), "wooden_scythe")
                .withDisplayName(ChatColor.WHITE + "Wooden Scythe")
                .withBaseDamage(Math.round(woodenBaseAttack * hoeAttackMultiplier))
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        int wornWoodenPickaxeToolLevel = 1;
        WORN_WOODEN_PICKAXE = new CustomToolBuilder(new ItemStack(Material.WOODEN_PICKAXE), "worn_wooden_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Worn Wooden Pickaxe")
                .withBaseDamage(Math.round(woodenBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(wornWoodenPickaxeToolLevel)
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        int stoneBaseAttack = 5;
        int stoneToolBaseDurability = 250;
        BASTARD_SWORD = new CustomToolBuilder(new ItemStack(Material.STONE_SWORD), "bastard_sword")
                .withDisplayName(ChatColor.WHITE + "Bastard Sword")
                .withBaseDamage(Math.round(stoneBaseAttack * swordAttackMultiplier))
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        BATTLE_AXE = new CustomToolBuilder(new ItemStack(Material.STONE_AXE), "battle_axe")
                .withDisplayName(ChatColor.WHITE + "Battle Axe")
                .withBaseDamage(Math.round(stoneBaseAttack * axeAttackMultiplier))
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        MACE = new CustomToolBuilder(new ItemStack(Material.STONE_SHOVEL), "mace")
                .withDisplayName(ChatColor.WHITE + "Mace")
                .withBaseDamage(Math.round(stoneBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        int cobblestonePickaxeToolLevel = 2;
        COBBLESTONE_PICKAXE = new CustomToolBuilder(new ItemStack(Material.STONE_PICKAXE), "cobblestone_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Cobblestone Pickaxe")
                .withBaseDamage(Math.round(stoneBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(cobblestonePickaxeToolLevel)
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        int bronzeBaseAttack = 8;
        int bronzeToolBaseDurability = 380;
        BRONZE_SWORD = new CustomToolBuilder(new ItemStack(Material.STONE_SWORD), "bronze_sword")
                .withDisplayName(ChatColor.WHITE + "Bronze Sword")
                .withBaseDamage(Math.round(bronzeBaseAttack * swordAttackMultiplier))
                .withBaseDurability(bronzeToolBaseDurability)
                .build();

        BRONZE_AXE = new CustomToolBuilder(new ItemStack(Material.STONE_AXE), "bronze_axe")
                .withDisplayName(ChatColor.WHITE + "Bronze Axe")
                .withBaseDamage(Math.round(bronzeBaseAttack * axeAttackMultiplier))
                .withBaseDurability(bronzeToolBaseDurability)
                .build();

        BRONZE_MACE = new CustomToolBuilder(new ItemStack(Material.STONE_SHOVEL), "bronze_mace")
                .withDisplayName(ChatColor.WHITE + "Bronze Mace")
                .withBaseDamage(Math.round(bronzeBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(bronzeToolBaseDurability)
                .build();

        int bronzePickaxeToolLevel = 3;
        BRONZE_PICKAXE = new CustomToolBuilder(new ItemStack(Material.STONE_PICKAXE), "bronze_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Bronze Pickaxe")
                .withBaseDamage(Math.round(bronzeBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(bronzePickaxeToolLevel)
                .withBaseDurability(bronzeToolBaseDurability)
                .build();

        int miststeelBaseAttack = 12;
        int miststeelToolBaseDurability = 560;
        MISTSTEEL_SWORD = new CustomToolBuilder(new ItemStack(Material.IRON_SWORD), "miststeel_sword")
                .withDisplayName(ChatColor.WHITE + "Miststeel Sword")
                .withBaseDamage(Math.round(miststeelBaseAttack * swordAttackMultiplier))
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        MISTSTEEL_AXE = new CustomToolBuilder(new ItemStack(Material.IRON_AXE), "miststeel_axe")
                .withDisplayName(ChatColor.WHITE + "Miststeel Axe")
                .withBaseDamage(Math.round(miststeelBaseAttack * axeAttackMultiplier))
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        MISTSTEEL_MACE = new CustomToolBuilder(new ItemStack(Material.IRON_SHOVEL), "miststeel_mace")
                .withDisplayName(ChatColor.WHITE + "Miststeel Mace")
                .withBaseDamage(Math.round(miststeelBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        MISTSTEEL_SCYTHE = new CustomToolBuilder(new ItemStack(Material.IRON_HOE), "miststeel_scythe")
                .withDisplayName(ChatColor.WHITE + "Miststeel Scythe")
                .withBaseDamage(Math.round(miststeelBaseAttack * hoeAttackMultiplier))
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        int miststeelPickaxeToolLevel = 4;
        MISTSTEEL_PICKAXE = new CustomToolBuilder(new ItemStack(Material.IRON_PICKAXE), "miststeel_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Miststeel Pickaxe")
                .withBaseDamage(Math.round(miststeelBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(miststeelPickaxeToolLevel)
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        int sunGoldBaseAttack = 18;
        int sunGoldTooBaseDurability = 875;
        SUN_GOLD_SWORD = new CustomToolBuilder(new ItemStack(Material.GOLDEN_SWORD), "sun_gold_sword")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Sword")
                .withBaseDamage(Math.round(sunGoldBaseAttack * swordAttackMultiplier))
                .withBaseDurability(sunGoldTooBaseDurability)
                .build();

        SUN_GOLD_AXE = new CustomToolBuilder(new ItemStack(Material.GOLDEN_AXE), "sun_gold_axe")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Axe")
                .withBaseDamage(Math.round(sunGoldBaseAttack * axeAttackMultiplier))
                .withBaseDurability(sunGoldTooBaseDurability)
                .build();

        SUN_GOLD_MACE = new CustomToolBuilder(new ItemStack(Material.GOLDEN_SHOVEL), "sun_gold_mace")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Mace")
                .withBaseDamage(Math.round(sunGoldBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(sunGoldTooBaseDurability)
                .build();

        int sunGoldPickaxeToolLevel = 5;
        SUN_GOLD_PICKAXE = new CustomToolBuilder(new ItemStack(Material.GOLDEN_PICKAXE), "sun_gold_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Sun Gold Pickaxe")
                .withBaseDamage(Math.round(sunGoldBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(sunGoldPickaxeToolLevel)
                .withBaseDurability(sunGoldTooBaseDurability)
                .build();

        int corruptedDiamondBaseAttack = 30;
        int corruptedDiamondToolBaseDurability = 1200;
        CORRUPTED_DIAMOND_SWORD = new CustomToolBuilder(new ItemStack(Material.DIAMOND_SWORD), "corrupted_diamond_sword")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Sword")
                .withBaseDamage(Math.round(corruptedDiamondBaseAttack * swordAttackMultiplier))
                .withBaseDurability(corruptedDiamondToolBaseDurability)
                .build();

        CORRUPTED_DIAMOND_AXE = new CustomToolBuilder(new ItemStack(Material.DIAMOND_AXE), "corrupted_diamond_axe")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Axe")
                .withBaseDamage(Math.round(corruptedDiamondBaseAttack * axeAttackMultiplier))
                .withBaseDurability(corruptedDiamondToolBaseDurability)
                .build();

        CORRUPTED_DIAMOND_MACE = new CustomToolBuilder(new ItemStack(Material.DIAMOND_SHOVEL), "corrupted_diamond_mace")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Mace")
                .withBaseDamage(Math.round(corruptedDiamondBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(corruptedDiamondToolBaseDurability)
                .build();

        CORRUPTED_DIAMOND_SCYTHE = new CustomToolBuilder(new ItemStack(Material.DIAMOND_HOE), "corrupted_diamond_scythe")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Scythe")
                .withBaseDamage(Math.round(corruptedDiamondBaseAttack * hoeAttackMultiplier))
                .withBaseDurability(corruptedDiamondToolBaseDurability)
                .build();

        int corruptedDiamondPickaxeToolLevel = 6;
        CORRUPTED_DIAMOND_PICKAXE = new CustomToolBuilder(new ItemStack(Material.DIAMOND_PICKAXE), "corrupted_diamond_pickaxe")
                .withDisplayName(ChatColor.WHITE + "Corrupted Diamond Pickaxe")
                .withBaseDamage(Math.round(corruptedDiamondBaseAttack * pickaxeAttackMultiplier))
                .withToolLevel(corruptedDiamondPickaxeToolLevel)
                .withBaseDurability(corruptedDiamondToolBaseDurability)
                .build();

        int adamantiteBaseAttack = 34;
        int adamantiteToolBaseDurability = 1580;
        ABYSSAL_EDGE = new CustomToolBuilder(new ItemStack(Material.NETHERITE_SWORD), "abyssal_edge")
                .withDisplayName(ChatColor.WHITE + "Abyssal Edge")
                .withBaseDamage(Math.round(adamantiteBaseAttack * swordAttackMultiplier))
                .withBaseDurability(adamantiteToolBaseDurability)
                .build();

        ABYSSAL_CLEAVER = new CustomToolBuilder(new ItemStack(Material.NETHERITE_AXE), "abyssal_cleaver")
                .withDisplayName(ChatColor.WHITE + "Abyssal Cleaver")
                .withBaseDamage(Math.round(adamantiteBaseAttack * axeAttackMultiplier))
                .withBaseDurability(adamantiteToolBaseDurability)
                .build();

        ABYSSAL_GRAVEDIGGER = new CustomToolBuilder(new ItemStack(Material.NETHERITE_SHOVEL), "abyssal_gravedigger")
                .withDisplayName(ChatColor.WHITE + "Abyssal Gravedigger")
                .withBaseDamage(Math.round(adamantiteBaseAttack * shovelAttackMultiplier))
                .withBaseDurability(adamantiteToolBaseDurability)
                .build();

        ABYSSAL_REAPER = new CustomToolBuilder(new ItemStack(Material.NETHERITE_HOE), "abyssal_reaper")
                .withDisplayName(ChatColor.WHITE + "Abyssal Reaper")
                .withBaseDamage(Math.round(adamantiteBaseAttack * hoeAttackMultiplier))
                .withBaseDurability(adamantiteToolBaseDurability)
                .build();

        int splinteredSwordBaseAttack = 2;
        SPLINTERED_SWORD = new CustomToolBuilder(new ItemStack(Material.WOODEN_SWORD), "splintered_sword")
                .withDisplayName(ChatColor.WHITE + "Splintered Sword")
                .withBaseDamage(Math.round(splinteredSwordBaseAttack))
                .withBaseDurability(woodenToolBaseDurability)
                .build();

        int arisenRevenantSwordBaseAttack = 5;
        ARISEN_REVENANT_SWORD = new CustomToolBuilder(new ItemStack(Material.WOODEN_SWORD), "arisen_revenant_sword")
                .withDisplayName(ChatColor.WHITE + "Arisen Revenant Sword")
                .withBaseDamage(Math.round(arisenRevenantSwordBaseAttack))
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        int gravediggerBaseAttack = 8;
        GRAVEDIGGER = new CustomToolBuilder(new ItemStack(Material.STONE_SHOVEL), "gravedigger")
                .withDisplayName(ChatColor.WHITE + "Gravedigger")
                .withBaseDamage(Math.round(gravediggerBaseAttack))
                .withBaseDurability(stoneToolBaseDurability)
                .build();

        int witheredScytheBaseAttack = 15;
        WITHERED_SCYTHE = new CustomToolBuilder(new ItemStack(Material.STONE_HOE), "withered_scythe")
                .withDisplayName(ChatColor.WHITE + "Withered Scythe")
                .withBaseDamage(Math.round(witheredScytheBaseAttack))
                .withBaseDurability(bronzeToolBaseDurability)
                .build();

        int forsakenScytheBaseAttack = 20;
        FORSAKEN_SCYTHE = new CustomToolBuilder(new ItemStack(Material.STONE_HOE), "forsaken_scythe")
                .withDisplayName(ChatColor.WHITE + "Forsaken Scythe")
                .withBaseDamage(Math.round(forsakenScytheBaseAttack))
                .withBaseDurability(miststeelToolBaseDurability)
                .build();

        int shortBowBaseDurability = 80;
        SHORT_BOW = new CustomToolBuilder(new ItemStack(Material.BOW), "short_bow")
                .withDisplayName(ChatColor.WHITE + "Short Bow")
                .withBaseDurability(shortBowBaseDurability)
                .build();

        int decayedBowBaseDurability = 50;
        DECAYED_BOW = new CustomToolBuilder(new ItemStack(Material.BOW), "decayed_bow")
                .withDisplayName(ChatColor.WHITE + "Decayed Bow")
                .withBaseDurability(decayedBowBaseDurability)
                .build();

        int legionaryBowBaseDurability = 150;
        LEGIONARY_BOW = new CustomToolBuilder(new ItemStack(Material.BOW), "legionary_bow")
                .withDisplayName(ChatColor.WHITE + "Legionary Bow")
                .withBaseDurability(legionaryBowBaseDurability)
                .build();

        int wormWoodBowBaseDurability = 200;
        WORM_WOOD_BOW = new CustomToolBuilder(new ItemStack(Material.BOW), "worm_wood_bow")
                .withDisplayName(ChatColor.WHITE + "Worm Wood Bow")
                .withBaseDurability(wormWoodBowBaseDurability)
                .build();

        int executionerCrossbowBaseDurability = 250;
        EXECUTIONER_CROSSBOW = new CustomToolBuilder(new ItemStack(Material.CROSSBOW), "executioner_crossbow")
                .withDisplayName(ChatColor.WHITE + "Executioner Crossbow")
                .withBaseDurability(executionerCrossbowBaseDurability)
                .build();

        // === Potions ===
        SWIFTNESS = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 0, false)
                .build();

        SWIFTNESS_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness Extended")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 600, 0, false)
                .build();

        SWIFTNESS_II = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness II")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 1, false)
                .build();

        SWIFTNESS_II_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_two_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness II Extended")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 600, 1, false)
                .build();

        SWIFTNESS_III = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness III")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 2, false)
                .build();

        SWIFTNESS_III_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_three_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness III Extended")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 600, 2, false)
                .build();

        SWIFTNESS_IV = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_four")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness IV")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 3, false)
                .build();

        SWIFTNESS_IV_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_four_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness IV Extended")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 600, 3, false)
                .build();

        SWIFTNESS_V = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_five")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness V")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 4, false)
                .build();

        SWIFTNESS_V_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_five_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness V Extended")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 600, 4, false)
                .build();

        SWIFTNESS_VI = new CustomPotionBuilder(new ItemStack(Material.POTION), "swiftness_six")
                .withDisplayName(ChatColor.AQUA + "Potion of Swiftness VI")
                .withPotionEffect(PotionEffectType.SPEED, 20 * 300, 5, false)
                .build();

        STRENGTH = new CustomPotionBuilder(new ItemStack(Material.POTION), "strength")
                .withDisplayName(ChatColor.AQUA + "Potion of Strength")
                .withPotionEffect(PotionEffectType.STRENGTH, 20 * 30, 0, false)
                .build();

        STRENGTH_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "strength_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Strength Extended")
                .withPotionEffect(PotionEffectType.STRENGTH, 20 * 90, 0, false)
                .build();

        STRENGTH_II = new CustomPotionBuilder(new ItemStack(Material.POTION), "strength_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Strength II")
                .withPotionEffect(PotionEffectType.STRENGTH, 20 * 30, 1, false)
                .build();

        STRENGTH_II_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "strength_two_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Strength II Extended")
                .withPotionEffect(PotionEffectType.STRENGTH, 20 * 90, 1, false)
                .build();

        STRENGTH_III = new CustomPotionBuilder(new ItemStack(Material.POTION), "strength_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Strength III")
                .withPotionEffect(PotionEffectType.STRENGTH, 20 * 30, 2, false)
                .build();

        REGENERATION = new CustomPotionBuilder(new ItemStack(Material.POTION), "regen")
                .withDisplayName(ChatColor.AQUA + "Potion of Regeneration")
                .withPotionEffect(PotionEffectType.REGENERATION, 20 * 10, 0, false)
                .build();

        REGENERATION_EXTENDED = new CustomPotionBuilder(new ItemStack(Material.POTION), "regen_extended")
                .withDisplayName(ChatColor.AQUA + "Potion of Regeneration Extended")
                .withPotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0, false)
                .build();

        REGENERATION_II = new CustomPotionBuilder(new ItemStack(Material.POTION), "regen_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Regeneration II")
                .withPotionEffect(PotionEffectType.REGENERATION, 20 * 10, 1, false)
                .build();

        ABSORPTION = new CustomPotionBuilder(new ItemStack(Material.POTION), "absorption")
                .withDisplayName(ChatColor.AQUA + "Potion of Absorption")
                .withPotionEffect(PotionEffectType.ABSORPTION, 20 * 120, 0, false)
                .withPotionColor(Color.YELLOW)
                .build();

        ABSORPTION_II = new CustomPotionBuilder(new ItemStack(Material.POTION), "absorption_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Absorption II")
                .withPotionEffect(PotionEffectType.ABSORPTION, 20 * 120, 1, false)
                .withPotionColor(Color.YELLOW)
                .build();

        ABSORPTION_III = new CustomPotionBuilder(new ItemStack(Material.POTION), "absorption_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Absorption III")
                .withPotionEffect(PotionEffectType.ABSORPTION, 20 * 120, 2, false)
                .withPotionColor(Color.YELLOW)
                .build();

        SLOWNESS = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "slowness")
                .withDisplayName(ChatColor.AQUA + "Potion of Slowness")
                .withPotionEffect(PotionEffectType.SLOWNESS, 20 * 20, 0, false)
                .build();

        SLOWNESS_II = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "slowness_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Slowness II")
                .withPotionEffect(PotionEffectType.SLOWNESS, 20 * 20, 1, false)
                .build();

        SLOWNESS_III = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "slowness_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Slowness III")
                .withPotionEffect(PotionEffectType.SLOWNESS, 20 * 20, 2, false)
                .build();

        WEAKNESS = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "weakness")
                .withDisplayName(ChatColor.AQUA + "Potion of Weakness")
                .withPotionEffect(PotionEffectType.WEAKNESS, 20 * 20, 0, false)
                .build();

        WEAKNESS_II = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "weakness_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Weakness II")
                .withPotionEffect(PotionEffectType.WEAKNESS, 20 * 20, 1, false)
                .build();

        WEAKNESS_III = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "weakness_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Weakness III")
                .withPotionEffect(PotionEffectType.WEAKNESS, 20 * 20, 2, false)
                .build();

        POISON = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "poison")
                .withDisplayName(ChatColor.AQUA + "Potion of Poison")
                .withPotionEffect(PotionEffectType.POISON, 20 * 15, 0, false)
                .build();

        POISON_II = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "poison_two")
                .withDisplayName(ChatColor.AQUA + "Potion of Poison II")
                .withPotionEffect(PotionEffectType.POISON, 20 * 15, 1, false)
                .build();

        POISON_III = new CustomPotionBuilder(new ItemStack(Material.SPLASH_POTION), "poison_three")
                .withDisplayName(ChatColor.AQUA + "Potion of Poison III")
                .withPotionEffect(PotionEffectType.POISON, 20 * 15, 2, false)
                .build();
    }

    public CustomItem(ItemStack itemStack, String itemName) {
        this.itemStack = itemStack;
        this.itemName = itemName;
    }

    public boolean compareItem(ItemStack item) {
        if(item == null || item.getType() == Material.AIR)
            return false;
        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ITEM, PersistentDataType.STRING))
            return false;

        return meta.getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ITEM, PersistentDataType.STRING).equals(itemName);
    }

    public static void register() {
        // === Resources ===
        Registry.CUSTOM_ITEM.register(SCRIPTING_PAPER.getItemName(), SCRIPTING_PAPER);
        Registry.CUSTOM_ITEM.register(SCRIPTING_TOME.getItemName(), SCRIPTING_TOME);
        Registry.CUSTOM_ITEM.register(LOG.getItemName(), LOG);
        Registry.CUSTOM_ITEM.register(TIN.getItemName(), TIN);
        Registry.CUSTOM_ITEM.register(BRONZE_INGOT.getItemName(), BRONZE_INGOT);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_INGOT.getItemName(), MISTSTEEL_INGOT);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_NUGGET.getItemName(), SUN_GOLD_NUGGET);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_INGOT.getItemName(), SUN_GOLD_INGOT);
        Registry.CUSTOM_ITEM.register(MITHRIL.getItemName(), MITHRIL);
        Registry.CUSTOM_ITEM.register(ORICHALCUM.getItemName(), ORICHALCUM);
        Registry.CUSTOM_ITEM.register(SOLARIUM.getItemName(), SOLARIUM);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND.getItemName(), CORRUPTED_DIAMOND);
        Registry.CUSTOM_ITEM.register(ADAMANTITE_INGOT.getItemName(), ADAMANTITE_INGOT);
        // === Armor ===
        Registry.CUSTOM_ITEM.register(HARD_LEATHER_HELMET.getItemName(), HARD_LEATHER_HELMET);
        Registry.CUSTOM_ITEM.register(HARD_LEATHER_CHESTPLATE.getItemName(), HARD_LEATHER_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(HARD_LEATHER_LEGGIGNS.getItemName(), HARD_LEATHER_LEGGIGNS);
        Registry.CUSTOM_ITEM.register(HARD_LEATHER_BOOTS.getItemName(), HARD_LEATHER_BOOTS);
        Registry.CUSTOM_ITEM.register(BRONZE_HELMET.getItemName(), BRONZE_HELMET);
        Registry.CUSTOM_ITEM.register(BRONZE_CHESTPLATE.getItemName(), BRONZE_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(BRONZE_LEGGINGS.getItemName(), BRONZE_LEGGINGS);
        Registry.CUSTOM_ITEM.register(BRONZE_BOOTS.getItemName(), BRONZE_BOOTS);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_HELMET.getItemName(), MISTSTEEL_HELMET);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_CHESTPLATE.getItemName(), MISTSTEEL_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_LEGGINGS.getItemName(), MISTSTEEL_LEGGINGS);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_BOOTS.getItemName(), MISTSTEEL_BOOTS);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_HELMET.getItemName(), SUN_GOLD_HELMET);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_CHESTPLATE.getItemName(), SUN_GOLD_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_LEGGINGS.getItemName(), SUN_GOLD_LEGGINGS);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_BOOTS.getItemName(), SUN_GOLD_BOOTS);
        Registry.CUSTOM_ITEM.register(ORICHALCUM_HELMET.getItemName(), ORICHALCUM_HELMET);
        Registry.CUSTOM_ITEM.register(ORICHALCUM_CHESTPLATE.getItemName(), ORICHALCUM_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(ORICHALCUM_LEGGINGS.getItemName(), ORICHALCUM_LEGGINGS);
        Registry.CUSTOM_ITEM.register(ORICHALCUM_BOOTS.getItemName(), ORICHALCUM_BOOTS);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_HELMET.getItemName(), CORRUPTED_DIAMOND_HELMET);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_CHESTPLATE.getItemName(), CORRUPTED_DIAMOND_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_LEGGINGS.getItemName(), CORRUPTED_DIAMOND_LEGGINGS);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_BOOTS.getItemName(), CORRUPTED_DIAMOND_BOOTS);
        Registry.CUSTOM_ITEM.register(AMETHYST_HELMET.getItemName(), AMETHYST_HELMET);
        Registry.CUSTOM_ITEM.register(AMETHYST_CHESTPLATE.getItemName(), AMETHYST_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(AMETHYST_LEGGINGS.getItemName(), AMETHYST_LEGGINGS);
        Registry.CUSTOM_ITEM.register(AMETHYST_BOOTS.getItemName(), AMETHYST_BOOTS);
        Registry.CUSTOM_ITEM.register(LAPIS_HELMET.getItemName(), LAPIS_HELMET);
        Registry.CUSTOM_ITEM.register(LAPIS_CHESTPLATE.getItemName(), LAPIS_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(LAPIS_LEGGINGS.getItemName(), LAPIS_LEGGINGS);
        Registry.CUSTOM_ITEM.register(LAPIS_BOOTS.getItemName(), LAPIS_BOOTS);
        Registry.CUSTOM_ITEM.register(MITHRIL_HELMET.getItemName(), MITHRIL_HELMET);
        Registry.CUSTOM_ITEM.register(MITHRIL_CHESTPLATE.getItemName(), MITHRIL_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(MITHRIL_LEGGINGS.getItemName(), MITHRIL_LEGGINGS);
        Registry.CUSTOM_ITEM.register(MITHRIL_BOOTS.getItemName(), MITHRIL_BOOTS);
        Registry.CUSTOM_ITEM.register(SOLARIUM_HELMET.getItemName(), SOLARIUM_HELMET);
        Registry.CUSTOM_ITEM.register(SOLARIUM_CHESTPLATE.getItemName(), SOLARIUM_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(SOLARIUM_LEGGINGS.getItemName(), SOLARIUM_LEGGINGS);
        Registry.CUSTOM_ITEM.register(SOLARIUM_BOOTS.getItemName(), SOLARIUM_BOOTS);
        Registry.CUSTOM_ITEM.register(ADAMANTITE_HELMET.getItemName(), ADAMANTITE_HELMET);
        Registry.CUSTOM_ITEM.register(ADAMANTITE_CHESTPLATE.getItemName(), ADAMANTITE_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(ADAMANTITE_LEGGINGS.getItemName(), ADAMANTITE_LEGGINGS);
        Registry.CUSTOM_ITEM.register(ADAMANTITE_BOOTS.getItemName(), ADAMANTITE_BOOTS);
        Registry.CUSTOM_ITEM.register(WORN_HELMET.getItemName(), WORN_HELMET);
        Registry.CUSTOM_ITEM.register(TATTERED_CHESTPLATE.getItemName(), TATTERED_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(LEGIONARY_HELMET.getItemName(), LEGIONARY_HELMET);
        Registry.CUSTOM_ITEM.register(LEGIONARY_CHESTPLATE.getItemName(), LEGIONARY_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(FORTIFIED_HELMET.getItemName(), FORTIFIED_HELMET);
        Registry.CUSTOM_ITEM.register(FORTIFIED_CHESTPLATE.getItemName(), FORTIFIED_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(FORTIFIED_LEGGINGS.getItemName(), FORTIFIED_LEGGINGS);
        Registry.CUSTOM_ITEM.register(FORTIFIED_BOOTS.getItemName(), FORTIFIED_BOOTS);
        Registry.CUSTOM_ITEM.register(WRAITH_HELMET.getItemName(), WRAITH_HELMET);
        Registry.CUSTOM_ITEM.register(WRAITH_CHESTPLATE.getItemName(), WRAITH_CHESTPLATE);
        Registry.CUSTOM_ITEM.register(WRAITH_LEGGINGS.getItemName(), WRAITH_LEGGINGS);
        Registry.CUSTOM_ITEM.register(WRAITH_BOOTS.getItemName(), WRAITH_BOOTS);
        Registry.CUSTOM_ITEM.register(MAZE_RUNNER_BOOTS.getItemName(), MAZE_RUNNER_BOOTS);
        // === Weapons ===
        Registry.CUSTOM_ITEM.register(WOODEN_SHORT_SWORD.getItemName(), WOODEN_SHORT_SWORD);
        Registry.CUSTOM_ITEM.register(WOODEN_HATCHET.getItemName(), WOODEN_HATCHET);
        Registry.CUSTOM_ITEM.register(WOODEN_SPADE.getItemName(), WOODEN_SPADE);
        Registry.CUSTOM_ITEM.register(WOODEN_SCYTHE.getItemName(), WOODEN_SCYTHE);
        Registry.CUSTOM_ITEM.register(WORN_WOODEN_PICKAXE.getItemName(), WORN_WOODEN_PICKAXE);
        Registry.CUSTOM_ITEM.register(BASTARD_SWORD.getItemName(), BASTARD_SWORD);
        Registry.CUSTOM_ITEM.register(BATTLE_AXE.getItemName(), BATTLE_AXE);
        Registry.CUSTOM_ITEM.register(MACE.getItemName(), MACE);
        Registry.CUSTOM_ITEM.register(COBBLESTONE_PICKAXE.getItemName(), COBBLESTONE_PICKAXE);
        Registry.CUSTOM_ITEM.register(BRONZE_SWORD.getItemName(), BRONZE_SWORD);
        Registry.CUSTOM_ITEM.register(BRONZE_AXE.getItemName(), BRONZE_AXE);
        Registry.CUSTOM_ITEM.register(BRONZE_MACE.getItemName(), BRONZE_MACE);
        Registry.CUSTOM_ITEM.register(BRONZE_PICKAXE.getItemName(), BRONZE_PICKAXE);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_SWORD.getItemName(), MISTSTEEL_SWORD);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_AXE.getItemName(), MISTSTEEL_AXE);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_MACE.getItemName(), MISTSTEEL_MACE);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_SCYTHE.getItemName(), MISTSTEEL_SCYTHE);
        Registry.CUSTOM_ITEM.register(MISTSTEEL_PICKAXE.getItemName(), MISTSTEEL_PICKAXE);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_SWORD.getItemName(), SUN_GOLD_SWORD);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_AXE.getItemName(), SUN_GOLD_AXE);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_MACE.getItemName(), SUN_GOLD_MACE);
        Registry.CUSTOM_ITEM.register(SUN_GOLD_PICKAXE.getItemName(), SUN_GOLD_PICKAXE);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_SWORD.getItemName(), CORRUPTED_DIAMOND_SWORD);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_AXE.getItemName(), CORRUPTED_DIAMOND_AXE);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_MACE.getItemName(), CORRUPTED_DIAMOND_MACE);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_SCYTHE.getItemName(), CORRUPTED_DIAMOND_SCYTHE);
        Registry.CUSTOM_ITEM.register(CORRUPTED_DIAMOND_PICKAXE.getItemName(), CORRUPTED_DIAMOND_PICKAXE);
        Registry.CUSTOM_ITEM.register(ABYSSAL_EDGE.getItemName(), ABYSSAL_EDGE);
        Registry.CUSTOM_ITEM.register(ABYSSAL_CLEAVER.getItemName(), ABYSSAL_CLEAVER);
        Registry.CUSTOM_ITEM.register(ABYSSAL_GRAVEDIGGER.getItemName(), ABYSSAL_GRAVEDIGGER);
        Registry.CUSTOM_ITEM.register(ABYSSAL_REAPER.getItemName(), ABYSSAL_REAPER);
        Registry.CUSTOM_ITEM.register(SPLINTERED_SWORD.getItemName(), SPLINTERED_SWORD);
        Registry.CUSTOM_ITEM.register(ARISEN_REVENANT_SWORD.getItemName(), ARISEN_REVENANT_SWORD);
        Registry.CUSTOM_ITEM.register(GRAVEDIGGER.getItemName(), GRAVEDIGGER);
        Registry.CUSTOM_ITEM.register(WITHERED_SCYTHE.getItemName(), WITHERED_SCYTHE);
        Registry.CUSTOM_ITEM.register(FORSAKEN_SCYTHE.getItemName(), FORSAKEN_SCYTHE);
        Registry.CUSTOM_ITEM.register(SHORT_BOW.getItemName(), SHORT_BOW);
        Registry.CUSTOM_ITEM.register(DECAYED_BOW.getItemName(), DECAYED_BOW);
        Registry.CUSTOM_ITEM.register(LEGIONARY_BOW.getItemName(), LEGIONARY_BOW);
        Registry.CUSTOM_ITEM.register(WORM_WOOD_BOW.getItemName(), WORM_WOOD_BOW);
        Registry.CUSTOM_ITEM.register(EXECUTIONER_CROSSBOW.getItemName(), EXECUTIONER_CROSSBOW);
        // === Potions ===
        Registry.CUSTOM_ITEM.register(SWIFTNESS.getItemName(), SWIFTNESS);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_EXTENDED.getItemName(), SWIFTNESS_EXTENDED);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_II.getItemName(), SWIFTNESS_II);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_II_EXTENDED.getItemName(), SWIFTNESS_II_EXTENDED);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_III.getItemName(), SWIFTNESS_III);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_III_EXTENDED.getItemName(), SWIFTNESS_III_EXTENDED);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_IV.getItemName(), SWIFTNESS_IV);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_IV_EXTENDED.getItemName(), SWIFTNESS_IV_EXTENDED);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_V.getItemName(), SWIFTNESS_V);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_V_EXTENDED.getItemName(), SWIFTNESS_V_EXTENDED);
        Registry.CUSTOM_ITEM.register(SWIFTNESS_VI.getItemName(), SWIFTNESS_VI);
        Registry.CUSTOM_ITEM.register(STRENGTH.getItemName(), STRENGTH);
        Registry.CUSTOM_ITEM.register(STRENGTH_EXTENDED.getItemName(), STRENGTH_EXTENDED);
        Registry.CUSTOM_ITEM.register(STRENGTH_II.getItemName(), STRENGTH_II);
        Registry.CUSTOM_ITEM.register(STRENGTH_II_EXTENDED.getItemName(), STRENGTH_II_EXTENDED);
        Registry.CUSTOM_ITEM.register(STRENGTH_III.getItemName(), STRENGTH_III);
        Registry.CUSTOM_ITEM.register(REGENERATION.getItemName(), REGENERATION);
        Registry.CUSTOM_ITEM.register(REGENERATION_EXTENDED.getItemName(), REGENERATION_EXTENDED);
        Registry.CUSTOM_ITEM.register(REGENERATION_II.getItemName(), REGENERATION_II);
        Registry.CUSTOM_ITEM.register(ABSORPTION.getItemName(), ABSORPTION);
        Registry.CUSTOM_ITEM.register(ABSORPTION_II.getItemName(), ABSORPTION_II);
        Registry.CUSTOM_ITEM.register(ABSORPTION_III.getItemName(), ABSORPTION_III);
        Registry.CUSTOM_ITEM.register(SLOWNESS.getItemName(), SLOWNESS);
        Registry.CUSTOM_ITEM.register(SLOWNESS_II.getItemName(), SLOWNESS_II);
        Registry.CUSTOM_ITEM.register(SLOWNESS_III.getItemName(), SLOWNESS_III);
        Registry.CUSTOM_ITEM.register(WEAKNESS.getItemName(), WEAKNESS);
        Registry.CUSTOM_ITEM.register(WEAKNESS_II.getItemName(), WEAKNESS_II);
        Registry.CUSTOM_ITEM.register(WEAKNESS_III.getItemName(), WEAKNESS_III);
        Registry.CUSTOM_ITEM.register(POISON.getItemName(), POISON);
        Registry.CUSTOM_ITEM.register(POISON_II.getItemName(), POISON_II);
        Registry.CUSTOM_ITEM.register(POISON_III.getItemName(), POISON_III);
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    public ItemStack getItemStack(int amount) {
        ItemStack clone = itemStack.clone();
        if(amount > 0)
            clone.setAmount(amount);

        return clone;
    }

    public String getItemName() {
        return itemName;
    }
}
