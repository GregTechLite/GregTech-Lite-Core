package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.unification.material.Materials.Milk
import gregtech.api.unification.material.Materials.Water
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.item.MCPotions
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SterilizedMilk
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BREWING_MILK
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.MILK_POWDER
import net.minecraft.init.Items.GLASS_BOTTLE

internal object MilkProcessing
{
    // @formatter:off

    fun init()
    {
        // Milk -> Butter
        FERMENTING_RECIPES.addRecipe {
            fluidInputs(Milk.getFluid(100))
            fluidOutputs(Butter.getFluid(90))
            EUt(VH[LV])
            duration(1 * MINUTE)
        }

        // Milk -> Sterilized Milk
        FLUID_HEATER_RECIPES.addRecipe {
            circuitMeta(1)
            fluidInputs(Milk.getFluid(250))
            fluidOutputs(SterilizedMilk.getFluid(250))
            EUt(VA[HV])
            duration(1 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Sterilized Milk -> Milk Powder
        CHEMICAL_DEHYDRATOR_RECIPES.addRecipe {
            fluidInputs(SterilizedMilk.getFluid(250))
            outputs(MILK_POWDER.stack())
            EUt(VH[MV])
            duration(6 * SECOND)
            cleanroom()
        }

        // Milk Powder -> Brewing Milk
        CANNER_RECIPES.addRecipe {
            inputs(MILK_POWDER.stack())
            inputs(MCPotions.WATER)
            output(BREWING_MILK)
            EUt(VA[ULV])
            duration(10 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(MILK_POWDER.stack())
            inputs(GLASS_BOTTLE.stack())
            fluidInputs(Water.getFluid(250))
            output(BREWING_MILK)
            EUt(VA[ULV])
            duration(10 * TICK)
        }
    }

    // @formatter:on
}