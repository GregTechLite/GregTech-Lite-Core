package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.category.RecipeCategories
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.NAQUADRIA_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

internal object DegenerateRheniumChain
{

    // @formatter:off

    fun init()
    {
        // Tier 1: 144L Rhenium -> 1000L DegenerateRhenium
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(plate, Rhenium)
            .inputs(ItemStack(NAQUADRIA_CHARGE))
            .fluidOutputs(DegenerateRhenium.getPlasma(1000))
            .EUt(VA[UHV])
            .duration(2 * MINUTE)
            .buildAndRegister()

        // Tier 2: 144L * 9 Rhenium -> 1000L * 9 DegenerateRhenium
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(plateDense, Rhenium)
            .inputs(ItemStack(TARANIUM_CHARGE))
            .fluidOutputs(DegenerateRhenium.getFluid(9000))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // Tier 3: 144L * 9 * 16 Rhenium -> 1000L * 9 * 16 DegenerateRhenium
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(block, Rhenium, 16)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidOutputs(DegenerateRhenium.getFluid(144000))
            .EUt(VA[UIV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Tier 4: 144L * 9 * 64 Rhenium -> 1000L * 9 * 64 DegenerateRhenium
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(block, Rhenium, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidOutputs(DegenerateRhenium.getFluid(576000))
            .EUt(VA[UXV])
            .duration(1 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Solidification of DegenerateRhenium.
        VACUUM_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(DegenerateRhenium.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 500))
            .output(dust, DegenerateRhenium)
            .fluidOutputs(Helium.getFluid(250))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        VACUUM_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_PLATE)
            .fluidInputs(DegenerateRhenium.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 500))
            .output(plate, DegenerateRhenium)
            .fluidOutputs(Helium.getFluid(250))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Degenerate Rhenium decompositions.
        MACERATOR_RECIPES.recipeBuilder()
            .input(plate, DegenerateRhenium)
            .output(dust, DegenerateRhenium)
            .EUt(VA[UHV])
            .duration(9 * SECOND + 6 * TICK) // Same as Rhenium plate decomposition
            .category(RecipeCategories.MACERATOR_RECYCLING)
            .buildAndRegister()

        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(plate, DegenerateRhenium)
            .fluidInputs(Oxygen.getPlasma(186))
            .output(dust, DegenerateRhenium)
            .EUt(VA[UHV])
            .duration(9 * SECOND + 6 * TICK) // Same as Rhenium plate decomposition
            .category(RecipeCategories.ARC_FURNACE_RECYCLING)
            .buildAndRegister()

    }

    // @formatter:on

}