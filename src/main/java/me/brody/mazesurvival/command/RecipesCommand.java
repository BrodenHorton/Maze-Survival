package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RecipesCommand implements SubCommand {
    private static final String NAME = "Recipes";
    private static final String DESCRIPTION = "Opens the custom recipe compendium";
    private static final String ERROR_MSG =
            String.format("%sInvalid args\nCommand Parameters: /ms recipes", ERROR_COLOR);

    public RecipesCommand() {}

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 1) {
            ChatUtils.msg(p, ERROR_MSG);
            return;
        }

        plugin.getCustomRecipeCompendium().open(p, 0);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public List<String> getTabCompletionList() {
        return new ArrayList<>();
    }
}
