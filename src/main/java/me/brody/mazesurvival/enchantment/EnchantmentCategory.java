package me.brody.mazesurvival.enchantment;

import org.bukkit.Material;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EnchantmentCategory {
    public static final EnchantmentCategory ARMOR;
    public static final EnchantmentCategory BOOTS;
    public static final EnchantmentCategory WEAPONS;
    public static final EnchantmentCategory BOWS;
    public static final EnchantmentCategory UNIVERSAL;

    private Set<Material> materials;

    static {
        ARMOR = new EnchantmentCategory();
        ARMOR.materials = Set.of(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
                Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
                Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
                Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
                Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
                Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS);

        BOOTS = new EnchantmentCategory();
        BOOTS.materials = Set.of(Material.LEATHER_BOOTS, Material.GOLDEN_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS);

        WEAPONS = new EnchantmentCategory();
        WEAPONS.materials = Set.of(Material.WOODEN_SHOVEL, Material.WOODEN_HOE, Material.WOODEN_AXE, Material.WOODEN_SWORD,
                Material.STONE_SHOVEL, Material.STONE_HOE, Material.STONE_AXE, Material.STONE_SWORD,
                Material.GOLDEN_SHOVEL, Material.GOLDEN_HOE, Material.GOLDEN_AXE, Material.GOLDEN_SWORD,
                Material.IRON_SHOVEL, Material.IRON_HOE, Material.IRON_AXE, Material.IRON_SWORD,
                Material.DIAMOND_SHOVEL, Material.DIAMOND_HOE, Material.DIAMOND_AXE, Material.DIAMOND_SWORD,
                Material.NETHERITE_SHOVEL, Material.NETHERITE_HOE, Material.NETHERITE_AXE, Material.NETHERITE_SWORD);

        BOWS = new EnchantmentCategory();
        BOWS.materials = Set.of(Material.BOW);

        UNIVERSAL = new EnchantmentCategory();
        UNIVERSAL.materials.addAll(ARMOR.materials);
        UNIVERSAL.materials.addAll(BOOTS.materials);
        UNIVERSAL.materials.addAll(WEAPONS.materials);
        UNIVERSAL.materials.addAll(BOWS.materials);
    }

    private EnchantmentCategory() {
        materials = new HashSet<>();
    }

    public boolean contains(Material material) {
        return materials.contains(material);
    }
}
