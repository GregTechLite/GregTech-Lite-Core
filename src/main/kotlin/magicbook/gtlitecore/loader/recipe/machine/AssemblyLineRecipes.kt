package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.RESEARCH_STATION_RECIPES
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.recipes.builders.ResearchRecipeBuilder
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.DrillingFluid
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SodiumPotassium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
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
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtech.common.items.MetaItems.TOOL_DATA_STICK
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.VOLTAGE_COIL_LuV
import gregtech.common.items.MetaItems.VOLTAGE_COIL_UV
import gregtech.common.items.MetaItems.VOLTAGE_COIL_ZPM
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.HULL
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ReneN5
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumPotassiumEutatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Stellite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumTungstenCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockComponentAssemblyCasing
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_IV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_LuV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MINING_DRONE_ZPM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UHV
import magicbook.gtlitecore.loader.recipe.machine.AssemblyLineRecipes.Companion.researchStack
import net.minecraft.item.ItemStack
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval

@Suppress("MISSING_DEPENDENCY_CLASS")
class AssemblyLineRecipes
{

    companion object
    {

        fun init()
        {
            componentRecipes()
            miningDroneRecipes()
            energyHatchesRecipes()
            laserHatchesRecipes()
            coALCasingRecipes()
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
                    r.researchStack(ELECTRIC_MOTOR_IV)
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
                    r.researchStack(ELECTRIC_MOTOR_LuV)
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
                    r.researchStack(ELECTRIC_MOTOR_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, ChromiumGermaniumTellurideMagnetic)
                .input(stickLong, Adamantium, 2)
                .input(ring, Adamantium, 4)
                .input(round, Adamantium, 8)
                .input(wireFine, Bedrockium, 64)
                .input(wireFine, Bedrockium, 64)
                .input(wireFine, Bedrockium, 32)
                .input(cableGtSingle, Europium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(2000))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(ELECTRIC_MOTOR_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_MOTOR_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

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
            //         r.researchStack(ELECTRIC_MOTOR_UHV)
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
                    r.researchStack(ELECTRIC_PISTON_IV)
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
                    r.researchStack(ELECTRIC_PISTON_LUV)
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
                    r.researchStack(ELECTRIC_PISTON_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UHV)
                .input(plate, Adamantium, 2)
                .input(ring, Adamantium, 4)
                .input(round, Adamantium, 8)
                .input(stick, Adamantium, 4)
                .input(gear, Adamantium)
                .input(gearSmall, Adamantium, 2)
                .input(cableGtSingle, Europium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(2000))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(ELECTRIC_PISTON_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_PISTON_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

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
                    r.researchStack(ELECTRIC_PUMP_IV)
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
                    r.researchStack(ELECTRIC_PUMP_LuV)
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
                    r.researchStack(ELECTRIC_PUMP_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UHV)
                .input(pipeLargeFluid, Europium)
                .input(plate, Adamantium, 2)
                .input(screw, Adamantium, 8)
                .input("ringAnyAdvancedSyntheticRubber", 12)
                .input(rotor, Adamantium)
                .input(cableGtSingle, Europium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(2000))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(ELECTRIC_PUMP_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ELECTRIC_PUMP_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // UEV

            // todo UEV Duranium pipe, UIV Neutronium pipe
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
                    r.researchStack(CONVEYOR_MODULE_IV)
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
                    r.researchStack(CONVEYOR_MODULE_LuV)
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
                    r.researchStack(CONVEYOR_MODULE_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UHV, 2)
                .input(plate, Adamantium, 2)
                .input(ring, Adamantium, 8)
                .input(round, Adamantium, 16)
                .input(screw, Adamantium, 8)
                .input(cableGtSingle, Europium, 2)
                .input("plateAnyAdvancedSyntheticRubber", 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(2000))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(CONVEYOR_MODULE_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(CONVEYOR_MODULE_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
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
                    r.researchStack(ROBOT_ARM_IV)
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
                    r.researchStack(ROBOT_ARM_LuV)
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
                    r.researchStack(ROBOT_ARM_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, Adamantium, 4)
                .input(gear, Adamantium)
                .input(gearSmall, Adamantium, 3)
                .input(ELECTRIC_MOTOR_UHV, 2)
                .input(ELECTRIC_PISTON_UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 16))
                .fluidInputs(Lubricant.getFluid(2000))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(ROBOT_ARM_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ROBOT_ARM_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // UEV
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
                    r.researchStack(EMITTER_IV)
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
                    r.researchStack(EMITTER_LuV)
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
                    r.researchStack(EMITTER_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Adamantium)
                .input(ELECTRIC_MOTOR_UHV)
                .input(stickLong, Dubnium, 4)
                .input(GRAVI_STAR)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .input(foil, Vibranium, 64)
                .input(foil, Vibranium, 64)
                .input(foil, Vibranium, 32)
                .input(cableGtSingle, Europium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(EMITTER_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(EMITTER_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // UEV
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
                    r.researchStack(SENSOR_IV)
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
                    .researchStack(SENSOR_LuV)
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
                .input(foil, Rutherfordium, 64)
                .input(foil, Rutherfordium, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Naquadria.getFluid(L))
                .output(SENSOR_UV)
                .EUt(100_000) // ZPM
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SENSOR_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Adamantium)
                .input(ELECTRIC_MOTOR_UHV)
                .input(plate, Dubnium, 4)
                .input(GRAVI_STAR)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .input(foil, Neutronium, 64)
                .input(foil, Neutronium, 64)
                .input(foil, Neutronium, 32)
                .input(cableGtSingle, Europium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(SENSOR_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(SENSOR_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // UEV

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
                    r.researchStack(FIELD_GENERATOR_IV)
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
                    r.researchStack(FIELD_GENERATOR_LuV)
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
                    r.researchStack(FIELD_GENERATOR_ZPM)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(48)
                }
                .buildAndRegister()

            // UHV
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Adamantium)
                .input(plateDouble, Adamantium, 4)
                .input(GRAVI_STAR)
                .input(EMITTER_UHV, 2)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .input(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
                .input(wireFine, RutheniumTriniumAmericiumNeutronate, 64)
                .input(wireFine, RutheniumTriniumAmericiumNeutronate, 32)
                .input(cableGtSingle, Europium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 16))
                .fluidInputs(Taranium.getFluid(L * 2))
                .output(FIELD_GENERATOR_UHV)
                .EUt(400_000) // UV
                .duration(20 * SECOND)
                .stationResearch { r ->
                    r.researchStack(FIELD_GENERATOR_UV)
                        .EUt(VA[UV].toLong())
                        .CWUt(24)
                }
                .buildAndRegister()

            // UEV

            // UIV

            // UXV

            // OpV

            // MAX

        }

        private fun miningDroneRecipes()
        {
            // LuV Mining Drone
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, RhodiumPlatedPalladium)
                .input(plate, RhodiumPlatedPalladium, 8)
                .input(plateDouble, Uranium235, 4)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(toolHeadDrill, HSSE, 6)
                .input(EMITTER_LuV, 2)
                .input(SENSOR_LuV, 2)
                .input(ROBOT_ARM_LuV, 2)
                .input(rotor, RhodiumPlatedPalladium, 4)
                .input(cableGtSingle, NiobiumTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(DrillingFluid.getFluid(2000))
                .output(MINING_DRONE_LuV)
                .EUt(VA[LuV].toLong())
                .duration(30 * SECOND)
                .scannerResearch { r ->
                    r.researchStack(MINING_DRONE_IV.stackForm)
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM Mining Drone
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, NaquadahAlloy)
                .input(plate, NaquadahAlloy, 8)
                .input(plateDouble, Plutonium239, 4)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(toolHeadDrill, NaquadahAlloy, 6)
                .input(EMITTER_ZPM, 2)
                .input(SENSOR_ZPM, 2)
                .input(ROBOT_ARM_ZPM, 2)
                .input(rotor, NaquadahAlloy, 4)
                .input(cableGtSingle, VanadiumGallium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(DrillingFluid.getFluid(4000))
                .output(MINING_DRONE_ZPM)
                .EUt(VA[ZPM].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(MINING_DRONE_LuV.stackForm)
                        .EUt(VA[LuV].toLong())
                        .CWUt(4)
                }
                .buildAndRegister()

            // UV Mining Drone
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Darmstadtium)
                .input(plate, Darmstadtium, 12)
                .input(plateDouble, Naquadah, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(toolHeadDrill, Duranium, 8)
                .input(EMITTER_UV, 2)
                .input(SENSOR_UV, 2)
                .input(ROBOT_ARM_UV, 2)
                .input(rotor, Darmstadtium, 6)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(DrillingFluid.getFluid(8000))
                .output(MINING_DRONE_UV)
                .EUt(VA[UV].toLong())
                .duration(30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(MINING_DRONE_ZPM.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(8)
                }
                .buildAndRegister()

            // TODO UHV-MAX Mining Drones
        }

        private fun energyHatchesRecipes()
        {
            // LuV Energy Hatch
            GTRecipeHandler.removeRecipesByInputs(SCANNER_RECIPES,
                TOOL_DATA_STICK.stackForm, ENERGY_INPUT_HATCH[IV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[LuV].stackForm,
                    OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4),
                    HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV),
                    VOLTAGE_COIL_LuV.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(6000), SolderingAlloy.getFluid(L * 5)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[LuV])
                .input(cableGtSingle, NiobiumTitanium, 2)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(VOLTAGE_COIL_LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 5))
                .fluidInputs(SodiumPotassium.getFluid(4000))
                .output(ENERGY_INPUT_HATCH[LuV])
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .scannerResearch { r -> r
                    .researchStack(ENERGY_INPUT_HATCH[IV])
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE) }
                .buildAndRegister()

            // LuV Dynamo Hatch
            GTRecipeHandler.removeRecipesByInputs(SCANNER_RECIPES,
                TOOL_DATA_STICK.stackForm, ENERGY_OUTPUT_HATCH[IV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[LuV].stackForm,
                    OreDictUnifier.get(spring, NiobiumTitanium, 4),
                    HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV),
                    VOLTAGE_COIL_LuV.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(6000), SolderingAlloy.getFluid(L * 5)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[LuV])
                .input(spring, NiobiumTitanium, 2)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(VOLTAGE_COIL_LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 5))
                .fluidInputs(SodiumPotassium.getFluid(4000))
                .output(ENERGY_OUTPUT_HATCH[LuV])
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .scannerResearch { r -> r
                    .researchStack(ENERGY_OUTPUT_HATCH[IV])
                    .EUt(VA[HV].toLong())
                    .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM Energy Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_ORB.stackForm, ENERGY_INPUT_HATCH[LuV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[ZPM].stackForm,
                    OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM),
                    VOLTAGE_COIL_ZPM.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(8000), SolderingAlloy.getFluid(L * 10)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[ZPM])
                .input(cableGtSingle, VanadiumGallium, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(VOLTAGE_COIL_ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .fluidInputs(SodiumPotassium.getFluid(8000))
                .output(ENERGY_INPUT_HATCH[ZPM])
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .scannerResearch { r -> r
                    .researchStack(ENERGY_INPUT_HATCH[LuV])
                    .EUt(VA[IV].toLong())
                    .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM Dynamo Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_ORB.stackForm, ENERGY_OUTPUT_HATCH[LuV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[ZPM].stackForm,
                    OreDictUnifier.get(spring, VanadiumGallium, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM),
                    VOLTAGE_COIL_ZPM.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(8000), SolderingAlloy.getFluid(L * 10)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[ZPM])
                .input(spring, VanadiumGallium, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(VOLTAGE_COIL_ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .fluidInputs(SodiumPotassium.getFluid(8000))
                .output(ENERGY_OUTPUT_HATCH[ZPM])
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .scannerResearch { r -> r
                    .researchStack(ENERGY_OUTPUT_HATCH[LuV])
                    .EUt(VA[IV].toLong())
                    .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV Energy Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_MODULE.stackForm, ENERGY_INPUT_HATCH[ZPM].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[UV].stackForm,
                    OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV),
                    VOLTAGE_COIL_UV.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(10000), SolderingAlloy.getFluid(L * 20)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UV])
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(VOLTAGE_COIL_UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(SodiumPotassium.getFluid(12000))
                .output(ENERGY_INPUT_HATCH[UV])
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_INPUT_HATCH[ZPM])
                    .EUt(VA[ZPM].toLong())
                    .CWUt(16)
                }
                .buildAndRegister()

            // UV Dynamo Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_MODULE.stackForm, ENERGY_OUTPUT_HATCH[ZPM].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[UV].stackForm,
                    OreDictUnifier.get(spring, YttriumBariumCuprate, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV),
                    VOLTAGE_COIL_UV.getStackForm(2)),
                arrayOf(SodiumPotassium.getFluid(10000), SolderingAlloy.getFluid(L * 20)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UV])
                .input(spring, YttriumBariumCuprate, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(VOLTAGE_COIL_UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(SodiumPotassium.getFluid(12000))
                .output(ENERGY_OUTPUT_HATCH[UV])
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_OUTPUT_HATCH[ZPM])
                    .EUt(VA[ZPM].toLong())
                    .CWUt(16)
                }
                .buildAndRegister()

            // UHV Energy Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_MODULE.stackForm, ENERGY_INPUT_HATCH[UV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[UHV].stackForm,
                    OreDictUnifier.get(cableGtSingle, Europium, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UHV),
                    OreDictUnifier.get(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 2)),
                arrayOf(SodiumPotassium.getFluid(12000), SolderingAlloy.getFluid(L * 40)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UHV])
                .input(cableGtSingle, Europium, 4)
                .input(NANO_PIC_CHIP, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(VOLTAGE_COIL_UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(SodiumPotassiumEutatic.getFluid(16000))
                .output(ENERGY_INPUT_HATCH[UHV])
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_INPUT_HATCH[UV])
                    .EUt(VA[UV].toLong())
                    .CWUt(24)
                }
                .buildAndRegister()

            // UHV Dynamo Hatch
            GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
                TOOL_DATA_MODULE.stackForm, ENERGY_OUTPUT_HATCH[UV].stackForm)
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(HULL[UHV].stackForm,
                    OreDictUnifier.get(spring, Europium, 4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UHV),
                    OreDictUnifier.get(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 2)),
                arrayOf(SodiumPotassium.getFluid(12000), SolderingAlloy.getFluid(L * 40)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UHV])
                .input(spring, Europium, 4)
                .input(NANO_PIC_CHIP, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(VOLTAGE_COIL_UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(SodiumPotassiumEutatic.getFluid(16000))
                .output(ENERGY_OUTPUT_HATCH[UHV])
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_OUTPUT_HATCH[UV])
                    .EUt(VA[UV].toLong())
                    .CWUt(24)
                }
                .buildAndRegister()

            // UEV Energy Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UEV])
                .input(cableGtSingle, Seaborgium, 4)
                .input(NANO_PIC_CHIP, 4)
                .input(circuit, MarkerMaterials.Tier.UEV)
                .input(VOLTAGE_COIL_UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 80))
                .fluidInputs(SodiumPotassiumEutatic.getFluid(18000))
                .output(ENERGY_INPUT_HATCH[UEV])
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_INPUT_HATCH[UHV])
                    .EUt(VA[UHV].toLong())
                    .CWUt(32)
                }
                .buildAndRegister()

            // UEV Dynamo Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UEV])
                .input(spring, Seaborgium, 4)
                .input(NANO_PIC_CHIP, 4)
                .input(circuit, MarkerMaterials.Tier.UEV)
                .input(VOLTAGE_COIL_UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 80))
                .fluidInputs(SodiumPotassiumEutatic.getFluid(18000))
                .output(ENERGY_OUTPUT_HATCH[UEV])
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .stationResearch { r -> r
                    .researchStack(ENERGY_OUTPUT_HATCH[UHV])
                    .EUt(VA[UHV].toLong())
                    .CWUt(32)
                }
                .buildAndRegister()

            // TODO UIV-OpV
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

        private fun coALCasingRecipes()
        {
            // LuV CoAL Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, RhodiumPlatedPalladium)
                .input(plateDense, RhodiumPlatedPalladium, 6)
                .input(ROBOT_ARM_LuV, 8)
                .input(ELECTRIC_PISTON_LUV, 10)
                .input(ELECTRIC_MOTOR_LuV, 16)
                .input(gear, RhodiumPlatedPalladium, 4)
                .input(gearSmall, RhodiumPlatedPalladium, 16)
                .input(cableGtQuadruple, NiobiumTitanium, 8)
                .input(circuit, MarkerMaterials.Tier.LuV, 8)
                .input(circuit, MarkerMaterials.Tier.IV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 24))
                .fluidInputs(Lubricant.getFluid(4000))
                .fluidInputs(Stellite.getFluid(L * 12))
                .fluidInputs(TitaniumTungstenCarbide.getFluid(L * 6))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.LuV, 4))
                .EUt(VA[LuV].toLong())
                .duration(32 * SECOND)
                .scannerResearch { b ->
                    b.researchStack(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.IV))
                        .EUt(VA[HV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // ZPM CoAL Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, NaquadahAlloy)
                .input(plateDense, NaquadahAlloy, 6)
                .input(ROBOT_ARM_ZPM, 8)
                .input(ELECTRIC_PISTON_ZPM, 10)
                .input(ELECTRIC_MOTOR_ZPM, 16)
                .input(gear, NaquadahAlloy, 4)
                .input(gearSmall, NaquadahAlloy, 16)
                .input(cableGtQuadruple, VanadiumGallium, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 8)
                .input(circuit, MarkerMaterials.Tier.LuV, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 24))
                .fluidInputs(Lubricant.getFluid(4000))
                .fluidInputs(HastelloyN.getFluid(L * 12))
                .fluidInputs(Zeron100.getFluid(L * 8))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.ZPM, 4))
                .EUt(VA[ZPM].toLong())
                .duration(32 * SECOND)
                .scannerResearch { b ->
                    b.researchStack(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.LuV))
                        .EUt(VA[IV].toLong())
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // UV CoAL Casing
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Darmstadtium)
                .input(plateDense, Darmstadtium, 6)
                .input(ROBOT_ARM_UV, 8)
                .input(ELECTRIC_PISTON_UV, 10)
                .input(ELECTRIC_MOTOR_UV, 16)
                .input(gear, Darmstadtium, 4)
                .input(gearSmall, Darmstadtium, 16)
                .input(cableGtQuadruple, YttriumBariumCuprate, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 24))
                .fluidInputs(Lubricant.getFluid(4000))
                .fluidInputs(ReneN5.getFluid(L * 12))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 8))
                .outputs(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.UV, 4))
                .EUt(VA[UV].toLong())
                .duration(32 * SECOND)
                .stationResearch { r ->
                    r.researchStack(GTLiteMetaBlocks.COMPONENT_ASSEMBLY_CASING.getItemVariant(BlockComponentAssemblyCasing.ComponentCasingType.ZPM))
                        .EUt(VA[ZPM].toLong())
                        .CWUt(6)
                }
                .buildAndRegister()

            // TODO UHV-MAX CoAL Casing
        }

        /**
         * Extension function of [ResearchRecipeBuilder.researchStack], allowed this function
         * accepted [MetaItem.MetaValueItem] (original value must accept [ItemStack]).
         */
        @ScheduledForRemoval(inVersion = "Next GTCEu Update")
        @Deprecated(message = "This extension function is scheduled for removal when GTCEu merged the convert functions for RecipeBuilder.",
                    replaceWith = ReplaceWith("gregtech.api.recipes.builders.ResearchRecipeBuilder.researchStack"))
        @JvmStatic
        private fun <T : ResearchRecipeBuilder<T>> ResearchRecipeBuilder<T>.researchStack(metaValueItem: MetaItem<*>.MetaValueItem): ResearchRecipeBuilder<T>
        {
            val stack: ItemStack = metaValueItem.stackForm
            return if (!stack.isEmpty) this.researchStack(stack) else this
        }

        /**
         * Extension function of [ResearchRecipeBuilder.researchStack], allowed this function
         * accepted [MetaTileEntity] (original value must accept [ItemStack]).
         */
        @ScheduledForRemoval(inVersion = "Next GTCEu Update")
        @Deprecated(message = "This extension function is scheduled for removal when GTCEu merged the convert functions for RecipeBuilder.",
            replaceWith = ReplaceWith("gregtech.api.recipes.builders.ResearchRecipeBuilder.researchStack"))
        @JvmStatic
        private fun <T : ResearchRecipeBuilder<T>> ResearchRecipeBuilder<T>.researchStack(metaTileEntity: MetaTileEntity): ResearchRecipeBuilder<T>
        {
            val stack: ItemStack = metaTileEntity.stackForm
            return if (!stack.isEmpty) this.researchStack(stack) else this
        }

    }

}