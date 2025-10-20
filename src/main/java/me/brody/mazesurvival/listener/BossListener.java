package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

public class BossListener implements Listener {
    private final Main plugin;

    public BossListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void defeatBoss(EntityDeathEvent e) {
        if(!e.getEntity().getPersistentDataContainer().has(NamespacedKeys.BOSS, PersistentDataType.STRING))
            return;

        Bukkit.broadcastMessage(ChatColor.GREEN + "Boss mob defeated!");
        MazeGrid grid = plugin.getMazeManager().getGrid();
        String bossId = e.getEntity().getPersistentDataContainer().get(NamespacedKeys.BOSS, PersistentDataType.STRING);
        for(Entity entity : grid.getWorld().getEntities()) {
            if(entity.getPersistentDataContainer().has(NamespacedKeys.BOSS, PersistentDataType.STRING)
                    && entity.getPersistentDataContainer().get(NamespacedKeys.BOSS, PersistentDataType.STRING).equals(bossId)) {
                return;
            }
        }

        Bukkit.broadcastMessage(ChatColor.GREEN + bossId + " Defeated!");
    }
}
