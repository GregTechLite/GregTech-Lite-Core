package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Copernicium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Meitnerium
import gregtech.api.unification.material.Materials.Moscovium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeHugeItem
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeItem
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalItem
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallItem
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_LuV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UEV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UV
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_LUV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_ZPM
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_LuV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UEV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_ZPM
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UHV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumStrontiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumCeriumCobaltIndium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MolybdenumDisilicide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutroniumSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubidiumTitanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Talonite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumTungstenCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCryostat
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionDivertor
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionVacuum
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV

internal object FusionCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Advanced recipes of Superconductor Wire Coil
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(wireGtDouble, VibraniumTritaniumActiniumIronSuperhydride, 2)
            .input(foil, NiobiumTitanium, 2)
            .fluidInputs(Trinium.getFluid(L * 2))
            .outputs(GTFusionCasing.FUSION_COIL.stack)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(wireGtDouble, FullereneSuperconductor)
            .input(foil, NiobiumTitanium)
            .fluidInputs(Trinium.getFluid(L))
            .outputs(GTFusionCasing.FUSION_COIL.stack)
            .EUt(VA[UEV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(wireGtDouble, BoronFranciumCarbideSuperconductor)
            .input(foil, NiobiumTitanium)
            .fluidInputs(Trinium.getFluid(L / 2))
            .outputs(GTFusionCasing.FUSION_COIL.getStack(2))
            .EUt(VA[UIV])
            .duration(1 * SECOND + 5 * TICK)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .input(wireGtDouble, NeutroniumSuperconductor)
            .input(foil, NiobiumTitanium)
            .fluidInputs(Trinium.getFluid(L / 4))
            .outputs(GTFusionCasing.FUSION_COIL.getStack(4))
            .EUt(VA[UXV])
            .duration(12 * TICK)
            .buildAndRegister()

        // Advanced Fusion Coil
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
            .input(FIELD_GENERATOR_LuV, 2)
            .input(ELECTRIC_PUMP_LuV)
            .input(NEUTRON_REFLECTOR, 4)
            .input(circuit, Tier.ZPM, 4)
            .input(pipeSmallFluid, Europium, 4)
            .input(plate, Americium, 4)
            .fluidInputs(YttriumBariumCuprate.getFluid(L * 4))
            .outputs(FusionCoil.ADVANCED.stack)
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Ultimate Fusion Coil
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(FusionCoil.ADVANCED.stack)
            .input(FIELD_GENERATOR_ZPM, 2)
            .input(ELECTRIC_PUMP_ZPM)
            .input(NEUTRON_REFLECTOR, 8)
            .input(circuit, Tier.UV, 4)
            .input(pipeSmallFluid, Duranium, 4)
            .input(plate, Dubnium, 4)
            .fluidInputs(Europium.getFluid(L * 4))
            .outputs(FusionCoil.ULTIMATE.stack)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Fusion Machine Casing MK4
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HULL[UHV])
            .inputs(FusionCoil.ADVANCED.stack)
            .input(VOLTAGE_COIL_UHV, 2)
            .input(FIELD_GENERATOR_UV)
            .input(plate, Dubnium, 6)
            .fluidInputs(Kevlar.getFluid(L * 8))
            .outputs(FusionCasing.MK4.getStack(2))
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Fusion Machine Casing MK5
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(HULL[UEV])
            .inputs(FusionCoil.ULTIMATE.stack)
            .input(VOLTAGE_COIL_UEV, 2)
            .input(FIELD_GENERATOR_UHV)
            .input(plate, Bohrium, 6)
            .fluidInputs(Kevlar.getFluid(L * 16))
            .outputs(FusionCasing.MK5.getStack(2))
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cryostat MK1
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .input(frameGt, TitaniumCarbide)
            .input(plate, TungstenSteel, 6)
            .input(springSmall, MolybdenumDisilicide, 2)
            .input(ELECTRIC_PUMP_LuV)
            .input(pipeSmallFluid, Inconel718, 2)
            .input(wireFine, Naquadah, 16)
            .input(screw, LithiumTitanate, 4)
            .fluidInputs(Duranium.getFluid(L * 4))
            .outputs(FusionCryostat.MK1.getStack(4))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cryostat MK2
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .input(frameGt, TungstenCarbide)
            .input(plate, RhodiumPlatedPalladium, 6)
            .input(springSmall, Talonite, 2)
            .input(ELECTRIC_PUMP_ZPM)
            .input(pipeNormalFluid, Inconel718, 2)
            .input(wireFine, NaquadahEnriched, 16)
            .input(screw, BariumTitanate, 4)
            .fluidInputs(Francium.getFluid(L * 4))
            .outputs(FusionCryostat.MK2.getStack(4))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cryostat MK3
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .input(frameGt, TitaniumTungstenCarbide)
            .input(plate, NaquadahAlloy, 6)
            .input(springSmall, HastelloyN, 2)
            .input(ELECTRIC_PUMP_UV)
            .input(pipeLargeFluid, Inconel718, 2)
            .input(wireFine, Naquadria, 16)
            .input(screw, RubidiumTitanate, 4)
            .fluidInputs(Meitnerium.getFluid(L * 4))
            .outputs(FusionCryostat.MK3.getStack(4))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cryostat MK4
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .input(frameGt, TitanSteel)
            .input(plate, Darmstadtium, 6)
            .input(springSmall, Trinaquadalloy, 2)
            .input(ELECTRIC_PUMP_UHV)
            .input(pipeHugeFluid, Inconel718, 2)
            .input(wireFine, Adamantium, 16)
            .input(screw, BariumStrontiumTitanate, 4)
            .fluidInputs(Copernicium.getFluid(L * 4))
            .outputs(FusionCryostat.MK4.getStack(4))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Cryostat MK5
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(13)
            .input(frameGt, TantalumHafniumSeaborgiumCarbide)
            .input(plate, Neutronium, 6)
            .input(springSmall, Tairitsium, 2)
            .input(ELECTRIC_PUMP_UEV)
            .input(pipeHugeFluid, Inconel718, 4)
            .input(wireFine, Vibranium, 16)
            .input(screw, CaesiumCeriumCobaltIndium, 4)
            .fluidInputs(Moscovium.getFluid(L * 4))
            .outputs(FusionCryostat.MK5.getStack(4))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Divertor MK1
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(frameGt, TitaniumCarbide)
            .input(plate, TungstenSteel, 2)
            .input(rotor, TungstenSteel)
            .input(CONVEYOR_MODULE_LuV)
            .input(pipeSmallItem, MaragingSteel250, 2)
            .input(wireFine, Naquadah, 16)
            .input(screw, LithiumTitanate, 4)
            .fluidInputs(Duranium.getFluid(L * 4))
            .outputs(FusionDivertor.MK1.getStack(4))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Divertor MK2
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(frameGt, TungstenCarbide)
            .input(plate, RhodiumPlatedPalladium, 4)
            .input(rotor, RhodiumPlatedPalladium)
            .input(CONVEYOR_MODULE_ZPM)
            .input(pipeNormalItem, MaragingSteel250, 2)
            .input(wireFine, NaquadahEnriched, 16)
            .input(screw, BariumTitanate, 4)
            .fluidInputs(Francium.getFluid(L * 4))
            .outputs(FusionDivertor.MK2.getStack(4))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Divertor MK3
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(frameGt, TitaniumTungstenCarbide)
            .input(plate, NaquadahAlloy, 4)
            .input(rotor, NaquadahAlloy)
            .input(CONVEYOR_MODULE_UV)
            .input(pipeLargeItem, MaragingSteel250, 2)
            .input(wireFine, Naquadria, 16)
            .input(screw, RubidiumTitanate, 4)
            .fluidInputs(Meitnerium.getFluid(L * 4))
            .outputs(FusionDivertor.MK3.getStack(4))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Divertor MK4
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(frameGt, TitanSteel)
            .input(plate, Darmstadtium, 4)
            .input(rotor, Darmstadtium)
            .input(CONVEYOR_MODULE_UHV)
            .input(pipeHugeItem, MaragingSteel250, 2)
            .input(wireFine, Adamantium, 16)
            .input(screw, BariumStrontiumTitanate, 4)
            .fluidInputs(Copernicium.getFluid(L * 4))
            .outputs(FusionDivertor.MK4.getStack(4))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Divertor MK5
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(14)
            .input(frameGt, TantalumHafniumSeaborgiumCarbide)
            .input(plate, Neutronium, 4)
            .input(rotor, Neutronium)
            .input(CONVEYOR_MODULE_UEV)
            .input(pipeHugeItem, MaragingSteel250, 4)
            .input(wireFine, Vibranium, 16)
            .input(screw, CaesiumCeriumCobaltIndium, 4)
            .fluidInputs(Moscovium.getFluid(L * 4))
            .outputs(FusionDivertor.MK5.getStack(4))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Vacuum MK1
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, TitaniumCarbide)
            .input(plateDouble, TungstenSteel, 2)
            .input(gearSmall, HSSG, 3)
            .input(ELECTRIC_PISTON_LUV)
            .input(NEUTRON_REFLECTOR)
            .input(wireFine, Naquadah, 16)
            .input(screw, LithiumTitanate, 4)
            .fluidInputs(Duranium.getFluid(L * 4))
            .outputs(FusionVacuum.MK1.getStack(4))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Vacuum MK2
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, TungstenCarbide)
            .input(plateDouble, RhodiumPlatedPalladium, 2)
            .input(gearSmall, HSSE, 3)
            .input(ELECTRIC_PISTON_ZPM)
            .input(NEUTRON_REFLECTOR, 2)
            .input(wireFine, NaquadahEnriched, 16)
            .input(screw, BariumTitanate, 4)
            .fluidInputs(Francium.getFluid(L * 4))
            .outputs(FusionVacuum.MK2.getStack(4))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Vacuum MK3
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, TitaniumTungstenCarbide)
            .input(plateDouble, NaquadahAlloy, 2)
            .input(gearSmall, HSSS, 3)
            .input(ELECTRIC_PISTON_UV)
            .input(NEUTRON_REFLECTOR, 4)
            .input(wireFine, Naquadria, 16)
            .input(screw, RubidiumTitanate, 4)
            .fluidInputs(Meitnerium.getFluid(L * 4))
            .outputs(FusionVacuum.MK3.getStack(4))
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Vacuum MK4
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, TitanSteel)
            .input(plateDouble, Darmstadtium, 2)
            .input(gearSmall, HDCS, 3)
            .input(ELECTRIC_PISTON_UHV)
            .input(NEUTRON_REFLECTOR, 8)
            .input(wireFine, Adamantium, 16)
            .input(screw, BariumStrontiumTitanate, 4)
            .fluidInputs(Copernicium.getFluid(L * 4))
            .outputs(FusionVacuum.MK4.getStack(4))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Vacuum MK5
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(15)
            .input(frameGt, TantalumHafniumSeaborgiumCarbide)
            .input(plateDouble, Neutronium, 2)
            .input(gearSmall, HastelloyK243, 3)
            .input(ELECTRIC_PISTON_UEV)
            .input(NEUTRON_REFLECTOR, 16)
            .input(wireFine, Vibranium, 16)
            .input(screw, CaesiumCeriumCobaltIndium, 4)
            .fluidInputs(Moscovium.getFluid(L * 4))
            .outputs(FusionVacuum.MK5.getStack(4))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}