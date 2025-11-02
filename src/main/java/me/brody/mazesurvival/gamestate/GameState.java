package me.brody.mazesurvival.gamestate;

import me.brody.mazesurvival.item.recipe.CustomRecipe;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.registry.Registry;

import java.util.*;

public class GameState {
    private static final List<CustomRecipe> BASE_RECIPES;

    private Set<UUID> clearedRegions;
    private List<UUID> discoveredRegions;
    private List<CustomRecipe> unlockedRecipes;

    static {
        BASE_RECIPES = List.of(
                Registry.CUSTOM_RECIPE.get("oak_to_log_iron_grade"),
                Registry.CUSTOM_RECIPE.get("cobblestone_iron_grade"),
                Registry.CUSTOM_RECIPE.get("leather_iron_grade"),
                Registry.CUSTOM_RECIPE.get("amethyst_shard_iron_grade"),
                Registry.CUSTOM_RECIPE.get("wheat_iron_grade"),
                Registry.CUSTOM_RECIPE.get("apple_iron_grade"),
                Registry.CUSTOM_RECIPE.get("carrot_iron_grade"),
                Registry.CUSTOM_RECIPE.get("rotten_flesh_iron_grade"),
                Registry.CUSTOM_RECIPE.get("string_iron_grade"),
                Registry.CUSTOM_RECIPE.get("spider_eye_iron_grade"),
                Registry.CUSTOM_RECIPE.get("slime_ball_iron_grade"),
                Registry.CUSTOM_RECIPE.get("gunpowder_iron_grade"),
                Registry.CUSTOM_RECIPE.get("wool"),
                Registry.CUSTOM_RECIPE.get("hard_leather_helmet"),
                Registry.CUSTOM_RECIPE.get("hard_leather_chestplate"),
                Registry.CUSTOM_RECIPE.get("hard_leather_leggings"),
                Registry.CUSTOM_RECIPE.get("hard_leather_boots"),
                Registry.CUSTOM_RECIPE.get("wooden_short_sword"),
                Registry.CUSTOM_RECIPE.get("wooden_hatchet"),
                Registry.CUSTOM_RECIPE.get("wooden_spade"),
                Registry.CUSTOM_RECIPE.get("wooden_scythe"),
                Registry.CUSTOM_RECIPE.get("worn_wooden_pickaxe"),
                Registry.CUSTOM_RECIPE.get("bastard_sword"),
                Registry.CUSTOM_RECIPE.get("battle_axe"),
                Registry.CUSTOM_RECIPE.get("mace"),
                Registry.CUSTOM_RECIPE.get("cobblestone_pickaxe"),
                Registry.CUSTOM_RECIPE.get("amethyst_helmet"),
                Registry.CUSTOM_RECIPE.get("amethyst_chestplate"),
                Registry.CUSTOM_RECIPE.get("amethyst_leggings"),
                Registry.CUSTOM_RECIPE.get("amethyst_boots")
        );
    }

    public GameState() {
        clearedRegions = new HashSet<>();
        discoveredRegions = new ArrayList<>();
        unlockedRecipes = new ArrayList<>();
        unlockedRecipes.addAll(BASE_RECIPES);
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
