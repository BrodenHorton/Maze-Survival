package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class debugCommand implements SubCommand {
    private static final String NAME = "debug";
    private static final String DESCRIPTION = "Prints debug info about current game session";

    public debugCommand() {

    }

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 1) {
            ChatUtils.msg(p, "&cInvalid args\nCommand Parameters: /ms debug");
            return;
        }

        if(plugin.getMazeManager() == null || plugin.getMazeManager().getGrid() == null) {
            ChatUtils.msg(p, "&cThere is no game session currently in progress");
        }
        else {
            // TODO: Create debug info message
            ChatUtils.msg(p, "===== Debug Info =====");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
            ChatUtils.msg(p, "");
        }
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
        return List.of();
    }
}
