package magicbook.gtlitecore.loader.recipe.chain.food

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BATH_CONDENSER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AminooxyaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vinegar
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VINEGAR
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class VinegarChain
{

    companion object
    {

        fun init()
        {

            // Vinegar
            FERMENTING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Sugar)
                .fluidInputs(Ethanol.getFluid(100))
                .fluidOutputs(Vinegar.getFluid(100))
                .EUt(2)
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            FERMENTING_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Sugar)
                .fluidInputs(Ethanol.getFluid(100))
                .fluidInputs(AminooxyaceticAcid.getFluid(50))
                .fluidOutputs(Vinegar.getFluid(400))
                .EUt(2)
                .duration(15 * TICK)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.GLASS_BOTTLE))
                .fluidInputs(Vinegar.getFluid(250))
                .output(VINEGAR)
                .EUt(4)
                .duration(10 * TICK)
                .buildAndRegister()

            // Vinegar -> C2H4O2 (AceticAcid)
            for (fluidStack in arrayOf(
                Water.getFluid(100),
                DistilledWater.getFluid(100),
                Ice.getFluid(50),
                Oxygen.getFluid(FluidStorageKeys.LIQUID, 25),
                Helium.getFluid(FluidStorageKeys.LIQUID, 10)))
            {
                BATH_CONDENSER_RECIPES.recipeBuilder()
                    .fluidInputs(Vinegar.getFluid(100))
                    .fluidInputs(fluidStack)
                    .fluidOutputs(AceticAcid.getFluid(100))
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

        }

    }

}