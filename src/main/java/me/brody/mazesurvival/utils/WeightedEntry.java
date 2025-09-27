package me.brody.mazesurvival.utils;

public class WeightedEntry<T> {
    private T value;
    private int weight;

    public WeightedEntry(T value, int weight) {
        this.value = value;
        this.weight = Math.max(weight, 0);
    }

    public T getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
