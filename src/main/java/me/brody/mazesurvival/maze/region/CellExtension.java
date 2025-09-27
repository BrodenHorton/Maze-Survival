package me.brody.mazesurvival.maze.region;

import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.utils.MazeSchematic;
import me.brody.mazesurvival.utils.Vector2Int;

public class CellExtension {
    private Vector2Int cellPosition;
    private Direction direction;
    private MazeSchematic schematic;

    public CellExtension(Vector2Int cellPosition, Direction direction, MazeSchematic schematic) {
        this.cellPosition = cellPosition;
        this.direction = direction;
        this.schematic = schematic;
    }

    public Vector2Int getCellPosition() {
        return cellPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public MazeSchematic getSchematic() {
        return schematic;
    }
}
