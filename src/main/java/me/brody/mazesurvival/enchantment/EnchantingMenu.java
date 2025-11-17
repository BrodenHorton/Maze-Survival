package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.persistentdata.Script;
import me.brody.mazesurvival.enchantment.persistentdata.ScriptDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.player.PlayerProfile;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.RomanNumeralUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class EnchantingMenu implements Listener {
    private static final int INVENTORY_SIZE = 54;
    private static final String INVENTORY_NAME = "Enchanting";
    private static final int ENCHANTMENTS_PER_PAGE = 5;
    private static final ItemStack TRIM;
    private static final ItemStack RETURN_TO_NAV_MENU_ICON;
    private static final ItemStack INFO_SIGN;
    private static final int SCRIPTING_TOME_SLOT = 49;

    private final Main plugin;

    private List<TableEnchantment> tableEnchantments;
    private Inventory inventory;

    static {
        TRIM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta trimMeta = TRIM.getItemMeta();
        trimMeta.setDisplayName(" ");
        TRIM.setItemMeta(trimMeta);

        RETURN_TO_NAV_MENU_ICON = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta returnToNavMenuIconMeta = RETURN_TO_NAV_MENU_ICON.getItemMeta();
        returnToNavMenuIconMeta.setDisplayName(ChatColor.GRAY + "<- Navigation Menu");
        returnToNavMenuIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU, PersistentDataType.STRING, "return-to-nav");
        RETURN_TO_NAV_MENU_ICON.setItemMeta(returnToNavMenuIconMeta);

        INFO_SIGN = new ItemStack(Material.OAK_SIGN);
        ItemMeta infoSignMeta = INFO_SIGN.getItemMeta();
        infoSignMeta.setDisplayName(ChatColor.YELLOW + "Enchanting Info");
        infoSignMeta.setLore(List.of(ChatColor.GREEN + "An enchantment can be added",
                ChatColor.GREEN + "to a " + ChatColor.LIGHT_PURPLE + "Scripting Item" + ChatColor.GREEN + " by placing",
                ChatColor.GREEN + "the " + ChatColor.LIGHT_PURPLE + "Scripting Item" + ChatColor.GREEN + " in the",
                ChatColor.GREEN + "empty slot and selecting the",
                ChatColor.GREEN + "enchantment you want.",
                "",
                ChatColor.GREEN + "Once you have enchanted a",
                ChatColor.LIGHT_PURPLE + "Scripting Item" + ChatColor.GREEN + " you will have",
                ChatColor.GREEN + "access to the next numeric",
                ChatColor.GREEN + "level of that enchantment type.",
                "",
                ChatColor.GREEN + "A " + ChatColor.LIGHT_PURPLE + "Scripting Item" + ChatColor.GREEN + " can be",
                ChatColor.GREEN + "added to a compatible item in",
                ChatColor.GREEN + "your inventory by selecting the ",
                ChatColor.LIGHT_PURPLE + "Scripting Item" + ChatColor.GREEN + " and then selecting",
                ChatColor.GREEN + "the item you want to enchant."));
        INFO_SIGN.setItemMeta(infoSignMeta);
    }

    public EnchantingMenu(Main plugin, List<TableEnchantment> enchantments) {
        this.plugin = plugin;
        this.tableEnchantments = enchantments;
        inventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_NAME);

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void openEnchantingMenu(PlayerProfile profile, int page) {
        inventory.clear();

        if(page < 1)
            page = 1;
        if(tableEnchantments.size() <= (page - 1) * ENCHANTMENTS_PER_PAGE)
            page = (int)Math.ceil(tableEnchantments.size() / (double)ENCHANTMENTS_PER_PAGE);

        int rowIndex = 0;
        for(int i = (page - 1) * ENCHANTMENTS_PER_PAGE; i < tableEnchantments.size() && i < page * ENCHANTMENTS_PER_PAGE; i++) {
            inventory.setItem(rowIndex * 9, tableEnchantments.get(i).getIcon());
            for(int j = 1; j <= tableEnchantments.get(i).getEnchantmentLevelXpCosts().size(); j++) {
                ItemStack pane;
                if(j ==1 || profile.getUpgradeLevelByEnchantment().containsKey(tableEnchantments.get(i).getEnchantment()) && profile.getUpgradeLevelByEnchantment().get(tableEnchantments.get(i).getEnchantment()) >= j)
                    pane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                else
                    pane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                ItemMeta paneMeta = pane.getItemMeta();
                paneMeta.setDisplayName(ChatColor.AQUA + tableEnchantments.get(i).getEnchantment().getEnchantmentName() + " " + RomanNumeralUtils.romanNumeralOf(j));
                paneMeta.setLore(List.of(ChatColor.GRAY + "Level Cost: " + tableEnchantments.get(i).getEnchantmentLevelXpCosts().get(j - 1)));
                paneMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_TYPE, PersistentDataType.STRING, tableEnchantments.get(i).getEnchantment().getEnchantmentName());
                paneMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_LEVEL, PersistentDataType.INTEGER, j);
                paneMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_COST, PersistentDataType.INTEGER, tableEnchantments.get(i).getEnchantmentLevelXpCosts().get(j - 1));
                pane.setItemMeta(paneMeta);
                inventory.setItem(rowIndex * 9 + j, pane);
            }
            rowIndex++;
        }
        addInventoryActionBar(inventory, page);

        profile.getPlayer().openInventory(inventory);
    }

    private void addInventoryActionBar(Inventory inventory, int page) {
        if(inventory.getType() != InventoryType.CHEST)
            return;

        for(int i = inventory.getSize() - 9; i < inventory.getSize(); i++)
            inventory.setItem(i, TRIM);

        inventory.setItem(inventory.getSize() - 9, RETURN_TO_NAV_MENU_ICON);

        if(page > 1) {
            ItemStack previousPageIcon = new ItemStack(Material.ARROW);
            ItemMeta previousPageIconMeta = previousPageIcon.getItemMeta();
            previousPageIconMeta.setDisplayName(ChatColor.WHITE + "<- Page " + (page - 1));
            previousPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU, PersistentDataType.STRING, "pagination");
            previousPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU_PAGE, PersistentDataType.INTEGER, page - 1);
            previousPageIcon.setItemMeta(previousPageIconMeta);
            inventory.setItem(inventory.getSize() - 8, previousPageIcon);
        }

        inventory.setItem(SCRIPTING_TOME_SLOT, null);

        if(tableEnchantments.size() > page * ENCHANTMENTS_PER_PAGE) {
            ItemStack nextPageIcon = new ItemStack(Material.ARROW);
            ItemMeta nextPageIconMeta = nextPageIcon.getItemMeta();
            nextPageIconMeta.setDisplayName(ChatColor.WHITE + "Page " + (page + 1) + " ->");
            nextPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU, PersistentDataType.STRING, "pagination");
            nextPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.ENCHANTING_MENU_PAGE, PersistentDataType.INTEGER, page + 1);
            nextPageIcon.setItemMeta(nextPageIconMeta);
            inventory.setItem(inventory.getSize() - 2, nextPageIcon);
        }

        inventory.setItem(inventory.getSize() - 1, INFO_SIGN);
    }

    @EventHandler
    public void enchantScriptingItem(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        if(e.getAction() != InventoryAction.PICKUP_ALL
                && e.getAction() != InventoryAction.PICKUP_HALF
                && e.getAction() != InventoryAction.PICKUP_ONE
                && e.getAction() != InventoryAction.PICKUP_SOME)
            return;
        ItemMeta enchantMeta = e.getCurrentItem().getItemMeta();
        PersistentDataContainer enchantDataContainer = enchantMeta.getPersistentDataContainer();
        if(!enchantDataContainer.has(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_TYPE))
            return;
        if(!enchantDataContainer.has(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_LEVEL))
            return;
        if(!enchantDataContainer.has(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_COST))
            return;
        Player player = (Player)e.getWhoClicked();
        ItemStack scriptingItem = e.getInventory().getItem(SCRIPTING_TOME_SLOT);
        if(scriptingItem == null) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        ItemMeta scriptingItemMeta = scriptingItem.getItemMeta();
        PersistentDataContainer scriptingItemDataContainer = scriptingItemMeta.getPersistentDataContainer();
        if(!scriptingItemDataContainer.has(NamespacedKeys.SCRIPT)) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        Script script = scriptingItemDataContainer.get(NamespacedKeys.SCRIPT, new ScriptDataType());
        if(script.getMaxEnchantCount() <= script.getEnchantmentEntries().size()) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        String enchantmentName = enchantDataContainer.get(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_TYPE, PersistentDataType.STRING);
        MazeEnchantment mazeEnchantment = Registry.ENCHANTMENT.get(enchantmentName);
        if(mazeEnchantment.containsEnchant(scriptingItem)) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        int enchantmentCost = enchantDataContainer.get(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_COST, PersistentDataType.INTEGER);
        if(player.getLevel() < enchantmentCost) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
        int enchantmentLevel = enchantDataContainer.get(NamespacedKeys.ENCHANTING_MENU_ENCHANTMENT_LEVEL, PersistentDataType.INTEGER);
        if(!profile.getUpgradeLevelByEnchantment().containsKey(mazeEnchantment) && enchantmentLevel > 1) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }
        if(profile.getUpgradeLevelByEnchantment().containsKey(mazeEnchantment) && profile.getUpgradeLevelByEnchantment().get(mazeEnchantment) < enchantmentLevel) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }

        player.setLevel(player.getLevel() - enchantmentCost);
        mazeEnchantment.enchantScript(scriptingItem, enchantmentLevel);
        if(profile.getUpgradeLevelByEnchantment().containsKey(mazeEnchantment)) {
            if(profile.getUpgradeLevelByEnchantment().get(mazeEnchantment) == enchantmentLevel)
                profile.getUpgradeLevelByEnchantment().put(mazeEnchantment, enchantmentLevel + 1);
        }
        else
            profile.getUpgradeLevelByEnchantment().put(mazeEnchantment, 2);

        int page = 1;
        for(int i = 0; i < tableEnchantments.size(); i++) {
            if(tableEnchantments.get(i).getEnchantment().getEnchantmentName().equals(enchantmentName)) {
                page = (int)Math.ceil(i / (double)ENCHANTMENTS_PER_PAGE);
                break;
            }
        }
        openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), page);
        if(scriptingItem != null)
            inventory.setItem(SCRIPTING_TOME_SLOT, scriptingItem);

        player.playSound(e.getWhoClicked(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
    }

    @EventHandler
    public void returnToNavigationMenu(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        PersistentDataContainer persistentDataContainer = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
        if(!persistentDataContainer.has(NamespacedKeys.ENCHANTING_MENU))
            return;
        if(!persistentDataContainer.get(NamespacedKeys.ENCHANTING_MENU, PersistentDataType.STRING).equals("return-to-nav"))
            return;

        Player player = (Player)e.getWhoClicked();
        plugin.getEnchantingController().openNavigationMenu(player);
    }

    @EventHandler
    public void paginate(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        PersistentDataContainer persistentDataContainer = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
        if(!persistentDataContainer.has(NamespacedKeys.ENCHANTING_MENU))
            return;
        if(!persistentDataContainer.get(NamespacedKeys.ENCHANTING_MENU, PersistentDataType.STRING).equals("pagination"))
            return;
        if(!persistentDataContainer.has(NamespacedKeys.ENCHANTING_MENU_PAGE, PersistentDataType.INTEGER))
            return;

        Player player = (Player)e.getWhoClicked();
        ItemStack scriptingTomeSlotItem = e.getInventory().getItem(SCRIPTING_TOME_SLOT);

        int page = persistentDataContainer.get(NamespacedKeys.ENCHANTING_MENU_PAGE, PersistentDataType.INTEGER);

        openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), page);
        if(scriptingTomeSlotItem != null)
            inventory.setItem(SCRIPTING_TOME_SLOT, scriptingTomeSlotItem);
    }

    @EventHandler
    public void placeScriptingTomeInSlot(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getSlot() != SCRIPTING_TOME_SLOT)
            return;
        if(e.getAction() != InventoryAction.PLACE_ALL && e.getAction() != InventoryAction.PLACE_ONE && e.getAction() != InventoryAction.PLACE_SOME && e.getAction() != InventoryAction.SWAP_WITH_CURSOR)
            return;

        ItemStack cursorItemStack = e.getCursor();
        ItemMeta cursorMeta = cursorItemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = cursorMeta.getPersistentDataContainer();
        if(!persistentDataContainer.has(NamespacedKeys.SCRIPT)) {
            e.setCancelled(true);
            return;
        }
        Script script = persistentDataContainer.get(NamespacedKeys.SCRIPT, new ScriptDataType());
        if(script.getMaxEnchantCount() <= script.getEnchantmentEntries().size()) {
            e.setCancelled(true);
            return;
        }
        ItemStack slotItem = e.getInventory().getItem(SCRIPTING_TOME_SLOT);
        if(cursorItemStack.getAmount() == 1 && slotItem != null) {
            ItemStack temp = slotItem.clone();
            slotItem.setType(cursorItemStack.getType());
            slotItem.setAmount(cursorItemStack.getAmount());
            slotItem.setItemMeta(cursorMeta);
            cursorItemStack.setType(temp.getType());
            cursorItemStack.setAmount(temp.getAmount());
            cursorItemStack.setItemMeta(temp.getItemMeta());
            e.setCancelled(true);
        }
        else if(cursorItemStack.getAmount() > 1 && slotItem != null) {
            e.setCancelled(true);
        }
        else if(cursorItemStack.getAmount() > 1 && slotItem == null) {
            ItemStack splitStack = cursorItemStack.clone();
            splitStack.setAmount(1);
            cursorItemStack.setAmount(cursorItemStack.getAmount() - 1);
            e.getInventory().setItem(SCRIPTING_TOME_SLOT, splitStack);
            e.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void cancelChestGuiInteractions(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getSlot() == SCRIPTING_TOME_SLOT)
            return;

        e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void cancelPlayerInventoryInteractions(InventoryClickEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getRawSlot() < INVENTORY_SIZE)
            return;
        if(e.getAction() != InventoryAction.MOVE_TO_OTHER_INVENTORY)
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void addScriptToPlayerInventoryOnClose(InventoryCloseEvent e) {
        if(!e.getInventory().equals(inventory))
            return;
        if(e.getInventory().getItem(SCRIPTING_TOME_SLOT) == null)
            return;

        if(e.getPlayer() instanceof Player player)
            player.getInventory().addItem(e.getInventory().getItem(SCRIPTING_TOME_SLOT));
    }

}
