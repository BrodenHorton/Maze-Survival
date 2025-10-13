package me.brody.mazesurvival.maze.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.event.Event;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.builder.structure.*;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.LocationCopier;
import me.brody.mazesurvival.utils.Vector3Int;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class StructureGenerator {
    private static final long GENERATION_DELAY = 25L;

    public Event<EventArgs> onStructureGenerationFinished;

    private final Main plugin;

    private MazeGrid grid;
    private Queue<MazeStructureGenerator> structures;
    private boolean isRunning;

    public StructureGenerator(Main plugin, MazeGrid grid) {
        onStructureGenerationFinished = new Event<>();
        this.plugin = plugin;
        this.grid = grid;
        structures = new ArrayDeque<>();
        isRunning = false;
    }

    public void start() {
        if(grid == null || isRunning)
            return;

        isRunning = true;
        initStructureQueue();
    }

    private void initStructureQueue() {
        Location gladeOrigin = grid.getGladeWorldOrigin();
        gladeOrigin.setY(gladeOrigin.getY() + 1);
        structures.add(new GladeStructure(plugin, grid.getGladeSchema(), gladeOrigin, 0));
        for(MazeRegion region : grid.getRegions()) {
            if(region.getHaven() != null && grid.getRegionHavenWorldOrigin(region) != null) {
                Location havenLocation = grid.getRegionHavenWorldOrigin(region);
                structures.add(new HavenStructure(plugin, region.getHaven().getSchematic(), havenLocation, region.getHaven().getDirection().rotation, region));
            }
            if(region.getBossRoom() != null && grid.getRegionBossRoomWorldOrigin(region) != null) {
                Location bossRoomLocation = grid.getRegionBossRoomWorldOrigin(region);
                structures.add(new BossRoomStructure(plugin, region.getBossRoom().getSchematic(), bossRoomLocation, region.getBossRoom().getDirection().rotation, region));
            }
        }

        processStructureQueue();
    }

    private void processStructureQueue() {
        if(structures.isEmpty())
            return;

        MazeStructureGenerator structure = structures.poll();
        structure.generateStructure();

        if(!structures.isEmpty())
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this::processStructureQueue, GENERATION_DELAY);
        else {
            isRunning = false;
            onStructureGenerationFinished.invoke(this, EventArgs.EMPTY);
        }
    }
}