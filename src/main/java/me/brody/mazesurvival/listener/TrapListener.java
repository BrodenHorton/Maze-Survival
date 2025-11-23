package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.LocationUtils;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class TrapListener implements Listener {
    private static final int BLINDNESS_DURATION = 200;
    private static final int SLOWNESS_DURATION = 200;
    private static final List<PotionEffect> enchantmentLevelEffects;

    private final Main plugin;

    static {
        enchantmentLevelEffects = new ArrayList<>();
        enchantmentLevelEffects.add(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 0, true));
        enchantmentLevelEffects.add(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 1, true));
        enchantmentLevelEffects.add(new PotionEffect(PotionEffectType.SPEED, 20 * 10, 2, true));
    }

    public TrapListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blindnessTrap(PlayerMoveEvent e) {
        if(plugin.getProfileManager().getProfileOf(e.getPlayer()) == null)
            return;
        Location belowPlayer = LocationUtils.copy(e.getPlayer().getLocation());
        belowPlayer.setY(Math.ceil(belowPlayer.getY()) - 2);
        if(belowPlayer.getBlock().getType() != Material.GRAY_GLAZED_TERRACOTTA)
            return;

        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, BLINDNESS_DURATION, 0));
    }

    @EventHandler
    public void slownessTrap(PlayerMoveEvent e) {
        if(plugin.getProfileManager().getProfileOf(e.getPlayer()) == null)
            return;
        Location belowPlayer = LocationUtils.copy(e.getPlayer().getLocation());
        belowPlayer.setY(Math.ceil(belowPlayer.getY()) - 2);
        if(belowPlayer.getBlock().getType() != Material.BROWN_GLAZED_TERRACOTTA)
            return;
        EnchantmentEntry soulSpeed = null;
        ItemStack boots = e.getPlayer().getInventory().getBoots();
        if(boots != null && boots.getItemMeta().getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS)) {
            EnchantmentList enchantmentList = boots.getItemMeta().getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
            if(enchantmentList != null) {
                for(EnchantmentEntry entry : enchantmentList.getEnchantmentEntries()) {
                    if(entry.getEnchantmentName().equals(MazeEnchantment.SOUL_SPEED.getEnchantmentName())) {
                        soulSpeed = entry;
                        break;
                    }
                }
            }
        }

        if(soulSpeed != null)
            e.getPlayer().addPotionEffect(enchantmentLevelEffects.get(soulSpeed.getLevel() - 1));
        else
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, SLOWNESS_DURATION, 2));
    }

    @EventHandler
    public void teleportTrap(PlayerMoveEvent e) {
        if(plugin.getProfileManager().getProfileOf(e.getPlayer()) == null)
            return;
        if(plugin.getMazeManager().getGrid() == null)
            return;
        MazeGrid grid = plugin.getMazeManager().getGrid();
        MazeRegion region = grid.getRegionAt(e.getPlayer().getLocation());
        if(region == null)
            return;
        Location belowPlayer = LocationUtils.copy(e.getPlayer().getLocation());
        belowPlayer.setY(Math.ceil(belowPlayer.getY()) - 2);
        if(belowPlayer.getBlock().getType() != Material.MAGENTA_GLAZED_TERRACOTTA)
            return;

        Vector2Int sourceCell = grid.getRegionCellAt(region, e.getPlayer().getLocation());
        Vector2Int destinationCell = region.getTileTeleportationDestinationCell(sourceCell);
        Location tpLocation = grid.getRegionCellWorldCenter(region, destinationCell);
        tpLocation = LocationUtils.centerOnBlock(tpLocation);
        tpLocation.setY(tpLocation.getY() + 1);

        if(tpLocation != null) {
            e.getPlayer().teleport(tpLocation);
            e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
    }

}
