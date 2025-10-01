package me.brody.mazesurvival.mob;

import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.utils.WeightedEntry;
import me.brody.mazesurvival.utils.WeightedList;

import java.util.List;

public class MobSpawnPool {
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

    private WeightedList<CustomMob> weightedItems;

    private MobSpawnPool() {
        this.weightedItems = new WeightedList<>();
    }

    private MobSpawnPool(List<WeightedEntry<CustomMob>> weightedItems) {
        this.weightedItems = new WeightedList<>(weightedItems);
    }

    public static void init() {
        List<WeightedEntry<CustomMob>> strongholdDayMobList = List.of(
                new WeightedEntry<>(CustomMob.REVENANT, 50),
                new WeightedEntry<>(CustomMob.ARACHNID, 40),
                new WeightedEntry<>(CustomMob.REMAINS, 35),
                new WeightedEntry<>(CustomMob.BOMBER, 10),
                new WeightedEntry<>(CustomMob.OOZE, 8),
                new WeightedEntry<>(CustomMob.SWIFT_ARACHNID, 3),
                new WeightedEntry<>(CustomMob.GOLDEN_HARE, 1)
        );
        STRONGHOLD_DAY_MOBS = new MobSpawnPool(strongholdDayMobList);

        List<WeightedEntry<CustomMob>> strongholdNightMobList = List.of(
                new WeightedEntry<>(CustomMob.FORTIFIED_REVENANT, 20),
                new WeightedEntry<>(CustomMob.BONE_CRUSHER, 15),
                new WeightedEntry<>(CustomMob.TURBO_ARACHNID, 20),
                new WeightedEntry<>(CustomMob.SUPER_BOMBER, 10),
                new WeightedEntry<>(CustomMob.MEGA_MAGMA_OOZE, 5),
                new WeightedEntry<>(CustomMob.WITHERED_REMAINS, 2)
        );
        STRONGHOLD_NIGHT_MOBS = new MobSpawnPool(strongholdNightMobList);

        List<WeightedEntry<CustomMob>> desertDayMobList = List.of(
                new WeightedEntry<>(CustomMob.HUNGRY_HORROR, 20),
                new WeightedEntry<>(CustomMob.ARISEN_REVENANT, 10),
                new WeightedEntry<>(CustomMob.INFUSED_REMAINS, 15),
                new WeightedEntry<>(CustomMob.SWIFT_ARACHNID, 18),
                new WeightedEntry<>(CustomMob.BOMBER, 8),
                new WeightedEntry<>(CustomMob.MAZE_BREEZE, 1)
        );
        DESERT_DAY_MOBS = new MobSpawnPool(desertDayMobList);

        List<WeightedEntry<CustomMob>> desertNihgtMobList = List.of(
                new WeightedEntry<>(CustomMob.STARVED_HORROR, 20),
                new WeightedEntry<>(CustomMob.TURBO_ARACHNID, 18),
                new WeightedEntry<>(CustomMob.BONE_CRUSHER, 15),
                new WeightedEntry<>(CustomMob.WITHERED_REMAINS, 10),
                new WeightedEntry<>(CustomMob.CHARGED_BOMBER, 8),
                new WeightedEntry<>(CustomMob.FIERY_FURY, 5)
        );
        DESERT_NIGHT_MOBS = new MobSpawnPool(desertNihgtMobList);

        List<WeightedEntry<CustomMob>> swampDayMobList = List.of(
                new WeightedEntry<>(CustomMob.BOGGED_REMAINS, 25),
                new WeightedEntry<>(CustomMob.SWIFT_ARACHNID, 25),
                new WeightedEntry<>(CustomMob.OOZE, 20),
                new WeightedEntry<>(CustomMob.BIG_OOZE, 15),
                new WeightedEntry<>(CustomMob.ARISEN_REVENANT, 10),
                new WeightedEntry<>(CustomMob.SUPER_BOMBER, 5),
                new WeightedEntry<>(CustomMob.ALCHEMIST, 2)
        );
        SWAMP_DAY_MOBS = new MobSpawnPool(swampDayMobList);

        List<WeightedEntry<CustomMob>> swampNightMobList = List.of(
                new WeightedEntry<>(CustomMob.MAN_EATER, 30),
                new WeightedEntry<>(CustomMob.FORGOTTEN_REMAINS, 25),
                new WeightedEntry<>(CustomMob.MEGA_OOZE, 20),
                new WeightedEntry<>(CustomMob.FORTIFIED_REVENANT, 15),
                new WeightedEntry<>(CustomMob.CHARGED_BOMBER, 10),
                new WeightedEntry<>(CustomMob.ALCHEMIST, 2)
        );
        SWAMP_NIGHT_MOBS = new MobSpawnPool(swampNightMobList);

        List<WeightedEntry<CustomMob>> netherDayMobList = List.of(
                new WeightedEntry<>(CustomMob.MAZE_PIGLIN, 25),
                new WeightedEntry<>(CustomMob.MAZE_HOGLIN, 25),
                new WeightedEntry<>(CustomMob.MAGMA_OOZE, 20),
                new WeightedEntry<>(CustomMob.BIG_MAGMA_OOZE, 15),
                new WeightedEntry<>(CustomMob.MAZE_BLAZE, 10),
                new WeightedEntry<>(CustomMob.WITHERED_REMAINS, 3)
        );
        NETHER_DAY_MOBS = new MobSpawnPool(netherDayMobList);

        List<WeightedEntry<CustomMob>> netherNightMobList = List.of(
                new WeightedEntry<>(CustomMob.MAZE_BRUTE, 25),
                new WeightedEntry<>(CustomMob.MAZE_HOGLIN, 25),
                new WeightedEntry<>(CustomMob.ULTRA_MAGMA_OOZE, 20),
                new WeightedEntry<>(CustomMob.INFERNO, 10),
                new WeightedEntry<>(CustomMob.FORSAKEN_REMAINS, 3)
        );
        NETHER_NIGHT_MOBS = new MobSpawnPool(netherNightMobList);

        List<WeightedEntry<CustomMob>> bastionDayMobList = List.of(
                new WeightedEntry<>(CustomMob.EXECUTIONER, 40),
                new WeightedEntry<>(CustomMob.FORTIFIED_REVENANT, 20),
                new WeightedEntry<>(CustomMob.WITHERED_REMAINS, 18),
                new WeightedEntry<>(CustomMob.MAZE_BRUTE, 15),
                new WeightedEntry<>(CustomMob.BONE_CRUSHER, 15),
                new WeightedEntry<>(CustomMob.TURBO_ARACHNID, 8)
        );
        BASTION_DAY_MOBS = new MobSpawnPool(bastionDayMobList);

        List<WeightedEntry<CustomMob>> bastionNightMobList = List.of(
                new WeightedEntry<>(CustomMob.MAZE_VINDICATOR, 40),
                new WeightedEntry<>(CustomMob.BARBARIAN, 20),
                new WeightedEntry<>(CustomMob.IMMORTAL_LEGIONARY, 20),
                new WeightedEntry<>(CustomMob.KING_ARACHNID, 15),
                new WeightedEntry<>(CustomMob.BEHEMOTH, 8),
                new WeightedEntry<>(CustomMob.HURRICANE, 3)
        );
        BASTION_NIGHT_MOBS = new MobSpawnPool(bastionNightMobList);

        List<WeightedEntry<CustomMob>> deepDarkDayMobList = List.of(
                new WeightedEntry<>(CustomMob.FORTIFIED_REVENANT, 40),
                new WeightedEntry<>(CustomMob.IMMORTAL_LEGIONARY, 40),
                new WeightedEntry<>(CustomMob.FORSAKEN_REMAINS, 30),
                new WeightedEntry<>(CustomMob.MAN_EATER, 30),
                new WeightedEntry<>(CustomMob.BARBARIAN, 25),
                new WeightedEntry<>(CustomMob.CHARGED_BOMBER, 25),
                new WeightedEntry<>(CustomMob.SUPER_CHARGED_BOMBER, 5)
        );
        DEEP_DARK_DAY_MOBS = new MobSpawnPool(deepDarkDayMobList);

        List<WeightedEntry<CustomMob>> deepDarkNightMobList = List.of(
                new WeightedEntry<>(CustomMob.DEATH_KNIGHT, 40),
                new WeightedEntry<>(CustomMob.WRAITH, 40),
                new WeightedEntry<>(CustomMob.ARCTIC_REMAINS, 30),
                new WeightedEntry<>(CustomMob.KING_ARACHNID, 30),
                new WeightedEntry<>(CustomMob.FORSAKEN_REMAINS, 20),
                new WeightedEntry<>(CustomMob.SUPER_CHARGED_BOMBER, 15)
        );
        DEEP_DARK_NIGHT_MOBS = new MobSpawnPool(deepDarkNightMobList);
    }

    public WeightedList<CustomMob> getWeightedItems() {
        return weightedItems;
    }

}
