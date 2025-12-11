package me.brody.mazesurvival.maze;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.*;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class GladeDoorListener implements Serializable {
    private transient Main plugin;

    public GladeDoorListener(Main plugin, DayNightCycle dayNightCycle) {
        this.plugin = plugin;
        registerEvents(dayNightCycle);
    }

    public void registerEvents(DayNightCycle dayNightCycle) {
        dayNightCycle.onStartOfDay.addListener(this::openGladeDoors);
        dayNightCycle.onStartOfNight.addListener(this::closeGladeDoors);
    }

    private void openGladeDoors(Object sender, EventArgs args) {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        Vector3Int doorOffset = new Vector3Int(0, 0, -(grid.getGladeSize() * grid.getRegionCellSize()) / 2);
        Location northDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), doorOffset);
        SchematicPaster.paste(northDoorLocation, 0, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());

        Vector3Int eastDoorOffset = doorOffset.clone();
        eastDoorOffset.rotateY(90);
        Location eastDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), eastDoorOffset);
        SchematicPaster.paste(eastDoorLocation, 270, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());

        Vector3Int southDoorOffset = doorOffset.clone();
        southDoorOffset.rotateY(180);
        Location southDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), southDoorOffset);
        SchematicPaster.paste(southDoorLocation, 180, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());

        Vector3Int westDoorOffset = doorOffset.clone();
        westDoorOffset.rotateY(270);
        Location westDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), westDoorOffset);
        SchematicPaster.paste(westDoorLocation, 90, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());

        for(Player p : plugin.getServer().getOnlinePlayers())
            p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 2f, 0.5f);
    }

    private void closeGladeDoors(Object sender, EventArgs args) {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        Vector3Int doorOffset = new Vector3Int(0, 0, -(grid.getGladeSize() * grid.getRegionCellSize()) / 2);
        Location northDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), doorOffset);
        SchematicPaster.paste(northDoorLocation, 0, false, MazeSchematic.GLADE_DOOR.getSchematicInputStream());

        Vector3Int eastDoorOffset = doorOffset.clone();
        eastDoorOffset.rotateY(90);
        Location eastDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), eastDoorOffset);
        SchematicPaster.paste(eastDoorLocation, 270, false, MazeSchematic.GLADE_DOOR.getSchematicInputStream());

        Vector3Int southDoorOffset = doorOffset.clone();
        southDoorOffset.rotateY(180);
        Location southDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), southDoorOffset);
        SchematicPaster.paste(southDoorLocation, 180, false, MazeSchematic.GLADE_DOOR.getSchematicInputStream());

        Vector3Int westDoorOffset = doorOffset.clone();
        westDoorOffset.rotateY(270);
        Location westDoorLocation = LocationUtils.shift(grid.getGladeWorldCenter(), westDoorOffset);
        SchematicPaster.paste(westDoorLocation, 90, false, MazeSchematic.GLADE_DOOR.getSchematicInputStream());

        boolean isPlayerInMaze = false;
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            MazeRegion region = grid.getRegionAt(p.getLocation());
            if(region != null) {
                if(grid.isInMaze(region, p.getLocation())) {
                    isPlayerInMaze = true;
                    break;
                }
            }
        }
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            if(isPlayerInMaze)
                p.playSound(p.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 2f, 0.5f);
            else
                p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 2f, 0.5f);
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        plugin = JavaPlugin.getPlugin(Main.class);
    }

}
