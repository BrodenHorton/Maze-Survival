package me.brody.mazesurvival.utils;

import org.bukkit.Location;

public class BoundsInt {
    private Vector3Int min;
    private Vector3Int max;

    public BoundsInt(Location p1, Location p2) {
        //min = new Location(p1.getWorld(), Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()), Math.min(p1.getZ(), p2.getZ()));
        //max = new Location(p1.getWorld(), Math.max(p1.getX(), p2.getX()), Math.max(p1.getY(), p2.getY()), Math.max(p1.getZ(), p2.getZ()));
    }

    public boolean contains(Location location) {
        return false;
    }

    public Vector3Int getMin() {
        return min;
    }

    public Vector3Int getMax() {
        return max;
    }
}
