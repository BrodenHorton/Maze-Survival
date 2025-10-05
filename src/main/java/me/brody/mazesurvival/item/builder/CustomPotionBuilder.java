package me.brody.mazesurvival.item.builder;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomPotionBuilder extends CustomItemBuilder<CustomPotionBuilder> {

    public CustomPotionBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    public CustomPotionBuilder withPotionEffect(PotionEffectType effectType, int duration, int amplifier, boolean ambient) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        ((PotionMeta)itemMeta).addCustomEffect(new PotionEffect(effectType, duration, amplifier, ambient), true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public CustomPotionBuilder withPotionColor(Color color) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        ((PotionMeta)itemMeta).setColor(color);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    @Override
    public CustomPotionBuilder link() {
        return this;
    }
}
