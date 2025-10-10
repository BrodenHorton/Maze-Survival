package me.brody.mazesurvival.maze.builder.structure;

import org.bukkit.Material;

public class CellTrap {
    public static final CellTrap SLOWNESS_TRAP = new CellTrap(Material.SOUL_SAND, Material.BROWN_GLAZED_TERRACOTTA);
    public static final CellTrap BLINDNESS_TRAP = new CellTrap(Material.OBSIDIAN, Material.GRAY_GLAZED_TERRACOTTA);
    public static final CellTrap TELEPORTATION_TRAP = new CellTrap(Material.CRYING_OBSIDIAN, Material.MAGENTA_GLAZED_TERRACOTTA);

    private Material surfaceBlock;
    private Material trapTriggerBlock;

    private CellTrap(Material surfaceBlock, Material trapTriggerBlock) {
        this.surfaceBlock = surfaceBlock;
        this.trapTriggerBlock = trapTriggerBlock;
    }

    public Material getSurfaceBlock() {
        return surfaceBlock;
    }

    public Material getTrapTriggerBlock() {
        return trapTriggerBlock;
    }
}
