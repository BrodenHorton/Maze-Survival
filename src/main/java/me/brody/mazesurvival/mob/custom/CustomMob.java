package me.brody.mazesurvival.mob.custom;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.mob.MobDropTable;
import me.brody.mazesurvival.registry.Registry;
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
    public static CustomMob MAZE_BRUTE;
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
    public static CustomMob MAZE_VINDICATOR;
    public static CustomMob BEHEMOTH;
    public static CustomMob ALCHEMIST;
    public static CustomMob GOLDEN_HARE;
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
        REVENANT.dropTable.addBasicDrop(new ItemStack(Material.ROTTEN_FLESH), 1, 0.5);
        REVENANT.dropTable.addBasicDrop(new ItemStack(Material.COBBLESTONE), 1, 0.5);
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

        HUNGRY_HORROR = new RevenantBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withHelmet(new ItemStack(Material.LEATHER_HELMET))
                .withHusk(true)
                .build();

        STARVED_HORROR = new RevenantBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.LEATHER_CHESTPLATE))
                .withHusk(true)
                .build();

        REMAINS = new RemainsBuilder(plugin).withMovementSpeed(0.3).withHelmet(new ItemStack(Material.LEATHER_HELMET)).build();
        REMAINS.dropTable.addBasicDrop(new ItemStack(Material.BONE), 1, 0.5);
        REMAINS.dropTable.addBasicDrop(new ItemStack(Material.ARROW), 1, 0.5);
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

        BONE_CRUSHER = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.IRON_AXE))
                .withHelmet(new ItemStack(Material.IRON_HELMET))
                .withChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE))
                .build();

        IMMORTAL_LEGIONARY = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.IRON_CHESTPLATE))
                .build();

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

        ARCTIC_REMAINS = new RemainsBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                // TODO: Add enchanted bow
                .withHelmet(new ItemStack(Material.CHAINMAIL_HELMET))
                .withStray(true)
                .build();

        ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SPIDER)
                .build();
        ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.STRING), 1, 0.5);
        ARACHNID.dropTable.addBasicDrop(new ItemStack(Material.SPIDER_EYE), 1, 0.5);

        SWIFT_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SPIDER)
                .build();

        TURBO_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.45)
                .withPowerAmplifier(1)
                .withEntityType(EntityType.SPIDER)
                .build();

        MAN_EATER = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.SPIDER)
                .build();

        KING_ARACHNID = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.SPIDER)
                .build();

        BOMBER = new BomberBuilder(plugin).withMaxHealth(20).withMovementSpeed(0.25).build();

        SUPER_BOMBER = new BomberBuilder(plugin).withMaxHealth(30).withMovementSpeed(0.4).build();

        CHARGED_BOMBER = new BomberBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowered(true)
                .build();

        SUPER_CHARGED_BOMBER = new BomberBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.4)
                .withPowered(true)
                .build();

        OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(30)
                .withMovementSpeed(0.25)
                .withPowerAmplifier(0)
                .withEntityType(EntityType.SLIME)
                .build();

        BIG_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.SLIME)
                .build();

        MEGA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.SLIME)
                .build();

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

        BIG_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(40)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(2)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        MEGA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        ULTRA_MAGMA_OOZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(6)
                .withEntityType(EntityType.MAGMA_CUBE)
                .build();

        MAZE_BLAZE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.BLAZE)
                .build();

        FIERY_FURY = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .withEntityType(EntityType.BLAZE)
                .build();

        INFERNO = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(70)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withEntityType(EntityType.BLAZE)
                .build();

        MAZE_PIGLIN = new MazePiglinBuilder(plugin)
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(2)
                .withMainHand(new ItemStack(Material.GOLDEN_SWORD))
                .build();

        MAZE_BRUTE = new MazeBruteBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.GOLDEN_AXE))
                .build();

        BARBARIAN = new MazeBruteBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(6)
                .withMainHand(new ItemStack(Material.GOLDEN_AXE))
                .withHelmet(new ItemStack(Material.GOLDEN_HELMET))
                .withChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE))
                .build();

        WRAITH = new MazeBruteBuilder(plugin)
                .withMaxHealth(100)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(10)
                .withMainHand(new ItemStack(Material.NETHERITE_AXE))
                .withHelmet(new ItemStack(Material.NETHERITE_HELMET))
                .withChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE))
                .build();

        NETHER_BEAST = new MazeHoglinBuilder(plugin)
                .withMaxHealth(60)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .build();

        GLUTTONOUS_BEAST = new MazeHoglinBuilder(plugin)
                .withMaxHealth(100)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(4)
                .build();

        MAZE_GHAST = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.GHAST)
                .build();

        WITHERED_REMAINS = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();

        FORSAKEN_REMAINS = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withMainHand(new ItemStack(Material.IRON_SWORD))
                .withEntityType(EntityType.WITHER_SKELETON)
                .build();

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

        HURRICANE = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(75)
                .withMovementSpeed(0.5)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.BREEZE)
                .build();

        EXECUTIONER = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(50)
                .withMovementSpeed(0.3)
                .withPowerAmplifier(3)
                .withMainHand(new ItemStack(Material.STONE_SWORD))
                .withEntityType(EntityType.PILLAGER)
                .build();

        MAZE_VINDICATOR = new SimpleCustomArmorMobBuilder(plugin)
                .withMaxHealth(80)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withMainHand(new ItemStack(Material.IRON_AXE))
                .withEntityType(EntityType.VINDICATOR)
                .build();

        BEHEMOTH = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(90)
                .withMovementSpeed(0.4)
                .withPowerAmplifier(5)
                .withEntityType(EntityType.RAVAGER)
                .build();

        ALCHEMIST = new SimpleCustomMobBuilder(plugin)
                .withMaxHealth(45)
                .withMovementSpeed(0.35)
                .withPowerAmplifier(3)
                .withEntityType(EntityType.WITCH)
                .build();

        GOLDEN_HARE = new HareBuilder(plugin)
                .withMaxHealth(20)
                .withMovementSpeed(0.7)
                .withRabbitType(Rabbit.Type.GOLD)
                .build();

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
        Registry.CUSTOM_MOB.register("maze_brute", MAZE_BRUTE);
        Registry.CUSTOM_MOB.register("executor", BARBARIAN);
        Registry.CUSTOM_MOB.register("wraith", WRAITH);
        Registry.CUSTOM_MOB.register("nether_beast", NETHER_BEAST);
        Registry.CUSTOM_MOB.register("gluttonous_beast", GLUTTONOUS_BEAST);
        Registry.CUSTOM_MOB.register("maze_ghast", MAZE_GHAST);
        Registry.CUSTOM_MOB.register("withered_remains", WITHERED_REMAINS);
        Registry.CUSTOM_MOB.register("forgotten_remains", FORSAKEN_REMAINS);
        Registry.CUSTOM_MOB.register("maze_wither", MAZE_WITHER);
        Registry.CUSTOM_MOB.register("maze_breeze", MAZE_BREEZE);
        Registry.CUSTOM_MOB.register("hurricane", HURRICANE);
        Registry.CUSTOM_MOB.register("maze_pillager", EXECUTIONER);
        Registry.CUSTOM_MOB.register("maze_vindicator", MAZE_VINDICATOR);
        Registry.CUSTOM_MOB.register("behemoth", BEHEMOTH);
        Registry.CUSTOM_MOB.register("alchemist", ALCHEMIST);
        Registry.CUSTOM_MOB.register("golden_hare", GOLDEN_HARE );
        Registry.CUSTOM_MOB.register("chicken_jockey", CHICKEN_JOCKEY);
    }
}
