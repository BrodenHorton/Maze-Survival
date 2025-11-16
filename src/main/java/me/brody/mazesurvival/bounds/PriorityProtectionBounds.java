package me.brody.mazesurvival.bounds;

import java.io.Serializable;

public class PriorityProtectionBounds implements Comparable<PriorityProtectionBounds>, Serializable {
    private int priority;
    private BoundsInt bounds;
    private ProtectionType protectionType;

    public PriorityProtectionBounds(int priority, BoundsInt bounds, ProtectionType protectionType) {
        this.priority = priority;
        this.bounds = bounds;
        this.protectionType = protectionType;
    }

    public int getPriority() {
        return priority;
    }

    public BoundsInt getBounds() {
        return bounds;
    }

    public ProtectionType getProtectionType() {
        return protectionType;
    }

    @Override
    public int compareTo(PriorityProtectionBounds other) {
        int value = 0;
        if(priority > other.priority)
            value = 1;
        else if(priority < other.priority)
            value = -1;

        return value;
    }
}
