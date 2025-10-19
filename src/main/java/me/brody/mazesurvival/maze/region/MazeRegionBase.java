package me.brody.mazesurvival.maze.region;

import me.brody.mazesurvival.boss.BossFight;
import me.brody.mazesurvival.loot.chest.LootTable;
import me.brody.mazesurvival.loot.trade.TradeTable;
import me.brody.mazesurvival.maze.BlockPalette;
import me.brody.mazesurvival.mob.MobSpawnPool;

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

    private MazeRegionBase(BlockPalette wallPalette, BlockPalette floorPalette, RegionDecoration decoration, LootTable mazeChestLootTable, TradeTable tradeTable, MobSpawnPool dayMobs, MobSpawnPool nightMobs) {
        this.wallPalette = wallPalette;
        this.floorPalette = floorPalette;
        this.decoration = decoration;
        this.mazeChestLootTable = mazeChestLootTable;
        this.tradeTable = tradeTable;
        this.dayMobs = dayMobs;
        this.nightMobs = nightMobs;
    }

    public static void initMazeBases() {
        STRONGHOLD = new MazeRegionBase(
                BlockPalette.STRONGHOLD_WALL,
                BlockPalette.STRONGHOLD_FLOOR,
                RegionDecoration.STRONGHOLD_DECORATIONS,
                LootTable.STRONGHOLD_LOOT_TABLE,
                TradeTable.STRONGHOLD_TRADE_TABLE,
                MobSpawnPool.STRONGHOLD_DAY_MOBS,
                MobSpawnPool.STRONGHOLD_NIGHT_MOBS);
        DESERT = new MazeRegionBase(
                BlockPalette.DESERT_WALL,
                BlockPalette.DESERT_FLOOR,
                RegionDecoration.DESERT_DECORATIONS,
                LootTable.DESERT_LOOT_TABLE,
                TradeTable.DESERT_TRADE_TABLE,
                MobSpawnPool.DESERT_DAY_MOBS,
                MobSpawnPool.DESERT_NIGHT_MOBS);
        SWAMP = new MazeRegionBase(
                BlockPalette.SWAMP_WALL,
                BlockPalette.SWAMP_FLOOR,
                RegionDecoration.SWAMP_DECORATIONS,
                LootTable.SWAMP_LOOT_TABLE,
                TradeTable.SWAMP_TRADE_TABLE,
                MobSpawnPool.SWAMP_DAY_MOBS,
                MobSpawnPool.SWAMP_NIGHT_MOBS);
        NETHER = new MazeRegionBase(
                BlockPalette.NETHER_WALL,
                BlockPalette.NETHER_FLOOR,
                RegionDecoration.NETHER_DECORATIONS,
                LootTable.NETHER_LOOT_TABLE,
                TradeTable.NETHER_TRADE_TABLE,
                MobSpawnPool.NETHER_DAY_MOBS,
                MobSpawnPool.NETHER_NIGHT_MOBS);
        BASTION = new MazeRegionBase(
                BlockPalette.BASTION_WALL,
                BlockPalette.BASTION_FLOOR,
                RegionDecoration.BASTION_DECORATIONS,
                LootTable.STRONGHOLD_LOOT_TABLE,
                TradeTable.STRONGHOLD_TRADE_TABLE,
                MobSpawnPool.BASTION_DAY_MOBS,
                MobSpawnPool.BASTION_NIGHT_MOBS);
        DEEP_DARK = new MazeRegionBase(
                BlockPalette.DEEP_DARK_WALL,
                BlockPalette.DEEP_DARK_FLOOR,
                RegionDecoration.DEEP_DARK_DECORATIONS,
                LootTable.DEEP_DARK_LOOT_TABLE,
                TradeTable.DEEP_DARK_TRADE_TABLE,
                MobSpawnPool.DEEP_DARK_DAY_MOBS,
                MobSpawnPool.DEEP_DARK_NIGHT_MOBS);
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
}

