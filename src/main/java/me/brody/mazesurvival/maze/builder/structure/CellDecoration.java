package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.maze.MazeCell;
import me.brody.mazesurvival.maze.region.CellOrientation;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.SchematicPaster;
import org.bukkit.Location;

import java.util.Random;

public class CellDecoration implements MazeStructureGenerator {
    private MazeSchematic schematic;
    private Location origin;
    private MazeCell cell;

    public CellDecoration(MazeSchematic schematic, MazeCell cell, Location origin) {
        this.schematic = schematic;
        this.origin = origin;
        this.cell = cell;
    }

    @Override
    public void generateStructure() {
        double rotation = getDecorationRotation(cell);
        SchematicPaster.paste(origin, rotation, true, schematic.getSchematicInputStream());
    }

    private double getDecorationRotation(MazeCell cell) {
        Random rand = new Random();
        CellOrientation orientation = cell.getCellOrientation();
        double rotation = 0;
        if(orientation == CellOrientation.DEADEND) {
            for(int i = 0; i < cell.walls.length; i++) {
                if(!cell.walls[i]) {
                    rotation = i * 90;
                    break;
                }
            }
        }
        else if(orientation == CellOrientation.CORRIDOR) {
            rotation = cell.walls[0] ? 90 : 0;
            rotation = rand.nextInt(0, 2) == 1 ? rotation + 180 : rotation;
        }
        else if(orientation == CellOrientation.BEND) {
            if(cell.walls[0] && cell.walls[1])
                rotation = 90;
            else if(cell.walls[1] && cell.walls[2])
                rotation = 180;
            else if(cell.walls[2] && cell.walls[3])
                rotation = 270;
        }
        else if(orientation == CellOrientation.INTERSECTION) {
            rotation = rand.nextInt(0, 4) * 90;
        }
        else if(orientation == CellOrientation.T_INTERSECTION) {
            for(int i = 0; i < cell.walls.length; i++) {
                if(cell.walls[i]) {
                    rotation = i * 90;
                    break;
                }
            }
        }

        return rotation;
    }
}
