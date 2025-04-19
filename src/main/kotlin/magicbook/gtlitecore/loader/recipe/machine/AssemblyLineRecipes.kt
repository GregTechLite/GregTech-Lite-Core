package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.builders.ResearchRecipeBuilder
import gregtech.api.recipes.builders.ResearchRecipeBuilder.ScannerRecipeBuilder
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class AssemblyLineRecipes
{

    companion object
    {

        fun init()
        {
            componentRecipes()
            energyHatchesRecipes()
            laserHatchesRecipes()
        }

        private fun componentRecipes()
        {
            electricMotorRecipes()
            electricPistonRecipes()
            electricPumpRecipes()
            conveyorModuleRecipes()
            robotArmRecipes()
            emitterRecipes()
            sensorRecipes()
            fieldGeneratorRecipes()
        }

        private fun electricMotorRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                    OreDictUnifier.get(stickLong, HSSS, 2),
                    OreDictUnifier.get(ring, HSSS, 2),
                    OreDictUnifier.get(round, HSSS, 4),
                    OreDictUnifier.get(wireFine, Ruridit, 64),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
                arrayOf(SolderingAlloy.getFluid(L),
                    Lubricant.getFluid(250)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, SamariumMagnetic)
                .input(stickLong, HSSS)
                .input(ring, HSSS, 2)
                .input(round, HSSS, 4)
                .input(wireFine, Ruridit, 64)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_MOTOR_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_MOTOR_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(30 * SECOND)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                    OreDictUnifier.get(stickLong, Osmiridium, 4),
                    OreDictUnifier.get(ring, Osmiridium, 4),
                    OreDictUnifier.get(round, Osmiridium, 8),
                    OreDictUnifier.get(wireFine, Europium, 64),
                    OreDictUnifier.get(wireFine, Europium, 32),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, SamariumMagnetic)
                .input(stickLong, Osmiridium)
                .input(ring, Osmiridium, 2)
                .input(round, Osmiridium, 4)
                .input(wireFine, Europium, 64)
                .input(wireFine, Europium, 32)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_MOTOR_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_MOTOR_LuV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(MINUTE)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, SamariumMagnetic),
                    OreDictUnifier.get(stickLong, Tritanium, 4),
                    OreDictUnifier.get(ring, Tritanium, 4),
                    OreDictUnifier.get(round, Tritanium, 8),
                    OreDictUnifier.get(wireFine, Americium, 64),
                    OreDictUnifier.get(wireFine, Americium, 64),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 4),
                    Lubricant.getFluid(1000), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, SamariumMagnetic)
                .input(stickLong, Tritanium, 2)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 8)
                .input(wireFine, Americium, 64)
                .input(wireFine, Americium, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L))
                .output(ELECTRIC_MOTOR_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_MOTOR_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            //     .input(stickLong, Adamantium, 2)
            //     .input(ring, Adamantium, 4)
            //     .input(round, Adamantium, 8)
            //     .input(wireFine, ElectrumFluxed, 64)
            //     .input(wireFine, ElectrumFluxed, 64)
            //     .input(wireFine, ElectrumFluxed, 32)
            //     .input(cableGtSingle, Europium, 2)
            //     .fluidInputs(SolderingAlloy.getFluid(L * 8))
            //     .fluidInputs(Lubricant.getFluid(2000))
            //     .fluidInputs(Taranium.getFluid(L * 2))
            //     .output(ELECTRIC_MOTOR_UHV)
            //     .EUt(400_000) // UV
            //     .duration(20 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(ELECTRIC_MOTOR_UV.stackForm)
            //             .EUt(VA[UV].toLong())
            //             .CWUt(24)
            //     }
            //     .buildAndRegister()

            // UEV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, ChromiumGermaniumTellurideMagnetic)
            //     .input(stickLong, HeavyQuarkDegenerateMatter, 4)
            //     .input(ring, HeavyQuarkDegenerateMatter, 8)
            //     .input(round, HeavyQuarkDegenerateMatter, 16)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(wireFine, QuantumAlloy, 64)
            //     .input(cableGtSingle, Seaborgium, 2)
            //     .fluidInputs(MutatedLivingSolder.gteFluid(L * 16))
            //     .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            //     .fluidInputs(MetastableOganesson.getFluid(L * 4))
            //     .fluidInputs(Fullerene.getFluid(L))
            //     .output(ELECTRIC_MOTOR_UEV)
            //     .EUt(1_800_000) // UHV
            //     .duration(40 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(ELECTRIC_MOTOR_UHV.stackForm)
            //             .EUt(VA[UHV].toLong())
            //             .CWUt(32)
            //     }
            //     .buildAndRegister()

            // UIV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, QuantumchromodynamicallyConfinedMatter, 8)
            //     .input(ring, QuantumchromodynamicallyConfinedMatter, 16)
            //     .input(round, QuantumchromodynamicallyConfinedMatter, 32)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 64)
            //     .input(wireFine, Hypogen, 32)
            //     .input(cableGtSingle, Meitnerium, 2)

            // UXV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(ENERGISED_TESSERACT)
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, TranscendentMetal, 4)
            //     .input(stickLong, MagnetoHydrodynamicallyConstrainedStarMatter, 4)
            //     .input(ring, TranscendentMetal, 8)
            //     .input(ring, MagnetoHydrodynamicallyConstrainedStarMatter, 8)
            //     .input(round, TranscendentMetal, 16)
            //     .input(round, MagnetoHydrodynamicallyConstrainedStarMatter, 16)
            //     .input(wireFine, SpaceTime, 64)
            //     .input(wireFine, SpaceTime, 64)
            //     .input(wireFine, MagMatter, 64)
            //     .input(wireFine, MagMatter, 64)
            //     .input(cableGtSingle, Periodicium, 2)

            // OpV
            // ASSEMBLY_LINE_RECIPES.recipeBuilder()
            //     .input(ENERGISED_TESSERACT)
            //     .input(stickLong, TengamAttuned, 2)
            //     .input(stickLong, CosmicNeutronium, 4)
            //     .input(stickLong, Eternity)
            // ...

            // MAX
        }

        private fun electricPistonRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_LuV.stackForm,
                    OreDictUnifier.get(plate, HSSS, 4),
                    OreDictUnifier.get(ring, HSSS, 4),
                    OreDictUnifier.get(round, HSSS, 16),
                    OreDictUnifier.get(stick, HSSS, 4),
                    OreDictUnifier.get(gear, HSSS, 1),
                    OreDictUnifier.get(gearSmall, HSSS, 2),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
                arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV)
                .input(plate, HSSS)
                .input(ring, HSSS, 2)
                .input(round, HSSS, 4)
                .input(stick, HSSS, 2)
                .input(gear, HSSS)
                .input(gearSmall, HSSS, 2)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_PISTON_LUV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_PISTON_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(30 * SECOND)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_ZPM.stackForm,
                    OreDictUnifier.get(plate, Osmiridium, 4),
                    OreDictUnifier.get(ring, Osmiridium, 4),
                    OreDictUnifier.get(round, Osmiridium, 16),
                    OreDictUnifier.get(stick, Osmiridium, 4),
                    OreDictUnifier.get(gear, Osmiridium, 1),
                    OreDictUnifier.get(gearSmall, Osmiridium, 2),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM)
                .input(plate, Osmiridium)
                .input(ring, Osmiridium, 2)
                .input(round, Osmiridium, 4)
                .input(stick, Osmiridium, 2)
                .input(gear, Osmiridium)
                .input(gearSmall, Osmiridium, 2)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_PISTON_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_PISTON_LUV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_UV.stackForm,
                    OreDictUnifier.get(plate, Tritanium, 4),
                    OreDictUnifier.get(ring, Tritanium, 4),
                    OreDictUnifier.get(round, Tritanium, 16),
                    OreDictUnifier.get(stick, Tritanium, 4),
                    OreDictUnifier.get(gear, NaquadahAlloy, 1),
                    OreDictUnifier.get(gearSmall, NaquadahAlloy, 2),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000),
                    Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV)
                .input(plate, Tritanium, 2)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 8)
                .input(stick, Tritanium, 4)
                .input(gear, Tritanium)
                .input(gearSmall, Tritanium, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L))
                .output(ELECTRIC_PISTON_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_PISTON_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV

            // UEV

            // UIV

            // UXV

            // OpV

            // MAX
        }

        private fun electricPumpRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_LuV.stackForm,
                    OreDictUnifier.get(pipeSmallFluid, NiobiumTitanium),
                    OreDictUnifier.get(plate, HSSS, 2),
                    OreDictUnifier.get(screw, HSSS, 8),
                    OreDictUnifier.get(ring, SiliconeRubber, 4),
                    OreDictUnifier.get(rotor, HSSS),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
                arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV)
                .input(pipeNormalFluid, NiobiumTitanium)
                .input(plate, HSSS)
                .input(screw, HSSS, 4)
                .input("ringAnySyntheticRubber", 4)
                .input(rotor, HSSS)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_PUMP_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_PUMP_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_ZPM.stackForm,
                    OreDictUnifier.get(pipeNormalFluid, Polybenzimidazole),
                    OreDictUnifier.get(plate, Osmiridium, 2),
                    OreDictUnifier.get(screw, Osmiridium, 8),
                    OreDictUnifier.get(ring, SiliconeRubber, 8),
                    OreDictUnifier.get(rotor, Osmiridium),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM)
                .input(pipeNormalFluid, Iridium)
                .input(plate, Osmiridium)
                .input(screw, Osmiridium, 4)
                .input("ringAnySyntheticRubber", 6)
                .input(rotor, Osmiridium)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_PUMP_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ELECTRIC_PUMP_LuV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_UV.stackForm,
                    OreDictUnifier.get(pipeLargeFluid, Naquadah),
                    OreDictUnifier.get(plate, Tritanium, 2),
                    OreDictUnifier.get(screw, Tritanium, 8),
                    OreDictUnifier.get(ring, SiliconeRubber, 16),
                    OreDictUnifier.get(rotor, NaquadahAlloy),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV)
                .input(pipeLargeFluid, Naquadah)
                .input(plate, Tritanium, 2)
                .input(screw, Tritanium, 8)
                .input("ringAnySyntheticRubber", 8)
                .input(rotor, Tritanium)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L))
                .output(ELECTRIC_PUMP_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_PUMP_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // todo UHV Europium pipe, UEV Duranium pipe, UIV Neutronium pipe
        }

        private fun conveyorModuleRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_LuV.getStackForm(2),
                    OreDictUnifier.get(plate, HSSS, 2),
                    OreDictUnifier.get(ring, HSSS, 4),
                    OreDictUnifier.get(round, HSSS, 16),
                    OreDictUnifier.get(screw, HSSS, 4),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 2)),
                arrayOf(SolderingAlloy.getFluid(L), Lubricant.getFluid(250),
                    StyreneButadieneRubber.getFluid(L * 8)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV, 2)
                .input(plate, HSSS)
                .input(ring, HSSS, 4)
                .input(round, HSSS, 8)
                .input(screw, HSSS, 4)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .input("plateAnySyntheticRubber", 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(CONVEYOR_MODULE_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(CONVEYOR_MODULE_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_ZPM.getStackForm(2),
                    OreDictUnifier.get(plate, Osmiridium, 2),
                    OreDictUnifier.get(ring, Osmiridium, 4),
                    OreDictUnifier.get(round, Osmiridium, 16),
                    OreDictUnifier.get(screw, Osmiridium, 4),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 2), Lubricant.getFluid(500), StyreneButadieneRubber.getFluid(L * 16)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(plate, Osmiridium)
                .input(ring, Osmiridium, 4)
                .input(round, Osmiridium, 8)
                .input(screw, Osmiridium, 4)
                .input(cableGtSingle, VanadiumGallium, 2)
                .input("plateAnySyntheticRubber", 10)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(CONVEYOR_MODULE_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(CONVEYOR_MODULE_LuV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELECTRIC_MOTOR_UV.getStackForm(2),
                    OreDictUnifier.get(plate, Tritanium, 2),
                    OreDictUnifier.get(ring, Tritanium, 4),
                    OreDictUnifier.get(round, Tritanium, 16),
                    OreDictUnifier.get(screw, Tritanium, 4),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)),
                arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(1000),
                    StyreneButadieneRubber.getFluid(L * 24), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV, 2)
                .input(plate, Tritanium, 2)
                .input(ring, Tritanium, 8)
                .input(round, Tritanium, 16)
                .input(screw, Tritanium, 8)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .input("plateAnySyntheticRubber", 12)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L))
                .output(CONVEYOR_MODULE_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(CONVEYOR_MODULE_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

        }

        private fun robotArmRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, HSSS, 4),
                    OreDictUnifier.get(gear, HSSS),
                    OreDictUnifier.get(gearSmall, HSSS, 3),
                    ELECTRIC_MOTOR_LuV.getStackForm(2),
                    ELECTRIC_PISTON_LUV.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.IV, 2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.EV, 4),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(250)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, HSSS, 2)
                .input(gear, HSSS)
                .input(gearSmall, HSSS, 2)
                .input(ELECTRIC_MOTOR_LuV, 2)
                .input(ELECTRIC_PISTON_LUV)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(circuit, MarkerMaterials.Tier.IV, 2)
                .input(circuit, MarkerMaterials.Tier.EV, 4)
                .input(cableGtSingle, NiobiumTitanium, 3)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ROBOT_ARM_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ROBOT_ARM_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, Osmiridium, 4),
                    OreDictUnifier.get(gear, Osmiridium),
                    OreDictUnifier.get(gearSmall, Osmiridium, 3),
                    ELECTRIC_MOTOR_ZPM.getStackForm(2),
                    ELECTRIC_PISTON_ZPM.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.IV, 4),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 8), Lubricant.getFluid(500)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, Osmiridium, 2)
                .input(gear, Osmiridium)
                .input(gearSmall, Osmiridium, 2)
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(ELECTRIC_PISTON_ZPM)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(circuit, MarkerMaterials.Tier.IV, 4)
                .input(cableGtSingle, VanadiumGallium, 3)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ROBOT_ARM_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(ROBOT_ARM_LuV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(stickLong, Tritanium, 4),
                    OreDictUnifier.get(gear, Tritanium),
                    OreDictUnifier.get(gearSmall, Tritanium, 3),
                    ELECTRIC_MOTOR_UV.getStackForm(2),
                    ELECTRIC_PISTON_UV.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 4),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 12), Lubricant.getFluid(1000),
                    Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, Tritanium, 4)
                .input(gear, Tritanium)
                .input(gearSmall, Tritanium, 3)
                .input(ELECTRIC_MOTOR_UV, 2)
                .input(ELECTRIC_PISTON_UV)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L))
                .output(ROBOT_ARM_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ROBOT_ARM_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()
        }

        private fun emitterRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, HSSS),
                    ELECTRIC_MOTOR_LuV.stackForm,
                    OreDictUnifier.get(stickLong, Ruridit, 4),
                    QUANTUM_STAR.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 2),
                    OreDictUnifier.get(foil, Palladium, 64),
                    OreDictUnifier.get(foil, Palladium, 32),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 2)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSS)
                .input(ELECTRIC_MOTOR_LuV)
                .input(stickLong, Ruridit, 2)
                .input(QUANTUM_STAR)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(foil, Osmium, 64)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(EMITTER_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(EMITTER_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                    ELECTRIC_MOTOR_ZPM.stackForm,
                    OreDictUnifier.get(stickLong, Osmiridium, 4),
                    QUANTUM_STAR.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    OreDictUnifier.get(foil, Trinium, 64),
                    OreDictUnifier.get(foil, Trinium, 32),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Osmiridium)
                .input(ELECTRIC_MOTOR_ZPM)
                .input(stickLong, NaquadahAlloy, 2)
                .input(QUANTUM_STAR, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(foil, Trinium, 64)
                .input(foil, Trinium, 32)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(EMITTER_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(EMITTER_LuV.stackForm)
                        .EUt(VA[LuV].toLong())
                        .CWUt(4)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                    ELECTRIC_MOTOR_UV.stackForm,
                    OreDictUnifier.get(stickLong, Tritanium, 4),
                    GRAVI_STAR.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV, 2),
                    OreDictUnifier.get(foil, Naquadria, 64),
                    OreDictUnifier.get(foil, Naquadria, 32),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 8), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(ELECTRIC_MOTOR_UV)
                .input(stickLong, EnrichedNaquadahAlloy, 4)
                .input(QUANTUM_STAR, 4)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(foil, Naquadria, 64)
                .input(foil, Naquadria, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Naquadria.getFluid(L))
                .output(EMITTER_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(EMITTER_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()
        }

        private fun sensorRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, HSSS),
                    ELECTRIC_MOTOR_LuV.stackForm,
                    OreDictUnifier.get(plate, Ruridit, 4),
                    QUANTUM_STAR.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 2),
                    OreDictUnifier.get(foil, Palladium, 64),
                    OreDictUnifier.get(foil, Palladium, 32),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 2)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSS)
                .input(ELECTRIC_MOTOR_LuV)
                .input(plate, Ruridit, 2)
                .input(QUANTUM_STAR)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(foil, Palladium, 64)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(SENSOR_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(SENSOR_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                    ELECTRIC_MOTOR_ZPM.stackForm,
                    OreDictUnifier.get(plate, Osmiridium, 4),
                    QUANTUM_STAR.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    OreDictUnifier.get(foil, Trinium, 64),
                    OreDictUnifier.get(foil, Trinium, 32),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Osmiridium)
                .input(ELECTRIC_MOTOR_ZPM)
                .input(plate, NaquadahAlloy, 2)
                .input(QUANTUM_STAR, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(foil, NaquadahEnriched, 64)
                .input(foil, NaquadahEnriched, 32)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(SENSOR_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .stationResearch { r -> r
                    .researchStack(SENSOR_LuV.stackForm)
                    .EUt(VA[LuV].toLong())
                    .CWUt(4)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                    ELECTRIC_MOTOR_UV.stackForm,
                    OreDictUnifier.get(plate, Tritanium, 4),
                    GRAVI_STAR.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV, 2),
                    OreDictUnifier.get(foil, Naquadria, 64),
                    OreDictUnifier.get(foil, Naquadria, 32),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 8), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(ELECTRIC_MOTOR_UV)
                .input(plate, EnrichedNaquadahAlloy, 4)
                .input(QUANTUM_STAR, 4)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(foil, Bedrockium, 64)
                .input(foil, Bedrockium, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Naquadria.getFluid(L))
                .output(SENSOR_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SENSOR_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

        }

        private fun fieldGeneratorRecipes()
        {
            // LuV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, HSSS),
                    OreDictUnifier.get(plate, HSSS, 6),
                    QUANTUM_STAR.stackForm,
                    EMITTER_LuV.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 2),
                    OreDictUnifier.get(wireFine, IndiumTinBariumTitaniumCuprate, 64),
                    OreDictUnifier.get(wireFine, IndiumTinBariumTitaniumCuprate, 64),
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSS)
                .input(plateDouble, HSSS, 4)
                .input(QUANTUM_STAR)
                .input(EMITTER_LuV, 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(wireFine, IndiumTinBariumTitaniumCuprate, 64)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(FIELD_GENERATOR_LuV)
                .EUt(6000) // IV
                .duration(20 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(FIELD_GENERATOR_IV.stackForm)
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, NaquadahAlloy),
                    OreDictUnifier.get(plate, NaquadahAlloy, 6),
                    QUANTUM_STAR.stackForm,
                    EMITTER_ZPM.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    OreDictUnifier.get(wireFine, UraniumRhodiumDinaquadide, 64),
                    OreDictUnifier.get(wireFine, UraniumRhodiumDinaquadide, 64),
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 8)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Osmiridium)
                .input(plateDouble, Osmiridium, 4)
                .input(QUANTUM_STAR, 2)
                .input(EMITTER_ZPM, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(wireFine, UraniumRhodiumDinaquadide, 64)
                .input(wireFine, UraniumRhodiumDinaquadide, 32)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .output(FIELD_GENERATOR_ZPM)
                .EUt(24000) // LuV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(FIELD_GENERATOR_LuV.stackForm)
                        .EUt(VA[LuV].toLong())
                        .CWUt(8)
                }
                .buildAndRegister()

            // UV
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Tritanium),
                    OreDictUnifier.get(plate, Tritanium, 6),
                    GRAVI_STAR.stackForm,
                    EMITTER_UV.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV, 2),
                    OreDictUnifier.get(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64),
                    OreDictUnifier.get(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64),
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
                arrayOf(SolderingAlloy.getFluid(L * 12), Naquadria.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(plateDouble, Tritanium, 4)
                .input(QUANTUM_STAR, 4)
                .input(EMITTER_UV, 2)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64)
                .input(wireFine, EnrichedNaquadahTriniumEuropiumDuranide, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Naquadria.getFluid(L))
                .output(FIELD_GENERATOR_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(FIELD_GENERATOR_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(48)
                }
                .buildAndRegister()

            // UHV

            // UEV

            // UIV

            // UXV

            // OpV

            // MAX

        }

        private fun energyHatchesRecipes()
        {
            // TODO UHV-OpV
        }

        private fun laserHatchesRecipes()
        {
            // TODO

            // 4194304A IV Laser Target Hatch

            // 4194304A IV Laser Source Hatch

            // ---------------------------------------------------------------------------------------------------------

            // 16777216A IV Laser Target Hatch

            // 16777216A IV Laser Source Hatch

        }

    }

}