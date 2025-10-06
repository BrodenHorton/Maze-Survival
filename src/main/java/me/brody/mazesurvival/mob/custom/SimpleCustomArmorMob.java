package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomArmorMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SimpleCustomArmorMob extends CustomArmorMob {
    public final Main plugin;

    private EntityType entityType;

    protected SimpleCustomArmorMob(SimpleCustomArmorMobBuilder builder) {
        this.plugin = builder.plugin;
        this.mobName = builder.mobName;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.mainHandWeapon = builder.mainHandWeapon;
        this.offHandWeapon = builder.offHandWeapon;
        this.armor = builder.armor;
        this.entityType = builder.entityType;
    }

    @Override
    public LivingEntity summon(Location location) {
        Mob mob = (Mob) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, entityType);
        mob.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        mob.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        mob.setHealth(maxHealth);
        mob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            mob.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if(mainHandWeapon != null)
            mob.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            mob.getEquipment().setItemInOffHand(offHandWeapon);
        if(armor.length >= 1 && armor[0] != null)
            mob.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            mob.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            mob.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            mob.getEquipment().setBoots(armor[3]);

        return mob;
    }

    public static class SimpleCustomArmorMobBuilder extends CustomArmorMobBuilder<SimpleCustomArmorMobBuilder, SimpleCustomArmorMob> {
        private EntityType entityType;

        public SimpleCustomArmorMobBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
        }

        public SimpleCustomArmorMobBuilder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        @Override
        public SimpleCustomArmorMobBuilder link() {
            return this;
        }

        @Override
        public SimpleCustomArmorMob build() {
            return new SimpleCustomArmorMob(this);
        }
    }

}
