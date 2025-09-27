package me.brody.mazesurvival.item.builder;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomPotionBuilder extends CustomItemBuilder<CustomPotionBuilder> {

    public CustomPotionBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    public CustomPotionBuilder withPotionEffect(PotionEffectType effectType, int duration, int amplifier, boolean ambient) {
        ((PotionMeta)itemMeta).addCustomEffect(new PotionEffect(effectType, duration, amplifier, ambient), true);
        return this;
    }

    public CustomPotionBuilder withPotionColor(Color color) {
        ((PotionMeta)itemMeta).setColor(color);
        return this;
    }

    @Override
    public CustomPotionBuilder link() {
        return this;
    }
}
