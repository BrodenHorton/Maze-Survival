package me.brody.mazesurvival.listener.mob;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpawnEggListener implements Listener {

    public SpawnEggListener() {}

    @EventHandler
    public void useTinyMagmaOozeSpawnEgg(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        ItemStack item = e.getItem();
        if(item == null)
            return;
        if(item.getType() != Material.MAGMA_CUBE_SPAWN_EGG)
            return;
        PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.CUSTOM_SPAWN_EGG))
            return;
        if(!dataContainer.get(NamespacedKeys.CUSTOM_SPAWN_EGG, PersistentDataType.STRING).equalsIgnoreCase(CustomMob.TINY_MAGMA_OOZE.getMobId()))
            return;

        e.setCancelled(true);
        e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
        Location spawnLocation = LocationUtils.copy(e.getClickedBlock().getLocation());
        spawnLocation.setY(spawnLocation.getY() + 1);
        MagmaCube magmaCube = (MagmaCube) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.MAGMA_CUBE);
        magmaCube.setSize(0);
    }
}
