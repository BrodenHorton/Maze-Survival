package me.brody.mazesurvival.loot.trade;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class TradeTable extends WeightedList<Supplier<MerchantRecipe>> {
    public static final int DEFAULT_MAX_USES = 20;
    public static final TradeTable STRONGHOLD_TRADE_TABLE;
    public static final TradeTable DESERT_TRADE_TABLE;
    public static final TradeTable SWAMP_TRADE_TABLE;
    public static final TradeTable NETHER_TRADE_TABLE;
    public static final TradeTable DEEP_DARK_TRADE_TABLE;

    static {
        STRONGHOLD_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.AMETHYST_SHARD);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 1), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(new ItemStack(Material.AMETHYST_SHARD), ItemGrade.IRON);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.LEATHER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(new ItemStack(Material.LEATHER), ItemGrade.IRON);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.GOLD),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2),ItemGrade.GOLD));
            ItemStack result = CustomItem.SWIFTNESS_II.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 1), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2),ItemGrade.GOLD));
            ItemStack result = CustomItem.SWIFTNESS_II_EXTENDED.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 1), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 1),ItemGrade.TITANIUM));
            ItemStack result = CustomItem.SWIFTNESS_III.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 3),ItemGrade.GOLD);
            ItemStack result = CustomItem.STRENGTH.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 1),ItemGrade.TITANIUM);
            ItemStack result = CustomItem.STRENGTH_EXTENDED.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 2),ItemGrade.GOLD);
            ItemStack result = CustomItem.ABSORPTION.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.LOOM);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.BLAST_FURNACE);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.SMOKER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.JUKEBOX);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.CLOCK);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient =ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM);
            STRONGHOLD_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient =ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM);
            STRONGHOLD_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.JUNGLE_LOG);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.JUNGLE_SAPLING);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.CHERRY_LOG);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.CHERRY_SAPLING);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.IRON);
            ItemStack result = new ItemStack(Material.GRASS_BLOCK);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.PODZOL);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.CLAY);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.BELL);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.SUNFLOWER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }

        DESERT_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.BRONZE_INGOT.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(), ItemGrade.IRON);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 2), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.LAPIS_LAZULI);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 3), ItemGrade.TITANIUM);
            ItemStack result = ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.IRON);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 3), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 3), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.SCRIPTING_PAPER.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            Map<MazeEnchantment, Integer> enchantments = new HashMap<>();
            enchantments.put(MazeEnchantment.PROTECTION, 1);
            enchantments.put(MazeEnchantment.THORNS, 1);
            enchantments.put(MazeEnchantment.SOUL_SPEED, 1);
            enchantments.put(MazeEnchantment.VITALITY, 1);
            enchantments.put(MazeEnchantment.SHARPNESS, 1);
            enchantments.put(MazeEnchantment.SMITE, 1);
            enchantments.put(MazeEnchantment.BANE_OF_ARTHROPODS, 1);
            enchantments.put(MazeEnchantment.FIRE_ASPECT, 1);
            enchantments.put(MazeEnchantment.KNOCKBACK, 1);
            enchantments.put(MazeEnchantment.LOOTING, 1);
            enchantments.put(MazeEnchantment.CRUSADER, 1);
            enchantments.put(MazeEnchantment.POWER, 1);
            enchantments.put(MazeEnchantment.PUNCH, 1);
            enchantments.put(MazeEnchantment.LINGERING_SHOT, 1);
            enchantments.put(MazeEnchantment.UNBREAKING, 1);
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 3), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 3), ItemGrade.TITANIUM));
            DESERT_TRADE_TABLE.add(new EnchantedScriptTradeEntry(ingredients, enchantments, false, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 2), ItemGrade.GOLD),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.SWIFTNESS_II_EXTENDED.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 1), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.SWIFTNESS_III.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 1), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.STRENGTH_EXTENDED.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 1), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.ABSORPTION.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.LOOM);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.BLAST_FURNACE);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.SMOKER);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.JUKEBOX);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 2), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.CLOCK);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 2), ItemGrade.TITANIUM);
            DESERT_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.SUGAR_CANE, 2), ItemGrade.TITANIUM);
            DESERT_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 3), ItemGrade.GOLD),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.SUGAR_CANE);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.ACACIA_LOG);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.ACACIA_SAPLING);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 3), ItemGrade.IRON);
            ItemStack result = new ItemStack(Material.CANDLE);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.CACTUS, 3), ItemGrade.IRON);
            ItemStack result = new ItemStack(Material.AZURE_BLUET);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }

        SWAMP_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.MISTSTEEL_INGOT.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.IRON);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 2), ItemGrade.GOLD);
            ItemStack result = CustomItem.ORICHALCUM.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.TITANIUM);
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.ORICHALCUM.getItemStack(), ItemGrade.IRON);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 3), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.SCRIPTING_PAPER.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            Map<MazeEnchantment, Integer> enchantments = new HashMap<>();
            enchantments.put(MazeEnchantment.PROTECTION, 2);
            enchantments.put(MazeEnchantment.THORNS, 2);
            enchantments.put(MazeEnchantment.SOUL_SPEED, 1);
            enchantments.put(MazeEnchantment.VITALITY, 1);
            enchantments.put(MazeEnchantment.SHARPNESS, 1);
            enchantments.put(MazeEnchantment.SMITE, 2);
            enchantments.put(MazeEnchantment.BANE_OF_ARTHROPODS, 2);
            enchantments.put(MazeEnchantment.FIRE_ASPECT, 1);
            enchantments.put(MazeEnchantment.KNOCKBACK, 2);
            enchantments.put(MazeEnchantment.LOOTING, 1);
            enchantments.put(MazeEnchantment.CRUSADER, 2);
            enchantments.put(MazeEnchantment.POWER, 1);
            enchantments.put(MazeEnchantment.PUNCH, 1);
            enchantments.put(MazeEnchantment.LINGERING_SHOT, 1);
            enchantments.put(MazeEnchantment.UNBREAKING, 1);
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 3), ItemGrade.TITANIUM));
            SWAMP_TRADE_TABLE.add(new EnchantedScriptTradeEntry(ingredients, enchantments, false, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2),ItemGrade.GOLD),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.SWIFTNESS_III.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.SWIFTNESS_III_EXTENDED.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.STRENGTH_II.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB, 1), ItemGrade.TITANIUM));
            ItemStack result = CustomItem.ABSORPTION_II.getItemStack();
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.LOOM);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.BLAST_FURNACE);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 3), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.SMOKER);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.JUKEBOX);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.CLOCK);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.TITANIUM);
            SWAMP_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.TITANIUM);
            SWAMP_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 1), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.JUNGLE_SAPLING);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.MYCELIUM);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.BLUE_ORCHID);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.ALLIUM);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.RED_TULIP);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.ORANGE_TULIP);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.WHITE_TULIP);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.PINK_TULIP);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.OXEYE_DAISY);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.CORNFLOWER);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.BEETROOT, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.LILY_OF_THE_VALLEY);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.SEA_LANTERN);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.OCHRE_FROGLIGHT);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.VERDANT_FROGLIGHT);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.HONEYCOMB), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.VERDANT_FROGLIGHT);
            SWAMP_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }

        NETHER_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD));
            ItemStack result = CustomItem.SUN_GOLD_INGOT.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD));
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_INGOT.getItemStack(), ItemGrade.IRON);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.MITHRIL.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 8), ItemGrade.TITANIUM);
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.MITHRIL.getItemStack(), ItemGrade.IRON);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.SCRIPTING_PAPER.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            Map<MazeEnchantment, Integer> enchantments = new HashMap<>();
            enchantments.put(MazeEnchantment.PROTECTION, 2);
            enchantments.put(MazeEnchantment.THORNS, 2);
            enchantments.put(MazeEnchantment.SOUL_SPEED, 2);
            enchantments.put(MazeEnchantment.VITALITY, 2);
            enchantments.put(MazeEnchantment.SHARPNESS, 2);
            enchantments.put(MazeEnchantment.SMITE, 4);
            enchantments.put(MazeEnchantment.BANE_OF_ARTHROPODS, 4);
            enchantments.put(MazeEnchantment.FIRE_ASPECT, 2);
            enchantments.put(MazeEnchantment.KNOCKBACK, 2);
            enchantments.put(MazeEnchantment.LOOTING, 2);
            enchantments.put(MazeEnchantment.CRUSADER, 2);
            enchantments.put(MazeEnchantment.POWER, 2);
            enchantments.put(MazeEnchantment.PUNCH, 2);
            enchantments.put(MazeEnchantment.LINGERING_SHOT, 2);
            enchantments.put(MazeEnchantment.UNBREAKING, 2);
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 8), ItemGrade.TITANIUM);
            NETHER_TRADE_TABLE.add(new EnchantedScriptTradeEntry(ingredient, enchantments, false, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 12), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.SCRIPTING_TOME.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.SWIFTNESS_III.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.SWIFTNESS_III_EXTENDED.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.SWIFTNESS_IV.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.STRENGTH_II.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.STRENGTH_II_EXTENDED.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.ABSORPTION_II.getItemStack();
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.LOOM);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.BLAST_FURNACE);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.SMOKER);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.JUKEBOX);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM);
            ItemStack result = new ItemStack(Material.CLOCK);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM);
            NETHER_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 5), ItemGrade.TITANIUM);
            NETHER_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.SHROOMLIGHT);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.MAGMA_BLOCK);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.WEEPING_VINES);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.TWISTING_VINES);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.BLACKSTONE);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP, 3), ItemGrade.GOLD),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.NETHER_WART, 1), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.LAVA_BUCKET);
            NETHER_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }

        DEEP_DARK_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.CORRUPTED_DIAMOND_HELMET.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD));
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.CORRUPTED_DIAMOND.getItemStack(), ItemGrade.IRON);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.ADAMANTITE_INGOT.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.TITANIUM);
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.ADAMANTITE_INGOT.getItemStack(), ItemGrade.IRON);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.TITANIUM);
            ItemStack result = CustomItem.SCRIPTING_TOME.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            Map<MazeEnchantment, Integer> enchantments = new HashMap<>();
            enchantments.put(MazeEnchantment.PROTECTION, 3);
            enchantments.put(MazeEnchantment.THORNS, 3);
            enchantments.put(MazeEnchantment.SOUL_SPEED, 3);
            enchantments.put(MazeEnchantment.VITALITY, 3);
            enchantments.put(MazeEnchantment.SHARPNESS, 3);
            enchantments.put(MazeEnchantment.SMITE, 5);
            enchantments.put(MazeEnchantment.BANE_OF_ARTHROPODS, 5);
            enchantments.put(MazeEnchantment.FIRE_ASPECT, 2);
            enchantments.put(MazeEnchantment.KNOCKBACK, 3);
            enchantments.put(MazeEnchantment.LOOTING, 3);
            enchantments.put(MazeEnchantment.CRUSADER, 3);
            enchantments.put(MazeEnchantment.POWER, 3);
            enchantments.put(MazeEnchantment.PUNCH, 3);
            enchantments.put(MazeEnchantment.LINGERING_SHOT, 3);
            enchantments.put(MazeEnchantment.UNBREAKING, 3);
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.TITANIUM);
            DEEP_DARK_TRADE_TABLE.add(new EnchantedScriptTradeEntry(ingredient, enchantments, true, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD);
            ItemStack result = CustomItem.SWIFTNESS_III.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.SWIFTNESS_III_EXTENDED.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.SWIFTNESS_IV.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.STRENGTH_II_EXTENDED.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.STRENGTH_III.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD);
            ItemStack result = CustomItem.ABSORPTION_III.getItemStack();
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.LOOM);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.BLAST_FURNACE);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 3), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.SMOKER);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4), ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 5), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.JUKEBOX);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 5), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.CLOCK);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 10);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 5), ItemGrade.GOLD);
            DEEP_DARK_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 5), ItemGrade.GOLD);
            DEEP_DARK_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredient, DEFAULT_MAX_USES), 5);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.CRYING_OBSIDIAN);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.END_STONE);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.END_ROD);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
        {
            ItemStack ingredient = ItemGradeUtils.createGradedItem(new ItemStack(Material.INK_SAC, 2), ItemGrade.GOLD);
            ItemStack result = new ItemStack(Material.PURPUR_BLOCK);
            DEEP_DARK_TRADE_TABLE.add(new SimpleTradeEntry(ingredient, result, DEFAULT_MAX_USES), 20);
        }
    }

    private TradeTable() {}

}
