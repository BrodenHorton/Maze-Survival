package me.brody.mazesurvival.loot;

import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import me.brody.mazesurvival.utils.WeightedEntry;
import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTable extends WeightedList<ItemStack> {
	public static final LootTable STRONGHOLD_LOOT_TABLE;
	public static final LootTable DESERT_LOOT_TABLE;
	public static final LootTable SWAMP_LOOT_TABLE;
	public static final LootTable NETHER_LOOT_TABLE;
	public static final LootTable DEEP_DARK_LOOT_TABLE;

	static {
		STRONGHOLD_LOOT_TABLE = new LootTable();
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.OAK_LOG), 4);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.COBBLESTONE), 2);
		ItemStack cobblestoneIronGrade = new ItemStack(Material.COBBLESTONE);
		ItemGradeUtils.setItemGrade(cobblestoneIronGrade, ItemGrade.IRON);
		STRONGHOLD_LOOT_TABLE.add(cobblestoneIronGrade, 5);
		ItemStack cobblestoneGoldGrade = new ItemStack(Material.COBBLESTONE);
		ItemGradeUtils.setItemGrade(cobblestoneGoldGrade, ItemGrade.GOLD);
		STRONGHOLD_LOOT_TABLE.add(cobblestoneGoldGrade, 5);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.LEATHER), 3);
		ItemStack leatherIronGrade = new ItemStack(Material.LEATHER);
		ItemGradeUtils.setItemGrade(leatherIronGrade, ItemGrade.IRON);
		STRONGHOLD_LOOT_TABLE.add(leatherIronGrade, 1);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.AMETHYST_SHARD), 4);
		STRONGHOLD_LOOT_TABLE.add(CustomItem.SWIFTNESS.getItemStack(), 3);
		STRONGHOLD_LOOT_TABLE.add(CustomItem.SWIFTNESS_EXTENDED.getItemStack(), 5);
		STRONGHOLD_LOOT_TABLE.add(CustomItem.SWIFTNESS_II.getItemStack(), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.APPLE), 3);
		ItemStack appleIronGrade = new ItemStack(Material.APPLE);
		ItemGradeUtils.setItemGrade(appleIronGrade, ItemGrade.IRON);
		STRONGHOLD_LOOT_TABLE.add(appleIronGrade, 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.WHEAT_SEEDS), 2);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.CARROT), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.DIRT), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.SAND), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.GRAVEL), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.ANDESITE), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.DIORITE), 5);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.GRANITE), 3);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.CHARCOAL), 5);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.BONE), 5);
		STRONGHOLD_LOOT_TABLE.add(new ItemStack(Material.GOLDEN_APPLE), 3);
		STRONGHOLD_LOOT_TABLE.add(CustomItem.MAZE_RUNNER_BOOTS.getItemStack(), 3);

		DESERT_LOOT_TABLE = new LootTable();
		DESERT_LOOT_TABLE.add(new ItemStack(Material.RAW_COPPER), 5);
		DESERT_LOOT_TABLE.add(CustomItem.TIN.getItemStack(), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.LAPIS_LAZULI), 5);
		DESERT_LOOT_TABLE.add(CustomItem.SCRIPTING_PAPER.getItemStack(), 5);
		// TODO: Add all Scripting Paper enchantments
		DESERT_LOOT_TABLE.add(CustomItem.SWIFTNESS_II.getItemStack(), 5);
		DESERT_LOOT_TABLE.add(CustomItem.SWIFTNESS_II_EXTENDED.getItemStack(), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.BIRCH_LOG), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.BIRCH_SAPLING), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.POTATO), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.CACTUS), 5);
		ItemStack cactusIronGrade = new ItemStack(Material.CACTUS);
		ItemGradeUtils.setItemGrade(cactusIronGrade, ItemGrade.IRON);
		DESERT_LOOT_TABLE.add(cactusIronGrade, 3);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.SUGAR_CANE), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.SUGAR), 5);
		ItemStack sugarIronGrade = new ItemStack(Material.SUGAR);
		ItemGradeUtils.setItemGrade(sugarIronGrade, ItemGrade.IRON);
		DESERT_LOOT_TABLE.add(sugarIronGrade, 3);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.SAND), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.RED_SAND), 5);
		ItemStack redSandIronGrade = new ItemStack(Material.RED_SAND);
		ItemGradeUtils.setItemGrade(redSandIronGrade, ItemGrade.IRON);
		DESERT_LOOT_TABLE.add(redSandIronGrade, 3);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.SANDSTONE), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.RED_SANDSTONE), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.DEAD_BUSH), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.BONE), 5);
		DESERT_LOOT_TABLE.add(new ItemStack(Material.GOLDEN_APPLE), 3);
		DESERT_LOOT_TABLE.add(CustomItem.MAZE_RUNNER_BOOTS.getItemStack(), 3);

		// TODO: Finish loot tables
		SWAMP_LOOT_TABLE = new LootTable();
		SWAMP_LOOT_TABLE.add(new ItemStack(Material.BONE), 5);
		SWAMP_LOOT_TABLE.add(new ItemStack(Material.GOLDEN_APPLE), 3);
		SWAMP_LOOT_TABLE.add(CustomItem.MAZE_RUNNER_BOOTS.getItemStack(), 3);

		NETHER_LOOT_TABLE = new LootTable();
		NETHER_LOOT_TABLE.add(new ItemStack(Material.BONE), 5);
		NETHER_LOOT_TABLE.add(new ItemStack(Material.GOLDEN_APPLE), 3);
		NETHER_LOOT_TABLE.add(CustomItem.MAZE_RUNNER_BOOTS.getItemStack(), 3);

		DEEP_DARK_LOOT_TABLE = new LootTable();
		DEEP_DARK_LOOT_TABLE.add(new ItemStack(Material.BONE), 5);
		DEEP_DARK_LOOT_TABLE.add(new ItemStack(Material.GOLDEN_APPLE), 3);
		DEEP_DARK_LOOT_TABLE.add(CustomItem.MAZE_RUNNER_BOOTS.getItemStack(), 3);
	}

	private LootTable() {}
	
}
