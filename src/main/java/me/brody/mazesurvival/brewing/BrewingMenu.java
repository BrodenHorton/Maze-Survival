package me.brody.mazesurvival.brewing;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.brewing.persistentdata.BrewingIngredientEntry;
import me.brody.mazesurvival.brewing.persistentdata.BrewingIngredientList;
import me.brody.mazesurvival.brewing.persistentdata.BrewingIngredientListDataType;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.player.PlayerProfile;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class BrewingMenu implements Listener {
    private static final int INVENTORY_SIZE = 54;
    private static final String INVENTORY_NAME = "Brewing";
    private static final Inventory INVENTORY;

    private final Main plugin;

    static {
        INVENTORY = Bukkit.createInventory(null, INVENTORY_SIZE, INVENTORY_NAME);

        for(int i = 0; i < BrewingPotion.VALUES.size(); i++) {
            BrewingPotion brewingPotion = BrewingPotion.VALUES.get(i);
            ItemStack potion = brewingPotion.getPotion();
            ItemMeta potionMeta = potion.getItemMeta();
            List<String> potionLore = new ArrayList<>();
            potionLore.add(" ");
            potionLore.add(ChatColor.GRAY + "Ingredients:");
            List<BrewingIngredientEntry> ingredients = brewingPotion.getIngredientList().getIngredients();
            for(int j = 0; j < ingredients.size(); j++) {
                BrewingIngredientEntry ingredient = ingredients.get(j);
                ItemStack ingredientItem = ingredient.getItem();
                if(ingredientItem == null)
                    continue;

                String ingredientName;
                if(ingredientItem.getItemMeta().getDisplayName().isBlank())
                    ingredientName = formatIngredientName(ingredientItem.getType().toString());
                else
                    ingredientName = ingredientItem.getItemMeta().getDisplayName();

                ItemGrade grade = ingredients.get(j).getGrade();
                if(grade != null)
                    potionLore.add(ChatColor.WHITE + ingredientName + grade.getColor() + " (" + grade.getName() + " Grade)" + ChatColor.GRAY + " x" + ingredient.getAmount());
                else
                    potionLore.add(ChatColor.WHITE + ingredientName + ChatColor.GRAY + " x" + ingredient.getAmount());
            }
            potionMeta.setLore(potionLore);
            potionMeta.getPersistentDataContainer().set(NamespacedKeys.BREWING_MENU_INGREDIENTS, new BrewingIngredientListDataType(), brewingPotion.getIngredientList());
            potion.setItemMeta(potionMeta);
            INVENTORY.addItem(potion);
        }

        ItemStack trim = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta trimMeta = trim.getItemMeta();
        trimMeta.setDisplayName(" ");
        trim.setItemMeta(trimMeta);
        for(int i = INVENTORY.getSize() - 9; i < INVENTORY.getSize(); i++)
            INVENTORY.setItem(i, trim);
    }


    public BrewingMenu(Main plugin) {
        this.plugin = plugin;
    }

    public static void openBrewingMenu(PlayerProfile profile) {
        if(profile == null)
            return;

        profile.getPlayer().openInventory(INVENTORY);
    }

    public static String formatIngredientName(String ingredientName) {
        String[] words = ingredientName.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            String str1 = words[i].substring(0, 1).toUpperCase();
            sb.append(str1 + words[i].substring(1));
            if(i < words.length - 1)
                sb.append(" ");
        }

        return sb.toString();
    }

    @EventHandler
    public void openBrewingMenuFromBrewingStand(InventoryOpenEvent e) {
        if(e.getInventory().getType() != InventoryType.BREWING)
            return;
        if(!(e.getPlayer() instanceof Player))
            return;

        e.setCancelled(true);
        Player player = (Player)e.getPlayer();
        PlayerProfile profile = plugin.getProfileManager().getProfileOf(player);
        if(profile != null)
            openBrewingMenu(profile);
        else
            ChatUtils.msg(player, "&e[Warning] " + ChatColor.WHITE + "You are unable to open the Brewing menu without a player profile.");
    }

    @EventHandler
    public void brewPotion(InventoryClickEvent e) {
        if(!e.getInventory().equals(INVENTORY))
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
        ItemMeta potionMeta = e.getCurrentItem().getItemMeta();
        PersistentDataContainer potionDataContainer = potionMeta.getPersistentDataContainer();
        if(!potionDataContainer.has(NamespacedKeys.CUSTOM_ITEM)) {
            e.setCancelled(true);
            return;
        }
        if(!potionDataContainer.has(NamespacedKeys.BREWING_MENU_INGREDIENTS)) {
            e.setCancelled(true);
            return;
        }
        BrewingIngredientList ingredientList = potionDataContainer.get(NamespacedKeys.BREWING_MENU_INGREDIENTS, new BrewingIngredientListDataType());
        if(ingredientList == null) {
            e.setCancelled(true);
            return;
        }
        Player player = (Player)e.getWhoClicked();
        List<BrewingIngredientEntry> ingredients = ingredientList.getIngredients();
        for(int i = 0; i < ingredients.size(); i++) {
            if(!InventoryUtils.containsSimilar(player.getInventory(), ingredients.get(i).getItem(), ingredients.get(i).getAmount())) {
                ChatUtils.msg(player, "&cYou do not have the materials to brew this potion");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                e.setCancelled(true);
                return;
            }
        }

        for(int i = 0; i < ingredients.size(); i++)
            InventoryUtils.removeItem(player.getInventory(), ingredients.get(i).getItem(), ingredients.get(i).getAmount());

        ItemStack potion = Registry.CUSTOM_ITEM.get(potionDataContainer.get(NamespacedKeys.CUSTOM_ITEM, PersistentDataType.STRING)).getItemStack();
        player.getInventory().addItem(potion);
        player.playSound(player, Sound.BLOCK_BREWING_STAND_BREW, 1, 1);
        e.setCancelled(true);
    }
}
