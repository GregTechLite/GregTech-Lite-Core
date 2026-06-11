package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.BioDiesel
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Soapstone
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.common.items.MetaItems.FERTILIZER
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BeanPhospholipid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CrudeSoybeanOil
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LinoleicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SoybeanOil
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOYBEAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.PLANT_PROTEIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.SOYBEAN_MEAL

internal object SoybeanProcessing
{

    // @formatter:off

    fun init()
    {
        // Soybean -> Crude Soybean Oil + Soybean Meal
        EXTRACTOR_RECIPES.addRecipe {
            input(SOYBEAN)
            outputs(SOYBEAN_MEAL.stack())
            fluidOutputs(CrudeSoybeanOil.getFluid(40))
            EUt(2) // ULV
            duration(1 * SECOND + 12 * TICK)
        }

        // Crude Soybean Oil -> Soybean Oil + Bean Phospholipid
        DISTILLATION_RECIPES.addRecipe {
            fluidInputs(CrudeSoybeanOil.getFluid(1000))
            fluidOutputs(SoybeanOil.getFluid(760))
            fluidOutputs(BeanPhospholipid.getFluid(140))
            fluidOutputs(LinoleicAcid.getFluid(80))
            EUt(VH[HV])
            duration(2 * SECOND)
        }

        // Soybean Meal -> Fertilizer
        FERMENTING_RECIPES.addRecipe {
            inputs(SOYBEAN_MEAL.stack())
            fluidInputs(Water.getFluid(100))
            output(FERTILIZER)
            EUt(VA[LV])
            duration(5 * SECOND)
        }

        // Soybean Meal -> Plant Protein
        COMPRESSOR_RECIPES.addRecipe {
            inputs(SOYBEAN_MEAL.stack())
            outputs(PLANT_PROTEIN.stack())
            EUt(VA[LV])
            duration(6 * SECOND)
        }

        // Crude Soybean Oil -> Glycerol & Bio Diesel
        CHEMICAL_RECIPES.addRecipe {
            input(dustTiny, SodiumHydroxide)
            fluidInputs(CrudeSoybeanOil.getFluid(6000))
            fluidInputs(Methanol.getFluid(1000))
            fluidOutputs(Glycerol.getFluid(1000))
            fluidOutputs(BioDiesel.getFluid(6000))
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        CHEMICAL_RECIPES.addRecipe {
            input(dustTiny, SodiumHydroxide)
            fluidInputs(CrudeSoybeanOil.getFluid(6000))
            fluidInputs(Ethanol.getFluid(1000))
            fluidOutputs(Glycerol.getFluid(1000))
            fluidOutputs(BioDiesel.getFluid(6000))
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            input(dust, SodiumHydroxide)
            fluidInputs(CrudeSoybeanOil.getFluid(54000))
            fluidInputs(Methanol.getFluid(9000))
            fluidOutputs(Glycerol.getFluid(9000))
            fluidOutputs(BioDiesel.getFluid(54000))
            EUt(VA[LV])
            duration(4 * MINUTE + 30 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            input(dust, SodiumHydroxide)
            fluidInputs(CrudeSoybeanOil.getFluid(54000))
            fluidInputs(Ethanol.getFluid(9000))
            fluidOutputs(Glycerol.getFluid(9000))
            fluidOutputs(BioDiesel.getFluid(54000))
            EUt(VA[LV])
            duration(4 * MINUTE + 30 * SECOND)
        }

        // Soybean Oil -> Glycerol & Bio Diesel
        CHEMICAL_RECIPES.addRecipe {
            input(dustTiny, SodiumHydroxide)
            fluidInputs(SoybeanOil.getFluid(6000))
            fluidInputs(Methanol.getFluid(1000))
            fluidOutputs(Glycerol.getFluid(1000))
            fluidOutputs(BioDiesel.getFluid(6000))
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        CHEMICAL_RECIPES.addRecipe {
            input(dustTiny, SodiumHydroxide)
            fluidInputs(SoybeanOil.getFluid(6000))
            fluidInputs(Ethanol.getFluid(1000))
            fluidOutputs(Glycerol.getFluid(1000))
            fluidOutputs(BioDiesel.getFluid(6000))
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            input(dust, SodiumHydroxide)
            fluidInputs(SoybeanOil.getFluid(54000))
            fluidInputs(Methanol.getFluid(9000))
            fluidOutputs(Glycerol.getFluid(9000))
            fluidOutputs(BioDiesel.getFluid(54000))
            EUt(VA[LV])
            duration(4 * MINUTE + 30 * SECOND)
        }

        LARGE_CHEMICAL_RECIPES.addRecipe {
            input(dust, SodiumHydroxide)
            fluidInputs(SoybeanOil.getFluid(54000))
            fluidInputs(Ethanol.getFluid(9000))
            fluidOutputs(Glycerol.getFluid(9000))
            fluidOutputs(BioDiesel.getFluid(54000))
            EUt(VA[LV])
            duration(4 * MINUTE + 30 * SECOND)
        }

        // Soybean Oil -> Lubricant
        for (lubricant in arrayOf(Talc, Soapstone, Redstone))
        {
            BREWING_RECIPES.addRecipe {
                input(dust, lubricant)
                fluidInputs(SoybeanOil.getFluid(1000))
                fluidOutputs(Lubricant.getFluid(1000))
                EUt(4) // ULV
                duration(6 * SECOND + 8 * TICK)
            }
        }
    }

    // @formatter:on

}