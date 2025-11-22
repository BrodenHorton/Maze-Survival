package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.Random;
import java.util.UUID;

public class CellTrapGenerator implements MazeStructureGenerator {
    private transient CellTrap cellTrap;
    private transient Location cellCenter;

    public CellTrapGenerator(CellTrap cellTrap, Location cellCenter) {
        this.cellTrap = cellTrap;
        this.cellCenter = cellCenter;
    }

    @Override
    public void generateStructure() {
        Random rand = new Random();
        int trapBlockNum = rand.nextInt(3, 6);
        for(int i = 0; i < trapBlockNum; i++) {
            Location surfaceTrapBlockLoc = LocationUtils.copy(cellCenter);
            surfaceTrapBlockLoc.setX(surfaceTrapBlockLoc.getX() + rand.nextInt(-3, 4));
            surfaceTrapBlockLoc.setZ(surfaceTrapBlockLoc.getZ() + rand.nextInt(-3, 4));
            surfaceTrapBlockLoc.getBlock().setType(cellTrap.getSurfaceBlock());

            Location trapTriggerBlockLoc = LocationUtils.copy(surfaceTrapBlockLoc);
            trapTriggerBlockLoc.setY(trapTriggerBlockLoc.getY() - 1);
            trapTriggerBlockLoc.getBlock().setType(cellTrap.getTrapTriggerBlock());
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(cellTrap.getId());
        oos.writeObject(cellCenter.getWorld().getUID());
        oos.writeDouble(cellCenter.getX());
        oos.writeDouble(cellCenter.getY());
        oos.writeDouble(cellCenter.getZ());
        oos.writeFloat(cellCenter.getYaw());
        oos.writeFloat(cellCenter.getPitch());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        cellTrap = Registry.CELL_TRAP.get(ois.readUTF());
        UUID worldUuid = (UUID) ois.readObject();
        double x = ois.readDouble();
        double y = ois.readDouble();
        double z = ois.readDouble();
        float yaw = ois.readFloat();
        float pitch = ois.readFloat();
        cellCenter = new Location(Bukkit.getWorld(worldUuid), x, y, z, yaw, pitch);
    }
}
