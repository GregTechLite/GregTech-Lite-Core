package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.ENERGY_CLUSTER
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGroupAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Protomatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing

internal object AntimatterCasingRecipes
{

    // @formatter:off

    fun init()
    {

        // Antimatter Containment Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.PMMA.getStack(16))
            .input(stickLong, Infinity, 4)
            .input(stickLong, CosmicNeutronium, 12)
            .input(EMITTER_UEV, 4)
            .input(foil, LanthanumGroupAlloyA, 24)
            .input(wireGtQuadruple, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .fluidInputs(Bedrockium.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .outputs(GlassCasing.ANTIMATTER_CONTAINMENT.getStack(64))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(GlassCasing.PMMA.stack)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Gravity Stabilization Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(MetalCasing.NEUTRONIUM.getStack(16))
            .input(SENSOR_UEV, 2)
            .input(ENERGY_CLUSTER)
            .input(plate, Infinity, 4)
            .input(gear, Infinity)
            .input(gearSmall, Infinity, 2)
            .input(GRAVI_STAR, 4)
            .input(circuit, Tier.UIV)
            .input(foil, LanthanumGroupAlloyB, 8)
            .input(wireGtQuadruple, VibraniumTritaniumActiniumIronSuperhydride, 16)
            .fluidInputs(Bedrockium.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(L * 16))
            .outputs(MultiblockCasing.GRAVITY_STABILIZATION_CASING.getStack(64))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MetalCasing.NEUTRONIUM.stack)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Protomatter Activation Coil
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(FusionCoil.ULTIMATE.getStack(16))
            .input(ELECTRIC_PUMP_UEV, 2)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 8)
            .input(plateDense, Infinity)
            .input(rotor, Hypogen, 4)
            .input(circuit, Tier.UIV)
            .input(FIELD_GENERATOR_UEV, 4)
            .input(foil, ActiniumGroupAlloyA, 16)
            .fluidInputs(Bedrockium.getFluid(L * 16))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(L * 8))
            .fluidInputs(Protomatter.getFluid(500))
            .outputs(MultiblockCasing.PROTOMATTER_ACTIVATION_COIL.getStack(32))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(FusionCoil.ULTIMATE.stack)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Antimatter Annihilation Matrix
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(16))
            .input(foil, Hypogen, 64)
            .input(EMITTER_UEV, 2)
            .input(circuit, Tier.UIV)
            .input(frameGt, Infinity, 4)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 8)
            .input(SENSOR_UEV, 2)
            .input(rotor, Infinity, 4)
            .input(foil, ActiniumGroupAlloyB, 16)
            .fluidInputs(CosmicNeutronium.getFluid(L * 10))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(Antimatter.getFluid(200))
            .outputs(MultiblockCasing.ANTIMATTER_ANNIHILATION_MATRIX.getStack(64))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(ScienceCasing.MOLECULAR_COIL.stack)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

    }

    // @formatter:on

}