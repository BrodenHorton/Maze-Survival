package me.brody.mazesurvival.gamestate;

import me.brody.mazesurvival.item.recipe.CustomRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameState {
    private int regionsCleared;
    private List<UUID> havensDiscovered;
    private List<CustomRecipe> unlockedRecipes;

    public GameState() {
        regionsCleared = 0;
        havensDiscovered = new ArrayList<>();
    }

    public void incrementRegionsCleared() {
        regionsCleared++;
    }

    public void addHavenDiscovered(UUID havenUuid) {
        havensDiscovered.add(havenUuid);
    }

    public void addUnlockedRecipes(List<CustomRecipe> recipes) {
        unlockedRecipes.addAll(recipes);
    }

    public int getRegionsCleared() {
        return regionsCleared;
    }

    public List<UUID> getHavensDiscovered() {
        return havensDiscovered;
    }

    public List<CustomRecipe> getUnlockedRecipes() {
        return unlockedRecipes;
    }
}
