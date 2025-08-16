package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ALLOY_BLAST_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COSMIC_RAY_DETECTING_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicHeterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Gluons
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyLeptonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LightQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetoResonatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NaquadriaEnergetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NdYAG
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuasifissioningPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StableBaryonicMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumFerrite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZephyreanAerotheum
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Heterodiamond
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.TARANIUM_CHARGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import net.minecraft.item.ItemStack

internal object ParticlesChain
{

    // @formatter:off

    fun init()
    {

        // Neutron-Proton Fermi Superfluid
        COSMIC_RAY_DETECTING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidOutputs(NeutronProtonFermiSuperfluid.getFluid(10))
            .EUt(VA[UHV])
            .duration(10 * TICK)
            .minHeight(100)
            .buildAndRegister()

        // Heavy Lepton Mixture
        COSMIC_RAY_DETECTING_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidOutputs(HeavyLeptonMixture.getFluid(10))
            .EUt(VA[UHV])
            .duration(10 * TICK)
            .minHeight(120)
            .buildAndRegister()

        // Hadronic Resonant Matter
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(lens, NdYAG)
            .notConsumable(lens, CubicHeterodiamond)
            .input(dust, MagnetoResonatic)
            .input(dust, StrontiumFerrite, 2)
            .fluidInputs(QuasifissioningPlasma.getFluid(L * 2))
            .fluidInputs(UUMatter.getFluid(1000))
            .fluidOutputs(HadronicResonantGas.getFluid(1000))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Stable Baryonic Matter
        ALLOY_BLAST_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, Taranium)
            .input(dust, MetastableOganesson)
            .input(dust, Praseodymium, 2)
            .input(dust, ZephyreanAerotheum, 4)
            .fluidInputs(NaquadriaEnergetic.getFluid(1000))
            .fluidOutputs(StableBaryonicMatter.getFluid(9000))
            .EUt(VA[UHV])
            .duration(1 * MINUTE + 30 * SECOND)
            .blastFurnaceTemp(9800) // Tritanium
            .buildAndRegister()

        // Dimensionally Shifted Superfluid
        LARGE_MIXER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .fluidInputs(FreeElectronGas.getFluid(2000))
            .fluidInputs(HadronicResonantGas.getFluid(1000))
            .fluidInputs(StableBaryonicMatter.getFluid(500))
            .fluidInputs(HeavyQuarks.getFluid(200))
            .fluidInputs(LightQuarks.getFluid(200))
            .fluidInputs(Gluons.getFluid(100))
            .fluidOutputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .EUt(VA[UEV])
            .duration(2 * MINUTE)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Resonant Strange Meson
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(HadronicResonantGas.getFluid(200))
            .fluidOutputs(ResonantStrangeMeson.getFluid(100))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Quantum Anomaly
        SIFTER_RECIPES.recipeBuilder()
            .notConsumable(springSmall, CosmicNeutronium)
            .fluidInputs(ResonantStrangeMeson.getFluid(4000))
            .chancedOutput(QUANTUM_ANOMALY, 2000, 0)
            .chancedOutput(QUANTUM_ANOMALY, 1500, 0)
            .chancedOutput(QUANTUM_ANOMALY, 1000, 0)
            .chancedOutput(QUANTUM_ANOMALY, 500, 0)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Advanced recipes for Quantum Anomaly.
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(lens, ChromaticGlass)
            .input(gem, Diamond, 16)
            .inputs(ItemStack(TARANIUM_CHARGE))
            .fluidInputs(Duranium.getFluid(L))
            .output(QUANTUM_ANOMALY, 16)
            .EUt(VA[UEV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(lens, ChromaticGlass)
            .input(gem, Heterodiamond, 16)
            .inputs(ItemStack(LEPTONIC_CHARGE))
            .fluidInputs(Tritanium.getFluid(L))
            .output(QUANTUM_ANOMALY, 64)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(lens, ChromaticGlass)
            .input(gem, CubicHeterodiamond, 16)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Taranium.getFluid(L))
            .output(QUANTUM_ANOMALY, 64)
            .output(QUANTUM_ANOMALY, 64)
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .buildAndRegister()

    }

}