package me.brody.mazesurvival.loot.trade;

import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;
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
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(1),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(new ItemStack(Material.LEATHER), ItemGrade.IRON);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
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
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 3),ItemGrade.GOLD));
            ItemStack result = CustomItem.STRENGTH.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 1),ItemGrade.TITANIUM));
            ItemStack result = CustomItem.STRENGTH_EXTENDED.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 2),ItemGrade.GOLD));
            ItemStack result = CustomItem.ABSORPTION.getItemStack();
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.COMPOSTER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.TITANIUM));
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
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(2),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.CAMPFIRE);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.CLOCK);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM));
            STRONGHOLD_TRADE_TABLE.add(new MusicDiscTradeEntry(ingredients, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE, 1), ItemGrade.TITANIUM));
            STRONGHOLD_TRADE_TABLE.add(new BannerPatternTradeEntry(ingredients, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.JUNGLE_LOG);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.JUNGLE_SAPLING);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.CHERRY_LOG);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT, 2), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.CHERRY_SAPLING);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT, 3), ItemGrade.IRON));
            ItemStack result = new ItemStack(Material.GRASS_BLOCK);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.PODZOL);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.WHEAT), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.CLAY);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.APPLE), ItemGrade.TITANIUM));
            ItemStack result = new ItemStack(Material.BELL);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.CARROT), ItemGrade.GOLD));
            ItemStack result = new ItemStack(Material.SUNFLOWER);
            STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }

        DESERT_TRADE_TABLE = new TradeTable();
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 2), ItemGrade.GOLD));
            ItemStack result = CustomItem.BRONZE_INGOT.getItemStack();
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }
        {
            List<ItemStack> ingredients = List.of(
                    ItemGradeUtils.createGradedItem(CustomItem.LOG.getItemStack(4),ItemGrade.TITANIUM),
                    ItemGradeUtils.createGradedItem(new ItemStack(Material.POTATO, 1), ItemGrade.TITANIUM));
            ItemStack result = ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(), ItemGrade.IRON);
            DESERT_TRADE_TABLE.add(new SimpleTradeEntry(ingredients, result, DEFAULT_MAX_USES), 10);
        }

        SWAMP_TRADE_TABLE = new TradeTable();

        NETHER_TRADE_TABLE = new TradeTable();

        DEEP_DARK_TRADE_TABLE = new TradeTable();

    }

    private TradeTable() {}

}
