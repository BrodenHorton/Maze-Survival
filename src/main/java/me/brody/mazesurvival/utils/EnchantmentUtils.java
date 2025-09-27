package me.brody.mazesurvival.utils;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentUtils {
    private static final Map<Enchantment, String> nameByEnchantment;

    static {
        nameByEnchantment = new HashMap<>();
        nameByEnchantment.put(Enchantment.PROTECTION, "Protection");
        nameByEnchantment.put(Enchantment.THORNS, "Thorns");
        nameByEnchantment.put(Enchantment.SOUL_SPEED, "Soul Speed");

        nameByEnchantment.put(Enchantment.SHARPNESS, "Sharpness");
        nameByEnchantment.put(Enchantment.SMITE, "Smite");
        nameByEnchantment.put(Enchantment.BANE_OF_ARTHROPODS, "Bane of Arthropods");
        nameByEnchantment.put(Enchantment.FIRE_ASPECT, "Fire Aspect");
        nameByEnchantment.put(Enchantment.KNOCKBACK, "Knockback");
        nameByEnchantment.put(Enchantment.LOOTING, "Looting");

        nameByEnchantment.put(Enchantment.POWER, "Power");
        nameByEnchantment.put(Enchantment.PUNCH, "Punch");
        nameByEnchantment.put(Enchantment.FLAME, "Flame");

        nameByEnchantment.put(Enchantment.UNBREAKING, "Unbreaking");
    }

    public static String getNameOf(Enchantment enchantment) {
        return nameByEnchantment.get(enchantment);
    }
}
