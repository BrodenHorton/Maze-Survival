package me.brody.mazesurvival.bounds;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class AreaProtectionManager implements Listener {
    private static final Map<Material, Integer> toolLevelByBreakableMaterial;

    private final Main plugin;
    private List<PriorityProtectionBounds> protectionBounds;

    static {
        toolLevelByBreakableMaterial = new HashMap<>();
        toolLevelByBreakableMaterial.put(Material.COBBLESTONE, 1);
        toolLevelByBreakableMaterial.put(Material.AMETHYST_CLUSTER, 2);
        toolLevelByBreakableMaterial.put(Material.COPPER_ORE, 2);
        toolLevelByBreakableMaterial.put(Material.LAPIS_ORE, 3);
        toolLevelByBreakableMaterial.put(Material.IRON_ORE, 3);
        toolLevelByBreakableMaterial.put(Material.NETHER_GOLD_ORE, 4);
        toolLevelByBreakableMaterial.put(Material.NETHER_QUARTZ_ORE, 5);
        toolLevelByBreakableMaterial.put(Material.DIAMOND_ORE, 5);
        toolLevelByBreakableMaterial.put(Material.ANCIENT_DEBRIS, 6);
        toolLevelByBreakableMaterial.put(Material.ANDESITE, 0);
        toolLevelByBreakableMaterial.put(Material.DIORITE, 0);
        toolLevelByBreakableMaterial.put(Material.GRANITE, 0);
        toolLevelByBreakableMaterial.put(Material.SAND, 0);
        toolLevelByBreakableMaterial.put(Material.RED_SAND, 0);
        toolLevelByBreakableMaterial.put(Material.CACTUS, 0);
        toolLevelByBreakableMaterial.put(Material.SUGAR_CANE, 0);
        toolLevelByBreakableMaterial.put(Material.BROWN_MUSHROOM, 0);
        toolLevelByBreakableMaterial.put(Material.RED_MUSHROOM, 0);
        toolLevelByBreakableMaterial.put(Material.NETHER_WART, 0);
        toolLevelByBreakableMaterial.put(Material.BLUE_ORCHID, 0);
        toolLevelByBreakableMaterial.put(Material.LILY_OF_THE_VALLEY, 0);
        toolLevelByBreakableMaterial.put(Material.OPEN_EYEBLOSSOM, 0);
        toolLevelByBreakableMaterial.put(Material.CLOSED_EYEBLOSSOM, 0);
        toolLevelByBreakableMaterial.put(Material.FLOWER_POT, 0);
        toolLevelByBreakableMaterial.put(Material.DEEPSLATE, 0);
    }

    public AreaProtectionManager(Main plugin) {
        this.plugin = plugin;
    }

    public void addProtectionBounds(PriorityProtectionBounds bounds) {
        int leftIndex = 0;
        int rightIndex = protectionBounds.size() - 1;
        int middleIndex = protectionBounds.size() / 2;

        while(leftIndex < rightIndex) {
            int eval = bounds.compareTo(protectionBounds.get(middleIndex));
            if(eval == 0)
                break;

            if(eval == 1) {
                rightIndex = middleIndex - 1;
            }
            else if(eval == -1)
                leftIndex = middleIndex + 1;

            middleIndex = (rightIndex + leftIndex) / 2;
        }

        protectionBounds.add(middleIndex, bounds);
    }

    public ProtectionType getProtectionType(Location location) {
        for(int i = 0; i < protectionBounds.size(); i++) {
            if(protectionBounds.get(i).getBounds().containsLocation(location))
                return protectionBounds.get(i).getProtectionType();
        }

        return ProtectionType.NONE;
    }

    public void protectionBoundsBlockBreak(PlayerInteractEvent e) {
        if(e.getPlayer() == null)
            return;
        if(e.getAction() != Action.LEFT_CLICK_BLOCK)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        ProtectionType protectionType = getProtectionType(e.getClickedBlock().getLocation());
        if(protectionType == ProtectionType.PROTECTED)
            e.setCancelled(true);
        else if(protectionType == ProtectionType.NONE) {
            if(!toolLevelByBreakableMaterial.containsKey(e.getClickedBlock().getType())) {
                e.setCancelled(true);
                return;
            }

            int toolLevel = 0;
            ItemStack heldItem = e.getPlayer().getEquipment().getItemInMainHand();
            if(heldItem != null) {
                ItemMeta meta = heldItem.getItemMeta();
                if(meta.getPersistentDataContainer().has(NamespacedKeys.TOOL_LEVEL, PersistentDataType.INTEGER))
                    toolLevel = meta.getPersistentDataContainer().get(NamespacedKeys.TOOL_LEVEL, PersistentDataType.INTEGER);
            }
            int requiredToolLevel = toolLevelByBreakableMaterial.get(e.getClickedBlock().getType());
            if(toolLevel < requiredToolLevel) {
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&bTool level &f" + requiredToolLevel + " &brequired to mine this resource")));
                e.setCancelled(true);
            }
        }
    }

    public void protectionBoundsBlockPlace(BlockPlaceEvent e) {
        if(e.getPlayer() == null)
            return;
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;

        ProtectionType protectionType = getProtectionType(e.getBlock().getLocation());
        if(protectionType != ProtectionType.BUILDABLE)
            e.setCancelled(true);
    }

}
