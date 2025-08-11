package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UIV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DegenerateRhenium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HafniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyLeptonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HighEnergyQuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Inconel625
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuarkGluonPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zeron100
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.ShieldingCore
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NAQUADRIA_SUPERSOLID
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STABLE_ADHESIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPERCONDUCTOR_COMPOSITE

internal object QuantumForceTransformerCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Force Field Glass
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.QUANTUM.stack)
            .input(FIELD_GENERATOR_ZPM)
            .input(stickLong, PreciousMetalAlloy, 6)
            .input(plate, Polymethylmethacrylate, 6)
            .fluidInputs(QuantumAlloy.getFluid(L * 6))
            .outputs(GlassCasing.FORCE_FIELD.stack)
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Particle Containment Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(12)
            .input(frameGt, TantalumCarbide)
            .inputs(MetalCasing.IRIDIUM.stack)
            .input(circuit, Tier.ZPM, 16)
            .input(screw, Inconel625, 32)
            .input(bolt, HafniumCarbide, 12)
            .input(plate, Zeron100, 8)
            .fluidInputs(Trinaquadalloy.getFluid(L * 4))
            .outputs(MultiblockCasing.PARTICLE_CONTAINMENT_CASING.getStack(2))
            .EUt(VA[ZPM])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Particle Excitation Wire Coil
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(WireCoil.INFINITY.stack)
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(2))
            .input(plateDouble, Seaborgium, 4)
            .input(screw, Abyssalloy, 8)
            .outputs(MultiblockCasing.PARTICLE_EXCITATION_WIRE_COIL.getStack(2))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Neutron Pulse Manipulator (T1)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.FORCE_FIELD.stack)
            .input(nanite, Carbon, 4)
            .input(EMITTER_UV, 4)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 8)
            .input(QUANTUM_ANOMALY)
            .input(plate, CosmicNeutronium, 2)
            .fluidInputs(Thulium.getFluid(L * 10))
            .fluidInputs(HeavyLeptonMixture.getFluid(5000))
            .fluidInputs(Neptunium.getPlasma(500))
            .fluidInputs(Fermium.getPlasma(500))
            .outputs(Manipulator.NEUTRON_PULSE.getStack(16))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(GlassCasing.FORCE_FIELD.stack)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Cosmic Fabric Manipulator (T2)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.FORCE_FIELD.getStack(2))
            .input(nanite, Carbon, 8)
            .input(EMITTER_UHV, 4)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 8)
            .input(QUANTUM_ANOMALY)
            .input(plate, DegenerateRhenium, 4)
            .input(STABLE_ADHESIVE, 4)
            .fluidInputs(Thulium.getFluid(L * 12))
            .fluidInputs(QuarkGluonPlasma.getFluid(5000))
            .fluidInputs(Neptunium.getPlasma(2500))
            .fluidInputs(Fermium.getPlasma(2500))
            .outputs(Manipulator.COSMIC_FABRIC.getStack(16))
            .EUt(VA[UIV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(Manipulator.NEUTRON_PULSE.stack)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Infinity Infused Manipulator (T3)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.FORCE_FIELD.getStack(4))
            .input(nanite, Carbon, 16)
            .input(EMITTER_UEV, 4)
            .input(wireGtHex, FullereneSuperconductor, 8)
            .input(QUANTUM_ANOMALY)
            .input(plate, Hypogen, 8)
            .input(SUPERCONDUCTOR_COMPOSITE, 4)
            .fluidInputs(Thulium.getFluid(L * 15))
            .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(5000))
            .fluidInputs(Neptunium.getPlasma(10000))
            .fluidInputs(Fermium.getPlasma(10000))
            .outputs(Manipulator.INFINITY_INFUSED.getStack(16))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(Manipulator.COSMIC_FABRIC.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // Spacetime Continuum Ripper (T4)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GlassCasing.FORCE_FIELD.getStack(8))
            .input(nanite, Carbon, 32)
            .input(EMITTER_UIV, 4)
            .input(wireGtHex, BoronFranciumCarbideSuperconductor, 8)
            .input(QUANTUM_ANOMALY)
            .input(plate, TranscendentMetal, 16)
            .input(NAQUADRIA_SUPERSOLID, 4)
            .fluidInputs(Thulium.getFluid(L * 20))
            .fluidInputs(SpaceTime.getFluid(5000))
            .fluidInputs(Neptunium.getPlasma(20000))
            .fluidInputs(Fermium.getPlasma(20000))
            .outputs(Manipulator.SPACETIME_CONTINUUM_RIPPER.getStack(16))
            .EUt(VA[OpV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(Manipulator.INFINITY_INFUSED.stack)
                    .EUt(VA[OpV])
                    .CWUt(160)
            }
            .buildAndRegister()

        // Neutron Shielding Core (T1)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, QuantumAlloy)
            .input(plateDense, EnrichedNaquadahAlloy, 4)
            .input(plateDense, Neutronium, 2)
            .input(FIELD_GENERATOR_UV)
            .input(screw, HastelloyX78, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 10))
            .outputs(ShieldingCore.NEUTRON.getStack(16))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MultiblockCasing.PARTICLE_CONTAINMENT_CASING.stack)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Cosmic Fabric Shielding Core (T2)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, QuantumAlloy, 2)
            .input(plateDense, PreciousMetalAlloy, 4)
            .input(plateDense, CosmicNeutronium, 2)
            .input(FIELD_GENERATOR_UHV)
            .input(screw, HastelloyK243, 16)
            .input(STABLE_ADHESIVE, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 20))
            .outputs(ShieldingCore.COSMIC_FABRIC.getStack(16))
            .EUt(VA[UIV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(ShieldingCore.NEUTRON.stack)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // Infinity Infused Shielding Core (T3)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, QuantumAlloy, 4)
            .input(plateDense, ArceusAlloy2B, 4)
            .input(plateDense, Hypogen, 2)
            .input(FIELD_GENERATOR_UEV)
            .input(screw, HalkoniteSteel, 16)
            .input(SUPERCONDUCTOR_COMPOSITE, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .outputs(ShieldingCore.INFINITY_INFUSED.getStack(16))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(ShieldingCore.COSMIC_FABRIC.stack)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // Spacetime Bending Core (T4)
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, QuantumAlloy, 8)
            .input(plateDense, Shirabon, 4)
            .input(plateDense, SpaceTime, 2)
            .input(FIELD_GENERATOR_UIV)
            .input(screw, TranscendentMetal, 16)
            .input(NAQUADRIA_SUPERSOLID, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .outputs(ShieldingCore.SPACETIME_BENDING_CORE.getStack(16))
            .EUt(VA[OpV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(ShieldingCore.INFINITY_INFUSED.stack)
                    .EUt(VA[OpV])
                    .CWUt(160)
            }
            .buildAndRegister()
    }

    // @formatter:on

}