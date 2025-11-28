package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomArmorMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ChickenJockey extends CustomArmorMob {
    public final Main plugin;
    private boolean isAdult;

    protected ChickenJockey(ChickenJockeyBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.mainHandWeapon = builder.mainHandWeapon;
        this.offHandWeapon = builder.offHandWeapon;
        this.armor = builder.armor;
        this.isAdult = builder.isAdult;
    }

    @Override
    public LivingEntity summon(Location location) {
        Zombie zombie = (Zombie) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.ZOMBIE);
        zombie.teleport(location);
        zombie.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, getMobId());
        zombie.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        zombie.setHealth(maxHealth);
        zombie.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if (isAdult)
            zombie.setAdult();
        else
            zombie.setBaby();
        zombie.getEquipment().setItemInMainHand(null);
        zombie.getEquipment().setItemInOffHand(null);
        if(mainHandWeapon != null)
            zombie.getEquipment().setItemInMainHand(mainHandWeapon);
        if(offHandWeapon != null)
            zombie.getEquipment().setItemInOffHand(offHandWeapon);
        zombie.getEquipment().setHelmet(null);
        zombie.getEquipment().setChestplate(null);
        zombie.getEquipment().setLeggings(null);
        zombie.getEquipment().setBoots(null);
        if(armor.length >= 1 && armor[0] != null)
            zombie.getEquipment().setHelmet(armor[0]);
        if(armor.length >= 2 && armor[1] != null)
            zombie.getEquipment().setChestplate(armor[1]);
        if(armor.length >= 3 && armor[2] != null)
            zombie.getEquipment().setLeggings(armor[2]);
        if(armor.length >= 4 && armor[3] != null)
            zombie.getEquipment().setBoots(armor[3]);

        Chicken chicken = (Chicken) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.CHICKEN);
        chicken.teleport(location);
        chicken.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        chicken.setHealth(maxHealth);
        chicken.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        chicken.addPassenger(zombie);

        return zombie;
    }

    public static class ChickenJockeyBuilder extends CustomArmorMobBuilder<ChickenJockeyBuilder, ChickenJockey> {
        public boolean isAdult;

        public ChickenJockeyBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
            this.isAdult = true;
        }

        public ChickenJockeyBuilder withAdult(boolean isAdult) {
            this.isAdult = isAdult;
            return this;
        }

        @Override
        public ChickenJockeyBuilder link() {
            return this;
        }

        @Override
        public ChickenJockey build() {
            return new ChickenJockey(this);
        }
    }
}
