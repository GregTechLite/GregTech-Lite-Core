package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.IronMagnetic
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.MagnesiumDiboride
import gregtech.api.unification.material.Materials.ManganesePhosphide
import gregtech.api.unification.material.Materials.MercuryBariumCalciumCuprate
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NeodymiumMagnetic
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.SamariumIronArsenicOxide
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SteelMagnetic
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.UraniumTriplatinum
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.gemFlawless
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_EV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_HV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_MV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UIV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_HV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_MV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_MV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_EV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_HV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_MV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.EMITTER_EV
import gregtech.common.items.MetaItems.EMITTER_HV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LV
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_MV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_EV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_HV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_MV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UEV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.QUANTUM_EYE
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.ROBOT_ARM_EV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_MV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_EV
import gregtech.common.items.MetaItems.SENSOR_HV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LV
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_MV
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UIV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.COMPONENT_ASSEMBLY_LINE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimensionallyShiftedSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedNaquadahAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Magnetium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Rhugnor
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumTritaniumActiniumIronSuperhydride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_EV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_HV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_IV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_LV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_LuV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_MV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CIRCUIT_ZPM
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

/**
 * Component Assembly Line (CoAL) Recipe Producer
 *
 * This is a recipe producer for CoAL, just like same machine in GoodGenerator,
 * player can produce 64 count per 1 working but do not have materials reduction
 * because the duration is like 1 count working via AL or Assembler.
 *
 * Here's a raw rule of CoAL recipes:
 * - Duration priority:
 *    - ULV-LV: 15s, MV-HV: 30s, EV-IV: 45s, LuV-ZPM: 60s,
 *      UV-UHV: 75s, UEV-UIV: 90s, UXV-OpV: 105s, MAX: 120s.
 * - ringX, roundX, wireFineX, stickLongX in AL recipes -> liquids in CoAL recipes.
 *
 * For special materials for Halkonite Steel or Magnetium, should transformed it to
 * its raw materials and liquid materials, e.g. Halkonite Steel = Bedrockium + Molten
 * Halkonite Steel.
 *
 * TODO Make it be a recipe producer...
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class ComponentAssemblyLineRecipeProducer
{

    companion object
    {

        fun produce()
        {
            electricMotorRecipes()
            electricPistonRecipes()
            electricPumpRecipes()
            conveyorModuleRecipes()
            robotArmRecipes()
            emitterRecipes()
            sensorRecipes()
            fieldGeneratorRecipes()
            componentCasingsRecipes()
        }

        private fun electricMotorRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Tin, 8) // 64x (2x cableGtSingle) = 64x (1x cableGtDouble) = 8x cableGtHex
                .input(stickLong, Iron, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, IronMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Copper, 16) // 64x (4x wireGtSingle) = 64x (1x wireGtQuadruple) = 16x wireGtHex
                .output(ELECTRIC_MOTOR_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Tin, 8) // 64x (2x cableGtSingle) = 64x (1x cableGtDouble) = 8x cableGtHex
                .input(stickLong, Steel, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, SteelMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Copper, 16) // 64x(4x wireGtSingle) = 64x (1x wireGtQuadruple) = 16x wireGtHex
                .output(ELECTRIC_MOTOR_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Copper, 8) // 64x (2x cableGtSingle) = 64x (1x cableGtDouble) = 8x cableGtHex
                .input(stickLong, Aluminium, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, SteelMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Cupronickel, 32) // 64x (4x wireGtDouble) = 64x (1x wireGtOctal) = 32x wireGtHex
                .output(ELECTRIC_MOTOR_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Silver, 16) // 64x (2x cableGtDouble) = 64x (1x cableGtQuadruple) = 16x cableGtHex
                .input(stickLong, StainlessSteel, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, SteelMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Electrum, 32) // 64x (4x wireGtDouble) = 64x (1x wireGtOctal) = 32x wireGtHex
                .output(ELECTRIC_MOTOR_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Aluminium, 16) // 64x (2x cableGtDouble) = 64x (1x cableGtQuadruple) = 16x cableGtHex
                .input(stickLong, Titanium, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, NeodymiumMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Kanthal, 32) // 64x (4x wireGtDouble) = 64x (1x wireGtOctal) = 32x wireGtHex
                .output(ELECTRIC_MOTOR_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtHex, Tungsten, 16) // 64x (2x cableGtDouble) = 64x (1x cableGtQuadruple) = 16x cableGtHex
                .input(stickLong, TungstenSteel, 64) // 2x (64x stick) = 64x stickLong
                .input(stickLong, NeodymiumMagnetic, 32) // 64x stick = 32x stickLong
                .input(wireGtHex, Graphene, 32) // 64x (4x wireGtDouble) = 64x (1x wireGtOctal) = 32x wireGtHex
                .output(ELECTRIC_MOTOR_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, SamariumMagnetic, 64) // 64x stickLong
                .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Lubricant.getFluid(250 * 64)) // 250 * 64
                .fluidInputs(HSSS.getFluid(L * 64 // stickLongX: [(144L) * 64]
                        + 36 * 2 * 64 // ringX: [(36L) * 2 * 64]
                        + 18 * 4 * 64)) // roundX: [(18L) * 4 * 64]
                .fluidInputs(Ruridit.getFluid(18 * 64 * 64)) // wireFineX: [(18L) * 64 * 64]
                .output(ELECTRIC_MOTOR_LuV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, SamariumMagnetic, 64) // 64x stickLong
                .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Lubricant.getFluid(500 * 64)) // 500 * 64
                .fluidInputs(Osmiridium.getFluid(L * 64 // stickLongX: [(144L) * 64]
                        + 36 * 2 * 64 // ringX: [(36L) * 2 * 64]
                        + 18 * 4 * 64)) //  roundX: [(18L) * 4 * 64]
                .fluidInputs(Europium.getFluid(18 * (64 + 32) * 64)) // wireFineX: [(18L) * (64 + 32) * 64]
                .output(ELECTRIC_MOTOR_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, SamariumMagnetic, 64) // 64x stickLong
                .input(cableGtHex, YttriumBariumCuprate, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Lubricant.getFluid(1000 * 64)) // 1000 * 64
                .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Tritanium.getFluid(L * 2 * 64 // stickLongX: [(144L) * 2 * 64]
                        + 36 * 4 * 64 // ringX: [(36L) * 4 * 64]
                        + 18 * 8 * 64)) // roundX: [(18L) * 8 * 64]
                .fluidInputs(Americium.getFluid(18 * (64 + 64) * 64)) // wireFineX: [(18L) * (64 + 64) * 64 ]
                .output(ELECTRIC_MOTOR_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, ChromiumGermaniumTellurideMagnetic, 64) // 64x stickLong
                .input(cableGtHex, Europium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Lubricant.getFluid(2000 * 64)) // 2000 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(L * 2 * 64 // stickLongX: [(144L) * 2 * 64]
                    + 36 * 4 * 64// ringX: [(36L) * 4 * 64]
                    + 18 * 8 * 64)) // roundX: [(18L) * 8 * 64]
                .fluidInputs(Bedrockium.getFluid(18 * (64 + 64 + 32) * 64)) // wireFineX: [(18L) * (64 + 64 + 32) * 64]
                .output(ELECTRIC_MOTOR_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, ChromiumGermaniumTellurideMagnetic, 64) // 64x stickLong
                .input(cableGtHex, Seaborgium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000 * 64)) // 4000 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(L * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                    + 36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                    + 18 * 16 * 64)) // roundX: [(18L) * 16 * 64]
                .fluidInputs(MetastableHassium.getFluid(18 * (64 + 64 + 64) * 64)) // wireFineX: [(18L) * (64 + 64 + 64) * 64]
                .output(ELECTRIC_MOTOR_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stickLong, Magnetium, 64) // 64x stickLong
                .input(cableGtHex, SuperheavyAlloyA, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000 * 64)) // 8000 * 64
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                    + 36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                    + 18 * 16 * 64)) // roundX: [(18L) * 16 * 64]
                .fluidInputs(Hypogen.getFluid(18 * (64 + 64 + 64 + 32) * 64)) // wireFine: [(18L) * (64 + 64 + 64 + 32) * 64]
                .output(ELECTRIC_MOTOR_UIV, 64)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UIV)
                .buildAndRegister()

            // UXV

            // OpV

            // MAX
        }

        private fun electricPistonRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Steel, 64) // 64x (2x stick) = 64x stickLong
                .input(cableGtHex, Tin, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(plateDouble, Steel, 64) // 64x (3x plate) = 96x plateDouble = (64 + 32)x plateDouble
                .input(plateDouble, Steel, 32)
                .input(gearSmall, Steel, 64) // 64x gearSmall
                .input(ELECTRIC_MOTOR_LV, 64)
                .output(ELECTRIC_PISTON_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Aluminium, 64) // 64x (2x stick) = 64x stickLong
                .input(cableGtHex, Copper, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(plateDouble, Aluminium, 64) // 64x (3x plate) = 96x plateDouble = (64 + 32)x plateDouble
                .input(plateDouble, Aluminium, 32)
                .input(gearSmall, Aluminium, 64) // 64x gearSmall
                .input(ELECTRIC_MOTOR_MV, 64)
                .output(ELECTRIC_PISTON_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, StainlessSteel, 64) // 64x (2x stick) = 64x stickLong
                .input(cableGtHex, Gold, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(plateDouble, StainlessSteel, 64) // 64x (3x plate) = 96x plateDouble = (64 + 32)x plateDouble
                .input(plateDouble, StainlessSteel, 32)
                .input(gearSmall, StainlessSteel, 64) // 64x gearSmall
                .input(ELECTRIC_MOTOR_HV, 64)
                .output(ELECTRIC_PISTON_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Titanium, 64) // 64x (2x stick) = 64x stickLong
                .input(cableGtHex, Aluminium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(plateDouble, Titanium, 64) // 64x (3x plate) = 96x plateDouble = (64 + 32)x plateDouble
                .input(plateDouble, Titanium, 32)
                .input(gearSmall, Titanium, 64) // 64x gearSmall
                .input(ELECTRIC_MOTOR_EV, 64)
                .output(ELECTRIC_PISTON_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, TungstenSteel, 64) // 64x (2x stick) = 64x stickLong
                .input(cableGtHex, Tungsten, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(plateDouble, TungstenSteel, 64) // 64x (3x plate) = 96x plateDouble = (64 + 32)x plateDouble
                .input(plateDouble, TungstenSteel, 32)
                .input(gearSmall, TungstenSteel, 64) // 64x gearSmall
                .input(ELECTRIC_MOTOR_IV, 64)
                .output(ELECTRIC_PISTON_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_LuV, 64)
                .input(plateDouble, HSSS, 32) // 64x plate = 32x plateDouble
                .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Lubricant.getFluid(250 * 64)) // 250 * 64
                .fluidInputs(HSSS.getFluid(36 * 2 * 64 // ringX: [(36L) * 2 * 64]
                        + 18 * 4 * 64 // roundX: [(18L) * 4 * 64]
                        + 72 * 2 * 64 // stickX: [(72L) * 2 * 64]
                        + 576 * 64 // gearX: [(576L) * 64]
                        + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_LUV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_ZPM, 64)
                .input(plateDouble, Osmiridium, 32) // 64x plate = 32x plateDouble
                .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Lubricant.getFluid(500 * 64)) // 500 * 64
                .fluidInputs(Osmiridium.getFluid(36 * 2 * 64 // ringX: [(36L) * 2 * 64]
                    + 18 * 4 * 64 // roundX: [(18L) * 4 * 64]
                    + 72 * 2 * 64 // stickX: [(72L) * 2 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmall: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_UV, 64)
                .input(plateDouble, Tritanium, 64) // 64x (2x plate) = 64x plateDouble
                .input(cableGtHex, YttriumBariumCuprate, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Lubricant.getFluid(1000 * 64)) // 1000 * 64
                .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Tritanium.getFluid(36 * 4 * 64 // ringX: [(36L) * 4 * 64]
                    + 18 * 8 * 64 // roundX: [(18L) * 8 * 64]
                    + 72 * 4 * 64 // stickX: [(72L) * 4 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_UHV, 64)
                .input(plateDouble, Adamantium, 64) // 64x (2x plate) = 64x plateDouble
                .input(cableGtHex, Europium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Lubricant.getFluid(2000 * 64)) // 2000 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(36 * 4 * 64 // ringX: [(36L) * 4 * 64]
                    + 18 * 8 * 64 // roundX: [(18L) * 8 * 64]
                    + 72 * 4 * 64 // stickX: [(72L) * 4 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_UEV, 64)
                .input(plateDouble, CosmicNeutronium, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, CosmicNeutronium, 64)
                .input(cableGtHex, Seaborgium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000 * 64)) // 4000 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                    + 18 * 16 * 64 // roundX: [(18L) * 16 * 64]
                    + 72 * 4 * 64 // stickX: [(72L) * 4 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(ELECTRIC_MOTOR_UIV, 64)
                .input(plateDouble, HeavyQuarkDegenerateMatter, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, HeavyQuarkDegenerateMatter, 64)
                .input(cableGtHex, SuperheavyAlloyA, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000 * 64)) // 8000 * 64
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                        + 18 * 16 * 64 // roundX: [(18L) * 16 * 64]
                        + 72 * 4 * 64 // stickX: [(72L) * 4 * 64]
                        + 576 * 64 // gearX: [(576L) * 64]
                        + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ELECTRIC_PISTON_UIV, 64)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UIV)
                .buildAndRegister()

            // UXV

            // OpV

            // MAX

        }

        private fun electricPumpRecipes()
        {
            // LV-EV
            for (rubber in arrayOf( // ringX: [(36L) * 2 * 64]
                Rubber.getFluid(36 * 2 * 64),
                StyreneButadieneRubber.getFluid(36 * 2 * 64),
                SiliconeRubber.getFluid(36 * 2 * 64),
                PolytetramethyleneGlycolRubber.getFluid(36 * 2 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 2 * 64)))
            {
                // LV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(cableGtHex, Tin, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(pipeHugeFluid, Bronze, 16) // 64x pipeNormalFluid = 16x pipeHugeFluid
                    .input(screw, Tin, 64)
                    .input(rotor, Tin, 64)
                    .input(ELECTRIC_MOTOR_LV, 64)
                    .fluidInputs(rubber)
                    .output(ELECTRIC_PUMP_LV, 64)
                    .EUt(VA[LV].toLong())
                    .duration(15 * SECOND)
                    .tier(LV)
                    .buildAndRegister()

                // MV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(cableGtHex, Copper, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(pipeHugeFluid, Steel, 16) // 64x pipeNormalFluid = 16x pipeHugeFluid
                    .input(screw, Bronze, 64)
                    .input(rotor, Bronze, 64)
                    .input(ELECTRIC_MOTOR_MV,  64)
                    .fluidInputs(rubber)
                    .output(ELECTRIC_PUMP_MV, 64)
                    .EUt(VA[MV].toLong())
                    .duration(30 * SECOND)
                    .tier(MV)
                    .buildAndRegister()

                // HV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(cableGtHex, Gold, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(pipeHugeFluid, StainlessSteel, 16) // 64x pipeNormalFluid = 16x pipeHugeFluid
                    .input(screw, Steel, 64)
                    .input(rotor, Steel, 64)
                    .input(ELECTRIC_MOTOR_HV, 64)
                    .fluidInputs(rubber)
                    .output(ELECTRIC_PUMP_HV, 64)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .tier(HV)
                    .buildAndRegister()

                // EV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(cableGtHex, Aluminium, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(pipeHugeFluid, Titanium, 16) // 64x pipeNormalFluid = 16x pipeHugeFluid
                    .input(screw, StainlessSteel, 64)
                    .input(rotor, StainlessSteel, 64)
                    .input(ELECTRIC_MOTOR_EV, 64)
                    .fluidInputs(rubber)
                    .output(ELECTRIC_PUMP_EV, 64)
                    .EUt(VA[EV].toLong())
                    .duration(45 * SECOND)
                    .tier(EV)
                    .buildAndRegister()
            }

            // IV
            for (rubber in arrayOf( // ringX: [(36L) * 2 * 64]
                StyreneButadieneRubber.getFluid(36 * 2 * 64),
                SiliconeRubber.getFluid(36 * 2 * 64),
                PolytetramethyleneGlycolRubber.getFluid(36 * 2 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 2 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(cableGtHex, Tungsten, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(pipeHugeFluid, TungstenSteel, 16) // 64x pipeNormalFluid = 16x pipeHugeFluid
                    .input(screw, TungstenSteel, 64)
                    .input(rotor, TungstenSteel, 64)
                    .input(ELECTRIC_MOTOR_IV, 64)
                    .fluidInputs(rubber)
                    .output(ELECTRIC_PUMP_IV , 64)
                    .EUt(VA[IV].toLong())
                    .duration(45 * SECOND)
                    .tier(IV)
                    .buildAndRegister()
            }

            // LuV
            for (rubber in arrayOf( // ringX: [(36L) * 4 * 64]
                StyreneButadieneRubber.getFluid(36 * 4 * 64),
                SiliconeRubber.getFluid(36 * 4 * 64),
                PolytetramethyleneGlycolRubber.getFluid(36 * 4 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 4 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_LuV, 64)
                    .input(plateDouble, HSSS, 32) // 64x plate = 32x plateDouble
                    .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(Lubricant.getFluid(250 * 64)) // 250 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(HSSS.getFluid(18 * 4 * 64 // screwX: [(18L) * 4 * 64]
                        + 576 * 64)) // rotorX: [(576) * 64]
                    .fluidInputs(NiobiumTitanium.getFluid(432 * 64)) // pipeNormalFluidX: [(432L) * 64]
                    .output(ELECTRIC_PUMP_LuV, 64)
                    .EUt(VA[LuV].toLong())
                    .duration(1 * MINUTE)
                    .tier(LuV)
                    .buildAndRegister()
            }

            // ZPM
            for (rubber in arrayOf( // ringX: [(36L) * 6 * 64]
                StyreneButadieneRubber.getFluid(36 * 6 * 64),
                SiliconeRubber.getFluid(36 * 6 * 64),
                PolytetramethyleneGlycolRubber.getFluid(36 * 6 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 6 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_ZPM, 64)
                    .input(plateDouble, Osmiridium, 32) // 64x plate = 32x plateDouble
                    .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(Lubricant.getFluid(500 * 64)) // 500 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Osmiridium.getFluid(18 * 4 * 64 // screwX: [(18L) * 4 * 64]
                        + 576 * 64)) // rotorX: [(576L) * 64]
                    .fluidInputs(Iridium.getFluid(432 * 64)) // pipeNormalFluidX: [(432L) * 64]
                    .output(ELECTRIC_PUMP_ZPM, 64)
                    .EUt(VA[ZPM].toLong())
                    .duration(1 * MINUTE)
                    .tier(ZPM)
                    .buildAndRegister()
            }

            // UV
            for (rubber in arrayOf( // ringX: [(36L) * 8 * 64]
                StyreneButadieneRubber.getFluid(36 * 8 * 64),
                SiliconeRubber.getFluid(36 * 8 * 64),
                PolytetramethyleneGlycolRubber.getFluid(36 * 8 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 8 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_UV, 64)
                    .input(plateDouble, Tritanium, 64) // 64x (2x plate) = 64x plateDouble
                    .input(cableGtHex, YttriumBariumCuprate, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                    .fluidInputs(Lubricant.getFluid(1000 * 64)) // 1000 * 64
                    .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Tritanium.getFluid(18 * 8 * 64 // screwX: [(18L) * 8 * 64]
                        + 576 * 64)) // rotorX: [(576L) * 64]
                    .fluidInputs(Naquadah.getFluid(864 * 64)) // pipeLargeFluidX: [(864L) * 64]
                    .output(ELECTRIC_PUMP_UV, 64)
                    .EUt(VA[UV].toLong())
                    .duration(1 * MINUTE + 15 * SECOND)
                    .tier(UV)
                    .buildAndRegister()
            }

            // UHV
            for (rubber in arrayOf( // ringX: [(36L) * 12 * 64]
                PolytetramethyleneGlycolRubber.getFluid(36 * 12 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 12 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_UHV, 64)
                    .input(plateDouble, Adamantium, 64) // 64x (2x plate) = 64x plateDouble
                    .input(cableGtHex, Europium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                    .fluidInputs(Lubricant.getFluid(2000 * 64)) // 2000 * 64
                    .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Adamantium.getFluid(18 * 8 * 64 // screwX: [(18L) * 8 * 64]
                            + 576 * 64)) // rotorX: [(576L) * 64]
                    .fluidInputs(Europium.getFluid(864 * 64)) // pipeLargeFluidX: [(864L) * 64]
                    .output(ELECTRIC_PUMP_UHV, 64)
                    .EUt(VA[UHV].toLong())
                    .duration(1 * MINUTE + 15 * SECOND)
                    .tier(UHV)
                    .buildAndRegister()
            }

            // UEV
            for (rubber in arrayOf( // ringX: [(36L) * 24 * 64]
                PolytetramethyleneGlycolRubber.getFluid(36 * 24 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 24 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_UEV, 64)
                    .input(plateDouble, CosmicNeutronium, 64) // 64x (4x plate) = 64x (2x plateDouble)
                    .input(plateDouble, CosmicNeutronium, 64)
                    .input(cableGtHex, Seaborgium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                    .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000 * 64)) // 4000 * 64
                    .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                    .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(CosmicNeutronium.getFluid(18 * 8 * 64 // screwX: [(18L) * 8 * 64]
                        + 576 * 64)) // rotorX: [(576L) * 64]
                    .fluidInputs(Duranium.getFluid(864 * 2 * 64)) // pipeLargeFluidX: [(864L) * 2 * 64]
                    .output(ELECTRIC_PUMP_UEV, 64)
                    .EUt(VA[UEV].toLong())
                    .duration(1 * MINUTE + 30 * SECOND)
                    .buildAndRegister()
            }

            // UIV
            for (rubber in arrayOf( // ringX: [(36L) * 32 * 64]
                PolytetramethyleneGlycolRubber.getFluid(36 * 32 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(36 * 32 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(ELECTRIC_MOTOR_UIV, 64)
                    .input(plateDouble, HeavyQuarkDegenerateMatter, 64) // 64x (4x plate) = 64x (2x plateDouble)
                    .input(plateDouble, HeavyQuarkDegenerateMatter, 64)
                    .input(cableGtHex, SuperheavyAlloyA, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                    .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000 * 64)) // 8000 * 64
                    .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                    .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(18 * 8 * 64 // screwX: [(18L) * 8 * 64]
                            + 576 * 64)) // rotorX: [(576L) * 64]
                    .fluidInputs(Neutronium.getFluid(864 * 2 * 64)) // pipeLargeFluidX: [(864L) * 2 * 64]
                    .output(ELECTRIC_PUMP_UIV, 64)
                    .EUt(VA[UIV].toLong())
                    .duration(1 * MINUTE + 30 * SECOND)
                    .buildAndRegister()
            }

            // UXV

            // OpV

            // MAX
        }

        private fun conveyorModuleRecipes()
        {
            // LV-EV
            for (rubber in arrayOf( // plateX: [(144L) * 6 * 64]
                Rubber.getFluid(L * 6 * 64),
                StyreneButadieneRubber.getFluid(L * 6 * 64),
                SiliconeRubber.getFluid(L * 6 * 64),
                PolytetramethyleneGlycolRubber.getFluid(L * 6 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 6 * 64)))
            {
                // LV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(cableGtHex, Tin, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(ELECTRIC_MOTOR_LV, 64)
                    .input(ELECTRIC_MOTOR_LV, 64)
                    .fluidInputs(rubber)
                    .output(CONVEYOR_MODULE_LV, 64)
                    .EUt(VA[LV].toLong())
                    .duration(15 * SECOND)
                    .tier(LV)
                    .buildAndRegister()

                // MV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(cableGtHex, Copper, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(ELECTRIC_MOTOR_MV, 64)
                    .input(ELECTRIC_MOTOR_MV, 64)
                    .fluidInputs(rubber)
                    .output(CONVEYOR_MODULE_MV, 64)
                    .EUt(VA[MV].toLong())
                    .duration(30 * SECOND)
                    .tier(MV)
                    .buildAndRegister()

                // HV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(cableGtHex, Gold, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(ELECTRIC_MOTOR_HV, 64)
                    .input(ELECTRIC_MOTOR_HV, 64)
                    .fluidInputs(rubber)
                    .output(CONVEYOR_MODULE_HV, 64)
                    .EUt(VA[HV].toLong())
                    .duration(30 * SECOND)
                    .tier(HV)
                    .buildAndRegister()

                // EV
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(cableGtHex, Aluminium, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(ELECTRIC_MOTOR_EV, 64)
                    .input(ELECTRIC_MOTOR_EV, 64)
                    .fluidInputs(rubber)
                    .output(CONVEYOR_MODULE_EV, 64)
                    .EUt(VA[EV].toLong())
                    .duration(45 * SECOND)
                    .tier(EV)
                    .buildAndRegister()
            }

            // IV
            for (rubber in arrayOf( // plateX: [(144L) * 6 * 64]
                StyreneButadieneRubber.getFluid(L * 6 * 64),
                SiliconeRubber.getFluid(L * 6 * 64),
                PolytetramethyleneGlycolRubber.getFluid(L * 6 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 6 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(cableGtHex, Tungsten, 4) // 64x cableGtSingle = 4x cableGtHex
                    .input(ELECTRIC_MOTOR_IV, 64)
                    .input(ELECTRIC_MOTOR_IV, 64)
                    .fluidInputs(rubber)
                    .output(CONVEYOR_MODULE_IV, 64)
                    .EUt(VA[IV].toLong())
                    .duration(45 * SECOND)
                    .tier(IV)
                    .buildAndRegister()
            }

            // LuV
            for (rubber in arrayOf( // plateX: [(144L) * 8 * 64]
                StyreneButadieneRubber.getFluid(L * 8 * 64),
                SiliconeRubber.getFluid(L * 8 * 64),
                PolytetramethyleneGlycolRubber.getFluid(L * 8 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 8 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_LuV, 64)
                    .input(ELECTRIC_MOTOR_LuV, 64)
                    .input(plateDouble, HSSS, 32) // 64x plate = 32x plateDouble
                    .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(Lubricant.getFluid(250 * 64)) // 250 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(HSSS.getFluid(36 * 4 * 64 // ringX: [(36L) * 4 * 64]
                        + 18 * 8 * 64 // roundX: [(18L) * 8 * 64]
                        + 18 * 4 * 64)) // screwX: [(18L) * 4 * 64]
                    .output(CONVEYOR_MODULE_LuV, 64)
                    .EUt(VA[LuV].toLong())
                    .duration(1 * MINUTE)
                    .tier(LuV)
                    .buildAndRegister()
            }

            // ZPM
            for (rubber in arrayOf( // plateX: [(144L) * 10 * 64]
                StyreneButadieneRubber.getFluid(L * 10 * 64),
                SiliconeRubber.getFluid(L * 10 * 64),
                PolytetramethyleneGlycolRubber.getFluid(L * 10 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 10 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_ZPM, 64)
                    .input(ELECTRIC_MOTOR_ZPM, 64)
                    .input(plateDouble, Osmiridium, 32) // 64x plate = 32x plateDouble
                    .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(Lubricant.getFluid(500 * 64)) // 500 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Osmiridium.getFluid(36 * 4 * 64 // ringX: [(36L) * 4 * 64]
                        + 18 * 8 * 64 // roundX: [(18L) * 8 * 64]
                        + 18 * 4 * 64)) // screwX: [(18L) * 4 * 64]
                    .output(CONVEYOR_MODULE_ZPM, 64)
                    .EUt(VA[ZPM].toLong())
                    .duration(1 * MINUTE)
                    .tier(ZPM)
                    .buildAndRegister()
            }

            // UV
            for (rubber in arrayOf( // plateX: [(144L) * 12 * 64]
                StyreneButadieneRubber.getFluid(L * 12 * 64),
                SiliconeRubber.getFluid(L * 12 * 64),
                PolytetramethyleneGlycolRubber.getFluid(L * 12 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 12 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_UV, 64)
                    .input(ELECTRIC_MOTOR_UV, 64)
                    .input(plateDouble, Tritanium, 64) // 64x (2x plate) = 64x plateDouble
                    .input(cableGtHex, YttriumBariumCuprate, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                    .fluidInputs(Lubricant.getFluid(1000 * 64)) // 64000
                    .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Tritanium.getFluid(36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                        + 18 * 16 * 64 // roundX: [(18L) * 16 * 64]
                        + 18 * 8 * 64)) // screwX: [(18L) * 8 * 64]
                    .output(CONVEYOR_MODULE_UV, 64)
                    .EUt(VA[UV].toLong())
                    .duration(1 * MINUTE + 15 * SECOND)
                    .tier(UV)
                    .buildAndRegister()
            }

            // UHV
            for (rubber in arrayOf( // plateX: [(144L) * 16 * 64]
                PolytetramethyleneGlycolRubber.getFluid(L * 16 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 16 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_UHV, 64)
                    .input(ELECTRIC_MOTOR_UHV, 64)
                    .input(plateDouble, Adamantium, 64) // 64x (2x plate) = 64x plateDouble
                    .input(cableGtHex, Europium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                    .fluidInputs(Lubricant.getFluid(2000 * 64)) // 2000 * 64
                    .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(Adamantium.getFluid(36 * 8 * 64 // ringX: [(36L) * 8 * 64]
                        + 18 * 16 * 64 // roundX: [(18L) * 16 * 64]
                        + 18 * 8 * 64)) // screwX: [(18L) * 8 * 64]
                    .output(CONVEYOR_MODULE_UHV, 64)
                    .EUt(VA[UHV].toLong())
                    .duration(1 * MINUTE + 15 * SECOND)
                    .tier(UHV)
                    .buildAndRegister()
            }

            // UEV
            for (rubber in arrayOf( // plateX: [(144L) * 32 * 64]
                PolytetramethyleneGlycolRubber.getFluid(L * 32 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 32 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_UEV, 64)
                    .input(ELECTRIC_MOTOR_UEV, 64)
                    .input(plateDouble, CosmicNeutronium, 64) // 64x (4x plate) = 64x (2x plateDouble)
                    .input(plateDouble, CosmicNeutronium, 64)
                    .input(cableGtHex, Seaborgium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                    .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000 * 64)) // 4000 * 64
                    .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                    .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(CosmicNeutronium.getFluid(36 * 16 * 64 // ringX: [(36L) * 16 * 64]
                        + 18 * 32 * 64 // roundX: [(18L) * 32 * 64]
                        + 18 * 8 * 64)) // screwX: [(18L) * 8 * 64]
                    .output(CONVEYOR_MODULE_UEV, 64)
                    .EUt(VA[UEV].toLong())
                    .duration(1 * MINUTE + 30 * SECOND)
                    .tier(UEV)
                    .buildAndRegister()
            }

            // UIV
            for (rubber in arrayOf( // plateX: [(144L) * 48 * 64]
                PolytetramethyleneGlycolRubber.getFluid(L * 48 * 64),
                PolyphosphonitrileFluoroRubber.getFluid(L * 48 * 64)))
            {
                COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(ELECTRIC_MOTOR_UIV, 64)
                    .input(ELECTRIC_MOTOR_UIV, 64)
                    .input(plateDouble, HeavyQuarkDegenerateMatter, 64) // 64x (4x plate) = 64x (2x plateDouble)
                    .input(plateDouble, HeavyQuarkDegenerateMatter, 64)
                    .input(cableGtHex, SuperheavyAlloyA, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                    .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                    .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000 * 64)) // 8000 * 64
                    .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                    .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                    .fluidInputs(rubber)
                    .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(36 * 16 * 64 // ringX: [(36L) * 16 * 64]
                        + 18 * 32 * 64 // roundX: [(18L) * 32 * 64]
                        + 18 * 8 * 64)) // screwX: [(18L) * 8 * 64]
                    .output(CONVEYOR_MODULE_UIV, 64)
                    .EUt(VA[UIV].toLong())
                    .duration(1 * MINUTE + 30 * SECOND)
                    .tier(UIV)
                    .buildAndRegister()
            }

            // UXV

            // OpV

            // MAX

        }

        private fun robotArmRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(cableGtHex, Tin, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .input(stickLong, Steel, 64) // 64x (2x stick) = 64x stickLong
                .input(ELECTRIC_MOTOR_LV, 64)
                .input(ELECTRIC_MOTOR_LV, 64)
                .input(ELECTRIC_PISTON_LV, 64)
                .input(WRAP_CIRCUIT_LV, 4) // 64x circuitX = 4x wrappedCircuitX
                .output(ROBOT_ARM_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(cableGtHex, Copper, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .input(stickLong, Aluminium, 64) // 64x (2x stick) = 64x stickLong
                .input(ELECTRIC_MOTOR_MV, 64)
                .input(ELECTRIC_MOTOR_MV, 64)
                .input(ELECTRIC_PISTON_MV, 64)
                .input(WRAP_CIRCUIT_MV, 4) // 64x circuitX = 4x wrappedCircuitX
                .output(ROBOT_ARM_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(cableGtHex, Gold, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .input(stickLong, StainlessSteel, 64) // 64x (2x stick) = 64x stickLong
                .input(ELECTRIC_MOTOR_HV, 64)
                .input(ELECTRIC_MOTOR_HV, 64)
                .input(ELECTRIC_PISTON_HV, 64)
                .input(WRAP_CIRCUIT_HV, 4) // 64x circuitX = 4x wrappedCircuitX
                .output(ROBOT_ARM_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(cableGtHex, Aluminium, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .input(stickLong, Titanium, 64)
                .input(ELECTRIC_MOTOR_EV, 64)
                .input(ELECTRIC_MOTOR_EV, 64)
                .input(ELECTRIC_PISTON_EV, 64)
                .input(WRAP_CIRCUIT_EV, 4) // 64x circuitX = 4x wrappedCircuitX
                .output(ROBOT_ARM_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(cableGtHex, Tungsten, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .input(stickLong, TungstenSteel, 64)
                .input(ELECTRIC_MOTOR_IV, 64)
                .input(ELECTRIC_MOTOR_IV, 64)
                .input(ELECTRIC_PISTON_IV, 64)
                .input(WRAP_CIRCUIT_IV, 4) // 64x circuitX = 4x wrappedCircuitX
                .output(ROBOT_ARM_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_LuV, 64)
                .input(ELECTRIC_MOTOR_LuV, 64)
                .input(ELECTRIC_PISTON_LUV, 64)
                .input(WRAP_CIRCUIT_LuV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_IV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_EV, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, NiobiumTitanium, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Lubricant.getFluid(250 * 64)) // 250 * 64
                .fluidInputs(HSSS.getFluid(144 * 2 * 64 // stickLongX: [(144L) * 2 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ROBOT_ARM_LuV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_ZPM, 64)
                .input(ELECTRIC_MOTOR_ZPM, 64)
                .input(ELECTRIC_PISTON_ZPM, 64)
                .input(WRAP_CIRCUIT_ZPM, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_LuV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_IV, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, VanadiumGallium, 12) // 64x (3x cableGtSingle) = 192x cableGtSingle = 12x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Lubricant.getFluid(500 * 64)) // 500 * 64
                .fluidInputs(Osmiridium.getFluid(144 * 2 * 64 // stickLong: [(144L) * 2 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 2 * 64)) // gearSmallX: [(144L) * 2 * 64]
                .output(ROBOT_ARM_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_UV, 64)
                .input(ELECTRIC_MOTOR_UV, 64)
                .input(ELECTRIC_PISTON_UV, 64)
                .input(WRAP_CIRCUIT_UV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_ZPM, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_LuV, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, YttriumBariumCuprate, 16) //  64x (cableGtQuadruple) = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 12 * 64)) // 1728 * 64
                .fluidInputs(Lubricant.getFluid(1000 * 64)) // 1000 * 64
                .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Tritanium.getFluid(144 * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 3 * 64)) // gearSmall [(144L) * 3 * 64]
                .output(ROBOT_ARM_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_UHV, 64)
                .input(ELECTRIC_MOTOR_UHV, 64)
                .input(ELECTRIC_PISTON_UHV, 64)
                .input(WRAP_CIRCUIT_UHV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_UV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_ZPM, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, Europium, 16) //  64x (cableGtQuadruple) = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(Lubricant.getFluid(2000 * 64)) // 2000 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(144 * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                    + 576 * 64 // gearX: [(576L) * 64]
                    + 144 * 3 * 64)) // gearSmallX: [(144L) * 3 * 64]
                .output(ROBOT_ARM_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_UEV, 64)
                .input(ELECTRIC_MOTOR_UEV, 64)
                .input(ELECTRIC_PISTON_UEV, 64)
                .input(WRAP_CIRCUIT_UEV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_UHV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_UV, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, Seaborgium, 16) //  64x (cableGtQuadruple) = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000 * 64)) // 4000 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(144 * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                    + 576 * 2 * 64 // gearX: [(576L) * 2 * 64]
                    + 144 * 3 * 64)) // gearSmallX: [(144L) * 3 * 64]
                .output(ROBOT_ARM_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ELECTRIC_MOTOR_UIV, 64)
                .input(ELECTRIC_MOTOR_UIV, 64)
                .input(ELECTRIC_PISTON_UIV, 64)
                .input(WRAP_CIRCUIT_UIV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(WRAP_CIRCUIT_UEV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(WRAP_CIRCUIT_UHV, 16) // 64x (4x circuitX) = 16x wrappedCircuitX
                .input(cableGtHex, SuperheavyAlloyA, 16) //  64x (cableGtQuadruple) = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 64 * 64)) // 9216 * 64
                .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000 * 64)) // 8000 * 64
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(144 * 4 * 64 // stickLongX: [(144L) * 4 * 64]
                        + 576 * 2 * 64 // gearX: [(576L) * 2 * 64]
                        + 144 * 3 * 64)) // gearSmallX: [(144L) * 3 * 64]
                .output(ROBOT_ARM_UIV, 64)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UIV)
                .buildAndRegister()

            // UXV

            // OpV

            // MAX

        }

        private fun emitterRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stickLong, Brass, 64) // 64x (4x stick) = 64x (2x stickLong)
                .input(stickLong, Brass, 64)
                .input(cableGtHex, Tin, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(WRAP_CIRCUIT_LV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(gem, Quartzite, 64)
                .output(EMITTER_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stickLong, Electrum, 64) // 64x (4x stick) = 64x (2x stickLong)
                .input(stickLong, Electrum, 64)
                .input(cableGtHex, Copper, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(WRAP_CIRCUIT_MV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(gemFlawless, Emerald, 64)
                .output(EMITTER_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stickLong, Chrome, 64) // 64x (4x stick) = 64x (2x stickLong)
                .input(stickLong, Chrome, 64)
                .input(cableGtHex, Gold, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(WRAP_CIRCUIT_HV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .inputs(ItemStack(Items.ENDER_EYE, 64))
                .output(EMITTER_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stickLong, Platinum, 64) // 64x (4x stick) = 64x (2x stickLong)
                .input(stickLong, Platinum, 64)
                .input(cableGtHex, Aluminium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(WRAP_CIRCUIT_EV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(QUANTUM_EYE, 64)
                .output(EMITTER_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stickLong, Iridium, 64) // 64x (4x stick) = 64x (2x stickLong)
                .input(stickLong, Iridium, 64)
                .input(cableGtHex, Tungsten, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .input(WRAP_CIRCUIT_IV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(QUANTUM_STAR, 64)
                .output(EMITTER_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_LuV, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_LuV, 8) // // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HSSS.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Ruridit.getFluid(144 * 2 * 64)) // stickLongX: [(144L) * 2 * 64]
                .fluidInputs(Osmium.getFluid(36 * 64 * 64)) // foilX: [(36L) * 64 * 64]
                .output(EMITTER_LuV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_ZPM, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_ZPM, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Osmiridium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(NaquadahAlloy.getFluid(144 * 2 * 64)) // stickLongX: [(144L) * 2 * 64]
                .fluidInputs(Trinium.getFluid(36 * (64 + 32) * 64)) // foilX: [(36L) * (64 + 32) * 64]
                .output(EMITTER_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_UV, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_UV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, YttriumBariumCuprate, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Naquadria.getFluid(L * 64 // 144 * 64
                    + 36 * (64 + 64) * 64)) // foilX: [(36L) * (64 + 64) * 64]
                .fluidInputs(Tritanium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144 * 4 * 64)) // stickLongX: [(144L) * 4 * 64]
                .output(EMITTER_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_UHV, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UHV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Europium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 12 * 64)) // 1728 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Dubnium.getFluid(144 * 4 * 64)) // stickLongX: [(144L) * 4 * 64]
                .fluidInputs(Vibranium.getFluid(36 * (64 + 64 + 32) * 64)) // foilX: [(36L) * (64 + 64 + 32) * 64]
                .output(EMITTER_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_UEV, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UEV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Seaborgium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Taranium.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Bohrium.getFluid(144 * 8 * 64)) // stickLongX: [(144L) * 8 * 64]
                .fluidInputs(MetastableFlerovium.getFluid(36 * (64 + 64 + 64) * 64)) // foilX: [(36L) * (64 + 64 + 64) * 64]
                .output(EMITTER_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ELECTRIC_MOTOR_UIV, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UIV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, SuperheavyAlloyA, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Shirabon.getFluid(144 * 8 * 64)) // stickLongX: [(144L) * 8 * 64]
                .fluidInputs(Rhugnor.getFluid(36 * (64 + 64 + 64 + 32) * 64)) // foilX: [(36L) * (64 + 64 + 64 + 32) * 64]
                .output(EMITTER_UIV, 64)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UIV)
                .buildAndRegister()

            // UXV

            // OpV

            // MAX
        }

        private fun sensorRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stickLong, Brass, 32) // 64x stick = 32x stickLong
                .input(plateDouble, Steel, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, Steel, 64)
                .input(WRAP_CIRCUIT_LV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(gem, Quartzite, 64)
                .output(SENSOR_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stickLong, Electrum, 32) // 64x stick = 32x stickLong
                .input(plateDouble, Aluminium, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, Aluminium, 64)
                .input(WRAP_CIRCUIT_MV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(gemFlawless, Emerald, 64)
                .output(SENSOR_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stickLong, Chrome, 32) // 64x stick = 32x stickLong
                .input(plateDouble, StainlessSteel, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, StainlessSteel, 64)
                .input(WRAP_CIRCUIT_HV, 4) // 64x circuitX = 4x wrappedCircuitX
                .inputs(ItemStack(Items.ENDER_EYE, 64))
                .output(SENSOR_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stickLong, Platinum, 32) // 64x stick = 32x stickLong
                .input(plateDouble, Titanium, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, Titanium, 64)
                .input(WRAP_CIRCUIT_EV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(QUANTUM_EYE, 64)
                .output(SENSOR_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stickLong, Iridium, 32) // 64x stick = 32x stickLong
                .input(plateDouble, TungstenSteel, 64) // 64x (4x plate) = 64x (2x plateDouble)
                .input(plateDouble, TungstenSteel, 64)
                .input(WRAP_CIRCUIT_IV, 4) // 64x circuitX = 4x wrappedCircuitX
                .input(QUANTUM_STAR, 64)
                .output(SENSOR_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_LuV, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_LuV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HSSS.getFluid(288 * 64)) // frameGt: [(288L) * 64]
                .fluidInputs(Ruridit.getFluid(144 * 2 * 64)) // plateX: [(144L) * 2 * 64]
                .fluidInputs(Palladium.getFluid(36 * 64 * 64)) // foilX: [(36L) * 64 * 64]
                .output(SENSOR_LuV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_ZPM, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_ZPM, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Osmiridium.getFluid(288 * 64)) // frameGt: [(288L) * 64]
                .fluidInputs(NaquadahAlloy.getFluid(144 * 2 * 64)) // plateX: [(144L) * 2 * 64]
                .fluidInputs(NaquadahEnriched.getFluid(36 * (64 + 32) * 64)) // foilX: [(36L) * (64 + 32) * 64]
                .output(SENSOR_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_UV, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(WRAP_CIRCUIT_UV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, YttriumBariumCuprate, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Tritanium.getFluid(288 * 64)) // frameGt: [(288L) * 64]
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144 * 4 * 64)) // plateX: [(144L) * 4 * 64]
                .fluidInputs(Rutherfordium.getFluid(36 * (64 + 64) * 64)) // foilX: [(36L) * (64 + 64) * 64]
                .output(SENSOR_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_UHV, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UHV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Europium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 12 * 64)) // 1728 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Dubnium.getFluid(L * 4 * 64)) // plateX: [(144L) * 4 * 64]
                .fluidInputs(Neutronium.getFluid(36 * (64 + 64 + 32) * 64)) // foilX: [(36L) * (64 + 64 + 32) * 64]
                .output(SENSOR_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_UEV, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UEV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Seaborgium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(288 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Bohrium.getFluid(L * 8 * 64)) // plateX: [(144L) * 8 * 64]
                .fluidInputs(Infinity.getFluid(36 * (64 + 64 + 64) * 64)) // foilX: [(36L) * (64 + 64 + 64) * 64]
                .output(SENSOR_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(ELECTRIC_MOTOR_UIV, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(WRAP_CIRCUIT_UIV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, SuperheavyAlloyA, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(CarbonNanotube.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 2 * 64)) // frameGtX: [(288L) * 64]
                .fluidInputs(Shirabon.getFluid(L * 8 * 64)) // plateX: [(144L) * 8 * 64]
                .fluidInputs(HalkoniteSteel.getFluid(36 * (64 + 64 + 64 + 32) * 64)) // foilX: [(36L) * (64 + 64 + 64 + 32) * 64]
                .fluidInputs(Bedrockium.getFluid(36 * (64 + 64 + 64 + 32) * 64))
                .output(SENSOR_UIV, 64)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UIV)
                .buildAndRegister()

            // UXV

            // OpV

            // MAX

        }

        private fun fieldGeneratorRecipes()
        {
            // LV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .inputs(ItemStack(Items.ENDER_PEARL, 64))
                .input(plateDouble, Steel, 64) // 64x (2x plate) = 64x plateDouble
                .input(WRAP_CIRCUIT_LV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .fluidInputs(ManganesePhosphide.getFluid(288 * 4 * 64)) // wireGtQuadruple: [(288L) * 4 * 64]
                .output(FIELD_GENERATOR_LV, 64)
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .tier(LV)
                .buildAndRegister()

            // MV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .inputs(ItemStack(Items.ENDER_EYE, 64))
                .input(plateDouble, Aluminium, 64) // 64x (2x plate) = 64x plateDouble
                .input(WRAP_CIRCUIT_MV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .fluidInputs(MagnesiumDiboride.getFluid(288 * 4 * 64)) // wireGtQuadruple: [(288L) * 4 * 64]
                .output(FIELD_GENERATOR_MV, 64)
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .tier(MV)
                .buildAndRegister()

            // HV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(QUANTUM_EYE, 64)
                .input(plateDouble, StainlessSteel, 64) // 64x (2x plate) = 64x plateDouble
                .input(WRAP_CIRCUIT_HV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .fluidInputs(MercuryBariumCalciumCuprate.getFluid(288 * 4 * 64)) // wireGtQuadruple: [(288L) * 4 * 64]
                .output(FIELD_GENERATOR_HV, 64)
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .tier(HV)
                .buildAndRegister()

            // EV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .inputs(ItemStack(Items.NETHER_STAR, 64))
                .input(plateDouble, Titanium, 64) // 64x (2x plateDouble)
                .input(plateDouble, Titanium, 64)
                .input(WRAP_CIRCUIT_EV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .fluidInputs(UraniumTriplatinum.getFluid(288 * 4 * 64)) // wireGtQuadruple: [(288L) * 4 * 64]
                .output(FIELD_GENERATOR_EV, 64)
                .EUt(VA[EV].toLong())
                .duration(45 * SECOND)
                .tier(EV)
                .buildAndRegister()

            // IV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(QUANTUM_STAR, 64)
                .input(plateDouble, TungstenSteel, 64) // 64x (2x plateDouble)
                .input(plateDouble, TungstenSteel, 64)
                .input(WRAP_CIRCUIT_IV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .fluidInputs(SamariumIronArsenicOxide.getFluid(288 * 4 * 64)) // wireGtQuadruple: [(288L) * 4 * 64]
                .output(FIELD_GENERATOR_IV, 64)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .tier(IV)
                .buildAndRegister()

            // LuV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(QUANTUM_STAR, 64)
                .input(EMITTER_LuV, 64)
                .input(EMITTER_LuV, 64)
                .input(WRAP_CIRCUIT_LuV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, NiobiumTitanium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(HSSS.getFluid(288 * 64 // frameGt: [(288L) * 64]
                    + 288 * 4 * 64)) // plateDoubleX: [(288L) * 4 * 64]
                .fluidInputs(IndiumTinBariumTitaniumCuprate.getFluid(18 * 64 * 64)) // wireFineX: [(18L) * 64 * 64]
                .output(FIELD_GENERATOR_LuV, 64)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE)
                .tier(LuV)
                .buildAndRegister()

            // ZPM
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(EMITTER_ZPM, 64)
                .input(EMITTER_ZPM, 64)
                .input(WRAP_CIRCUIT_ZPM, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, VanadiumGallium, 8) // 64x (2x cableGtSingle) = 64x cableGtDouble = 8x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 8 * 64)) // 1152 * 64
                .fluidInputs(Osmiridium.getFluid(288 * 64 // frameGtX: [(288L) * 64]
                        + 288 * 4 * 64)) // plateDoubleX: [(288L) * 4 * 64]
                .fluidInputs(UraniumRhodiumDinaquadide.getFluid(18 * (64 + 32) * 64)) // wireFineX: [(18L) * (64 + 32) * 64]
                .output(FIELD_GENERATOR_ZPM, 64)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .tier(ZPM)
                .buildAndRegister()

            // UV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(QUANTUM_STAR, 64)
                .input(EMITTER_UV, 64)
                .input(EMITTER_UV, 64)
                .input(WRAP_CIRCUIT_UV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, YttriumBariumCuprate, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 12 * 64)) // 1728 * 64
                .fluidInputs(Naquadria.getFluid(L * 64)) // 144 * 64
                .fluidInputs(Tritanium.getFluid(288 * 64 // frameGtX: [(288L) * 64]
                        + 288 * 4 * 64)) // plateDoubleX: [(288L) * 4 * 64]
                .fluidInputs(EnrichedNaquadahTriniumEuropiumDuranide.getFluid(18 * (64 + 64) * 64)) // wireFineX: [(18L) * (64 + 64) * 64]
                .output(FIELD_GENERATOR_UV, 64)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UV)
                .buildAndRegister()

            // UHV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(GRAVI_STAR, 64)
                .input(EMITTER_UHV, 64)
                .input(EMITTER_UHV, 64)
                .input(WRAP_CIRCUIT_UHV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Europium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64)) // 2304 * 64
                .fluidInputs(Taranium.getFluid(L * 2 * 64)) // 288 * 64
                .fluidInputs(Adamantium.getFluid(288 * 64 // frameGtX: [(288L) * 64]
                    + 288 * 4 * 64)) // plateDoubleX: [(288L) * 4 * 64]
                .fluidInputs(RutheniumTriniumAmericiumNeutronate.getFluid(18 * (64 + 64 + 32) * 64)) // wireFineX: [(18L) * (64 + 64 + 32) * 64]
                .output(FIELD_GENERATOR_UHV, 64)
                .EUt(VA[UHV].toLong())
                .duration(1 * MINUTE + 15 * SECOND)
                .tier(UHV)
                .buildAndRegister()

            // UEV
            COMPONENT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(GRAVI_STAR, 64)
                .input(GRAVI_STAR, 64)
                .input(EMITTER_UEV, 64)
                .input(EMITTER_UEV, 64)
                .input(WRAP_CIRCUIT_UEV, 8) // 64x (2x circuitX) = 8x wrappedCircuitX
                .input(cableGtHex, Seaborgium, 16) // 64x (4x cableGtSingle) = 64x cableGtQuadruple = 16x cableGtHex
                .fluidInputs(MutatedLivingSolder.getFluid(L * 32 * 64)) // 4608 * 64
                .fluidInputs(MetastableOganesson.getFluid(L * 4 * 64)) // 576 * 64
                .fluidInputs(Fullerene.getFluid(L * 64)) // 144 * 64
                .fluidInputs(CosmicNeutronium.getFluid(288 * 64 // frameGtX: [(288L) * 64]
                    + 288 * 8 * 64)) // plateDoubleX: [(288L) * 8 * 64]
                .fluidInputs(VibraniumTritaniumActiniumIronSuperhydride.getFluid(18 * (64 + 64 + 64) * 64)) // wireFineX: [(18L) * (64 + 64 + 64) * 64]
                .output(FIELD_GENERATOR_UEV, 64)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .tier(UEV)
                .buildAndRegister()

            // UIV

            // UXV

            // OpV

            // MAX
        }

        private fun componentCasingsRecipes()
        {

        }

    }

}