package me.brody.mazesurvival.effects;

import me.brody.mazesurvival.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WindBlowingSoundRunnable extends BukkitRunnable {
    private static final int MAX_RUN_COUNT = 60;
    private static final long DELAY = 40L;

    private final Main plugin;
    private Player player;
    int count;

    public WindBlowingSoundRunnable(Main plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        count = 0;
    }

    @Override
    public void run() {
        player.playSound(player.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.2f, 2f);
        player.playSound(player.getLocation(), Sound.ENTITY_HORSE_BREATHE, 0.3f, 0.5f);

        if(++count >= MAX_RUN_COUNT)
            this.cancel();
    }

    public void start() {
        this.runTaskTimer(plugin, DELAY, 1L);
    }
}
