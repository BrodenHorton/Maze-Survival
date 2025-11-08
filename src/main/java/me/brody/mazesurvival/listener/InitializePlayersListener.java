package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeManager;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InitializePlayersListener implements Listener {
    private final Main plugin;

    public InitializePlayersListener(Main plugin, MazeManager mazeManager) {
        this.plugin = plugin;
        mazeManager.onMazeConstructionFinished.addListener(this::initializeOnlinePlayers);
    }

    private void initializePlayer(Player player) {
        plugin.getProfileManager().createPlayerProfile(player);
        player.teleport(plugin.getMazeManager().getGrid().getGladeRespawnLocation());
        plugin.getRespawnManager().setPlayerRespawnLocation(player, plugin.getMazeManager().getGrid().getGladeRespawnLocation());
        // TODO: Make the sound effect a scheduled event that runs for 2 seconds
        player.playSound(player.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1f, 0.4f);
        ChatUtils.msg(player, "&aYou have been dropped into the Glade!");
        ChatUtils.msg(player, "&aView crafting recipes with &e/ms recipes");
    }

    private void initializeOnlinePlayers(Object sender, EventArgs e) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            initializePlayer(player);
        }
    }

    @EventHandler
    public void initializePlayerOnJoin(PlayerJoinEvent e) {
        if(plugin.getProfileManager().getProfileOf(e.getPlayer()) != null)
            return;

        initializePlayer(e.getPlayer());
    }

}
