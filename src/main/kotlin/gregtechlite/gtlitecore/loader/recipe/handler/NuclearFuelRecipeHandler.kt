package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.DustProperty
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.api.t
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRod
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodEnriched
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensity
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FUEL_ROD_EMPTY

@Suppress("unused")
object NuclearFuelRecipeHandler
{

    // @formatter:off

    fun init()
    {
        fuelRod.addProcessingHandler(PropertyKey.DUST, ::processFuelRod)
    }

    private fun processFuelRod(fuelRodPrefix: OrePrefix, material: Material, property: DustProperty)
    {
        // Common Fuel Rod
        CANNER_RECIPES.addRecipe {
            input(FUEL_ROD_EMPTY)
            input(dust, material, 4)
            output(fuelRod, material)
            EUt(VA[LV])
            duration(20.s)
        }

        PACKER_RECIPES.addRecipe {
            circuitMeta(1)
            input(fuelRod, material)
            output(dust, material)
            output(FUEL_ROD_EMPTY)
            EUt(7) // ULV
            duration(7.s + 10.t)
        }

        // Enriched Fuel Rod
        PACKER_RECIPES.addRecipe {
            circuitMeta(2)
            input(fuelRod, material, 2)
            output(fuelRodEnriched, material)
            EUt(7) // ULV
            duration(5.s)
        }

        PACKER_RECIPES.addRecipe {
            circuitMeta(1)
            input(fuelRodEnriched, material)
            output(fuelRod, material, 2)
            EUt(7) // ULV
            duration(7.s + 10.t)
        }

        // High Density Fuel Rod
        PACKER_RECIPES.addRecipe {
            circuitMeta(2)
            input(fuelRodEnriched, material, 2)
            output(fuelRodHighDensity, material)
            EUt(7) // ULV
            duration(5.s)
        }

        PACKER_RECIPES.addRecipe {
            circuitMeta(4)
            input(fuelRod, material, 4)
            output(fuelRodHighDensity, material)
            EUt(7) // ULV
            duration(10.s)
        }

        PACKER_RECIPES.addRecipe {
            circuitMeta(3)
            input(fuelRodHighDensity, material)
            output(fuelRod, material, 4)
            EUt(7) // ULV
            duration(12.s + 10.t)
        }

        PACKER_RECIPES.addRecipe {
            circuitMeta(5)
            input(fuelRodHighDensity, material)
            output(fuelRodEnriched, material, 2)
            EUt(7) // ULV
            duration(7.s + 10.t)
        }
    }

    // @formatter:on

}