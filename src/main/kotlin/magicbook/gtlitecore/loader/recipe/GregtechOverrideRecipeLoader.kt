package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VN
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_RESISTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.BLACKLIGHT
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENERGY_CLUSTER
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB_CLUSTER
import gregtech.common.items.MetaItems.ENERGY_MODULE
import gregtech.common.items.MetaItems.FIELD_GENERATOR_IV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_LuV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_UV
import gregtech.common.items.MetaItems.FIELD_GENERATOR_ZPM
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.QUANTUM_STAR
import gregtech.common.items.MetaItems.ULTIMATE_BATTERY
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtech.common.metatileentities.MetaTileEntities.FUSION_REACTOR
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.LARGE_PLASMA_TURBINE
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Plutonium244
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polyetheretherketone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WoodsGlass
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.copy
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTIMATE_CIRCUIT_BOARD
import magicbook.gtlitecore.loader.recipe.component.CraftingComponents
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import java.util.*

@Suppress("MISSING_DEPENDENCY_CLASS")
class GregtechOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {
            // In original GTCEu (and GT5), if you want to get Wrought Iron, then you should
            // smelt Iron nugget to get Wrought Iron nugget, then use Steam Compressor to get
            // Wrought Iron ingot (for not harded recipes configuration situation, you can
            // get Wrought Iron ingot by crafting station, in GTCEu). But now, you can smelt
            // Iron ingot to get Wrought Iron ingot.
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Iron),
                OreDictUnifier.get(ingot, WroughtIron))

            // Down-tier Clay electrolysis to provide another choice of Aluminium, another
            // choice is Cryolite and Ruby Juice processing. With Dust Block, you can build
            // an infinity Clay chain at LV stage.

            // Sand -> Dust Block
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .inputs(ItemStack(Blocks.SAND))
                .outputs(ItemStack(GTLiteMetaBlocks.DUST_BLOCK))
                .EUt(VH[LV].toLong())
                .duration(10 * TICK)
                .buildAndRegister()

            // Dust Block + H2O -> Clay
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(ItemStack(GTLiteMetaBlocks.DUST_BLOCK))
                .fluidInputs(Water.getFluid(1000))
                .outputs(ItemStack(Blocks.CLAY))
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Down-tier Clay electrolysis from MV stage to LV stage.
            ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Clay, 13)
                .output(dust, Sodium, 2)
                .output(dust, Lithium)
                .output(dust, Aluminium, 2)
                .output(dust, Silicon, 2)
                .fluidOutputs(Water.getFluid(6000))
                .EUt(VA[LV].toLong())
                .duration(9 * SECOND + 2 * TICK)
                .buildAndRegister()

            // Reduce time spent of Water electrolysis from 75s to 15s, and then player can
            // get Hydrogen and Oxygen easier. Another correspondenced recipes please
            // see: AluminiumSodiumProcessing, this is a conflict resolved of these recipes.
            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
                Water.getFluid(1000))

            // H2O -> 2H + O
            ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Down-tier Cleanroom Plascrete because Mining Drone Airport is at LV stage,
            // player cannot get MV assembler in LV stage.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Steel),
                    OreDictUnifier.get(plate, Polyethylene, 6)),
                arrayOf(Concrete.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(frameGt, Steel)
                .input(plate, Polyethylene, 6)
                .fluidInputs(Concrete.getFluid(L))
                .outputs(MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.PLASCRETE, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Override all conveyor modules recipes with new ore dictionary to compatible
            // with several synthetic rubbers.
            ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_iv_silicone_rubber")
            ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_iv_styrene_butadiene_rubber")

            // LV-EV Conveyor Modules
            for (i in LV .. EV)
            {
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_silicone_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_styrene_butadiene_rubber")

                ModHandler.addShapedRecipe(true, "conveyor_module.${VN[i].lowercase(Locale.getDefault())}", CraftingComponent.CONVEYOR.getIngredient(i) as ItemStack,
                    "PPP", "MCM", "PPP",
                    'M', CraftingComponent.MOTOR.getIngredient(i),
                    'P', "plateAnyRubber",
                    'C', UnificationEntry(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material))

                for (rubber in arrayOf(Rubber, SiliconeRubber, StyreneButadieneRubber))
                {
                    GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                        arrayOf(copy(CraftingComponent.MOTOR.getIngredient(i) as ItemStack, 2),
                            OreDictUnifier.get(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material),
                            IntCircuitIngredient.getIntegratedCircuit(1)),
                        arrayOf(rubber.getFluid(L * 6)))
                }

                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material)
                    .inputs(copy(CraftingComponent.MOTOR.getIngredient(i) as ItemStack, 2))
                    .input("plateAnyRubber", 6)
                    .outputs(CraftingComponent.CONVEYOR.getIngredient(i) as ItemStack)
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

            // IV Conveyor Module
            ModHandler.addShapedRecipe(true, "conveyor_module.iv", CONVEYOR_MODULE_IV.stackForm,
                "PPP", "MCM", "PPP",
                'M', ELECTRIC_MOTOR_IV,
                'P', "plateAnySyntheticRubber",
                'C', UnificationEntry(cableGtSingle, Tungsten))

            for (rubber in arrayOf(SiliconeRubber, StyreneButadieneRubber))
            {
                GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                    arrayOf(ELECTRIC_MOTOR_IV.getStackForm(2),
                        OreDictUnifier.get(cableGtSingle, Tungsten),
                        IntCircuitIngredient.getIntegratedCircuit(1)),
                    arrayOf(rubber.getFluid(L * 6)))
            }

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(cableGtSingle, Tungsten)
                .input(ELECTRIC_MOTOR_IV, 2)
                .input("plateAnySyntheticRubber", 6)
                .output(CONVEYOR_MODULE_IV)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Override all electric pumps recipes with new ore dictionary to compatible
            // with several synthetic rubbers.
            ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_iv_silicone_rubber")
            ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_iv_styrene_butadiene_rubber")

            for (i in LV .. EV)
            {
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_silicone_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_styrene_butadiene_rubber")

                ModHandler.addShapedRecipe(true, "electric_pump.${VN[i].lowercase()}", CraftingComponent.PUMP.getIngredient(i) as ItemStack,
                    "STR", "hPw", "RMC",
                    'S', UnificationEntry(screw, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material),
                    'T', UnificationEntry(rotor, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material),
                    'R', "ringAnyRubber",
                    'P', UnificationEntry(pipeNormalFluid, (CraftingComponent.PIPE_NORMAL.getIngredient(i) as UnificationEntry).material),
                    'M', CraftingComponent.MOTOR.getIngredient(i) as ItemStack,
                    'C', UnificationEntry(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material))

                for (rubber in arrayOf(Rubber, SiliconeRubber, StyreneButadieneRubber))
                {
                    GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                        *arrayOf(OreDictUnifier.get(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material),
                            OreDictUnifier.get(pipeNormalFluid, (CraftingComponent.PIPE_NORMAL.getIngredient(i) as UnificationEntry).material),
                            OreDictUnifier.get(screw, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material),
                            OreDictUnifier.get(rotor, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material),
                            OreDictUnifier.get(ring, rubber, 2),
                            CraftingComponent.MOTOR.getIngredient(i) as ItemStack))
                }

                ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, (CraftingComponents.CABLE_COMPONENT.getIngredient(i) as UnificationEntry).material)
                    .input(pipeNormalFluid, (CraftingComponent.PIPE_NORMAL.getIngredient(i) as UnificationEntry).material)
                    .input(screw, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material)
                    .input(rotor, (CraftingComponent.ROTOR.getIngredient(i) as UnificationEntry).material)
                    .input("ringAnyRubber", 2)
                    .inputs(CraftingComponent.MOTOR.getIngredient(i) as ItemStack)
                    .outputs(CraftingComponent.PUMP.getIngredient(i) as ItemStack)
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

            ModHandler.addShapedRecipe(true, "electric_pump.iv", ELECTRIC_PUMP_IV.stackForm,
                "STR", "hPw", "RMC",
                'S', UnificationEntry(screw, TungstenSteel),
                'T', UnificationEntry(rotor, TungstenSteel),
                'R', "ringAnySyntheticRubber",
                'P', UnificationEntry(pipeNormalFluid, TungstenSteel),
                'M', ELECTRIC_MOTOR_IV,
                'C', UnificationEntry(cableGtSingle, Tungsten))

            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, Tungsten)
                .input(pipeNormalFluid, TungstenSteel)
                .input(screw, TungstenSteel)
                .input(rotor, TungstenSteel)
                .input("ringAnySyntheticRubber", 2)
                .input(ELECTRIC_MOTOR_IV)
                .output(ELECTRIC_PUMP_IV)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            for (rubber in arrayOf(SiliconeRubber, StyreneButadieneRubber))
            {
                GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                    *arrayOf(OreDictUnifier.get(cableGtSingle, Tungsten),
                        OreDictUnifier.get(pipeNormalFluid, TungstenSteel),
                        OreDictUnifier.get(screw, TungstenSteel),
                        OreDictUnifier.get(rotor, TungstenSteel),
                        OreDictUnifier.get(ring, rubber, 2),
                        ELECTRIC_MOTOR_IV.stackForm))
            }

            // Let fusion MK1 used Curium and other fission products.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 4),
                    OreDictUnifier.get(plateDouble, Plutonium241),
                    OreDictUnifier.get(plateDouble, Osmiridium),
                    FIELD_GENERATOR_IV.getStackForm(2),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                    OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate, 32)),
                arrayOf(SolderingAlloy.getFluid(L * 8), NiobiumTitanium.getFluid(L * 8)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL))
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(plateDouble, Curium)
                .input(plateDouble, Osmiridium)
                .input(FIELD_GENERATOR_IV, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(wireGtSingle, IndiumTinBariumTitaniumCuprate, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(NiobiumTitanium.getFluid(L * 8))
                .output(FUSION_REACTOR[0])
                .EUt(VA[LuV].toLong())
                .duration(30 * SECOND)
                .scannerResearch { r -> r
                    .researchStack(OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate))
                    .EUt(VA[IV].toLong())
                    .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // Let fusion MK2 used Nano PIC, MK3 used Pico PIC.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV, 4),
                    OreDictUnifier.get(plateDouble, Naquadria),
                    OreDictUnifier.get(plateDouble, Europium),
                    FIELD_GENERATOR_LuV.getStackForm(2),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32),
                    OreDictUnifier.get(wireGtSingle, UraniumRhodiumDinaquadide, 32)),
                arrayOf(SolderingAlloy.getFluid(L * 8), VanadiumGallium.getFluid(L * 8)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(plateDouble, Einsteinium)
                .input(plateDouble, Naquadria)
                .input(FIELD_GENERATOR_LuV, 2)
                .input(NANO_PIC_CHIP, 64)
                .input(wireGtSingle, UraniumRhodiumDinaquadide, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(VanadiumGallium.getFluid(L * 8))
                .output(FUSION_REACTOR[1])
                .EUt(61440) // ZPM
                .duration(1 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(FUSION_REACTOR[0].stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UHV, 4),
                    QUANTUM_STAR.stackForm,
                    OreDictUnifier.get(plateDouble, Americium),
                    FIELD_GENERATOR_ZPM.getStackForm(2),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                    OreDictUnifier.get(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 32)),
                arrayOf(SolderingAlloy.getFluid(L * 8), YttriumBariumCuprate.getFluid(L * 8)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UHV, 4)
                .input(plateDouble, Mendelevium)
                .input(plateDouble, Rutherfordium)
                .input(FIELD_GENERATOR_ZPM, 2)
                .input(PICO_PIC_CHIP, 64)
                .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(YttriumBariumCuprate.getFluid(L * 8))
                .output(FUSION_REACTOR[2])
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(FUSION_REACTOR[1].stackForm)
                        .EUt(VA[UV].toLong())
                        .CWUt(32)
                }
                .buildAndRegister()

            // Buff the neutronium fusion recipes.
            GTRecipeHandler.removeRecipesByInputs(FUSION_RECIPES,
                Americium.getFluid(128),
                Naquadria.getFluid(128))

            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Americium.getFluid(L))
                .fluidInputs(Naquadria.getFluid(L))
                .fluidOutputs(Neutronium.getFluid(L / 2))
                .EUt(98304) // ZPM
                .duration(10 * SECOND)
                .EUToStart(600_000_000L) // 600M EU, MK3
                .buildAndRegister()

            // Blacklight overriden.
            ModHandler.removeRecipeByName("${GTValues.MODID}:blacklight")
            ModHandler.addShapedRecipe(true, "blacklight", BLACKLIGHT.stackForm,
                "SPS", "QEQ", "XPW",
                'P', UnificationEntry(plate, TungstenCarbide),
                'Q', UnificationEntry(plate, WoodsGlass),
                'E', UnificationEntry(spring, Europium),
                'S', UnificationEntry(screw, TungstenCarbide),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', UnificationEntry(cableGtSingle, Platinum))

            // Let radon be Pu244 + U238
            GTRecipeHandler.removeRecipesByInputs(LARGE_CHEMICAL_RECIPES,
                arrayOf(OreDictUnifier.get(ingot, Plutonium239, 8),
                    OreDictUnifier.get(dust, Uranium238)),
                arrayOf(Air.getFluid(10000)))

            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(ingot, Plutonium244, 8)
                .input(dust, Uranium238)
                .fluidInputs(Air.getFluid(10000))
                .output(dust, Plutonium244, 8)
                .fluidOutputs(Radon.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(3 * MINUTE + 20 * SECOND)
                .buildAndRegister()

            // Down-tier Large Plasma Turbine recipes hull requirement from LuV to IV.
            ModHandler.removeRecipeByName("${GTValues.MODID}:large_plasma_turbine")
            ModHandler.addShapedRecipe(true, "large_plasma_turbine", LARGE_PLASMA_TURBINE.stackForm,
                "XGX", "GHG", "PGP",
                'H', HULL[IV].stackForm,
                'G', UnificationEntry(gear, TungstenSteel),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LuV),
                'P', UnificationEntry(pipeLargeFluid, TungstenSteel))

            // Adjust energy module recipes.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(plateDouble, Europium, 8),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 4),
                    ENERGY_LAPOTRONIC_ORB_CLUSTER.stackForm,
                    FIELD_GENERATOR_LuV.stackForm,
                    HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32),
                    ADVANCED_SMD_DIODE.getStackForm(12),
                    ADVANCED_SMD_CAPACITOR.getStackForm(12),
                    ADVANCED_SMD_RESISTOR.getStackForm(12),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(12),
                    ADVANCED_SMD_INDUCTOR.getStackForm(12),
                    OreDictUnifier.get(wireFine, Ruridit, 64),
                    OreDictUnifier.get(bolt, Trinium, 16)),
                arrayOf(SolderingAlloy.getFluid(L * 10)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(plate, Rutherfordium, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(ENERGY_LAPOTRONIC_ORB_CLUSTER)
                .input(FIELD_GENERATOR_LuV)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(ADVANCED_SMD_DIODE, 16)
                .input(ADVANCED_SMD_CAPACITOR, 16)
                .input(ADVANCED_SMD_RESISTOR, 16)
                .input(ADVANCED_SMD_TRANSISTOR, 16)
                .input(ADVANCED_SMD_INDUCTOR, 16)
                .input(wireFine, Ruridit, 64)
                .input(bolt, Trinium, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .output(ENERGY_MODULE)
                .EUt(100_000) // ZPM
                .duration(1 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(ENERGY_LAPOTRONIC_ORB_CLUSTER.stackForm)
                        .EUt(VA[LuV].toLong())
                        .CWUt(16)
                }
                .buildAndRegister()

            // Adjust energy cluster recipes, allowed it used all UV-tier circuits.
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(plate, Americium, 16),
                    WETWARE_SUPER_COMPUTER_UV.getStackForm(4),
                    ENERGY_MODULE.stackForm,
                    FIELD_GENERATOR_ZPM.stackForm,
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32),
                    ADVANCED_SMD_DIODE.getStackForm(16),
                    ADVANCED_SMD_CAPACITOR.getStackForm(16),
                    ADVANCED_SMD_RESISTOR.getStackForm(16),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(16),
                    ADVANCED_SMD_INDUCTOR.getStackForm(16),
                    OreDictUnifier.get(wireFine, Osmiridium, 64),
                    OreDictUnifier.get(bolt, Naquadria, 16)),
                arrayOf(SolderingAlloy.getFluid(L * 20), Polybenzimidazole.getFluid(L * 4)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD)
                .input(plate, Dubnium, 8)
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(ENERGY_MODULE)
                .input(FIELD_GENERATOR_ZPM)
                .input(NANO_PIC_CHIP, 32)
                .input(GOOWARE_SMD_DIODE, 16)
                .input(GOOWARE_SMD_CAPACITOR, 16)
                .input(GOOWARE_SMD_RESISTOR, 16)
                .input(GOOWARE_SMD_TRANSISTOR, 16)
                .input(GOOWARE_SMD_INDUCTOR, 16)
                .input(wireFine, Osmiridium, 64)
                .input(bolt, Naquadria, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(Polybenzimidazole.getFluid(L * 4))
                .output(ENERGY_CLUSTER)
                .EUt(200_000) // UV
                .duration(1 * MINUTE + 30 * SECOND)
                .stationResearch { r ->
                    r.researchStack(ENERGY_MODULE.stackForm)
                        .EUt(VA[ZPM].toLong())
                        .CWUt(96)
                }
                .buildAndRegister()

            // Ultimate Battery
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
                arrayOf(OreDictUnifier.get(plateDouble, Darmstadtium, 16),
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.UHV, 4),
                    ENERGY_CLUSTER.getStackForm(16),
                    FIELD_GENERATOR_UV.getStackForm(4),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(64),
                    ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(64),
                    ADVANCED_SMD_DIODE.getStackForm(64),
                    ADVANCED_SMD_CAPACITOR.getStackForm(64),
                    ADVANCED_SMD_RESISTOR.getStackForm(64),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(64),
                    ADVANCED_SMD_INDUCTOR.getStackForm(64),
                    OreDictUnifier.get(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 64),
                    OreDictUnifier.get(bolt, Neutronium, 64)),
                arrayOf(SolderingAlloy.getFluid(L * 40), Polybenzimidazole.getFluid(L * 16),
                    Naquadria.getFluid(L * 18)))

            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ULTIMATE_CIRCUIT_BOARD)
                .input(plateDouble, Bohrium, 16)
                .input(circuit, MarkerMaterials.Tier.UHV, 4)
                .input(ENERGY_CLUSTER, 16)
                .input(FIELD_GENERATOR_UV, 4)
                .input(PICO_PIC_WAFER, 64)
                .input(OPTICAL_SMD_DIODE, 32)
                .input(OPTICAL_SMD_CAPACITOR, 32)
                .input(OPTICAL_SMD_RESISTOR, 32)
                .input(OPTICAL_SMD_TRANSISTOR, 32)
                .input(OPTICAL_SMD_INDUCTOR, 32)
                .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 64)
                .input(bolt, Neutronium, 64)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(Polyetheretherketone.getFluid(L * 16))
                .fluidInputs(Vibranium.getFluid(L * 18))
                .output(ULTIMATE_BATTERY)
                .EUt(800_000) // UHV
                .duration(2 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(ENERGY_CLUSTER.stackForm)
                        .EUt(VA[UHV].toLong())
                        .CWUt(144)
                }
                .buildAndRegister()

        }

    }

}