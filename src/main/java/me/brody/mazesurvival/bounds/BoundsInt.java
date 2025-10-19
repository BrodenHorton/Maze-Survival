package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;

/**
 * Represents a rectangular bounds between a min and max location
 */
public class BoundsInt {
    private Vector3Int min;
    private Vector3Int max;

    public BoundsInt(Vector3Int p1, Vector3Int p2) {
        min = new Vector3Int(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.min(p1.z, p2.z));
        max = new Vector3Int(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y), Math.max(p1.z, p2.z));
    }

    public BoundsInt(Location p1, Location p2) {
        min = new Vector3Int((int)Math.min(p1.getX(), p2.getX()), (int)Math.min(p1.getY(), p2.getY()), (int)Math.min(p1.getZ(), p2.getZ()));
        max = new Vector3Int((int)Math.max(p1.getX(), p2.getX()), (int)Math.max(p1.getY(), p2.getY()), (int)Math.max(p1.getZ(), p2.getZ()));
    }

    public boolean containsLocation(Location location) {
        return location.getX() >= min.x && location.getY() >= min.y && location.getZ() >= min.z
                && location.getX() < max.x && location.getY() < max.y && location.getZ() < max.z;
    }

    public void shift(Vector3Int shift) {
        min.add(shift);
        max.add(shift);
    }

    public void shift(Location location) {
        Vector3Int shift = new Vector3Int((int)Math.floor(location.getX()), (int)Math.floor(location.getY()), (int)Math.floor(location.getZ()));
        min.add(shift);
        max.add(shift);
    }

    public void shiftX(int xShift) {
        min.x += xShift;
        max.x += xShift;
    }

    public void shiftY(int yShift) {
        min.y += yShift;
        max.y += yShift;
    }

    public void shiftZ(int zShift) {
        min.z += zShift;
        max.z += zShift;
    }

    public BoundsInt clone() {
        return new BoundsInt(min, max);
    }

    public Vector3Int getMin() {
        return min;
    }

    public void setMin(Vector3Int min) {
        this.min = min;
    }

    public Vector3Int getMax() {
        return max;
    }

    public void setMax(Vector3Int max) {
        this.max = max;
    }
}
