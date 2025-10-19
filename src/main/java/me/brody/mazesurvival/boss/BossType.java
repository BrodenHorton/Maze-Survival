package me.brody.mazesurvival.boss;

public enum BossType {
    DESERT("desert-boss"),
    SWAMP("swamp-boss"),
    NETHER("nether-boss"),
    DEEP_DARK("deep-dark-boss");

    private String bossId;

    BossType(String bossId) {
        this.bossId = bossId;
    }

    public String getBossId() {
        return bossId;
    }
}
