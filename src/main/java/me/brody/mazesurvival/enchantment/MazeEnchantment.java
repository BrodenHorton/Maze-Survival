package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.registry.Registry;
import net.royawesome.jlibnoise.module.combiner.Power;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.PoweredRail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class MazeEnchantment {
    public static MazeEnchantment PROTECTION;
    public static MazeEnchantment THORNS;
    public static MazeEnchantment SOUL_SPEED;
    public static MazeEnchantment VITALITY;
    public static MazeEnchantment MAZE_RUNNER;
    public static MazeEnchantment SHARPNESS;
    public static MazeEnchantment SMITE;
    public static MazeEnchantment BANE_OF_ARTHROPODS;
    public static MazeEnchantment FIRE_ASPECT;
    public static MazeEnchantment KNOCKBACK;
    public static MazeEnchantment LOOTING;
    public static MazeEnchantment CRUSADER;
    public static MazeEnchantment POWER;
    public static MazeEnchantment PUNCH;
    public static MazeEnchantment FLAME;
    public static MazeEnchantment LINGERING_SHOT;
    public static MazeEnchantment UNBREAKING;
    public static MazeEnchantment SOUL_BOUND;

    protected Main plugin;
    protected String enchantmentName;
    protected EnchantmentCategory category;

    public MazeEnchantment(Main plugin, String enchantmentName, EnchantmentCategory category) {
        this.plugin = plugin;
        this.enchantmentName = enchantmentName;
        this.category = category;
    }

    public static void init(Main plugin) {
        // Armor Enchantments
        PROTECTION = new SimpleEnchantment(plugin, Enchantment.PROTECTION, EnchantmentCategory.ARMOR);
        register(PROTECTION);
        THORNS = new SimpleEnchantment(plugin, Enchantment.THORNS, EnchantmentCategory.ARMOR);
        register(THORNS);
        SOUL_SPEED = new SimpleEnchantment(plugin, Enchantment.SOUL_SPEED, EnchantmentCategory.BOOTS);
        register(SOUL_SPEED);
        VITALITY = new CustomEnchantment(plugin, "Vitality", EnchantmentCategory.ARMOR);
        register(VITALITY);
        MAZE_RUNNER = new CustomEnchantment(plugin, "Maze Runner", EnchantmentCategory.BOOTS);
        register(MAZE_RUNNER);
        // Weapon enchantments
        SHARPNESS = new SimpleEnchantment(plugin, Enchantment.SHARPNESS, EnchantmentCategory.WEAPONS);
        register(SHARPNESS);
        SMITE = new SimpleEnchantment(plugin, Enchantment.SMITE, EnchantmentCategory.WEAPONS);
        register(SMITE);
        BANE_OF_ARTHROPODS = new SimpleEnchantment(plugin, Enchantment.BANE_OF_ARTHROPODS, EnchantmentCategory.WEAPONS);
        register(BANE_OF_ARTHROPODS);
        FIRE_ASPECT = new SimpleEnchantment(plugin, Enchantment.FIRE_ASPECT, EnchantmentCategory.WEAPONS);
        register(FIRE_ASPECT);
        KNOCKBACK = new SimpleEnchantment(plugin, Enchantment.KNOCKBACK, EnchantmentCategory.WEAPONS);
        register(KNOCKBACK);
        LOOTING = new SimpleEnchantment(plugin, Enchantment.LOOTING, EnchantmentCategory.WEAPONS);
        register(LOOTING);
        CRUSADER = new CustomEnchantment(plugin, "Crusader", EnchantmentCategory.WEAPONS);
        register(CRUSADER);
        // Bow Enchantments
        POWER = new SimpleEnchantment(plugin, Enchantment.POWER, EnchantmentCategory.BOWS);
        register(POWER);
        PUNCH = new SimpleEnchantment(plugin, Enchantment.PUNCH, EnchantmentCategory.BOWS);
        register(PUNCH);
        FLAME = new SimpleEnchantment(plugin, Enchantment.FLAME, EnchantmentCategory.BOWS);
        register(FLAME);
        LINGERING_SHOT = new CustomEnchantment(plugin, "Lingering Shot", EnchantmentCategory.BOWS);
        register(LINGERING_SHOT);
        // Universal Enchantments
        UNBREAKING = new SimpleEnchantment(plugin, Enchantment.UNBREAKING, EnchantmentCategory.UNIVERSAL);
        register(UNBREAKING);
        SOUL_BOUND = new CustomEnchantment(plugin, "Soul Bound", EnchantmentCategory.UNIVERSAL);
        register(SOUL_BOUND);
    }

    private static void register(MazeEnchantment enchantment) {
        Registry.ENCHANTMENT.register(enchantment.enchantmentName, enchantment);
    }

    public abstract void enchantScript(ItemStack itemStack, int level);

    public abstract void enchantItem(ItemStack itemStack, int level);

    public abstract boolean containsEnchant(ItemStack itemStack);

    public String getEnchantmentName() {
        return enchantmentName;
    }

    public EnchantmentCategory getCategory() {
        return category;
    }
}
