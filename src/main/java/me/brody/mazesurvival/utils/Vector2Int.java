package me.brody.mazesurvival.utils;

import java.util.Objects;

public class Vector2Int {
    public int x, y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Int clone() {
        return new Vector2Int(x, y);
    }

    public static Vector2Int Zero(){
        return new Vector2Int(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Vector2Int other))
            return false;

        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
        return 7 * 31 + Objects.hashCode(x) + Objects.hashCode(y);
    }
}
