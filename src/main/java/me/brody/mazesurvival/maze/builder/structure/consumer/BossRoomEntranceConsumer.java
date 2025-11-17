package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.SerializableConsumer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.UUID;

public class BossRoomEntranceConsumer implements SerializableConsumer<Player> {
    private transient final Main plugin;
    private MazeRegion region;
    private transient Location teleportLocation;
    private BoundsInt bossRoomBounds;

    public BossRoomEntranceConsumer(Main plugin, MazeRegion region, Location teleportLocation, BoundsInt bossRoomBounds) {
        this.plugin = plugin;
        this.region = region;
        this.teleportLocation = teleportLocation;
        this.bossRoomBounds = bossRoomBounds;
    }

    @Override
    public void accept(Player p) {
        if(region.getBossFight() != null && !plugin.getGameState().getClearedRegions().contains(region.getUuid())) {
            boolean isPlayerInBounds = false;
            for(Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                if(bossRoomBounds.containsLocation(onlinePlayer.getLocation())) {
                    isPlayerInBounds = true;
                    break;
                }
            }

            if(!isPlayerInBounds) {
                region.getBossFight().start(region);
                ChatUtils.msg(p, "&aYou have triggered the boss fight!");
            }
        }
        p.teleport(teleportLocation);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(teleportLocation.getWorld().getUID());
        oos.writeDouble(teleportLocation.getX());
        oos.writeDouble(teleportLocation.getY());
        oos.writeDouble(teleportLocation.getZ());
        oos.writeFloat(teleportLocation.getYaw());
        oos.writeFloat(teleportLocation.getPitch());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        UUID worldUuid = (UUID) ois.readObject();
        double x = ois.readDouble();
        double y = ois.readDouble();
        double z = ois.readDouble();
        float yaw = ois.readFloat();
        float pitch = ois.readFloat();
        teleportLocation = new Location(Bukkit.getWorld(worldUuid), x, y, z, yaw, pitch);
    }
}
