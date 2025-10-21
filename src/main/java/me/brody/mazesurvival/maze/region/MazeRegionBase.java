package me.brody.mazesurvival.maze.region;

import me.brody.mazesurvival.boss.BossFight;
import me.brody.mazesurvival.item.recipe.CustomRecipe;
import me.brody.mazesurvival.loot.chest.LootTable;
import me.brody.mazesurvival.loot.trade.TradeTable;
import me.brody.mazesurvival.maze.BlockPalette;
import me.brody.mazesurvival.mob.MobSpawnPool;
import me.brody.mazesurvival.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class MazeRegionBase {
    public static MazeRegionBase STRONGHOLD;
    public static MazeRegionBase DESERT;
    public static MazeRegionBase SWAMP;
    public static MazeRegionBase NETHER;
    public static MazeRegionBase BASTION;
    public static MazeRegionBase DEEP_DARK;

    private BlockPalette floorPalette;
    private BlockPalette wallPalette;
    private RegionDecoration decoration;
    private LootTable mazeChestLootTable;
    private TradeTable tradeTable;
    private MobSpawnPool dayMobs;
    private MobSpawnPool nightMobs;
    private List<CustomRecipe> accessibleRecipes;

    private MazeRegionBase(BlockPalette wallPalette, BlockPalette floorPalette, RegionDecoration decoration,
                           LootTable mazeChestLootTable, TradeTable tradeTable, MobSpawnPool dayMobs, MobSpawnPool nightMobs) {
        this(wallPalette, floorPalette, decoration, mazeChestLootTable, tradeTable, dayMobs, nightMobs, new ArrayList<>());
    }

    private MazeRegionBase(BlockPalette wallPalette, BlockPalette floorPalette, RegionDecoration decoration,
                           LootTable mazeChestLootTable, TradeTable tradeTable, MobSpawnPool dayMobs,
                           MobSpawnPool nightMobs, List<CustomRecipe> accessibleRecipes) {
        this.wallPalette = wallPalette;
        this.floorPalette = floorPalette;
        this.decoration = decoration;
        this.mazeChestLootTable = mazeChestLootTable;
        this.tradeTable = tradeTable;
        this.dayMobs = dayMobs;
        this.nightMobs = nightMobs;
        this.accessibleRecipes = accessibleRecipes;
    }

    public static void initMazeBases() {
        List<CustomRecipe> strongholdRecipes = List.of(
                Registry.CUSTOM_RECIPE.get("oak_to_log_iron_grade"),
                Registry.CUSTOM_RECIPE.get("cobblestone_iron_grade"),
                Registry.CUSTOM_RECIPE.get("leather_iron_grade"),
                Registry.CUSTOM_RECIPE.get("amethyst_shard_iron_grade"),
                Registry.CUSTOM_RECIPE.get("wheat_iron_grade"),
                Registry.CUSTOM_RECIPE.get("apple_iron_grade"),
                Registry.CUSTOM_RECIPE.get("carrot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("rotten_flesh_iron_grade"),
                Registry.CUSTOM_RECIPE.get("string_iron_grade"),
                Registry.CUSTOM_RECIPE.get("spider_eye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("slime_ball_iron_grade"),
                Registry.CUSTOM_RECIPE.get("gunpowder_iron_grade"),
                Registry.CUSTOM_RECIPE.get("wool"),
                Registry.CUSTOM_RECIPE.get("hard_leather_helmet"),
                Registry.CUSTOM_RECIPE.get("hard_leather_chestplate"),
                Registry.CUSTOM_RECIPE.get("hard_leather_leggings"),
                Registry.CUSTOM_RECIPE.get("hard_leather_boots"),
                Registry.CUSTOM_RECIPE.get("wooden_short_sword"),
                Registry.CUSTOM_RECIPE.get("wooden_hatchet"),
                Registry.CUSTOM_RECIPE.get("wooden_spade"),
                Registry.CUSTOM_RECIPE.get("wooden_scythe"),
                Registry.CUSTOM_RECIPE.get("worn_wooden_pickaxe"),
                Registry.CUSTOM_RECIPE.get("bastard_sword"),
                Registry.CUSTOM_RECIPE.get("battle_axe"),
                Registry.CUSTOM_RECIPE.get("mace"),
                Registry.CUSTOM_RECIPE.get("cobblestone_pickaxe"),
                Registry.CUSTOM_RECIPE.get("amethyst_helmet"),
                Registry.CUSTOM_RECIPE.get("amethyst_chestplate"),
                Registry.CUSTOM_RECIPE.get("amethyst_leggings"),
                Registry.CUSTOM_RECIPE.get("amethyst_boots")
        );
        STRONGHOLD = new MazeRegionBase(
                BlockPalette.STRONGHOLD_WALL,
                BlockPalette.STRONGHOLD_FLOOR,
                RegionDecoration.STRONGHOLD_DECORATIONS,
                LootTable.STRONGHOLD_LOOT_TABLE,
                TradeTable.STRONGHOLD_TRADE_TABLE,
                MobSpawnPool.STRONGHOLD_DAY_MOBS,
                MobSpawnPool.STRONGHOLD_NIGHT_MOBS,
                strongholdRecipes);

        List<CustomRecipe> desertRecipes = List.of(
                Registry.CUSTOM_RECIPE.get("bronze_ingot"),
                Registry.CUSTOM_RECIPE.get("bronze_ingot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("lapis_iron_grade"),
                Registry.CUSTOM_RECIPE.get("potato_iron_grade"),
                Registry.CUSTOM_RECIPE.get("cactus_iron_grade"),
                Registry.CUSTOM_RECIPE.get("sugar_cane_iron_grade"),
                Registry.CUSTOM_RECIPE.get("sugar_iron_grade"),
                Registry.CUSTOM_RECIPE.get("feather_iron_grade"),
                Registry.CUSTOM_RECIPE.get("blaze_rod_iron_grade"),
                Registry.CUSTOM_RECIPE.get("breeze_rod_iron_grade"),
                Registry.CUSTOM_RECIPE.get("bronze_helmet"),
                Registry.CUSTOM_RECIPE.get("bronze_chestplate"),
                Registry.CUSTOM_RECIPE.get("bronze_leggings"),
                Registry.CUSTOM_RECIPE.get("bronze_boots"),
                Registry.CUSTOM_RECIPE.get("bronze_sword"),
                Registry.CUSTOM_RECIPE.get("bronze_axe"),
                Registry.CUSTOM_RECIPE.get("bronze_mace"),
                Registry.CUSTOM_RECIPE.get("bronze_pickaxe"),
                Registry.CUSTOM_RECIPE.get("lapis_helmet"),
                Registry.CUSTOM_RECIPE.get("lapis_chestplate"),
                Registry.CUSTOM_RECIPE.get("lapis_leggings"),
                Registry.CUSTOM_RECIPE.get("lapis_boots")
        );
        DESERT = new MazeRegionBase(
                BlockPalette.DESERT_WALL,
                BlockPalette.DESERT_FLOOR,
                RegionDecoration.DESERT_DECORATIONS,
                LootTable.DESERT_LOOT_TABLE,
                TradeTable.DESERT_TRADE_TABLE,
                MobSpawnPool.DESERT_DAY_MOBS,
                MobSpawnPool.DESERT_NIGHT_MOBS,
                desertRecipes);

        List<CustomRecipe> swampRecipes = List.of(
                Registry.CUSTOM_RECIPE.get("miststeel_ingot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("orichalcum_iron_grade"),
                Registry.CUSTOM_RECIPE.get("beetroot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("brown_mushroom_iron_grade"),
                Registry.CUSTOM_RECIPE.get("red_mushroom_iron_grade"),
                Registry.CUSTOM_RECIPE.get("honeycomb_iron_grade"),
                Registry.CUSTOM_RECIPE.get("red_dye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("blue_dye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("yellow_dye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("purple_dye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("miststeel_helmet"),
                Registry.CUSTOM_RECIPE.get("miststeel_chestplate"),
                Registry.CUSTOM_RECIPE.get("miststeel_leggings"),
                Registry.CUSTOM_RECIPE.get("miststeel_boots"),
                Registry.CUSTOM_RECIPE.get("miststeel_sword"),
                Registry.CUSTOM_RECIPE.get("miststeel_axe"),
                Registry.CUSTOM_RECIPE.get("miststeel_mace"),
                Registry.CUSTOM_RECIPE.get("miststeel_scythe"),
                Registry.CUSTOM_RECIPE.get("miststeel_pickaxe"),
                Registry.CUSTOM_RECIPE.get("orichalcum_helmet"),
                Registry.CUSTOM_RECIPE.get("orichalcum_chestplate"),
                Registry.CUSTOM_RECIPE.get("orichalcum_leggings"),
                Registry.CUSTOM_RECIPE.get("orichalcum_boots"),
                Registry.CUSTOM_RECIPE.get("enchanting_table"),
                Registry.CUSTOM_RECIPE.get("brewing_stand")
        );
        SWAMP = new MazeRegionBase(
                BlockPalette.SWAMP_WALL,
                BlockPalette.SWAMP_FLOOR,
                RegionDecoration.SWAMP_DECORATIONS,
                LootTable.SWAMP_LOOT_TABLE,
                TradeTable.SWAMP_TRADE_TABLE,
                MobSpawnPool.SWAMP_DAY_MOBS,
                MobSpawnPool.SWAMP_NIGHT_MOBS,
                swampRecipes);

        List<CustomRecipe> netherRecipes = List.of(
                Registry.CUSTOM_RECIPE.get("sun_gold_nugget_iron_grade"),
                Registry.CUSTOM_RECIPE.get("sun_gold_ingot"),
                Registry.CUSTOM_RECIPE.get("sun_gold_ingot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("mithril"),
                Registry.CUSTOM_RECIPE.get("mithril_iron_grade"),
                Registry.CUSTOM_RECIPE.get("nether_wart_iron_grade"),
                Registry.CUSTOM_RECIPE.get("porkchop_iron_grade"),
                Registry.CUSTOM_RECIPE.get("glowstone_dust_iron_grade"),
                Registry.CUSTOM_RECIPE.get("soul_sand_iron_grade"),
                Registry.CUSTOM_RECIPE.get("ghast_tear_iron_grade"),
                Registry.CUSTOM_RECIPE.get("glowstone"),
                Registry.CUSTOM_RECIPE.get("sun_gold_helmet"),
                Registry.CUSTOM_RECIPE.get("sun_gold_chestplate"),
                Registry.CUSTOM_RECIPE.get("sun_gold_leggings"),
                Registry.CUSTOM_RECIPE.get("sun_gold_boots"),
                Registry.CUSTOM_RECIPE.get("sun_gold_sword"),
                Registry.CUSTOM_RECIPE.get("sun_gold_axe"),
                Registry.CUSTOM_RECIPE.get("sun_gold_mace"),
                Registry.CUSTOM_RECIPE.get("sun_gold_pickaxe"),
                Registry.CUSTOM_RECIPE.get("mithril_helmet"),
                Registry.CUSTOM_RECIPE.get("mithril_chestplate"),
                Registry.CUSTOM_RECIPE.get("mithril_leggings"),
                Registry.CUSTOM_RECIPE.get("mithril_boots")
        );
        NETHER = new MazeRegionBase(
                BlockPalette.NETHER_WALL,
                BlockPalette.NETHER_FLOOR,
                RegionDecoration.NETHER_DECORATIONS,
                LootTable.NETHER_LOOT_TABLE,
                TradeTable.NETHER_TRADE_TABLE,
                MobSpawnPool.NETHER_DAY_MOBS,
                MobSpawnPool.NETHER_NIGHT_MOBS,
                netherRecipes);

        BASTION = new MazeRegionBase(
                BlockPalette.BASTION_WALL,
                BlockPalette.BASTION_FLOOR,
                RegionDecoration.BASTION_DECORATIONS,
                LootTable.STRONGHOLD_LOOT_TABLE,
                TradeTable.STRONGHOLD_TRADE_TABLE,
                MobSpawnPool.BASTION_DAY_MOBS,
                MobSpawnPool.BASTION_NIGHT_MOBS);

        List<CustomRecipe> deepDarkRecipes = List.of(
                Registry.CUSTOM_RECIPE.get("corrupted_diamond"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_iron_grade"),
                Registry.CUSTOM_RECIPE.get("adamantite_ingot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("echo_shard_iron_grade"),
                Registry.CUSTOM_RECIPE.get("glow_ink_sac_iron_grade"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_helmet"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_chestplate"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_leggings"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_boots"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_sword"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_axe"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_mace"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_scythe"),
                Registry.CUSTOM_RECIPE.get("corrupted_diamond_pickaxe"),
                Registry.CUSTOM_RECIPE.get("adamantite_helmet"),
                Registry.CUSTOM_RECIPE.get("adamantite_chestplate"),
                Registry.CUSTOM_RECIPE.get("adamantite_leggings"),
                Registry.CUSTOM_RECIPE.get("adamantite_boots"),
                Registry.CUSTOM_RECIPE.get("abyssal_edge"),
                Registry.CUSTOM_RECIPE.get("abyssal_cleaver"),
                Registry.CUSTOM_RECIPE.get("abyssal_gravedigger"),
                Registry.CUSTOM_RECIPE.get("abyssal_reaper")
        );
        DEEP_DARK = new MazeRegionBase(
                BlockPalette.DEEP_DARK_WALL,
                BlockPalette.DEEP_DARK_FLOOR,
                RegionDecoration.DEEP_DARK_DECORATIONS,
                LootTable.DEEP_DARK_LOOT_TABLE,
                TradeTable.DEEP_DARK_TRADE_TABLE,
                MobSpawnPool.DEEP_DARK_DAY_MOBS,
                MobSpawnPool.DEEP_DARK_NIGHT_MOBS,
                deepDarkRecipes);
    }

    public BlockPalette getFloorPalette() {
        return floorPalette;
    }

    public void setFloorPalette(BlockPalette floorPalette) {
        this.floorPalette = floorPalette;
    }

    public BlockPalette getWallPalette() {
        return wallPalette;
    }

    public void setWallPalette(BlockPalette wallPalette) {
        this.wallPalette = wallPalette;
    }

    public RegionDecoration getDecoration() {
        return decoration;
    }

    public LootTable getMazeChestLootTable() {
        return mazeChestLootTable;
    }

    public TradeTable getTradeTable() {
        return tradeTable;
    }

    public MobSpawnPool getDayMobs() {
        return dayMobs;
    }

    public MobSpawnPool getNightMobs() {
        return nightMobs;
    }

    public List<CustomRecipe> getAccessibleRecipes() {
        return accessibleRecipes;
    }
}

