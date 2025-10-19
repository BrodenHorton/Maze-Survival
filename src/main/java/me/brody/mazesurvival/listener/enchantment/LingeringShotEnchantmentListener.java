package me.brody.mazesurvival.listener.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.CustomEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class LingeringShotEnchantmentListener implements Listener {
    private static final Map<Material, Map<Integer, PotionEffect>> lingeringShotEffects;

    private final Main plugin;

    static {
        lingeringShotEffects = new HashMap<>();

        Map<Integer, PotionEffect> poisonPotionEffectByLevel = new HashMap<>();
        poisonPotionEffectByLevel.put(1, new PotionEffect(PotionEffectType.POISON, 20 * 5, 0, false));
        poisonPotionEffectByLevel.put(2, new PotionEffect(PotionEffectType.POISON, 20 * 8, 0, false));
        poisonPotionEffectByLevel.put(3, new PotionEffect(PotionEffectType.POISON, 20 * 8, 1, false));
        lingeringShotEffects.put(Material.SPIDER_EYE, poisonPotionEffectByLevel);

        Map<Integer, PotionEffect> slownessPotionEffectByLevel = new HashMap<>();
        slownessPotionEffectByLevel.put(1, new PotionEffect(PotionEffectType.SLOWNESS, 20 * 4, 0, false));
        slownessPotionEffectByLevel.put(2, new PotionEffect(PotionEffectType.SLOWNESS, 20 * 6, 0, false));
        slownessPotionEffectByLevel.put(3, new PotionEffect(PotionEffectType.SLOWNESS, 20 * 5, 1, false));
        lingeringShotEffects.put(Material.COBWEB, slownessPotionEffectByLevel);

        Map<Integer, PotionEffect> speedPotionEffectByLevel = new HashMap<>();
        speedPotionEffectByLevel.put(1, new PotionEffect(PotionEffectType.SPEED, 20 * 8, 0, false));
        speedPotionEffectByLevel.put(2, new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0, false));
        speedPotionEffectByLevel.put(3, new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1, false));
        lingeringShotEffects.put(Material.SUGAR, speedPotionEffectByLevel);

        Map<Integer, PotionEffect> regenerationPotionEffectByLevel = new HashMap<>();
        regenerationPotionEffectByLevel.put(1, new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, 0, false));
        regenerationPotionEffectByLevel.put(2, new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 0, false));
        regenerationPotionEffectByLevel.put(3, new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, 1, false));
        lingeringShotEffects.put(Material.GHAST_TEAR, regenerationPotionEffectByLevel);

        Map<Integer, PotionEffect> witherPotionEffectByLevel = new HashMap<>();
        witherPotionEffectByLevel.put(1, new PotionEffect(PotionEffectType.WITHER, 20 * 6, 0, false));
        witherPotionEffectByLevel.put(2, new PotionEffect(PotionEffectType.WITHER, 20 * 10, 0, false));
        witherPotionEffectByLevel.put(3, new PotionEffect(PotionEffectType.WITHER, 20 * 10, 1, false));
        lingeringShotEffects.put(Material.WITHER_ROSE, witherPotionEffectByLevel);
    }

    public LingeringShotEnchantmentListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void lingeringShotHitEntityEffect(ProjectileHitEvent e) {
        if(!(e.getEntity() instanceof Arrow))
            return;
        if(e.getHitEntity() == null || !(e.getHitEntity() instanceof LivingEntity))
            return;
        LivingEntity hitEntity = (LivingEntity)e.getHitEntity();
        if(!(e.getEntity().getShooter() instanceof Player))
            return;
        Player player = (Player)e.getEntity().getShooter();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if(!mainHandItem.getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS))
            return;
        EnchantmentList enchantmentList = mainHandItem.getItemMeta().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
        EnchantmentEntry lingeringShotEnchantment = null;
        for(EnchantmentEntry enchantment : enchantmentList.getEnchantmentEntries()) {
            if(enchantment.getEnchantmentName().equals(CustomEnchantment.LINGERING_SHOT.getEnchantmentName())) {
                lingeringShotEnchantment = enchantment;
                break;
            }
        }
        if(lingeringShotEnchantment == null)
            return;
        if(!lingeringShotEffects.containsKey(player.getInventory().getItemInOffHand().getType()))
            return;
        PotionEffect potionEffect = lingeringShotEffects.get(player.getInventory().getItemInOffHand().getType()).get(lingeringShotEnchantment.getLevel());
        if(potionEffect == null)
            return;

        hitEntity.addPotionEffect(potionEffect);
        if(player.getInventory().getItemInOffHand() != null)
            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
    }

    @EventHandler
    public void lingeringShotHitBlockEffect(ProjectileHitEvent e) {
        if(!(e.getEntity() instanceof Arrow))
            return;
        if(e.getHitBlock() == null)
            return;
        if(!(e.getEntity().getShooter() instanceof Player))
            return;
        Player player = (Player)e.getEntity().getShooter();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if(!mainHandItem.getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS))
            return;
        EnchantmentList enchantmentList = mainHandItem.getItemMeta().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
        EnchantmentEntry lingeringShotEnchantment = null;
        for(EnchantmentEntry enchantment : enchantmentList.getEnchantmentEntries()) {
            if(enchantment.getEnchantmentName().equals(CustomEnchantment.LINGERING_SHOT.getEnchantmentName())) {
                lingeringShotEnchantment = enchantment;
                break;
            }
        }
        if(lingeringShotEnchantment == null)
            return;
        if(!lingeringShotEffects.containsKey(player.getInventory().getItemInOffHand().getType()))
            return;
        PotionEffect potionEffect = lingeringShotEffects.get(player.getInventory().getItemInOffHand().getType()).get(lingeringShotEnchantment.getLevel());
        if(potionEffect == null)
            return;

        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
        potionMeta.addCustomEffect(potionEffect, false);
        potion.setItemMeta(potionMeta);

        Location targetLocation = LocationUtils.copy(e.getHitBlock().getLocation());
        targetLocation.setY(targetLocation.getY() + 1);
        AreaEffectCloud lingeringPotion = (AreaEffectCloud)targetLocation.getWorld().spawnEntity(targetLocation, EntityType.AREA_EFFECT_CLOUD);
        lingeringPotion.addCustomEffect(potionEffect, false);
        lingeringPotion.setDuration(100);

        if(player.getInventory().getItemInOffHand() != null)
            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
    }
}
