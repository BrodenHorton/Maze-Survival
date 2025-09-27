package me.brody.mazesurvival.mob;

public class MobSpawnConfig {
    public static final long MOB_SPAWNING_UPDATE_IN_TICKS = 20L;

    public static final MobSpawnConfig DAY_SPAWN_CONFIG;
    public static final MobSpawnConfig NIGHT_SPAWN_CONFIG;

    private int maxMobsNearPlayer;
    private int maxConcurrentSpawnCount;
    private int maxCellSpawnDistance;
    private int minCellSpawnDistance;
    private int despawnRadius;
    private int mobRespawnCooldownInSeconds;

    static {
        DAY_SPAWN_CONFIG = new MobSpawnConfig(7, 3, 8, 3, 80, 15);
        NIGHT_SPAWN_CONFIG = new MobSpawnConfig(10, 3, 6, 2, 65, 10);
    }

    public MobSpawnConfig(int maxMobsNearPlayer, int maxConcurrentSpawnCount, int maxCellSpawnDistance, int minCellSpawnDistance, int despawnRadius, int mobRespawnCooldownInSeconds) {
        this.maxMobsNearPlayer = maxMobsNearPlayer;
        this.maxConcurrentSpawnCount = maxConcurrentSpawnCount;
        this.maxCellSpawnDistance = maxCellSpawnDistance;
        this.minCellSpawnDistance = minCellSpawnDistance;
        this.despawnRadius = despawnRadius;
        this.mobRespawnCooldownInSeconds = mobRespawnCooldownInSeconds;
    }

    public int getMaxMobsNearPlayer() {
        return maxMobsNearPlayer;
    }

    public int getMaxConcurrentSpawnCount() {
        return maxConcurrentSpawnCount;
    }

    public int getMaxCellSpawnDistance() {
        return maxCellSpawnDistance;
    }

    public int getMinCellSpawnDistance() {
        return minCellSpawnDistance;
    }

    public int getDespawnRadius() {
        return despawnRadius;
    }

    public int getMobRespawnCooldownInSeconds() {
        return mobRespawnCooldownInSeconds;
    }
}
