package magicbook.gtlitecore.loader.recipe.component

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
import gregtech.common.items.MetaItems
import gregtech.common.items.MetaItems.FLUID_REGULATOR_EV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_HV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_IV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_LUV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_LV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_MV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_UV
import gregtech.common.items.MetaItems.FLUID_REGULATOR_ZPM
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlackDwarfMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChromiumGermaniumTellurideMagnetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicFabric
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Creon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LutetiumManganeseGermanium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Magnetium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetohydrodynamicallyConstrainedStarMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Periodicium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SamariumCobalt
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpaceTime
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumBariumCalciumCuprate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Universium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.common.item.GTLiteMetaItems
import net.minecraft.init.Blocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class CraftingComponents
{

    companion object
    {

        // FLUID_REGULATOR
        val FLUID_REGULATOR = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(pipeTinyFluid, Bronze),
            LV  to FLUID_REGULATOR_LV.stackForm,
            MV  to FLUID_REGULATOR_MV.stackForm,
            HV  to FLUID_REGULATOR_HV.stackForm,
            EV  to FLUID_REGULATOR_EV.stackForm,
            IV  to FLUID_REGULATOR_IV.stackForm,
            LuV to FLUID_REGULATOR_LUV.stackForm,
            ZPM to FLUID_REGULATOR_ZPM.stackForm,
            UV  to FLUID_REGULATOR_UV.stackForm,
            UHV to FLUID_REGULATOR_UV.stackForm,
            UEV to FLUID_REGULATOR_UV.stackForm,
            UIV to FLUID_REGULATOR_UV.stackForm,
            UXV to FLUID_REGULATOR_UV.stackForm,
            OpV to FLUID_REGULATOR_UV.stackForm,
            MAX to FLUID_REGULATOR_UV.stackForm,
        ).toMap())

        // GEAR
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
        //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
        //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
        //      12: Creon, 13: BlackDwarfMatter, 14: TODO
        val GEAR = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(gear, Tritanium)
        ).toMap())

        // GEAR_SMALL
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
        //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
        //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon
        //      12: Creon, 13: BlackDwarfMatter, 14: TODO
        val GEAR_SMALL = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(gearSmall, Tritanium)
        ).toMap())

        // PLATE_DENSE
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
        //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
        //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
        //      12: Creon, 13: BlackDwarfMatter, 14: TODO
        val PLATE_DENSE = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(plateDense, Tritanium)
        ).toMap())

        // STICK
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
        //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
        //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
        //      12: Creon, 13: BlackDwarfMatter, 14: TODO
        val STICK = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(stick, Tritanium)
        ).toMap())

        // STICK_LONG
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
        //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
        //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
        //      12: Creon, 13: BlackDwarfMatter, 14: TODO
        val STICK_LONG = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(stickLong, Tritanium)
        ).toMap())

        // SPRING_SMALL
        // New: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
        //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
        //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
        //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
        val SPRING_SMALL = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(springSmall, Tritanium)
        ).toMap())

        // CABLE_COMPONENT
        // New: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
        //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
        //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyA, 12: SuperheavyAlloyB,
        //      13: Periodicium, 14: TODO
        val CABLE_COMPONENT = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(cableGtSingle, Tritanium)
        ).toMap())

        // PLATE_COMPONENT
        // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium
        //      5: TungstenSteel, 6: HSSS, 7: Osmiridium, 8: Tritanium, 9: Adamantium,
        //      10: CosmicNeutronium, 11: HeavyQuarkDegenerateMatter, 12: TranscendentMetal,
        //      13: MagnetohydrodynamicallyConstrainedStarMatter, 14: TODO
        val PLATE_COMPONENT = CraftingComponent.Component(sequenceOf(
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
            // MAX to UnificationEntry(plate, Tritanium)
        ).toMap())

        fun setCraftingComponents()
        {
            // WIRE_ELECTRIC
            // Old: 0-1: Gold, 2: Silver, 3: Electrum, 4: Platinum, 5-8: Osmium.
            // New: 0-1: Gold, 2: Silver, 3: Electrum, 4: Platinum, 5-6: Osmium,
            //      7-8: Naquadah, 9: Trinium, 10: Tritanium, 11: Adamantium,
            //      12: Infinity, 13: HalkoniteSteel, 14: SpaceTime
            CraftingComponent.WIRE_ELECTRIC.appendIngredients(sequenceOf(
                ZPM to UnificationEntry(wireGtSingle, Naquadah),
                UV  to UnificationEntry(wireGtSingle, Naquadah),
                UHV to UnificationEntry(wireGtSingle, Trinium),
                UEV to UnificationEntry(wireGtSingle, Tritanium),
                UIV to UnificationEntry(wireGtSingle, Adamantium),
                UXV to UnificationEntry(wireGtSingle, Infinity),
                OpV to UnificationEntry(wireGtSingle, HalkoniteSteel),
                MAX to UnificationEntry(wireGtSingle, SpaceTime)
            ).toMap())

            // WIRE_QUAD
            // Old: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate
            // New: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.WIRE_QUAD.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtQuadruple, Europium),
                UEV to UnificationEntry(wireGtQuadruple, Seaborgium),
                UIV to UnificationEntry(wireGtQuadruple, SuperheavyAlloyA),
                UXV to UnificationEntry(wireGtQuadruple, SuperheavyAlloyB),
                OpV to UnificationEntry(wireGtQuadruple, Periodicium),
                // MAX to UnificationEntry(wireGtQuadruple, Tritanium)
            ).toMap())

            // WIRE_OCT
            // Old: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate
            // New: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.WIRE_OCT.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtOctal, Europium),
                UEV to UnificationEntry(wireGtOctal, Seaborgium),
                UIV to UnificationEntry(wireGtOctal, SuperheavyAlloyA),
                UXV to UnificationEntry(wireGtOctal, SuperheavyAlloyB),
                OpV to UnificationEntry(wireGtOctal, Periodicium),
                // MAX to UnificationEntry(wireGtOctal, Tritanium)
            ).toMap())

            // WIRE_HEX
            // Old: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate
            // New: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.WIRE_HEX.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtHex, Europium),
                UEV to UnificationEntry(wireGtHex, Seaborgium),
                UIV to UnificationEntry(wireGtHex, SuperheavyAlloyA),
                UXV to UnificationEntry(wireGtHex, SuperheavyAlloyB),
                OpV to UnificationEntry(wireGtHex, Periodicium),
                // MAX to UnificationEntry(wireGtHex, Tritanium)
            ).toMap())

            // CABLE
            // Old: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium
            // New: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.CABLE.appendIngredients(sequenceOf(
                UEV to UnificationEntry(cableGtSingle, Seaborgium),
                UIV to UnificationEntry(cableGtSingle, SuperheavyAlloyA),
                UXV to UnificationEntry(cableGtSingle, SuperheavyAlloyB),
                OpV to UnificationEntry(cableGtSingle, Periodicium),
                // MAX to UnificationEntry(cableGtSingle, Tritanium)
            ).toMap())

            // CABLE_QUAD
            // Old: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium
            // New: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.CABLE_QUAD.appendIngredients(sequenceOf(
                UEV to UnificationEntry(cableGtQuadruple, Seaborgium),
                UIV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
                UXV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
                OpV to UnificationEntry(cableGtQuadruple, Periodicium),
                // MAX to UnificationEntry(cableGtQuadruple, Tritanium)
            ).toMap())

            // CABLE_OCT
            // Old: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium
            // New: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.CABLE_OCT.appendIngredients(sequenceOf(
                UEV to UnificationEntry(cableGtOctal, Seaborgium),
                UIV to UnificationEntry(cableGtOctal, SuperheavyAlloyA),
                UXV to UnificationEntry(cableGtOctal, SuperheavyAlloyB),
                OpV to UnificationEntry(cableGtOctal, Periodicium),
                // MAX to UnificationEntry(cableGtOctal, Tritanium)
            ).toMap())

            // CABLE_HEX
            // Old: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium
            // New: 0: RedAlloy, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Platinum,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.CABLE_HEX.appendIngredients(sequenceOf(
                UEV to UnificationEntry(cableGtHex, Seaborgium),
                UIV to UnificationEntry(cableGtHex, SuperheavyAlloyA),
                UXV to UnificationEntry(cableGtHex, SuperheavyAlloyB),
                OpV to UnificationEntry(cableGtHex, Periodicium),
                // MAX to UnificationEntry(cableGtHex, Tritanium)
            ).toMap())

            // CABLE_TIER_UP
            // Old: 0: Tin, 1: Copper, 2: Gold, 3: Aluminium, 4: Platinum, 5: NiobiumTitanium,
            //      6: VanadiumGallium, 7: YttriumBariumCuprate, 8: Europium
            // New: 0: Tin, 1: Copper, 2: Gold, 3: Aluminium, 4: Platinum, 5: NiobiumTitanium,
            //      6: VanadiumGallium, 7: YttriumBariumCuprate, 8: Europium, 9: Seaborgium,
            //      10: SuperheavyAlloyLight, 11: SuperheavyAlloyHeavy, 12: Periodicium,
            //      13-14: TODO
            CraftingComponent.CABLE_TIER_UP.appendIngredients(sequenceOf(
                UHV to UnificationEntry(cableGtSingle, Seaborgium),
                UEV to UnificationEntry(cableGtSingle, SuperheavyAlloyA),
                UIV to UnificationEntry(cableGtSingle, SuperheavyAlloyB),
                UXV to UnificationEntry(cableGtSingle, Periodicium),
                // OpV to UnificationEntry(cableGtSingle, Tritanium),
                // MAX to UnificationEntry(cableGtSingle, Tritanium)
            ).toMap())

            // CABLE_QUAD_TIER_UP
            // Old: 0: Tin, 1: Copper, 2: Gold, 3: Aluminium, 4: Platinum, 5: NiobiumTitanium,
            //      6: VanadiumGallium, 7: YttriumBariumCuprate, 8: Europium
            // New: 0: Tin, 1: Copper, 2: Gold, 3: Aluminium, 4: Platinum, 5: NiobiumTitanium,
            //      6: VanadiumGallium, 7: YttriumBariumCuprate, 8: Europium, 9: Seaborgium,
            //      10: SuperheavyAlloyLight, 11: SuperheavyAlloyHeavy, 12: Periodicium,
            //      13-14: TODO
            CraftingComponent.CABLE_QUAD_TIER_UP.appendIngredients(sequenceOf(
                UHV to UnificationEntry(cableGtQuadruple, Seaborgium),
                UEV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
                UIV to UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
                UXV to UnificationEntry(cableGtQuadruple, Periodicium),
                // OpV to UnificationEntry(cableGtQuadruple, Tritanium),
                // MAX to UnificationEntry(cableGtQuadruple, Tritanium)
            ).toMap())

            // PIPE_NORMAL
            // Old: 0-1: Bronze, 2: Steel, 3: StainlessSteel, 4: Titanium, 5: TungstenSteel,
            //      6: NiobiumTitanium, 7: Iridium, 8: Naquadah
            // New: 0-1: Bronze, 2: Steel, 3: StainlessSteel, 4: Titanium, 5: TungstenSteel,
            //      6: NiobiumTitanium, 7: Iridium, 8: Naquadah, 9: Europium, 10: Duranium,
            //      11: Neutronium, 12: HeavyQuarkDegenerateMatter,
            //      13: QuantumchromodynamicallyConfinedMatter, 14: TranscendentMetal
            CraftingComponent.PIPE_NORMAL.appendIngredients(sequenceOf(
                UHV to UnificationEntry(pipeNormalFluid, Europium),
                UEV to UnificationEntry(pipeNormalFluid, Duranium),
                UIV to UnificationEntry(pipeNormalFluid, Neutronium),
                UXV to UnificationEntry(pipeNormalFluid, HeavyQuarkDegenerateMatter),
                OpV to UnificationEntry(pipeNormalFluid, QuantumchromodynamicallyConfinedMatter),
                MAX to UnificationEntry(pipeNormalFluid, TranscendentMetal)
            ).toMap())

            // PIPE_LARGE
            // Old: 0-1: Bronze, 2: Steel, 3: StainlessSteel, 4: Titanium, 5: TungstenSteel,
            //      6: NiobiumTitanium, 7: Iridium, 8: Naquadah
            // New: 0-1: Bronze, 2: Steel, 3: StainlessSteel, 4: Titanium, 5: TungstenSteel,
            //      6: NiobiumTitanium, 7: Iridium, 8: Naquadah, 9: Europium, 10: Duranium,
            //      11: Neutronium, 12: HeavyQuarkDegenerateMatter,
            //      13: QuantumchromodynamicallyConfinedMatter, 14: TranscendentMetal
            CraftingComponent.PIPE_LARGE.appendIngredients(sequenceOf(
                UHV to UnificationEntry(pipeLargeFluid, Europium),
                UEV to UnificationEntry(pipeLargeFluid, Duranium),
                UIV to UnificationEntry(pipeLargeFluid, Neutronium),
                UXV to UnificationEntry(pipeLargeFluid, HeavyQuarkDegenerateMatter),
                OpV to UnificationEntry(pipeLargeFluid, QuantumchromodynamicallyConfinedMatter),
                MAX to UnificationEntry(pipeLargeFluid, TranscendentMetal)
            ).toMap())

            // GLASS
            // Old: 0-2: Glass, 3-4: Tempered Glass, 5-6: Laminated Glass, 7-8: Fusion Glass
            // New: 0: Glass, 1-14: glass ore dictionaries.
            CraftingComponent.GLASS.appendIngredients(sequenceOf(
                ULV to Blocks.GLASS,
                LV  to "glassLv",
                MV  to "glassMv",
                HV  to "glassHv",
                EV  to "glassEv",
                IV  to "glassIv",
                LuV to "glassLuv",
                ZPM to "glassZpm",
                UV  to "glassUv",
                UHV to "glassUhv",
                UEV to "glassUev",
                UIV to "glassUiv",
                UXV to "glassUxv",
                OpV to "glassOpv",
                MAX to "glassMax"
            ).toMap())

            // PLATE
            // Old: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
            //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
            //      8: Darmstadtium, 9: Neutronium
            // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
            //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
            //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
            //      12: Creon, 13: BlackDwarfMatter, 14: TODO
            CraftingComponent.PLATE.appendIngredients(sequenceOf(
                UEV to UnificationEntry(plate, Vibranium),
                UIV to UnificationEntry(plate, Shirabon),
                UXV to UnificationEntry(plate, Creon),
                OpV to UnificationEntry(plate, BlackDwarfMatter),
                // MAX to UnificationEntry(plate, Tritanium)
            ).toMap())

            // DOUBLE_PLATE
            // Old: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
            //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
            //      8: Darmstadtium, 9: Neutronium
            // New: 0: WroughtIron, 1: Steel, 2: Aluminium, 3: StainlessSteel, 4: Titanium,
            //      5: TungstenSteel, 6: RhodiumPlatedPalladium, 7: NaquadahAlloy,
            //      8: Darmstadtium, 9: Neutronium, 10: Vibranium, 11: Shirabon,
            //      12: Creon, 13: BlackDwarfMatter, 14: TODO
            CraftingComponent.DOUBLE_PLATE.appendIngredients(sequenceOf(
                UEV to UnificationEntry(plateDouble, Vibranium),
                UIV to UnificationEntry(plateDouble, Shirabon),
                UXV to UnificationEntry(plateDouble, Creon),
                OpV to UnificationEntry(plateDouble, BlackDwarfMatter),
                // MAX to UnificationEntry(plateDouble, Tritanium)
            ).toMap())

            // HULL_PLATE
            // Old: 0: Wood, 1-2: WroughtIron, 3-4: Polyethylene (PE),
            //      5-6: Polytetrafluoroethylene (PTFE), 7-9: Polybenzimidazole (PBI),
            // New: 0: Wood, 1-2: WroughtIron, 3-4: Polyethylene (PE),
            //      5-6: Polytetrafluoroethylene (PTFE), 7-8: Polybenzimidazole (PBI),
            //      9-10: Kevlar, 11-12: FullerenePolymerMatrix (FPM), 13-14: CosmicFabric
            CraftingComponent.HULL_PLATE.appendIngredients(sequenceOf(
                UHV to UnificationEntry(plate, Kevlar),
                UEV to UnificationEntry(plate, Kevlar),
                UIV to UnificationEntry(plate, FullerenePolymerMatrix),
                UXV to UnificationEntry(plate, FullerenePolymerMatrix),
                OpV to UnificationEntry(plate, CosmicFabric),
                MAX to UnificationEntry(plate, CosmicFabric)
            ).toMap())

            // ROTOR
            // Old: 0-1: Tin, 2: Bronze, 3: Steel, 4: StainlessSteel, 5: TungstenSteel,
            //      6: RhodiumPlatedPalladium, 7: NaquadahAlloy, 8: Darmstadtium
            // New: 0-1: Tin, 2: Bronze, 3: Steel, 4: StainlessSteel, 5: TungstenSteel,
            //      6: RhodiumPlatedPalladium, 7: NaquadahAlloy, 8: Darmstadtium,
            //      9: Neutronium, 10: Vibranium, 11: Shirabon, 12: Creon, 13: BlackDwarfMatter,
            //      14: TODO
            CraftingComponent.ROTOR.appendIngredients(sequenceOf(
                UHV to UnificationEntry(rotor, Neutronium),
                UEV to UnificationEntry(rotor, Vibranium),
                UIV to UnificationEntry(rotor, Shirabon),
                UXV to UnificationEntry(rotor, Creon),
                OpV to UnificationEntry(rotor, BlackDwarfMatter),
                // MAX to UnificationEntry(rotor, Tritanium)
            ).toMap())

            // GRINDER
            // Old: 0-2: Diamond, 3-4: COMPONENT_GRINDER_DIAMOND,
            //      5: COMPONENT_GRINDER_TUNGSTEN
            // New: 0-2 Diamond, 3-4: COMPONENT_GRINDER_DIAMOND,
            //      5-8: COMPONENT_GRINDER_TUNGSTEN, 9-11: COMPONENT_GRINDER_BORON_NITRIDE,
            //      12-14: COMPONENT_GRINDER_HALKONITE_STEEL
            CraftingComponent.GRINDER.appendIngredients(sequenceOf(
                LuV to MetaItems.COMPONENT_GRINDER_TUNGSTEN.stackForm,
                ZPM to MetaItems.COMPONENT_GRINDER_TUNGSTEN.stackForm,
                UV  to MetaItems.COMPONENT_GRINDER_TUNGSTEN.stackForm,
                UHV to GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE.stackForm,
                UEV to GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE.stackForm,
                UIV to GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE.stackForm,
                UXV to GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL.stackForm,
                OpV to GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL.stackForm,
                MAX to GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL.stackForm
            ).toMap())

            // SAWBLADE
            // Old: 0: Bronze, 1: CobaltBrass, 2: VanadiumSteel, 3: BlueSteel, 4: Ultimet,
            //      5: TungstenCarbide, 6: HSSE, 7: NaquadahAlloy, 8: Duranium
            // New: 0: Bronze, 1: CobaltBrass, 2: VanadiumSteel, 3: BlueSteel, 4: Ultimet,
            //      5: TungstenCarbide, 6: HSSE, 7: NaquadahAlloy, 8: Duranium,
            //      9: CubicBoronNitride, 10: HDCS, 11: HastelloyK243, 12: Creon, 13-14: TODO
            CraftingComponent.SAWBLADE.appendIngredients(sequenceOf(
                UHV to UnificationEntry(toolHeadBuzzSaw, CubicBoronNitride),
                UEV to UnificationEntry(toolHeadBuzzSaw, HDCS),
                UIV to UnificationEntry(toolHeadBuzzSaw, HastelloyK243),
                UXV to UnificationEntry(toolHeadBuzzSaw, Creon),
                // OpV to UnificationEntry(toolHeadBuzzSaw, Tritanium),
                // MAX to UnificationEntry(toolHeadBuzzSaw, Tritanium)
            ).toMap())

            // COIL_HEATING
            // Old: 0-1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome, 5: RTMAlloy,
            //      6: HSSG, 7: Naquadah, 8: NaquadahAlloy
            // New: 0-1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome, 5: RTMAlloy,
            //      6: HSSG, 7: Naquadah, 8: NaquadahAlloy, 9: Tritanium, 10: Adamantium,
            //      11: Infinity, 12: HalkoniteSteel, 13: SpaceTime, 14: TODO
            CraftingComponent.COIL_HEATING.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtDouble, Tritanium),
                UEV to UnificationEntry(wireGtDouble, Adamantium),
                UIV to UnificationEntry(wireGtDouble, Infinity),
                UXV to UnificationEntry(wireGtDouble, HalkoniteSteel),
                OpV to UnificationEntry(wireGtDouble, SpaceTime),
                // MAX to UnificationEntry(wireGtDouble, Tritanium)
            ).toMap())

            // COIL_HEATING_DOUBLE
            // Old: 0-1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome, 5: RTMAlloy,
            //      6: HSSG, 7: Naquadah, 8: NaquadahAlloy
            // New: 0-1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome, 5: RTMAlloy,
            //      6: HSSG, 7: Naquadah, 8: NaquadahAlloy, 9: Tritanium, 10: Adamantium,
            //      11: Infinity, 12: HalkoniteSteel, 13: SpaceTime, 14: TODO
            CraftingComponent.COIL_HEATING_DOUBLE.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtQuadruple, Tritanium),
                UEV to UnificationEntry(wireGtQuadruple, Adamantium),
                UIV to UnificationEntry(wireGtQuadruple, Infinity),
                UXV to UnificationEntry(wireGtQuadruple, HalkoniteSteel),
                OpV to UnificationEntry(wireGtQuadruple, SpaceTime),
                // MAX to UnificationEntry(wireGtQuadruple, Tritanium)
            ).toMap())

            // COIL_ELECTRIC
            // Old: 0-1: Tin, 2: Copper, 3: Silver, 4: Steel, 5: Graphene,
            //      6: NiobiumNitride, 7: VanadiumGallium, 8: YttriumBariumCuprate
            // New: 0-1: Tin, 2: Copper, 3: Silver, 4: Steel, 5: Graphene,
            //      6: NiobiumNitride, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: ThalliumBariumCalciumCuprate, 10: CosmicNeutronium,
            //      11: Hypogen, 12: MagMatter, 13: Universium, 14: TODO
            CraftingComponent.COIL_ELECTRIC.appendIngredients(sequenceOf(
                UHV to UnificationEntry(wireGtOctal, ThalliumBariumCalciumCuprate),
                UEV to UnificationEntry(wireGtOctal, CosmicNeutronium),
                UIV to UnificationEntry(wireGtHex, Hypogen),
                UXV to UnificationEntry(wireGtHex, MagMatter),
                OpV to UnificationEntry(wireGtHex, Universium),
                // MAX to UnificationEntry(wireGtHex, Tritanium)
            ).toMap())

            // STICK_MAGNETIC
            // Old: 0-1: IronMagnetic, 2-3: SteelMagnetic, 4-8: NeodymiumMagnetic
            // New: 0-1: IronMagnetic, 2-3: SteelMagnetic, 4-7: NeodymiumMagnetic,
            //      8-11: SamariumMagnetic, 12-14: ChromiumGermaniumTellurideMagnetic
            CraftingComponent.STICK_MAGNETIC.appendIngredients(sequenceOf(
                UV to UnificationEntry(stick, SamariumMagnetic),
                UHV to UnificationEntry(stick, SamariumMagnetic),
                UEV to UnificationEntry(stickLong, SamariumMagnetic),
                UIV to UnificationEntry(stickLong, SamariumMagnetic),
                UXV to UnificationEntry(stick, ChromiumGermaniumTellurideMagnetic),
                OpV to UnificationEntry(stick, ChromiumGermaniumTellurideMagnetic),
                MAX to UnificationEntry(stickLong, ChromiumGermaniumTellurideMagnetic)
            ).toMap())

            // STICK_DISTILLATION
            // Old: 0: Blaze, 1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome,
            //      5: RTMAlloy, 6: HSSG, 7: Naquadah, 8: NaquadahAlloy
            // New: 0: Blaze, 1: Copper, 2: Cupronickel, 3: Kanthal, 4: Nichrome,
            //      5: RTMAlloy, 6: HSSG, 7: Naquadah, 8: NaquadahAlloy, 9: Trinium,
            //      10: Tritanium, 11: Adamantium, 12: Infinity, 13: HalkoniteSteel,
            //      14: SpaceTime
            CraftingComponent.STICK_DISTILLATION.appendIngredients(sequenceOf(
                UHV to UnificationEntry(spring, Trinium),
                UEV to UnificationEntry(spring, Tritanium),
                UIV to UnificationEntry(spring, Adamantium),
                UXV to UnificationEntry(spring, Infinity),
                OpV to UnificationEntry(spring, HalkoniteSteel),
                MAX to UnificationEntry(spring, SpaceTime)
            ).toMap())

            // STICK_ELECTROMAGNETIC
            // Old: 0-1: Iron, 2-3: Steel, 4: Neodymium
            // New: 0-1: Iron, 2-3: Steel, 4-5: Neodymium, 6-9: VanadiumGallium,
            //      10-11: SamariumCobalt, 12-13: LutetiumManganeseGermanium, 14: Magnetium
            CraftingComponent.STICK_ELECTROMAGNETIC.appendIngredients(sequenceOf(
                IV  to UnificationEntry(stick, Neodymium),
                LuV to UnificationEntry(stick, VanadiumGallium),
                ZPM to UnificationEntry(stick, VanadiumGallium),
                UV  to UnificationEntry(stickLong, VanadiumGallium),
                UHV to UnificationEntry(stickLong, VanadiumGallium),
                UEV to UnificationEntry(stick, SamariumCobalt),
                UIV to UnificationEntry(stick, SamariumCobalt),
                UXV to UnificationEntry(stickLong, LutetiumManganeseGermanium),
                OpV to UnificationEntry(stickLong, LutetiumManganeseGermanium),
                MAX to UnificationEntry(stick, Magnetium)
            ).toMap())

            // STICK_RADIOACTIVE
            // Old: 4: Uranium235, 5: Plutonium241, 6: NaquadahEnriched, 7: Americium
            // New: 0-1: Uranium235, 2: Uranium238, 3: Plutonium239, 4: Plutonium241,
            //      5: Naquadah, 6: NaquadahEnriched, 7: Naquadria, 8: Americium,
            //      9: Rutherfordium, 10: Dubnium, 11: MetastableFlerovium,
            //      12: MetastableHassium, 13: SuperheavyAlloyLight,
            //      14: SuperheavyAlloyHeavy
            CraftingComponent.STICK_RADIOACTIVE.appendIngredients(sequenceOf(
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
                MAX to UnificationEntry(stick, SuperheavyAlloyB)
            ).toMap())

            // PIPE_REACTOR
            // Old: 0-2: Glass, 3-5: Polyethylene (PE), 6-8: Polytetrafluoroethylene (PTFE)
            // New: 0-2: Glass, 3-5: Polyethylene (PE), 6-8: Polytetrafluoroethylene (PTFE),
            //      9-11: Polybenzimidazole (PBI), 12-14: Kevlar
            CraftingComponent.PIPE_REACTOR.appendIngredients(sequenceOf(
                UHV to UnificationEntry(pipeNormalFluid, Polybenzimidazole),
                UEV to UnificationEntry(pipeLargeFluid, Polybenzimidazole),
                UIV to UnificationEntry(pipeHugeFluid, Polybenzimidazole),
                UXV to UnificationEntry(pipeNormalFluid, Kevlar),
                OpV to UnificationEntry(pipeLargeFluid, Kevlar),
                MAX to UnificationEntry(pipeHugeFluid, Kevlar)
            ).toMap())

            // POWER_COMPONENT
            // Old: 0-2: ULPIC, 3: LPIC, 4: PIC, 5-6: HPIC, 7-9: UHPIC
            // New: 0-2: ULPIC, 3: LPIC, 4: PIC, 5-6: HPIC, 7-8: UHPIC,
            //      9-10: NPIC, 11-12: PPIC, 13-14: FPIC
            CraftingComponent.POWER_COMPONENT.appendIngredients(sequenceOf(
                UHV to GTLiteMetaItems.NANO_PIC_CHIP.stackForm,
                UEV to GTLiteMetaItems.NANO_PIC_CHIP.stackForm,
                UIV to GTLiteMetaItems.PICO_PIC_CHIP.stackForm,
                UXV to GTLiteMetaItems.PICO_PIC_CHIP.stackForm,
                OpV to GTLiteMetaItems.FEMTO_PIC_CHIP.stackForm,
                MAX to GTLiteMetaItems.FEMTO_PIC_CHIP.stackForm
            ).toMap())

            // VOLTAGE_COIL
            // Old: 0: VOLTAGE_COIL_ULV, 1: VOLTAGE_COIL_LV, 2: VOLTAGE_COIL_MV, 3: VOLTAGE_COIL_HV,
            //      4: VOLTAGE_COIL_EV, 5: VOLTAGE_COIL_IV, 6: VOLTAGE_COIL_LuV, 7: VOLTAGE_COIL_ZPM,
            //      8: VOLTAGE_COIL_UV
            // New: 0: VOLTAGE_COIL_ULV, 1: VOLTAGE_COIL_LV, 2: VOLTAGE_COIL_MV, 3: VOLTAGE_COIL_HV,
            //      4: VOLTAGE_COIL_EV, 5: VOLTAGE_COIL_IV, 6: VOLTAGE_COIL_LuV, 7: VOLTAGE_COIL_ZPM,
            //      8: VOLTAGE_COIL_UV, 9: VOLTAGE_COIL_UHV, 10: VOLTAGE_COIL_UEV, 11: VOLTAGE_COIL_UIV,
            //      12: VOLTAGE_COIL_UXV, 13: VOLTAGE_COIL_OpV, 14: VOLTAGE_COIL_MAX
            CraftingComponent.VOLTAGE_COIL.appendIngredients(sequenceOf(
                UHV to GTLiteMetaItems.VOLTAGE_COIL_UHV.stackForm,
                UEV to GTLiteMetaItems.VOLTAGE_COIL_UEV.stackForm,
                UIV to GTLiteMetaItems.VOLTAGE_COIL_UIV.stackForm,
                UXV to GTLiteMetaItems.VOLTAGE_COIL_UXV.stackForm,
                OpV to GTLiteMetaItems.VOLTAGE_COIL_OpV.stackForm,
                MAX to GTLiteMetaItems.VOLTAGE_COIL_MAX.stackForm
            ).toMap())

            // SPRING
            // Old: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium
            // New: 0: Lead, 1: Tin, 2: Copper, 3: Gold, 4: Aluminium, 5: Tungsten,
            //      6: NiobiumTitanium, 7: VanadiumGallium, 8: YttriumBariumCuprate,
            //      9: Europium, 10: Seaborgium, 11: SuperheavyAlloyLight,
            //      12: SuperheavyAlloyHeavy, 13: Periodicium, 14: TODO
            CraftingComponent.SPRING.appendIngredients(sequenceOf(
                UEV to UnificationEntry(spring, Seaborgium),
                UIV to UnificationEntry(spring, SuperheavyAlloyA),
                UXV to UnificationEntry(spring, SuperheavyAlloyB),
                OpV to UnificationEntry(spring, Periodicium),
                // MAX to UnificationEntry(spring, Tritanium)
            ).toMap())

        }

    }

}