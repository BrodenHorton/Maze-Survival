package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.utils.LocationCopier;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

public class CellTrapGenerator implements MazeStructureGenerator {
    private CellTrap cellTrap;
    private Location cellCenter;

    public CellTrapGenerator(CellTrap cellTrap, Location cellCenter) {
        this.cellTrap = cellTrap;
        this.cellCenter = cellCenter;
    }

    @Override
    public void generateStructure() {
        Random rand = new Random();
        int trapBlockNum = rand.nextInt(3, 6);
        for(int i = 0; i < trapBlockNum; i++) {
            Location surfaceTrapBlockLoc = LocationCopier.copy(cellCenter);
            surfaceTrapBlockLoc.setX(surfaceTrapBlockLoc.getX() + rand.nextInt(-3, 4));
            surfaceTrapBlockLoc.setZ(surfaceTrapBlockLoc.getZ() + rand.nextInt(-3, 4));
            surfaceTrapBlockLoc.getBlock().setType(cellTrap.getSurfaceBlock());

            Location trapTriggerBlockLoc = LocationCopier.copy(surfaceTrapBlockLoc);
            trapTriggerBlockLoc.setY(trapTriggerBlockLoc.getY() - 1);
            trapTriggerBlockLoc.getBlock().setType(cellTrap.getTrapTriggerBlock());
        }
    }
}
