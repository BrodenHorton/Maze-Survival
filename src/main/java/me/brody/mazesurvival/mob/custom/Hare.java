package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomMobBuilder;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hare extends CustomMob {
    public final Main plugin;

    private Rabbit.Type rabbitType;

    protected Hare(HareBuilder builder) {
        this.plugin = builder.plugin;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.rabbitType = builder.rabbitType;
    }

    @Override
    public LivingEntity summon(Location location) {
        Rabbit rabbit;
        rabbit = (Rabbit) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.RABBIT);
        rabbit.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        rabbit.setHealth(maxHealth);
        rabbit.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            rabbit.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        rabbit.setRabbitType(rabbitType);

        return rabbit;
    }

    public static class HareBuilder extends CustomMobBuilder<HareBuilder, Hare> {
        public Rabbit.Type rabbitType;

        public HareBuilder(Main plugin) {
            super(plugin);
            this.rabbitType = Rabbit.Type.BROWN;
        }

        public HareBuilder withRabbitType(Rabbit.Type rabbitType) {
            this.rabbitType = rabbitType;
            return this;
        }

        @Override
        public HareBuilder link() {
            return this;
        }

        @Override
        public Hare build() {
            return new Hare(this);
        }
    }
}
