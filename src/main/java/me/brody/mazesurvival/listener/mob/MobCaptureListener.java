package me.brody.mazesurvival.listener.mob;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

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
        spawnEggByMob.put(EntityType.FROG, new ItemStack(Material.FROG_SPAWN_EGG));
        spawnEggByMob.put(EntityType.TURTLE, new ItemStack(Material.TURTLE_SPAWN_EGG));
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

    @EventHandler
    public void captureTinyMagmaOoze(ProjectileHitEvent e) {
        if(!(e.getEntity() instanceof Egg projectile))
            return;
        if(e.getHitEntity() == null)
            return;
        EntityType entityType = e.getHitEntity().getType();
        if(entityType != EntityType.MAGMA_CUBE)
            return;
        if(!e.getHitEntity().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_MOB))
            return;
        if(!e.getHitEntity().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_MOB, PersistentDataType.STRING).equalsIgnoreCase(CustomMob.TINY_MAGMA_OOZE.getMobId()))
            return;

        Location location = e.getHitEntity().getLocation();
        ItemStack spawnEgg = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG);
        ItemMeta meta = spawnEgg.getItemMeta();
        meta.getPersistentDataContainer().set(NamespacedKeys.CUSTOM_SPAWN_EGG, PersistentDataType.STRING, CustomMob.TINY_MAGMA_OOZE.getMobId());
        spawnEgg.setItemMeta(meta);
        location.getWorld().dropItem(location, spawnEgg);
        location.getWorld().playSound(location, Sound.BLOCK_BEEHIVE_ENTER, 1f, 1.8f);
        projectile.remove();
        e.getHitEntity().remove();
        e.setCancelled(true);
    }

}
