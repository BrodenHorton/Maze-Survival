package me.brody.mazesurvival.item;

import me.brody.mazesurvival.registry.Registry;
import org.bukkit.ChatColor;

public enum ItemGrade {
    IRON("Iron", ChatColor.GRAY),
    GOLD("Gold", ChatColor.YELLOW),
    TITANIUM("Titanium", ChatColor.BLUE);

    private String name;
    private ChatColor color;

    ItemGrade(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public static void register() {
        Registry.ITEM_GRADE.register(IRON.name.toLowerCase(), IRON);
        Registry.ITEM_GRADE.register(GOLD.name.toLowerCase(), GOLD);
        Registry.ITEM_GRADE.register(TITANIUM.name.toLowerCase(), TITANIUM);
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
