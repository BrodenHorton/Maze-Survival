package me.brody.mazesurvival.utils;

import org.bukkit.Location;

public class LocationUtils {
    public static Location copy(Location location) {
        return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public static Location shift(Location location, Vector3Int shift) {
        Location result = copy(location);
        result.setX(result.getX() + shift.x);
        result.setY(result.getY() + shift.y);
        result.setZ(result.getZ() + shift.z);

        return result;
    }

    public static Location shift(Location location, Location shift) {
        Location result = copy(location);
        result.setX(result.getX() + shift.getX());
        result.setY(result.getY() + shift.getY());
        result.setZ(result.getZ() + shift.getZ());

        return result;
    }

    public static Location rotate(Location location, int rotation) {
        Location result = copy(location);
        double rotationInRad = Math.toRadians(rotation);
        result.setX(location.getX() * Math.cos(rotationInRad) + location.getZ() * Math.sin(rotationInRad));
        result.setZ(-location.getX() * Math.sin(rotationInRad) + location.getZ() * Math.cos(rotationInRad));

        return result;
    }

    public static Location centerOnBlock(Location location) {
        Location result = copy(location);
        result.setX(result.getX() + 0.5);
        result.setZ(result.getZ() + 0.5);

        return result;
    }

    public static Location alignToBlockOrigin(Location location) {
        Location result = copy(location);
        result.setX((int)result.getX());
        result.setY((int)result.getY());
        result.setZ((int)result.getZ());
        result.setYaw(0f);
        result.setPitch(0f);

        return result;
    }
}
