package me.brody.mazesurvival.mob;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MobDropTable {
    private List<MobDropEntry> basicDrops;
    private WeightedList<ItemStack> rareDrops;

    public MobDropTable() {
        basicDrops = new ArrayList<>();
        rareDrops = new WeightedList<>();
    }

    public void addBasicDrop(ItemStack itemStack, int maxQuantity, double dropRate) {
        if(itemStack == null)
            return;

        dropRate = Math.max(dropRate, 1);
        addBasicDrop(new MobDropEntry(itemStack, maxQuantity, dropRate));
    }

    public void addBasicDrop(MobDropEntry dropEntry) {
        basicDrops.add(dropEntry);
    }

    public void addRareDrop(ItemStack itemStack, int weight) {
        if(itemStack == null)
            return;

        rareDrops.add(itemStack, weight);
    }

    public List<MobDropEntry> getBasicDrops() {
        return basicDrops;
    }

    public WeightedList<ItemStack> getRareDrops() {
        return rareDrops;
    }
}
