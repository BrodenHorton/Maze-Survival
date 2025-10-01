package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
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

    public static Map<String, MazeEnchantment> MAZE_ENCHANTMENT_BY_NAME;

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
        THORNS = new SimpleEnchantment(plugin, Enchantment.THORNS, EnchantmentCategory.ARMOR);
        SOUL_SPEED = new SimpleEnchantment(plugin, Enchantment.SOUL_SPEED, EnchantmentCategory.BOOTS);
        VITALITY = new CustomEnchantment(plugin, "Vitality", EnchantmentCategory.ARMOR);
        MAZE_RUNNER = new CustomEnchantment(plugin, "Maze Runner", EnchantmentCategory.BOOTS);
        // Weapon enchantments
        SHARPNESS = new SimpleEnchantment(plugin, Enchantment.SHARPNESS, EnchantmentCategory.WEAPONS);
        SMITE = new SimpleEnchantment(plugin, Enchantment.SMITE, EnchantmentCategory.WEAPONS);
        BANE_OF_ARTHROPODS = new SimpleEnchantment(plugin, Enchantment.BANE_OF_ARTHROPODS, EnchantmentCategory.WEAPONS);
        FIRE_ASPECT = new SimpleEnchantment(plugin, Enchantment.FIRE_ASPECT, EnchantmentCategory.WEAPONS);
        KNOCKBACK = new SimpleEnchantment(plugin, Enchantment.KNOCKBACK, EnchantmentCategory.WEAPONS);
        LOOTING = new SimpleEnchantment(plugin, Enchantment.LOOTING, EnchantmentCategory.WEAPONS);
        CRUSADER = new CustomEnchantment(plugin, "Crusader", EnchantmentCategory.WEAPONS);
        // Bow Enchantments
        POWER = new SimpleEnchantment(plugin, Enchantment.POWER, EnchantmentCategory.BOWS);
        PUNCH = new SimpleEnchantment(plugin, Enchantment.PUNCH, EnchantmentCategory.BOWS);
        FLAME = new SimpleEnchantment(plugin, Enchantment.FLAME, EnchantmentCategory.BOWS);
        LINGERING_SHOT = new CustomEnchantment(plugin, "Lingering Shot", EnchantmentCategory.BOWS);
        // Universal Enchantments
        UNBREAKING = new SimpleEnchantment(plugin, Enchantment.UNBREAKING, EnchantmentCategory.UNIVERSAL);
        SOUL_BOUND = new CustomEnchantment(plugin, "Soul Bound", EnchantmentCategory.UNIVERSAL);

        MAZE_ENCHANTMENT_BY_NAME = new HashMap<>();
        // Armor
        MAZE_ENCHANTMENT_BY_NAME.put(PROTECTION.getEnchantmentName(), PROTECTION);
        MAZE_ENCHANTMENT_BY_NAME.put(THORNS.getEnchantmentName(), THORNS);
        MAZE_ENCHANTMENT_BY_NAME.put(SOUL_SPEED.getEnchantmentName(), SOUL_SPEED);
        MAZE_ENCHANTMENT_BY_NAME.put(VITALITY.getEnchantmentName(), VITALITY);
        MAZE_ENCHANTMENT_BY_NAME.put(MAZE_RUNNER.getEnchantmentName(), MAZE_RUNNER);
        // Weapon
        MAZE_ENCHANTMENT_BY_NAME.put(SHARPNESS.getEnchantmentName(), SHARPNESS);
        MAZE_ENCHANTMENT_BY_NAME.put(SMITE.getEnchantmentName(), SMITE);
        MAZE_ENCHANTMENT_BY_NAME.put(BANE_OF_ARTHROPODS.getEnchantmentName(), BANE_OF_ARTHROPODS);
        MAZE_ENCHANTMENT_BY_NAME.put(FIRE_ASPECT.getEnchantmentName(), FIRE_ASPECT);
        MAZE_ENCHANTMENT_BY_NAME.put(KNOCKBACK.getEnchantmentName(), KNOCKBACK);
        MAZE_ENCHANTMENT_BY_NAME.put(LOOTING.getEnchantmentName(), LOOTING);
        MAZE_ENCHANTMENT_BY_NAME.put(CRUSADER.getEnchantmentName(), CRUSADER);
        // Bow
        MAZE_ENCHANTMENT_BY_NAME.put(POWER.getEnchantmentName(), POWER);
        MAZE_ENCHANTMENT_BY_NAME.put(PUNCH.getEnchantmentName(), PUNCH);
        MAZE_ENCHANTMENT_BY_NAME.put(FLAME.getEnchantmentName(), FLAME);
        MAZE_ENCHANTMENT_BY_NAME.put(LINGERING_SHOT.getEnchantmentName(), LINGERING_SHOT);
        // Universal
        MAZE_ENCHANTMENT_BY_NAME.put(UNBREAKING.getEnchantmentName(), UNBREAKING);
        MAZE_ENCHANTMENT_BY_NAME.put(SOUL_BOUND.getEnchantmentName(), SOUL_BOUND);
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
