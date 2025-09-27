package me.brody.mazesurvival.brewing;

import me.brody.mazesurvival.brewing.persistentdata.BrewingIngredientList;
import me.brody.mazesurvival.brewing.persistentdata.SimpleBrewingIngredientEntry;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BrewingPotion {
    public static final BrewingPotion SWIFTNESS;
    public static final BrewingPotion SWIFTNESS_EXTENDED;
    public static final BrewingPotion SWIFTNESS_II;
    public static final BrewingPotion SWIFTNESS_II_EXTENDED;
    public static final BrewingPotion SWIFTNESS_III;
    public static final BrewingPotion SWIFTNESS_III_EXTENDED;
    public static final BrewingPotion SWIFTNESS_IV;
    public static final BrewingPotion STRENGTH;
    public static final BrewingPotion STRENGTH_EXTENDED;
    public static final BrewingPotion STRENGTH_II;
    public static final BrewingPotion STRENGTH_II_EXTENDED;
    public static final BrewingPotion STRENGTH_III;
    public static final BrewingPotion REGENERATION;
    public static final BrewingPotion REGENERATION_EXTENDED;
    public static final BrewingPotion REGENERATION_II;
    public static final BrewingPotion ABSORPTION;
    public static final BrewingPotion ABSORPTION_II;
    public static final BrewingPotion ABSORPTION_III;
    public static final BrewingPotion SLOWNESS;
    public static final BrewingPotion SLOWNESS_II;
    public static final BrewingPotion SLOWNESS_III;
    public static final BrewingPotion WEAKNESS;
    public static final BrewingPotion WEAKNESS_II;
    public static final BrewingPotion WEAKNESS_III;
    public static final BrewingPotion POISON;
    public static final BrewingPotion POISON_II;
    public static final BrewingPotion POISON_III;

    public static final List<BrewingPotion> VALUES;

    private ItemStack potion;
    private BrewingIngredientList ingredientList;

    static {
        BrewingIngredientList swiftnessIngredients = new BrewingIngredientList();
        swiftnessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 4, ItemGrade.GOLD));
        swiftnessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 3, ItemGrade.IRON));
        SWIFTNESS = new BrewingPotion(CustomItem.SWIFTNESS.getItemStack(), swiftnessIngredients);

        BrewingIngredientList swiftnessExtendedIngredients = new BrewingIngredientList();
        swiftnessExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 4, ItemGrade.GOLD));
        swiftnessExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 2, ItemGrade.GOLD));
        SWIFTNESS_EXTENDED = new BrewingPotion(CustomItem.SWIFTNESS_EXTENDED.getItemStack(), swiftnessExtendedIngredients);

        BrewingIngredientList swiftnessTwoIngredients = new BrewingIngredientList();
        swiftnessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 7, ItemGrade.GOLD));
        swiftnessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 4, ItemGrade.GOLD));
        SWIFTNESS_II = new BrewingPotion(CustomItem.SWIFTNESS_II.getItemStack(), swiftnessTwoIngredients);

        BrewingIngredientList swiftnessTwoExtendedIngredients = new BrewingIngredientList();
        swiftnessTwoExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 2, ItemGrade.TITANIUM));
        swiftnessTwoExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 5, ItemGrade.GOLD));
        SWIFTNESS_II_EXTENDED = new BrewingPotion(CustomItem.SWIFTNESS_II_EXTENDED.getItemStack(), swiftnessTwoExtendedIngredients);

        BrewingIngredientList swiftnessThreeIngredients = new BrewingIngredientList();
        swiftnessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 4, ItemGrade.TITANIUM));
        swiftnessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 2, ItemGrade.TITANIUM));
        swiftnessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.HONEYCOMB.toString(), 5, ItemGrade.IRON));
        SWIFTNESS_III = new BrewingPotion(CustomItem.SWIFTNESS_III.getItemStack(), swiftnessThreeIngredients);

        BrewingIngredientList swiftnessThreeExtendedIngredients = new BrewingIngredientList();
        swiftnessThreeExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 5, ItemGrade.TITANIUM));
        swiftnessThreeExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 3, ItemGrade.TITANIUM));
        swiftnessThreeExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.HONEYCOMB.toString(), 2, ItemGrade.GOLD));
        SWIFTNESS_III_EXTENDED = new BrewingPotion(CustomItem.SWIFTNESS_III_EXTENDED.getItemStack(), swiftnessThreeExtendedIngredients);

        BrewingIngredientList swiftnessFourIngredients = new BrewingIngredientList();
        swiftnessFourIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SUGAR.toString(), 5, ItemGrade.TITANIUM));
        swiftnessFourIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.FEATHER.toString(), 3, ItemGrade.TITANIUM));
        swiftnessFourIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.HONEYCOMB.toString(), 2, ItemGrade.GOLD));
        swiftnessFourIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BREEZE_ROD.toString(), 3, ItemGrade.IRON));
        SWIFTNESS_IV = new BrewingPotion(CustomItem.SWIFTNESS_IV.getItemStack(), swiftnessFourIngredients);

        BrewingIngredientList strengthIngredients = new BrewingIngredientList();
        strengthIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BLAZE_ROD.toString(), 2));
        strengthIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_DYE.toString(), 5, ItemGrade.IRON));
        STRENGTH = new BrewingPotion(CustomItem.STRENGTH.getItemStack(), strengthIngredients);

        BrewingIngredientList strengthExtendedIngredients = new BrewingIngredientList();
        strengthExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BLAZE_ROD.toString(), 4));
        strengthExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_DYE.toString(), 2, ItemGrade.GOLD));
        STRENGTH_EXTENDED = new BrewingPotion(CustomItem.STRENGTH_EXTENDED.getItemStack(), strengthExtendedIngredients);

        BrewingIngredientList strengthTwoIngredients = new BrewingIngredientList();
        strengthTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BLAZE_ROD.toString(), 2, ItemGrade.IRON));
        strengthTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_DYE.toString(), 5, ItemGrade.GOLD));
        strengthTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_SAND.toString(), 3, ItemGrade.IRON));
        STRENGTH_II = new BrewingPotion(CustomItem.STRENGTH_II.getItemStack(), strengthTwoIngredients);

        BrewingIngredientList strengthTwoExtendedIngredients = new BrewingIngredientList();
        strengthTwoExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BLAZE_ROD.toString(), 2, ItemGrade.IRON));
        strengthTwoExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_DYE.toString(), 6, ItemGrade.GOLD));
        strengthTwoExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_SAND.toString(), 1, ItemGrade.GOLD));
        STRENGTH_II_EXTENDED = new BrewingPotion(CustomItem.STRENGTH_II_EXTENDED.getItemStack(), strengthTwoExtendedIngredients);

        BrewingIngredientList strengthThreeIngredients = new BrewingIngredientList();
        strengthThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BLAZE_ROD.toString(), 1, ItemGrade.GOLD));
        strengthThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_DYE.toString(), 2, ItemGrade.TITANIUM));
        strengthThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_SAND.toString(), 1, ItemGrade.TITANIUM));
        STRENGTH_III = new BrewingPotion(CustomItem.STRENGTH_III.getItemStack(), strengthThreeIngredients);

        BrewingIngredientList regenerationIngredients = new BrewingIngredientList();
        regenerationIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GLOWSTONE_DUST.toString(), 3, ItemGrade.IRON));
        regenerationIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GHAST_TEAR.toString(), 1, ItemGrade.IRON));
        regenerationIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.MAGENTA_DYE.toString(), 2, ItemGrade.GOLD));
        REGENERATION = new BrewingPotion(CustomItem.REGENERATION.getItemStack(), regenerationIngredients);

        BrewingIngredientList regenerationExtendedIngredients = new BrewingIngredientList();
        regenerationExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GLOWSTONE_DUST.toString(), 2, ItemGrade.GOLD));
        regenerationExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GHAST_TEAR.toString(), 2, ItemGrade.IRON));
        regenerationExtendedIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.MAGENTA_DYE.toString(), 1, ItemGrade.TITANIUM));
        REGENERATION_EXTENDED = new BrewingPotion(CustomItem.REGENERATION_EXTENDED.getItemStack(), regenerationExtendedIngredients);

        BrewingIngredientList regenerationTwoIngredients = new BrewingIngredientList();
        regenerationTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GLOWSTONE_DUST.toString(), 1, ItemGrade.TITANIUM));
        regenerationTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GHAST_TEAR.toString(), 1, ItemGrade.GOLD));
        regenerationTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.MAGENTA_DYE.toString(), 3, ItemGrade.TITANIUM));
        REGENERATION_II = new BrewingPotion(CustomItem.REGENERATION_II.getItemStack(), regenerationTwoIngredients);

        BrewingIngredientList absorptionIngredients = new BrewingIngredientList();
        absorptionIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BEETROOT.toString(), 2, ItemGrade.GOLD));
        absorptionIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BROWN_MUSHROOM.toString(), 1, ItemGrade.GOLD));
        absorptionIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.YELLOW_DYE.toString(), 3, ItemGrade.GOLD));
        ABSORPTION = new BrewingPotion(CustomItem.ABSORPTION.getItemStack(), absorptionIngredients);

        BrewingIngredientList absorptionTwoIngredients = new BrewingIngredientList();
        absorptionTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BEETROOT.toString(), 1, ItemGrade.TITANIUM));
        absorptionTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BROWN_MUSHROOM.toString(), 3, ItemGrade.GOLD));
        absorptionTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GLOWSTONE_DUST.toString(), 2, ItemGrade.GOLD));
        absorptionTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.YELLOW_DYE.toString(), 3, ItemGrade.TITANIUM));
        ABSORPTION_II = new BrewingPotion(CustomItem.ABSORPTION_II.getItemStack(), absorptionTwoIngredients);

        BrewingIngredientList absorptionThreeIngredients = new BrewingIngredientList();
        absorptionThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BEETROOT.toString(), 3, ItemGrade.TITANIUM));
        absorptionThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.BROWN_MUSHROOM.toString(), 5, ItemGrade.GOLD));
        absorptionThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GLOWSTONE_DUST.toString(), 1, ItemGrade.TITANIUM));
        absorptionThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.YELLOW_DYE.toString(), 5, ItemGrade.TITANIUM));
        ABSORPTION_III = new BrewingPotion(CustomItem.ABSORPTION_III.getItemStack(), absorptionThreeIngredients);

        BrewingIngredientList slownessIngredients = new BrewingIngredientList();
        slownessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SOUL_SAND.toString(), 3, ItemGrade.IRON));
        slownessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SLIME_BALL.toString(), 2, ItemGrade.IRON));
        slownessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 1, ItemGrade.GOLD));
        SLOWNESS = new BrewingPotion(CustomItem.SLOWNESS.getItemStack(), slownessIngredients);

        BrewingIngredientList slownessTwoIngredients = new BrewingIngredientList();
        slownessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SOUL_SAND.toString(), 3, ItemGrade.GOLD));
        slownessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SLIME_BALL.toString(), 2, ItemGrade.GOLD));
        slownessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 2, ItemGrade.GOLD));
        SLOWNESS_II = new BrewingPotion(CustomItem.SLOWNESS_II.getItemStack(), slownessTwoIngredients);

        BrewingIngredientList slownessThreeIngredients = new BrewingIngredientList();
        slownessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SOUL_SAND.toString(), 5, ItemGrade.GOLD));
        slownessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SLIME_BALL.toString(), 3, ItemGrade.GOLD));
        slownessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 3, ItemGrade.GOLD));
        SLOWNESS_III = new BrewingPotion(CustomItem.SLOWNESS_III.getItemStack(), slownessThreeIngredients);

        BrewingIngredientList weaknessIngredients = new BrewingIngredientList();
        weaknessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.ROTTEN_FLESH.toString(), 1, ItemGrade.GOLD));
        weaknessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.NETHER_WART.toString(), 1, ItemGrade.GOLD));
        weaknessIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 1, ItemGrade.GOLD));
        WEAKNESS = new BrewingPotion(CustomItem.WEAKNESS.getItemStack(), weaknessIngredients);

        BrewingIngredientList weaknessTwoIngredients = new BrewingIngredientList();
        weaknessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.ROTTEN_FLESH.toString(), 3, ItemGrade.GOLD));
        weaknessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.NETHER_WART.toString(), 1, ItemGrade.TITANIUM));
        weaknessTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 2, ItemGrade.GOLD));
        WEAKNESS_II = new BrewingPotion(CustomItem.WEAKNESS_II.getItemStack(), weaknessTwoIngredients);

        BrewingIngredientList weaknessThreeIngredients = new BrewingIngredientList();
        weaknessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.ROTTEN_FLESH.toString(), 5, ItemGrade.GOLD));
        weaknessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.NETHER_WART.toString(), 2, ItemGrade.TITANIUM));
        weaknessThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 3, ItemGrade.GOLD));
        WEAKNESS_III = new BrewingPotion(CustomItem.WEAKNESS_III.getItemStack(), weaknessThreeIngredients);

        BrewingIngredientList poisonIngredients = new BrewingIngredientList();
        poisonIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SPIDER_EYE.toString(), 1, ItemGrade.GOLD));
        poisonIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.CACTUS.toString(), 2, ItemGrade.GOLD));
        poisonIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_MUSHROOM.toString(), 2, ItemGrade.GOLD));
        poisonIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 1, ItemGrade.GOLD));
        POISON = new BrewingPotion(CustomItem.POISON.getItemStack(), poisonIngredients);

        BrewingIngredientList poisonTwoIngredients = new BrewingIngredientList();
        poisonTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SPIDER_EYE.toString(), 3, ItemGrade.GOLD));
        poisonTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.CACTUS.toString(), 1, ItemGrade.TITANIUM));
        poisonTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_MUSHROOM.toString(), 5, ItemGrade.GOLD));
        poisonTwoIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 2, ItemGrade.GOLD));
        POISON_II = new BrewingPotion(CustomItem.POISON_II.getItemStack(), poisonTwoIngredients);

        BrewingIngredientList poisonThreeIngredients = new BrewingIngredientList();
        poisonThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.SPIDER_EYE.toString(), 5, ItemGrade.GOLD));
        poisonThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.CACTUS.toString(), 2, ItemGrade.TITANIUM));
        poisonThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.RED_MUSHROOM.toString(), 3, ItemGrade.TITANIUM));
        poisonThreeIngredients.getIngredients().add(new SimpleBrewingIngredientEntry(Material.GUNPOWDER.toString(), 3, ItemGrade.GOLD));
        POISON_III = new BrewingPotion(CustomItem.POISON_III.getItemStack(), poisonThreeIngredients);

        VALUES = List.of(SWIFTNESS, SWIFTNESS_EXTENDED, SWIFTNESS_II, SWIFTNESS_II_EXTENDED, SWIFTNESS_III, SWIFTNESS_III_EXTENDED,
                SWIFTNESS_IV, STRENGTH, STRENGTH_EXTENDED, STRENGTH_II, STRENGTH_II_EXTENDED, STRENGTH_III, REGENERATION, REGENERATION_EXTENDED,
                REGENERATION_II, ABSORPTION, ABSORPTION_II, ABSORPTION_III, SLOWNESS, SLOWNESS_II, SLOWNESS_III, WEAKNESS, WEAKNESS_II,
                WEAKNESS_III, POISON, POISON_II, POISON_III);
    }

    private BrewingPotion(ItemStack potion, BrewingIngredientList ingredients) {
        this.potion = potion;
        this.ingredientList = ingredients;
    }

    public ItemStack getPotion() {
        return potion.clone();
    }

    public BrewingIngredientList getIngredientList() {
        return ingredientList;
    }
}
