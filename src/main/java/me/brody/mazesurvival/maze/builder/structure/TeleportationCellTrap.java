package me.brody.mazesurvival.maze.builder.structure;

import org.bukkit.Location;
import org.bukkit.Material;

public class TeleportationCellTrap extends CellTrap {

    public TeleportationCellTrap(Location cellOrigin) {
        super(cellOrigin, Material.CRYING_OBSIDIAN, Material.MAGENTA_GLAZED_TERRACOTTA);
    }

}
