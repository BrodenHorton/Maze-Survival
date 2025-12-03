package me.brody.mazesurvival.listener.mob;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpawnEggListener implements Listener {
    private final Main plugin;

    public SpawnEggListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void useTinyMagmaOozeSpawnEgg(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        ItemStack item = e.getItem();
        if(item.getType() != Material.MAGMA_CUBE_SPAWN_EGG)
            return;
        PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
        if(!dataContainer.has(NamespacedKeys.CUSTOM_SPAWN_EGG))
            return;
        CustomMob mob = Registry.CUSTOM_MOB.get(dataContainer.get(NamespacedKeys.CUSTOM_SPAWN_EGG, PersistentDataType.STRING));
        if(mob == null)
            return;

        mob.summon(e.getClickedBlock().getLocation());
    }
}
