package me.brody.mazesurvival.maze;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import me.brody.mazesurvival.utils.Vector2Int;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GladeDoorListener {
    private final Main plugin;

    public GladeDoorListener(Main plugin, DayNightCycle dayNightCycle) {
        this.plugin = plugin;
        dayNightCycle.onStartOfDay.addListener(this::openGladeDoors);
        dayNightCycle.onStartOfNight.addListener(this::closeGladeDoors);
    }

    private void openGladeDoors(Object sender, EventArgs args) {
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 0, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 90, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 180, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 270, false, MazeSchematic.GLADE_DOOR_OPENED.getSchematicInputStream());

        for(Player p : plugin.getServer().getOnlinePlayers())
            p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 2f, 0.5f);
    }

    private void closeGladeDoors(Object sender, EventArgs args) {
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 0, true, MazeSchematic.GLADE_DOOR.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 90, true, MazeSchematic.GLADE_DOOR.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 180, true, MazeSchematic.GLADE_DOOR.getSchematicInputStream());
        SchematicPaster.paste(plugin.getMazeManager().getGrid().getGladeWorldCenter(), 270, true, MazeSchematic.GLADE_DOOR.getSchematicInputStream());

        MazeGrid grid = plugin.getMazeManager().getGrid();
        boolean isPlayerInMaze = false;
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            MazeRegion region = grid.getRegionAt(p.getLocation());
            if(region != null) {
                Vector2Int cellCoords = grid.getRegionCellAt(region, p.getLocation());
                if(cellCoords != null && region.getMazeCells()[cellCoords.y][cellCoords.x] != null) {
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

}
