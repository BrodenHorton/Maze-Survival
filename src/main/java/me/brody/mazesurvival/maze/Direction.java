package me.brody.mazesurvival.maze;

public enum Direction {
    NORTH(0, 0, -1),
    EAST(1, 1, 0),
    SOUTH(2, 0, 1),
    WEST(3, -1, 0);

    public final int id;
    public final int xDirOffset;
    public final int yDirOffset;
    public final int rotation;

    Direction(int id, int xDirOffset, int yDirOffset) {
        this.id = id;
        this.xDirOffset = xDirOffset;
        this.yDirOffset = yDirOffset;
        rotation = id * 90;
    }
}
