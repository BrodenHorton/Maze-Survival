package me.brody.mazesurvival.item.recipe;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class CustomRecipeCraftingMenu implements Listener {
    private static final int INVENTORY_SIZE = 54;
    private static final String INVENTORY_NAME = "Crafting Recipe";
    private static final ItemStack TRIM;

    private final Main plugin;

    static {
        TRIM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta trimMeta = TRIM.getItemMeta();
        trimMeta.setDisplayName(" ");
        TRIM.setItemMeta(trimMeta);
    }

    public CustomRecipeCraftingMenu(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void open(Player player, CustomRecipe recipe) {
        if(player == null || recipe == null)
            return;

        Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_NAME);

        String[] menuLayout = new String[5];
        menuLayout[0] = "WWWWW";
        menuLayout[1] = "W   W WWW";
        menuLayout[2] = "W   WGW W";
        menuLayout[3] = "W   W WWW";
        menuLayout[4] = "WWWWW";
        Map<Character, Material> materialByChar = new HashMap<>();
        materialByChar.put('W', Material.WHITE_STAINED_GLASS_PANE);
        materialByChar.put('G', Material.LIME_STAINED_GLASS_PANE);
        materialByChar.put(' ', null);

        for(int i = 0; i < menuLayout.length; i++) {
            for(int j = 0; j < menuLayout[i].length(); j++) {
                Material mat = materialByChar.get(menuLayout[i].charAt(j));
                ItemStack itemStack = mat != null ? new ItemStack(mat) : null;
                if(itemStack != null) {
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setDisplayName(" ");
                    itemStack.setItemMeta(meta);
                }
                inventory.setItem(i * 9 + j, itemStack);
            }
        }

        ItemStack[][] recipeDisplay = recipe.getRecipeDisplay();
        // TODO: Add recipe display

        addInventoryActionBar(inventory);

        player.openInventory(inventory);
    }

    private void addInventoryActionBar(Inventory inventory) {
        if(inventory.getType() != InventoryType.CHEST)
            return;

        for(int i = inventory.getSize() - 9; i < inventory.getSize(); i++)
            inventory.setItem(i, TRIM);

        ItemStack compendiumNavIcon = new ItemStack(Material.ARROW);
        ItemMeta meta = compendiumNavIcon.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "<- Compendium ");
        meta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_RECIPE_MENU, PersistentDataType.STRING, "compendium_nav");
        compendiumNavIcon.setItemMeta(meta);
        inventory.setItem(inventory.getSize() - 8, compendiumNavIcon);
    }

    @EventHandler
    public void navigateToCompendium(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;
        if(e.getRawSlot() >= INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        PersistentDataContainer dataContainer = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.CUSTOM_RECIPE_MENU))
            return;
        if(!dataContainer.get(NamespacedKeys.CUSTOM_RECIPE_MENU, PersistentDataType.STRING).equals("compendium_nav"))
            return;

        Player player = (Player)e.getWhoClicked();
        plugin.getCustomRecipeCompendium().open(player, 0);
    }

    @EventHandler
    public void cancelInventoryInteract(InventoryClickEvent e) {
        if(!e.getView().getTitle().equals(INVENTORY_NAME))
            return;

        e.setCancelled(true);
    }

}
