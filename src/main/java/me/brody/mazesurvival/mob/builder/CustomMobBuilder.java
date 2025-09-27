package me.brody.mazesurvival.mob.builder;

import me.brody.mazesurvival.Main;

public abstract class CustomMobBuilder<T, U> {
    public final Main plugin;

    public double maxHealth;
    public double movementSpeed;
    public int powerAmplifier;

    public CustomMobBuilder(Main plugin) {
        this.plugin = plugin;
        maxHealth = 20.0;
        movementSpeed = 0.25;
        powerAmplifier = -1;
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

    public abstract T link();

    public abstract U build();
}
