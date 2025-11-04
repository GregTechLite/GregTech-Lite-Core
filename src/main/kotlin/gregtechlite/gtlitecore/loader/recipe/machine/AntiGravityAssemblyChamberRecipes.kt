package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonE
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.KaptonK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_ASSEMBLY_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_COMPUTER_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_MAINFRAME_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ARAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HPIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULTIMATE_CIRCUIT_BOARD

object AntiGravityAssemblyChamberRecipes
{

    // @formatter:off

    fun init()
    {

        // region T6: Crystal

        // UV Crystal Mainframe
        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 16)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16)
            .input(WRAP_ADVANCED_SMD_DIODE, 16)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_ADVANCED_SMD_DIODE, 16 * 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 4)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4)
            .input(WRAP_GOOWARE_SMD_DIODE, 4)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 4 * 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, HSSE, 16)
            .input(CRYSTAL_COMPUTER_ZPM, 32)
            .input(WRAP_OPTICAL_SMD_INDUCTOR)
            .input(WRAP_OPTICAL_SMD_CAPACITOR)
            .input(WRAP_OPTICAL_SMD_DIODE)
            .input(WRAP_HPIC_CHIP, 2)
            .input(WRAP_RAM_CHIP, 32)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .output(CRYSTAL_MAINFRAME_UV, 16)
            .EUt(VA[LuV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, HSSE, 16 * 64)
            .input(CRYSTAL_COMPUTER_ZPM, 32 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 64)
            .input(WRAP_HPIC_CHIP, 2 * 64)
            .input(WRAP_RAM_CHIP, 32 * 64)
            .input(wireGtHex, IndiumTinBariumTitaniumCuprate, 8 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 10 * 64))
            .output(CRYSTAL_MAINFRAME_UV, 16 * 64)
            .EUt(VA[LuV])
            .duration(2 * MINUTE * 64) // Original: 10s, Wrapped: 10s * 16 = 160s
            .buildAndRegister()

        // endregion

        // region T7: Wetware

        // UHV Wetware Mainframe
        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_ADVANCED_SMD_DIODE, 32)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 32)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 32)
            .input(WRAP_ADVANCED_SMD_RESISTOR, 32)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(12 * MINUTE) // Original: 60s, Wrapped: 60s * 16 = 960s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_ADVANCED_SMD_DIODE, 32 * 64)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_RESISTOR, 32 * 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 32 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(12 * MINUTE * 64) // Original: 60s, Wrapped: 60s * 16 = 960s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_GOOWARE_SMD_DIODE, 8)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 8)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 8)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 8)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 8)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(6 * MINUTE) // Original: 30s, Wrapped: 30s * 16 = 480s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 8 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 8 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 8 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(6 * MINUTE * 64) // Original: 30s, Wrapped: 30s * 16 = 480s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Tritanium, 16)
            .input(WETWARE_SUPER_COMPUTER_UV, 32)
            .input(WRAP_OPTICAL_SMD_DIODE, 2)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 2)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 2)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 2)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 2)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16)
            .EUt(300_000) // UV
            .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Tritanium, 16 * 64)
            .input(WETWARE_SUPER_COMPUTER_UV, 32 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 2 * 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 2 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 2 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, EnrichedNaquadahTriniumEuropiumDuranide, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 20 * 64))
            .fluidInputs(Polybenzimidazole.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Europium.getFluid(L * 4 * 64 * 64)) // plate (144) * 4 * 64
            .output(WETWARE_MAINFRAME_UHV, 16 * 64)
            .EUt(300_000) // UV
            .duration(3 * MINUTE * 64) // Original: 15s, Wrapped: 15s * 16 = 240s
            .buildAndRegister()

        // endregion

        // region T8: Gooware

        // UHV Gooware Computer
        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 48)
            .input(WRAP_GOOWARE_SMD_DIODE, 8)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 32)
            .EUt(VA[UHV])
            .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD, 64)
            .input(GOOWARE_ASSEMBLY_UV, 48 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 8 * 64)
            .input(WRAP_NOR_CHIP, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtQuadruple, Europium, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .output(GOOWARE_COMPUTER_UHV, 32 * 64)
            .EUt(VA[UHV])
            .duration(8 * MINUTE * 64) // Original: 40s, Wrapped: 40s * 16 = 640s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_ASSEMBLY_UV, 48)
            .input(WRAP_OPTICAL_SMD_DIODE, 2)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .output(GOOWARE_COMPUTER_UHV, 32)
            .EUt(VA[UHV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD, 64)
            .input(GOOWARE_ASSEMBLY_UV, 48 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 2 * 64)
            .input(WRAP_NOR_CHIP, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtQuadruple, Europium, 16 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 16 * 64))
            .output(GOOWARE_COMPUTER_UHV, 32 * 64)
            .EUt(VA[UHV])
            .duration(4 * MINUTE * 64) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // UEV Gooware Mainframe
        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_GOOWARE_SMD_DIODE, 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 64)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(20 * MINUTE) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_GOOWARE_SMD_DIODE, 64 * 64)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_RESISTOR, 64 * 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 64 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(20 * MINUTE * 64) // Original: 90s, Wrapped: 90s * 16 = 1440s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_OPTICAL_SMD_DIODE, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(10 * MINUTE) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_OPTICAL_SMD_DIODE, 16 * 64)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16 * 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(10 * MINUTE * 64) // Original: 45s, Wrapped: 45s * 16 = 720s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(5 * MINUTE) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_SPINTRONIC_SMD_DIODE, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4 * 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4 * 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration(5 * MINUTE * 64) // Original: 22.5s, Wrapped: 22.5s * 16 = 360s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(frameGt, Darmstadtium, 64)
            .input(GOOWARE_COMPUTER_UHV, 48)
            .input(WRAP_COSMIC_SMD_DIODE)
            .input(WRAP_COSMIC_SMD_CAPACITOR)
            .input(WRAP_COSMIC_SMD_TRANSISTOR)
            .input(WRAP_COSMIC_SMD_RESISTOR)
            .input(WRAP_COSMIC_SMD_INDUCTOR)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(KaptonE.getFluid(L * 16))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16)
            .EUt(VA[UHV])
            .duration(2 * MINUTE + 30 * SECOND) // Original: 11.25s, Wrapped: 1.25s * 16 = 180s
            .buildAndRegister()

        ANTI_GRAVITY_ASSEMBLY_CHAMBER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(frameGt, Darmstadtium, 64 * 64)
            .input(GOOWARE_COMPUTER_UHV, 48 * 64)
            .input(WRAP_COSMIC_SMD_DIODE, 64)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 64)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 64)
            .input(WRAP_COSMIC_SMD_RESISTOR, 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR, 64)
            .input(WRAP_ARAM_CHIP, 32 * 64)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 128 * 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 40 * 64))
            .fluidInputs(KaptonE.getFluid(L * 16 * 64))
            .fluidInputs(KaptonK.getFluid(36 * 64 * 16 * 64)) // foil (36) * 64 * 16
            .fluidInputs(Americium.getFluid(L * 8 * 64 * 64)) // plate (144) * 8 * 64
            .output(GOOWARE_MAINFRAME_UEV, 16 * 64)
            .EUt(VA[UHV])
            .duration((2 * MINUTE + 30 * SECOND) * 64) // Original: 11.25s, Wrapped: 1.25s * 16 = 180s
            .buildAndRegister()

        // endregion

        // region T9: Optical

        // endregion

    }

    // @formatter:on

}