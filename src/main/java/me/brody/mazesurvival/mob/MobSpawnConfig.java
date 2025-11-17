package me.brody.mazesurvival.mob;

import java.io.Serializable;

public class MobSpawnConfig implements Serializable {
    public static final long MOB_SPAWNING_UPDATE_IN_TICKS = 20L;

    private int maxMobsNearPlayer;
    private int maxConcurrentSpawnCount;
    private int maxCellSpawnDistance;
    private int minCellSpawnDistance;
    private int despawnRadius;
    private int mobRespawnCooldownInSeconds;

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
