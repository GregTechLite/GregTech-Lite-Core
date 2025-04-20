package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lignite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class AcetyleneChain
{

    companion object
    {

        fun init()
        {
            // CaO + 3C -> CaC2 + CO
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .input(dust, Carbon, 3)
                .output(dust, CalciumCarbide, 3)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .inputs(ItemStack(Items.COAL, 3, 0))
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .inputs(ItemStack(Items.COAL, 3, 1))
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .input(gem, Lignite, 3)
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .input(dust, Charcoal, 3)
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .input(dust, Coal, 3)
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            ROASTER_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 2)
                .input(dust, Lignite, 3)
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Ash, 3, 2000, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            // CaC2 + 2H2O -> Ca(OH)2 + C2H2
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CalciumCarbide, 3)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, CalciumHydroxide, 5)
                .fluidOutputs(Acetylene.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Ca(OH)2 + CO2 -> CaCO3 + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CalciumHydroxide, 5)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, Calcite, 5)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

        }

    }

}