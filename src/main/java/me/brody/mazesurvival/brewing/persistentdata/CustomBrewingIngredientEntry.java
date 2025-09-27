package me.brody.mazesurvival.brewing.persistentdata;

import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.inventory.ItemStack;

public class CustomBrewingIngredientEntry extends BrewingIngredientEntry {

    public CustomBrewingIngredientEntry(String ingredientName, int amount) {
        super(ingredientName, amount);
    }

    public CustomBrewingIngredientEntry(String ingredientName, int amount, ItemGrade grade) {
        super(ingredientName, amount, grade);
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemStack = Registry.CUSTOM_ITEM.get(ingredientName).getItemStack();
        if(grade != null)
            ItemGradeUtils.setItemGrade(itemStack, grade);
        return itemStack;
    }
}
