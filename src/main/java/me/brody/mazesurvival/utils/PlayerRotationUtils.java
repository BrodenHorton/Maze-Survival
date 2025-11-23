package me.brody.mazesurvival.utils;

import me.brody.mazesurvival.maze.Direction;

public class PlayerRotationUtils {

    public static float getYaw(Direction dir) {
        return (dir.id * 90) - 180;
    }
}
