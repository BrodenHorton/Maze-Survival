package me.brody.mazesurvival.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedList<T> {
    private static Random rng = new Random();

    private List<WeightedEntry<T>> weightedEntries;

    public WeightedList() {
        weightedEntries = new ArrayList<>();
    }

    public WeightedList(List<WeightedEntry<T>> weightedEntries) {
        this.weightedEntries = new ArrayList<>(weightedEntries);
    }

    public void add(T value, int weight) {
        weightedEntries.add(new WeightedEntry<>(value, weight));
    }

    public T getWeightedValue() {
        if(weightedEntries.isEmpty())
            return null;

        int totalWeight = 0;
        for (int i = 0; i < weightedEntries.size(); i++)
            totalWeight += weightedEntries.get(i).getWeight();

        T value = null;
        int weightIndex = rng.nextInt(1, totalWeight + 1);
        for (int i = 0; i < weightedEntries.size(); i++) {
            weightIndex -= weightedEntries.get(i).getWeight();
            if (weightIndex <= 0) {
                value = weightedEntries.get(i).getValue();
                break;
            }
        }

        return value;
    }

    private Pair<T, Integer> getWeightedValueAndIndex(List<WeightedEntry<T>> weightedList) {
        Integer index = null;
        int totalWeight = 0;
        for (int i = 0; i < weightedList.size(); i++)
            totalWeight += weightedList.get(i).getWeight();

        T value = null;
        int weightIndex = rng.nextInt(1, totalWeight + 1);
        for (int i = 0; i < weightedList.size(); i++) {
            weightIndex -= weightedList.get(i).getWeight();
            if (weightIndex <= 0) {
                value = weightedList.get(i).getValue();
                index = i;
                break;
            }
        }

        return new Pair<>(value, index);
    }

    public List<T> getWeightedValues(int amt) {
        if(weightedEntries.isEmpty())
            return null;

        List<T> values = new ArrayList<>();
        for (int i = 0; i < amt; i++) {
            T value = getWeightedValue();
            if (value != null)
                values.add(value);
        }

        return values;
    }

    public List<T> getWeightedValues(int min, int max) {
        return getWeightedValues(rng.nextInt(min, max + 1));
    }

    public List<T> getWeightedValuesNoRepeat(int amt) {
        List<WeightedEntry<T>> weightedListCopy = new ArrayList<>(weightedEntries);
        List<T> results = new ArrayList<>();
        for(int i = 0; i < amt && !weightedListCopy.isEmpty(); i++) {
            Pair<T, Integer> valueAndIndex = getWeightedValueAndIndex(weightedListCopy);
            T value = valueAndIndex.x;
            int chosenIndex = valueAndIndex.y;
            results.add(value);
            weightedListCopy.remove(chosenIndex);
        }

        return results;
    }

    public List<T> getWeightedValuesNoRepeat(int min, int max) {
        return getWeightedValuesNoRepeat(rng.nextInt(min, max + 1));
    }

    public List<WeightedEntry<T>> getWeightedEntries() {
        return weightedEntries;
    }
}
