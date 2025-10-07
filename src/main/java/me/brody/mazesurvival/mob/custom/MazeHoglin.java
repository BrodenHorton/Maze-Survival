package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.builder.CustomArmorMobBuilder;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MazeHoglin extends CustomArmorMob {
    public final Main plugin;

    protected MazeHoglin(MazeHoglinBuilder builder) {
        super(builder.mobName);
        this.plugin = builder.plugin;
        this.dropTable = builder.dropTable;
        this.maxHealth = builder.maxHealth;
        this.movementSpeed = builder.movementSpeed;
        this.powerAmplifier = builder.powerAmplifier;
    }

    @Override
    public LivingEntity summon(Location location) {
        Hoglin hoglin = (Hoglin) plugin.getServer().getWorld(location.getWorld().getUID()).spawnEntity(location, EntityType.HOGLIN);
        hoglin.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING, mobName.toLowerCase().replace(' ', '-'));
        hoglin.setImmuneToZombification(true);
        hoglin.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        hoglin.setHealth(maxHealth);
        hoglin.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        if(powerAmplifier >= 0)
            hoglin.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200000, powerAmplifier, true));

        return hoglin;
    }

    public static class MazeHoglinBuilder extends CustomArmorMobBuilder<MazeHoglinBuilder, MazeHoglin> {

        public MazeHoglinBuilder(Main plugin, String mobName) {
            super(plugin, mobName);
        }

        @Override
        public MazeHoglinBuilder link() {
            return this;
        }

        @Override
        public MazeHoglin build() {
            return new MazeHoglin(this);
        }
    }
}
