package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumOxalate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumTrihydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OxalicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumHydride
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

internal object ActiniumSuperhydrideChain
{

    // @formatter:off

    fun init()
    {
        // Na + H -> NaH
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Sodium)
            .fluidInputs(Hydrogen.getFluid(1000))
            .output(ingot, SodiumHydride, 2)
            .EUt(VA[HV])
            .duration(15 * SECOND)
            .buildAndRegister()

        // Ac + 2C2H2O4 + 2O -> Ac(CO2)4 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Actinium)
            .fluidInputs(OxalicAcid.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(2000))
            .output(dust, ActiniumOxalate, 13)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[IV])
            .duration(9 * SECOND)
            .buildAndRegister()

        // Ac(CO2)4 + 13Na + 3NaH + 4CCl4 -> AcH3 + 16NaCl + 8CO2
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, ActiniumOxalate, 13)
            .input(dust, Sodium, 13)
            .input(dust, SodiumHydride, 6)
            .fluidInputs(CarbonTetrachloride.getFluid(4000))
            .output(dust, ActiniumTrihydride, 4)
            .output(dust, Salt, 32)
            .fluidOutputs(CarbonDioxide.getFluid(8000))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Tier 1: 16AcH3 + 144H -> 16AcH12
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, ActiniumTrihydride, 64)
            .inputs(ItemStack(TARANIUM_CHARGE))
            .fluidInputs(Hydrogen.getFluid(144000))
            .fluidOutputs(ActiniumSuperhydride.getPlasma(16000))
            .EUt(VA[UHV])
            .duration(2 * MINUTE)
            .buildAndRegister()

        // Tier 2: 64AcH3 + 576H -> 64AcH12
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, ActiniumTrihydride, 64)
            .input(dust, ActiniumTrihydride, 64)
            .input(dust, ActiniumTrihydride, 64)
            .input(dust, ActiniumTrihydride, 64)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidInputs(Hydrogen.getFluid(576000))
            .fluidOutputs(ActiniumSuperhydride.getPlasma(64000))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // Tier 3: 576AcH3 + 19008H -> 576AcH12
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .input(block, ActiniumTrihydride, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Hydrogen.getFluid(19008000))
            .fluidOutputs(ActiniumSuperhydride.getPlasma(576000))
            .EUt(VA[UIV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Solidification of AcH12.
        VACUUM_RECIPES.recipeBuilder()
            .fluidInputs(ActiniumSuperhydride.getPlasma(1000))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 500))
            .output(dust, ActiniumSuperhydride, 13)
            .fluidOutputs(Helium.getFluid(250))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}