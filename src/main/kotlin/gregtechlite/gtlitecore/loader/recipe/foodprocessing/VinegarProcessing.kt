package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.FERMENTING_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BATH_CONDENSER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminooxyaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vinegar
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VINEGAR
import net.minecraft.init.Items.GLASS_BOTTLE

internal object VinegarProcessing
{

    // @formatter:off

    fun init()
    {
        // Vinegar
        FERMENTING_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Sugar)
            fluidInputs(Ethanol.getFluid(100))
            fluidOutputs(Vinegar.getFluid(100))
            EUt(2)
            duration(2 * SECOND + 10 * TICK)
        }

        FERMENTING_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Sugar)
            fluidInputs(Ethanol.getFluid(100))
            fluidInputs(AminooxyaceticAcid.getFluid(50))
            fluidOutputs(Vinegar.getFluid(400))
            EUt(2)
            duration(15 * TICK)
        }

        CANNER_RECIPES.addRecipe {
            inputs(GLASS_BOTTLE)
            fluidInputs(Vinegar.getFluid(250))
            output(VINEGAR)
            EUt(4)
            duration(10 * TICK)
        }

        // Vinegar -> C2H4O2 (AceticAcid)
        for (fluid in arrayOf(
            Water.getFluid(100),
            DistilledWater.getFluid(100),
            Ice.getFluid(50),
            Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
            Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
        {
            BATH_CONDENSER_RECIPES.addRecipe {
                fluidInputs(Vinegar.getFluid(100))
                fluidInputs(fluid)
                fluidOutputs(AceticAcid.getFluid(100))
                EUt(VA[LV])
                duration(5 * SECOND)
            }
        }
    }

    // @formatter:on

}