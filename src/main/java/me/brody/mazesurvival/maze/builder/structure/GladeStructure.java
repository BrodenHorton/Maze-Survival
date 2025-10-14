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
        SchematicPaster.paste(origin, rotation, true, schematic.getSchematicInputStream());
        generateProtectionBounds();
    }

    private void generateProtectionBounds() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        int gladeBuildableWidth = grid.getRegionCellSize() * grid.getGladeSize() - grid.getWallWidth() - 1;
        int gladeDepth = 10;
        Vector3Int min = new Vector3Int((int)origin.getX(), (int)origin.getY() - gladeDepth, (int)origin.getZ());
        Vector3Int max = new Vector3Int((int)origin.getX() + gladeBuildableWidth, (int)origin.getY() + grid.getWallHeight(), (int)origin.getZ() + gladeBuildableWidth);
        BoundsInt bounds = new BoundsInt(min, max);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, bounds, ProtectionType.BUILDABLE));

        Vector3Int minMeridionalGate = new Vector3Int(-8, 0, -1);
        Vector3Int maxMeridionalGate = new Vector3Int(8, grid.getWallHeight(), 1);
        BoundsInt meridionalGateBounds = new BoundsInt(minMeridionalGate, maxMeridionalGate);
        Vector3Int minZonalGate = new Vector3Int(-1, 0, -8);
        Vector3Int maxZonalGate = new Vector3Int(1, grid.getWallHeight(), 8);
        BoundsInt zonalGateBounds = new BoundsInt(minZonalGate, maxZonalGate);

        Location gladeCenter = grid.getGladeWorldCenter();
        gladeCenter.setY(gladeCenter.getY() + 1);
        plugin.getLogger().info("Glade Center: " + gladeCenter);
        int gladeCenterToGateBoundsCenter = (grid.getRegionCellSize() * grid.getGladeSize() / 2) - grid.getWallWidth();

        BoundsInt northGateBounds = meridionalGateBounds.clone();
        northGateBounds.shift(gladeCenter);
        northGateBounds.shiftZ(-gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, northGateBounds, ProtectionType.PROTECTED));
        plugin.getLogger().info("Protection Bounds placed at: " + northGateBounds.getMin() + " " + northGateBounds.getMax());

        BoundsInt eastGateBounds = zonalGateBounds.clone();
        eastGateBounds.shift(gladeCenter);
        eastGateBounds.shiftX(gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, eastGateBounds, ProtectionType.PROTECTED));
        plugin.getLogger().info("Protection Bounds placed at: " + eastGateBounds.getMin() + " " + eastGateBounds.getMax());

        BoundsInt southGateBounds = meridionalGateBounds.clone();
        southGateBounds.shift(gladeCenter);
        southGateBounds.shiftZ(gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, southGateBounds, ProtectionType.PROTECTED));

        BoundsInt westGateBounds = zonalGateBounds.clone();
        westGateBounds.shift(gladeCenter);
        westGateBounds.shiftX(-gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, westGateBounds, ProtectionType.PROTECTED));
    }
}
