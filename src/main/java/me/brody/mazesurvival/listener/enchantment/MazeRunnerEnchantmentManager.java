package me.brody.mazesurvival.listener.enchantment;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.enchantment.CustomEnchantment;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentEntry;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentList;
import me.brody.mazesurvival.enchantment.persistentdata.EnchantmentListDataType;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MazeRunnerEnchantmentManager {
    public static MazeRunnerEnchantmentManager instance;

    private Main plugin;

    private MazeRunnerEnchantmentManager() {}

    public void run(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this::updateMazeRunnerEnchantment, 0L, 1L);
    }

    private void updateMazeRunnerEnchantment() {
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            ItemStack boots = player.getInventory().getBoots();
            if( boots == null || boots.getType() == Material.AIR)
                continue;
            ItemMeta meta = boots.getItemMeta();
            if(!meta.getPersistentDataContainer().has(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType()))
                continue;

            EnchantmentList enchantmentList = meta.getPersistentDataContainer().get(NamespacedKeys.CUSTOM_ENCHANTMENTS, new EnchantmentListDataType());
            for(EnchantmentEntry enchantment : enchantmentList.getEnchantmentEntries()) {
                if(enchantment.getEnchantmentName().equals(CustomEnchantment.MAZE_RUNNER.getEnchantmentName())) {
                    if(player.getPotionEffect(PotionEffectType.SPEED) == null || player.getPotionEffect(PotionEffectType.SPEED).getAmplifier() < 2)
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 1, true));
                    break;
                }
            }
        }
    }

    public static MazeRunnerEnchantmentManager getInstance() {
        if(instance == null)
            instance = new MazeRunnerEnchantmentManager();

        return instance;
    }

}
