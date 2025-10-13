package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;

public class GladeStructure implements MazeStructureGenerator {
    private final Main plugin;
    private MazeSchematic schematic;
    private Location origin;
    private double rotation;

    public GladeStructure(Main plugin, MazeSchematic schematic, Location origin, double rotation) {
        this.plugin = plugin;
        this.schematic = schematic;
        this.origin = origin;
        this.rotation = rotation;
    }

    @Override
    public void generateStructure() {
        SchematicPaster.paste(origin, rotation, schematic.getSchematicInputStream());
        generateProtectionBounds();
    }

    private void generateProtectionBounds() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        int gladeBuildableWidth = grid.getRegionCellSize() * grid.getGladeSize() - grid.getWallWidth() - 1;
        int gladeBuildableHeight = 100;
        Vector3Int min = new Vector3Int((int)origin.getX(), (int)origin.getY() - (gladeBuildableHeight / 2), (int)origin.getZ());
        Vector3Int max = new Vector3Int((int)origin.getX() + gladeBuildableWidth, (int)origin.getY() + (gladeBuildableHeight / 2), (int)origin.getZ() + gladeBuildableWidth);
        BoundsInt bounds = new BoundsInt(min, max);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, bounds, ProtectionType.BUILDABLE));
    }
}
