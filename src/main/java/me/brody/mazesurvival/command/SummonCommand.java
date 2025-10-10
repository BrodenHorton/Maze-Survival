package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SummonCommand implements SubCommand {
    private static final String NAME = "Summon";
    private static final String DESCRIPTION = "Summon the specified custom mob.";

    public SummonCommand() {}

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 2) {
            ChatUtils.msg(p, String.format("%sInvalid args\nCommand Parameters: /ms summon <Mob>", ERROR_COLOR));
            return;
        }
        if(Registry.CUSTOM_MOB.get(args[1]) == null) {
            ChatUtils.msg(p, String.format("%sInvalid mob type: %s", ERROR_COLOR, args[1]));
            return;
        }

        CustomMob customMob = Registry.CUSTOM_MOB.get(args[1]);
        customMob.summon(p.getLocation());
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
        return new ArrayList<>(Registry.CUSTOM_MOB.keySet());
    }
}
