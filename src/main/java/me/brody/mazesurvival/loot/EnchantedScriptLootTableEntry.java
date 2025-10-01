package me.brody.mazesurvival.loot;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EnchantedScriptLootTableEntry implements LootTableEntry {
    private static final Random RNG = new Random();

    private Map<MazeEnchantment, Integer> enchantments;
    private boolean isDoubleEnchant;

    public EnchantedScriptLootTableEntry(Map<MazeEnchantment, Integer> enchantments, boolean isDoubleEnchant) {
        this.enchantments = enchantments;
        this.isDoubleEnchant = isDoubleEnchant;
    }

    @Override
    public ItemStack obtain() {
        ItemStack itemStack = isDoubleEnchant ? CustomItem.SCRIPTING_TOME.getItemStack() : CustomItem.SCRIPTING_PAPER.getItemStack();
        int enchantCount = isDoubleEnchant ? 2 : 1;
        List<Map.Entry<MazeEnchantment, Integer>> enchantmentList = new ArrayList<>(enchantments.entrySet());
        for(int i = 0; i < enchantCount; i++) {
            Map.Entry<MazeEnchantment, Integer> enchantment = enchantmentList.get(RNG.nextInt(0, enchantmentList.size()));
            enchantment.getKey().enchantScript(itemStack, enchantment.getValue());
        }

        return itemStack;
    }
}
