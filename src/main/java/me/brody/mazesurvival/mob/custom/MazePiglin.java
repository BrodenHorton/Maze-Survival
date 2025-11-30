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

public class MazePiglin extends CustomArmorMob {
    public final Main plugin;

    private boolean isBrute;

    protected MazePiglin(MazePiglinBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.mainHandWeapon = builder.mainHandWeapon;
        this.offHandWeapon = builder.offHandWeapon;
        this.armor = builder.armor;
        this.isBrute = builder.isBrute;
    }

    @Override
    public LivingEntity summon(Location location) {
        PiglinAbstract mob;
        if(isBrute)
            mob = (PiglinBrute) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.PIGLIN_BRUTE);
        else
            mob = (Piglin) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.PIGLIN);
        mob.teleport(location);
        mob.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, getMobId());
        mob.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        mob.setHealth(maxHealth);
        mob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            mob.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        mob.getEquipment().setItemInMainHand(null);
        mob.getEquipment().setItemInOffHand(null);
        if(mainHandWeapon != null)
            mob.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            mob.getEquipment().setItemInOffHand(offHandWeapon);
        mob.getEquipment().setHelmet(null);
        mob.getEquipment().setChestplate(null);
        mob.getEquipment().setLeggings(null);
        mob.getEquipment().setBoots(null);
        if(armor.length >= 1 && armor[0] != null)
            mob.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            mob.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            mob.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            mob.getEquipment().setBoots(armor[3]);
        mob.setImmuneToZombification(true);

        return mob;
    }

    public static class MazePiglinBuilder extends CustomArmorMobBuilder<MazePiglinBuilder, MazePiglin> {
        private boolean isBrute;

        public MazePiglinBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
            isBrute = false;
        }

        public MazePiglinBuilder withBrute(boolean isBrute) {
            this.isBrute = isBrute;
            return this;
        }

        @Override
        public MazePiglinBuilder link() {
            return this;
        }

        @Override
        public MazePiglin build() {
            return new MazePiglin(this);
        }
    }
}
