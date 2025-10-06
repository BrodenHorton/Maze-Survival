package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.mob.drop.MobDropTable;
import me.brody.mazesurvival.mob.drop.SimpleMobDropTable;
import me.brody.mazesurvival.mob.drop.SpecialMobDropTable;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;
import org.bukkit.inventory.ItemStack;
import me.brody.mazesurvival.mob.custom.SimpleCustomMob.SimpleCustomMobBuilder;
import me.brody.mazesurvival.mob.custom.SimpleCustomArmorMob.SimpleCustomArmorMobBuilder;
import me.brody.mazesurvival.mob.custom.Revenant.RevenantBuilder;
import me.brody.mazesurvival.mob.custom.Remains.RemainsBuilder;
import me.brody.mazesurvival.mob.custom.Bomber.BomberBuilder;
import me.brody.mazesurvival.mob.custom.MazePiglin.MazePiglinBuilder;
import me.brody.mazesurvival.mob.custom.MazeBrute.MazeBruteBuilder;
import me.brody.mazesurvival.mob.custom.MazeHoglin.MazeHoglinBuilder;
import me.brody.mazesurvival.mob.custom.Hare.HareBuilder;

public abstract class CustomMob {
    public static CustomMob REVENANT;
    public static CustomMob ARISEN_REVENANT;
    public static CustomMob FORTIFIED_REVENANT;
    public static CustomMob DEATH_KNIGHT;
    public static CustomMob HUNGRY_HORROR;
    public static CustomMob STARVED_HORROR;
    public static CustomMob REMAINS;
    public static CustomMob INFUSED_REMAINS;
    public static CustomMob BONE_CRUSHER;
    public static CustomMob IMMORTAL_LEGIONARY;
    public static CustomMob ROYAL_REMAINS;
    public static CustomMob BOGGED_REMAINS;
    public static CustomMob FORGOTTEN_REMAINS;
    public static CustomMob ARCTIC_REMAINS;
    public static CustomMob ARACHNID;
    public static CustomMob SWIFT_ARACHNID;
    public static CustomMob TURBO_ARACHNID;
    public static CustomMob MAN_EATER;
    public static CustomMob KING_ARACHNID;
    public static CustomMob BOMBER;
    public static CustomMob SUPER_BOMBER;
    public static CustomMob CHARGED_BOMBER;
    public static CustomMob SUPER_CHARGED_BOMBER;
    public static CustomMob OOZE;
    public static CustomMob BIG_OOZE;
    public static CustomMob MEGA_OOZE;
    public static CustomMob ULTRA_OOZE;
    public static CustomMob MAGMA_OOZE;
    public static CustomMob BIG_MAGMA_OOZE;
    public static CustomMob MEGA_MAGMA_OOZE;
    public static CustomMob ULTRA_MAGMA_OOZE;
    public static CustomMob MAZE_BLAZE;
    public static CustomMob FIERY_FURY;
    public static CustomMob INFERNO;
    public static CustomMob MAZE_PIGLIN;
    public static CustomMob BARBARIAN;
    public static CustomMob WRAITH;
    public static CustomMob NETHER_BEAST;
    public static CustomMob GLUTTONOUS_BEAST;
    public static CustomMob MAZE_GHAST;
    public static CustomMob WITHERED_REMAINS;
    public static CustomMob FORSAKEN_REMAINS;
    public static CustomMob MAZE_WITHER;
    public static CustomMob MAZE_BREEZE;
    public static CustomMob HURRICANE;
    public static CustomMob EXECUTIONER;
    public static CustomMob BEHEMOTH;
    public static CustomMob ALCHEMIST;
    public static CustomMob GOLDEN_HARE_STRONGHOLD;
    public static CustomMob GOLDEN_HARE_DESERT;
    public static CustomMob GOLDEN_HARE_SWAMP;
    public static CustomMob GOLDEN_HARE_NETHER;
    public static CustomMob GOLDEN_HARE_DEEP_DARK;
    public static CustomMob CHICKEN_JOCKEY;

    protected String mobName;
    protected double maxHealth;
    protected double movementSpeed;
    protected int powerAmplifier;
    protected MobDropTable dropTable;

    protected CustomMob() {}

    public static void init(Main plugin) {
        SimpleMobDropTable revenantDropTable = new SimpleMobDropTable();
        revenantDropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 1);
        revenantDropTable.addBasicDrop(new ItemStack(Material.COBBLESTONE), 1);
        revenantDropTable.addRareDrop(new ItemStack(Material.LEATHER), 10);
        revenantDropTable.addRareDrop(CustomItem.WORN_HELMET.getItemStack(), 10);
        revenantDropTable.addRareDrop(CustomItem.SPLINTERED_SWORD.getItemStack(), 10);
        REVENANT = new RevenantBuilder(plugin, "Revenant")
                .withDropTable(revenantDropTable)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .build();

        SimpleMobDropTable arisenRevenantDropTable = new SimpleMobDropTable();
        arisenRevenantDropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 2);
        arisenRevenantDropTable.addRareDrop(CustomItem.ARISEN_REVENANT_SWORD.getItemStack(), 10);
        arisenRevenantDropTable.addRareDrop(CustomItem.MISTSTEEL_INGOT.getItemStack(), 10);
        ARISEN_REVENANT = new RevenantBuilder(plugin, "Arisen Revenant")
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(0)
                .withDropTable(arisenRevenantDropTable)
                .withMainHand(new ItemStack(Material.WOODEN_SWORD))
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .build();

        SimpleMobDropTable fortifiedRevenantDropTable = new SimpleMobDropTable();
        fortifiedRevenantDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 2);
        fortifiedRevenantDropTable.addRareDrop(CustomItem.MISTSTEEL_INGOT.getItemStack(), 10);
        fortifiedRevenantDropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.IRON), 10);
        FORTIFIED_REVENANT = new RevenantBuilder(plugin, "Fortified Revenant")
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withDropTable(fortifiedRevenantDropTable)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .withLeggings(new ItemStack(Material.IRON_LEGGINGS))
                .withBoots(new ItemStack(Material.IRON_BOOTS))
                .build();

        SimpleMobDropTable deathKnightDropTable = new SimpleMobDropTable();
        deathKnightDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.TITANIUM), 1);
        deathKnightDropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        deathKnightDropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        deathKnightDropTable.addRareDrop(CustomItem.ADAMANTITE_INGOT.getItemStack(), 10);
        DEATH_KNIGHT = new RevenantBuilder(plugin, "Death Knight")
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withDropTable(deathKnightDropTable)
                .withMainHand(new ItemStack(Material.DIAMOND_SWORD))
                .withHelmet(new ItemStack(Material.NETHERITE_HELMET))
                .withChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE))
                .withLeggings(new ItemStack(Material.NETHERITE_LEGGINGS))
                .withBoots(new ItemStack(Material.NETHERITE_BOOTS))
                .build();

        SimpleMobDropTable hungryHorrorDropTable = new SimpleMobDropTable();
        hungryHorrorDropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 2);
        hungryHorrorDropTable.addBasicDrop(new ItemStack(Material.SAND), 2);
        hungryHorrorDropTable.addBasicDrop(new ItemStack(Material.RED_SAND), 1);
        hungryHorrorDropTable.addRareDrop(new ItemStack(Material.RAW_COPPER), 10);
        hungryHorrorDropTable.addRareDrop(CustomItem.TIN.getItemStack(), 10);
        hungryHorrorDropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.RAW_COPPER), ItemGrade.IRON), 10);
        hungryHorrorDropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.TIN.getItemStack(), ItemGrade.IRON), 10);
        HUNGRY_HORROR = new RevenantBuilder(plugin, "Hungry Horror")
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withDropTable(hungryHorrorDropTable)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withHusk(true)
                .build();

        SimpleMobDropTable starvedHorrorDropTable = new SimpleMobDropTable();
        starvedHorrorDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 1);
        starvedHorrorDropTable.addBasicDrop(new ItemStack(Material.SAND), 3);
        starvedHorrorDropTable.addBasicDrop(new ItemStack(Material.RED_SAND), 1);
        starvedHorrorDropTable.addBasicDrop(new ItemStack(Material.RAW_COPPER), 1);
        starvedHorrorDropTable.addBasicDrop(CustomItem.TIN.getItemStack(), 1);
        starvedHorrorDropTable.addRareDrop(new ItemStack(Material.LAPIS_LAZULI), 10);
        starvedHorrorDropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.IRON), 10);
        STARVED_HORROR = new RevenantBuilder(plugin, "Starved Horror")
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withDropTable(starvedHorrorDropTable)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .withHusk(true)
                .build();

        SimpleMobDropTable remainsDropTable = new SimpleMobDropTable();
        remainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        remainsDropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        remainsDropTable.addRareDrop(CustomItem.WORN_HELMET.getItemStack(), 10);
        remainsDropTable.addRareDrop(CustomItem.SHORT_BOW.getItemStack(), 10);
        REMAINS = new RemainsBuilder(plugin, "Remains")
                .withMovementSpeed(0.3)
                .withDropTable(remainsDropTable)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET)).build();

        SimpleMobDropTable infusedRemainsDropTable = new SimpleMobDropTable();
        infusedRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        infusedRemainsDropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        infusedRemainsDropTable.addRareDrop(CustomItem.SHORT_BOW.getItemStack(), 10);
        ItemStack infusedBow = new ItemStack(Material.BOW);
        infusedBow.addEnchantment(Enchantment.POWER, 1);
        INFUSED_REMAINS = new RemainsBuilder(plugin, "Infused Remains")
                .withMaxHealth(30)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withDropTable(infusedRemainsDropTable)
                .withMainHand(infusedBow)
                .withHelmet(new ItemStack(Material.CHAINMAIL_HELMET))
                .withChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .build();

        SimpleMobDropTable boneCrusherDropTable = new SimpleMobDropTable();
        boneCrusherDropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        BONE_CRUSHER = new RemainsBuilder(plugin, "Bone Crusher")
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withDropTable(boneCrusherDropTable)
                .withMainHand(new ItemStack(Material.IRON_AXE))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .build();

        SimpleMobDropTable immortalLegionaryDropTable = new SimpleMobDropTable();
        immortalLegionaryDropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        immortalLegionaryDropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        immortalLegionaryDropTable.addRareDrop(CustomItem.LEGIONARY_BOW.getItemStack(), 10);
        immortalLegionaryDropTable.addRareDrop(new ItemStack(Material.ECHO_SHARD), 10);
        IMMORTAL_LEGIONARY = new RemainsBuilder(plugin, "Immortal Legionary")
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withDropTable(immortalLegionaryDropTable)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .build();

        ROYAL_REMAINS = new RemainsBuilder(plugin, "Royal Remains")
                .withMaxHealth(80)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.GOLDEN_SWORD))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .withLeggings(new ItemStack(Material.IRON_LEGGINGS))
                .withBoots(new ItemStack(Material.IRON_BOOTS))
                .build();

        SimpleMobDropTable boggedRemainsDropTable = new SimpleMobDropTable();
        boggedRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        boggedRemainsDropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        BOGGED_REMAINS = new RemainsBuilder(plugin, "Bogged Remains")
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withDropTable(boggedRemainsDropTable)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withBogged(true)
                .build();

        SimpleMobDropTable forgottenRemainsDropTable = new SimpleMobDropTable();
        forgottenRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        forgottenRemainsDropTable.addBasicDrop(new ItemStack(Material.COAL), 1);
        FORGOTTEN_REMAINS = new RemainsBuilder(plugin, "Forgotten Remains")
                .withMaxHealth(80)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(5)
                .withDropTable(forgottenRemainsDropTable)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .withLeggings(new ItemStack(Material.LEATHER_LEGGINGS))
                .withBoots(new ItemStack(Material.LEATHER_BOOTS))
                .withBogged(true)
                .build();

        SimpleMobDropTable arcticRemainsDropTable = new SimpleMobDropTable();
        arcticRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        arcticRemainsDropTable.addBasicDrop(new ItemStack(Material.ARROW), 2);
        ItemStack arcticBow = new ItemStack(Material.BOW);
        infusedBow.addEnchantment(Enchantment.POWER, 3);
        ARCTIC_REMAINS = new RemainsBuilder(plugin, "Arctic Remains")
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withDropTable(arcticRemainsDropTable)
                .withMainHand(arcticBow)
                .withHelmet(new ItemStack(Material.CHAINMAIL_HELMET))
                .withStray(true)
                .build();

        SimpleMobDropTable arachnidDropTable = new SimpleMobDropTable();
        arachnidDropTable.addBasicDrop(new ItemStack(Material.STRING), 1);
        arachnidDropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 1);
        ARACHNID = new SimpleCustomMobBuilder(plugin, "Arachnid")
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(0)
                .withDropTable(arachnidDropTable)
                .withEntityType(EntityType.SPIDER)
                .build();

        SimpleMobDropTable swiftArachnidDropTable = new SimpleMobDropTable();
        swiftArachnidDropTable.addBasicDrop(new ItemStack(Material.STRING), 1);
        swiftArachnidDropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 1);
        SWIFT_ARACHNID = new SimpleCustomMobBuilder(plugin, "Swift Arachnid")
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(0)
                .withDropTable(swiftArachnidDropTable)
                .withEntityType(EntityType.SPIDER)
                .build();

        SimpleMobDropTable turboArachnidDropTable = new SimpleMobDropTable();
        turboArachnidDropTable.addBasicDrop(new ItemStack(Material.STRING), 3);
        turboArachnidDropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 2);
        TURBO_ARACHNID = new SimpleCustomMobBuilder(plugin, "Turbo Arachnid")
                .withMaxHealth(50)
                .withMovementSpeed(0.45)
                .withPowerAmplifier(1)
                .withDropTable(turboArachnidDropTable)
                .withEntityType(EntityType.SPIDER)
                .build();

        SimpleMobDropTable manEaterDropTable = new SimpleMobDropTable();
        manEaterDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.STRING), ItemGrade.IRON), 1);
        manEaterDropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 2);
        MAN_EATER = new SimpleCustomMobBuilder(plugin, "Man Eater")
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withDropTable(manEaterDropTable)
                .withEntityType(EntityType.SPIDER)
                .build();

        SimpleMobDropTable kingArachnidDropTable = new SimpleMobDropTable();
        kingArachnidDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.STRING), ItemGrade.GOLD), 2);
        kingArachnidDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.SPIDER_EYE), ItemGrade.GOLD), 1);
        kingArachnidDropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        kingArachnidDropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        KING_ARACHNID = new SimpleCustomMobBuilder(plugin, "King Arachnid")
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(4)
                .withDropTable(kingArachnidDropTable)
                .withEntityType(EntityType.SPIDER)
                .build();

        SimpleMobDropTable bomberDropTable = new SimpleMobDropTable();
        bomberDropTable.addBasicDrop(new ItemStack(Material.GUNPOWDER), 1);
        BOMBER = new BomberBuilder(plugin, "Bomber")
                .withMaxHealth(20)
                .withMovementSpeed(0.25)
                .withDropTable(bomberDropTable)
                .build();

        SimpleMobDropTable superBomberDropTable = new SimpleMobDropTable();
        superBomberDropTable.addBasicDrop(new ItemStack(Material.GUNPOWDER), 2);
        SUPER_BOMBER = new BomberBuilder(plugin, "Super Bomber")
                .withMaxHealth(30)
                .withMovementSpeed(0.4)
                .withDropTable(superBomberDropTable)
                .build();

        SimpleMobDropTable chargedBomberDropTable = new SimpleMobDropTable();
        chargedBomberDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.GUNPOWDER), ItemGrade.IRON), 1);
        CHARGED_BOMBER = new BomberBuilder(plugin, "Charged Bomber")
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withDropTable(chargedBomberDropTable)
                .withPowered(true)
                .build();

        SimpleMobDropTable superChargedBomberDropTable = new SimpleMobDropTable();
        superChargedBomberDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.GUNPOWDER), ItemGrade.GOLD), 1);
        superChargedBomberDropTable.addRareDrop(new ItemStack(Material.DIAMOND), 10);
        SUPER_CHARGED_BOMBER = new BomberBuilder(plugin, "Super Charged Bomber")
                .withMaxHealth(40)
                .withMovementSpeed(0.4)
                .withDropTable(superChargedBomberDropTable)
                .withPowered(true)
                .build();

        SimpleMobDropTable oozeDropTable = new SimpleMobDropTable();
        oozeDropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 1);
        OOZE = new SimpleCustomMobBuilder(plugin, "Ooze")
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withDropTable(oozeDropTable)
                .withEntityType(EntityType.SLIME)
                .build();

        SimpleMobDropTable bigOozeDropTable = new SimpleMobDropTable();
        bigOozeDropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 1);
        BIG_OOZE = new SimpleCustomMobBuilder(plugin, "Big Ooze")
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withDropTable(bigOozeDropTable)
                .withEntityType(EntityType.SLIME)
                .build();

        SimpleMobDropTable megaOozeDropTable = new SimpleMobDropTable();
        megaOozeDropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 2);
        MEGA_OOZE = new SimpleCustomMobBuilder(plugin, "Mega Ooze")
                .withMaxHealth(60)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withDropTable(megaOozeDropTable)
                .withEntityType(EntityType.SLIME)
                .build();

        SimpleMobDropTable ultraOozeDropTable = new SimpleMobDropTable();
        ultraOozeDropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 2);
        ULTRA_OOZE = new SimpleCustomMobBuilder(plugin, "Ultra Ooze")
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withDropTable(ultraOozeDropTable)
                .withEntityType(EntityType.SLIME)
                .build();

        SimpleMobDropTable magmaOozeDropTable = new SimpleMobDropTable();
        magmaOozeDropTable.addBasicDrop(new ItemStack(Material.COAL), 1);
        MAGMA_OOZE = new SimpleCustomMobBuilder(plugin, "Magma Ooze")
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withDropTable(magmaOozeDropTable)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        SimpleMobDropTable bigMagmaOozeDropTable = new SimpleMobDropTable();
        bigMagmaOozeDropTable.addBasicDrop(new ItemStack(Material.COAL), 1);
        BIG_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin, "Big Magma Ooze")
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withDropTable(bigMagmaOozeDropTable)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        SimpleMobDropTable megaMagmaOozeDropTable = new SimpleMobDropTable();
        megaMagmaOozeDropTable.addBasicDrop(new ItemStack(Material.COAL), 2);
        MEGA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin, "Mega Magma Ooze")
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(4)
                .withDropTable(megaMagmaOozeDropTable)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        SimpleMobDropTable ultraMagmaOozeDropTable = new SimpleMobDropTable();
        ultraMagmaOozeDropTable.addBasicDrop(new ItemStack(Material.COAL), 2);
        ULTRA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin, "Ultra Magma Ooze")
                .withMaxHealth(80)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(6)
                .withDropTable(ultraMagmaOozeDropTable)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        SimpleMobDropTable mazeBlazeDropTable = new SimpleMobDropTable();
        mazeBlazeDropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 1);
        MAZE_BLAZE = new SimpleCustomMobBuilder(plugin, "Maze Blaze")
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withDropTable(mazeBlazeDropTable)
                .withEntityType(EntityType.BLAZE)
                .build();

        SimpleMobDropTable fieryFuryDropTable = new SimpleMobDropTable();
        fieryFuryDropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 2);
        FIERY_FURY = new SimpleCustomMobBuilder(plugin, "Fiery Fury")
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .withDropTable(fieryFuryDropTable)
                .withEntityType(EntityType.BLAZE)
                .build();

        SimpleMobDropTable infernoDropTable = new SimpleMobDropTable();
        infernoDropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 2);
        infernoDropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.BLAZE_ROD), ItemGrade.IRON), 10);
        INFERNO = new SimpleCustomMobBuilder(plugin, "Inferno")
                .withMaxHealth(70)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withDropTable(infernoDropTable)
                .withEntityType(EntityType.BLAZE)
                .build();

        SimpleMobDropTable mazePiglinDropTable = new SimpleMobDropTable();
        mazePiglinDropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 1);
        mazePiglinDropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 1);
        mazePiglinDropTable.addBasicDrop(new ItemStack(Material.NETHERRACK), 1);
        mazePiglinDropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_NUGGET.getItemStack(), ItemGrade.IRON), 10);
        MAZE_PIGLIN = new MazePiglinBuilder(plugin, "Maze Piglin")
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withDropTable(mazePiglinDropTable)
                .withMainHand(new ItemStack(Material.GOLDEN_SWORD))
                .build();

        SimpleMobDropTable barbarianDropTable = new SimpleMobDropTable();
        barbarianDropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 1);
        barbarianDropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 2);
        BARBARIAN = new MazeBruteBuilder(plugin, "Barbarian")
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withDropTable(barbarianDropTable)
                .withMainHand(new ItemStack(Material.GOLDEN_AXE))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE))
                .build();

        SimpleMobDropTable wraithDropTable = new SimpleMobDropTable();
        wraithDropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        wraithDropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        wraithDropTable.addRareDrop(CustomItem.WRAITH_HELMET.getItemStack(), 10);
        wraithDropTable.addRareDrop(CustomItem.WRAITH_CHESTPLATE.getItemStack(), 10);
        wraithDropTable.addRareDrop(CustomItem.WRAITH_LEGGINGS.getItemStack(), 10);
        wraithDropTable.addRareDrop(CustomItem.WRAITH_BOOTS.getItemStack(), 10);
        wraithDropTable.addRareDrop(CustomItem.ADAMANTITE_INGOT.getItemStack(), 10);
        WRAITH = new MazeBruteBuilder(plugin, "Wraith")
                .withMaxHealth(100)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(10)
                .withDropTable(wraithDropTable)
                .withMainHand(new ItemStack(Material.NETHERITE_AXE))
                .withHelmet(new ItemStack(Material.NETHERITE_HELMET))
                .withChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE))
                .build();

        SimpleMobDropTable netherBeastDropTable = new SimpleMobDropTable();
        netherBeastDropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 2);
        netherBeastDropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.IRON), 10);
        NETHER_BEAST = new MazeHoglinBuilder(plugin, "Nether Beast")
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withDropTable(netherBeastDropTable)
                .withPowerAmplifier(3)
                .build();

        SimpleMobDropTable gluttonousBeastDropTable = new SimpleMobDropTable();
        gluttonousBeastDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.IRON), 1);
        gluttonousBeastDropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 1);
        gluttonousBeastDropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM), 1);
        GLUTTONOUS_BEAST = new MazeHoglinBuilder(plugin, "Gluttonous Beast")
                .withMaxHealth(100)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .withDropTable(gluttonousBeastDropTable)
                .build();

        SimpleMobDropTable mazeGhastDropTable = new SimpleMobDropTable();
        mazeGhastDropTable.addBasicDrop(new ItemStack(Material.GHAST_TEAR), 2);
        MAZE_GHAST = new SimpleCustomMobBuilder(plugin, "Maze Ghast")
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(3)
                .withDropTable(mazeGhastDropTable)
                .withEntityType(EntityType.GHAST)
                .build();

        SimpleMobDropTable witheredRemainsDropTable = new SimpleMobDropTable();
        witheredRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        witheredRemainsDropTable.addBasicDrop(new ItemStack(Material.COAL), 1);
        WITHERED_REMAINS = new SimpleCustomArmorMobBuilder(plugin, "Withered Remains")
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withDropTable(witheredRemainsDropTable)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();

        SimpleMobDropTable forsakenRemainsDropTable = new SimpleMobDropTable();
        forsakenRemainsDropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        forsakenRemainsDropTable.addBasicDrop(new ItemStack(Material.COAL), 2);
        FORSAKEN_REMAINS = new SimpleCustomArmorMobBuilder(plugin, "Forsaken Remains")
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withDropTable(forsakenRemainsDropTable)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();

        MAZE_WITHER = new SimpleCustomMobBuilder(plugin, "Maze Wither")
                .withMaxHealth(300)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.WITHER)
                .build();

        SimpleMobDropTable mazeBreezeDropTable = new SimpleMobDropTable();
        mazeBreezeDropTable.addBasicDrop(new ItemStack(Material.BREEZE_ROD), 1);
        MAZE_BREEZE = new SimpleCustomMobBuilder(plugin, "Maze Breeze")
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withDropTable(mazeBreezeDropTable)
                .withEntityType(EntityType.BREEZE)
                .build();

        SimpleMobDropTable hurricaneDropTable = new SimpleMobDropTable();
        hurricaneDropTable.addBasicDrop(new ItemStack(Material.BREEZE_ROD), 2);
        HURRICANE = new SimpleCustomMobBuilder(plugin, "Hurricane")
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(5)
                .withDropTable(hurricaneDropTable)
                .withEntityType(EntityType.BREEZE)
                .build();

        SimpleMobDropTable executionerDropTable = new SimpleMobDropTable();
        executionerDropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 1);
        executionerDropTable.addRareDrop(new ItemStack(Material.DIAMOND), 10);
        EXECUTIONER = new SimpleCustomArmorMobBuilder(plugin, "Executioner")
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withDropTable(executionerDropTable)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.VINDICATOR)
                .build();

        SimpleMobDropTable behemothDropTable = new SimpleMobDropTable();
        behemothDropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        behemothDropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        BEHEMOTH = new SimpleCustomMobBuilder(plugin, "Behemoth")
                .withMaxHealth(90)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withDropTable(behemothDropTable)
                .withEntityType(EntityType.RAVAGER)
                .build();

        SimpleMobDropTable alchemistDropTable = new SimpleMobDropTable();
        alchemistDropTable.addBasicDrop(new ItemStack(Material.BROWN_MUSHROOM), 2);
        alchemistDropTable.addBasicDrop(new ItemStack(Material.RED_MUSHROOM), 2);
        alchemistDropTable.addBasicDrop(new ItemStack(Material.GLOWSTONE_DUST), 2);
        alchemistDropTable.addBasicDrop(new ItemStack(Material.SUGAR), 2);
        alchemistDropTable.addRareDrop(CustomItem.SWIFTNESS_II.getItemStack(), 10);
        alchemistDropTable.addRareDrop(CustomItem.STRENGTH.getItemStack(), 10);
        alchemistDropTable.addRareDrop(CustomItem.POISON.getItemStack(), 10);
        alchemistDropTable.addRareDrop(CustomItem.SLOWNESS.getItemStack(), 10);
        alchemistDropTable.addRareDrop(CustomItem.WEAKNESS.getItemStack(), 10);
        ALCHEMIST = new SimpleCustomMobBuilder(plugin, "Alchemist")
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withDropTable(alchemistDropTable)
                .withEntityType(EntityType.WITCH)
                .build();

        SpecialMobDropTable goldenHareStrongholdDropTable = new SpecialMobDropTable(1, 2);
        goldenHareStrongholdDropTable.addDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.COBBLESTONE), ItemGrade.TITANIUM), 1);
        goldenHareStrongholdDropTable.addDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LEATHER, 3), ItemGrade.GOLD), 1);
        goldenHareStrongholdDropTable.addDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.AMETHYST_SHARD), ItemGrade.GOLD), 1);
        goldenHareStrongholdDropTable.addDrop(CustomItem.SWIFTNESS_III_EXTENDED.getItemStack(), 1);
        GOLDEN_HARE_STRONGHOLD = new HareBuilder(plugin, "Golden Hare Stronghold")
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withDropTable(goldenHareStrongholdDropTable)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

        SpecialMobDropTable goldenHareDesertDropTable = new SpecialMobDropTable(1, 2);
        goldenHareDesertDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        goldenHareDesertDropTable.addDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.GOLD), 1);
        goldenHareDesertDropTable.addDrop(CustomItem.SCRIPTING_PAPER.getItemStack(2), 1);
        goldenHareDesertDropTable.addDrop(CustomItem.SWIFTNESS_III_EXTENDED.getItemStack(), 1);
        goldenHareDesertDropTable.addDrop(CustomItem.SWIFTNESS_IV.getItemStack(), 1);
        GOLDEN_HARE_DESERT = new HareBuilder(plugin, "Golden Hare Desert")
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withDropTable(goldenHareDesertDropTable)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

        SpecialMobDropTable goldenHareSwampDropTable = new SpecialMobDropTable(1, 2);
        goldenHareSwampDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        goldenHareSwampDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.ORICHALCUM.getItemStack(), ItemGrade.GOLD), 1);
        goldenHareSwampDropTable.addDrop(CustomItem.SCRIPTING_PAPER.getItemStack(2), 1);
        goldenHareSwampDropTable.addDrop(CustomItem.SWIFTNESS_IV.getItemStack(2), 1);
        GOLDEN_HARE_SWAMP = new HareBuilder(plugin, "Golden Hare Swamp")
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withDropTable(goldenHareSwampDropTable)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

        SpecialMobDropTable goldenHareNetherDropTable = new SpecialMobDropTable(1, 2);
        goldenHareNetherDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        goldenHareNetherDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.MITHRIL.getItemStack(), ItemGrade.GOLD), 1);
        goldenHareNetherDropTable.addDrop(CustomItem.SCRIPTING_TOME.getItemStack(), 1);
        goldenHareNetherDropTable.addDrop(CustomItem.SWIFTNESS_IV_EXTENDED.getItemStack(), 1);
        GOLDEN_HARE_NETHER = new HareBuilder(plugin, "Golden Hare Nether")
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withDropTable(goldenHareNetherDropTable)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

        SpecialMobDropTable goldenHareDeepDarkDropTable = new SpecialMobDropTable(1, 2);
        goldenHareDeepDarkDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.CORRUPTED_DIAMOND.getItemStack(2), ItemGrade.GOLD), 1);
        goldenHareDeepDarkDropTable.addDrop(ItemGradeUtils.createGradedItem(CustomItem.ADAMANTITE_INGOT.getItemStack(), ItemGrade.GOLD), 1);
        goldenHareDeepDarkDropTable.addDrop(CustomItem.SCRIPTING_TOME.getItemStack(2), 1);
        goldenHareDeepDarkDropTable.addDrop(CustomItem.SWIFTNESS_V.getItemStack(), 1);
        GOLDEN_HARE_DEEP_DARK = new HareBuilder(plugin, "Golden Hare Deep Dark")
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withDropTable(goldenHareDeepDarkDropTable)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

        // TODO: Add CHICKEN_JOCKEY
    }

    public static void register() {
        registerCustomMob(REVENANT);
        registerCustomMob(ARISEN_REVENANT);
        registerCustomMob(FORTIFIED_REVENANT);
        registerCustomMob(DEATH_KNIGHT);
        registerCustomMob(HUNGRY_HORROR);
        registerCustomMob(STARVED_HORROR);
        registerCustomMob(REMAINS);
        registerCustomMob(INFUSED_REMAINS);
        registerCustomMob(BONE_CRUSHER);
        registerCustomMob(IMMORTAL_LEGIONARY);
        registerCustomMob(ROYAL_REMAINS);
        registerCustomMob(BOGGED_REMAINS);
        registerCustomMob(FORGOTTEN_REMAINS);
        registerCustomMob(ARCTIC_REMAINS);
        registerCustomMob(ARACHNID);
        registerCustomMob(SWIFT_ARACHNID);
        registerCustomMob(TURBO_ARACHNID);
        registerCustomMob(MAN_EATER);
        registerCustomMob(KING_ARACHNID);
        registerCustomMob(BOMBER);
        registerCustomMob(SUPER_BOMBER);
        registerCustomMob(CHARGED_BOMBER);
        registerCustomMob(SUPER_CHARGED_BOMBER);
        registerCustomMob(OOZE);
        registerCustomMob(BIG_OOZE);
        registerCustomMob(MEGA_OOZE);
        registerCustomMob(ULTRA_OOZE);
        registerCustomMob(MAGMA_OOZE);
        registerCustomMob(BIG_MAGMA_OOZE);
        registerCustomMob(MEGA_MAGMA_OOZE);
        registerCustomMob(ULTRA_MAGMA_OOZE);
        registerCustomMob(MAZE_BLAZE);
        registerCustomMob(FIERY_FURY);
        registerCustomMob(INFERNO);
        registerCustomMob(MAZE_PIGLIN);
        registerCustomMob(BARBARIAN);
        registerCustomMob(WRAITH);
        registerCustomMob(NETHER_BEAST);
        registerCustomMob(GLUTTONOUS_BEAST);
        registerCustomMob(MAZE_GHAST);
        registerCustomMob(WITHERED_REMAINS);
        registerCustomMob(FORSAKEN_REMAINS);
        registerCustomMob(MAZE_WITHER);
        registerCustomMob(MAZE_BREEZE);
        registerCustomMob(HURRICANE);
        registerCustomMob(EXECUTIONER);
        registerCustomMob(BEHEMOTH);
        registerCustomMob(ALCHEMIST);
        registerCustomMob(GOLDEN_HARE_STRONGHOLD);
        registerCustomMob(CHICKEN_JOCKEY);
    }

    private static void registerCustomMob(CustomMob customMob) {
        Registry.CUSTOM_MOB.register(customMob.getMobName().toLowerCase().replace(" ", "_"), customMob);
    }

    public abstract LivingEntity summon(Location location);

    public String getMobName() {
        return mobName;
    }

    public MobDropTable getDropTable() {
        return dropTable;
    }
}
