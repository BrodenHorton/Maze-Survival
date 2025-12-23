package me.brody.mazesurvival.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class RespawnManager implements Listener, Serializable {
    private transient Map<UUID, Location> respawnLocationByPlayer;

    public RespawnManager() {
        respawnLocationByPlayer = new HashMap<>();
    }

    public void setPlayerRespawnLocation(Player p, Location location) {
        respawnLocationByPlayer.put(p.getUniqueId(), location);
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        Set<Map.Entry<UUID, Location>> entrySet = respawnLocationByPlayer.entrySet();
        oos.writeInt(entrySet.size());
        for(Map.Entry<UUID, Location> entry : entrySet) {
            oos.writeObject(entry.getKey());
            oos.writeObject(entry.getValue().getWorld().getUID());
            oos.writeDouble(entry.getValue().getX());
            oos.writeDouble(entry.getValue().getY());
            oos.writeDouble(entry.getValue().getZ());
            oos.writeFloat(entry.getValue().getYaw());
            oos.writeFloat(entry.getValue().getPitch());
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        respawnLocationByPlayer = new HashMap<>();
        int entryCount = ois.readInt();
        for(int i = 0; i < entryCount; i++) {
            UUID playerUuid = (UUID) ois.readObject();
            UUID worldUuid = (UUID) ois.readObject();
            double x = ois.readDouble();
            double y = ois.readDouble();
            double z = ois.readDouble();
            float yaw = ois.readFloat();
            float pitch = ois.readFloat();
            respawnLocationByPlayer.put(playerUuid, new Location(Bukkit.getWorld(worldUuid), x, y, z, yaw, pitch));
        }
    }

    @Override
    public String toString() {
        return "RespawnManager{" +
                "respawnLocationByPlayer=" + respawnLocationByPlayer +
                '}';
    }

    @EventHandler
    public void setPlayerRespawnLocationEvent(PlayerRespawnEvent e) {
        if(!respawnLocationByPlayer.containsKey(e.getPlayer().getUniqueId()))
            return;

        e.setRespawnLocation(respawnLocationByPlayer.get(e.getPlayer().getUniqueId()));
        /*e.getPlayer().setFoodLevel(15);
        e.getPlayer().setSaturation(0);*/
    }
}
