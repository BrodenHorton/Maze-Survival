package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.utils.LocationUtils;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Chest;
import org.bukkit.block.TileState;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class TrapChestListener implements Listener {
    private final Main plugin;

    public TrapChestListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelPlayerTrapChestInteraction(InventoryOpenEvent e) {
        if(!e.getInventory().getType().equals(InventoryType.CHEST))
            return;
        if(!(e.getInventory().getHolder() instanceof Chest))
            return;
        Chest chest = (Chest)e.getInventory().getHolder();
        if(!(chest.getBlock().getState() instanceof TileState tileState))
            return;
        NamespacedKey key = new NamespacedKey(plugin, "trap-chest");
        if(!tileState.getPersistentDataContainer().has(key))
            return;
        Player player = (Player)e.getPlayer();
        MazeRegion region = plugin.getMazeManager().getGrid().getRegionAt(player.getLocation());
        if(region == null)
            return;

        e.setCancelled(true);
        NamespacedKey breakKey = new NamespacedKey(plugin, "break-on-open");
        if(tileState.getPersistentDataContainer().has(breakKey))
            chest.getBlock().setType(Material.AIR);
    }

    @EventHandler
    public void splashPotionTrap(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if(e.getClickedBlock().getType() != Material.TRAPPED_CHEST)
            return;
        if(!(e.getClickedBlock().getState() instanceof TileState tileState))
            return;
        NamespacedKey key = new NamespacedKey(plugin, "trap-chest");
        if(!tileState.getPersistentDataContainer().has(key))
            return;
        if(!tileState.getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("splash-potion"))
            return;

        ItemStack itemStack = new ItemStack(Material.SPLASH_POTION);
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 200, 1), false);

        itemStack.setItemMeta(potionMeta);

        Location splashPotionSpawnLoc = LocationUtils.copy(e.getClickedBlock().getLocation());
        splashPotionSpawnLoc.setX(splashPotionSpawnLoc.getX() + 0.5);
        splashPotionSpawnLoc.setY(splashPotionSpawnLoc.getY() + 1);
        splashPotionSpawnLoc.setZ(splashPotionSpawnLoc.getZ() + 0.5);

        Vector velocity = new Vector(0, 0.25, 0);
        velocity.setX(e.getPlayer().getLocation().getX() - splashPotionSpawnLoc.getX());
        velocity.setZ(e.getPlayer().getLocation().getZ() - splashPotionSpawnLoc.getZ());
        double xzTotal = Math.abs(velocity.getX()) + Math.abs(velocity.getZ());
        velocity.setX(velocity.getX() / xzTotal * 0.5);
        velocity.setZ(velocity.getZ() / xzTotal * 0.5);

        ThrownPotion thrownPotion = (ThrownPotion) e.getPlayer().getWorld().spawnEntity(splashPotionSpawnLoc, EntityType.SPLASH_POTION);
        thrownPotion.setVelocity(velocity);
        thrownPotion.setItem(itemStack);
    }

    @EventHandler
    public void tntTrap(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if(e.getClickedBlock().getType() != Material.TRAPPED_CHEST)
            return;
        if(!(e.getClickedBlock().getState() instanceof TileState tileState))
            return;
        NamespacedKey key = new NamespacedKey(plugin, "trap-chest");
        if(!tileState.getPersistentDataContainer().has(key))
            return;
        if(!tileState.getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("tnt"))
            return;

        Location tntSpawnLoc = LocationUtils.copy(e.getClickedBlock().getLocation());
        tntSpawnLoc.setX(tntSpawnLoc.getX() + 0.5);
        tntSpawnLoc.setY(tntSpawnLoc.getY() + 1);
        tntSpawnLoc.setZ(tntSpawnLoc.getZ() + 0.5);
        TNTPrimed tnt = (TNTPrimed) e.getPlayer().getWorld().spawnEntity(tntSpawnLoc, EntityType.TNT);
        tnt.setFuseTicks(28);
    }

    @EventHandler
    public void ambushTrap(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if(e.getClickedBlock().getType() != Material.TRAPPED_CHEST)
            return;
        if(!(e.getClickedBlock().getState() instanceof TileState tileState))
            return;
        NamespacedKey key = new NamespacedKey(plugin, "trap-chest");
        if(!tileState.getPersistentDataContainer().has(key))
            return;
        if(!tileState.getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("ambush"))
            return;

        e.getClickedBlock().getWorld().strikeLightning(e.getClickedBlock().getLocation());
        Location spawnLocation = LocationUtils.copy(e.getClickedBlock().getLocation());
        spawnLocation = LocationUtils.centerOnBlock(spawnLocation);
        for(int i = 0; i < 4; i++) {
            LivingEntity skeleton = CustomMob.GUARDIAN_REMAINS.summon(spawnLocation);
            skeleton.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0, true));
        }
    }

    @EventHandler
    public void teleportationTrap(PlayerInteractEvent e) {
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        if(e.getClickedBlock().getType() != Material.TRAPPED_CHEST)
            return;
        if(!(e.getClickedBlock().getState() instanceof TileState tileState))
            return;
        NamespacedKey key = new NamespacedKey(plugin, "trap-chest");
        if(!tileState.getPersistentDataContainer().has(key))
            return;
        if(!tileState.getPersistentDataContainer().get(key, PersistentDataType.STRING).equals("teleportation"))
            return;
        if(plugin.getMazeManager().getGrid() == null)
            return;
        MazeGrid grid = plugin.getMazeManager().getGrid();
        MazeRegion region = grid.getRegionAt(e.getPlayer().getLocation());
        if(region == null)
            return;

        Vector2Int sourceCell = grid.getRegionCellAt(region, e.getClickedBlock().getLocation());
        Vector2Int destinationCell = region.getTrapChestTeleportationDestinationCell(sourceCell);
        Location tpLocation = grid.getRegionCellWorldCenter(region, destinationCell);
        tpLocation.setY(tpLocation.getY() + 1);

        if(tpLocation != null) {
            e.getPlayer().teleport(tpLocation);
            e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
    }

}
