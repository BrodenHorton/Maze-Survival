package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ooze extends CustomMob {
    public final Main plugin;

    private int size;
    private boolean isMagmaCube;

    protected Ooze(OozeBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.mobName = builder.mobName;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
        this.size = builder.size;
        this.isMagmaCube = builder.isMagmaCube;
    }

    @Override
    public LivingEntity summon(Location location) {
        Slime slime;
        if(isMagmaCube)
            slime = (MagmaCube) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.MAGMA_CUBE);
        else
            slime = (Slime) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.SLIME);
        slime.teleport(location);
        slime.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        slime.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        slime.setHealth(maxHealth);
        slime.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            slime.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        slime.setSize(size);

        return slime;
    }

    public static class OozeBuilder extends CustomMobBuilder<OozeBuilder, Ooze> {
        private int size;
        private boolean isMagmaCube;

        public OozeBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
            size = 1;
            isMagmaCube = false;
        }

        public OozeBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        public OozeBuilder withMagmaCube(boolean isMagmaCube) {
            this.isMagmaCube = isMagmaCube;
            return this;
        }

        @Override
        public OozeBuilder link() {
            return this;
        }

        @Override
        public Ooze build() {
            return new Ooze(this);
        }
    }
}
