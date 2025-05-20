package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.springSmall
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ALLOY_BLAST_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.COSMIC_RAY_DETECTING_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicHeterodiamond
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimensionallyShiftedSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FreeElectronGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HadronicResonantGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyLeptonMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadriaEnergetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NdYAG
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeutronProtonFermiSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuasifissioningPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StableBaryonicMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumFerrite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZephyreanAerotheum
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY

@Suppress("MISSING_DEPENDENCY_CLASS")
class ParticlesChain
{

    companion object
    {

        fun init()
        {
            // Neutron-Proton Fermi Superfluid
            COSMIC_RAY_DETECTING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidOutputs(NeutronProtonFermiSuperfluid.getFluid(10))
                .EUt(VA[UHV].toLong())
                .duration(10 * TICK)
                .minHeight(100)
                .buildAndRegister()

            // Heavy Lepton Mixture
            COSMIC_RAY_DETECTING_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidOutputs(HeavyLeptonMixture.getFluid(10))
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UHV].toLong())
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
                .EUt(VA[UEV].toLong())
                .duration(2 * MINUTE)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Resonant Strange Meson
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(HadronicResonantGas.getFluid(200))
                .fluidOutputs(ResonantStrangeMeson.getFluid(100))
                .EUt(VA[UEV].toLong())
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
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

}