package me.brody.mazesurvival.maze.builder.structure.consumer;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.SerializableConsumer;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
}
