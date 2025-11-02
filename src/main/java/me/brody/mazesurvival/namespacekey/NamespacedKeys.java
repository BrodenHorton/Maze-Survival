package me.brody.mazesurvival.namespacekey;

import org.bukkit.NamespacedKey;

public class NamespacedKeys {
    public static String PLUGIN_NAMESPACE = "mazesurvival";

    // Custom Item keys
    public static final NamespacedKey CUSTOM_ITEM = new NamespacedKey(PLUGIN_NAMESPACE, "custom-item");
    public static final NamespacedKey TOOL_LEVEL = new NamespacedKey(PLUGIN_NAMESPACE, "tool-level");
    public static final NamespacedKey ARMOR_HEALTH_BOOST = new NamespacedKey(PLUGIN_NAMESPACE, "armor-health-boost");
    public static final NamespacedKey ITEM_GRADE = new NamespacedKey(PLUGIN_NAMESPACE, "item-grade");

    // Custom Recipe Compendium Menu Keys
    public static final NamespacedKey CUSTOM_RECIPE_INDEX = new NamespacedKey(PLUGIN_NAMESPACE, "custom-recipe-index");
    public static final NamespacedKey CUSTOM_RECIPE_MENU = new NamespacedKey(PLUGIN_NAMESPACE, "custom-recipe-menu");
    public static final NamespacedKey CUSTOM_RECIPE_MENU_PAGE = new NamespacedKey(PLUGIN_NAMESPACE, "custom-recipe-menu-page");

    // Item Enchantment keys
    public static final NamespacedKey SCRIPT = new NamespacedKey(PLUGIN_NAMESPACE, "script");
    public static final NamespacedKey CUSTOM_ENCHANTMENTS = new NamespacedKey(PLUGIN_NAMESPACE, "custom-enchantments");

    // Custom Mob keys
    public static final NamespacedKey CUSTOM_MOB = new NamespacedKey(PLUGIN_NAMESPACE, "custom-mob");

    // Boos keys
    public static final NamespacedKey REGION_BOSS = new NamespacedKey(PLUGIN_NAMESPACE, "region-boss");

    // Markers
    public static final NamespacedKey HAVEN_TELEPORT_MARKER = new NamespacedKey(PLUGIN_NAMESPACE, "haven-teleport-marker");

    // Wandering Trader keys
    public static final NamespacedKey WANDERING_TRADER = new NamespacedKey(PLUGIN_NAMESPACE, "wandering-trader");

    // Enchanting Menu keys
    public static final NamespacedKey ENCHANTING_NAV = new NamespacedKey(PLUGIN_NAMESPACE, "enchantment-nav");
    public static final NamespacedKey ENCHANTING_MENU = new NamespacedKey(PLUGIN_NAMESPACE, "enchanting-menu");
    public static final NamespacedKey ENCHANTING_MENU_PAGE = new NamespacedKey(PLUGIN_NAMESPACE, "enchanting-menu-page");
    public static final NamespacedKey ENCHANTING_MENU_ENCHANTMENT_TYPE = new NamespacedKey(PLUGIN_NAMESPACE, "enchanting-menu-enchantment-type");
    public static final NamespacedKey ENCHANTING_MENU_ENCHANTMENT_LEVEL = new NamespacedKey(PLUGIN_NAMESPACE, "enchanting-menu-enchantment-level");
    public static final NamespacedKey ENCHANTING_MENU_ENCHANTMENT_COST = new NamespacedKey(PLUGIN_NAMESPACE, "enchanting-menu-enchantment-cost");

    // Brewing Menu keys
    public static final NamespacedKey BREWING_MENU_INGREDIENTS = new NamespacedKey(PLUGIN_NAMESPACE, "brewing-menu-ingredients");

    // Generation
    public static final NamespacedKey GENERATE_LOOT = new NamespacedKey(PLUGIN_NAMESPACE, "generate-loot");
    public static final NamespacedKey BREAK_ON_CLOSE = new NamespacedKey(PLUGIN_NAMESPACE, "break-on-close");
    public static final NamespacedKey BREAK_ON_OPEN = new NamespacedKey(PLUGIN_NAMESPACE, "break-on-open");
    public static final NamespacedKey TRAP_CHEST = new NamespacedKey(PLUGIN_NAMESPACE, "trap-chest");

}
