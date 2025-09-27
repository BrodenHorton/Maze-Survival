package me.brody.mazesurvival.utils;

import org.bukkit.Location;

public class LocationCopier {
    public static Location copy(Location location) {
        return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
    }
}
