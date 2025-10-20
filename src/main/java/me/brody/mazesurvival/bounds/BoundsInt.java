package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.utils.Vector3Int;
import org.bukkit.Location;

/**
 * Represents a rectangular bounds between a min and max location. Both min and max are inclusive.
 *
 * Example: A bounds of min 0,0,0 and max 0,0,0 will represent a 1x1x1 cube region at the location 0,0,0
 */
public class BoundsInt {
    private Vector3Int min;
    private Vector3Int max;

    public BoundsInt(Vector3Int p1, Vector3Int p2) {
        recalculateMinAndMax(p1, p2);
    }

    public BoundsInt(Location p1, Location p2) {
        recalculateMinAndMax(
                new Vector3Int((int)p1.getX(), (int)p1.getY(), (int)p1.getZ()),
                new Vector3Int((int)p2.getX(), (int)p2.getY(), (int)p2.getZ()));
    }

    public boolean containsLocation(Location location) {
        return location.getX() >= min.x && location.getY() >= min.y && location.getZ() >= min.z
                && Math.floor(location.getX()) <= max.x && Math.floor(location.getY()) <= max.y && Math.floor(location.getZ()) <= max.z;
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

    public void rotateY(int rotation) {
        min.rotateY(rotation);
        max.rotateY(rotation);
        recalculateMinAndMax(min, max);
    }

    public BoundsInt clone() {
        return new BoundsInt(min, max);
    }

    private void recalculateMinAndMax(Vector3Int p1, Vector3Int p2) {
        min = new Vector3Int(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.min(p1.z, p2.z));
        max = new Vector3Int(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y), Math.max(p1.z, p2.z));
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

    @Override
    public String toString() {
        return min.toString() + ", " + max.toString();
    }
}
