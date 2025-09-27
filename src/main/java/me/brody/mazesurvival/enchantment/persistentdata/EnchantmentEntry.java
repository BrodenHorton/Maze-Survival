package me.brody.mazesurvival.enchantment.persistentdata;

import java.io.Serializable;

public class EnchantmentEntry implements Serializable {
    private String enchantmentName;
    private int level;

    public EnchantmentEntry(String enchantmentName, int level) {
        this.enchantmentName = enchantmentName;
        this.level = level;
    }

    public String getEnchantmentName() {
        return enchantmentName;
    }

    public int getLevel() {
        return level;
    }
}
