package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockComputerCasing
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.metatileentities.MetaTileEntities.HPCA_BRIDGE_COMPONENT
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_WAFER

internal object ScienceCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Molecular Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.HIGH_POWER_CASING))
            .input(plateDense, Osmiridium, 6)
            .input(foil, Trinium, 12)
            .input(screw, TungstenSteel, 24)
            .input(ring, TungstenSteel, 24)
            .input(FIELD_GENERATOR_IV)
            .fluidInputs(Osmium.getFluid(L * 9))
            .outputs(ScienceCasing.MOLECULAR_CASING.getStack(2))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Quantum Glass
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.stack)
            .inputs(MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.LAMINATED_GLASS))
            .fluidInputs(Trinium.getFluid(L * 4))
            .outputs(GlassCasing.QUANTUM.stack)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Hollow Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.stack)
            .inputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE, 2))
            .input(plate, Europium, 2)
            .input(plateDouble, Plutonium239, 4)
            .input(plateDouble, Lead, 8)
            .input(plate, Uranium238, 16)
            .input(screw, Uranium235, 16)
            .fluidInputs(Trinium.getFluid(L * 9))
            .fluidInputs(Osmium.getFluid(L * 9))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .fluidInputs(Argon.getFluid(1000))
            .outputs(ScienceCasing.HOLLOW_CASING.getStack(4))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r ->
                r.researchStack(ScienceCasing.MOLECULAR_CASING.stack)
                    .EUt(VA[UV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Molecular Coil
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.HOLLOW_CASING.stack)
            .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL, 2))
            .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM, 2))
            .input(wireFine, Europium, 64)
            .input(foil, Europium, 64)
            .fluidInputs(GSTGlass.getFluid(L * 16))
            .fluidInputs(SiliconeRubber.getFluid(L * 13))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
            .fluidInputs(Trinium.getFluid(L * 9))
            .outputs(ScienceCasing.MOLECULAR_COIL.getStack(8))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r ->
                r.researchStack(ScienceCasing.HOLLOW_CASING.stack)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Dimensional Bridge Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_CASING.getStack(16))
            .input(HPCA_BRIDGE_COMPONENT, 32)
            .input(circuit, Tier.UHV, 2)
            .input(EMITTER_UHV, 4)
            .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 8)
            .input(ATTO_PIC_WAFER, 2)
            .input(FIELD_GENERATOR_UHV)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(MetastableOganesson.getFluid(L * 40))
            .fluidInputs(NaquadahEnriched.getFluid(L * 10))
            .outputs(ScienceCasing.DIMENSIONAL_BRIDGE_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .stationResearch { r ->
                r.researchStack(HPCA_BRIDGE_COMPONENT.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(24)
            }
            .buildAndRegister()

    }

    // @formatter:on

}