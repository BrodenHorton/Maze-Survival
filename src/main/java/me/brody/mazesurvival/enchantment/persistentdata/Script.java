package me.brody.mazesurvival.enchantment.persistentdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Script implements Serializable {
    private int maxEnchantCount;
    private List<EnchantmentEntry> enchantmentEntries;

    public Script(int maxEnchantCount) {
        this.maxEnchantCount = maxEnchantCount;
        enchantmentEntries = new ArrayList<>();
    }

    public int getMaxEnchantCount() {
        return maxEnchantCount;
    }

    public List<EnchantmentEntry> getEnchantmentEntries() {
        return enchantmentEntries;
    }
}
