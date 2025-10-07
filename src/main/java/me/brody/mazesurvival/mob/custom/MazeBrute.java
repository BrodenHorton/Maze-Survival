package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomArmorMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class MazeBrute extends CustomArmorMob {
    public final Main plugin;

    protected MazeBrute(MazeBruteBuilder builder) {
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
        PiglinBrute brute = (PiglinBrute) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.PIGLIN_BRUTE);
        brute.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        brute.setImmuneToZombification(true);
        brute.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        brute.setHealth(maxHealth);
        brute.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            brute.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if(mainHandWeapon != null)
            brute.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            brute.getEquipment().setItemInOffHand(offHandWeapon);
        Arrays.fill(brute.getEquipment().getArmorContents(), null);
        if(armor.length >= 1 && armor[0] != null)
            brute.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            brute.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            brute.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            brute.getEquipment().setBoots(armor[3]);

        return brute;
    }

    public static class MazeBruteBuilder extends CustomArmorMobBuilder<MazeBruteBuilder, MazeBrute> {

        public MazeBruteBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
        }

        @Override
        public MazeBruteBuilder link() {
            return this;
        }

        @Override
        public MazeBrute build() {
            return new MazeBrute(this);
        }
    }
}
