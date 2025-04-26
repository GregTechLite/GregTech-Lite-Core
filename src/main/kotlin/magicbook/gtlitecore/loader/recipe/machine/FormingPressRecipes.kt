package magicbook.gtlitecore.loader.recipe.machine

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
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_BUTCHERY_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_CROWBAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_FILE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_HARD_HAMMER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_MORTAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_ROLLING_PIN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SAW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SCREWDRIVER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SOFT_MALLET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WIRE_CUTTER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WRENCH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_ADAMANTIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_COSMIC_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_VIBRANIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_BOLT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_DRILL_HEAD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_RING
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD_LONG
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROUND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_SCREW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_TURBINE_BLADE

class FormingPressRecipes
{

    companion object
    {

        fun init()
        {
            // Shape Mold (Rod)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROD)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_ROD)
                .EUt(22) // LV
                .duration(6 * SECOND)
                .buildAndRegister()

            // Shape Mold (Bolt)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BOLT)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_BOLT)
                .EUt(22) // LV
                .duration(6 * SECOND)
                .buildAndRegister()

            // Shape Mold (Round)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROUND)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_ROUND)
                .EUt(22) // LV
                .duration(6 * SECOND)
                .buildAndRegister()

            // Shape Mold (Screw)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_SCREW)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_SCREW)
                .EUt(22) // LV
                .duration(6 * SECOND)
                .buildAndRegister()

            // Shape Mold (Ring)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_RING)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_RING)
                .EUt(22) // LV
                .duration(6 * SECOND)
                .buildAndRegister()

            // Shape Mold (Long Rod)
            FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROD_LONG)
                .input(SHAPE_EMPTY)
                .output(SHAPE_MOLD_ROD_LONG)
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

        }

    }

}