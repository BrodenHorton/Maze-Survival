package me.brody.mazesurvival.daynightcycle;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeManager;
import org.bukkit.GameRule;
import org.bukkit.World;

public class DayNightCycle {
    private static final int START_OF_NIGHT_IN_TICKS = 13500;
    private static final int TOTAL_TICKS_IN_CYCLE = 24000;

    public Event<EventArgs> onStartOfDay;
    public Event<EventArgs> onStartOfNight;

    private final Main plugin;
    private World world;

    public DayNightCycle(Main plugin, MazeManager mazeManager) {
        this.plugin = plugin;
        mazeManager.onMazeConstructionFinished.addListener(this::startDayNightCycle);

        onStartOfDay = new Event<>();
        onStartOfNight = new Event<>();
    }

    public void startDayNightCycle(Object sender, EventArgs e) {
        plugin.getLogger().info("Day Night Cycle has started!");
        world = plugin.getMazeManager().getGrid().getGridOrigin().getWorld();
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(0L);
        onStartOfDay.invoke(this, EventArgs.EMPTY);

        // TODO: Change to check every tick to make sure day night cycle is correct
        final int dayTimeMultiplier = 2;
        final int nightTimeMultiplier = 4;
        final long tickTimeScale = 1;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if(world.getTime() + nightTimeMultiplier >= TOTAL_TICKS_IN_CYCLE) {
                plugin.getLogger().info("Start of day invoked");
                onStartOfDay.invoke(this, EventArgs.EMPTY);
            }
            else if(world.getTime() < START_OF_NIGHT_IN_TICKS && world.getTime() + dayTimeMultiplier >= START_OF_NIGHT_IN_TICKS) {
                plugin.getLogger().info("Start of night invoked");
                onStartOfNight.invoke(this, EventArgs.EMPTY);
            }

            if(world.getTime() < START_OF_NIGHT_IN_TICKS)
                world.setTime(world.getTime() + dayTimeMultiplier);
            else
                world.setTime((world.getTime() + nightTimeMultiplier) % TOTAL_TICKS_IN_CYCLE);
        }, 0L, tickTimeScale);
    }

    public boolean isDay() {
        return world.getTime() < START_OF_NIGHT_IN_TICKS;
    }
}
