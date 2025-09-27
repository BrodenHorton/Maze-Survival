package me.brody.mazesurvival.brewing.persistentdata;

import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SimpleBrewingIngredientEntry extends BrewingIngredientEntry {

    public SimpleBrewingIngredientEntry(String ingredientName, int amount) {
        super(ingredientName, amount);
    }

    public SimpleBrewingIngredientEntry(String ingredientName, int amount, ItemGrade grade) {
        super(ingredientName, amount, grade);
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.valueOf(ingredientName));
        if(grade != null)
            ItemGradeUtils.setItemGrade(itemStack, grade);
        return itemStack;
    }

}
