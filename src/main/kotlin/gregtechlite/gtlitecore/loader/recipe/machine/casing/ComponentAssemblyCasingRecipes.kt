package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeLargeItem
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_EV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_HV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_MV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_EV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_HV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_IV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_MV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ROBOT_ARM_EV
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_MV
import gregtech.common.items.MetaItems.ROBOT_ARM_OpV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_UXV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SMART_FILTER
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AxinoFusedRedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CinobiteA243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lafium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ReneN5
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SelfInteractingDarkMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Stellite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumTungstenCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zeron100
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.variant.ComponentAssemblyCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX

internal object ComponentAssemblyCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Advanced Filter Casing
        ModHandler.addShapedRecipe(true, "advanced_filter_casing", MultiblockCasing.ADVANCED_FILTER_CASING.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PDP", "SCG", "MFR",
            'C', GTCleanroomCasing.FILTER_CASING.stack,
            'S', SMART_FILTER,
            'M', ELECTRIC_MOTOR_UV,
            'D', CONVEYOR_MODULE_UV,
            'F', UnificationEntry(frameGt, Iridium),
            'R', UnificationEntry(rotor, Duranium),
            'G', UnificationEntry(gearSmall, HastelloyN),
            'P', UnificationEntry(pipeLargeItem, Osmiridium))

        // LV CoAL Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, Steel)
            .input(plateDense, Steel, 4)
            .input(ROBOT_ARM_LV, 4)
            .input(ELECTRIC_PISTON_LV, 8)
            .input(ELECTRIC_MOTOR_LV, 10)
            .input(gear, Steel, 4)
            .input(wireGtQuadruple, Tin, 6)
            .input(circuit, Tier.LV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .outputs(ComponentAssemblyCasing.LV.getStack(4))
            .EUt(VA[LV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // MV CoAL Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, Aluminium)
            .input(plateDense, Aluminium, 4)
            .input(ROBOT_ARM_MV, 4)
            .input(ELECTRIC_PISTON_MV, 8)
            .input(ELECTRIC_MOTOR_MV, 10)
            .input(gear, Aluminium, 4)
            .input(wireGtQuadruple, Copper, 6)
            .input(circuit, Tier.MV, 8)
            .input(circuit, Tier.LV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .outputs(ComponentAssemblyCasing.MV.getStack(4))
            .EUt(VA[MV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // HV CoAL Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, StainlessSteel)
            .input(plateDense, StainlessSteel, 4)
            .input(ROBOT_ARM_HV, 4)
            .input(ELECTRIC_PISTON_HV, 8)
            .input(ELECTRIC_MOTOR_HV, 10)
            .input(gear, StainlessSteel, 4)
            .input(wireGtQuadruple, Gold, 6)
            .input(circuit, Tier.HV, 8)
            .input(circuit, Tier.MV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .outputs(ComponentAssemblyCasing.HV.getStack(4))
            .EUt(VA[HV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // EV CoAL Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, Titanium)
            .input(plateDense, Titanium, 4)
            .input(ROBOT_ARM_EV, 4)
            .input(ELECTRIC_PISTON_EV, 8)
            .input(ELECTRIC_MOTOR_EV, 10)
            .input(gear, Titanium, 4)
            .input(wireGtQuadruple, Aluminium, 6)
            .input(circuit, Tier.EV, 8)
            .input(circuit, Tier.HV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .outputs(ComponentAssemblyCasing.EV.getStack(4))
            .EUt(VA[EV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // IV CoAL Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(frameGt, TungstenSteel)
            .input(plateDense, TungstenSteel, 4)
            .input(ROBOT_ARM_IV, 4)
            .input(ELECTRIC_PISTON_IV, 8)
            .input(ELECTRIC_MOTOR_IV, 10)
            .input(gear, TungstenSteel, 4)
            .input(wireGtQuadruple, Tungsten, 6)
            .input(circuit, Tier.IV, 8)
            .input(circuit, Tier.EV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .outputs(ComponentAssemblyCasing.IV.getStack(4))
            .EUt(VA[IV])
            .duration(16 * SECOND)
            .buildAndRegister()

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
            .input(circuit, Tier.LuV, 8)
            .input(circuit, Tier.IV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 24))
            .fluidInputs(Lubricant.getFluid(4000))
            .fluidInputs(Stellite.getFluid(L * 12))
            .fluidInputs(TitaniumTungstenCarbide.getFluid(L * 6))
            .outputs(ComponentAssemblyCasing.LuV.getStack(4))
            .EUt(VA[LuV])
            .duration(32 * SECOND)
            .scannerResearch {
                it.researchStack(ComponentAssemblyCasing.IV.stack)
                    .EUt(VA[HV])
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
            .input(circuit, Tier.ZPM, 8)
            .input(circuit, Tier.LuV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 24))
            .fluidInputs(Lubricant.getFluid(4000))
            .fluidInputs(HastelloyN.getFluid(L * 12))
            .fluidInputs(Zeron100.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.ZPM.getStack(4))
            .EUt(VA[ZPM])
            .duration(32 * SECOND)
            .scannerResearch {
                it.researchStack(ComponentAssemblyCasing.LuV.stack)
                    .EUt(VA[IV])
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
            .input(circuit, Tier.UV, 8)
            .input(circuit, Tier.ZPM, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 24))
            .fluidInputs(Lubricant.getFluid(4000))
            .fluidInputs(ReneN5.getFluid(L * 12))
            .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.UV.getStack(4))
            .EUt(VA[UV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.ZPM.stack)
                    .EUt(VA[ZPM])
                    .CWUt(6)
            }
            .buildAndRegister()

        // UHV CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(plateDense, Neutronium, 6)
            .input(ROBOT_ARM_UHV, 8)
            .input(ELECTRIC_PISTON_UHV, 10)
            .input(ELECTRIC_MOTOR_UHV, 16)
            .input(gear, Neutronium, 4)
            .input(gearSmall, Neutronium, 16)
            .input(cableGtQuadruple, Europium, 8)
            .input(circuit, Tier.UHV, 8)
            .input(circuit, Tier.UV, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 24))
            .fluidInputs(Lubricant.getFluid(4000))
            .fluidInputs(Tairitsium.getFluid(L * 12))
            .fluidInputs(Pikyonium64B.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.UHV.getStack(4))
            .EUt(VA[UHV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.UV.stack)
                    .EUt(VA[UV])
                    .CWUt(12)
            }
            .buildAndRegister()

        // UEV CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Vibranium)
            .input(plateDense, Vibranium, 6)
            .input(ROBOT_ARM_UEV, 8)
            .input(ELECTRIC_PISTON_UEV, 10)
            .input(ELECTRIC_MOTOR_UEV, 16)
            .input(gear, Vibranium, 4)
            .input(gearSmall, Vibranium, 16)
            .input(cableGtQuadruple, Seaborgium, 8)
            .input(circuit, Tier.UEV, 8)
            .input(circuit, Tier.UHV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 24))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(TitanSteel.getFluid(L * 12))
            .fluidInputs(HastelloyX78.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.UEV.getStack(4))
            .EUt(VA[UEV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.UHV.stack)
                    .EUt(VA[UHV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UIV CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Shirabon)
            .input(plateDense, Shirabon, 6)
            .input(ROBOT_ARM_UIV, 8)
            .input(ELECTRIC_PISTON_UIV, 10)
            .input(ELECTRIC_MOTOR_UIV, 16)
            .input(gear, Shirabon, 4)
            .input(gearSmall, Shirabon, 16)
            .input(cableGtQuadruple, SuperheavyAlloyA, 8)
            .input(circuit, Tier.UIV, 8)
            .input(circuit, Tier.UEV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 24))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(HastelloyK243.getFluid(L * 12))
            .fluidInputs(ArceusAlloy2B.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.UIV.getStack(4))
            .EUt(VA[UIV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.UEV.stack)
                    .EUt(VA[UEV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UXV CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Creon)
            .input(plateDense, Creon, 6)
            .input(ROBOT_ARM_UXV, 8)
            .input(ELECTRIC_PISTON_UXV, 10)
            .input(ELECTRIC_MOTOR_UXV, 16)
            .input(gear, Creon, 4)
            .input(gearSmall, Creon, 16)
            .input(cableGtQuadruple, SuperheavyAlloyB, 8)
            .input(circuit, Tier.UXV, 8)
            .input(circuit, Tier.UIV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 24))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(CinobiteA243.getFluid(L * 12))
            .fluidInputs(QuantumAlloy.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.UXV.getStack(4))
            .EUt(VA[UXV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.UIV.stack)
                    .EUt(VA[UIV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // OpV CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, BlackDwarfMatter)
            .input(plateDense, BlackDwarfMatter, 6)
            .input(ROBOT_ARM_OpV, 8)
            .input(ELECTRIC_PISTON_OpV, 10)
            .input(ELECTRIC_MOTOR_OpV, 16)
            .input(gear, BlackDwarfMatter, 4)
            .input(gearSmall, BlackDwarfMatter, 16)
            .input(cableGtQuadruple, Periodicium, 8)
            .input(circuit, Tier.OpV, 8)
            .input(circuit, Tier.UXV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 24))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(Abyssalloy.getFluid(L * 12))
            .fluidInputs(Lafium.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.OpV.getStack(4))
            .EUt(VA[OpV])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.UXV.stack)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // MAX CoAL Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, AxinoFusedRedMatter)
            .input(plateDense, AxinoFusedRedMatter, 6)
            .input(ROBOT_ARM_MAX, 8)
            .input(ELECTRIC_PISTON_MAX, 10)
            .input(ELECTRIC_MOTOR_MAX, 16)
            .input(gear, AxinoFusedRedMatter, 4)
            .input(gearSmall, AxinoFusedRedMatter, 16)
            .input(cableGtQuadruple, RealizedQuantumFoamShard, 8)
            .input(circuit, Tier.MAX, 8)
            .input(circuit, Tier.OpV, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 24))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(Legendarium.getFluid(L * 12))
            .fluidInputs(SelfInteractingDarkMatter.getFluid(L * 8))
            .outputs(ComponentAssemblyCasing.MAX.getStack(4))
            .EUt(VA[MAX])
            .duration(32 * SECOND)
            .stationResearch {
                it.researchStack(ComponentAssemblyCasing.OpV.stack)
                    .EUt(VA[OpV])
                    .CWUt(128)
            }
            .buildAndRegister()

    }

    // @formatter:on

}