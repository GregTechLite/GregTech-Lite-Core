package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EggLiquid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EggWhite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EggYolk
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fat
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lecithin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhospholipidEthanolEmulsion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SugarFreeEggWhite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Yeast
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.EGGSHELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.WET_EGG_WHITE_CAKE
import net.minecraft.init.Items.EGG

internal object EggProcessing
{

    // @formatter:off

    fun init()
    {
        eggPreProcess()
        eggWhiteYolkProcess()
        lecithinProcess()
    }

    private fun eggPreProcess()
    {
        // Egg -> Eggshell + Egg Liquid
        EXTRACTOR_RECIPES.addRecipe {
            input(EGG)
            outputs(EGGSHELL.stack())
            fluidOutputs(EggLiquid.getFluid(100))
            EUt(VA[LV])
            duration(2 * SECOND + 8 * TICK)
        }

        // Eggshell -> CaCO3
        MACERATOR_RECIPES.addRecipe {
            inputs(EGGSHELL.stack())
            output(dust, Calcite)
            EUt(2) // ULV
            duration(12 * TICK)
        }

        // Egg Liquid -> Egg White + Egg Yolk
        CENTRIFUGE_RECIPES.addRecipe {
            fluidInputs(EggLiquid.getFluid(300))
            fluidOutputs(EggWhite.getFluid(200))
            fluidOutputs(EggYolk.getFluid(100))
            EUt(VA[LV])
            duration(3 * SECOND + 12 * TICK)
        }
    }

    private fun eggWhiteYolkProcess()
    {
        // Egg White -> Sugar Free Egg White
        EXTRACTOR_RECIPES.removeRecipe(OreDictUnifier.get(dust, EggWhite))
        FERMENTING_RECIPES.addRecipe {
            circuitMeta(1)
            input(dustTiny, Yeast)
            fluidInputs(EggWhite.getFluid(2000))
            fluidOutputs(SugarFreeEggWhite.getFluid(2000))
            EUt(V[ULV] + VH[ULV])
            duration(15 * SECOND)
        }

        FERMENTING_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Yeast)
            fluidInputs(EggWhite.getFluid(18000))
            fluidOutputs(SugarFreeEggWhite.getFluid(18000))
            EUt(V[LV] + VH[LV])
            duration(15 * SECOND)
        }

        // Sugar Free Egg White -> Egg White
        CHEMICAL_DEHYDRATOR_RECIPES.addRecipe {
            fluidInputs(SugarFreeEggWhite.getFluid(250))
            output(dust, EggWhite)
            fluidOutputs(Water.getFluid(100))
            EUt(VH[MV])
            duration(6 * SECOND)
        }

        // Egg Yolk
        EXTRACTOR_RECIPES.removeRecipe(OreDictUnifier.get(dust, EggYolk))
        CHEMICAL_DEHYDRATOR_RECIPES.addRecipe {
            fluidInputs(EggYolk.getFluid(250))
            output(dust, EggYolk)
            fluidOutputs(Water.getFluid(100))
            EUt(VH[MV])
            duration(6 * SECOND)
        }
    }

    private fun lecithinProcess()
    {
        // Egg Yolk + C2H6O -> Phospholipid Ethanol Emulsion + Wet Egg White Cake
        CHEMICAL_RECIPES.addRecipe {
            input(dust, EggYolk)
            fluidInputs(Ethanol.getFluid(2000))
            outputs(WET_EGG_WHITE_CAKE.stack())
            fluidOutputs(PhospholipidEthanolEmulsion.getFluid(2000))
            EUt(VA[HV])
            duration(20 * SECOND)
        }

        // Phospholipid Ethanol Emulsion -> Lecithin + Fat + C2H6O + Water
        DISTILLATION_RECIPES.addRecipe {
            fluidInputs(PhospholipidEthanolEmulsion.getFluid(1000))
            fluidOutputs(Lecithin.getFluid(20))
            fluidOutputs(Fat.getFluid(55))
            fluidOutputs(Ethanol.getFluid(900))
            fluidOutputs(Water.getFluid(15))
            EUt(VA[HV])
            duration(6 * SECOND)
        }

        // Wet Egg White Cake -> Egg White + C2H6O
        CHEMICAL_DEHYDRATOR_RECIPES.addRecipe {
            inputs(WET_EGG_WHITE_CAKE.getStack(2))
            output(dust, EggWhite)
            fluidOutputs(Ethanol.getFluid(400))
            EUt(VA[MV])
            duration(4 * SECOND)
        }
    }

    // @formatter:on

}