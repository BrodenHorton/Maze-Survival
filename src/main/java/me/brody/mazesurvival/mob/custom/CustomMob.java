package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.mob.MobDropTable;
import me.brody.mazesurvival.registry.Registry;
import me.brody.mazesurvival.utils.ItemGradeUtils;
import org.bukkit.Location;
import org.bukkit.Material;
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

    protected double maxHealth;
    protected double movementSpeed;
    protected int powerAmplifier;
    protected MobDropTable dropTable;

    protected CustomMob() {
        dropTable = new MobDropTable();
    }

    public static void init(Main plugin) {
        REVENANT = new RevenantBuilder(plugin).withHelmet(new ItemStack(Material.LEATHER_HELMET)).build();
        REVENANT.dropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 1);
        REVENANT.dropTable.addBasicDrop(new ItemStack(Material.COBBLESTONE), 1);
        REVENANT.dropTable.addRareDrop(new ItemStack(Material.LEATHER), 10);
        REVENANT.dropTable.addRareDrop(CustomItem.WORN_HELMET.getItemStack(), 10);
        REVENANT.dropTable.addRareDrop(CustomItem.SPLINTERED_SWORD.getItemStack(), 10);

        ARISEN_REVENANT = new RevenantBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(0)
                .withMainHand(new ItemStack(Material.WOODEN_SWORD))
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .build();
        ARISEN_REVENANT.dropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 2);
        ARISEN_REVENANT.dropTable.addRareDrop(CustomItem.ARISEN_REVENANT_SWORD.getItemStack(), 10);
        ARISEN_REVENANT.dropTable.addRareDrop(CustomItem.MISTSTEEL_INGOT.getItemStack(), 10);

        FORTIFIED_REVENANT = new RevenantBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .withLeggings(new ItemStack(Material.IRON_LEGGINGS))
                .withBoots(new ItemStack(Material.IRON_BOOTS))
                .build();
        FORTIFIED_REVENANT.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 2);
        FORTIFIED_REVENANT.dropTable.addRareDrop(CustomItem.MISTSTEEL_INGOT.getItemStack(), 10);
        FORTIFIED_REVENANT.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(), ItemGrade.IRON), 10);

        DEATH_KNIGHT = new RevenantBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withMainHand(new ItemStack(Material.DIAMOND_SWORD))
                .withHelmet(new ItemStack(Material.NETHERITE_HELMET))
                .withChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE))
                .withLeggings(new ItemStack(Material.NETHERITE_LEGGINGS))
                .withBoots(new ItemStack(Material.NETHERITE_BOOTS))
                .build();
        DEATH_KNIGHT.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.TITANIUM), 1);
        DEATH_KNIGHT.dropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        DEATH_KNIGHT.dropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        DEATH_KNIGHT.dropTable.addRareDrop(CustomItem.ADAMANTITE_INGOT.getItemStack(), 10);

        HUNGRY_HORROR = new RevenantBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withHusk(true)
                .build();
        HUNGRY_HORROR.dropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 2);
        HUNGRY_HORROR.dropTable.addBasicDrop(new ItemStack(Material.SAND), 2);
        HUNGRY_HORROR.dropTable.addBasicDrop(new ItemStack(Material.RED_SAND), 1);
        HUNGRY_HORROR.dropTable.addRareDrop(new ItemStack(Material.RAW_COPPER), 10);
        HUNGRY_HORROR.dropTable.addRareDrop(CustomItem.TIN.getItemStack(), 10);
        HUNGRY_HORROR.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.RAW_COPPER), ItemGrade.IRON), 10);
        HUNGRY_HORROR.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.TIN.getItemStack(), ItemGrade.IRON), 10);

        STARVED_HORROR = new RevenantBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .withHusk(true)
                .build();
        STARVED_HORROR.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 1);
        STARVED_HORROR.dropTable.addBasicDrop(new ItemStack(Material.SAND), 3);
        STARVED_HORROR.dropTable.addBasicDrop(new ItemStack(Material.RED_SAND), 1);
        STARVED_HORROR.dropTable.addBasicDrop(new ItemStack(Material.RAW_COPPER), 1);
        STARVED_HORROR.dropTable.addBasicDrop(CustomItem.TIN.getItemStack(), 1);
        STARVED_HORROR.dropTable.addRareDrop(new ItemStack(Material.LAPIS_LAZULI), 10);
        STARVED_HORROR.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.IRON), 10);

        REMAINS = new RemainsBuilder(plugin).withMovementSpeed(0.3).withHelmet(new ItemStack(Material.LEATHER_HELMET)).build();
        REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        REMAINS.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        REMAINS.dropTable.addRareDrop(CustomItem.WORN_HELMET.getItemStack(), 10);
        REMAINS.dropTable.addRareDrop(CustomItem.SHORT_BOW.getItemStack(), 10);

        INFUSED_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                // TODO: Add enchanted bow
                .withHelmet(new ItemStack(Material.CHAINMAIL_HELMET))
                .withChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .build();
        INFUSED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        INFUSED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        INFUSED_REMAINS.dropTable.addRareDrop(CustomItem.SHORT_BOW.getItemStack(), 10);

        BONE_CRUSHER = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.IRON_AXE))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .build();
        BONE_CRUSHER.dropTable.addBasicDrop(new ItemStack(Material.BONE), 2);

        IMMORTAL_LEGIONARY = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .build();
        IMMORTAL_LEGIONARY.dropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        IMMORTAL_LEGIONARY.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);
        IMMORTAL_LEGIONARY.dropTable.addRareDrop(CustomItem.LEGIONARY_BOW.getItemStack(), 10);
        IMMORTAL_LEGIONARY.dropTable.addRareDrop(new ItemStack(Material.ECHO_SHARD), 10);

        ROYAL_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.GOLDEN_SWORD))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .withLeggings(new ItemStack(Material.IRON_LEGGINGS))
                .withBoots(new ItemStack(Material.IRON_BOOTS))
                .build();

        BOGGED_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withBogged(true)
                .build();
        BOGGED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        BOGGED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 1);

        FORGOTTEN_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(5)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .withLeggings(new ItemStack(Material.LEATHER_LEGGINGS))
                .withBoots(new ItemStack(Material.LEATHER_BOOTS))
                .withBogged(true)
                .build();
        FORGOTTEN_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        FORGOTTEN_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.COAL), 1);

        ARCTIC_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                // TODO: Add enchanted bow
                .withHelmet(new ItemStack(Material.CHAINMAIL_HELMET))
                .withStray(true)
                .build();
        ARCTIC_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1);
        ARCTIC_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 2);

        ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SPIDER)
                .build();
        ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.STRING), 1);
        ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 1);

        SWIFT_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SPIDER)
                .build();
        SWIFT_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.STRING), 1);
        SWIFT_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 1);

        TURBO_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.45)
                .withPowerAmplifier(1)
                .withEntityType(EntityType.SPIDER)
                .build();
        TURBO_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.STRING), 3);
        TURBO_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 2);

        MAN_EATER = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.SPIDER)
                .build();
        MAN_EATER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.STRING), ItemGrade.IRON), 1);
        MAN_EATER.dropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 2);

        KING_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.SPIDER)
                .build();
        KING_ARACHNID.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.STRING), ItemGrade.GOLD), 2);
        KING_ARACHNID.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.SPIDER_EYE), ItemGrade.GOLD), 1);
        KING_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        KING_ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);

        BOMBER = new BomberBuilder(plugin).withMaxHealth(20).withMovementSpeed(0.25).build();
        BOMBER.dropTable.addBasicDrop(new ItemStack(Material.GUNPOWDER), 1);

        SUPER_BOMBER = new BomberBuilder(plugin).withMaxHealth(30).withMovementSpeed(0.4).build();
        SUPER_BOMBER.dropTable.addBasicDrop(new ItemStack(Material.GUNPOWDER), 2);

        CHARGED_BOMBER = new BomberBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowered(true)
                .build();
        CHARGED_BOMBER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.GUNPOWDER), ItemGrade.IRON), 1);

        SUPER_CHARGED_BOMBER = new BomberBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.4)
                .withPowered(true)
                .build();
        SUPER_CHARGED_BOMBER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.GUNPOWDER), ItemGrade.GOLD), 1);
        SUPER_CHARGED_BOMBER.dropTable.addRareDrop(new ItemStack(Material.DIAMOND), 10);

        OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SLIME)
                .build();
        OOZE.dropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 1);

        BIG_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.SLIME)
                .build();
        BIG_OOZE.dropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 1);

        MEGA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.SLIME)
                .build();
        MEGA_OOZE.dropTable.addBasicDrop(new ItemStack(Material.SLIME_BALL), 2);

        ULTRA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.SLIME)
                .build();

        MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();
        MAGMA_OOZE.dropTable.addBasicDrop(new ItemStack(Material.COAL), 1);

        BIG_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();
        BIG_MAGMA_OOZE.dropTable.addBasicDrop(new ItemStack(Material.COAL), 1);

        MEGA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();
        MEGA_MAGMA_OOZE.dropTable.addBasicDrop(new ItemStack(Material.COAL), 2);

        ULTRA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(6)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();
        ULTRA_MAGMA_OOZE.dropTable.addBasicDrop(new ItemStack(Material.COAL), 2);

        MAZE_BLAZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.BLAZE)
                .build();
        MAZE_BLAZE.dropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 1);

        FIERY_FURY = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.BLAZE)
                .build();
        FIERY_FURY.dropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 2);

        INFERNO = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(70)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withEntityType(EntityType.BLAZE)
                .build();
        INFERNO.dropTable.addBasicDrop(new ItemStack(Material.BLAZE_ROD), 2);
        INFERNO.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.BLAZE_ROD), ItemGrade.IRON), 10);

        MAZE_PIGLIN = new MazePiglinBuilder(plugin)
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.GOLDEN_SWORD))
                .build();
        MAZE_PIGLIN.dropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 1);
        MAZE_PIGLIN.dropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 1);
        MAZE_PIGLIN.dropTable.addBasicDrop(new ItemStack(Material.NETHERRACK), 1);
        MAZE_PIGLIN.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_NUGGET.getItemStack(), ItemGrade.IRON), 10);

        BARBARIAN = new MazeBruteBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withMainHand(new ItemStack(Material.GOLDEN_AXE))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE))
                .build();
        BARBARIAN.dropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 1);
        BARBARIAN.dropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 2);

        WRAITH = new MazeBruteBuilder(plugin)
                .withMaxHealth(100)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(10)
                .withMainHand(new ItemStack(Material.NETHERITE_AXE))
                .withHelmet(new ItemStack(Material.NETHERITE_HELMET))
                .withChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE))
                .build();
        WRAITH.dropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        WRAITH.dropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);
        WRAITH.dropTable.addRareDrop(CustomItem.WRAITH_HELMET.getItemStack(), 10);
        WRAITH.dropTable.addRareDrop(CustomItem.WRAITH_CHESTPLATE.getItemStack(), 10);
        WRAITH.dropTable.addRareDrop(CustomItem.WRAITH_LEGGINGS.getItemStack(), 10);
        WRAITH.dropTable.addRareDrop(CustomItem.WRAITH_BOOTS.getItemStack(), 10);
        WRAITH.dropTable.addRareDrop(CustomItem.ADAMANTITE_INGOT.getItemStack(), 10);

        NETHER_BEAST = new MazeHoglinBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .build();
        NETHER_BEAST.dropTable.addBasicDrop(new ItemStack(Material.PORKCHOP), 2);
        NETHER_BEAST.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.IRON), 10);

        GLUTTONOUS_BEAST = new MazeHoglinBuilder(plugin)
                .withMaxHealth(100)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .build();
        GLUTTONOUS_BEAST.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.IRON), 1);
        GLUTTONOUS_BEAST.dropTable.addBasicDrop(CustomItem.SUN_GOLD_NUGGET.getItemStack(), 1);
        GLUTTONOUS_BEAST.dropTable.addRareDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.PORKCHOP), ItemGrade.TITANIUM), 1);

        MAZE_GHAST = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.GHAST)
                .build();
        MAZE_GHAST.dropTable.addBasicDrop(new ItemStack(Material.GHAST_TEAR), 2);

        WITHERED_REMAINS = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();
        WITHERED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        WITHERED_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.COAL), 1);

        FORSAKEN_REMAINS = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();
        FORSAKEN_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 2);
        FORSAKEN_REMAINS.dropTable.addBasicDrop(new ItemStack(Material.COAL), 2);

        MAZE_WITHER = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(300)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.WITHER)
                .build();

        MAZE_BREEZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.BREEZE)
                .build();
        MAZE_BREEZE.dropTable.addBasicDrop(new ItemStack(Material.BREEZE_ROD), 1);

        HURRICANE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.BREEZE)
                .build();
        HURRICANE.dropTable.addBasicDrop(new ItemStack(Material.BREEZE_ROD), 2);

        EXECUTIONER = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.VINDICATOR)
                .build();
        EXECUTIONER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.ROTTEN_FLESH), ItemGrade.GOLD), 1);
        EXECUTIONER.dropTable.addRareDrop(new ItemStack(Material.DIAMOND), 10);

        BEHEMOTH = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(90)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.RAVAGER)
                .build();
        BEHEMOTH.dropTable.addBasicDrop(new ItemStack(Material.ECHO_SHARD), 1);
        BEHEMOTH.dropTable.addBasicDrop(new ItemStack(Material.DIAMOND), 1);

        ALCHEMIST = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.WITCH)
                .build();
        ALCHEMIST.dropTable.addBasicDrop(new ItemStack(Material.BROWN_MUSHROOM), 2);
        ALCHEMIST.dropTable.addBasicDrop(new ItemStack(Material.RED_MUSHROOM), 2);
        ALCHEMIST.dropTable.addBasicDrop(new ItemStack(Material.GLOWSTONE_DUST), 2);
        ALCHEMIST.dropTable.addBasicDrop(new ItemStack(Material.SUGAR), 2);
        ALCHEMIST.dropTable.addRareDrop(CustomItem.SWIFTNESS_II.getItemStack(), 10);
        ALCHEMIST.dropTable.addRareDrop(CustomItem.STRENGTH.getItemStack(), 10);
        ALCHEMIST.dropTable.addRareDrop(CustomItem.POISON.getItemStack(), 10);
        ALCHEMIST.dropTable.addRareDrop(CustomItem.SLOWNESS.getItemStack(), 10);
        ALCHEMIST.dropTable.addRareDrop(CustomItem.WEAKNESS.getItemStack(), 10);

        GOLDEN_HARE_STRONGHOLD = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();
        GOLDEN_HARE_STRONGHOLD.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.COBBLESTONE), ItemGrade.TITANIUM), 1);
        GOLDEN_HARE_STRONGHOLD.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LEATHER, 3), ItemGrade.GOLD), 1);
        GOLDEN_HARE_STRONGHOLD.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.AMETHYST_SHARD), ItemGrade.GOLD), 1);
        GOLDEN_HARE_STRONGHOLD.dropTable.addBasicDrop(CustomItem.SWIFTNESS_III_EXTENDED.getItemStack(), 1);

        GOLDEN_HARE_DESERT = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();
        GOLDEN_HARE_DESERT.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.BRONZE_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        GOLDEN_HARE_DESERT.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(new ItemStack(Material.LAPIS_LAZULI), ItemGrade.GOLD), 1);
        GOLDEN_HARE_DESERT.dropTable.addBasicDrop(CustomItem.SCRIPTING_PAPER.getItemStack(2), 1);
        GOLDEN_HARE_DESERT.dropTable.addBasicDrop(CustomItem.SWIFTNESS_III_EXTENDED.getItemStack(), 1);
        GOLDEN_HARE_DESERT.dropTable.addBasicDrop(CustomItem.SWIFTNESS_IV.getItemStack(), 1);

        GOLDEN_HARE_SWAMP = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();
        GOLDEN_HARE_SWAMP.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.MISTSTEEL_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        GOLDEN_HARE_SWAMP.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.ORICHALCUM.getItemStack(), ItemGrade.GOLD), 1);
        GOLDEN_HARE_SWAMP.dropTable.addBasicDrop(CustomItem.SCRIPTING_PAPER.getItemStack(2), 1);
        GOLDEN_HARE_SWAMP.dropTable.addBasicDrop(CustomItem.SWIFTNESS_IV.getItemStack(2), 1);

        GOLDEN_HARE_NETHER = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();
        GOLDEN_HARE_NETHER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.SUN_GOLD_INGOT.getItemStack(2), ItemGrade.GOLD), 1);
        GOLDEN_HARE_NETHER.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.MITHRIL.getItemStack(), ItemGrade.GOLD), 1);
        GOLDEN_HARE_NETHER.dropTable.addBasicDrop(CustomItem.SCRIPTING_TOME.getItemStack(), 1);
        GOLDEN_HARE_NETHER.dropTable.addBasicDrop(CustomItem.SWIFTNESS_IV_EXTENDED.getItemStack(), 1);

        GOLDEN_HARE_DEEP_DARK = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();
        GOLDEN_HARE_DEEP_DARK.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.CORRUPTED_DIAMOND.getItemStack(2), ItemGrade.GOLD), 1);
        GOLDEN_HARE_DEEP_DARK.dropTable.addBasicDrop(ItemGradeUtils.createGradedItem(CustomItem.ADAMANTITE_INGOT.getItemStack(), ItemGrade.GOLD), 1);
        GOLDEN_HARE_DEEP_DARK.dropTable.addBasicDrop(CustomItem.SCRIPTING_TOME.getItemStack(2), 1);
        GOLDEN_HARE_DEEP_DARK.dropTable.addBasicDrop(CustomItem.SWIFTNESS_V.getItemStack(), 1);

        // TODO: Add CHICKEN_JOCKEY
    }

    public abstract LivingEntity summon(Location location);

    public static void register() {
        Registry.CUSTOM_MOB.register("revenant", REVENANT);
        Registry.CUSTOM_MOB.register("arisen_revenant", ARISEN_REVENANT);
        Registry.CUSTOM_MOB.register("fortified_revenant", FORTIFIED_REVENANT);
        Registry.CUSTOM_MOB.register("death_knight", DEATH_KNIGHT);
        Registry.CUSTOM_MOB.register("hungry_horror", HUNGRY_HORROR);
        Registry.CUSTOM_MOB.register("starved_horror", STARVED_HORROR);
        Registry.CUSTOM_MOB.register("remains", REMAINS);
        Registry.CUSTOM_MOB.register("infused_remains", INFUSED_REMAINS);
        Registry.CUSTOM_MOB.register("bone_crusher", BONE_CRUSHER);
        Registry.CUSTOM_MOB.register("immortal_legionary", IMMORTAL_LEGIONARY);
        Registry.CUSTOM_MOB.register("royal_remains", ROYAL_REMAINS);
        Registry.CUSTOM_MOB.register("bogged_remains", BOGGED_REMAINS);
        Registry.CUSTOM_MOB.register("forgotten_remains", FORGOTTEN_REMAINS);
        Registry.CUSTOM_MOB.register("arctic_remains", ARCTIC_REMAINS);
        Registry.CUSTOM_MOB.register("arachnid", ARACHNID);
        Registry.CUSTOM_MOB.register("swift_arachnid", SWIFT_ARACHNID);
        Registry.CUSTOM_MOB.register("turbo_arachnid", TURBO_ARACHNID);
        Registry.CUSTOM_MOB.register("man_eater", MAN_EATER);
        Registry.CUSTOM_MOB.register("king_arachnid", KING_ARACHNID);
        Registry.CUSTOM_MOB.register("bomber", BOMBER);
        Registry.CUSTOM_MOB.register("super_bomber", SUPER_BOMBER);
        Registry.CUSTOM_MOB.register("charged_bomber", CHARGED_BOMBER);
        Registry.CUSTOM_MOB.register("super_charged_bomber", SUPER_CHARGED_BOMBER);
        Registry.CUSTOM_MOB.register("ooze", OOZE);
        Registry.CUSTOM_MOB.register("big_ooze", BIG_OOZE);
        Registry.CUSTOM_MOB.register("mega_ooze", MEGA_OOZE);
        Registry.CUSTOM_MOB.register("ultra_ooze", ULTRA_OOZE);
        Registry.CUSTOM_MOB.register("magma_ooze", MAGMA_OOZE);
        Registry.CUSTOM_MOB.register("big_magma_ooze", BIG_MAGMA_OOZE);
        Registry.CUSTOM_MOB.register("mega_magma_ooze", MEGA_MAGMA_OOZE);
        Registry.CUSTOM_MOB.register("ultra_magma_ooze", ULTRA_MAGMA_OOZE);
        Registry.CUSTOM_MOB.register("maze_blaze", MAZE_BLAZE);
        Registry.CUSTOM_MOB.register("fiery_fury", FIERY_FURY);
        Registry.CUSTOM_MOB.register("inferno", INFERNO);
        Registry.CUSTOM_MOB.register("maze_piglin", MAZE_PIGLIN);
        Registry.CUSTOM_MOB.register("barbarian", BARBARIAN);
        Registry.CUSTOM_MOB.register("wraith", WRAITH);
        Registry.CUSTOM_MOB.register("nether_beast", NETHER_BEAST);
        Registry.CUSTOM_MOB.register("gluttonous_beast", GLUTTONOUS_BEAST);
        Registry.CUSTOM_MOB.register("maze_ghast", MAZE_GHAST);
        Registry.CUSTOM_MOB.register("withered_remains", WITHERED_REMAINS);
        Registry.CUSTOM_MOB.register("forgotten_remains", FORSAKEN_REMAINS);
        Registry.CUSTOM_MOB.register("maze_wither", MAZE_WITHER);
        Registry.CUSTOM_MOB.register("maze_breeze", MAZE_BREEZE);
        Registry.CUSTOM_MOB.register("hurricane", HURRICANE);
        Registry.CUSTOM_MOB.register("executioner", EXECUTIONER);
        Registry.CUSTOM_MOB.register("behemoth", BEHEMOTH);
        Registry.CUSTOM_MOB.register("alchemist", ALCHEMIST);
        Registry.CUSTOM_MOB.register("golden_hare", GOLDEN_HARE_STRONGHOLD);
        Registry.CUSTOM_MOB.register("chicken_jockey", CHICKEN_JOCKEY);
    }
}
