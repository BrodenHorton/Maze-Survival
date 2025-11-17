package me.brody.mazesurvival.registry;

import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.item.recipe.CustomRecipe;
import me.brody.mazesurvival.maze.grid.MazeGridBase;
import me.brody.mazesurvival.maze.region.MazeRegionBase;
import me.brody.mazesurvival.mob.custom.CustomMob;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry<T, K> {
    public static Registry<String, MazeGridBase> GRID_BASE = new Registry<>();
    public static Registry<String, MazeRegionBase> REGION_BASE = new Registry<>();
    public static Registry<String, CustomItem> CUSTOM_ITEM = new Registry<>();
    public static Registry<String, MazeEnchantment> ENCHANTMENT = new Registry<>();
    public static Registry<String, ItemGrade> ITEM_GRADE = new Registry<>();

    public static Registry<String, CustomMob> CUSTOM_MOB = new Registry<>();
    public static Registry<String, CustomRecipe> CUSTOM_RECIPE = new Registry<>();

    private Map<T, K> resources;

    private Registry() {
        resources = new HashMap<>();
    }

    public void register(T key, K value) {
        resources.put(key, value);
    }

    public K get(T key) {
        if(resources.get(key) == null)
            System.err.println("Registry key " + key.toString() + " is null.");

        return resources.get(key);
    }

    public boolean containsKey(T key) {
        return resources.containsKey(key);
    }

    public Set<T> keySet() {
        return resources.keySet();
    }
}
