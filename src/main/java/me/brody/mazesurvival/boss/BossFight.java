package me.brody.mazesurvival.boss;

import me.brody.mazesurvival.maze.region.MazeRegion;

import java.io.Serializable;

public interface BossFight extends Serializable {
    void start(MazeRegion region);
}
