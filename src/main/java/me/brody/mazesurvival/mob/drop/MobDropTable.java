package me.brody.mazesurvival.mob.drop;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface MobDropTable {
    List<ItemStack> getDrops(int lootingLevel);
}
