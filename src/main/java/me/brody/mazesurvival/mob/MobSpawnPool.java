package me.brody.mazesurvival.mob;

import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.utils.WeightedList;

public class MobSpawnPool extends WeightedList<CustomMob> {
    public static MobSpawnPool STRONGHOLD_DAY_MOBS;
    public static MobSpawnPool STRONGHOLD_NIGHT_MOBS;
    public static MobSpawnPool DESERT_DAY_MOBS;
    public static MobSpawnPool DESERT_NIGHT_MOBS;
    public static MobSpawnPool SWAMP_DAY_MOBS;
    public static MobSpawnPool SWAMP_NIGHT_MOBS;
    public static MobSpawnPool NETHER_DAY_MOBS;
    public static MobSpawnPool NETHER_NIGHT_MOBS;
    public static MobSpawnPool BASTION_DAY_MOBS;
    public static MobSpawnPool BASTION_NIGHT_MOBS;
    public static MobSpawnPool DEEP_DARK_DAY_MOBS;
    public static MobSpawnPool DEEP_DARK_NIGHT_MOBS;

    private MobSpawnPool() {}

    public static void init() {
        STRONGHOLD_DAY_MOBS = new MobSpawnPool();
        STRONGHOLD_DAY_MOBS.add(CustomMob.REVENANT, 50);
        STRONGHOLD_DAY_MOBS.add(CustomMob.ARACHNID, 40);
        STRONGHOLD_DAY_MOBS.add(CustomMob.REMAINS, 35);
        STRONGHOLD_DAY_MOBS.add(CustomMob.BOMBER, 10);
        STRONGHOLD_DAY_MOBS.add(CustomMob.OOZE, 8);
        STRONGHOLD_DAY_MOBS.add(CustomMob.SWIFT_ARACHNID, 3);
        STRONGHOLD_DAY_MOBS.add(CustomMob.GOLDEN_HARE_STRONGHOLD, 1);

        STRONGHOLD_NIGHT_MOBS = new MobSpawnPool();
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.FORTIFIED_REVENANT, 20);
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.BONE_CRUSHER, 15);
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.TURBO_ARACHNID, 20);
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.SUPER_BOMBER, 10);
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.MEGA_MAGMA_OOZE, 5);
        STRONGHOLD_NIGHT_MOBS.add(CustomMob.WITHERED_REMAINS, 2);

        DESERT_DAY_MOBS = new MobSpawnPool();
        DESERT_DAY_MOBS.add(CustomMob.HUNGRY_HORROR, 20);
        DESERT_DAY_MOBS.add(CustomMob.INFUSED_REMAINS, 15);
        DESERT_DAY_MOBS.add(CustomMob.SWIFT_ARACHNID, 18);
        DESERT_DAY_MOBS.add(CustomMob.BOMBER, 8);
        DESERT_DAY_MOBS.add(CustomMob.MAZE_BLAZE, 5);
        DESERT_DAY_MOBS.add(CustomMob.MAZE_BREEZE, 1);
        DESERT_DAY_MOBS.add(CustomMob.GOLDEN_HARE_DESERT, 1);

        DESERT_NIGHT_MOBS = new MobSpawnPool();
        DESERT_NIGHT_MOBS.add(CustomMob.STARVED_HORROR, 20);
        DESERT_NIGHT_MOBS.add(CustomMob.TURBO_ARACHNID, 18);
        DESERT_NIGHT_MOBS.add(CustomMob.BONE_CRUSHER, 15);
        DESERT_NIGHT_MOBS.add(CustomMob.WITHERED_REMAINS, 10);
        DESERT_NIGHT_MOBS.add(CustomMob.CHARGED_BOMBER, 8);
        DESERT_NIGHT_MOBS.add(CustomMob.FIERY_FURY, 5);
        DESERT_NIGHT_MOBS.add(CustomMob.HURRICANE, 5);

        SWAMP_DAY_MOBS = new MobSpawnPool();
        SWAMP_DAY_MOBS.add(CustomMob.BOGGED_REMAINS, 25);
        SWAMP_DAY_MOBS.add(CustomMob.SWIFT_ARACHNID, 25);
        SWAMP_DAY_MOBS.add(CustomMob.OOZE, 20);
        SWAMP_DAY_MOBS.add(CustomMob.BIG_OOZE, 15);
        SWAMP_DAY_MOBS.add(CustomMob.ARISEN_REVENANT, 10);
        SWAMP_DAY_MOBS.add(CustomMob.SUPER_BOMBER, 5);
        SWAMP_DAY_MOBS.add(CustomMob.HURRICANE, 5);
        SWAMP_DAY_MOBS.add(CustomMob.ALCHEMIST, 2);
        SWAMP_DAY_MOBS.add(CustomMob.GOLDEN_HARE_SWAMP, 1);

        SWAMP_NIGHT_MOBS = new MobSpawnPool();
        SWAMP_NIGHT_MOBS.add(CustomMob.MAN_EATER, 30);
        SWAMP_NIGHT_MOBS.add(CustomMob.FORGOTTEN_REMAINS, 25);
        SWAMP_NIGHT_MOBS.add(CustomMob.MEGA_OOZE, 20);
        SWAMP_NIGHT_MOBS.add(CustomMob.CHARGED_BOMBER, 10);
        SWAMP_NIGHT_MOBS.add(CustomMob.ALCHEMIST, 2);

        NETHER_DAY_MOBS = new MobSpawnPool();
        NETHER_DAY_MOBS.add(CustomMob.MAZE_PIGLIN, 25);
        NETHER_DAY_MOBS.add(CustomMob.NETHER_BEAST, 25);
        NETHER_DAY_MOBS.add(CustomMob.MAGMA_OOZE, 20);
        NETHER_DAY_MOBS.add(CustomMob.BIG_MAGMA_OOZE, 15);
        NETHER_DAY_MOBS.add(CustomMob.MAZE_BLAZE, 10);
        NETHER_DAY_MOBS.add(CustomMob.WITHERED_REMAINS, 3);
        NETHER_DAY_MOBS.add(CustomMob.GOLDEN_HARE_NETHER, 1);

        NETHER_NIGHT_MOBS = new MobSpawnPool();
        NETHER_NIGHT_MOBS.add(CustomMob.EXECUTIONER, 25);
        NETHER_NIGHT_MOBS.add(CustomMob.GLUTTONOUS_BEAST, 25);
        NETHER_NIGHT_MOBS.add(CustomMob.ULTRA_MAGMA_OOZE, 20);
        NETHER_NIGHT_MOBS.add(CustomMob.INFERNO, 10);
        NETHER_NIGHT_MOBS.add(CustomMob.FORSAKEN_REMAINS, 3);

        DEEP_DARK_DAY_MOBS = new MobSpawnPool();
        DEEP_DARK_DAY_MOBS.add(CustomMob.FORTIFIED_REVENANT, 30);
        DEEP_DARK_DAY_MOBS.add(CustomMob.IMMORTAL_LEGIONARY, 30);
        DEEP_DARK_DAY_MOBS.add(CustomMob.FORSAKEN_REMAINS, 20);
        DEEP_DARK_DAY_MOBS.add(CustomMob.MAN_EATER, 20);
        DEEP_DARK_DAY_MOBS.add(CustomMob.BARBARIAN, 15);
        DEEP_DARK_DAY_MOBS.add(CustomMob.CHARGED_BOMBER, 15);
        DEEP_DARK_DAY_MOBS.add(CustomMob.SUPER_CHARGED_BOMBER, 5);
        DEEP_DARK_DAY_MOBS.add(CustomMob.GOLDEN_HARE_DEEP_DARK, 1);

        DEEP_DARK_NIGHT_MOBS = new MobSpawnPool();
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.DEATH_KNIGHT, 40);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.WRAITH, 40);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.ARCTIC_REMAINS, 30);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.KING_ARACHNID, 30);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.FORSAKEN_REMAINS, 20);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.SUPER_CHARGED_BOMBER, 15);
        DEEP_DARK_NIGHT_MOBS.add(CustomMob.BEHEMOTH, 10);
    }

}
