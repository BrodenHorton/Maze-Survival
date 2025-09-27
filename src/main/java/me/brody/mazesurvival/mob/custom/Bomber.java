package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomMobBuilder;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Bomber extends CustomMob {
    public final Main plugin;

    private boolean isPowered;

    protected Bomber(BomberBuilder builder) {
        this.plugin = builder.plugin;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.isPowered = builder.isPowered;
    }

    @Override
    public LivingEntity summon(Location location) {
        Creeper creeper = (Creeper)plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.CREEPER);
        creeper.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        creeper.setHealth(maxHealth);
        creeper.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        creeper.setPowered(isPowered);

        return creeper;
    }

    public static class BomberBuilder extends CustomMobBuilder<BomberBuilder, Bomber> {
        private boolean isPowered;

        public BomberBuilder(Main plugin) {
            super(plugin);
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
