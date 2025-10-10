package me.brody.mazesurvival.mob.drop;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleMobDropTable implements MobDropTable {
    private static final double DEFAULT_RARE_DROP_RATE = 0.5;

    private List<MobDropEntry> basicDrops;
    private WeightedList<ItemStack> rareDrops;

    public SimpleMobDropTable() {
        basicDrops = new ArrayList<>();
        rareDrops = new WeightedList<>();
    }

    public void addBasicDrop(ItemStack itemStack, int maxQuantity) {
        if(itemStack == null)
            return;

        addBasicDrop(new MobDropEntry(itemStack, maxQuantity));
    }

    public void addBasicDrop(MobDropEntry dropEntry) {
        basicDrops.add(dropEntry);
    }

    public void addRareDrop(ItemStack itemStack, int weight) {
        if(itemStack == null)
            return;

        rareDrops.add(itemStack, weight);
    }

    @Override
    public List<ItemStack> getDrops(int lootingLevel) {
        Random rng = new Random();
        List<ItemStack> drops = new ArrayList<>();
        for(MobDropEntry entry : basicDrops) {
            int dropAmt = rng.nextInt(0, entry.getMaxQuantity() + lootingLevel + 1);
            if(dropAmt > 0) {
                ItemStack item = entry.getItemStack().clone();
                item.setAmount(dropAmt);
                drops.add(item);
            }
        }
        double dropRate = DEFAULT_RARE_DROP_RATE * (lootingLevel + 1);
        double rngRoll = rng.nextDouble();
        System.out.println("rng Roll: " + rngRoll + " dropRate: " + dropRate);
        boolean shouldDropRare = rngRoll < dropRate;
        if(shouldDropRare) {
            System.out.println("Dropping rare item!");
            ItemStack rareDrop = rareDrops.getWeightedValue().clone();
            if(rareDrop != null)
                drops.add(rareDrop);
        }

        return drops;
    }
}
