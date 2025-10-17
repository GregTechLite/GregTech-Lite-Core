package gregtechlite.gtlitecore.loader.recipe.component

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.RESEARCH_STATION_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_LuV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_OpV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_OpV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UIV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_OpV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_UXV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Omnium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_MOTOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PISTON_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX

internal object RobotArmRecipes
{

    // @formatter:off

    fun init()
    {
        // LuV
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(stickLong, HSSS, 4),
                OreDictUnifier.get(gear, HSSS),
                OreDictUnifier.get(gearSmall, HSSS, 3),
                ELECTRIC_MOTOR_LuV.getStackForm(2),
                ELECTRIC_PISTON_LUV.stackForm,
                OreDictUnifier.get(circuit, Tier.LuV),
                OreDictUnifier.get(circuit, Tier.IV, 2),
                OreDictUnifier.get(circuit, Tier.EV, 4),
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 4), Lubricant.getFluid(250)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, HSSS, 2)
            .input(gear, HSSS)
            .input(gearSmall, HSSS, 2)
            .input(ELECTRIC_MOTOR_LuV, 2)
            .input(ELECTRIC_PISTON_LUV)
            .input(circuit, Tier.LuV)
            .input(circuit, Tier.IV, 2)
            .input(circuit, Tier.EV, 4)
            .input(cableGtSingle, NiobiumTitanium, 3)
            .fluidInputs(SolderingAlloy.getFluid(L * 4))
            .fluidInputs(Lubricant.getFluid(250))
            .output(ROBOT_ARM_LuV)
            .EUt(6000) // IV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ROBOT_ARM_IV)
                    .EUt(VA[HV])
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
                OreDictUnifier.get(circuit, Tier.ZPM),
                OreDictUnifier.get(circuit, Tier.LuV, 2),
                OreDictUnifier.get(circuit, Tier.IV, 4),
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 8), Lubricant.getFluid(500)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, Osmiridium, 2)
            .input(gear, Osmiridium)
            .input(gearSmall, Osmiridium, 2)
            .input(ELECTRIC_MOTOR_ZPM, 2)
            .input(ELECTRIC_PISTON_ZPM)
            .input(circuit, Tier.ZPM)
            .input(circuit, Tier.LuV, 2)
            .input(circuit, Tier.IV, 4)
            .input(cableGtSingle, VanadiumGallium, 3)
            .fluidInputs(SolderingAlloy.getFluid(L * 8))
            .fluidInputs(Lubricant.getFluid(500))
            .output(ROBOT_ARM_ZPM)
            .EUt(24000) // LuV
            .duration(20 * SECOND)
            .scannerResearch { r ->
                r.researchStack(ROBOT_ARM_LuV)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // UV
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            ROBOT_ARM_ZPM.stackForm, TOOL_DATA_ORB.stackForm)

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(OreDictUnifier.get(stickLong, Tritanium, 4),
                OreDictUnifier.get(gear, Tritanium),
                OreDictUnifier.get(gearSmall, Tritanium, 3),
                ELECTRIC_MOTOR_UV.getStackForm(2),
                ELECTRIC_PISTON_UV.stackForm,
                OreDictUnifier.get(circuit, Tier.UV),
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                OreDictUnifier.get(circuit, Tier.LuV, 4),
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)),
            arrayOf(SolderingAlloy.getFluid(L * 12), Lubricant.getFluid(1000),
                Naquadria.getFluid(L * 4)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, Tritanium, 4)
            .input(gear, Tritanium)
            .input(gearSmall, Tritanium, 3)
            .input(ELECTRIC_MOTOR_UV, 2)
            .input(ELECTRIC_PISTON_UV)
            .input(circuit, Tier.UV)
            .input(circuit, Tier.ZPM, 2)
            .input(circuit, Tier.LuV, 4)
            .input(cableGtSingle, YttriumBariumCuprate, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 12))
            .fluidInputs(Lubricant.getFluid(1000))
            .fluidInputs(Naquadria.getFluid(L))
            .output(ROBOT_ARM_UV)
            .EUt(100_000) // ZPM
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_ZPM)
                    .EUt(VA[ZPM])
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
            .input(circuit, Tier.UHV)
            .input(circuit, Tier.UV, 2)
            .input(circuit, Tier.ZPM, 4)
            .input(cableGtSingle, Europium, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 16))
            .fluidInputs(Lubricant.getFluid(2000))
            .fluidInputs(Taranium.getFluid(L * 2))
            .output(ROBOT_ARM_UHV)
            .EUt(400_000) // UV
            .duration(20 * SECOND)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_UV)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, CosmicNeutronium, 4)
            .input(gear, CosmicNeutronium, 2)
            .input(gearSmall, CosmicNeutronium, 6)
            .input(ELECTRIC_MOTOR_UEV, 2)
            .input(ELECTRIC_PISTON_UEV)
            .input(circuit, Tier.UEV)
            .input(circuit, Tier.UHV, 2)
            .input(circuit, Tier.UV, 4)
            .input(cableGtSingle, Seaborgium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 32))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(4000))
            .fluidInputs(MetastableOganesson.getFluid(L * 4))
            .fluidInputs(Fullerene.getFluid(L))
            .output(ROBOT_ARM_UEV)
            .EUt(1_800_000) // UHV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_UHV)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, HeavyQuarkDegenerateMatter, 4)
            .input(gear, HeavyQuarkDegenerateMatter, 2)
            .input(gearSmall, HeavyQuarkDegenerateMatter, 6)
            .input(ELECTRIC_MOTOR_UIV, 2)
            .input(ELECTRIC_PISTON_UIV)
            .input(circuit, Tier.UIV)
            .input(circuit, Tier.UEV, 2)
            .input(circuit, Tier.UHV, 4)
            .input(cableGtSingle, SuperheavyAlloyA, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 64))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 8))
            .fluidInputs(CarbonNanotube.getFluid(L * 2))
            .output(ROBOT_ARM_UIV)
            .EUt(6_000_000) // UIV
            .duration(40 * SECOND)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_UEV)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, TranscendentMetal, 8)
            .input(gear, TranscendentMetal, 2)
            .input(gearSmall, TranscendentMetal, 6)
            .input(ELECTRIC_MOTOR_UXV, 2)
            .input(ELECTRIC_PISTON_UXV)
            .input(circuit, Tier.UXV)
            .input(circuit, Tier.UIV, 2)
            .input(circuit, Tier.UEV, 4)
            .input(cableGtSingle, SuperheavyAlloyB, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 128))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .fluidInputs(PrimordialMatter.getFluid(L * 16))
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(ROBOT_ARM_UXV)
            .EUt(20_000_000) // UIV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_UIV)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(stickLong, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            .input(gear, MagnetohydrodynamicallyConstrainedStarMatter, 2)
            .input(gearSmall, MagnetohydrodynamicallyConstrainedStarMatter, 6)
            .input(ELECTRIC_MOTOR_OpV, 2)
            .input(ELECTRIC_PISTON_OpV)
            .input(circuit, Tier.OpV)
            .input(circuit, Tier.UXV, 2)
            .input(circuit, Tier.UIV, 4)
            .input(cableGtSingle, Periodicium, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 256))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(32000))
            .fluidInputs(WhiteDwarfMatter.getFluid(L * 32))
            .fluidInputs(CosmicFabric.getFluid(L * 8))
            .output(ROBOT_ARM_OpV)
            .EUt(50_000_000) // UXV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_UXV)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // MAX
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Omnium, 16)
            .input(frameGt, Universium, 16)
            .input(frameGt, BlackDwarfMatter, 16)
            .input(frameGt, WhiteDwarfMatter, 16)
            .input(gear, Omnium, 4)
            .input(gear, SpaceTime, 4)
            .input(gearSmall, Omnium, 12)
            .input(gearSmall, TranscendentMetal, 12)
            .input(ELECTRIC_MOTOR_MAX, 2)
            .input(ELECTRIC_PISTON_MAX)
            .input(circuit, Tier.MAX)
            .input(circuit, Tier.OpV, 2)
            .input(circuit, Tier.UXV, 4)
            .input(circuit, Tier.UIV, 8)
            .input(cableGtHex, RealizedQuantumFoamShard, 4)
            .input(nanite, MagMatter, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 512))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(64000))
            .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 64))
            .fluidInputs(CosmicFabric.getFluid(L * 16))
            .output(ROBOT_ARM_MAX)
            .EUt(300_000_000) // OpV
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(ROBOT_ARM_OpV)
                    .EUt(VA[OpV])
                    .CWUt(256)
            }
            .buildAndRegister()
    }

    // @formatter:on

}