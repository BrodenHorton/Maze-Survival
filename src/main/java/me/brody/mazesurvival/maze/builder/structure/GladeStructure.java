package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

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
        int gladeBuildableWidth = grid.getRegionCellSize() * grid.getGladeSize() - grid.getWallWidth();
        int gladeDepth = 10;
        Vector3Int min = new Vector3Int((int)origin.getX(), (int)origin.getY() - gladeDepth, (int)origin.getZ());
        Vector3Int max = new Vector3Int((int)origin.getX() + gladeBuildableWidth, (int)origin.getY() + grid.getWallHeight(), (int)origin.getZ() + gladeBuildableWidth);
        BoundsInt bounds = new BoundsInt(min, max);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, bounds, ProtectionType.BUILDABLE));

        Location gladeCenter = grid.getGladeWorldCenter();
        gladeCenter.setY(gladeCenter.getY() + 1);
        int gateBoundsWidth = 17;
        int gateBoundsLength = 3;
        int gladeCenterToGateBoundsCenter = (grid.getRegionCellSize() * grid.getGladeSize() / 2) - grid.getWallWidth();
        Vector3Int minMeridionalGate = new Vector3Int(-gateBoundsWidth / 2, 0, -gateBoundsLength / 2);
        Vector3Int maxMeridionalGate = new Vector3Int(gateBoundsWidth / 2 + 1, grid.getWallHeight(), gateBoundsLength / 2 + 1);
        BoundsInt meridionalGateBounds = new BoundsInt(minMeridionalGate, maxMeridionalGate);
        meridionalGateBounds.shift(gladeCenter);
        Vector3Int minZonalGate = new Vector3Int(-gateBoundsLength / 2, 0, -gateBoundsWidth / 2);
        Vector3Int maxZonalGate = new Vector3Int(gateBoundsLength / 2 + 1, grid.getWallHeight(), gateBoundsWidth / 2 + 1);
        BoundsInt zonalGateBounds = new BoundsInt(minZonalGate, maxZonalGate);
        zonalGateBounds.shift(gladeCenter);

        BoundsInt northGateBounds = meridionalGateBounds.clone();
        northGateBounds.shiftZ(-gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, northGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(northGateBounds, playerSpawnPointConsumer(grid.getGladeRespawnLocation()), null));

        BoundsInt eastGateBounds = zonalGateBounds.clone();
        eastGateBounds.shiftX(gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, eastGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(eastGateBounds, playerSpawnPointConsumer(grid.getGladeRespawnLocation()), null));

        BoundsInt southGateBounds = meridionalGateBounds.clone();
        southGateBounds.shiftZ(gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, southGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(southGateBounds, playerSpawnPointConsumer(grid.getGladeRespawnLocation()), null));

        BoundsInt westGateBounds = zonalGateBounds.clone();
        westGateBounds.shiftX(-gladeCenterToGateBoundsCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, westGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(westGateBounds, playerSpawnPointConsumer(grid.getGladeRespawnLocation()), null));
    }

    private Consumer<Player> playerSpawnPointConsumer(Location location) {
        return (p) -> plugin.getRespawnManager().setPlayerRespawnLocation(p, location);
    }
}
