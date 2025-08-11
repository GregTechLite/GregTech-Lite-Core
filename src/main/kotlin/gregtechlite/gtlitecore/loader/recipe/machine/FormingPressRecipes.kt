package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.CREDIT_COPPER
import gregtech.common.items.MetaItems.CREDIT_GOLD
import gregtech.common.items.MetaItems.CREDIT_NAQUADAH
import gregtech.common.items.MetaItems.CREDIT_NEUTRONIUM
import gregtech.common.items.MetaItems.CREDIT_OSMIUM
import gregtech.common.items.MetaItems.CREDIT_PLATINUM
import gregtech.common.items.MetaItems.CREDIT_SILVER
import gregtech.common.items.MetaItems.SHAPE_EMPTY
import gregtech.common.items.MetaItems.SHAPE_MOLD_CREDIT
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cellulose
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_ADAMANTIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_COSMIC_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_VIBRANIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_SCREW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_TURBINE_BLADE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object FormingPressRecipes
{

    // @formatter:off

    fun init()
    {

        // Shape Mold (Screw)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_SCREW)
            .input(SHAPE_EMPTY)
            .output(SHAPE_MOLD_SCREW)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Shape Mold (Turbine Blade)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_TURBINE_BLADE)
            .input(SHAPE_EMPTY)
            .output(SHAPE_MOLD_TURBINE_BLADE)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Shape Mold (Drill Head)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_DRILL_HEAD)
            .input(SHAPE_EMPTY)
            .output(SHAPE_MOLD_DRILL_HEAD)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Saw)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SAW)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_SAW)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Hard Hammer)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_HARD_HAMMER)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_HARD_HAMMER)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Soft Mallet)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SOFT_MALLET)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_SOFT_MALLET)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Wrench)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_WRENCH)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_WRENCH)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (File)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_FILE)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_FILE)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Crowbar)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_CROWBAR)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_CROWBAR)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Screwdriver)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_SCREWDRIVER)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_SCREWDRIVER)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Mortar)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_MORTAR)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_MORTAR)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Wire Cutter)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_WIRE_CUTTER)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_WIRE_CUTTER)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Knife)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_KNIFE)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_KNIFE)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Casting Mold (Butchery Knife)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_BUTCHERY_KNIFE)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_BUTCHERY_KNIFE)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()


        // Casting Mold (Rolling Pin)
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(CASTING_MOLD_ROLLING_PIN)
            .input(CASTING_MOLD_EMPTY)
            .output(CASTING_MOLD_ROLLING_PIN)
            .EUt(22) // LV
            .duration(6 * SECOND)
            .buildAndRegister()

        // Copper Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Copper)
            .output(CREDIT_COPPER, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Silver Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Silver)
            .output(CREDIT_SILVER, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Gold Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Gold)
            .output(CREDIT_GOLD, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Platinum Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Platinum)
            .output(CREDIT_PLATINUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Osmium Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Osmium)
            .output(CREDIT_OSMIUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Naquadah Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Naquadah)
            .output(CREDIT_NAQUADAH, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Neutronium Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Neutronium)
            .output(CREDIT_NEUTRONIUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Adamantium Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Adamantium)
            .output(CREDIT_ADAMANTIUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Vibranium Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Vibranium)
            .output(CREDIT_VIBRANIUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Cosmic Neutronium Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, CosmicNeutronium)
            .output(CREDIT_COSMIC_NEUTRONIUM, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Infinity Credit
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_CREDIT)
            .input(plate, Infinity)
            .output(CREDIT_INFINITY, 4)
            .EUt(VH[LV].toLong())
            .duration(5 * SECOND)
            .buildAndRegister()

        // Paper
        FORMING_PRESS_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_PLATE)
            .input(dust, Cellulose)
            .outputs(ItemStack(Items.PAPER))
            .EUt(4) // ULV
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}