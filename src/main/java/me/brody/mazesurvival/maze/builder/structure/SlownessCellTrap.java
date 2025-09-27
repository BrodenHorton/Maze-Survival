package me.brody.mazesurvival.maze.builder.structure;

import org.bukkit.Location;
import org.bukkit.Material;

public class SlownessCellTrap extends CellTrap {

    public SlownessCellTrap(Location cellOrigin) {
        super(cellOrigin, Material.SOUL_SAND, Material.BROWN_GLAZED_TERRACOTTA);
    }

}
