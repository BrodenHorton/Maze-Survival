package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class GradeCommand implements SubCommand {
    private static final String NAME = "Grade";
    private static final String DESCRIPTION = "Set the grade of the item in your main hand";
    private static final String ERR_MSG = "&cInvalid arguments\nCommand Parameters: /ms grade <Iron | Gold | Titanium>";

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 2) {
            ChatUtils.msg(p, ERR_MSG);
            return;
        }
        String gradeInput = args[1].toLowerCase();
        if(Registry.ITEM_GRADE.get(gradeInput) == null) {
            ChatUtils.msg(p, ERR_MSG);
            return;
        }

        ItemGrade grade = Registry.ITEM_GRADE.get(args[1]);
        ItemGradeUtils.setItemGrade(p.getInventory().getItemInMainHand(), grade);
        p.playSound(p, Sound.ENTITY_ITEM_PICKUP, 1, 1);
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
        return List.of("iron", "gold", "titanium");
    }
}
