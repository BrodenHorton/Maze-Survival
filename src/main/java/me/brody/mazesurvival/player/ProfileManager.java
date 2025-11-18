package me.brody.mazesurvival.player;

import me.brody.mazesurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileManager implements Serializable {
    private transient Main plugin;
    private List<PlayerProfile> playerProfiles;

    public ProfileManager(Main plugin) {
        this.plugin = plugin;
        playerProfiles = new ArrayList<>();
    }

    public void createPlayerProfile(Player player) {
        if(getProfileOf(player) != null)
            return;

        playerProfiles.add(new PlayerProfile(player.getUniqueId()));
        plugin.getLogger().info("New Player Profile created for " + player.getName());
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

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        plugin = JavaPlugin.getPlugin(Main.class);
    }

    @Override
    public String toString() {
        return "ProfileManager{" +
                "playerProfiles=" + playerProfiles +
                '}';
    }
}
