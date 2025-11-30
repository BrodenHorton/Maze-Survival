package me.brody.mazesurvival.item.builder;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class CustomArmorBuilder extends CustomDurabilityItemBuilder<CustomArmorBuilder> {

    public CustomArmorBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    public CustomArmorBuilder withColor(Color color) {
        return withColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public CustomArmorBuilder withColor(int red, int green, int blue) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        ((LeatherArmorMeta)itemMeta).setColor(Color.fromRGB(red, green, blue));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    @Override
    public CustomArmorBuilder link() {
        return this;
    }
}
