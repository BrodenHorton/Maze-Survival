package me.brody.mazesurvival.loot;

import me.brody.mazesurvival.utils.WeightedEntry;
import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTable {
	public static final LootTable NONE;
	public static final LootTable STRONGHOLD_LOOT_TABLE;
	/*public static final LootTable DESERT_LOOT_TABLE;
	public static final LootTable SWAMP_LOOT_TABLE;
	public static final LootTable NETHER_LOOT_TABLE;
	public static final LootTable BASTION_LOOT_TABLE;
	public static final LootTable DEEP_DARK_LOOT_TABLE;*/

	private WeightedList<ItemStack> weightedItems;

	static {
		NONE = new LootTable();

		List<WeightedEntry<ItemStack>> strongholdWeightedItems = List.of(
				new WeightedEntry<>(new ItemStack(Material.OAK_SAPLING, 1), 4),
				new WeightedEntry<>(new ItemStack(Material.OAK_LOG, 1), 2),
				new WeightedEntry<>(new ItemStack(Material.OAK_LOG, 2), 2),
				new WeightedEntry<>(new ItemStack(Material.COBBLESTONE, 1), 5),
				new WeightedEntry<>(new ItemStack(Material.COBBLESTONE, 2), 3),
				new WeightedEntry<>(new ItemStack(Material.COBBLESTONE, 3), 1),
				new WeightedEntry<>(new ItemStack(Material.APPLE, 1), 4),
				new WeightedEntry<>(new ItemStack(Material.BREAD, 1), 3),
				new WeightedEntry<>(new ItemStack(Material.BEEF, 1), 5),
				new WeightedEntry<>(new ItemStack(Material.BEEF, 2), 3),
				new WeightedEntry<>(new ItemStack(Material.PORKCHOP, 1), 5),
				new WeightedEntry<>(new ItemStack(Material.PORKCHOP, 2), 3),
				new WeightedEntry<>(new ItemStack(Material.CHICKEN, 1), 5),
				new WeightedEntry<>(new ItemStack(Material.CHICKEN, 2), 3)
		);
		STRONGHOLD_LOOT_TABLE = new LootTable(strongholdWeightedItems);

	}

	public LootTable() {
		this.weightedItems = new WeightedList<>();
	}
	
	public LootTable(List<WeightedEntry<ItemStack>> weightedItems) {
		this.weightedItems = new WeightedList<>(weightedItems);
	}

	public WeightedList<ItemStack> getWeightedItems() {
		return weightedItems;
	}
	
}
