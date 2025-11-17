package me.brody.mazesurvival.daynightcycle;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeManager;
import me.brody.mazesurvival.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.UUID;

public class DayNightCycle implements Serializable {
    private static final int GAME_START_TIME_IN_TICKS = 22000;
    private static final int START_OF_DAY_IN_TICKS = 0;
    private static final int START_OF_NIGHT_IN_TICKS = 13500;
    private static final int TOTAL_TICKS_IN_CYCLE = 24000;

    public Event<EventArgs> onStartOfDay;
    public Event<EventArgs> onStartOfNight;

    private transient Main plugin;
    private boolean isDay;
    private transient World world;

    public DayNightCycle(Main plugin, MazeManager mazeManager) {
        this.plugin = plugin;
        isDay = false;
        mazeManager.onMazeConstructionFinished.addListener(this::startDayNightCycle);

        onStartOfDay = new Event<>();
        onStartOfNight = new Event<>();
    }

    public void startDayNightCycle(Object sender, EventArgs e) {
        plugin.getLogger().info("Day Night Cycle has started!");
        world = plugin.getMazeManager().getGrid().getGridOrigin().getWorld();
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        isDay = false;
        world.setTime(GAME_START_TIME_IN_TICKS);

        final int dayTimeStep = 2;
        final int nightTimeStep = 4;
        final long tickTimeScale = 1;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if(!isDay && world.getTime() >= START_OF_DAY_IN_TICKS && world.getTime() < START_OF_NIGHT_IN_TICKS) {
                plugin.getLogger().info("Start of day invoked");
                isDay = true;
                onStartOfDay.invoke(this, EventArgs.EMPTY);
            }
            else if(isDay && (world.getTime() < START_OF_DAY_IN_TICKS || world.getTime() >= START_OF_NIGHT_IN_TICKS)) {
                plugin.getLogger().info("Start of night invoked");
                isDay = false;
                onStartOfNight.invoke(this, EventArgs.EMPTY);
            }

            int timeStep = isDay ? dayTimeStep : nightTimeStep;
            world.setTime((world.getTime() + timeStep) % TOTAL_TICKS_IN_CYCLE);
        }, 0L, tickTimeScale);
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(world.getUID());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        plugin = JavaPlugin.getPlugin(Main.class);
        UUID worldUuid = (UUID) ois.readObject();
        world = Bukkit.getWorld(worldUuid);
    }

    public boolean isDay() {
        return isDay;
    }
}
