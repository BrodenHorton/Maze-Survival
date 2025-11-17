package me.brody.mazesurvival.player;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerProfile implements Serializable {
    private UUID uuid;
    private transient Map<MazeEnchantment, Integer> upgradeLevelByEnchantment;

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
        upgradeLevelByEnchantment = new HashMap<>();
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        Set<Map.Entry<MazeEnchantment, Integer>> entrySet = upgradeLevelByEnchantment.entrySet();
        oos.writeInt(entrySet.size());
        for(Map.Entry<MazeEnchantment, Integer> entry : entrySet) {
            oos.writeUTF(entry.getKey().getEnchantmentName());
            oos.writeInt(entry.getValue());
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        upgradeLevelByEnchantment = new HashMap<>();
        int enchantmentUpgradeCount = ois.readInt();
        for(int i = 0; i < enchantmentUpgradeCount; i++) {
            MazeEnchantment enchantment = Registry.ENCHANTMENT.get(ois.readUTF());
            int upgradeLevel = ois.readInt();
            upgradeLevelByEnchantment.put(enchantment, upgradeLevel);
        }
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
