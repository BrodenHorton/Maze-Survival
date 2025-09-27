package me.brody.mazesurvival.maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.brody.mazesurvival.registry.Registry;
import org.bukkit.Material;

public class BlockPalette {
	public static BlockPalette STRONGHOLD_WALL;
	public static BlockPalette STRONGHOLD_FLOOR;
	public static BlockPalette DESERT_WALL;
	public static BlockPalette DESERT_FLOOR;
	public static BlockPalette SWAMP_WALL;
	public static BlockPalette SWAMP_FLOOR;
	public static BlockPalette NETHER_WALL;
	public static BlockPalette NETHER_FLOOR;
	public static BlockPalette BASTION_WALL;
	public static BlockPalette BASTION_FLOOR;
	public static BlockPalette DEEP_DARK_WALL;
	public static BlockPalette DEEP_DARK_FLOOR;

	private static final Random RNG = new Random();

	private Map<Material, Integer> weightByMaterial;

	public BlockPalette() {
		weightByMaterial = new HashMap<>();
	}

	public BlockPalette(Map<Material, Integer> blocksWithWeight) {
		this.weightByMaterial = blocksWithWeight;
	}

	static {
		STRONGHOLD_WALL = new BlockPalette();
		STRONGHOLD_WALL.getWeightByMaterial().put(Material.STONE_BRICKS, 2);
		STRONGHOLD_WALL.getWeightByMaterial().put(Material.CRACKED_STONE_BRICKS, 1);
		STRONGHOLD_WALL.getWeightByMaterial().put(Material.MOSSY_COBBLESTONE, 1);
		STRONGHOLD_FLOOR = new BlockPalette();
		STRONGHOLD_FLOOR.getWeightByMaterial().put(Material.STONE_BRICKS, 5);
		STRONGHOLD_FLOOR.getWeightByMaterial().put(Material.CRACKED_STONE_BRICKS, 3);

		DESERT_WALL = new BlockPalette();
		DESERT_WALL.getWeightByMaterial().put(Material.SMOOTH_SANDSTONE, 8);
		DESERT_WALL.getWeightByMaterial().put(Material.SANDSTONE, 2);
		DESERT_WALL.getWeightByMaterial().put(Material.CHISELED_SANDSTONE, 1);
		DESERT_FLOOR = new BlockPalette();
		DESERT_FLOOR.getWeightByMaterial().put(Material.SANDSTONE, 18);
		DESERT_FLOOR.getWeightByMaterial().put(Material.BIRCH_PLANKS, 2);
		DESERT_FLOOR.getWeightByMaterial().put(Material.SANDSTONE_STAIRS, 1);

		SWAMP_WALL = new BlockPalette();
		SWAMP_WALL.getWeightByMaterial().put(Material.MUDDY_MANGROVE_ROOTS, 6);
		SWAMP_WALL.getWeightByMaterial().put(Material.MANGROVE_WOOD, 1);
		SWAMP_FLOOR = new BlockPalette();
		SWAMP_FLOOR.getWeightByMaterial().put(Material.MUD, 8);
		SWAMP_FLOOR.getWeightByMaterial().put(Material.SOUL_SOIL, 1);

		NETHER_WALL = new BlockPalette();
		NETHER_WALL.getWeightByMaterial().put(Material.NETHERRACK, 15);
		NETHER_WALL.getWeightByMaterial().put(Material.RED_NETHER_BRICKS, 1);
		NETHER_FLOOR = new BlockPalette();
		NETHER_FLOOR.getWeightByMaterial().put(Material.NETHERRACK, 8);
		NETHER_FLOOR.getWeightByMaterial().put(Material.CRIMSON_NYLIUM, 4);
		NETHER_FLOOR.getWeightByMaterial().put(Material.SOUL_SOIL, 1);

		BASTION_WALL = new BlockPalette();
		BASTION_WALL.getWeightByMaterial().put(Material.POLISHED_BLACKSTONE_BRICKS, 10);
		BASTION_WALL.getWeightByMaterial().put(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS, 4);
		BASTION_WALL.getWeightByMaterial().put(Material.POLISHED_BLACKSTONE, 2);
		BASTION_WALL.getWeightByMaterial().put(Material.GILDED_BLACKSTONE, 2);
		BASTION_WALL.getWeightByMaterial().put(Material.CHISELED_POLISHED_BLACKSTONE, 1);
		BASTION_FLOOR = new BlockPalette();
		BASTION_FLOOR.getWeightByMaterial().put(Material.DEEPSLATE_TILES, 10);
		BASTION_FLOOR.getWeightByMaterial().put(Material.DEEPSLATE_BRICKS, 2);
		BASTION_FLOOR.getWeightByMaterial().put(Material.MAGMA_BLOCK, 1);

		DEEP_DARK_WALL = new BlockPalette();
		DEEP_DARK_WALL.getWeightByMaterial().put(Material.COAL_BLOCK, 4);
		DEEP_DARK_WALL.getWeightByMaterial().put(Material.SCULK, 1);
		DEEP_DARK_FLOOR = new BlockPalette();
		DEEP_DARK_FLOOR.getWeightByMaterial().put(Material.DEEPSLATE, 15);
		DEEP_DARK_FLOOR.getWeightByMaterial().put(Material.DEEPSLATE_COAL_ORE, 3);
		DEEP_DARK_FLOOR.getWeightByMaterial().put(Material.BLACKSTONE, 1);
		DEEP_DARK_FLOOR.getWeightByMaterial().put(Material.COAL_BLOCK, 1);
	}
	
	public Material pickBlockFromPalette() {
		int totalPaletteWeight = 0;
		for(Integer weight : weightByMaterial.values())
			totalPaletteWeight += weight;

		Material chosenBlock = null;
		int randValue = RNG.nextInt(totalPaletteWeight);
		for(Map.Entry<Material, Integer> blockEntry : weightByMaterial.entrySet()) {
			if(randValue < blockEntry.getValue()) {
				chosenBlock = blockEntry.getKey();
				break;
			}
			
			randValue -= blockEntry.getValue();
		}
		
		return chosenBlock;
	}

	public Map<Material, Integer> getWeightByMaterial() {
		return weightByMaterial;
	}
}
