package me.brody.mazesurvival.item.recipe;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CustomRecipeCompendium implements Listener {
    private static final int INVENTORY_SIZE = 54;
    private static final String INVENTORY_NAME = "Recipes";
    private static final ItemStack TRIM;

    private final Main plugin;
    private CustomRecipeCraftingMenu craftingMenu;

    static {
        TRIM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta trimMeta = TRIM.getItemMeta();
        trimMeta.setDisplayName(" ");
        TRIM.setItemMeta(trimMeta);
    }

    public CustomRecipeCompendium(Main plugin) {
        this.plugin = plugin;
        craftingMenu = new CustomRecipeCraftingMenu(plugin);
    }

    public void open(Player player, int pageIndex) {
        if(player == null || pageIndex < 0)
            return;

        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_NAME);
        List<CustomRecipe> unlockedRecipes = plugin.getGameState().getUnlockedRecipes();

        if(unlockedRecipes.size() < pageIndex * (INVENTORY_SIZE - 9))
            pageIndex = 0;

        int startIndex = pageIndex * (INVENTORY_SIZE - 9);
        int endIndex = Math.min(startIndex + (INVENTORY_SIZE - 9), unlockedRecipes.size());
        for(int i = startIndex; i < endIndex; i++) {
            CustomRecipe recipe = unlockedRecipes.get(i);
            ItemStack result = recipe.getResult();
            ItemMeta meta = result.getItemMeta();
            meta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_INDEX, PersistentDataType.INTEGER, i);
            result.setItemMeta(meta);
            inventory.addItem(result);
        }

        addInventoryActionBar(inventory, pageIndex);

        player.openInventory(inventory);
    }

    private void addInventoryActionBar(Inventory inventory, int pageIndex) {
        if(inventory.getType() != InventoryType.CHEST)
            return;

        for(int i = inventory.getSize() - 9; i < inventory.getSize(); i++)
            inventory.setItem(i, TRIM);

        if(pageIndex > 0) {
            ItemStack previousPageIcon = new ItemStack(Material.ARROW);
            ItemMeta previousPageIconMeta = previousPageIcon.getItemMeta();
            previousPageIconMeta.setDisplayName(ChatColor.WHITE + "<- Page " + pageIndex);
            previousPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_MENU, PersistentDataType.STRING, "pagination");
            previousPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_MENU_PAGE, PersistentDataType.INTEGER, pageIndex - 1);
            previousPageIcon.setItemMeta(previousPageIconMeta);
            inventory.setItem(inventory.getSize() - 8, previousPageIcon);
        }

        int unlockedRecipeCount = plugin.getGameState().getUnlockedRecipes().size();
        if(unlockedRecipeCount > (pageIndex + 1) * (inventory.getSize() - 9)) {
            ItemStack nextPageIcon = new ItemStack(Material.ARROW);
            ItemMeta nextPageIconMeta = nextPageIcon.getItemMeta();
            nextPageIconMeta.setDisplayName(ChatColor.WHITE + "Page " + (pageIndex + 2) + " ->");
            nextPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_MENU, PersistentDataType.STRING, "pagination");
            nextPageIconMeta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_MENU_PAGE, PersistentDataType.INTEGER, pageIndex + 1);
            nextPageIcon.setItemMeta(nextPageIconMeta);
            inventory.setItem(inventory.getSize() - 2, nextPageIcon);
        }
    }

    @EventHandler
    public void customRecipeMenuSelectRecipe(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        if(e.getAction() != InventoryAction.PICKUP_ALL
                && e.getAction() != InventoryAction.PICKUP_HALF
                && e.getAction() != InventoryAction.PICKUP_ONE
                && e.getAction() != InventoryAction.PICKUP_SOME) {
            e.setCancelled(true);
            return;
        }
        ItemMeta meta = e.getCurrentItem().getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.CUSTOM_RECIPE_INDEX)) {
            e.setCancelled(true);
            return;
        }

        e.setCancelled(true);
        int recipeIndex = dataContainer.get(NamespacedKeys.CUSTOM_RECIPE_INDEX, PersistentDataType.INTEGER);
        Player player = (Player)e.getWhoClicked();
        craftingMenu.open(player, plugin.getGameState().getUnlockedRecipes().get(recipeIndex));
        ChatUtils.msg(player, "&aRecipe Index " + recipeIndex + " selected");
    }

    @EventHandler
    public void paginate(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        PersistentDataContainer dataContainer = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.CUSTOM_RECIPE_MENU))
            return;
        if(!dataContainer.get(NamespacedKeys.CUSTOM_RECIPE_MENU, PersistentDataType.STRING).equals("pagination"))
            return;
        if(!dataContainer.has(NamespacedKeys.CUSTOM_RECIPE_MENU_PAGE, PersistentDataType.INTEGER))
            return;

        Player player = (Player)e.getWhoClicked();
        int page = dataContainer.get(NamespacedKeys.CUSTOM_RECIPE_MENU_PAGE, PersistentDataType.INTEGER);

        open(player, page);
    }

    @EventHandler
    public void cancelInventoryInteract(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;

        e.setCancelled(true);
    }
}
