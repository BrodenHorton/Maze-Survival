package me.brody.mazesurvival.brewing.persistentdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrewingIngredientList implements Serializable {
    private List<BrewingIngredientEntry> ingredients;

    public BrewingIngredientList() {
        ingredients = new ArrayList<>();
    }

    public List<BrewingIngredientEntry> getIngredients() {
        return ingredients;
    }
}
