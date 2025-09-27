package me.brody.mazesurvival.listener;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.CustomEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PlayerHealthManager {
    public static final int PLAYER_BASE_HEALTH = 6;

    public static PlayerHealthManager instance;

    private Main plugin;

    private PlayerHealthManager() {}

    public void run(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updatePlayerHealth, 0L, 1L);
    }

    private void updatePlayerHealth() {
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            double maxHealth = PLAYER_BASE_HEALTH;
            for(ItemStack item : player.getEquipment().getArmorContents()) {
                if(item == null || item.getItemMeta() == null)
                    continue;

                ItemMeta meta = item.getItemMeta();
                if(meta.getPersistentDataContainer().has(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER))
                    maxHealth += meta.getPersistentDataContainer().get(NamespacedKeys.ARMOR_HEALTH_BOOST, PersistentDataType.INTEGER);
                if(meta.getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType())) {
                    EnchantmentList enchantmentList = meta.getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
                    for(EnchantmentEntry enchantment : enchantmentList.getEnchantmentEntries()) {
                        if(enchantment.getEnchantmentName().equals(CustomEnchantment.VITALITY.getEnchantmentName())) {
                            maxHealth += enchantment.getLevel() * 3;
                            break;
                        }
                    }
                }
            }

            if(player.getAttribute(Attribute.MAX_HEALTH).getValue() != maxHealth)
                player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        }
    }

    public static PlayerHealthManager getInstance() {
        if(instance == null)
            instance = new PlayerHealthManager();

        return instance;
    }
}
