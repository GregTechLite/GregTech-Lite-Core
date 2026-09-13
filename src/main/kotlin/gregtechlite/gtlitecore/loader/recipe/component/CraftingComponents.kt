package gregtechlite.gtlitecore.loader.recipe.component

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.SamariumMagnetic
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadBuzzSaw
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.COMPONENT_GRINDER_TUNGSTEN
import gregtech.common.items.MetaItems.FLUID_REGULATOR_EV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_HV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_IV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_LUV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_LV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_MV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_UV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_ZPM
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AxinoFusedRedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LutetiumManganeseGermanium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Omnium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumCobalt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SelfInteractingDarkMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumBariumCalciumCuprate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.glass
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONVEYOR_MODULE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PUMP_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMITTER_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FIELD_GENERATOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SENSOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UXV

object CraftingComponents
{
    // @formatter:off

    /**
     * Fluid Regulator
     *
     * | Tier     | Item                   |
     * |----------|------------------------|
     * | ULV (0)  | Bronze Tiny Fluid Pipe |
     * | LV (1)   | LV Fluid Regulator     |
     * | MV (2)   | MV Fluid Regulator     |
     * | HV (3)   | HV Fluid Regulator     |
     * | EV (4)   | EV Fluid Regulator     |
     * | IV (5)   | IV Fluid Regulator     |
     * | LuV (6)  | LuV Fluid Regulator    |
     * | ZPM (7)  | ZPM Fluid Regulator    |
     * | UV (8)   | UV Fluid Regulator     |
     * | UHV (9)  | UHV Fluid Regulator    |
     * | UEV (10) | UEV Fluid Regulator    |
     * | UIV (11) | UIV Fluid Regulator    |
     * | UXV (12) | UXV Fluid Regulator    |
     * | OpV (13) | OpV Fluid Regulator    |
     * | MAX (14) | MAX Fluid Regulator    |
     */
    @JvmStatic
    val FLUID_REGULATOR = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(pipeTinyFluid, Bronze),
        LV  to FLUID_REGULATOR_LV.stack(),
        MV  to FLUID_REGULATOR_MV.stack(),
        HV  to FLUID_REGULATOR_HV.stack(),
        EV  to FLUID_REGULATOR_EV.stack(),
        IV  to FLUID_REGULATOR_IV.stack(),
        LuV to FLUID_REGULATOR_LUV.stack(),
        ZPM to FLUID_REGULATOR_ZPM.stack(),
        UV  to FLUID_REGULATOR_UV.stack(),
        UHV to FLUID_REGULATOR_UV.stack(),
        UEV to FLUID_REGULATOR_UV.stack(),
        UIV to FLUID_REGULATOR_UV.stack(),
        UXV to FLUID_REGULATOR_UV.stack(),
        OpV to FLUID_REGULATOR_UV.stack(),
        MAX to FLUID_REGULATOR_UV.stack()))

    /**
     * Gear
     *
     * | Tier     | Material                 |
     * |----------|--------------------------|
     * | ULV (0)  | Wrought Iron             |
     * | LV (1)   | Steel                    |
     * | MV (2)   | Aluminium                |
     * | HV (3)   | Stainless Steel          |
     * | EV (4)   | Titanium                 |
     * | IV (5)   | Tungsten Steel           |
     * | LuV (6)  | Rhodium Plated Palladium |
     * | ZPM (7)  | Naquadah Alloy           |
     * | UV (8)   | Darmstadtium             |
     * | UHV (9)  | Neutronium               |
     * | UEV (10) | Vibranium                |
     * | UIV (11) | Shirabon                 |
     * | UXV (12) | Creon                    |
     * | OpV (13) | Black Dwarf Matter       |
     * | MAX (14) | Axino-Fused Red Matter   |
     */
    @JvmStatic
    val GEAR = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(gear, WroughtIron),
        LV  to UnificationEntry(gear, Steel),
        MV  to UnificationEntry(gear, Aluminium),
        HV  to UnificationEntry(gear, StainlessSteel),
        EV  to UnificationEntry(gear, Titanium),
        IV  to UnificationEntry(gear, TungstenSteel),
        LuV to UnificationEntry(gear, RhodiumPlatedPalladium),
        ZPM to UnificationEntry(gear, NaquadahAlloy),
        UV  to UnificationEntry(gear, Darmstadtium),
        UHV to UnificationEntry(gear, Neutronium),
        UEV to UnificationEntry(gear, Vibranium),
        UIV to UnificationEntry(gear, Shirabon),
        UXV to UnificationEntry(gear, Creon),
        OpV to UnificationEntry(gear, BlackDwarfMatter),
        MAX to UnificationEntry(gear, AxinoFusedRedMatter)))

    /**
     * Small Gear
     *
     * | Tier     | Material                 |
     * |----------|--------------------------|
     * | ULV (0)  | Wrought Iron             |
     * | LV (1)   | Steel                    |
     * | MV (2)   | Aluminium                |
     * | HV (3)   | Stainless Steel          |
     * | EV (4)   | Titanium                 |
     * | IV (5)   | Tungsten Steel           |
     * | LuV (6)  | Rhodium Plated Palladium |
     * | ZPM (7)  | Naquadah Alloy           |
     * | UV (8)   | Darmstadtium             |
     * | UHV (9)  | Neutronium               |
     * | UEV (10) | Vibranium                |
     * | UIV (11) | Shirabon                 |
     * | UXV (12) | Creon                    |
     * | OpV (13) | Black Dwarf Matter       |
     * | MAX (14) | Axino-Fused Red Matter   |
     */
    @JvmStatic
    val GEAR_SMALL = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(gearSmall, WroughtIron),
        LV  to UnificationEntry(gearSmall, Steel),
        MV  to UnificationEntry(gearSmall, Aluminium),
        HV  to UnificationEntry(gearSmall, StainlessSteel),
        EV  to UnificationEntry(gearSmall, Titanium),
        IV  to UnificationEntry(gearSmall, TungstenSteel),
        LuV to UnificationEntry(gearSmall, RhodiumPlatedPalladium),
        ZPM to UnificationEntry(gearSmall, NaquadahAlloy),
        UV  to UnificationEntry(gearSmall, Darmstadtium),
        UHV to UnificationEntry(gearSmall, Neutronium),
        UEV to UnificationEntry(gearSmall, Vibranium),
        UIV to UnificationEntry(gearSmall, Shirabon),
        UXV to UnificationEntry(gearSmall, Creon),
        OpV to UnificationEntry(gearSmall, BlackDwarfMatter),
        MAX to UnificationEntry(gearSmall, AxinoFusedRedMatter)))

    /**
     * Dense Plate
     *
     * | Tier     | Material                 |
     * |----------|--------------------------|
     * | ULV (0)  | Wrought Iron             |
     * | LV (1)   | Steel                    |
     * | MV (2)   | Aluminium                |
     * | HV (3)   | Stainless Steel          |
     * | EV (4)   | Titanium                 |
     * | IV (5)   | Tungsten Steel           |
     * | LuV (6)  | Rhodium Plated Palladium |
     * | ZPM (7)  | Naquadah Alloy           |
     * | UV (8)   | Darmstadtium             |
     * | UHV (9)  | Neutronium               |
     * | UEV (10) | Vibranium                |
     * | UIV (11) | Shirabon                 |
     * | UXV (12) | Creon                    |
     * | OpV (13) | Black Dwarf Matter       |
     * | MAX (14) | Axino-Fused Red Matter   |
     */
    @JvmStatic
    val PLATE_DENSE = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(plateDense, WroughtIron),
        LV  to UnificationEntry(plateDense, Steel),
        MV  to UnificationEntry(plateDense, Aluminium),
        HV  to UnificationEntry(plateDense, StainlessSteel),
        EV  to UnificationEntry(plateDense, Titanium),
        IV  to UnificationEntry(plateDense, TungstenSteel),
        LuV to UnificationEntry(plateDense, RhodiumPlatedPalladium),
        ZPM to UnificationEntry(plateDense, NaquadahAlloy),
        UV  to UnificationEntry(plateDense, Darmstadtium),
        UHV to UnificationEntry(plateDense, Neutronium),
        UEV to UnificationEntry(plateDense, Vibranium),
        UIV to UnificationEntry(plateDense, Shirabon),
        UXV to UnificationEntry(plateDense, Creon),
        OpV to UnificationEntry(plateDense, BlackDwarfMatter),
        MAX to UnificationEntry(plateDense, AxinoFusedRedMatter)))

    /**
     * Stick
     *
     * | Tier     | Material                 |
     * |----------|--------------------------|
     * | ULV (0)  | Wrought Iron             |
     * | LV (1)   | Steel                    |
     * | MV (2)   | Aluminium                |
     * | HV (3)   | Stainless Steel          |
     * | EV (4)   | Titanium                 |
     * | IV (5)   | Tungsten Steel           |
     * | LuV (6)  | Rhodium Plated Palladium |
     * | ZPM (7)  | Naquadah Alloy           |
     * | UV (8)   | Darmstadtium             |
     * | UHV (9)  | Neutronium               |
     * | UEV (10) | Vibranium                |
     * | UIV (11) | Shirabon                 |
     * | UXV (12) | Creon                    |
     * | OpV (13) | Black Dwarf Matter       |
     * | MAX (14) | Axino-Fused Red Matter   |
     */
    @JvmStatic
    val STICK = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(stick, WroughtIron),
        LV  to UnificationEntry(stick, Steel),
        MV  to UnificationEntry(stick, Aluminium),
        HV  to UnificationEntry(stick, StainlessSteel),
        EV  to UnificationEntry(stick, Titanium),
        IV  to UnificationEntry(stick, TungstenSteel),
        LuV to UnificationEntry(stick, RhodiumPlatedPalladium),
        ZPM to UnificationEntry(stick, NaquadahAlloy),
        UV  to UnificationEntry(stick, Darmstadtium),
        UHV to UnificationEntry(stick, Neutronium),
        UEV to UnificationEntry(stick, Vibranium),
        UIV to UnificationEntry(stick, Shirabon),
        UXV to UnificationEntry(stick, Creon),
        OpV to UnificationEntry(stick, BlackDwarfMatter),
        MAX to UnificationEntry(stick, AxinoFusedRedMatter)))

    /**
     * Long Stick
     *
     * | Tier     | Material                 |
     * |----------|--------------------------|
     * | ULV (0)  | Wrought Iron             |
     * | LV (1)   | Steel                    |
     * | MV (2)   | Aluminium                |
     * | HV (3)   | Stainless Steel          |
     * | EV (4)   | Titanium                 |
     * | IV (5)   | Tungsten Steel           |
     * | LuV (6)  | Rhodium Plated Palladium |
     * | ZPM (7)  | Naquadah Alloy           |
     * | UV (8)   | Darmstadtium             |
     * | UHV (9)  | Neutronium               |
     * | UEV (10) | Vibranium                |
     * | UIV (11) | Shirabon                 |
     * | UXV (12) | Creon                    |
     * | OpV (13) | Black Dwarf Matter       |
     * | MAX (14) | Axino-Fused Red Matter   |
     */
    @JvmStatic
    val STICK_LONG = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(stickLong, WroughtIron),
        LV  to UnificationEntry(stickLong, Steel),
        MV  to UnificationEntry(stickLong, Aluminium),
        HV  to UnificationEntry(stickLong, StainlessSteel),
        EV  to UnificationEntry(stickLong, Titanium),
        IV  to UnificationEntry(stickLong, TungstenSteel),
        LuV to UnificationEntry(stickLong, RhodiumPlatedPalladium),
        ZPM to UnificationEntry(stickLong, NaquadahAlloy),
        UV  to UnificationEntry(stickLong, Darmstadtium),
        UHV to UnificationEntry(stickLong, Neutronium),
        UEV to UnificationEntry(stickLong, Vibranium),
        UIV to UnificationEntry(stickLong, Shirabon),
        UXV to UnificationEntry(stickLong, Creon),
        OpV to UnificationEntry(stickLong, BlackDwarfMatter),
        MAX to UnificationEntry(stickLong, AxinoFusedRedMatter)))

    /**
     * Small Spring
     *
     * | Tier     | Material                    |
     * |----------|-----------------------------|
     * | ULV (0)  | Lead                        |
     * | LV (1)   | Tin                         |
     * | MV (2)   | Copper                      |
     * | HV (3)   | Gold                        |
     * | EV (4)   | Aluminium                   |
     * | IV (5)   | Tungsten                    |
     * | LuV (6)  | Niobium Titanium            |
     * | ZPM (7)  | Vanadium Gallium            |
     * | UV (8)   | Yttrium Barium Cuprate      |
     * | UHV (9)  | Europium                    |
     * | UEV (10) | Seaborgium                  |
     * | UIV (11) | Superheavy Alloy (Light)    |
     * | UXV (12) | Superheavy Alloy (Heavy)    |
     * | OpV (13) | Periodicium                 |
     * | MAX (14) | Realized Quantum Foam Shard |
     */
    @JvmStatic
    val SPRING_SMALL = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(springSmall, Lead),
        LV  to UnificationEntry(springSmall, Tin),
        MV  to UnificationEntry(springSmall, Copper),
        HV  to UnificationEntry(springSmall, Gold),
        EV  to UnificationEntry(springSmall, Aluminium),
        IV  to UnificationEntry(springSmall, Tungsten),
        LuV to UnificationEntry(springSmall, NiobiumTitanium),
        ZPM to UnificationEntry(springSmall, VanadiumGallium),
        UV  to UnificationEntry(springSmall, YttriumBariumCuprate),
        UHV to UnificationEntry(springSmall, Europium),
        UEV to UnificationEntry(springSmall, Seaborgium),
        UIV to UnificationEntry(springSmall, SuperheavyAlloyA),
        UXV to UnificationEntry(springSmall, SuperheavyAlloyB),
        OpV to UnificationEntry(springSmall, Periodicium),
        MAX to UnificationEntry(springSmall, RealizedQuantumFoamShard)))

    /**
     * Component Cable
     *
     * | Tier     | Material                    |
     * |----------|-----------------------------|
     * | ULV (0)  | Red Alloy                   |
     * | LV (1)   | Tin                         |
     * | MV (2)   | Copper                      |
     * | HV (3)   | Gold                        |
     * | EV (4)   | Aluminium                   |
     * | IV (5)   | Tungsten                    |
     * | LuV (6)  | Niobium Titanium            |
     * | ZPM (7)  | Vanadium Gallium            |
     * | UV (8)   | Yttrium Barium Cuprate      |
     * | UHV (9)  | Europium                    |
     * | UEV (10) | Seaborgium                  |
     * | UIV (11) | Superheavy Alloy (Light)    |
     * | UXV (12) | Superheavy Alloy (Heavy)    |
     * | OpV (13) | Periodicium                 |
     * | MAX (14) | Realized Quantum Foam Shard |
     */
    @JvmStatic
    val CABLE_COMPONENT = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(cableGtSingle, RedAlloy),
        LV  to UnificationEntry(cableGtSingle, Tin),
        MV  to UnificationEntry(cableGtSingle, Copper),
        HV  to UnificationEntry(cableGtSingle, Gold),
        EV  to UnificationEntry(cableGtSingle, Aluminium),
        IV  to UnificationEntry(cableGtSingle, Tungsten),
        LuV to UnificationEntry(cableGtSingle, NiobiumTitanium),
        ZPM to UnificationEntry(cableGtSingle, VanadiumGallium),
        UV  to UnificationEntry(cableGtSingle, YttriumBariumCuprate),
        UHV to UnificationEntry(cableGtSingle, Europium),
        UEV to UnificationEntry(cableGtSingle, Seaborgium),
        UIV to UnificationEntry(cableGtSingle, SuperheavyAlloyA),
        UXV to UnificationEntry(cableGtSingle, SuperheavyAlloyB),
        OpV to UnificationEntry(cableGtSingle, Periodicium),
        MAX to UnificationEntry(cableGtSingle, RealizedQuantumFoamShard)))

    /**
     * Component Plate
     *
     * | Tier     | Material                                        |
     * |----------|-------------------------------------------------|
     * | ULV (0)  | Wrought Iron                                    |
     * | LV (1)   | Steel                                           |
     * | MV (2)   | Aluminium                                       |
     * | HV (3)   | Stainless Steel                                 |
     * | EV (4)   | Titanium                                        |
     * | IV (5)   | Tungsten Steel                                  |
     * | LuV (6)  | HSS-S                                           |
     * | ZPM (7)  | Osmiridium                                      |
     * | UV (8)   | Tritanium                                       |
     * | UHV (9)  | Adamantium                                      |
     * | UEV (10) | Cosmic Neutronium                               |
     * | UIV (11) | Heavy Quark Degenerate Matter                   |
     * | UXV (12) | Transcendent Metal                              |
     * | OpV (13) | Magnetohydrodynamically Constrained Star Matter |
     * | MAX (14) | Omnium                                          |
     */
    @JvmStatic
    val PLATE_COMPONENT = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(plate, WroughtIron),
        LV  to UnificationEntry(plate, Steel),
        MV  to UnificationEntry(plate, Aluminium),
        HV  to UnificationEntry(plate, StainlessSteel),
        EV  to UnificationEntry(plate, Titanium),
        IV  to UnificationEntry(plate, TungstenSteel),
        LuV to UnificationEntry(plate, HSSS),
        ZPM to UnificationEntry(plate, Osmiridium),
        UV  to UnificationEntry(plate, Tritanium),
        UHV to UnificationEntry(plate, Adamantium),
        UEV to UnificationEntry(plate, CosmicNeutronium),
        UIV to UnificationEntry(plate, HeavyQuarkDegenerateMatter),
        UXV to UnificationEntry(plate, TranscendentMetal),
        OpV to UnificationEntry(plate, MagnetohydrodynamicallyConstrainedStarMatter),
        MAX to UnificationEntry(plate, Omnium)))

    /**
     * Tier-Up Octal Cable
     *
     * TODO: MAX tier material
     *
     * | Tier     | Old Material           | New Material                |
     * |----------|------------------------|-----------------------------|
     * | ULV (0)  | Tin                    | Tin                         |
     * | LV (1)   | Copper                 | Copper                      |
     * | MV (2)   | Gold                   | Gold                        |
     * | HV (3)   | Aluminium              | Aluminium                   |
     * | EV (4)   | Platinum               | Platinum                    |
     * | IV (5)   | Niobium Titanium       | Niobium Titanium            |
     * | LuV (6)  | Vanadium Gallium       | Vanadium Gallium            |
     * | ZPM (7)  | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
     * | UV (8)   | Europium               | Europium                    |
     * | UHV (9)  | -                      | Seaborgium                  |
     * | UEV (10) | -                      | Superheavy Alloy (Light)    |
     * | UIV (11) | -                      | Superheavy Alloy (Heavy)    |
     * | UXV (12) | -                      | Periodicium                 |
     * | OpV (13) | -                      | Realized Quantum Foam Shard |
     * | MAX (14) | -                      | -                           |
     */
    @JvmStatic
    val CABLE_OCT_TIER_UP = CraftingComponent.Component(mapOf(
        ULV to UnificationEntry(cableGtOctal, Tin),
        LV  to UnificationEntry(cableGtOctal, Copper),
        MV  to UnificationEntry(cableGtOctal, Gold),
        HV  to UnificationEntry(cableGtOctal, Aluminium),
        EV  to UnificationEntry(cableGtOctal, Platinum),
        IV  to UnificationEntry(cableGtOctal, NiobiumTitanium),
        LuV to UnificationEntry(cableGtOctal, VanadiumGallium),
        ZPM to UnificationEntry(cableGtOctal, YttriumBariumCuprate),
        UV  to UnificationEntry(cableGtOctal, Europium),
        UHV to UnificationEntry(cableGtOctal, Seaborgium),
        UEV to UnificationEntry(cableGtOctal, SuperheavyAlloyA),
        UIV to UnificationEntry(cableGtOctal, SuperheavyAlloyB),
        UXV to UnificationEntry(cableGtOctal, Periodicium),
        OpV to UnificationEntry(cableGtOctal, RealizedQuantumFoamShard)))

    fun setCraftingComponents()
    {
        /**
         * Electric Wire
         *
         * | Tier     | Old Material | New Material    |
         * |----------|--------------|-----------------|
         * | ULV (0)  | Gold         | Gold            |
         * | LV (1)   | Gold         | Gold            |
         * | MV (2)   | Silver       | Silver          |
         * | HV (3)   | Electrum     | Electrum        |
         * | EV (4)   | Platinum     | Platinum        |
         * | IV (5)   | Osmium       | Osmium          |
         * | LuV (6)  | Osmium       | Osmium          |
         * | ZPM (7)  | Osmium       | Naquadah        |
         * | UV (8)   | Osmium       | Naquadah        |
         * | UHV (9)  | -            | Trinium         |
         * | UEV (10) | -            | Tritanium       |
         * | UIV (11) | -            | Adamantium      |
         * | UXV (12) | -            | Infinity        |
         * | OpV (13) | -            | Halkonite Steel |
         * | MAX (14) | -            | Space Time      |
         */
        CraftingComponent.WIRE_ELECTRIC.appendIngredients(mapOf(
            ZPM to UnificationEntry(wireGtSingle, Naquadah),
            UV  to UnificationEntry(wireGtSingle, Naquadah),
            UHV to UnificationEntry(wireGtSingle, Trinium),
            UEV to UnificationEntry(wireGtSingle, Tritanium),
            UIV to UnificationEntry(wireGtSingle, Adamantium),
            UXV to UnificationEntry(wireGtSingle, Infinity),
            OpV to UnificationEntry(wireGtSingle, HalkoniteSteel),
            MAX to UnificationEntry(wireGtSingle, SpaceTime)))

        /**
         * Quadruple Wire
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Lead                   | Lead                        |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Tungsten               | Tungsten                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.WIRE_QUAD.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtQuadruple, Europium),
            UEV to UnificationEntry(wireGtQuadruple, Seaborgium),
            UIV to UnificationEntry(wireGtQuadruple, SuperheavyAlloyA),
            UXV to UnificationEntry(wireGtQuadruple, SuperheavyAlloyB),
            OpV to UnificationEntry(wireGtQuadruple, Periodicium),
            MAX to UnificationEntry(wireGtQuadruple, RealizedQuantumFoamShard)))

        /**
         * Octal Wire
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Lead                   | Lead                        |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Tungsten               | Tungsten                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.WIRE_OCT.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtOctal, Europium),
            UEV to UnificationEntry(wireGtOctal, Seaborgium),
            UIV to UnificationEntry(wireGtOctal, SuperheavyAlloyA),
            UXV to UnificationEntry(wireGtOctal, SuperheavyAlloyB),
            OpV to UnificationEntry(wireGtOctal, Periodicium),
            MAX to UnificationEntry(wireGtOctal, RealizedQuantumFoamShard)))

        /**
         * Hex Wire
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Lead                   | Lead                        |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Tungsten               | Tungsten                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.WIRE_HEX.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtHex, Europium),
            UEV to UnificationEntry(wireGtHex, Seaborgium),
            UIV to UnificationEntry(wireGtHex, SuperheavyAlloyA),
            UXV to UnificationEntry(wireGtHex, SuperheavyAlloyB),
            OpV to UnificationEntry(wireGtHex, Periodicium),
            MAX to UnificationEntry(wireGtHex, RealizedQuantumFoamShard)))

        /**
         * Cable
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Red Alloy              | Red Alloy                   |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Platinum               | Platinum                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.CABLE.appendIngredients(mapOf(
            UEV to UnificationEntry(cableGtSingle, Seaborgium),
            UIV to UnificationEntry(cableGtSingle, SuperheavyAlloyA),
            UXV to UnificationEntry(cableGtSingle, SuperheavyAlloyB),
            OpV to UnificationEntry(cableGtSingle, Periodicium),
            MAX to UnificationEntry(cableGtSingle, RealizedQuantumFoamShard)))

        /**
         * Quadruple Cable
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Red Alloy              | Red Alloy                   |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Platinum               | Platinum                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.CABLE_QUAD.appendIngredients(mapOf(
            UEV to UnificationEntry(cableGtQuadruple, Seaborgium),
            UIV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
            UXV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
            OpV to UnificationEntry(cableGtQuadruple, Periodicium),
            MAX to UnificationEntry(cableGtQuadruple, RealizedQuantumFoamShard)))

        /**
         * Octal Cable
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Red Alloy              | Red Alloy                   |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Platinum               | Platinum                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.CABLE_OCT.appendIngredients(mapOf(
            UEV to UnificationEntry(cableGtOctal, Seaborgium),
            UIV to UnificationEntry(cableGtOctal, SuperheavyAlloyA),
            UXV to UnificationEntry(cableGtOctal, SuperheavyAlloyB),
            OpV to UnificationEntry(cableGtOctal, Periodicium),
            MAX to UnificationEntry(cableGtOctal, RealizedQuantumFoamShard)))

        /**
         * Hex Cable
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Red Alloy              | Red Alloy                   |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Platinum               | Platinum                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | -                      | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.CABLE_HEX.appendIngredients(mapOf(
            UEV to UnificationEntry(cableGtHex, Seaborgium),
            UIV to UnificationEntry(cableGtHex, SuperheavyAlloyA),
            UXV to UnificationEntry(cableGtHex, SuperheavyAlloyB),
            OpV to UnificationEntry(cableGtHex, Periodicium),
            MAX to UnificationEntry(cableGtHex, RealizedQuantumFoamShard)))

        /**
         * Tier-Up Cable
         *
         * TODO: MAX tier material
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Tin                    | Tin                         |
         * | LV (1)   | Copper                 | Copper                      |
         * | MV (2)   | Gold                   | Gold                        |
         * | HV (3)   | Aluminium              | Aluminium                   |
         * | EV (4)   | Platinum               | Platinum                    |
         * | IV (5)   | Niobium Titanium       | Niobium Titanium            |
         * | LuV (6)  | Vanadium Gallium       | Vanadium Gallium            |
         * | ZPM (7)  | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UV (8)   | Europium               | Europium                    |
         * | UHV (9)  | -                      | Seaborgium                  |
         * | UEV (10) | -                      | Superheavy Alloy (Light)    |
         * | UIV (11) | -                      | Superheavy Alloy (Heavy)    |
         * | UXV (12) | -                      | Periodicium                 |
         * | OpV (13) | -                      | Realized Quantum Foam Shard |
         * | MAX (14) | -                      | -                           |
         */
        CraftingComponent.CABLE_TIER_UP.appendIngredients(mapOf(
            UHV to UnificationEntry(cableGtSingle, Seaborgium),
            UEV to UnificationEntry(cableGtSingle, SuperheavyAlloyA),
            UIV to UnificationEntry(cableGtSingle, SuperheavyAlloyB),
            UXV to UnificationEntry(cableGtSingle, Periodicium),
            OpV to UnificationEntry(cableGtSingle, RealizedQuantumFoamShard)))

        /**
         * Tier-Up Quadruple Cable
         *
         * TODO: MAX tier material
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Tin                    | Tin                         |
         * | LV (1)   | Copper                 | Copper                      |
         * | MV (2)   | Gold                   | Gold                        |
         * | HV (3)   | Aluminium              | Aluminium                   |
         * | EV (4)   | Platinum               | Platinum                    |
         * | IV (5)   | Niobium Titanium       | Niobium Titanium            |
         * | LuV (6)  | Vanadium Gallium       | Vanadium Gallium            |
         * | ZPM (7)  | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UV (8)   | Europium               | Europium                    |
         * | UHV (9)  | -                      | Seaborgium                  |
         * | UEV (10) | -                      | Superheavy Alloy (Light)    |
         * | UIV (11) | -                      | Superheavy Alloy (Heavy)    |
         * | UXV (12) | -                      | Periodicium                 |
         * | OpV (13) | -                      | Realized Quantum Foam Shard |
         * | MAX (14) | -                      | -                           |
         */
        CraftingComponent.CABLE_QUAD_TIER_UP.appendIngredients(mapOf(
            UHV to UnificationEntry(cableGtQuadruple, Seaborgium),
            UEV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
            UIV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
            UXV to UnificationEntry(cableGtQuadruple, Periodicium),
            OpV to UnificationEntry(cableGtQuadruple, RealizedQuantumFoamShard)))

        /**
         * Normal Pipe
         *
         * | Tier     | Old Material     | New Material                             |
         * |----------|------------------|------------------------------------------|
         * | ULV (0)  | Bronze           | Bronze                                   |
         * | LV (1)   | Bronze           | Bronze                                   |
         * | MV (2)   | Steel            | Steel                                    |
         * | HV (3)   | Stainless Steel  | Stainless Steel                          |
         * | EV (4)   | Titanium         | Titanium                                 |
         * | IV (5)   | Tungsten Steel   | Tungsten Steel                           |
         * | LuV (6)  | Niobium Titanium | Niobium Titanium                         |
         * | ZPM (7)  | Iridium          | Iridium                                  |
         * | UV (8)   | Naquadah         | Naquadah                                 |
         * | UHV (9)  | -                | Europium                                 |
         * | UEV (10) | -                | Duranium                                 |
         * | UIV (11) | -                | Neutronium                               |
         * | UXV (12) | -                | Heavy Quark Degenerate Matter            |
         * | OpV (13) | -                | Quantumchromodynamically Confined Matter |
         * | MAX (14) | -                | Transcendent Metal                       |
         */
        CraftingComponent.PIPE_NORMAL.appendIngredients(mapOf(
            UHV to UnificationEntry(pipeNormalFluid, Europium),
            UEV to UnificationEntry(pipeNormalFluid, Duranium),
            UIV to UnificationEntry(pipeNormalFluid, Neutronium),
            UXV to UnificationEntry(pipeNormalFluid, HeavyQuarkDegenerateMatter),
            OpV to UnificationEntry(pipeNormalFluid, QuantumchromodynamicallyConfinedMatter),
            MAX to UnificationEntry(pipeNormalFluid, TranscendentMetal)))

        /**
         * Large Pipe
         *
         * | Tier     | Old Material     | New Material                             |
         * |----------|------------------|------------------------------------------|
         * | ULV (0)  | Bronze           | Bronze                                   |
         * | LV (1)   | Bronze           | Bronze                                   |
         * | MV (2)   | Steel            | Steel                                    |
         * | HV (3)   | Stainless Steel  | Stainless Steel                          |
         * | EV (4)   | Titanium         | Titanium                                 |
         * | IV (5)   | Tungsten Steel   | Tungsten Steel                           |
         * | LuV (6)  | Niobium Titanium | Niobium Titanium                         |
         * | ZPM (7)  | Iridium          | Iridium                                  |
         * | UV (8)   | Naquadah         | Naquadah                                 |
         * | UHV (9)  | -                | Europium                                 |
         * | UEV (10) | -                | Duranium                                 |
         * | UIV (11) | -                | Neutronium                               |
         * | UXV (12) | -                | Heavy Quark Degenerate Matter            |
         * | OpV (13) | -                | Quantumchromodynamically Confined Matter |
         * | MAX (14) | -                | Transcendent Metal                       |
         */
        CraftingComponent.PIPE_LARGE.appendIngredients(mapOf(
            UHV to UnificationEntry(pipeLargeFluid, Europium),
            UEV to UnificationEntry(pipeLargeFluid, Duranium),
            UIV to UnificationEntry(pipeLargeFluid, Neutronium),
            UXV to UnificationEntry(pipeLargeFluid, HeavyQuarkDegenerateMatter),
            OpV to UnificationEntry(pipeLargeFluid, QuantumchromodynamicallyConfinedMatter),
            MAX to UnificationEntry(pipeLargeFluid, TranscendentMetal)))

        /**
         * Glass
         *
         * | Tier     | Old Material    | New Material |
         * |----------|-----------------|--------------|
         * | ULV (0)  | Glass           | Glass        |
         * | LV (1)   | Glass           | LV Glass     |
         * | MV (2)   | Glass           | MV Glass     |
         * | HV (3)   | Tempered Glass  | HV Glass     |
         * | EV (4)   | Tempered Glass  | EV Glass     |
         * | IV (5)   | Laminated Glass | IV Glass     |
         * | LuV (6)  | Laminated Glass | LuV Glass    |
         * | ZPM (7)  | Fusion Glass    | ZPM Glass    |
         * | UV (8)   | Fusion Glass    | UV Glass     |
         * | UHV (9)  | -               | UHV Glass    |
         * | UEV (10) | -               | UEV Glass    |
         * | UIV (11) | -               | UIV Glass    |
         * | UXV (12) | -               | UXV Glass    |
         * | OpV (13) | -               | OpV Glass    |
         * | MAX (14) | -               | MAX Glass    |
         */
        CraftingComponent.GLASS.appendIngredients(mapOf(
            ULV to UnificationEntry(glass, Tier.ULV),
            LV  to UnificationEntry(glass, Tier.LV),
            MV  to UnificationEntry(glass, Tier.MV),
            HV  to UnificationEntry(glass, Tier.HV),
            EV  to UnificationEntry(glass, Tier.EV),
            IV  to UnificationEntry(glass, Tier.IV),
            LuV to UnificationEntry(glass, Tier.LuV),
            ZPM to UnificationEntry(glass, Tier.ZPM),
            UV  to UnificationEntry(glass, Tier.UV),
            UHV to UnificationEntry(glass, Tier.UHV),
            UEV to UnificationEntry(glass, Tier.UEV),
            UIV to UnificationEntry(glass, Tier.UIV),
            UXV to UnificationEntry(glass, Tier.UXV),
            OpV to UnificationEntry(glass, Tier.OpV),
            MAX to UnificationEntry(glass, Tier.MAX)))

        /**
         * Plate
         *
         * | Tier     | Old Material             | New Material             |
         * |----------|--------------------------|--------------------------|
         * | ULV (0)  | Wrought Iron             | Wrought Iron             |
         * | LV (1)   | Steel                    | Steel                    |
         * | MV (2)   | Aluminium                | Aluminium                |
         * | HV (3)   | Stainless Steel          | Stainless Steel          |
         * | EV (4)   | Titanium                 | Titanium                 |
         * | IV (5)   | Tungsten Steel           | Tungsten Steel           |
         * | LuV (6)  | Rhodium Plated Palladium | Rhodium Plated Palladium |
         * | ZPM (7)  | Naquadah Alloy           | Naquadah Alloy           |
         * | UV (8)   | Darmstadtium             | Darmstadtium             |
         * | UHV (9)  | Neutronium               | Neutronium               |
         * | UEV (10) | -                        | Vibranium                |
         * | UIV (11) | -                        | Shirabon                 |
         * | UXV (12) | -                        | Creon                    |
         * | OpV (13) | -                        | Black Dwarf Matter       |
         * | MAX (14) | -                        | Axino-Fused Red Matter   |
         */
        CraftingComponent.PLATE.appendIngredients(mapOf(
            UEV to UnificationEntry(plate, Vibranium),
            UIV to UnificationEntry(plate, Shirabon),
            UXV to UnificationEntry(plate, Creon),
            OpV to UnificationEntry(plate, BlackDwarfMatter),
            MAX to UnificationEntry(plate, AxinoFusedRedMatter)))

        /**
         * Double Plate
         *
         * | Tier     | Old Material             | New Material             |
         * |----------|--------------------------|--------------------------|
         * | ULV (0)  | Wrought Iron             | Wrought Iron             |
         * | LV (1)   | Steel                    | Steel                    |
         * | MV (2)   | Aluminium                | Aluminium                |
         * | HV (3)   | Stainless Steel          | Stainless Steel          |
         * | EV (4)   | Titanium                 | Titanium                 |
         * | IV (5)   | Tungsten Steel           | Tungsten Steel           |
         * | LuV (6)  | Rhodium Plated Palladium | Rhodium Plated Palladium |
         * | ZPM (7)  | Naquadah Alloy           | Naquadah Alloy           |
         * | UV (8)   | Darmstadtium             | Darmstadtium             |
         * | UHV (9)  | Neutronium               | Neutronium               |
         * | UEV (10) | -                        | Vibranium                |
         * | UIV (11) | -                        | Shirabon                 |
         * | UXV (12) | -                        | Creon                    |
         * | OpV (13) | -                        | Black Dwarf Matter       |
         * | MAX (14) | -                        | Axino-Fused Red Matter   |
         */
        CraftingComponent.DOUBLE_PLATE.appendIngredients(mapOf(
            UEV to UnificationEntry(plateDouble, Vibranium),
            UIV to UnificationEntry(plateDouble, Shirabon),
            UXV to UnificationEntry(plateDouble, Creon),
            OpV to UnificationEntry(plateDouble, BlackDwarfMatter),
            MAX to UnificationEntry(plateDouble, AxinoFusedRedMatter)))

        /**
         * Hull Plate
         *
         * | Tier     | Old Material                   | New Material                   |
         * |----------|--------------------------------|--------------------------------|
         * | ULV (0)  | Wood                           | Wood                           |
         * | LV (1)   | Wrought Iron                   | Wrought Iron                   |
         * | MV (2)   | Wrought Iron                   | Wrought Iron                   |
         * | HV (3)   | Polyethylene (PE)              | Polyethylene (PE)              |
         * | EV (4)   | Polyethylene (PE)              | Polyethylene (PE)              |
         * | IV (5)   | Polytetrafluoroethylene (PTFE) | Polytetrafluoroethylene (PTFE) |
         * | LuV (6)  | Polytetrafluoroethylene (PTFE) | Polytetrafluoroethylene (PTFE) |
         * | ZPM (7)  | Polybenzimidazole (PBI)        | Polybenzimidazole (PBI)        |
         * | UV (8)   | Polybenzimidazole (PBI)        | Polybenzimidazole (PBI)        |
         * | UHV (9)  | Polybenzimidazole (PBI)        | Kevlar                         |
         * | UEV (10) | -                              | Kevlar                         |
         * | UIV (11) | -                              | Fullerene Polymer Matrix (FPM) |
         * | UXV (12) | -                              | Fullerene Polymer Matrix (FPM) |
         * | OpV (13) | -                              | Cosmic Fabric                  |
         * | MAX (14) | -                              | Cosmic Fabric                  |
         */
        CraftingComponent.HULL_PLATE.appendIngredients(mapOf(
            UHV to UnificationEntry(plate, Kevlar),
            UEV to UnificationEntry(plate, Kevlar),
            UIV to UnificationEntry(plate, FullerenePolymerMatrix),
            UXV to UnificationEntry(plate, FullerenePolymerMatrix),
            OpV to UnificationEntry(plate, CosmicFabric),
            MAX to UnificationEntry(plate, CosmicFabric)))

        /**
         * Rotor
         *
         * | Tier     | Old Material             | New Material             |
         * |----------|--------------------------|--------------------------|
         * | ULV (0)  | Tin                      | Tin                      |
         * | LV (1)   | Tin                      | Tin                      |
         * | MV (2)   | Bronze                   | Bronze                   |
         * | HV (3)   | Steel                    | Steel                    |
         * | EV (4)   | Stainless Steel          | Stainless Steel          |
         * | IV (5)   | Tungsten Steel           | Tungsten Steel           |
         * | LuV (6)  | Rhodium Plated Palladium | Rhodium Plated Palladium |
         * | ZPM (7)  | Naquadah Alloy           | Naquadah Alloy           |
         * | UV (8)   | Darmstadtium             | Darmstadtium             |
         * | UHV (9)  | -                        | Neutronium               |
         * | UEV (10) | -                        | Vibranium                |
         * | UIV (11) | -                        | Shirabon                 |
         * | UXV (12) | -                        | Creon                    |
         * | OpV (13) | -                        | Black Dwarf Matter       |
         * | MAX (14) | -                        | Axino-Fused Red Matter   |
         */
        CraftingComponent.ROTOR.appendIngredients(mapOf(
            UHV to UnificationEntry(rotor, Neutronium),
            UEV to UnificationEntry(rotor, Vibranium),
            UIV to UnificationEntry(rotor, Shirabon),
            UXV to UnificationEntry(rotor, Creon),
            OpV to UnificationEntry(rotor, BlackDwarfMatter),
            MAX to UnificationEntry(rotor, AxinoFusedRedMatter)))

        /**
         * Grinder
         *
         * | Tier     | Old Item         | New Item                |
         * |----------|------------------|-------------------------|
         * | ULV (0)  | Diamond          | Diamond                 |
         * | LV (1)   | Diamond          | Diamond                 |
         * | MV (2)   | Diamond          | Diamond                 |
         * | HV (3)   | Diamond Grinder  | Diamond Grinder         |
         * | EV (4)   | Diamond Grinder  | Diamond Grinder         |
         * | IV (5)   | Tungsten Grinder | Tungsten Grinder        |
         * | LuV (6)  | -                | Tungsten Grinder        |
         * | ZPM (7)  | -                | Tungsten Grinder        |
         * | UV (8)   | -                | Tungsten Grinder        |
         * | UHV (9)  | -                | Boron Nitride Grinder   |
         * | UEV (10) | -                | Boron Nitride Grinder   |
         * | UIV (11) | -                | Boron Nitride Grinder   |
         * | UXV (12) | -                | Halkonite Steel Grinder |
         * | OpV (13) | -                | Halkonite Steel Grinder |
         * | MAX (14) | -                | Halkonite Steel Grinder |
         */
        CraftingComponent.GRINDER.appendIngredients(mapOf(
            LuV to COMPONENT_GRINDER_TUNGSTEN.stack(),
            ZPM to COMPONENT_GRINDER_TUNGSTEN.stack(),
            UV  to COMPONENT_GRINDER_TUNGSTEN.stack(),
            UHV to COMPONENT_GRINDER_BORON_NITRIDE.stack(),
            UEV to COMPONENT_GRINDER_BORON_NITRIDE.stack(),
            UIV to COMPONENT_GRINDER_BORON_NITRIDE.stack(),
            UXV to COMPONENT_GRINDER_HALKONITE_STEEL.stack(),
            OpV to COMPONENT_GRINDER_HALKONITE_STEEL.stack(),
            MAX to COMPONENT_GRINDER_HALKONITE_STEEL.stack()))

        /**
         * Sawblade
         *
         * | Tier     | Old Material     | New Material                          |
         * |----------|------------------|---------------------------------------|
         * | ULV (0)  | Bronze           | Bronze                                |
         * | LV (1)   | Cobalt Brass     | Cobalt Brass                          |
         * | MV (2)   | Vanadium Steel   | Vanadium Steel                        |
         * | HV (3)   | Blue Steel       | Blue Steel                            |
         * | EV (4)   | Ultimet          | Ultimet                               |
         * | IV (5)   | Tungsten Carbide | Tungsten Carbide                      |
         * | LuV (6)  | HSS-E            | HSS-E                                 |
         * | ZPM (7)  | Naquadah Alloy   | Naquadah Alloy                        |
         * | UV (8)   | Duranium         | Duranium                              |
         * | UHV (9)  | -                | Cubic Boron Nitride                   |
         * | UEV (10) | -                | High Durability Compound Steel (HDCS) |
         * | UIV (11) | -                | Hastelloy K243                        |
         * | UXV (12) | -                | Legendarium                           |
         * | OpV (13) | -                | Creon                                 |
         * | MAX (14) | -                | Axino-Fused Red Matter                |
         */
        CraftingComponent.SAWBLADE.appendIngredients(mapOf(
            UHV to UnificationEntry(toolHeadBuzzSaw, CubicBoronNitride),
            UEV to UnificationEntry(toolHeadBuzzSaw, HDCS),
            UIV to UnificationEntry(toolHeadBuzzSaw, HastelloyK243),
            UXV to UnificationEntry(toolHeadBuzzSaw, Legendarium),
            OpV to UnificationEntry(toolHeadBuzzSaw, Creon),
            MAX to UnificationEntry(toolHeadBuzzSaw, AxinoFusedRedMatter)))

        /**
         * Heating Coil Wire
         *
         * | Tier     | Old Material   | New Material    |
         * |----------|----------------|-----------------|
         * | ULV (0)  | Copper         | Copper          |
         * | LV (1)   | Copper         | Copper          |
         * | MV (2)   | Cupronickel    | Cupronickel     |
         * | HV (3)   | Kanthal        | Kanthal         |
         * | EV (4)   | Nichrome       | Nichrome        |
         * | IV (5)   | RTM Alloy      | RTM Alloy       |
         * | LuV (6)  | HSS-G          | HSS-G           |
         * | ZPM (7)  | Naquadah       | Naquadah        |
         * | UV (8)   | Naquadah Alloy | Naquadah Alloy  |
         * | UHV (9)  | -              | Tritanium       |
         * | UEV (10) | -              | Adamantium      |
         * | UIV (11) | -              | Infinity        |
         * | UXV (12) | -              | Halkonite Steel |
         * | OpV (13) | -              | Space Time      |
         * | MAX (14) | -              | Eternity        |
         */
        CraftingComponent.COIL_HEATING.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtDouble, Tritanium),
            UEV to UnificationEntry(wireGtDouble, Adamantium),
            UIV to UnificationEntry(wireGtDouble, Infinity),
            UXV to UnificationEntry(wireGtDouble, HalkoniteSteel),
            OpV to UnificationEntry(wireGtDouble, SpaceTime),
            MAX to UnificationEntry(wireGtDouble, Eternity)))

        /**
         * Heating Coil Double Wire
         *
         * | Tier     | Old Material   | New Material    |
         * |----------|----------------|-----------------|
         * | ULV (0)  | Copper         | Copper          |
         * | LV (1)   | Copper         | Copper          |
         * | MV (2)   | Cupronickel    | Cupronickel     |
         * | HV (3)   | Kanthal        | Kanthal         |
         * | EV (4)   | Nichrome       | Nichrome        |
         * | IV (5)   | RTM Alloy      | RTM Alloy       |
         * | LuV (6)  | HSS-G          | HSS-G           |
         * | ZPM (7)  | Naquadah       | Naquadah        |
         * | UV (8)   | Naquadah Alloy | Naquadah Alloy  |
         * | UHV (9)  | -              | Tritanium       |
         * | UEV (10) | -              | Adamantium      |
         * | UIV (11) | -              | Infinity        |
         * | UXV (12) | -              | Halkonite Steel |
         * | OpV (13) | -              | Space Time      |
         * | MAX (14) | -              | Eternity        |
         */
        CraftingComponent.COIL_HEATING_DOUBLE.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtQuadruple, Tritanium),
            UEV to UnificationEntry(wireGtQuadruple, Adamantium),
            UIV to UnificationEntry(wireGtQuadruple, Infinity),
            UXV to UnificationEntry(wireGtQuadruple, HalkoniteSteel),
            OpV to UnificationEntry(wireGtQuadruple, SpaceTime),
            MAX to UnificationEntry(wireGtQuadruple, Eternity)))

        /**
         * Electric Coil Wire
         *
         * | Tier     | Old Material           | New Material                    |
         * |----------|------------------------|---------------------------------|
         * | ULV (0)  | Tin                    | Tin                             |
         * | LV (1)   | Tin                    | Tin                             |
         * | MV (2)   | Copper                 | Copper                          |
         * | HV (3)   | Silver                 | Silver                          |
         * | EV (4)   | Steel                  | Steel                           |
         * | IV (5)   | Graphene               | Graphene                        |
         * | LuV (6)  | Niobium Nitride        | Niobium Nitride                 |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium                |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate          |
         * | UHV (9)  | -                      | Thallium Barium Calcium Cuprate |
         * | UEV (10) | -                      | Cosmic Neutronium               |
         * | UIV (11) | -                      | Hypogen                         |
         * | UXV (12) | -                      | Mag Matter                      |
         * | OpV (13) | -                      | Universium                      |
         * | MAX (14) | -                      | Self-Interacting Dark Matter    |
         */
        CraftingComponent.COIL_ELECTRIC.appendIngredients(mapOf(
            UHV to UnificationEntry(wireGtOctal, ThalliumBariumCalciumCuprate),
            UEV to UnificationEntry(wireGtOctal, CosmicNeutronium),
            UIV to UnificationEntry(wireGtHex, Hypogen),
            UXV to UnificationEntry(wireGtHex, MagMatter),
            OpV to UnificationEntry(wireGtHex, Universium),
            MAX to UnificationEntry(wireGtHex, SelfInteractingDarkMatter)))

        /**
         * Magnetic Stick
         *
         * | Tier     | Old Material       | New Material                          |
         * |----------|--------------------|---------------------------------------|
         * | ULV (0)  | Magnetic Iron      | Magnetic Iron                         |
         * | LV (1)   | Magnetic Iron      | Magnetic Iron                         |
         * | MV (2)   | Magnetic Steel     | Magnetic Steel                        |
         * | HV (3)   | Magnetic Steel     | Magnetic Steel                        |
         * | EV (4)   | Magnetic Neodymium | Magnetic Neodymium                    |
         * | IV (5)   | Magnetic Neodymium | Magnetic Neodymium                    |
         * | LuV (6)  | -                  | Magnetic Neodymium                    |
         * | ZPM (7)  | -                  | Magnetic Neodymium                    |
         * | UV (8)   | -                  | Magnetic Samarium                     |
         * | UHV (9)  | -                  | Magnetic Samarium                     |
         * | UEV (10) | -                  | Magnetic Chromium Germanium Telluride |
         * | UIV (11) | -                  | Magnetic Chromium Germanium Telluride |
         * | UXV (12) | -                  | Magnetic Chromium Germanium Telluride |
         * | OpV (13) | -                  | Magnetic Chromium Germanium Telluride |
         * | MAX (14) | -                  | Magnetic Chromium Germanium Telluride |
         */
        CraftingComponent.STICK_MAGNETIC.appendIngredients(mapOf(
            UV to UnificationEntry(stick, SamariumMagnetic),
            UHV to UnificationEntry(stick, SamariumMagnetic),
            UEV to UnificationEntry(stickLong, SamariumMagnetic),
            UIV to UnificationEntry(stickLong, SamariumMagnetic),
            UXV to UnificationEntry(stick, ChromiumGermaniumTellurideMagnetic),
            OpV to UnificationEntry(stick, ChromiumGermaniumTellurideMagnetic),
            MAX to UnificationEntry(stickLong, ChromiumGermaniumTellurideMagnetic)))

        /**
         * Distillation Stick (Spring)
         *
         * | Tier     | Old Material   | New Material    |
         * |----------|----------------|-----------------|
         * | ULV (0)  | Blaze          | Blaze           |
         * | LV (1)   | Copper         | Copper          |
         * | MV (2)   | Cupronickel    | Cupronickel     |
         * | HV (3)   | Kanthal        | Kanthal         |
         * | EV (4)   | Nichrome       | Nichrome        |
         * | IV (5)   | RTM Alloy      | RTM Alloy       |
         * | LuV (6)  | HSS-G          | HSS-G           |
         * | ZPM (7)  | Naquadah       | Naquadah        |
         * | UV (8)   | Naquadah Alloy | Naquadah Alloy  |
         * | UHV (9)  | -              | Trinium         |
         * | UEV (10) | -              | Tritanium       |
         * | UIV (11) | -              | Adamantium      |
         * | UXV (12) | -              | Infinity        |
         * | OpV (13) | -              | Halkonite Steel |
         * | MAX (14) | -              | Space-Time      |
         */
        CraftingComponent.STICK_DISTILLATION.appendIngredients(mapOf(
            UHV to UnificationEntry(spring, Trinium),
            UEV to UnificationEntry(spring, Tritanium),
            UIV to UnificationEntry(spring, Adamantium),
            UXV to UnificationEntry(spring, Infinity),
            OpV to UnificationEntry(spring, HalkoniteSteel),
            MAX to UnificationEntry(spring, SpaceTime)))

        /**
         * Electromagnetic Stick
         *
         * | Tier     | Old Material | New Material                              |
         * |----------|--------------|-------------------------------------------|
         * | ULV (0)  | Iron         | Iron                                      |
         * | LV (1)   | Iron         | Iron                                      |
         * | MV (2)   | Steel        | Steel                                     |
         * | HV (3)   | Steel        | Steel                                     |
         * | EV (4)   | Neodymium    | Neodymium                                 |
         * | IV (5)   | -            | Neodymium                                 |
         * | LuV (6)  | -            | Vanadium Gallium                          |
         * | ZPM (7)  | -            | Vanadium Gallium                          |
         * | UV (8)   | -            | Vanadium Gallium (Long Stick)             |
         * | UHV (9)  | -            | Vanadium Gallium (Long Stick)             |
         * | UEV (10) | -            | Samarium Cobalt                           |
         * | UIV (11) | -            | Samarium Cobalt                           |
         * | UXV (12) | -            | Lutetium Manganese Germanium (Long Stick) |
         * | OpV (13) | -            | Lutetium Manganese Germanium (Long Stick) |
         * | MAX (14) | -            | Magnetium                                 |
         */
        CraftingComponent.STICK_ELECTROMAGNETIC.appendIngredients(mapOf(
            IV  to UnificationEntry(stick, Neodymium),
            LuV to UnificationEntry(stick, VanadiumGallium),
            ZPM to UnificationEntry(stick, VanadiumGallium),
            UV  to UnificationEntry(stickLong, VanadiumGallium),
            UHV to UnificationEntry(stickLong, VanadiumGallium),
            UEV to UnificationEntry(stick, SamariumCobalt),
            UIV to UnificationEntry(stick, SamariumCobalt),
            UXV to UnificationEntry(stickLong, LutetiumManganeseGermanium),
            OpV to UnificationEntry(stickLong, LutetiumManganeseGermanium),
            MAX to UnificationEntry(stick, Magnetium)))

        /**
         * Radioactive Stick
         *
         * | Tier     | Old Material      | New Material             |
         * |----------|-------------------|--------------------------|
         * | ULV (0)  | -                 | Uranium-235              |
         * | LV (1)   | -                 | Uranium-235              |
         * | MV (2)   | -                 | Uranium-238              |
         * | HV (3)   | -                 | Plutonium-239            |
         * | EV (4)   | Uranium-235       | Plutonium-241            |
         * | IV (5)   | Plutonium-241     | Naquadah                 |
         * | LuV (6)  | Enriched Naquadah | Enriched Naquadah        |
         * | ZPM (7)  | Americium         | Naquadria                |
         * | UV (8)   | -                 | Americium                |
         * | UHV (9)  | -                 | Rutherfordium            |
         * | UEV (10) | -                 | Dubnium                  |
         * | UIV (11) | -                 | Metastable Flerovium     |
         * | UXV (12) | -                 | Metastable Hassium       |
         * | OpV (13) | -                 | Superheavy Alloy (Light) |
         * | MAX (14) | -                 | Superheavy Alloy (Heavy) |
         */
        CraftingComponent.STICK_RADIOACTIVE.appendIngredients(mapOf(
            ULV to UnificationEntry(stick, Thorium),
            LV  to UnificationEntry(stick, Uranium235),
            MV  to UnificationEntry(stick, Uranium238),
            HV  to UnificationEntry(stick, Plutonium239),
            EV  to UnificationEntry(stick, Plutonium241),
            IV  to UnificationEntry(stick, Naquadah),
            LuV to UnificationEntry(stick, Curium),
            ZPM to UnificationEntry(stick, Californium),
            UV  to UnificationEntry(stick, Mendelevium),
            UHV to UnificationEntry(stick, Rutherfordium),
            UEV to UnificationEntry(stick, Dubnium),
            UIV to UnificationEntry(stick, MetastableFlerovium),
            UXV to UnificationEntry(stick, MetastableHassium),
            OpV to UnificationEntry(stick, SuperheavyAlloyA),
            MAX to UnificationEntry(stick, SuperheavyAlloyB)))

        /**
         * Reactor Pipe
         *
         * | Tier     | Old Material                   | New Material                   |
         * |----------|--------------------------------|--------------------------------|
         * | ULV (0)  | Glass                          | Glass                          |
         * | LV (1)   | Glass                          | Glass                          |
         * | MV (2)   | Glass                          | Glass                          |
         * | HV (3)   | Polyethylene (PE)              | Polyethylene (PE)              |
         * | EV (4)   | Polyethylene (PE)              | Polyethylene (PE)              |
         * | IV (5)   | Polyethylene (PE)              | Polyethylene (PE)              |
         * | LuV (6)  | Polytetrafluoroethylene (PTFE) | Polytetrafluoroethylene (PTFE) |
         * | ZPM (7)  | Polytetrafluoroethylene (PTFE) | Polytetrafluoroethylene (PTFE) |
         * | UV (8)   | Polytetrafluoroethylene (PTFE) | Polytetrafluoroethylene (PTFE) |
         * | UHV (9)  | -                              | Polybenzimidazole (PBI)        |
         * | UEV (10) | -                              | Polybenzimidazole (PBI)        |
         * | UIV (11) | -                              | Polybenzimidazole (PBI)        |
         * | UXV (12) | -                              | Kevlar                         |
         * | OpV (13) | -                              | Kevlar                         |
         * | MAX (14) | -                              | Kevlar                         |
         */
        CraftingComponent.PIPE_REACTOR.appendIngredients(mapOf(
            UHV to UnificationEntry(pipeNormalFluid, Polybenzimidazole),
            UEV to UnificationEntry(pipeLargeFluid, Polybenzimidazole),
            UIV to UnificationEntry(pipeHugeFluid, Polybenzimidazole),
            UXV to UnificationEntry(pipeNormalFluid, Kevlar),
            OpV to UnificationEntry(pipeLargeFluid, Kevlar),
            MAX to UnificationEntry(pipeHugeFluid, Kevlar)))

        /**
         * Component Power IC
         *
         * | Tier     | Old Item            | New Item            |
         * |----------|---------------------|---------------------|
         * | ULV (0)  | Ultra Low Power IC  | Ultra Low Power IC  |
         * | LV (1)   | Ultra Low Power IC  | Ultra Low Power IC  |
         * | MV (2)   | Ultra Low Power IC  | Ultra Low Power IC  |
         * | HV (3)   | Low Power IC        | Low Power IC        |
         * | EV (4)   | Power IC            | Power IC            |
         * | IV (5)   | High Power IC       | High Power IC       |
         * | LuV (6)  | High Power IC       | High Power IC       |
         * | ZPM (7)  | Ultra High Power IC | Ultra High Power IC |
         * | UV (8)   | Ultra High Power IC | Ultra High Power IC |
         * | UHV (9)  | Ultra High Power IC | Nano Power IC       |
         * | UEV (10) | -                   | Nano Power IC       |
         * | UIV (11) | -                   | Pico Power IC       |
         * | UXV (12) | -                   | Pico Power IC       |
         * | OpV (13) | -                   | Femto Power IC      |
         * | MAX (14) | -                   | Femto Power IC      |
         */
        CraftingComponent.POWER_COMPONENT.appendIngredients(mapOf(
            UHV to NANO_PIC_CHIP.stack(),
            UEV to NANO_PIC_CHIP.stack(),
            UIV to PICO_PIC_CHIP.stack(),
            UXV to PICO_PIC_CHIP.stack(),
            OpV to FEMTO_PIC_CHIP.stack(),
            MAX to FEMTO_PIC_CHIP.stack()))

        /**
         * Voltage Coil
         *
         * | Tier     | Old Item         | New Item         |
         * |----------|------------------|------------------|
         * | ULV (0)  | ULV Voltage Coil | ULV Voltage Coil |
         * | LV (1)   | LV Voltage Coil  | LV Voltage Coil  |
         * | MV (2)   | MV Voltage Coil  | MV Voltage Coil  |
         * | HV (3)   | HV Voltage Coil  | HV Voltage Coil  |
         * | EV (4)   | EV Voltage Coil  | EV Voltage Coil  |
         * | IV (5)   | IV Voltage Coil  | IV Voltage Coil  |
         * | LuV (6)  | LuV Voltage Coil | LuV Voltage Coil |
         * | ZPM (7)  | ZPM Voltage Coil | ZPM Voltage Coil |
         * | UV (8)   | UV Voltage Coil  | UV Voltage Coil  |
         * | UHV (9)  | -                | UHV Voltage Coil |
         * | UEV (10) | -                | UEV Voltage Coil |
         * | UIV (11) | -                | UIV Voltage Coil |
         * | UXV (12) | -                | UXV Voltage Coil |
         * | OpV (13) | -                | OpV Voltage Coil |
         * | MAX (14) | -                | MAX Voltage Coil |
         */
        CraftingComponent.VOLTAGE_COIL.appendIngredients(mapOf(
            UHV to VOLTAGE_COIL_UHV.stack(),
            UEV to VOLTAGE_COIL_UEV.stack(),
            UIV to VOLTAGE_COIL_UIV.stack(),
            UXV to VOLTAGE_COIL_UXV.stack(),
            OpV to VOLTAGE_COIL_OpV.stack(),
            MAX to VOLTAGE_COIL_MAX.stack()))

        /**
         * Spring
         *
         * | Tier     | Old Material           | New Material                |
         * |----------|------------------------|-----------------------------|
         * | ULV (0)  | Lead                   | Lead                        |
         * | LV (1)   | Tin                    | Tin                         |
         * | MV (2)   | Copper                 | Copper                      |
         * | HV (3)   | Gold                   | Gold                        |
         * | EV (4)   | Aluminium              | Aluminium                   |
         * | IV (5)   | Tungsten               | Tungsten                    |
         * | LuV (6)  | Niobium Titanium       | Niobium Titanium            |
         * | ZPM (7)  | Vanadium Gallium       | Vanadium Gallium            |
         * | UV (8)   | Yttrium Barium Cuprate | Yttrium Barium Cuprate      |
         * | UHV (9)  | Europium               | Europium                    |
         * | UEV (10) | -                      | Seaborgium                  |
         * | UIV (11) | -                      | Superheavy Alloy (Light)    |
         * | UXV (12) | -                      | Superheavy Alloy (Heavy)    |
         * | OpV (13) | -                      | Periodicium                 |
         * | MAX (14) | -                      | Realized Quantum Foam Shard |
         */
        CraftingComponent.SPRING.appendIngredients(mapOf(
            UEV to UnificationEntry(spring, Seaborgium),
            UIV to UnificationEntry(spring, SuperheavyAlloyA),
            UXV to UnificationEntry(spring, SuperheavyAlloyB),
            OpV to UnificationEntry(spring, Periodicium),
            MAX to UnificationEntry(spring, RealizedQuantumFoamShard)))

        /**
         * Electric Motor
         *
         * | Tier     | Old Item           | New Item           |
         * |----------|--------------------|--------------------|
         * | ULV (0)  | -                  | -                  |
         * | LV (1)   | LV Electric Motor  | LV Electric Motor  |
         * | MV (2)   | MV Electric Motor  | MV Electric Motor  |
         * | HV (3)   | HV Electric Motor  | HV Electric Motor  |
         * | EV (4)   | EV Electric Motor  | EV Electric Motor  |
         * | IV (5)   | IV Electric Motor  | IV Electric Motor  |
         * | LuV (6)  | LuV Electric Motor | LuV Electric Motor |
         * | ZPM (7)  | ZPM Electric Motor | ZPM Electric Motor |
         * | UV (8)   | UV Electric Motor  | UV Electric Motor  |
         * | UHV (9)  | UHV Electric Motor | UHV Electric Motor |
         * | UEV (10) | UEV Electric Motor | UEV Electric Motor |
         * | UIV (11) | UIV Electric Motor | UIV Electric Motor |
         * | UXV (12) | UXV Electric Motor | UXV Electric Motor |
         * | OpV (13) | OpV Electric Motor | OpV Electric Motor |
         * | MAX (14) | -                  | MAX Electric Motor |
         */
        CraftingComponent.MOTOR.appendIngredients(mapOf(
            MAX to ELECTRIC_MOTOR_MAX.stack()))

        /**
         * Electric Piston
         *
         * | Tier     | Old Item            | New Item            |
         * |----------|---------------------|---------------------|
         * | ULV (0)  | -                   | -                   |
         * | LV (1)   | LV Electric Piston  | LV Electric Piston  |
         * | MV (2)   | MV Electric Piston  | MV Electric Piston  |
         * | HV (3)   | HV Electric Piston  | HV Electric Piston  |
         * | EV (4)   | EV Electric Piston  | EV Electric Piston  |
         * | IV (5)   | IV Electric Piston  | IV Electric Piston  |
         * | LuV (6)  | LuV Electric Piston | LuV Electric Piston |
         * | ZPM (7)  | ZPM Electric Piston | ZPM Electric Piston |
         * | UV (8)   | UV Electric Piston  | UV Electric Piston  |
         * | UHV (9)  | UHV Electric Piston | UHV Electric Piston |
         * | UEV (10) | UEV Electric Piston | UEV Electric Piston |
         * | UIV (11) | UIV Electric Piston | UIV Electric Piston |
         * | UXV (12) | UXV Electric Piston | UXV Electric Piston |
         * | OpV (13) | OpV Electric Piston | OpV Electric Piston |
         * | MAX (14) | -                   | MAX Electric Piston |
         */
        CraftingComponent.PISTON.appendIngredients(mapOf(
            MAX to ELECTRIC_PISTON_MAX.stack()))

        /**
         * Electric Pump
         *
         * | Tier     | Old Item          | New Item          |
         * |----------|-------------------|-------------------|
         * | ULV (0)  | -                 | -                 |
         * | LV (1)   | LV Electric Pump  | LV Electric Pump  |
         * | MV (2)   | MV Electric Pump  | MV Electric Pump  |
         * | HV (3)   | HV Electric Pump  | HV Electric Pump  |
         * | EV (4)   | EV Electric Pump  | EV Electric Pump  |
         * | IV (5)   | IV Electric Pump  | IV Electric Pump  |
         * | LuV (6)  | LuV Electric Pump | LuV Electric Pump |
         * | ZPM (7)  | ZPM Electric Pump | ZPM Electric Pump |
         * | UV (8)   | UV Electric Pump  | UV Electric Pump  |
         * | UHV (9)  | UHV Electric Pump | UHV Electric Pump |
         * | UEV (10) | UEV Electric Pump | UEV Electric Pump |
         * | UIV (11) | UIV Electric Pump | UIV Electric Pump |
         * | UXV (12) | UXV Electric Pump | UXV Electric Pump |
         * | OpV (13) | OpV Electric Pump | OpV Electric Pump |
         * | MAX (14) | -                 | MAX Electric Pump |
         */
        CraftingComponent.PUMP.appendIngredients(mapOf(
            MAX to ELECTRIC_PUMP_MAX.stack()))

        /**
         * Conveyor Module
         *
         * | Tier     | Old Item            | New Item            |
         * |----------|---------------------|---------------------|
         * | ULV (0)  | -                   | -                   |
         * | LV (1)   | LV Conveyor Module  | LV Conveyor Module  |
         * | MV (2)   | MV Conveyor Module  | MV Conveyor Module  |
         * | HV (3)   | HV Conveyor Module  | HV Conveyor Module  |
         * | EV (4)   | EV Conveyor Module  | EV Conveyor Module  |
         * | IV (5)   | IV Conveyor Module  | IV Conveyor Module  |
         * | LuV (6)  | LuV Conveyor Module | LuV Conveyor Module |
         * | ZPM (7)  | ZPM Conveyor Module | ZPM Conveyor Module |
         * | UV (8)   | UV Conveyor Module  | UV Conveyor Module  |
         * | UHV (9)  | UHV Conveyor Module | UHV Conveyor Module |
         * | UEV (10) | UEV Conveyor Module | UEV Conveyor Module |
         * | UIV (11) | UIV Conveyor Module | UIV Conveyor Module |
         * | UXV (12) | UXV Conveyor Module | UXV Conveyor Module |
         * | OpV (13) | OpV Conveyor Module | OpV Conveyor Module |
         * | MAX (14) | -                   | MAX Conveyor Module |
         */
        CraftingComponent.CONVEYOR.appendIngredients(mapOf(
            MAX to CONVEYOR_MODULE_MAX.stack()))

        /**
         * Robot Arm
         *
         * | Tier     | Old Item      | New Item      |
         * |----------|---------------|---------------|
         * | ULV (0)  | -             | -             |
         * | LV (1)   | LV Robot Arm  | LV Robot Arm  |
         * | MV (2)   | MV Robot Arm  | MV Robot Arm  |
         * | HV (3)   | HV Robot Arm  | HV Robot Arm  |
         * | EV (4)   | EV Robot Arm  | EV Robot Arm  |
         * | IV (5)   | IV Robot Arm  | IV Robot Arm  |
         * | LuV (6)  | LuV Robot Arm | LuV Robot Arm |
         * | ZPM (7)  | ZPM Robot Arm | ZPM Robot Arm |
         * | UV (8)   | UV Robot Arm  | UV Robot Arm  |
         * | UHV (9)  | UHV Robot Arm | UHV Robot Arm |
         * | UEV (10) | UEV Robot Arm | UEV Robot Arm |
         * | UIV (11) | UIV Robot Arm | UIV Robot Arm |
         * | UXV (12) | UXV Robot Arm | UXV Robot Arm |
         * | OpV (13) | OpV Robot Arm | OpV Robot Arm |
         * | MAX (14) | -             | MAX Robot Arm |
         */
        CraftingComponent.ROBOT_ARM.appendIngredients(mapOf(
            MAX to ROBOT_ARM_MAX.stack()))

        /**
         * Emitter
         *
         * | Tier     | Old Item    | New Item    |
         * |----------|-------------|-------------|
         * | ULV (0)  | -           | -           |
         * | LV (1)   | LV Emitter  | LV Emitter  |
         * | MV (2)   | MV Emitter  | MV Emitter  |
         * | HV (3)   | HV Emitter  | HV Emitter  |
         * | EV (4)   | EV Emitter  | EV Emitter  |
         * | IV (5)   | IV Emitter  | IV Emitter  |
         * | LuV (6)  | LuV Emitter | LuV Emitter |
         * | ZPM (7)  | ZPM Emitter | ZPM Emitter |
         * | UV (8)   | UV Emitter  | UV Emitter  |
         * | UHV (9)  | UHV Emitter | UHV Emitter |
         * | UEV (10) | UEV Emitter | UEV Emitter |
         * | UIV (11) | UIV Emitter | UIV Emitter |
         * | UXV (12) | UXV Emitter | UXV Emitter |
         * | OpV (13) | OpV Emitter | OpV Emitter |
         * | MAX (14) | -           | MAX Emitter |
         */
        CraftingComponent.EMITTER.appendIngredients(mapOf(
            MAX to EMITTER_MAX.stack()))

        /**
         * Sensor
         *
         * | Tier     | Old Item   | New Item   |
         * |----------|------------|------------|
         * | ULV (0)  | -          | -          |
         * | LV (1)   | LV Sensor  | LV Sensor  |
         * | MV (2)   | MV Sensor  | MV Sensor  |
         * | HV (3)   | HV Sensor  | HV Sensor  |
         * | EV (4)   | EV Sensor  | EV Sensor  |
         * | IV (5)   | IV Sensor  | IV Sensor  |
         * | LuV (6)  | LuV Sensor | LuV Sensor |
         * | ZPM (7)  | ZPM Sensor | ZPM Sensor |
         * | UV (8)   | UV Sensor  | UV Sensor  |
         * | UHV (9)  | UHV Sensor | UHV Sensor |
         * | UEV (10) | UEV Sensor | UEV Sensor |
         * | UIV (11) | UIV Sensor | UIV Sensor |
         * | UXV (12) | UXV Sensor | UXV Sensor |
         * | OpV (13) | OpV Sensor | OpV Sensor |
         * | MAX (14) | -          | MAX Sensor |
         */
        CraftingComponent.SENSOR.appendIngredients(mapOf(
            MAX to SENSOR_MAX.stack()))

        /**
         * Field Generator
         *
         * | Tier     | Old Item            | New Item            |
         * |----------|---------------------|---------------------|
         * | ULV (0)  | -                   | -                   |
         * | LV (1)   | LV Field Generator  | LV Field Generator  |
         * | MV (2)   | MV Field Generator  | MV Field Generator  |
         * | HV (3)   | HV Field Generator  | HV Field Generator  |
         * | EV (4)   | EV Field Generator  | EV Field Generator  |
         * | IV (5)   | IV Field Generator  | IV Field Generator  |
         * | LuV (6)  | LuV Field Generator | LuV Field Generator |
         * | ZPM (7)  | ZPM Field Generator | ZPM Field Generator |
         * | UV (8)   | UV Field Generator  | UV Field Generator  |
         * | UHV (9)  | UHV Field Generator | UHV Field Generator |
         * | UEV (10) | UEV Field Generator | UEV Field Generator |
         * | UIV (11) | UIV Field Generator | UIV Field Generator |
         * | UXV (12) | UXV Field Generator | UXV Field Generator |
         * | OpV (13) | OpV Field Generator | OpV Field Generator |
         * | MAX (14) | -                   | MAX Field Generator |
         */
        CraftingComponent.FIELD_GENERATOR.appendIngredients(mapOf(
            MAX to FIELD_GENERATOR_MAX.stack()))
    }

    // @formatter:on
}