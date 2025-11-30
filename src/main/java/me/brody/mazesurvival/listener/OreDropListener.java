package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.GameMode;
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

    public OreDropListener() {}

    @EventHandler(priority = EventPriority.HIGHEST)
    public void amethystClusterDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.AMETHYST_CLUSTER)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.AMETHYST_SHARD, dropCount);
        Location dropLocation = LocationUtils.copy(e.getBlock().getLocation());
        dropLocation = LocationUtils.centerOnBlock(dropLocation);
        e.getBlock().getWorld().dropItemNaturally(dropLocation, customDrop);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void copperOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.COPPER_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        int tinDropCount = RNG.nextInt(0, 3);
        ItemStack tinDrop = CustomItem.TIN.getItemStack(tinDropCount);
        Location dropLocation = LocationUtils.copy(e.getBlock().getLocation());
        dropLocation = LocationUtils.centerOnBlock(dropLocation);
        e.getBlock().getWorld().dropItemNaturally(dropLocation, new ItemStack(Material.RAW_COPPER));
        e.getBlock().getWorld().dropItemNaturally(dropLocation, tinDrop);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void lapisOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.LAPIS_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.LAPIS_LAZULI, dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void ironOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.IRON_ORE && e.getBlock().getType() != Material.DEEPSLATE_IRON_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.RAW_IRON));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void netherGoldOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.NETHER_GOLD_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(2, 5);
        ItemStack customDrop = CustomItem.SUN_GOLD_NUGGET.getItemStack(dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void netherQuartzOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.NETHER_QUARTZ_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        int dropCount = RNG.nextInt(1, 4);
        ItemStack customDrop = new ItemStack(Material.QUARTZ, dropCount);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), customDrop);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void diamondOreDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.DIAMOND_ORE && e.getBlock().getType() != Material.DEEPSLATE_DIAMOND_ORE)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void ancientDebrisDrop(BlockBreakEvent e) {
        if(e.isCancelled())
            return;
        if(e.getBlock().getType() != Material.ANCIENT_DEBRIS)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        e.setDropItems(false);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.ANCIENT_DEBRIS));
    }
}
