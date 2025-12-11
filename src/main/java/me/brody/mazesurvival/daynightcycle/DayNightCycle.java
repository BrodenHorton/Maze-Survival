package me.brody.mazesurvival.daynightcycle;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.MazeManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.UUID;

public class DayNightCycle implements Serializable {
    private static final int GAME_START_TIME_IN_TICKS = 22000;
    private static final int START_OF_DAY_IN_TICKS = 0;
    private static final int START_OF_NIGHT_IN_TICKS = 13500;
    private static final int TOTAL_TICKS_IN_CYCLE = 24000;

    public transient Event<EventArgs> onStartOfDay;
    public transient Event<EventArgs> onStartOfNight;

    private transient Main plugin;
    private boolean isDay;
    private transient World world;

    public DayNightCycle(Main plugin, MazeManager mazeManager) {
        this.plugin = plugin;
        isDay = false;
        onStartOfDay = new Event<>();
        onStartOfNight = new Event<>();
        registerEvents(mazeManager);
    }

    public void registerEvents(MazeManager mazeManager) {
        mazeManager.onMazeConstructionFinished.addListener((o, e) -> {
            initDayNightCycle(mazeManager.getGrid().getWorld());
        });
    }

    private void initDayNightCycle(World world) {
        this.world = world;
        isDay = false;
        world.setTime(GAME_START_TIME_IN_TICKS);
        startDayNightCycle();
    }

    public void startDayNightCycle() {
        plugin.getLogger().info("Day Night Cycle has started!");
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        final int dayTimeStep = 2;
        final int nightTimeStep = 3;
        final long tickTimeScale = 3;
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
        onStartOfDay = new Event<>();
        onStartOfNight = new Event<>();
    }

    @Override
    public String toString() {
        return "DayNightCycle{" +
                "isDay=" + isDay +
                ", world=" + world +
                '}';
    }

    public boolean isDay() {
        return isDay;
    }
}
