package me.brody.mazesurvival.mob.builder;

import me.brody.mazesurvival.Main;
import org.bukkit.inventory.ItemStack;

public abstract class CustomArmorMobBuilder<T, U> extends CustomMobBuilder<T, U> {
    public ItemStack mainHandWeapon;
    public ItemStack offHandWeapon;
    public ItemStack[] armor;

    public CustomArmorMobBuilder(Main plugin, String mobName) {
        super(plugin, mobName);
        armor = new ItemStack[4];
    }

    public T withMainHand(ItemStack item) {
        this.mainHandWeapon = item;
        return link();
    }

    public T withOffHand(ItemStack item) {
        this.offHandWeapon = item;
        return link();
    }

    public T withHelmet(ItemStack item) {
        this.armor[0] = item;
        return link();
    }

    public T withChestplate(ItemStack item) {
        this.armor[1] = item;
        return link();
    }

    public T withLeggings(ItemStack item) {
        this.armor[2] = item;
        return link();
    }

    public T withBoots(ItemStack item) {
        this.armor[3] = item;
        return link();
    }
}
