package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.builder.structure.consumer.GladeEntranceConsumer;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class GladeStructure implements MazeStructureGenerator {
    private transient final Main plugin;
    private MazeSchematic schematic;
    private transient Location origin;
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
        final int gladeBuildableWidth = grid.getRegionCellSize() * grid.getGladeSize() - grid.getWallWidth();
        final int gladeDepth = 10;
        final int gateBoundsWidth = 17;
        final int gateBoundsLength = 3;
        final int gladeCenterToGateBoundsCenter = (grid.getRegionCellSize() * grid.getGladeSize() / 2) - grid.getWallWidth();
        Location gladeCenter = grid.getGladeWorldCenter();

        BoundsInt buildableBounds = new BoundsInt(new Vector3Int(-gladeBuildableWidth / 2, -gladeDepth, -gladeBuildableWidth / 2), new Vector3Int(gladeBuildableWidth / 2, grid.getWallHeight(), gladeBuildableWidth / 2));
        buildableBounds.shift(gladeCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, buildableBounds, ProtectionType.BUILDABLE));

        BoundsInt gateBounds = new BoundsInt(new Vector3Int(-gateBoundsWidth / 2, 1, -gateBoundsLength / 2), new Vector3Int(gateBoundsWidth / 2, grid.getWallHeight(), gateBoundsLength / 2));
        gateBounds.shiftZ(-gladeCenterToGateBoundsCenter);

        BoundsInt northGateBounds = gateBounds.clone();
        northGateBounds.shift(gladeCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, northGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(northGateBounds, new GladeEntranceConsumer(plugin, grid.getGladeRespawnLocation()), null));

        BoundsInt eastGateBounds = gateBounds.clone();
        eastGateBounds.rotateY(270);
        eastGateBounds.shift(gladeCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, eastGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(eastGateBounds, new GladeEntranceConsumer(plugin, grid.getGladeRespawnLocation()), null));

        BoundsInt southGateBounds = gateBounds.clone();
        southGateBounds.rotateY(180);
        southGateBounds.shift(gladeCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, southGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(southGateBounds, new GladeEntranceConsumer(plugin, grid.getGladeRespawnLocation()), null));

        BoundsInt westGateBounds = gateBounds.clone();
        westGateBounds.rotateY(90);
        westGateBounds.shift(gladeCenter);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(1, westGateBounds, ProtectionType.PROTECTED));
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(westGateBounds, new GladeEntranceConsumer(plugin, grid.getGladeRespawnLocation()), null));
    }

    private Consumer<Player> playerSpawnPointConsumer(Location location) {
        return (p) -> plugin.getRespawnManager().setPlayerRespawnLocation(p, location);
    }
}
