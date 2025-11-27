package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AmethystSetBonusManager {
    public static AmethystSetBonusManager instance;

    private Main plugin;

    private AmethystSetBonusManager() {}

    public void run(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updateAmethystSetBonus, 0L, 1L);
    }

    private void updateAmethystSetBonus() {
        if(!plugin.getGameManager().isGameRunning())
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            if(!CustomItem.AMETHYST_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.AMETHYST_CHESTPLATE.compareItem(player.getInventory().getChestplate())
                || !CustomItem.AMETHYST_LEGGINGS.compareItem(player.getInventory().getLeggings()) || !CustomItem.AMETHYST_BOOTS.compareItem(player.getInventory().getBoots()))
                continue;
            MazeRegion region = grid.getRegionAt(player.getLocation());
            if(region == null)
                continue;
            if(grid.getRegionCellAt(region, player.getLocation()) == null)
                continue;

            if(player.getPotionEffect(PotionEffectType.SPEED) == null || player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() < 2)
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1, true));
        }
    }

    public static AmethystSetBonusManager getInstance() {
        if(instance == null)
            instance = new AmethystSetBonusManager();

        return instance;
    }
}
