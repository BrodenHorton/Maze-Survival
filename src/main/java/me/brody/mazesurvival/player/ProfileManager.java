package me.brody.mazesurvival.player;

import me.brody.mazesurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileManager implements Listener {
    private final Main plugin;

    private List<PlayerProfile> playerProfiles;

    public ProfileManager(Main plugin) {
        this.plugin = plugin;
        playerProfiles = new ArrayList<>();
    }

    public List<PlayerProfile> getPlayerProfiles() {
        return playerProfiles;
    }

    public PlayerProfile getProfileOf(Player player) {
        return getProfileOf(player.getUniqueId());
    }

    public PlayerProfile getProfileOf(UUID uuid) {
        for(int i = 0; i < playerProfiles.size(); i++) {
            if(playerProfiles.get(i).getUuid().equals(uuid))
                return playerProfiles.get(i);
        }

        return null;
    }

    @EventHandler
    public void createProfileOnJoin(PlayerJoinEvent e) {
        PlayerProfile profile = getProfileOf(e.getPlayer().getUniqueId());
        if(profile != null)
            return;

        playerProfiles.add(new PlayerProfile(e.getPlayer().getUniqueId()));
        plugin.getLogger().info("New Player Profile created for " + e.getPlayer().getName());
    }
}
