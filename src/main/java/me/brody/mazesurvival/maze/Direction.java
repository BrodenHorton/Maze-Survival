package me.brody.mazesurvival.maze;

public enum Direction {
    NORTH(0, 0, -1, 0),
    EAST(1, 1, 0, 90),
    SOUTH(2, 0, 1, 180),
    WEST(3, -1, 0, 270);

    public final int id;
    public final int xDirOffset;
    public final int yDirOffset;
    public final int rotation;

    Direction(int id, int xDirOffset, int yDirOffset, int rotation) {
        this.id = id;
        this.xDirOffset = xDirOffset;
        this.yDirOffset = yDirOffset;
        this.rotation = rotation;
    }
}
