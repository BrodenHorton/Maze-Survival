package me.brody.mazesurvival.utils;

import java.io.InputStream;

public class MazeSchematic {
    private static final String SCHEMATICS_FOLDER_PATH = "/schematics";
    private static final String STRONGHOLD_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/stronghold";
    private static final String DESERT_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/desert";
    private static final String SWAMP_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/swamp";
    private static final String NETHER_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/nether";
    private static final String BASTION_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/bastion";
    private static final String DEEP_DARK_FOLDER_PATH = SCHEMATICS_FOLDER_PATH + "/deepdark";

    public static final MazeSchematic GLADE = new MazeSchematic(SCHEMATICS_FOLDER_PATH + "/glade-7x7.schem");

    // Stronghold Decorations
    public static final MazeSchematic STRONGHOLD_DEADEND_1 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-deadend-1.schem");
    public static final MazeSchematic STRONGHOLD_DEADEND_2 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-deadend-2.schem");
    public static final MazeSchematic STRONGHOLD_DEADEND_3 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-deadend-3.schem");
    public static final MazeSchematic STRONGHOLD_DEADEND_4 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-deadend-4.schem");
    public static final MazeSchematic STRONGHOLD_CORRIDOR_1 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-corridor-1.schem");
    public static final MazeSchematic STRONGHOLD_CORRIDOR_2 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-corridor-2.schem");
    public static final MazeSchematic STRONGHOLD_CORRIDOR_3 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-corridor-3.schem");
    public static final MazeSchematic STRONGHOLD_CORRIDOR_4 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-corridor-4.schem");
    public static final MazeSchematic STRONGHOLD_BEND_1 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-bend-1.schem");
    public static final MazeSchematic STRONGHOLD_BEND_2 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-bend-2.schem");
    public static final MazeSchematic STRONGHOLD_BEND_3 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-bend-3.schem");
    public static final MazeSchematic STRONGHOLD_BEND_4 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-bend-4.schem");
    public static final MazeSchematic STRONGHOLD_T_INTERSECTION_1 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-T-intersection-1.schem");
    public static final MazeSchematic STRONGHOLD_T_INTERSECTION_2 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-T-intersection-2.schem");
    public static final MazeSchematic STRONGHOLD_T_INTERSECTION_3 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-T-intersection-3.schem");
    public static final MazeSchematic STRONGHOLD_INTERSECTION_1 = new MazeSchematic(STRONGHOLD_FOLDER_PATH + "/stronghold-intersection-1.schem");

    // Desert Decorations
    public static final MazeSchematic DESERT_DEADEND_1 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-deadend-1.schem");
    public static final MazeSchematic DESERT_DEADEND_2 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-deadend-2.schem");
    public static final MazeSchematic DESERT_DEADEND_3 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-deadend-3.schem");
    public static final MazeSchematic DESERT_DEADEND_4 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-deadend-4.schem");
    public static final MazeSchematic DESERT_CORRIDOR_1 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-corridor-1.schem");
    public static final MazeSchematic DESERT_CORRIDOR_2 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-corridor-2.schem");
    public static final MazeSchematic DESERT_CORRIDOR_3 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-corridor-3.schem");
    public static final MazeSchematic DESERT_CORRIDOR_4 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-corridor-4.schem");
    public static final MazeSchematic DESERT_BEND_1 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-bend-1.schem");
    public static final MazeSchematic DESERT_BEND_2 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-bend-2.schem");
    public static final MazeSchematic DESERT_BEND_3 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-bend-3.schem");
    public static final MazeSchematic DESERT_BEND_4 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-bend-4.schem");
    public static final MazeSchematic DESERT_T_INTERSECTION_1 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-T-intersection-1.schem");
    public static final MazeSchematic DESERT_T_INTERSECTION_2 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-T-intersection-2.schem");
    public static final MazeSchematic DESERT_T_INTERSECTION_3 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-T-intersection-3.schem");
    public static final MazeSchematic DESERT_T_INTERSECTION_4 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-T-intersection-4.schem");
    public static final MazeSchematic DESERT_INTERSECTION_1 = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-intersection-1.schem");
    // Desert Unique Decorations
    public static final MazeSchematic DESERT_OASIS = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-oasis.schem");

    // Swamp Decorations
    public static final MazeSchematic SWAMP_DEADEND_1 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-deadend-1.schem");
    public static final MazeSchematic SWAMP_DEADEND_2 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-deadend-2.schem");
    public static final MazeSchematic SWAMP_DEADEND_3 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-deadend-3.schem");
    public static final MazeSchematic SWAMP_DEADEND_4 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-deadend-4.schem");
    public static final MazeSchematic SWAMP_CORRIDOR_1 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-corridor-1.schem");
    public static final MazeSchematic SWAMP_CORRIDOR_2 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-corridor-2.schem");
    public static final MazeSchematic SWAMP_CORRIDOR_3 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-corridor-3.schem");
    public static final MazeSchematic SWAMP_CORRIDOR_4 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-corridor-4.schem");
    public static final MazeSchematic SWAMP_BEND_1 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-bend-1.schem");
    public static final MazeSchematic SWAMP_BEND_2 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-bend-2.schem");
    public static final MazeSchematic SWAMP_BEND_3 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-bend-3.schem");
    public static final MazeSchematic SWAMP_BEND_4 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-bend-4.schem");
    public static final MazeSchematic SWAMP_T_INTERSECTION_1 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-T-intersection-1.schem");
    public static final MazeSchematic SWAMP_T_INTERSECTION_2 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-T-intersection-2.schem");
    public static final MazeSchematic SWAMP_T_INTERSECTION_3 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-T-intersection-3.schem");
    public static final MazeSchematic SWAMP_INTERSECTION_1 = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-intersection-1.schem");
    // Swamp Unique Decorations
    public static final MazeSchematic SWAMP_WITCH_HUT = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-witch-hut.schem");

    // Nether Decorations
    public static final MazeSchematic NETHER_DEADEND_1 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-deadend-1.schem");
    public static final MazeSchematic NETHER_DEADEND_2 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-deadend-2.schem");
    public static final MazeSchematic NETHER_DEADEND_3 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-deadend-3.schem");
    public static final MazeSchematic NETHER_DEADEND_4 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-deadend-4.schem");
    public static final MazeSchematic NETHER_CORRIDOR_1 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-corridor-1.schem");
    public static final MazeSchematic NETHER_CORRIDOR_2 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-corridor-2.schem");
    public static final MazeSchematic NETHER_CORRIDOR_3 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-corridor-3.schem");
    public static final MazeSchematic NETHER_CORRIDOR_4 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-corridor-4.schem");
    public static final MazeSchematic NETHER_BEND_1 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-bend-1.schem");
    public static final MazeSchematic NETHER_BEND_2 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-bend-2.schem");
    public static final MazeSchematic NETHER_BEND_3 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-bend-3.schem");
    public static final MazeSchematic NETHER_BEND_4 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-bend-4.schem");
    public static final MazeSchematic NETHER_T_INTERSECTION_1 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-T-intersection-1.schem");
    public static final MazeSchematic NETHER_T_INTERSECTION_2 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-T-intersection-2.schem");
    public static final MazeSchematic NETHER_T_INTERSECTION_3 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-T-intersection-3.schem");
    public static final MazeSchematic NETHER_T_INTERSECTION_4 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-T-intersection-4.schem");
    public static final MazeSchematic NETHER_INTERSECTION_1 = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-intersection-1.schem");

    // Bastion Decorations
    public static final MazeSchematic BASTION_DEADEND_1 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-deadend-1.schem");
    public static final MazeSchematic BASTION_DEADEND_2 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-deadend-2.schem");
    public static final MazeSchematic BASTION_DEADEND_3 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-deadend-3.schem");
    public static final MazeSchematic BASTION_DEADEND_4 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-deadend-4.schem");
    public static final MazeSchematic BASTION_CORRIDOR_1 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-corridor-1.schem");
    public static final MazeSchematic BASTION_CORRIDOR_2 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-corridor-2.schem");
    public static final MazeSchematic BASTION_CORRIDOR_3 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-corridor-3.schem");
    public static final MazeSchematic BASTION_CORRIDOR_4 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-corridor-4.schem");
    public static final MazeSchematic BASTION_BEND_1 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-bend-1.schem");
    public static final MazeSchematic BASTION_BEND_2 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-bend-2.schem");
    public static final MazeSchematic BASTION_BEND_3 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-bend-3.schem");
    public static final MazeSchematic BASTION_BEND_4 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-bend-4.schem");
    public static final MazeSchematic BASTION_T_INTERSECTION_1 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-T-intersection-1.schem");
    public static final MazeSchematic BASTION_T_INTERSECTION_2 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-T-intersection-2.schem");
    public static final MazeSchematic BASTION_T_INTERSECTION_3 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-T-intersection-3.schem");
    public static final MazeSchematic BASTION_INTERSECTION_1 = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-intersection-1.schem");

    // Deep Dark Decorations
    public static final MazeSchematic DEEP_DARK_DEADEND_1 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-deadend-1.schem");
    public static final MazeSchematic DEEP_DARK_DEADEND_2 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-deadend-2.schem");
    public static final MazeSchematic DEEP_DARK_DEADEND_3 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-deadend-3.schem");
    public static final MazeSchematic DEEP_DARK_DEADEND_4 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-deadend-4.schem");
    public static final MazeSchematic DEEP_DARK_CORRIDOR_1 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-corridor-1.schem");
    public static final MazeSchematic DEEP_DARK_CORRIDOR_2 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-corridor-2.schem");
    public static final MazeSchematic DEEP_DARK_CORRIDOR_3 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-corridor-3.schem");
    public static final MazeSchematic DEEP_DARK_CORRIDOR_4 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-corridor-4.schem");
    public static final MazeSchematic DEEP_DARK_BEND_1 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-bend-1.schem");
    public static final MazeSchematic DEEP_DARK_BEND_2 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-bend-2.schem");
    public static final MazeSchematic DEEP_DARK_BEND_3 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-bend-3.schem");
    public static final MazeSchematic DEEP_DARK_BEND_4 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-bend-4.schem");
    public static final MazeSchematic DEEP_DARK_T_INTERSECTION_1 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-T-intersection-1.schem");
    public static final MazeSchematic DEEP_DARK_T_INTERSECTION_2 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-T-intersection-2.schem");
    public static final MazeSchematic DEEP_DARK_T_INTERSECTION_3 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-T-intersection-3.schem");
    public static final MazeSchematic DEEP_DARK_INTERSECTION_1 = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-intersection-1.schem");

    // Desert Transitions
    public static final MazeSchematic DESERT_HAVEN = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-haven.schem");
    public static final MazeSchematic DESERT_BOSS_ROOM = new MazeSchematic(DESERT_FOLDER_PATH + "/desert-boss-room.schem");

    // Swamp Transitions
    public static final MazeSchematic SWAMP_HAVEN = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-haven.schem");
    public static final MazeSchematic SWAMP_BOSS_ROOM = new MazeSchematic(SWAMP_FOLDER_PATH + "/swamp-boss-room.schem");

    // Nether Transitions
    public static final MazeSchematic NETHER_HAVEN = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-haven.schem");
    public static final MazeSchematic NETHER_BOSS_ROOM = new MazeSchematic(NETHER_FOLDER_PATH + "/nether-boss-room.schem");

    // Bastion Transitions
    public static final MazeSchematic BASTION_HAVEN = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-haven.schem");
    public static final MazeSchematic BASTION_BOSS_ROOM = new MazeSchematic(BASTION_FOLDER_PATH + "/bastion-boss-room.schem");

    // Deep Dark Transitions
    public static final MazeSchematic DEEP_DARK_HAVEN = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-haven.schem");
    public static final MazeSchematic DEEP_DARK_BOSS_ROOM = new MazeSchematic(DEEP_DARK_FOLDER_PATH + "/deep-dark-boss-room.schem");

    private String path;

    private MazeSchematic(String path) {
        this.path = path;
    }

    public InputStream getSchematicInputStream() {
        return this.getClass().getResourceAsStream(path);
    }

    public String getPath() {
        return new String(path);
    }
}
