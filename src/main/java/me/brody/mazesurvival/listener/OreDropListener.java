package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OreDropListener implements Listener {
    private static final Random RNG = new Random();
    private final Main plugin;

    public OreDropListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void amethystClusterDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.AMETHYST_CLUSTER)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.AMETHYST_SHARD, dropCount);
        Location dropLocation = LocationUtils.copy(e.getBlock().getLocation());
        dropLocation = LocationUtils.centerOnBlock(dropLocation);
        e.getBlock().getWorld().dropItemNaturally(dropLocation, customDrop);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void copperOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.COPPER_ORE)
            return;

        int tinDropCount = RNG.nextInt(0, 3);
        ItemStack customDrop = CustomItem.TIN.getItemStack(tinDropCount);
        Location dropLocation = LocationUtils.copy(e.getBlock().getLocation());
        dropLocation = LocationUtils.centerOnBlock(dropLocation);
        e.getBlock().getWorld().dropItemNaturally(dropLocation, customDrop);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void lapisOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.LAPIS_ORE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.LAPIS_LAZULI, dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void netherGoldOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.NETHER_GOLD_ORE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(2, 5);
        ItemStack customDrop = CustomItem.SUN_GOLD_NUGGET.getItemStack(dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void netherQuartzOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.NETHER_QUARTZ_ORE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.QUARTZ, dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

}
