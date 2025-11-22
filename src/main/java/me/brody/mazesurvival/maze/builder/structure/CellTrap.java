package me.brody.mazesurvival.maze.builder.structure;

import org.bukkit.Material;

public class CellTrap {
    public static final CellTrap SLOWNESS_TRAP = new CellTrap("slowness", Material.SOUL_SAND, Material.BROWN_GLAZED_TERRACOTTA);
    public static final CellTrap BLINDNESS_TRAP = new CellTrap("blindness", Material.OBSIDIAN, Material.GRAY_GLAZED_TERRACOTTA);
    public static final CellTrap TELEPORTATION_TRAP = new CellTrap("teleportation", Material.CRYING_OBSIDIAN, Material.MAGENTA_GLAZED_TERRACOTTA);

    private String id;
    private Material surfaceBlock;
    private Material trapTriggerBlock;

    private CellTrap(String id, Material surfaceBlock, Material trapTriggerBlock) {
        this.id = id;
        this.surfaceBlock = surfaceBlock;
        this.trapTriggerBlock = trapTriggerBlock;
    }

    public String getId() {
        return id;
    }

    public Material getSurfaceBlock() {
        return surfaceBlock;
    }

    public Material getTrapTriggerBlock() {
        return trapTriggerBlock;
    }
}
