package me.brody.mazesurvival.utils;

import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemGradeUtils {

    public static ItemStack createGradedItem(ItemStack itemStack, ItemGrade grade) {
        if(itemStack == null)
            return null;

        setItemGrade(itemStack, grade);
        return itemStack;
    }

    public static void setItemGrade(ItemStack item, ItemGrade grade) {
        if(grade == null)
            return;

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(NamespacedKeys.ITEM_GRADE, PersistentDataType.STRING, grade.getName());
        if(meta.getDisplayName().isBlank()) {
            String defaultName = item.getType().name().replace("_", " ").toLowerCase();
            defaultName = capitalizeAfterSpace(defaultName);
            meta.setDisplayName(ChatColor.WHITE + defaultName);
        }
        else {
            meta.setDisplayName(ChatColor.WHITE + meta.getDisplayName());
        }
        if(meta.getLore() != null)
            meta.getLore().add(grade.getColor() + grade.getName() + " Grade");
        else
            meta.setLore(List.of(grade.getColor() + grade.getName() + " Grade"));
        if(meta.getEnchants().isEmpty())
            meta.addEnchant(Enchantment.FORTUNE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }

    private static String capitalizeAfterSpace(String str) {
        char[] chars = str.toCharArray();
        boolean hasCapitalNext = true;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == ' ') {
                hasCapitalNext = true;
                continue;
            }
            if(hasCapitalNext) {
                chars[i] = Character.toUpperCase(chars[i]);
                hasCapitalNext = false;
            }
        }

        return new String(chars);
    }

}
