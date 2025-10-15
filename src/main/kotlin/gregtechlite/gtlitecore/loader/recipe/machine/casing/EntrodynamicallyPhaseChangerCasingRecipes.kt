package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CinobiteA243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lafium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WoodsGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY

internal object EntrodynamicallyPhaseChangerCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Lattice QCD Shielding Casing
        SPACE_ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, QuantumchromodynamicallyConfinedMatter, 4)
            .input(plate, Shirabon, 16)
            .input(plate, CinobiteA243, 16)
            .input(plate, HalkoniteSteel, 16)
            .input(stickLong, LanthanumGroupAlloyB, 8)
            .input(screw, HastelloyK243, 32)
            .input(nanite, Chrome)
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(CosmicNeutronium.getFluid(L * 40))
            .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 20))
            .outputs(MultiblockCasing.LATTICE_QCD_THERMAL_SHIELDING_CASING.getStack(64))
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .tier(4)
            .buildAndRegister()

        // Hamilton-Killing Flow Control Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium, 16)
            .inputs(ScienceCasing.HOLLOW_CASING.getStack(32))
            .inputs(ActiveUniqueCasing.TEMPERATURE_CONTROLLER.getStack(64))
            .input(plateDense, Neutronium, 6)
            .input(ELECTRIC_PUMP_UIV, 8)
            .input(wireGtSingle, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .input(QUANTUM_ANOMALY)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(CosmicNeutronium.getFluid(L * 10))
            .fluidInputs(TransitionAlloyB.getFluid(L * 5))
            .outputs(MultiblockCasing.HAMILTON_KILLING_FLOW_CONTROL_CASING.getStack(64))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(ActiveUniqueCasing.TEMPERATURE_CONTROLLER.stack)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Nano Shielding Frame
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.QUANTUM.getStack(8))
            .input(frameGt, ChromaticGlass, 16)
            .input(stickLong, Lafium, 2)
            .input(stickLong, TransitionAlloyA, 2)
            .input(lens, GSTGlass, 4)
            .input(lens, ZBLANGlass, 4)
            .input(lens, WoodsGlass, 4)
            .input(lens, Polymethylmethacrylate, 4)
            .input(nanite, Iron)
            .fluidInputs(Bedrockium.getFluid(L * 40))
            .fluidInputs(Pikyonium64B.getFluid(L * 20))
            .fluidInputs(Thulium.getFluid(L * 16))
            .fluidInputs(Hypogen.getFluid(L * 4))
            .outputs(GlassCasing.NANO_SHIELDING_FRAME.getStack(64))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(GlassCasing.QUANTUM.stack)
                    .EUt(VA[UIV])
                    .CWUt(32)
            }
            .buildAndRegister()

    }

    // @formatter:on

}