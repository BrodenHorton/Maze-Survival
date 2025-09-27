package me.brody.mazesurvival.listener.setbonus;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class MithrilSetBonusListener implements Listener {
    private static final Random RNG = new Random();
    private static final int DAMAGE_NEGATION_CHANCE = 25;

    private final Main plugin;

    public MithrilSetBonusListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void negateDamageRoll(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player player))
            return;
        if(!CustomItem.MITHRIL_HELMET.compareItem(player.getInventory().getHelmet()) || !CustomItem.MITHRIL_CHESTPLATE.compareItem(player.getInventory().getChestplate())
                || !CustomItem.MITHRIL_LEGGINGS.compareItem(player.getInventory().getLeggings()) || !CustomItem.MITHRIL_BOOTS.compareItem(player.getInventory().getBoots()))
            return;

        if(RNG.nextInt(DAMAGE_NEGATION_CHANCE) == 0) {
            e.setDamage(0);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "Attack Evaded!"));
            player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_FRAME_FILL, 1, 1);
        }
    }
}
