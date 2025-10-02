package me.brody.mazesurvival.loot.trade;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.inventory.MerchantRecipe;

import java.util.function.Supplier;

public class TradeTable extends WeightedList<Supplier<MerchantRecipe>> {
    public static final TradeTable STRONGHOLD_TRADE_TABLE;
    public static final TradeTable DESERT_TRADE_TABLE;
    public static final TradeTable SWAMP_TRADE_TABLE;
    public static final TradeTable NETHER_TRADE_TABLE;
    public static final TradeTable DEEP_DARK_TRADE_TABLE;

    static {
        STRONGHOLD_TRADE_TABLE = new TradeTable();
        //STRONGHOLD_TRADE_TABLE.add(new SimpleTradeEntry());

        DESERT_TRADE_TABLE = new TradeTable();

        SWAMP_TRADE_TABLE = new TradeTable();

        NETHER_TRADE_TABLE = new TradeTable();

        DEEP_DARK_TRADE_TABLE = new TradeTable();

    }

    private TradeTable() {}

}
