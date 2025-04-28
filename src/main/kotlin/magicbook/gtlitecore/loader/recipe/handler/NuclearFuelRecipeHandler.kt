package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems

@Suppress("MISSING_DEPENDENCY_CLASS")
class NuclearFuelRecipeHandler
{

    companion object
    {

        fun init()
        {
            GTLiteOrePrefix.fuelRodSingle.addProcessingHandler(PropertyKey.DUST, this::processFuelRod)
        }

        private fun processFuelRod(fuelRodPrefix: OrePrefix, material: Material, property: DustProperty)
        {
            // fuelRodSingle
            CANNER_RECIPES.recipeBuilder()
                .input(GTLiteMetaItems.FUEL_ROD_EMPTY)
                .input(OrePrefix.dust, material, 4)
                .output(GTLiteOrePrefix.fuelRodSingle, material)
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(GTLiteOrePrefix.fuelRodSingle, material)
                .output(OrePrefix.dust, material)
                .output(GTLiteMetaItems.FUEL_ROD_EMPTY)
                .EUt(7) // ULV
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // fuelRodDouble
            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(GTLiteOrePrefix.fuelRodSingle, material, 2)
                .output(GTLiteOrePrefix.fuelRodDouble, material)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(GTLiteOrePrefix.fuelRodDouble, material)
                .output(GTLiteOrePrefix.fuelRodSingle, material, 2)
                .EUt(7) // ULV
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // fuelRodQuadruple
            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(GTLiteOrePrefix.fuelRodDouble, material, 2)
                .output(GTLiteOrePrefix.fuelRodQuadruple, material)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(GTLiteOrePrefix.fuelRodSingle, material, 4)
                .output(GTLiteOrePrefix.fuelRodQuadruple, material)
                .EUt(7) // ULV
                .duration(10 * SECOND)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(GTLiteOrePrefix.fuelRodQuadruple, material)
                .output(GTLiteOrePrefix.fuelRodSingle, material, 4)
                .EUt(7) // ULV
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(GTLiteOrePrefix.fuelRodQuadruple, material)
                .output(GTLiteOrePrefix.fuelRodDouble, material, 2)
                .EUt(7) // ULV
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}