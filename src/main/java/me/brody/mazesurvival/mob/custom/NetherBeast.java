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

public class NetherBeast extends CustomMob {
    public final Main plugin;

    protected NetherBeast(NetherBeastBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.mobName = builder.mobName;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
    }

    @Override
    public LivingEntity summon(Location location) {
        Hoglin hoglin;
        hoglin = (Hoglin) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.HOGLIN);
        hoglin.teleport(location);
        hoglin.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, getMobId());
        hoglin.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        hoglin.setHealth(maxHealth);
        hoglin.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            hoglin.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));
        if(isBaby)
            hoglin.setBaby();
        else
            hoglin.setAdult();
        hoglin.setImmuneToZombification(true);

        return hoglin;
    }

    public static class NetherBeastBuilder extends CustomMobBuilder<NetherBeastBuilder, NetherBeast> {
        public NetherBeastBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
        }

        @Override
        public NetherBeastBuilder link() {
            return this;
        }

        @Override
        public NetherBeast build() {
            return new NetherBeast(this);
        }
    }
}
