package me.brody.mazesurvival.loot.chest;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class ChestFiller {
    private LootTable lootTable;
    private int minSlotFill;
    private int maxSlotFill;

    public ChestFiller(LootTable lootTable, int minSlotFill, int maxSlotFill) {
        this.lootTable = lootTable;
        this.minSlotFill = minSlotFill;
        this.maxSlotFill = maxSlotFill;
    }

    public void generateInventoryLoot(Inventory inventory) {
        Random rand = new Random();
        int fillAmt = rand.nextInt(minSlotFill, maxSlotFill + 1);
        fillAmt = Math.min(fillAmt, inventory.getSize() - 1);
        List<Integer> slotIndices = new ArrayList<>();
        for(int i = 0; i < inventory.getSize(); i++)
            slotIndices.add(i);
        Collections.shuffle(slotIndices);
        List<ItemStack> items = new ArrayList<>();
        List<Supplier<ItemStack>> lootTableEntries = lootTable.getWeightedValues(fillAmt);
        for(Supplier<ItemStack> entry : lootTableEntries)
            items.add(entry.get());
        for(int i = 0; i < items.size(); i++)
            inventory.setItem(slotIndices.get(i), items.get(i));
    }
}
