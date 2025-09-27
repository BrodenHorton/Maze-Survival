package me.brody.mazesurvival.player;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerProfile {
    private UUID uuid;
    private Map<MazeEnchantment, Integer> upgradeLevelByEnchantment;

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
        upgradeLevelByEnchantment = new HashMap<>();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<MazeEnchantment, Integer> getUpgradeLevelByEnchantment() {
        return upgradeLevelByEnchantment;
    }

}
