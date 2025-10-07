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

import java.util.Arrays;

public class MazePiglin extends CustomArmorMob {
    public final Main plugin;

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
    }

    @Override
    public LivingEntity summon(Location location) {
        Piglin piglin = (Piglin) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.PIGLIN);
        piglin.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        piglin.setImmuneToZombification(true);
        piglin.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        piglin.setHealth(maxHealth);
        piglin.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            piglin.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if(mainHandWeapon != null)
            piglin.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            piglin.getEquipment().setItemInOffHand(offHandWeapon);
        Arrays.fill(piglin.getEquipment().getArmorContents(), null);
        if(armor.length >= 1 && armor[0] != null)
            piglin.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            piglin.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            piglin.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            piglin.getEquipment().setBoots(armor[3]);

        return piglin;
    }

    public static class MazePiglinBuilder extends CustomArmorMobBuilder<MazePiglinBuilder, MazePiglin> {

        public MazePiglinBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
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
