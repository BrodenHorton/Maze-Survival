package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.loot.chest.ChestFiller;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.block.TileState;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ChestListener implements Listener {
    private final Main plugin;

    public ChestListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void generateMazeChestLoot(InventoryOpenEvent e) {
        if(plugin.getMazeManager() == null || plugin.getMazeManager().getGrid() == null)
            return;
        if(!e.getInventory().getType().equals(InventoryType.CHEST))
            return;
        if(!(e.getInventory().getHolder() instanceof Chest))
            return;
        Chest chest = (Chest)e.getInventory().getHolder();
        if(!(chest.getBlock().getState() instanceof TileState tileState))
            return;
        if(!tileState.getPersistentDataContainer().has(NamespacedKeys.GENERATE_LOOT))
            return;
        Player player = (Player)e.getPlayer();
        MazeRegion region = plugin.getMazeManager().getGrid().getRegionAt(player.getLocation());
        if(region == null)
            return;

        tileState.getPersistentDataContainer().remove(NamespacedKeys.GENERATE_LOOT);
        tileState.update(); // Update must be called on BlockState before adding items to the inventory for some reason

        ChestFiller chestFiller = new ChestFiller(region.getLootTable(), 3, 5);
        chestFiller.generateInventoryLoot(e.getInventory());
    }

    @EventHandler
    public void destroyMazeChestOnClose(InventoryCloseEvent e) {
        if(plugin.getMazeManager() == null || plugin.getMazeManager().getGrid() == null)
            return;
        if(!e.getInventory().getType().equals(InventoryType.CHEST))
            return;
        if(!(e.getInventory().getHolder() instanceof Chest chest))
            return;
        if(!(chest.getBlock().getState() instanceof TileState tileState))
            return;
        if(!tileState.getPersistentDataContainer().has(NamespacedKeys.BREAK_ON_CLOSE))
            return;
        Player player = (Player)e.getPlayer();
        MazeRegion region = plugin.getMazeManager().getGrid().getRegionAt(player.getLocation());
        if(region == null)
            return;

        chest.getLocation().getWorld().spawnParticle(Particle.BLOCK, chest.getLocation(), 50, 0.5, 0.5, 0.5, chest.getBlock().getBlockData());
        chest.getLocation().getWorld().playSound(chest.getLocation(), Sound.BLOCK_WOOD_BREAK, 1f, 1f);
        chest.getBlock().breakNaturally(new ItemStack(Material.DIAMOND_AXE, 1));
    }

    @EventHandler
    public void removeMazeChestItemEntity(EntitySpawnEvent e) {
        if(plugin.getMazeManager() == null || plugin.getMazeManager().getGrid() == null)
            return;
        if(!(e.getEntity() instanceof Item item))
            return;
        if(item.getItemStack().getType() != Material.CHEST)
            return;
        MazeRegion region = plugin.getMazeManager().getGrid().getRegionAt(e.getLocation());
        if(region == null)
            return;

        item.remove();
    }

    @EventHandler
    public void generateLootOnPotBreak(BlockBreakEvent e) {
        if(plugin.getMazeManager() == null || plugin.getMazeManager().getGrid() == null)
            return;
        if(e.getBlock().getType() != Material.DECORATED_POT)
            return;
        if(!(e.getBlock().getState() instanceof TileState tileState))
            return;
        if(!tileState.getPersistentDataContainer().has(NamespacedKeys.GENERATE_LOOT))
            return;
        MazeRegion region = plugin.getMazeManager().getGrid().getRegionAt(e.getBlock().getLocation());
        if(region == null)
            return;

        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);
        List<ItemStack> items = new ArrayList<>();
        List<Supplier<ItemStack>> lootTableEntries = region.getLootTable().getWeightedValues(1, 2);
        for(Supplier<ItemStack> entry : lootTableEntries)
            items.add(entry.get());
        for(int i = 0; i < items.size(); i++)
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), items.get(i));
    }

}
