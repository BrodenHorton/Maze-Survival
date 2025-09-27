package me.brody.mazesurvival.mob.custom;

import org.bukkit.inventory.ItemStack;

public abstract class CustomArmorMob extends CustomMob {
    protected ItemStack mainHandWeapon;
    protected ItemStack offHandWeapon;
    protected ItemStack[] armor;

    public CustomArmorMob() {}
}
