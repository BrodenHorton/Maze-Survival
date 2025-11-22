package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.builder.structure.consumer.BossRoomEntranceConsumer;
import me.brody.mazesurvival.maze.builder.structure.consumer.BossRoomExitConsumer;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.UUID;
import java.util.function.Consumer;

public class BossRoomStructure implements MazeStructureGenerator {
    private transient Main plugin;
    private MazeSchematic schematic;
    private transient Location origin;
    private double rotation;
    private MazeRegion region;

    public BossRoomStructure(Main plugin, MazeSchematic schematic, Location origin, double rotation, MazeRegion region) {
        this.plugin = plugin;
        this.schematic = schematic;
        this.origin = origin;
        this.rotation = rotation;
        this.region = region;
    }

    @Override
    public void generateStructure() {
        SchematicPaster.paste(origin, rotation, true, schematic.getSchematicInputStream());
        BoundsInt bossRoomBounds = generateProtectionBounds();
        generateBossRoomTriggers(bossRoomBounds);
    }

    private BoundsInt generateProtectionBounds() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int bossRoomWidth = 25;
        final int bossRoomDepth = 4;
        final int bossRoomWallWidth = 1;
        final int bossRoomLength = grid.getMarginInBlocks() * 2 - grid.getWallWidth() - (bossRoomWallWidth * 2);
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int cellOriginToProtectionBounds = distanceToCellCenter + grid.getWallWidth() + bossRoomWallWidth;
        CellExtension bossRoom = region.getBossRoom();
        Location bossRoomOrigin = grid.getRegionBossRoomWorldOrigin(region);

        BoundsInt bossRoomBounds = new BoundsInt(new Vector3Int(-bossRoomWidth / 2, -bossRoomDepth, -cellOriginToProtectionBounds - bossRoomLength), new Vector3Int(bossRoomWidth / 2, grid.getWallHeight(), -cellOriginToProtectionBounds - 1));
        bossRoomBounds.rotateY(-bossRoom.getDirection().rotation);
        bossRoomBounds.shift(bossRoomOrigin);
        plugin.getAreaProtectionManager().addProtectionBounds(new PriorityProtectionBounds(0, bossRoomBounds, ProtectionType.PROTECTED));

        return bossRoomBounds;
    }

    public void generateBossRoomTriggers(BoundsInt bossRoomBounds) {
        CellExtension bossRoom = region.getBossRoom();
        if(bossRoom == null)
            return;

        MazeGrid grid = plugin.getMazeManager().getGrid();
        final int triggerWidth = 7;
        final int triggerHeight = 10;
        final int distanceToCellCenter = (grid.getRegionCellSize() - grid.getWallWidth()) / 2;
        final int entranceToExitDistance = 4;
        final int teleportDistance = 7;
        final float primaryDirectionYaw = (bossRoom.getDirection().id - 2) * 90;
        final float secondaryDirectionYaw = ((bossRoom.getDirection().id + 2 % 4) - 2) * 90;
        Location bossRoomOrigin = grid.getRegionBossRoomWorldOrigin(region);
        float cellCenterToEntranceTeleport = distanceToCellCenter + teleportDistance;
        float cellCenterToExitTeleport = distanceToCellCenter - 3;

        BoundsInt entrance = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2, triggerHeight, 0));
        entrance.shiftZ(-distanceToCellCenter);
        entrance.rotateY(-bossRoom.getDirection().rotation);
        entrance.shift(bossRoomOrigin);
        Location entranceTeleportLocation = new Location(grid.getWorld(), 0, 1, -cellCenterToEntranceTeleport, primaryDirectionYaw, 0);
        entranceTeleportLocation = LocationUtils.rotate(entranceTeleportLocation, -bossRoom.getDirection().rotation);
        entranceTeleportLocation = LocationUtils.shift(entranceTeleportLocation, bossRoomOrigin);
        entranceTeleportLocation = LocationUtils.centerOnBlock(entranceTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(entrance, new BossRoomEntranceConsumer(plugin, region, entranceTeleportLocation, bossRoomBounds), null));

        BoundsInt exit = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2, triggerHeight, 0));
        exit.shiftZ(-distanceToCellCenter - entranceToExitDistance);
        exit.rotateY(-bossRoom.getDirection().rotation);
        exit.shift(bossRoomOrigin);
        Location exitTeleportLocation = new Location(grid.getWorld(), 0, 1, cellCenterToExitTeleport, secondaryDirectionYaw, 0);
        exitTeleportLocation = LocationUtils.rotate(exitTeleportLocation, -bossRoom.getDirection().rotation);
        exitTeleportLocation = LocationUtils.shift(exitTeleportLocation, bossRoomOrigin);
        exitTeleportLocation = LocationUtils.centerOnBlock(exitTeleportLocation);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(exit, new BossRoomExitConsumer(plugin, region, exitTeleportLocation), null));
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(origin.getWorld().getUID());
        oos.writeDouble(origin.getX());
        oos.writeDouble(origin.getY());
        oos.writeDouble(origin.getZ());
        oos.writeFloat(origin.getYaw());
        oos.writeFloat(origin.getPitch());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        plugin = JavaPlugin.getPlugin(Main.class);
        UUID worldUuid = (UUID) ois.readObject();
        double x = ois.readDouble();
        double y = ois.readDouble();
        double z = ois.readDouble();
        float yaw = ois.readFloat();
        float pitch = ois.readFloat();
        origin = new Location(Bukkit.getWorld(worldUuid), x, y, z, yaw, pitch);
    }
}
