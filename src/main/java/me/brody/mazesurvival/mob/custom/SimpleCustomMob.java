package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class SimpleCustomMob extends CustomMob {
    public final Main plugin;

    private EntityType entityType;

    protected SimpleCustomMob(SimpleCustomMobBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.entityType = builder.entityType;
    }

    @Override
    public LivingEntity summon(Location location) {
        Mob mob = (Mob) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, entityType);
        mob.teleport(location);
        mob.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        mob.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        mob.setHealth(maxHealth);
        mob.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            mob.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));

        return mob;
    }

    public static class SimpleCustomMobBuilder extends CustomMobBuilder<SimpleCustomMobBuilder, SimpleCustomMob> {
        private EntityType entityType;

        public SimpleCustomMobBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
        }

        public SimpleCustomMobBuilder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        @Override
        public SimpleCustomMobBuilder link() {
            return this;
        }

        @Override
        public SimpleCustomMob build() {
            return new SimpleCustomMob(this);
        }
    }
}
