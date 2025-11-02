package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MobCaptureListener implements Listener {
    private static final Map<EntityType, ItemStack> spawnEggByMob;
    private final Main plugin;

    static {
        spawnEggByMob = new HashMap<>();
        spawnEggByMob.put(EntityType.CHICKEN, new ItemStack(Material.CHICKEN_SPAWN_EGG));
        spawnEggByMob.put(EntityType.PIG, new ItemStack(Material.PIG_SPAWN_EGG));
        spawnEggByMob.put(EntityType.COW, new ItemStack(Material.COW_SPAWN_EGG));
        spawnEggByMob.put(EntityType.SHEEP, new ItemStack(Material.SHEEP_SPAWN_EGG));
        spawnEggByMob.put(EntityType.SQUID, new ItemStack(Material.SQUID_SPAWN_EGG));
        spawnEggByMob.put(EntityType.GLOW_SQUID, new ItemStack(Material.GLOW_SQUID_SPAWN_EGG));
    }

    public MobCaptureListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void captureMob(ProjectileHitEvent e) {
        if(!(e.getEntity() instanceof Egg projectile))
            return;
        if(e.getHitEntity() == null)
            return;
        EntityType entityType = e.getHitEntity().getType();
        if(!spawnEggByMob.containsKey(entityType))
            return;

        Location location = e.getHitEntity().getLocation();
        location.getWorld().dropItem(location, spawnEggByMob.get(entityType));
        location.getWorld().playSound(location, Sound.BLOCK_BEEHIVE_ENTER, 1f, 1.8f);
        projectile.remove();
        e.getHitEntity().remove();
        e.setCancelled(true);
    }

}
