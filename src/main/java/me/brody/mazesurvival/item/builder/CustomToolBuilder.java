package me.brody.mazesurvival.item.builder;

import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomToolBuilder extends CustomItemBuilder<CustomToolBuilder> {
    private static Map<Material, Double> attackSpeedByTool;

    static {
        attackSpeedByTool = new HashMap<>();
        double swordAttackSpeed = -2.4;
        attackSpeedByTool.put(Material.WOODEN_SWORD, swordAttackSpeed);
        attackSpeedByTool.put(Material.STONE_SWORD, swordAttackSpeed);
        attackSpeedByTool.put(Material.IRON_SWORD, swordAttackSpeed);
        attackSpeedByTool.put(Material.GOLDEN_SWORD, swordAttackSpeed);
        attackSpeedByTool.put(Material.DIAMOND_SWORD, swordAttackSpeed);
        attackSpeedByTool.put(Material.NETHERITE_SWORD, swordAttackSpeed);
        double axeAttackSpeed = -3.0;
        attackSpeedByTool.put(Material.WOODEN_AXE, axeAttackSpeed);
        attackSpeedByTool.put(Material.STONE_AXE, axeAttackSpeed);
        attackSpeedByTool.put(Material.IRON_AXE, axeAttackSpeed);
        attackSpeedByTool.put(Material.GOLDEN_AXE, axeAttackSpeed);
        attackSpeedByTool.put(Material.DIAMOND_AXE, axeAttackSpeed);
        attackSpeedByTool.put(Material.NETHERITE_AXE, axeAttackSpeed);
        double shovelAttackSpeed = -2.2;
        attackSpeedByTool.put(Material.WOODEN_SHOVEL, shovelAttackSpeed);
        attackSpeedByTool.put(Material.STONE_SHOVEL, shovelAttackSpeed);
        attackSpeedByTool.put(Material.IRON_SHOVEL, shovelAttackSpeed);
        attackSpeedByTool.put(Material.GOLDEN_SHOVEL, shovelAttackSpeed);
        attackSpeedByTool.put(Material.DIAMOND_SHOVEL, shovelAttackSpeed);
        attackSpeedByTool.put(Material.NETHERITE_SHOVEL, shovelAttackSpeed);
        double hoeAttackSpeed = -2;
        attackSpeedByTool.put(Material.WOODEN_HOE, hoeAttackSpeed);
        attackSpeedByTool.put(Material.STONE_HOE, hoeAttackSpeed);
        attackSpeedByTool.put(Material.IRON_HOE, hoeAttackSpeed);
        attackSpeedByTool.put(Material.GOLDEN_HOE, hoeAttackSpeed);
        attackSpeedByTool.put(Material.DIAMOND_HOE, hoeAttackSpeed);
        attackSpeedByTool.put(Material.NETHERITE_HOE, hoeAttackSpeed);
        double pickaxeAttackSpeed = -2.8;
        attackSpeedByTool.put(Material.WOODEN_PICKAXE, pickaxeAttackSpeed);
        attackSpeedByTool.put(Material.STONE_PICKAXE, pickaxeAttackSpeed);
        attackSpeedByTool.put(Material.IRON_PICKAXE, pickaxeAttackSpeed);
        attackSpeedByTool.put(Material.GOLDEN_PICKAXE, pickaxeAttackSpeed);
        attackSpeedByTool.put(Material.DIAMOND_PICKAXE, pickaxeAttackSpeed);
        attackSpeedByTool.put(Material.NETHERITE_PICKAXE, pickaxeAttackSpeed);
    }

    public CustomToolBuilder(ItemStack itemStack, String customItemName) {
        super(itemStack, customItemName);
    }

    public CustomToolBuilder withBaseDamage(int baseDamage) {
        AttributeModifier attackDamageModifier = new AttributeModifier(NamespacedKey.minecraft("base_attack_damage"), baseDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        double attackSpeed = attackSpeedByTool.containsKey(itemStack.getType()) ? attackSpeedByTool.get(itemStack.getType()) : 0;
        AttributeModifier attackSpeedModifier = new AttributeModifier(NamespacedKey.minecraft("base_attack_speed"), attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifier);
        itemMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifier);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public CustomToolBuilder withToolLevel(int toolLevel) {
        if(toolLevel < 1)
            return this;

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NamespacedKeys.TOOL_LEVEL, PersistentDataType.INTEGER, toolLevel);
        List<String> lore = itemMeta.getLore() != null ? itemMeta.getLore() : new ArrayList<>();
        lore.add(0, ChatColor.GRAY + "Tool Level: " + ChatColor.WHITE + toolLevel);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    @Override
    public CustomToolBuilder link() {
        return this;
    }
}
