package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.common.items.MetaItems.EMITTER_UXV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.ComponentAssemblyCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RAW_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RELATIVISTIC_HEAT_CAPACITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPERCONDUCTOR_COMPOSITE

object NanoAssemblyComplexCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Nano Precise Assembly Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, SpaceTime, 4)
            .inputs(ComponentAssemblyCasing.UXV.getStack(8))
            .input(plateDouble, Creon, 32)
            .input(plateDouble, Magnetium, 32)
            .input(screw, Hypogen, 8)
            .input(screw, QuantumchromodynamicallyConfinedMatter, 8)
            .input(SUPERCONDUCTOR_COMPOSITE)
            .input(EMITTER_UXV, 2)
            .input(ENERGISED_TESSERACT)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 16))
            .fluidInputs(MetastableOganesson.getFluid(L * 16))
            .outputs(ActiveUniqueCasing.NANO_PRECISE_ASSEMBLY_UNIT.getStack(64))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(frameGt, SpaceTime)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Nanite Transmission Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, TranscendentMetal)
            .input(stickLong, Creon, 12)
            .inputs(MultiblockCasing.THERMAL_ENERGY_TRANSMISSION_CASING.stack)
            .input(RAW_TESSERACT, 8)
            .input(plateDense, HarmonicPhononMatter, 4)
            .input(RELATIVISTIC_HEAT_CAPACITY, 6)
            .input(FIELD_GENERATOR_UIV, 4)
            .input(bolt, Legendarium, 24)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(MetastableHassium.getFluid(L * 64))
            .outputs(MultiblockCasing.NANITE_TRANSMISSION_CASING.getStack(64))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(MultiblockCasing.THERMAL_ENERGY_TRANSMISSION_CASING.stack)
                    .EUt(VA[UXV])
                    .CWUt(72)
            }
            .buildAndRegister()

        // Nanite Control Casing
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, QuantumchromodynamicallyConfinedMatter, 4)
            .input(plateDense, Infinity, 2)
            .input(plate, QuantumAlloy, 16)
            .input(frameGt, Mellion, 4)
            .input(plateDense, CosmicNeutronium, 2)
            .inputs(GTMultiblockCasing.ASSEMBLY_CONTROL.getStack(64))
            .input(stickLong, Shirabon, 8)
            .input(plate, Creon, 16)
            .input(plate, SpaceTime, 16)
            .input(stickLong, Legendarium, 8)
            .inputs(GTMultiblockCasing.ASSEMBLY_LINE_CASING.getStack(64))
            .input(plateDense, TranscendentMetal, 2)
            .input(frameGt, TitanSteel, 4)
            .input(plate, HalkoniteSteel, 16)
            .input(plateDense, MagMatter, 2)
            .input(frameGt, Abyssalloy, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(256000))
            .fluidInputs(Bedrockium.getFluid(L * 256))
            .fluidInputs(Neutronium.getFluid(L * 256))
            .outputs(ActiveUniqueCasing.NANITE_CONTROL_CASING.getStack(64))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .tier(5)
            .buildAndRegister()
    }

    // @formatter:on

}