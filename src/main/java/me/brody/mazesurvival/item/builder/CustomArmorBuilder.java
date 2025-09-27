package me.brody.mazesurvival.item.builder;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CustomArmorBuilder extends CustomItemBuilder<CustomArmorBuilder> {

    public CustomArmorBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    public CustomArmorBuilder withColor(Color color) {
        return withColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public CustomArmorBuilder withColor(int red, int green, int blue) {
        ((LeatherArmorMeta)itemMeta).setColor(Color.fromRGB(red, green, blue));
        return this;
    }

    @Override
    public CustomArmorBuilder link() {
        return this;
    }
}
