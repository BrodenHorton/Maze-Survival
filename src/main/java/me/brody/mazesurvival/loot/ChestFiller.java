package me.brody.mazesurvival.loot;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChestFiller {
    private LootTable lootTable;
    private int maxSlotFill;
    private int minSlotFill;

    public ChestFiller(LootTable lootTable, int maxSlotFill, int minSlotFill) {
        this.lootTable = lootTable;
        this.maxSlotFill = maxSlotFill;
        this.minSlotFill = minSlotFill;
    }

    public void generateInventoryLoot(Inventory inventory) {
        Random rand = new Random();
        int fillAmt = rand.nextInt(minSlotFill, maxSlotFill + 1);
        fillAmt = fillAmt <= inventory.getSize() ? fillAmt : inventory.getSize();
        List<Integer> slotIndices = new ArrayList<>();
        for(int i = 0; i < inventory.getSize(); i++)
            slotIndices.add(i);
        Collections.shuffle(slotIndices);
        for(int i = 0; i < fillAmt; i++) {
            ItemStack itemStack = lootTable.getWeightedItems().getWeightedValue();
            inventory.setItem(slotIndices.get(i), itemStack);
        }
    }
}
