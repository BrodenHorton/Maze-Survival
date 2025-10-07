package me.brody.mazesurvival.mob.drop;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialMobDropTable implements MobDropTable {
    private WeightedList<ItemStack> drops;
    private int minDrops;
    private int maxDrops;

    public SpecialMobDropTable(int minDrops, int maxDrops) {
        drops = new WeightedList<>();
        this.minDrops = minDrops;
        this.maxDrops = maxDrops;
    }

    public void addDrop(ItemStack itemStack, int weight) {
        if(itemStack == null)
            return;

        drops.add(itemStack, weight);
    }

    @Override
    public List<ItemStack> getDrops(int lootingLevel) {
        Random rng = new Random();
        List<ItemStack> clonedDrops = new ArrayList<>();
        List<ItemStack> weightedDrops = drops.getWeightedValues(rng.nextInt(minDrops, maxDrops + 1));
        for(ItemStack drop : weightedDrops)
            clonedDrops.add(drop.clone());

        return clonedDrops;
    }
}
