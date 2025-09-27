package me.brody.mazesurvival.maze.builder.structure;

import org.bukkit.Location;
import org.bukkit.Material;

public class BlindnessCellTrap extends CellTrap {

    public BlindnessCellTrap(Location cellCenter) {
        super(cellCenter, Material.OBSIDIAN, Material.GRAY_GLAZED_TERRACOTTA);
    }

}
