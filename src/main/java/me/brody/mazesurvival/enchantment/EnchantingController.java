package me.brody.mazesurvival.enchantment;

import me.brody.mazesurvival.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EnchantingController implements Listener {
    private final Main plugin;

    private EnchantingNavigationMenu navigationMenu;

    private List<TableEnchantment> armorEnchantments;
    private List<TableEnchantment> weaponEnchantments;
    private List<TableEnchantment> bowEnchantments;
    private List<TableEnchantment> universalEnchantments;

    public EnchantingController(Main plugin) {
        this.plugin = plugin;

        navigationMenu = new EnchantingNavigationMenu(plugin);

        armorEnchantments = new ArrayList<>();
        weaponEnchantments = new ArrayList<>();
        bowEnchantments = new ArrayList<>();
        universalEnchantments = new ArrayList<>();

        // Armor Enchantments
        ItemStack protectionIcon = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta protectionIconMeta = protectionIcon.getItemMeta();
        protectionIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Protection");
        List<String> protectionLore = List.of(ChatColor.GOLD + "Grants damage negation from all",
                ChatColor.GOLD + "damage sources.");
        protectionIconMeta.setLore(protectionLore);
        protectionIcon.setItemMeta(protectionIconMeta);
        armorEnchantments.add(new TableEnchantment(MazeEnchantment.PROTECTION, protectionIcon, List.of(15, 25, 35)));

        ItemStack thornsIcon = new ItemStack(Material.CACTUS);
        ItemMeta thornsIconMeta = thornsIcon.getItemMeta();
        thornsIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Thorns");
        List<String> thornsLore = List.of(ChatColor.GOLD + "Damages enemies that hit you",
                ChatColor.GOLD + "with melee attacks.");
        thornsIconMeta.setLore(thornsLore);
        thornsIcon.setItemMeta(thornsIconMeta);
        armorEnchantments.add(new TableEnchantment(MazeEnchantment.THORNS, thornsIcon, List.of(10, 15, 25)));

        ItemStack soulSpeedIcon = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta soulSpeedIconMeta = soulSpeedIcon.getItemMeta();
        soulSpeedIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Soul Speed");
        List<String> soulSpeedLore = List.of(ChatColor.GOLD + "Gives a speed boost for",
                ChatColor.GOLD + "a short duration when you",
                ChatColor.GOLD + "walk over soul sand.",
                ChatColor.GOLD + "You also no longer trigger",
                ChatColor.GOLD + "slowness traps");
        soulSpeedIconMeta.setLore(soulSpeedLore);
        soulSpeedIcon.setItemMeta(soulSpeedIconMeta);
        armorEnchantments.add(new TableEnchantment(MazeEnchantment.SOUL_SPEED, soulSpeedIcon, List.of(12, 24, 30)));

        ItemStack vitalityIcon = new ItemStack(Material.POTION);
        PotionMeta vitalityIconMeta = (PotionMeta)vitalityIcon.getItemMeta();
        vitalityIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Vitality");
        List<String> vitalityLore = List.of(ChatColor.GOLD + "Increases maximum player health");
        vitalityIconMeta.setLore(vitalityLore);
        vitalityIconMeta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0), true);
        vitalityIcon.setItemMeta(vitalityIconMeta);
        armorEnchantments.add(new TableEnchantment(MazeEnchantment.VITALITY, vitalityIcon, List.of(15, 24, 34)));

        // Weapon enchantments
        ItemStack sharpnessIcon = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta sharpnessIconMeta = sharpnessIcon.getItemMeta();
        sharpnessIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sharpness");
        List<String> sharpnessLore = List.of(ChatColor.GOLD + "Increases damage against all enemies.");
        sharpnessIconMeta.setLore(sharpnessLore);
        sharpnessIcon.setItemMeta(sharpnessIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.SHARPNESS, sharpnessIcon, List.of(15, 25, 35)));

        ItemStack smiteIcon = new ItemStack(Material.ZOMBIE_HEAD);
        ItemMeta smiteIconMeta = smiteIcon.getItemMeta();
        smiteIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Smite");
        List<String> smiteLore = List.of(ChatColor.GOLD + "Vastly increases damage dealt to",
                ChatColor.GOLD + "undead enemies.");
        smiteIconMeta.setLore(smiteLore);
        smiteIcon.setItemMeta(smiteIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.SMITE, smiteIcon, List.of(10, 15, 20, 25, 30)));

        ItemStack baneOfArthropodsIcon = new ItemStack(Material.SPIDER_EYE);
        ItemMeta baneOfArthropodsIconMeta = baneOfArthropodsIcon.getItemMeta();
        baneOfArthropodsIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Bane of Arthropods");
        List<String> baneOfArthropodsLore = List.of(ChatColor.GOLD + "Vastly increases damage dealt to",
                ChatColor.GOLD + "arthropod enemies.");
        baneOfArthropodsIconMeta.setLore(baneOfArthropodsLore);
        baneOfArthropodsIcon.setItemMeta(baneOfArthropodsIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.BANE_OF_ARTHROPODS, baneOfArthropodsIcon, List.of(10, 15, 20, 25, 30)));

        ItemStack fireAspectIcon = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta fireAspectIconMeta = fireAspectIcon.getItemMeta();
        fireAspectIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Fire Aspect");
        List<String> fireAspectLore = List.of(ChatColor.GOLD + "Ignites enemies on fire when hit.");
        fireAspectIconMeta.setLore(fireAspectLore);
        fireAspectIcon.setItemMeta(fireAspectIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.FIRE_ASPECT, fireAspectIcon, List.of(15, 25)));

        ItemStack knockbackIcon = new ItemStack(Material.WIND_CHARGE);
        ItemMeta knockbackIconMeta = knockbackIcon.getItemMeta();
        knockbackIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Knockback");
        List<String> knockbackLore = List.of(ChatColor.GOLD + "Knocks back enemies when hit.");
        knockbackIconMeta.setLore(knockbackLore);
        knockbackIcon.setItemMeta(knockbackIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.KNOCKBACK, knockbackIcon, List.of(10, 20, 30)));

        ItemStack lootingIcon = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta lootingIconMeta = lootingIcon.getItemMeta();
        lootingIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Looting");
        List<String> lootingLore = List.of(ChatColor.GOLD + "Enemies drop more loot on death.");
        lootingIconMeta.setLore(lootingLore);
        lootingIcon.setItemMeta(lootingIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.LOOTING, lootingIcon, List.of(10, 18, 26)));

        ItemStack crusaderIcon = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta crusaderIconMeta = crusaderIcon.getItemMeta();
        crusaderIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Crusader");
        List<String> crusaderLore = List.of(ChatColor.GOLD + "Grants extra xp when you kill",
                ChatColor.GOLD + "enemies. Enemies will no longer",
                ChatColor.GOLD + "drop loot.");
        crusaderIconMeta.setLore(crusaderLore);
        crusaderIcon.setItemMeta(crusaderIconMeta);
        weaponEnchantments.add(new TableEnchantment(MazeEnchantment.CRUSADER, crusaderIcon, List.of(8, 16, 24)));

        // Bow Enchantments
        ItemStack powerIcon = new ItemStack(Material.BOW);
        ItemMeta powerIconMeta = powerIcon.getItemMeta();
        powerIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Power");
        List<String> powerLore = List.of(ChatColor.GOLD + "Increases bow damage.");
        powerIconMeta.setLore(powerLore);
        powerIcon.setItemMeta(powerIconMeta);
        bowEnchantments.add(new TableEnchantment(MazeEnchantment.POWER, powerIcon, List.of(15, 26, 38)));

        ItemStack punchIcon = new ItemStack(Material.WIND_CHARGE);
        ItemMeta punchIconMeta = punchIcon.getItemMeta();
        punchIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Punch");
        List<String> punchLore = List.of(ChatColor.GOLD + "Knocks back enemies when hit",
                ChatColor.GOLD + "by an arrow.");
        punchIconMeta.setLore(punchLore);
        punchIcon.setItemMeta(punchIconMeta);
        bowEnchantments.add(new TableEnchantment(MazeEnchantment.PUNCH, punchIcon, List.of(10, 20, 32)));

        ItemStack flameIcon = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta flameIconMeta = flameIcon.getItemMeta();
        flameIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Flame");
        List<String> flameLore = List.of(ChatColor.GOLD + "Ignites enemies on fire when",
                ChatColor.GOLD + "hit by an arrow.");
        flameIconMeta.setLore(flameLore);
        flameIcon.setItemMeta(flameIconMeta);
        bowEnchantments.add(new TableEnchantment(MazeEnchantment.FLAME, flameIcon, List.of(22)));

        ItemStack lingeringShotIcon = new ItemStack(Material.TIPPED_ARROW);
        ItemMeta lingeringShotIconMeta = lingeringShotIcon.getItemMeta();
        lingeringShotIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Lingering Shot");
        List<String> lingeringShotLore = List.of(ChatColor.GOLD + "Inflicts enemy with given potion",
                ChatColor.GOLD + "effect if hit. If the arrow hits",
                ChatColor.GOLD + "a block, a lingering potion effect",
                ChatColor.GOLD + "will cover the ground.",
                "",
                ChatColor.GOLD + "Different items can be held in your",
                ChatColor.GOLD + "off hand to change the potion effect.",
                ChatColor.GOLD + "The held item is lost on use:",
                ChatColor.WHITE + "Poison: " + ChatColor.GRAY + "Spider Eye",
                ChatColor.WHITE + "Regeneration: " + ChatColor.GRAY + "Ghast Tear",
                ChatColor.WHITE + "Slowness: " + ChatColor.GRAY + "Cobweb",
                ChatColor.WHITE + "Speed: " + ChatColor.GRAY + "Sugar",
                ChatColor.WHITE + "Wither: " + ChatColor.GRAY + "Wither Rose");
        lingeringShotIconMeta.setLore(lingeringShotLore);
        ((PotionMeta)lingeringShotIconMeta).addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 5, 0), true);
        lingeringShotIcon.setItemMeta(lingeringShotIconMeta);
        bowEnchantments.add(new TableEnchantment(MazeEnchantment.LINGERING_SHOT, lingeringShotIcon, List.of(12, 18, 24)));

        // Universal Enchantments
        ItemStack unbreakingIcon = new ItemStack(Material.BEDROCK);
        ItemMeta unbreakingIconMeta = unbreakingIcon.getItemMeta();
        unbreakingIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Unbreaking");
        List<String> unbreakingLore = List.of(ChatColor.GOLD + "Increases the durability of an item.");
        unbreakingIconMeta.setLore(unbreakingLore);
        unbreakingIcon.setItemMeta(unbreakingIconMeta);
        universalEnchantments.add(new TableEnchantment(MazeEnchantment.UNBREAKING, unbreakingIcon, List.of(12, 25, 36)));

        ItemStack soulBoundIcon = new ItemStack(Material.SOUL_LANTERN);
        ItemMeta soulBoundIconMeta = soulBoundIcon.getItemMeta();
        soulBoundIconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Soul Bound");
        List<String> soulBoundLore = List.of(ChatColor.GOLD + "When the user dies, the item",
                ChatColor.GOLD + "is not lost.");
        soulBoundIconMeta.setLore(soulBoundLore);
        soulBoundIcon.setItemMeta(soulBoundIconMeta);
        universalEnchantments.add(new TableEnchantment(MazeEnchantment.SOUL_BOUND, soulBoundIcon, List.of(30)));
    }

    public void openNavigationMenu(Player player) {
        navigationMenu.openEnchantingMenu(player);
    }

    public void openArmorEnchantingMenu(Player player) {
        EnchantingMenu enchantingMenu = new EnchantingMenu(plugin, armorEnchantments);
        enchantingMenu.openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), 1);
    }

    public void openWeaponEnchantingMenu(Player player) {
        EnchantingMenu enchantingMenu = new EnchantingMenu(plugin, weaponEnchantments);
        enchantingMenu.openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), 1);
    }

    public void openBowEnchantingMenu(Player player) {
        EnchantingMenu enchantingMenu = new EnchantingMenu(plugin, bowEnchantments);
        enchantingMenu.openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), 1);
    }

    public void openUniversalEnchantingMenu(Player player) {
        EnchantingMenu enchantingMenu = new EnchantingMenu(plugin, universalEnchantments);
        enchantingMenu.openEnchantingMenu(plugin.getProfileManager().getProfileOf(player), 1);
    }

    public EnchantingNavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    @EventHandler
    public void openArmorEnchantingMenuFromNav(InventoryClickEvent e) {
        if(e.getView().getTitle() != EnchantingNavigationMenu.INVENTORY_NAME)
            return;
        if(e.getRawSlot() >= EnchantingNavigationMenu.INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        if(!e.getCurrentItem().equals(EnchantingNavigationMenu.ARMOR_ICON))
            return;

        Player player = (Player)e.getWhoClicked();
        openArmorEnchantingMenu(player);
    }

    @EventHandler
    public void openWeaponEnchantingMenuFromNav(InventoryClickEvent e) {
        if(e.getView().getTitle() != EnchantingNavigationMenu.INVENTORY_NAME)
            return;
        if(e.getRawSlot() >= EnchantingNavigationMenu.INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        if(!e.getCurrentItem().equals(EnchantingNavigationMenu.WEAPON_ICON))
            return;

        Player player = (Player)e.getWhoClicked();
        openWeaponEnchantingMenu(player);
    }

    @EventHandler
    public void openBowEnchantingMenuFromNav(InventoryClickEvent e) {
        if(e.getView().getTitle() != EnchantingNavigationMenu.INVENTORY_NAME)
            return;
        if(e.getRawSlot() >= EnchantingNavigationMenu.INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        if(!e.getCurrentItem().equals(EnchantingNavigationMenu.BOW_ICON))
            return;

        Player player = (Player)e.getWhoClicked();
        openBowEnchantingMenu(player);
    }

    @EventHandler
    public void openUniversalEnchantingMenuFromNav(InventoryClickEvent e) {
        if(e.getView().getTitle() != EnchantingNavigationMenu.INVENTORY_NAME)
            return;
        if(e.getRawSlot() >= EnchantingNavigationMenu.INVENTORY_SIZE)
            return;
        if(e.getCurrentItem() == null)
            return;
        e.setCancelled(true);
        if(!e.getCurrentItem().equals(EnchantingNavigationMenu.UNIVERSAL_ICON))
            return;

        Player player = (Player)e.getWhoClicked();
        openUniversalEnchantingMenu(player);
    }
}
