package magicbook.gtlitecore.loader.recipe.chain

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumOxalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumSuperhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ActiniumTrihydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonTetrachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.OxalicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumHydride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class ActiniumSuperhydrideChain
{

    companion object
    {

        fun init()
        {
            // Na + H -> NaH
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Sodium)
                .fluidInputs(Hydrogen.getFluid(1000))
                .output(ingot, SodiumHydride, 2)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Ac + 2C2H2O4 + 2O -> Ac(CO2)4 + 2H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Actinium)
                .fluidInputs(OxalicAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, ActiniumOxalate, 13)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[IV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tier 1: 16AcH3 + 144H -> 16AcH12
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, ActiniumTrihydride, 64)
                .inputs(ItemStack(TARANIUM_CHARGE))
                .fluidInputs(Hydrogen.getFluid(144000))
                .fluidOutputs(ActiniumSuperhydride.getPlasma(16000))
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Tier 3: 576AcH3 + 19008H -> 576AcH12
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(block, ActiniumTrihydride, 64)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Hydrogen.getFluid(19008000))
                .fluidOutputs(ActiniumSuperhydride.getPlasma(576000))
                .EUt(VA[UIV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Solidification of AcH12.
            VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(ActiniumSuperhydride.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                .output(dust, ActiniumSuperhydride, 13)
                .fluidOutputs(Helium.getFluid(250))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}