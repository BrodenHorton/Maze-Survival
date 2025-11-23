package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class DebugCommand implements SubCommand {
    private static final String NAME = "debug";
    private static final String DESCRIPTION = "Prints debug info about current game session";

    public DebugCommand() {}

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 1) {
            ChatUtils.msg(p, "&cInvalid args\nCommand Parameters: /ms debug");
            return;
        }

        String noData = "-";
        ChatUtils.msg(p, "&a===== Debug Info =====");
        ChatUtils.msg(p, "&eGameManager");
        String isGameRunningInfo = plugin.getGameManager() != null && plugin.getGameManager().isGameRunning() ? "true" : "false";
        ChatUtils.msg(p, "Is Game Running: " + isGameRunningInfo);
        String isDebugModeEnabled = plugin.getGameManager() != null && plugin.getGameManager().isDebugModeEnabled() ? "true" : "false";
        ChatUtils.msg(p, "Is Debug Mode Enabled: " + isDebugModeEnabled);
        ChatUtils.msg(p, "&eMazeManager:");
        String mazeManagerInfo = plugin.getMazeManager() != null ? plugin.getMazeManager().toString() : noData;
        ChatUtils.msg(p, mazeManagerInfo);
        ChatUtils.msg(p, "&eProfileManager");
        String profileManagerInfo = plugin.getProfileManager() != null ? plugin.getProfileManager().toString() : noData;
        ChatUtils.msg(p, profileManagerInfo);
        ChatUtils.msg(p, "&eDayNightCycle");
        String dayNightCycleInfo = plugin.getDayNightCycle() != null ? plugin.getDayNightCycle().toString() : noData;
        ChatUtils.msg(p, dayNightCycleInfo);
        ChatUtils.msg(p, "&eMobManager");
        String mobManagerInfo = plugin.getMobManager() != null ? plugin.getMobManager().toString() : noData;
        ChatUtils.msg(p, mobManagerInfo);
        ChatUtils.msg(p, "&eWanderingTraderManager");
        String wanderingTraderManagerInfo = plugin.getWanderingTraderManager() != null ? plugin.getWanderingTraderManager().toString() : noData;
        ChatUtils.msg(p, wanderingTraderManagerInfo);
        ChatUtils.msg(p, "&eAreaProtectionManager");
        String areaProtectionManagerInfo = plugin.getAreaProtectionManager() != null ? plugin.getAreaProtectionManager().toString() : noData;
        ChatUtils.msg(p, areaProtectionManagerInfo);
        ChatUtils.msg(p, "&eCollisionManager");
        String collisionManagerInfo = plugin.getCollisionManager() != null ? plugin.getCollisionManager().toString() : noData;
        ChatUtils.msg(p, collisionManagerInfo);
        ChatUtils.msg(p, "&eRespawnManager");
        String respawnManagerInfo = plugin.getRespawnManager() != null ? plugin.getRespawnManager().toString() : noData;
        ChatUtils.msg(p, respawnManagerInfo);
        ChatUtils.msg(p, "&eGameState");
        String gameStateInfo = plugin.getGameState() != null ? plugin.getGameState().toString() : noData;
        ChatUtils.msg(p, gameStateInfo);
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