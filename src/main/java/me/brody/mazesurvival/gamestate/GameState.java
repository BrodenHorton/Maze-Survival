package me.brody.mazesurvival.gamestate;

import me.brody.mazesurvival.item.recipe.CustomRecipe;
import me.brody.mazesurvival.maze.region.MazeRegion;

import java.util.*;

public class GameState {
    private Set<UUID> clearedRegions;
    private List<UUID> discoveredRegions;
    private List<CustomRecipe> unlockedRecipes;

    public GameState() {
        clearedRegions = new HashSet<>();
        discoveredRegions = new ArrayList<>();
        unlockedRecipes = new ArrayList<>();
    }

    public void addClearedRegion(MazeRegion region) {
        clearedRegions.add(region.getUuid());
    }

    public void addDiscoveredRegion(MazeRegion region) {
        discoveredRegions.add(region.getUuid());
        unlockedRecipes.addAll(region.getAccessibleRecipes());
    }

    public void addUnlockedRecipes(List<CustomRecipe> recipes) {
        unlockedRecipes.addAll(recipes);
    }

    public Set<UUID> getClearedRegions() {
        return clearedRegions;
    }

    public List<UUID> getDiscoveredRegions() {
        return discoveredRegions;
    }

    public List<CustomRecipe> getUnlockedRecipes() {
        return unlockedRecipes;
    }
}
