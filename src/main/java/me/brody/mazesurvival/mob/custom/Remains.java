package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomArmorMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Remains extends CustomArmorMob {
    public final Main plugin;

    private boolean isBogged;
    private boolean isStray;

    protected Remains(RemainsBuilder builder) {
        this.plugin = builder.plugin;
        this.mobName = builder.mobName;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.mainHandWeapon = builder.mainHandWeapon;
        this.offHandWeapon = builder.offHandWeapon;
        this.armor = builder.armor;
        this.isBogged = builder.isBogged;
        this.isStray = builder.isStray;
    }

    @Override
    public LivingEntity summon(Location location) {
        AbstractSkeleton skeleton;
        if(isBogged)
            skeleton = (Bogged) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.BOGGED);
        else if(isStray)
            skeleton = (Stray) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.STRAY);
        else
            skeleton = (Skeleton) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.SKELETON);
        skeleton.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        skeleton.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        skeleton.setHealth(maxHealth);
        skeleton.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            skeleton.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if(mainHandWeapon != null)
            skeleton.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            skeleton.getEquipment().setItemInOffHand(offHandWeapon);
        if(armor.length >= 1 && armor[0] != null)
            skeleton.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            skeleton.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            skeleton.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            skeleton.getEquipment().setBoots(armor[3]);

        return skeleton;
    }

    public static class RemainsBuilder extends CustomArmorMobBuilder<RemainsBuilder, Remains> {
        private boolean isBogged;
        private boolean isStray;

        public RemainsBuilder(Main plugin, String mobName) {
            super(plugin,mobName);
            isBogged = false;
            isStray = false;
        }

        public RemainsBuilder withBogged(boolean isBogged) {
            this.isBogged = isBogged;
            return this;
        }

        public RemainsBuilder withStray(boolean isStray) {
            this.isStray = isStray;
            return this;
        }

        @Override
        public RemainsBuilder link() {
            return this;
        }

        @Override
        public Remains build() {
            return new Remains(this);
        }
    }
}
