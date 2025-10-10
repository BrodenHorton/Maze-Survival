package me.brody.mazesurvival.bounds;

public class PriorityProtectionBounds implements Comparable<PriorityProtectionBounds> {
    private Priority priority;
    private BoundsInt bounds;
    private boolean isProtectedArea;

    public PriorityProtectionBounds(Priority priority, BoundsInt bounds, boolean isProtectedArea) {
        this.priority = priority;
        this.bounds = bounds;
        this.isProtectedArea = isProtectedArea;
    }

    public Priority getPriority() {
        return priority;
    }

    public BoundsInt getBounds() {
        return bounds;
    }

    public boolean isProtectedArea() {
        return isProtectedArea;
    }

    @Override
    public int compareTo(PriorityProtectionBounds o) {
        return priority.compareTo(o.getPriority());
    }
}
