package me.brody.mazesurvival.mob.drop;

import me.brody.mazesurvival.utils.WeightedList;
import org.bukkit.inventory.ItemStack;

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
        return drops.getWeightedValues(rng.nextInt(minDrops, maxDrops + 1));
    }
}
