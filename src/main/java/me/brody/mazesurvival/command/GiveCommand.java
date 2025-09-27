package me.brody.mazesurvival.command;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ChatUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveCommand implements SubCommand {
    private static final String NAME = "Give";
    private static final String DESCRIPTION = "Places specified item into the players inventory";
    private static final String ERROR_MSG =
            String.format("%sInvalid args\nCommand Parameters: /ms give <Item Name> <Amount>", ERROR_COLOR);

    public GiveCommand() {}

    @Override
    public void executeCommand(Main plugin, Player p, String[] args) {
        if(args.length != 2 && args.length != 3 || Registry.CUSTOM_ITEM.get(args[1]) == null) {
            ChatUtils.msg(p, ERROR_MSG);
            return;
        }

        int amt = 1;
        if(args.length == 3) {
            try {
                amt = Integer.parseInt(args[2]);
            }
            catch(NumberFormatException e) {
                ChatUtils.msg(p, ERROR_MSG);
                return;
            }
        }
        ItemStack itemStack = Registry.CUSTOM_ITEM.get(args[1]).getItemStack();
        itemStack.setAmount(amt);
        p.getInventory().addItem(itemStack);
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
        return new ArrayList<>(Registry.CUSTOM_ITEM.keySet());
    }
}
