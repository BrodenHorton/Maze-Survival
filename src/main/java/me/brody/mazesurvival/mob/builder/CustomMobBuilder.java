package me.brody.mazesurvival.mob.builder;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.drop.MobDropTable;
import me.brody.mazesurvival.mob.drop.SimpleMobDropTable;

public abstract class CustomMobBuilder<T, U> {
    public final Main plugin;

    public String mobName;
    public double maxHealth;
    public double movementSpeed;
    public int powerAmplifier;
    public boolean isBaby;
    public MobDropTable dropTable;

    public CustomMobBuilder(Main plugin, String mobName) {
        this.plugin = plugin;
        this.mobName = mobName;
        maxHealth = 20.0;
        movementSpeed = 0.25;
        powerAmplifier = -1;
        isBaby = false;
        dropTable = new SimpleMobDropTable();
    }

    public T withMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
        return link();
    }

    public T withMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
        return link();
    }

    public T withPowerAmplifier(int powerAmplifier) {
        this.powerAmplifier = powerAmplifier;
        return link();
    }

    public T withBaby(boolean isBaby) {
        this.isBaby = isBaby;
        return link();
    }

    public T withDropTable(MobDropTable dropTable) {
        this.dropTable = dropTable;
        return link();
    }

    public abstract T link();

    public abstract U build();
}
