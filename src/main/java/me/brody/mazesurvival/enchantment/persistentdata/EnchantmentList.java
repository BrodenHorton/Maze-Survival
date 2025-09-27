package me.brody.mazesurvival.enchantment.persistentdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentList implements Serializable {
    private List<EnchantmentEntry> enchantmentEntries;

    public EnchantmentList() {
        enchantmentEntries = new ArrayList<>();
    }

    public EnchantmentList(List<EnchantmentEntry> enchantmentEntries) {
        this.enchantmentEntries = enchantmentEntries;
    }

    public List<EnchantmentEntry> getEnchantmentEntries() {
        return enchantmentEntries;
    }
}
