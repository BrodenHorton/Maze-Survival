package me.brody.mazesurvival.effects;

import me.brody.mazesurvival.Main;
import org.bukkit.entity.Player;

public class WindBlowingSoundEffect {
    private final Main plugin;

    public WindBlowingSoundEffect(Main plugin) {
        this.plugin = plugin;
    }

    public void execute(Player player) {
        new WindBlowingSoundRunnable(plugin, player).start();
    }

}
