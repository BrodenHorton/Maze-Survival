package me.brody.mazesurvival.utils;

import java.util.Objects;

public class Vector3Int {
    public int x, y, z;

    public Vector3Int(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3Int addition) {
        x += addition.x;
        y += addition.y;
        z += addition.z;
    }

    public void rotateY(int rotation) {
        double rotationInRad = Math.toRadians(rotation);
        int xEval = (int)(x * Math.cos(rotationInRad) + z * Math.sin(rotationInRad));
        int zEval = (int)(-x * Math.sin(rotationInRad) + z * Math.cos(rotationInRad));
        System.out.println("rotation: " + rotation + ", xEval: " + xEval + ", zEval: " + zEval);
        x = xEval;
        z = zEval;
    }

    public Vector3Int clone() {
        return new Vector3Int(x, y, z);
    }

    public static Vector3Int Zero(){
        return new Vector3Int(0, 0, 0);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Vector3Int other))
            return false;

        return other.x == this.x && other.y == this.y && other.z == this.z;
    }

    @Override
    public int hashCode() {
        return 7 * 31 + Objects.hashCode(x) + Objects.hashCode(y) + Objects.hashCode(z);
    }
}
