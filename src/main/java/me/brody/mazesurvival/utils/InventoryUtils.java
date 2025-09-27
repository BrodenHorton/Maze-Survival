package me.brody.mazesurvival.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static boolean removeItem(Inventory inventory, ItemStack itemStack, int amount) {
        int remainingCount = amount;
        for(int i = 0; i < inventory.getSize() && remainingCount > 0; i++) {
            ItemStack inventoryItem = inventory.getItem(i);
            if(inventoryItem == null)
                continue;

            if(inventoryItem.isSimilar(itemStack)) {
                if(inventoryItem.getAmount() > remainingCount) {
                    inventoryItem.setAmount(inventoryItem.getAmount() - remainingCount);
                    remainingCount = 0;
                    break;
                }
                else {
                    remainingCount -= inventoryItem.getAmount();
                    inventory.setItem(i, null);
                }
            }
        }

        return remainingCount <= 0;
    }

    public static boolean containsSimilar(Inventory inventory, ItemStack itemStack, int amount) {
        int remainingCount = amount;
        for(int i = 0; i < inventory.getSize() && remainingCount > 0; i++) {
            ItemStack inventoryItem = inventory.getItem(i);
            if(inventoryItem == null)
                continue;

            if(inventoryItem.isSimilar(itemStack)) {
                if(inventoryItem.getAmount() > remainingCount) {
                    remainingCount = 0;
                    break;
                }
                else
                    remainingCount -= inventoryItem.getAmount();
            }
        }

        return remainingCount <= 0;
    }

}
