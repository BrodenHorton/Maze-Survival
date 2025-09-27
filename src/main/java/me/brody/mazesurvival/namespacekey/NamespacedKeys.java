package me.brody.mazesurvival.namespacekey;

import org.bukkit.NamespacedKey;

public class NamespacedKeys {
    public static String PLUGIN_NAMESPACE = "mazesurvival";

    // Custom Item keys
    public static final NamespacedKey CUSTOM_ITEM = new NamespacedKey(PLUGIN_NAMESPACE, "custom-item");
    public static final NamespacedKey TOOL_LEVEL = new NamespacedKey(PLUGIN_NAMESPACE, "tool-level");
    public static final NamespacedKey ARMOR_HEALTH_BOOST = new NamespacedKey(PLUGIN_NAMESPACE, "armor-health-boost");
    public static final NamespacedKey ITEM_GRADE = new NamespacedKey(PLUGIN_NAMESPACE, "item-grade");

    // Item Enchantment keys
    public static final NamespacedKey SCRIPT = new NamespacedKey(PLUGIN_NAMESPACE, "script");
    public static final NamespacedKey CUSTOM_ENCHANTMENTS = new NamespacedKey(PLUGIN_NAMESPACE, "custom-enchantments");

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
}
