package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plateDouble
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.NUCLEAR_FUELS
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodDepletedDouble
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodDepletedQuadruple
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodDepletedSingle
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodDouble
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodQuadruple
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodSingle
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.FUEL_ROD_EMPTY

@Suppress("MISSING_DEPENDENCY_CLASS")
class NuclearFissionRecipeProducer
{

    companion object
    {

        fun produce()
        {
            // Empty Fuel Rod
            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, Iron)
                .output(FUEL_ROD_EMPTY)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, WroughtIron)
                .output(FUEL_ROD_EMPTY)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, Steel)
                .output(FUEL_ROD_EMPTY, 2)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, VanadiumSteel)
                .output(FUEL_ROD_EMPTY, 4)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, BlueSteel)
                .output(FUEL_ROD_EMPTY, 8)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, HSSG)
                .output(FUEL_ROD_EMPTY, 16)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, HSSE)
                .output(FUEL_ROD_EMPTY, 32)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(12)
                .input(plateDouble, HSSS)
                .output(FUEL_ROD_EMPTY, 64)
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            thoriumProcess() // Th -> Th, U238, Pa
            protactiniumProcess() // Pa -> Pa, U235, U
            uraniumProcess() // U -> U238, U235, Np
            neptuniumProcess() // Np -> Np, Pu241, Pu239
            plutoniumProcess() // Pu239 -> Pu239, Pu241, Am
            americiumProcess() // Am -> Am, Lu, Cm
        }

        private fun thoriumProcess()
        {
            // 1x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodSingle, Thorium)
                .output(fuelRodDepletedSingle, Thorium)
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedSingle, Thorium)
                .output(dust, Steel, 2)
                .output(dust, Thorium, 2)
                .output(dust, Uranium238)
                .output(dust, Protactinium)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 2x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodDouble, Thorium)
                .output(fuelRodDepletedDouble, Thorium)
                .EUt(VA[HV] / 2L)
                .duration(80 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedDouble, Thorium)
                .output(dust, Steel, 4)
                .output(dust, Thorium, 4)
                .output(dust, Uranium238, 2)
                .output(dust, Protactinium, 2)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 4x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodQuadruple, Thorium)
                .output(fuelRodDepletedQuadruple, Thorium)
                .EUt(VA[HV].toLong())
                .duration(320 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedQuadruple, Thorium)
                .output(dust, Steel, 8)
                .output(dust, Thorium, 8)
                .output(dust, Uranium238, 4)
                .output(dust, Protactinium, 4)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

        }

        private fun protactiniumProcess()
        {
            // 1x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodSingle, Protactinium)
                .output(fuelRodDepletedSingle, Protactinium)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedSingle, Protactinium)
                .output(dust, Steel, 2)
                .output(dust, Protactinium, 2)
                .output(dust, Uranium235)
                .output(dust, Uranium)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 2x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodDouble, Protactinium)
                .output(fuelRodDepletedDouble, Protactinium)
                .EUt(VA[HV] / 2L)
                .duration(120 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedDouble, Protactinium)
                .output(dust, Steel, 4)
                .output(dust, Protactinium, 4)
                .output(dust, Uranium235, 2)
                .output(dust, Uranium, 2)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 4x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodQuadruple, Protactinium)
                .output(fuelRodDepletedQuadruple, Protactinium)
                .EUt(VA[HV].toLong())
                .duration(480 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedQuadruple, Protactinium)
                .output(dust, Steel, 8)
                .output(dust, Protactinium, 8)
                .output(dust, Uranium235, 4)
                .output(dust, Uranium, 4)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

        private fun uraniumProcess()
        {
            // 1x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodSingle, Uranium)
                .output(fuelRodDepletedSingle, Uranium)
                .EUt(VA[HV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedSingle, Uranium)
                .output(dust, Steel, 2)
                .output(dust, Uranium238, 2)
                .output(dust, Uranium235)
                .output(dust, Neptunium)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 2x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodDouble, Uranium)
                .output(fuelRodDepletedDouble, Uranium)
                .EUt(VA[EV].toLong())
                .duration(160 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedDouble, Uranium)
                .output(dust, Steel, 4)
                .output(dust, Uranium238, 4)
                .output(dust, Uranium235, 2)
                .output(dust, Neptunium, 2)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 4x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodQuadruple, Uranium)
                .output(fuelRodDepletedQuadruple, Uranium)
                .EUt(VA[IV].toLong())
                .duration(640 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedQuadruple, Uranium)
                .output(dust, Steel, 8)
                .output(dust, Uranium238, 8)
                .output(dust, Uranium235, 4)
                .output(dust, Neptunium, 4)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

        private fun neptuniumProcess()
        {
            // 1x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodSingle, Neptunium)
                .output(fuelRodDepletedSingle, Neptunium)
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedSingle, Neptunium)
                .output(dust, Steel, 2)
                .output(dust, Neptunium, 2)
                .output(dust, Plutonium241)
                .output(dust, Plutonium239)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 2x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodDouble, Neptunium)
                .output(fuelRodDepletedDouble, Neptunium)
                .EUt(VA[IV] / 2L)
                .duration(60 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedDouble, Neptunium)
                .output(dust, Steel, 4)
                .output(dust, Neptunium, 4)
                .output(dust, Plutonium241, 2)
                .output(dust, Plutonium239, 2)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // 4x
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodQuadruple, Neptunium)
                .output(fuelRodDepletedQuadruple, Neptunium)
                .EUt(VA[IV].toLong())
                .duration(240 * SECOND)
                .buildAndRegister()

            THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(fuelRodDepletedQuadruple, Neptunium)
                .output(dust, Steel, 8)
                .output(dust, Neptunium, 8)
                .output(dust, Plutonium241, 4)
                .output(dust, Plutonium239, 4)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

        private fun plutoniumProcess()
        {

        }

        private fun americiumProcess()
        {

        }

    }

}