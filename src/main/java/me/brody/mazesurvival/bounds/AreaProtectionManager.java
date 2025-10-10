package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.Main;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class AreaProtectionManager implements Listener {
    private static final List<Material> breakableMaterials;

    private final Main plugin;
    private List<PriorityProtectionBounds> priorityProtectionBounds;

    static {
        breakableMaterials = new ArrayList<>();
        // TODO: Add breakable materials
    }

    public AreaProtectionManager(Main plugin) {
        this.plugin = plugin;
    }

    public void addProtectionBounds(Priority priority, BoundsInt bounds, boolean isProtectedArea) {
        // TODO: Sort list based on priority
        priorityProtectionBounds.add(new PriorityProtectionBounds(priority, bounds, isProtectedArea));
    }

    // TODO: Add block break event

    // TODO: Add block place event

}
