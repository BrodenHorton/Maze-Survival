package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.player.PlayerProfile;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EnchantingNavigationMenu implements Listener {
    public final static int INVENTORY_SIZE  = 9;
    public final static String INVENTORY_NAME = "Enchanting Navigation Menu";
    public final static ItemStack ARMOR_ICON;
    public final static ItemStack WEAPON_ICON;
    public final static ItemStack BOW_ICON;
    public final static ItemStack UNIVERSAL_ICON;

    private final Main plugin;

    static {
        ARMOR_ICON = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta armorIconMeta = ARMOR_ICON.getItemMeta();
        armorIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Armor Enchantments");
        armorIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_NAV, PersistentDataType.STRING, "armor");
        ARMOR_ICON.setItemMeta(armorIconMeta);

        WEAPON_ICON = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta weaponIconMeta = WEAPON_ICON.getItemMeta();
        weaponIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Weapon Enchantments");
        weaponIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_NAV, PersistentDataType.STRING, "weapon");
        WEAPON_ICON.setItemMeta(weaponIconMeta);

        BOW_ICON = new ItemStack(Material.BOW);
        ItemMeta bowIconMeta = BOW_ICON.getItemMeta();
        bowIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Bow Enchantments");
        bowIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_NAV, PersistentDataType.STRING, "bow");
        BOW_ICON.setItemMeta(bowIconMeta);

        UNIVERSAL_ICON = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta universalIconMeta = UNIVERSAL_ICON.getItemMeta();
        universalIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Universal Enchantments");
        universalIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_NAV, PersistentDataType.STRING, "universal");
        UNIVERSAL_ICON.setItemMeta(universalIconMeta);
    }

    public EnchantingNavigationMenu(Main plugin) {
        this.plugin = plugin;
    }

    public void openEnchantingMenu(Player player) {
        PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
        if(profile == null) {
            ChatUtils.msg(player, "&e[Warning] &fYou are unable to open the enchanting menu without a PlayerProfile.");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_NAME);
        inventory.setItem(1, ARMOR_ICON);
        inventory.setItem(3, WEAPON_ICON);
        inventory.setItem(5, BOW_ICON);
        inventory.setItem(7, UNIVERSAL_ICON);

        player.openInventory(inventory);
    }

    @EventHandler
    public void openEnchantingNavigationMenu(InventoryOpenEvent e) {
        if(e.getInventory().getType() != InventoryType.ENCHANTING)
            return;
        if(!(e.getPlayer() instanceof Player))
            return;

        e.setCancelled(true);
        Player player = (Player)e.getPlayer();
        plugin.getEnchantingController().openNavigationMenu(player);
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void cancelChestGuiInteractions(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;

        e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void cancelPlayerInventoryInteractions(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;
        if(e.getRawSlot() < INVENTORY_SIZE)
            return;
        if(e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY)
            return;

        e.setCancelled(true);
    }

}
