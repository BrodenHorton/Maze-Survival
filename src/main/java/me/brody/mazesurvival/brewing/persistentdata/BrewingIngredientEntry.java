package me.brody.mazesurvival.brewing.persistentdata;

import me.brody.mazesurvival.item.ItemGrade;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public abstract class BrewingIngredientEntry implements Serializable {
    protected String ingredientName;
    protected int amount;
    protected ItemGrade grade;

    public BrewingIngredientEntry(String ingredientName, int amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public BrewingIngredientEntry(String ingredientName, int amount, ItemGrade grade) {
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.grade = grade;
    }

    public abstract ItemStack getItem();

    public String getIngredientName() {
        return ingredientName;
    }

    public int getAmount() {
        return amount;
    }

    public ItemGrade getGrade() {
        return grade;
    }
}
