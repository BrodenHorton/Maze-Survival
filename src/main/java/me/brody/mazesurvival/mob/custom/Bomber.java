package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Bomber extends CustomMob {
    public final Main plugin;

    private boolean isPowered;

    protected Bomber(BomberBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.mobName = builder.mobName;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.isPowered = builder.isPowered;
    }

    @Override
    public LivingEntity summon(Location location) {
        Creeper creeper = (Creeper)plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.CREEPER);
        creeper.teleport(location);
        creeper.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, getMobId());
        creeper.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        creeper.setHealth(maxHealth);
        creeper.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        creeper.setPowered(isPowered);

        return creeper;
    }

    public static class BomberBuilder extends CustomMobBuilder<BomberBuilder, Bomber> {
        private boolean isPowered;

        public BomberBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
            isPowered = false;
        }

        public BomberBuilder withPowered(boolean isPowered) {
            this.isPowered = isPowered;
            return this;
        }

        @Override
        public BomberBuilder link() {
            return this;
        }

        @Override
        public Bomber build() {
            return new Bomber(this);
        }
    }
}
