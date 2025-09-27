package me.brody.mazesurvival.daynightcycle;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeManager;
import org.bukkit.GameRule;
import org.bukkit.World;

public class DayNightCycle {
    private static final int DAY_TIME_MULTIPLIER = 2;
    private static final int NIGHT_TIME_MULTIPLIER = 4;
    private static final long TICK_TIME_SCALE = 1;

    public Event<EventArgs> onStartOfDay;
    public Event<EventArgs> onStartOfNight;

    private final Main plugin;

    public DayNightCycle(Main plugin, MazeManager mazeManager) {
        this.plugin = plugin;
        mazeManager.onMazeConstructionFinished.addListener(this::startDayNightCycle);

        onStartOfDay = new Event<>();
        onStartOfNight = new Event<>();
    }

    public void startDayNightCycle(Object sender, EventArgs e) {
        plugin.getLogger().info("Day Night Cycle has started!");
        World world = plugin.getMazeManager().getGrid().getGridOrigin().getWorld();
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(0L);
        onStartOfDay.invoke(this, EventArgs.EMPTY);

        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if(world.getTime() + NIGHT_TIME_MULTIPLIER >= 24000) {
                plugin.getLogger().info("Start of day invoked");
                onStartOfDay.invoke(this, EventArgs.EMPTY);
            }
            else if(world.getTime() < 13500 && world.getTime() + DAY_TIME_MULTIPLIER >= 13500) {
                plugin.getLogger().info("Start of night invoked");
                onStartOfNight.invoke(this, EventArgs.EMPTY);
            }

            if(world.getTime() < 13500)
                world.setTime(world.getTime() + DAY_TIME_MULTIPLIER);
            else
                world.setTime((world.getTime() + NIGHT_TIME_MULTIPLIER) % 24000);
        }, 0L, TICK_TIME_SCALE);
    }
}
