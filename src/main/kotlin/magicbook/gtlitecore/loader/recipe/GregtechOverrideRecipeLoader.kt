package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class GregtechOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {

            // Let ingotIron can smelt to ingotWroughtIron just like nuggetIron -> nuggetWroughtIron.
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Iron),
                OreDictUnifier.get(ingot, WroughtIron))

            // Down-tier Clay electrolysis to provide another choice of aluminium,
            // cryolite and ruby juice and this is three methods to get aluminium at LV stage.
            ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Clay, 13)
                .output(dust, Sodium, 2)
                .output(dust, Lithium)
                .output(dust, Aluminium, 2)
                .output(dust, Silicon, 2)
                .fluidOutputs(Water.getFluid(6000))
                .EUt(VA[LV].toLong())
                .duration(9 * SECOND + 2 * TICK)
                .buildAndRegister()

            // Reduce time spent of water electrolysis from 75s to 15s.
            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
                Water.getFluid(1000))

            // H2O -> 2H + O
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // TODO circuitMeta 1 and 2 for salt and rock salt with water electrolysis.

            // Down-tier Cleanroom Plascrete because Mining Drone Airport is at LV stage,
            // player cannot get MV assembler in LV stage.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Steel),
                    OreDictUnifier.get(plate, Polyethylene, 6)),
                arrayOf(Concrete.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(frameGt, Steel)
                .input(plate, Polyethylene, 6)
                .fluidInputs(Concrete.getFluid(L))
                .outputs(MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.PLASCRETE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}