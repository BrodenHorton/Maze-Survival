package me.brody.mazesurvival.maze.builder.structure;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.BoundsInt;
import me.brody.mazesurvival.bounds.CollisionBounds;
import me.brody.mazesurvival.bounds.PriorityProtectionBounds;
import me.brody.mazesurvival.bounds.ProtectionType;
import me.brody.mazesurvival.maze.Direction;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.CellExtension;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.utils.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class BossRoomStructure implements MazeStructureGenerator {
    private final Main plugin;
    private MazeSchematic schematic;
    private Location origin;
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
        generateBossRoomTriggers();
        generateProtectionBounds();
    }

    public void generateBossRoomTriggers() {
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
        final float halfBlockOffset = 0.5f;
        Location bossRoomOrigin = grid.getRegionBossRoomWorldOrigin(region);
        float cellCenterToEntranceTeleport = distanceToCellCenter + teleportDistance + halfBlockOffset;
        float cellCenterToExitTeleport = distanceToCellCenter - 3 + halfBlockOffset;

        BoundsInt entrance = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2, triggerHeight, 0));
        entrance.shiftZ(-distanceToCellCenter);
        entrance.rotateY(-bossRoom.getDirection().rotation);
        entrance.shift(bossRoomOrigin);
        Location entranceTeleportLocation = new Location(grid.getWorld(), halfBlockOffset, 1, -cellCenterToEntranceTeleport, primaryDirectionYaw, 0);
        entranceTeleportLocation = LocationUtils.rotate(entranceTeleportLocation, -bossRoom.getDirection().rotation);
        entranceTeleportLocation = LocationUtils.shift(entranceTeleportLocation, bossRoomOrigin);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(entrance, getBossRoomEntranceConsumer(entranceTeleportLocation), null));

        BoundsInt exit = new BoundsInt(new Vector3Int(-(triggerWidth / 2), 0, 0), new Vector3Int(triggerWidth / 2, triggerHeight, 0));
        exit.shiftZ(-distanceToCellCenter - entranceToExitDistance);
        exit.rotateY(-bossRoom.getDirection().rotation);
        exit.shift(bossRoomOrigin);
        Location exitTeleportLocation = new Location(grid.getWorld(), halfBlockOffset, 1, cellCenterToExitTeleport, secondaryDirectionYaw, 0);
        exitTeleportLocation = LocationUtils.rotate(exitTeleportLocation, -bossRoom.getDirection().rotation);
        exitTeleportLocation = LocationUtils.shift(exitTeleportLocation, bossRoomOrigin);
        plugin.getCollisionManager().addTriggerBounds(new CollisionBounds(exit, getBossRoomExitConsumer(exitTeleportLocation), null));
    }

    private Consumer<Player> getBossRoomEntranceConsumer(Location teleportLocation) {
        return (p) -> {
            if(region.getBossFight() != null)
                region.getBossFight().start(region);
            p.teleport(teleportLocation);
            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            ChatUtils.msg(p, "&aYou have triggered the boss fight!");
        };
    }

    private Consumer<Player> getBossRoomExitConsumer(Location teleportLocation) {
        return (p) -> {
            if(false) {
                p.teleport(teleportLocation);
                p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 1f);
            }
            else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bYou cannot exit until the boss is defeated")));
            }
        };
    }

    private void generateProtectionBounds() {
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
    }
}
