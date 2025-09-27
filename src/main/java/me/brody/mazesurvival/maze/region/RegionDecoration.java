package me.brody.mazesurvival.maze.region;

import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.MazeSchematic;

import java.util.*;

public class RegionDecoration {
    public static RegionDecoration NONE;
    public static RegionDecoration STRONGHOLD_DECORATIONS;
    public static RegionDecoration DESERT_DECORATIONS;
    public static RegionDecoration SWAMP_DECORATIONS;
    public static RegionDecoration NETHER_DECORATIONS;
    public static RegionDecoration BASTION_DECORATIONS;
    public static RegionDecoration DEEP_DARK_DECORATIONS;

    private Map<CellOrientation, List<MazeSchematic>> cellDecorations;
    private Map<CellOrientation, List<MazeSchematic>> uniqueDecorations;

    private RegionDecoration() {
        cellDecorations = new HashMap<>();
        uniqueDecorations = new HashMap<>();
    }

    static {
        NONE = new RegionDecoration();

        STRONGHOLD_DECORATIONS = new RegionDecoration();
        STRONGHOLD_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.STRONGHOLD_DEADEND_1, MazeSchematic.STRONGHOLD_DEADEND_2, MazeSchematic.STRONGHOLD_DEADEND_3, MazeSchematic.STRONGHOLD_DEADEND_4));
        STRONGHOLD_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.STRONGHOLD_CORRIDOR_1, MazeSchematic.STRONGHOLD_CORRIDOR_2, MazeSchematic.STRONGHOLD_CORRIDOR_3, MazeSchematic.STRONGHOLD_CORRIDOR_4));
        STRONGHOLD_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.STRONGHOLD_BEND_1, MazeSchematic.STRONGHOLD_BEND_2, MazeSchematic.STRONGHOLD_BEND_3, MazeSchematic.STRONGHOLD_BEND_4));
        STRONGHOLD_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.STRONGHOLD_T_INTERSECTION_1, MazeSchematic.STRONGHOLD_T_INTERSECTION_2, MazeSchematic.STRONGHOLD_T_INTERSECTION_3));
        STRONGHOLD_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.STRONGHOLD_INTERSECTION_1));

        DESERT_DECORATIONS = new RegionDecoration();
        DESERT_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.DESERT_DEADEND_1, MazeSchematic.DESERT_DEADEND_2, MazeSchematic.DESERT_DEADEND_3, MazeSchematic.DESERT_DEADEND_4));
        DESERT_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.DESERT_CORRIDOR_1, MazeSchematic.DESERT_CORRIDOR_2, MazeSchematic.DESERT_CORRIDOR_3, MazeSchematic.DESERT_CORRIDOR_4));
        DESERT_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.DESERT_BEND_1, MazeSchematic.DESERT_BEND_2, MazeSchematic.DESERT_BEND_3, MazeSchematic.DESERT_BEND_4));
        DESERT_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.DESERT_T_INTERSECTION_1, MazeSchematic.DESERT_T_INTERSECTION_2, MazeSchematic.DESERT_T_INTERSECTION_3, MazeSchematic.DESERT_T_INTERSECTION_4));
        DESERT_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.DESERT_INTERSECTION_1));
        DESERT_DECORATIONS.getUniqueDecorations().put(CellOrientation.DEADEND, List.of(MazeSchematic.DESERT_OASIS));

        SWAMP_DECORATIONS = new RegionDecoration();
        SWAMP_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.SWAMP_DEADEND_1, MazeSchematic.SWAMP_DEADEND_2, MazeSchematic.SWAMP_DEADEND_3, MazeSchematic.SWAMP_DEADEND_4));
        SWAMP_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.SWAMP_CORRIDOR_1, MazeSchematic.SWAMP_CORRIDOR_2, MazeSchematic.SWAMP_CORRIDOR_3, MazeSchematic.SWAMP_CORRIDOR_4));
        SWAMP_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.SWAMP_BEND_1, MazeSchematic.SWAMP_BEND_2, MazeSchematic.SWAMP_BEND_3, MazeSchematic.SWAMP_BEND_4));
        SWAMP_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.SWAMP_T_INTERSECTION_1, MazeSchematic.SWAMP_T_INTERSECTION_2, MazeSchematic.SWAMP_T_INTERSECTION_3));
        SWAMP_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.SWAMP_INTERSECTION_1));
        SWAMP_DECORATIONS.getUniqueDecorations().put(CellOrientation.DEADEND, List.of(MazeSchematic.SWAMP_WITCH_HUT));

        NETHER_DECORATIONS = new RegionDecoration();
        NETHER_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.NETHER_DEADEND_1, MazeSchematic.NETHER_DEADEND_2, MazeSchematic.NETHER_DEADEND_3, MazeSchematic.NETHER_DEADEND_4));
        NETHER_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.NETHER_CORRIDOR_1, MazeSchematic.NETHER_CORRIDOR_2, MazeSchematic.NETHER_CORRIDOR_3, MazeSchematic.NETHER_CORRIDOR_4));
        NETHER_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.NETHER_BEND_1, MazeSchematic.NETHER_BEND_2, MazeSchematic.NETHER_BEND_3, MazeSchematic.NETHER_BEND_4));
        NETHER_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.NETHER_T_INTERSECTION_1, MazeSchematic.NETHER_T_INTERSECTION_2, MazeSchematic.NETHER_T_INTERSECTION_3, MazeSchematic.NETHER_T_INTERSECTION_4));
        NETHER_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.NETHER_INTERSECTION_1));

        BASTION_DECORATIONS = new RegionDecoration();
        BASTION_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.BASTION_DEADEND_1, MazeSchematic.BASTION_DEADEND_2, MazeSchematic.BASTION_DEADEND_3, MazeSchematic.BASTION_DEADEND_4));
        BASTION_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.BASTION_CORRIDOR_1, MazeSchematic.BASTION_CORRIDOR_2, MazeSchematic.BASTION_CORRIDOR_3, MazeSchematic.BASTION_CORRIDOR_4));
        BASTION_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.BASTION_BEND_1, MazeSchematic.BASTION_BEND_2, MazeSchematic.BASTION_BEND_3, MazeSchematic.BASTION_BEND_4));
        BASTION_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.BASTION_T_INTERSECTION_1, MazeSchematic.BASTION_T_INTERSECTION_2, MazeSchematic.BASTION_T_INTERSECTION_3));
        BASTION_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.BASTION_INTERSECTION_1));

        DEEP_DARK_DECORATIONS = new RegionDecoration();
        DEEP_DARK_DECORATIONS.getCellDecorations().put(CellOrientation.DEADEND, List.of(
                MazeSchematic.DEEP_DARK_DEADEND_1, MazeSchematic.DEEP_DARK_DEADEND_2, MazeSchematic.DEEP_DARK_DEADEND_3, MazeSchematic.DEEP_DARK_DEADEND_4));
        DEEP_DARK_DECORATIONS.getCellDecorations().put(CellOrientation.CORRIDOR, List.of(
                MazeSchematic.DEEP_DARK_CORRIDOR_1, MazeSchematic.DEEP_DARK_CORRIDOR_2, MazeSchematic.DEEP_DARK_CORRIDOR_3, MazeSchematic.DEEP_DARK_CORRIDOR_4));
        DEEP_DARK_DECORATIONS.getCellDecorations().put(CellOrientation.BEND, List.of(
                MazeSchematic.DEEP_DARK_BEND_1, MazeSchematic.DEEP_DARK_BEND_2, MazeSchematic.DEEP_DARK_BEND_3, MazeSchematic.DEEP_DARK_BEND_4));
        DEEP_DARK_DECORATIONS.getCellDecorations().put(CellOrientation.T_INTERSECTION, List.of(
                MazeSchematic.DEEP_DARK_T_INTERSECTION_1, MazeSchematic.DEEP_DARK_T_INTERSECTION_2, MazeSchematic.DEEP_DARK_T_INTERSECTION_3));
        DEEP_DARK_DECORATIONS.getCellDecorations().put(CellOrientation.INTERSECTION, List.of(MazeSchematic.DEEP_DARK_INTERSECTION_1));
    }

    public MazeSchematic getRandomDecoration(CellOrientation orientation) {
        if(!cellDecorations.containsKey(orientation) || cellDecorations.get(orientation).isEmpty())
            return null;

        Random rand = new Random();
        List<MazeSchematic> decorations = cellDecorations.get(orientation);
        return decorations.get(rand.nextInt(0, decorations.size()));
    }

    public Map<CellOrientation, List<MazeSchematic>> getCellDecorations() {
        return cellDecorations;
    }

    public Map<CellOrientation, List<MazeSchematic>> getUniqueDecorations() {
        return uniqueDecorations;
    }
}
