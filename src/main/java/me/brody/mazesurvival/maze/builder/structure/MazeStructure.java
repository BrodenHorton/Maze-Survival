package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import org.bukkit.Location;

public class MazeStructure implements MazeStructureGenerator {
    private MazeSchematic schematic;
    private Location origin;
    private double rotation;

    public MazeStructure(MazeSchematic schematic, Location origin, double rotation) {
        this.schematic = schematic;
        this.origin = origin;
        this.rotation = rotation;
    }

    @Override
    public void generateStructure() {
        SchematicPaster.paste(origin, rotation, schematic.getSchematicInputStream());
    }
}
