package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.items.OreDictNames
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SterlingSilver
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.pipeNonupleFluid
import gregtech.api.unification.ore.OrePrefix.pipeQuadrupleFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.EMITTER_EV
import gregtech.common.items.MetaItems.EMITTER_HV
import gregtech.common.items.MetaItems.EMITTER_IV
import gregtech.common.items.MetaItems.EMITTER_LV
import gregtech.common.items.MetaItems.EMITTER_MV
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtech.common.items.MetaItems.SENSOR_EV
import gregtech.common.items.MetaItems.SENSOR_HV
import gregtech.common.items.MetaItems.SENSOR_IV
import gregtech.common.items.MetaItems.SENSOR_LV
import gregtech.common.items.MetaItems.SENSOR_MV
import gregtech.common.items.MetaItems.VACUUM_TUBE
import gregtech.common.metatileentities.MetaTileEntities.ALUMINIUM_CRATE
import gregtech.common.metatileentities.MetaTileEntities.ALUMINIUM_DRUM
import gregtech.common.metatileentities.MetaTileEntities.BRONZE_CRATE
import gregtech.common.metatileentities.MetaTileEntities.BRONZE_DRUM
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.FLUID_EXPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.FLUID_IMPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.ITEM_EXPORT_BUS
import gregtech.common.metatileentities.MetaTileEntities.ITEM_IMPORT_BUS
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_1024
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_256
import gregtech.common.metatileentities.MetaTileEntities.LASER_INPUT_HATCH_4096
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_1024
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_256
import gregtech.common.metatileentities.MetaTileEntities.LASER_OUTPUT_HATCH_4096
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_CHEST
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_TANK
import gregtech.common.metatileentities.MetaTileEntities.STAINLESS_STEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.STAINLESS_STEEL_DRUM
import gregtech.common.metatileentities.MetaTileEntities.STEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.STEEL_DRUM
import gregtech.common.metatileentities.MetaTileEntities.TITANIUM_CRATE
import gregtech.common.metatileentities.MetaTileEntities.TITANIUM_DRUM
import gregtech.common.metatileentities.MetaTileEntities.TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.TUNGSTENSTEEL_CRATE
import gregtech.common.metatileentities.MetaTileEntities.TUNGSTENSTEEL_DRUM
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler.addIOHatchRecipes
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler.addMultiFluidHatchRecipes
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BerylliumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromaticGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChromiumGermaniumTellurideMagnetic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lafium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitaniumTungstenCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MAGNETRON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UXV
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_1048576
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_16384
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_262144
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_65536
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_1048576
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_16384
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_262144
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_65536
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NONUPLE_FLUID_EXPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NONUPLE_FLUID_IMPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUADRUPLE_FLUID_EXPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUADRUPLE_FLUID_IMPORT_HATCH
import net.minecraft.init.Blocks
import net.minecraft.init.Blocks.TORCH
import net.minecraft.init.Items.ELYTRA
import net.minecraft.item.ItemStack

internal object AssemblerRecipes
{

    // @formatter:off

    fun init()
    {
        itemHatchesRecipes()
        fluidHatchesRecipes()
        energyHatchesRecipes()
        dynamoHatchesRecipes()
        hiAmpEnergyHatchesRecipes()
        hiAmpDynamoHatchesRecipes()
        laserHatchesRecipes()
        voltageCoilRecipes()
        miningDroneRecipes()
        miscItemsRecipes()
        vanillaChangingRecipes()
    }

    private fun itemHatchesRecipes()
    {
        // Remove original recipes for UHV Item Import/Export Bus.
        ASSEMBLER_RECIPES.removeRecipe(
            arrayOf(HULL[UHV].stack(), QUANTUM_CHEST[LV].stack(),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        ASSEMBLER_RECIPES.removeRecipe(
            arrayOf(HULL[UHV].stack(), QUANTUM_CHEST[LV].stack(),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        // Add recipes for UHV-OpV Item Import/Export Bus, and add high-tier plastics recipes for
        // the original recipes.
        addIOHatchRecipes(ULV, ITEM_IMPORT_BUS[ULV], ITEM_EXPORT_BUS[ULV], OreDictNames.chestWood.toString())
        addIOHatchRecipes(LV,  ITEM_IMPORT_BUS[LV],  ITEM_EXPORT_BUS[LV],  OreDictNames.chestWood.toString())
        addIOHatchRecipes(MV,  ITEM_IMPORT_BUS[MV],  ITEM_EXPORT_BUS[MV],  BRONZE_CRATE.stack())
        addIOHatchRecipes(HV,  ITEM_IMPORT_BUS[HV],  ITEM_EXPORT_BUS[HV],  STEEL_CRATE.stack())
        addIOHatchRecipes(EV,  ITEM_IMPORT_BUS[EV],  ITEM_EXPORT_BUS[EV],  ALUMINIUM_CRATE.stack())
        addIOHatchRecipes(IV,  ITEM_IMPORT_BUS[IV],  ITEM_EXPORT_BUS[IV],  STAINLESS_STEEL_CRATE.stack())
        addIOHatchRecipes(LuV, ITEM_IMPORT_BUS[LuV], ITEM_EXPORT_BUS[LuV], TITANIUM_CRATE.stack())
        addIOHatchRecipes(ZPM, ITEM_IMPORT_BUS[ZPM], ITEM_EXPORT_BUS[ZPM], TUNGSTENSTEEL_CRATE.stack())
        addIOHatchRecipes(UV,  ITEM_IMPORT_BUS[UV],  ITEM_EXPORT_BUS[UV],  QUANTUM_CHEST[ULV].stack())
        addIOHatchRecipes(UHV, ITEM_IMPORT_BUS[UHV], ITEM_EXPORT_BUS[UHV], QUANTUM_CHEST[LV].stack())
        addIOHatchRecipes(UEV, ITEM_IMPORT_BUS[UEV], ITEM_EXPORT_BUS[UEV], QUANTUM_CHEST[MV].stack())
        addIOHatchRecipes(UIV, ITEM_IMPORT_BUS[UIV], ITEM_EXPORT_BUS[UIV], QUANTUM_CHEST[HV].stack())
        addIOHatchRecipes(UXV, ITEM_IMPORT_BUS[UXV], ITEM_EXPORT_BUS[UXV], QUANTUM_CHEST[EV].stack())
        addIOHatchRecipes(OpV, ITEM_IMPORT_BUS[OpV], ITEM_EXPORT_BUS[OpV], QUANTUM_CHEST[IV].stack())
    }

    private fun fluidHatchesRecipes()
    {
        // region Fluid Import/Export Hatch

        // Remove original recipes for UHV Fluid Import/Export Hatch.
        ASSEMBLER_RECIPES.removeRecipe(
            arrayOf(HULL[UHV].stack(), QUANTUM_TANK[LV].stack(),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        ASSEMBLER_RECIPES.removeRecipe(
            arrayOf(HULL[UHV].stack(), QUANTUM_TANK[LV].stack(),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        // Add recipes for UHV-OpV Fluid Import/Export Hatch, and add high-tier plastics recipes for
        // the original recipes.
        addIOHatchRecipes(ULV, FLUID_IMPORT_HATCH[ULV], FLUID_EXPORT_HATCH[ULV], ItemStack(Blocks.GLASS))
        addIOHatchRecipes(LV,  FLUID_IMPORT_HATCH[LV],  FLUID_EXPORT_HATCH[LV],  ItemStack(Blocks.GLASS))
        addIOHatchRecipes(MV,  FLUID_IMPORT_HATCH[MV],  FLUID_EXPORT_HATCH[MV],  BRONZE_DRUM.stack())
        addIOHatchRecipes(HV,  FLUID_IMPORT_HATCH[HV],  FLUID_EXPORT_HATCH[HV],  STEEL_DRUM.stack())
        addIOHatchRecipes(EV,  FLUID_IMPORT_HATCH[EV],  FLUID_EXPORT_HATCH[EV],  ALUMINIUM_DRUM.stack())
        addIOHatchRecipes(IV,  FLUID_IMPORT_HATCH[IV],  FLUID_EXPORT_HATCH[IV],  STAINLESS_STEEL_DRUM.stack())
        addIOHatchRecipes(LuV, FLUID_IMPORT_HATCH[LuV], FLUID_EXPORT_HATCH[LuV], TITANIUM_DRUM.stack())
        addIOHatchRecipes(ZPM, FLUID_IMPORT_HATCH[ZPM], FLUID_EXPORT_HATCH[ZPM], TUNGSTENSTEEL_DRUM.stack())
        addIOHatchRecipes(UV,  FLUID_IMPORT_HATCH[UV],  FLUID_EXPORT_HATCH[UV],  QUANTUM_TANK[ULV].stack())
        addIOHatchRecipes(UHV, FLUID_IMPORT_HATCH[UHV], FLUID_EXPORT_HATCH[UHV], QUANTUM_TANK[LV].stack())
        addIOHatchRecipes(UEV, FLUID_IMPORT_HATCH[UEV], FLUID_EXPORT_HATCH[UEV], QUANTUM_TANK[MV].stack())
        addIOHatchRecipes(UIV, FLUID_IMPORT_HATCH[UIV], FLUID_EXPORT_HATCH[UIV], QUANTUM_TANK[HV].stack())
        addIOHatchRecipes(UXV, FLUID_IMPORT_HATCH[UXV], FLUID_EXPORT_HATCH[UXV], QUANTUM_TANK[EV].stack())
        addIOHatchRecipes(OpV, FLUID_IMPORT_HATCH[OpV], FLUID_EXPORT_HATCH[OpV], QUANTUM_TANK[IV].stack())

        // endregion

        // region 4x/9x Fluid Import/Export Hatch

        // ULV Quadruple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_IMPORT_HATCH[ULV])
            input(pipeQuadrupleFluid, TinAlloy)
            fluidInputs(Glue.getFluid(1000))
            output(QUADRUPLE_FLUID_IMPORT_HATCH[0])
            EUt(VA[ULV])
            duration(15 * SECOND)
        }

        // LV Quadruple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_IMPORT_HATCH[LV])
            input(pipeQuadrupleFluid, Copper)
            fluidInputs(Glue.getFluid(2000))
            output(QUADRUPLE_FLUID_IMPORT_HATCH[1])
            EUt(VA[LV])
            duration(15 * SECOND)
        }

        // MV Quadruple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_IMPORT_HATCH[MV])
            input(pipeQuadrupleFluid, Steel)
            fluidInputs(Polyethylene.getFluid(L * 4))
            output(QUADRUPLE_FLUID_IMPORT_HATCH[2])
            EUt(VA[MV])
            duration(15 * SECOND)
        }

        // HV Quadruple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_IMPORT_HATCH[HV])
            input(pipeQuadrupleFluid, StainlessSteel)
            fluidInputs(Polyethylene.getFluid(L * 4))
            output(QUADRUPLE_FLUID_IMPORT_HATCH[3])
            EUt(VA[HV])
            duration(15 * SECOND)
        }

        // ULV Quadruple Output Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_EXPORT_HATCH[ULV])
            input(pipeQuadrupleFluid, TinAlloy)
            fluidInputs(Glue.getFluid(1000))
            output(QUADRUPLE_FLUID_EXPORT_HATCH[0])
            EUt(VA[ULV])
            duration(15 * SECOND)
        }

        // LV Quadruple Output Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_EXPORT_HATCH[LV])
            input(pipeQuadrupleFluid, Copper)
            fluidInputs(Glue.getFluid(2000))
            output(QUADRUPLE_FLUID_EXPORT_HATCH[1])
            EUt(VA[LV])
            duration(15 * SECOND)
        }

        // MV Quadruple Output Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_EXPORT_HATCH[MV])
            input(pipeQuadrupleFluid, Steel)
            fluidInputs(Polyethylene.getFluid(L * 4))
            output(QUADRUPLE_FLUID_EXPORT_HATCH[2])
            EUt(VA[MV])
            duration(15 * SECOND)
        }

        // HV Quadruple Output Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(4)
            input(FLUID_EXPORT_HATCH[HV])
            input(pipeQuadrupleFluid, StainlessSteel)
            fluidInputs(Polyethylene.getFluid(L * 4))
            output(QUADRUPLE_FLUID_EXPORT_HATCH[3])
            EUt(VA[HV])
            duration(15 * SECOND)
        }

        // ULV Nonuple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_IMPORT_HATCH[ULV])
            input(pipeNonupleFluid, TinAlloy)
            fluidInputs(Glue.getFluid(2250))
            output(NONUPLE_FLUID_IMPORT_HATCH[0])
            EUt(VA[ULV])
            duration(30 * SECOND)
        }

        // LV Nonuple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_IMPORT_HATCH[LV])
            input(pipeNonupleFluid, Copper)
            fluidInputs(Glue.getFluid(4500))
            output(NONUPLE_FLUID_IMPORT_HATCH[1])
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        // MV Nonuple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_IMPORT_HATCH[MV])
            input(pipeNonupleFluid, Steel)
            fluidInputs(Polyethylene.getFluid(L * 9))
            output(NONUPLE_FLUID_IMPORT_HATCH[2])
            EUt(VA[MV])
            duration(30 * SECOND)
        }

        // HV Nonuple Input Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_IMPORT_HATCH[HV])
            input(pipeNonupleFluid, StainlessSteel)
            fluidInputs(Polyethylene.getFluid(L * 9))
            output(NONUPLE_FLUID_IMPORT_HATCH[3])
            EUt(VA[HV])
            duration(30 * SECOND)
        }

        // ULV Nonuple Export Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_EXPORT_HATCH[ULV])
            input(pipeNonupleFluid, TinAlloy)
            fluidInputs(Glue.getFluid(2250))
            output(NONUPLE_FLUID_EXPORT_HATCH[0])
            EUt(VA[ULV])
            duration(30 * SECOND)
        }

        // LV Nonuple Export Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_EXPORT_HATCH[LV])
            input(pipeNonupleFluid, Copper)
            fluidInputs(Glue.getFluid(4500))
            output(NONUPLE_FLUID_EXPORT_HATCH[1])
            EUt(VA[LV])
            duration(30 * SECOND)
        }

        // MV Nonuple Export Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_EXPORT_HATCH[MV])
            input(pipeNonupleFluid, Steel)
            fluidInputs(Polyethylene.getFluid(L * 9))
            output(NONUPLE_FLUID_EXPORT_HATCH[2])
            EUt(VA[MV])
            duration(30 * SECOND)
        }

        // HV Nonuple Export Hatch
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(FLUID_EXPORT_HATCH[HV])
            input(pipeNonupleFluid, StainlessSteel)
            fluidInputs(Polyethylene.getFluid(L * 9))
            output(NONUPLE_FLUID_EXPORT_HATCH[3])
            EUt(VA[HV])
            duration(30 * SECOND)
        }

        // Modify UHV 4x/9x Hatch to use Kevlar to align the plastic requirement remove original UHV 4x/9x Hatch recipe.
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(FLUID_IMPORT_HATCH[UHV].stack(),
                    Neutronium.getItemForm(pipeQuadrupleFluid),
                    IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(FLUID_EXPORT_HATCH[UHV].stack(),
                    Neutronium.getItemForm(pipeQuadrupleFluid),
                    IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(Polybenzimidazole.getFluid(L * 5)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(FLUID_IMPORT_HATCH[UHV].stack(),
                    Neutronium.getItemForm(pipeNonupleFluid),
                    IntCircuitIngredient.getIntegratedCircuit(9)),
            arrayOf(Polybenzimidazole.getFluid(L * 9)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(FLUID_EXPORT_HATCH[UHV].stack(),
                    Neutronium.getItemForm(pipeNonupleFluid),
                    IntCircuitIngredient.getIntegratedCircuit(9)),
            arrayOf(Polybenzimidazole.getFluid(L * 9)))

        addMultiFluidHatchRecipes(UHV, Europium)
        addMultiFluidHatchRecipes(UEV, Duranium)
        addMultiFluidHatchRecipes(UIV, Neutronium)
        addMultiFluidHatchRecipes(UXV, HeavyQuarkDegenerateMatter)
        addMultiFluidHatchRecipes(OpV, QuantumchromodynamicallyConfinedMatter)

        // endregion
    }

    private fun energyHatchesRecipes()
    {
        // ...
    }

    private fun dynamoHatchesRecipes()
    {
        // TODO: Remove this bugfix when GTCEu PR#2910 merged.
        ASSEMBLER_RECIPES.removeRecipe(
            TRANSFORMER[ULV].stack(),
            ENERGY_OUTPUT_HATCH_4A[ULV].stack(),
            OreDictUnifier.get(wireGtOctal, Lead, 2),
            OreDictUnifier.get(plate, WroughtIron, 4))
    }

    private fun hiAmpEnergyHatchesRecipes()
    {
        // ...
    }

    private fun hiAmpDynamoHatchesRecipes()
    {
        // ...
    }

    private fun laserHatchesRecipes()
    {
        // Add recipes for original laser hatches in GTCEu.
        for (tier in UHV..OpV)
        {
            val actualTier = tier // Because laser hatch start at IV stage.
            // 256A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(1)
                input(HULL[tier])
                input(lens, Diamond)
                inputs(CraftingComponent.SENSOR.getIngredient(tier) as ItemStack)
                inputs(CraftingComponent.PUMP.getIngredient(tier) as ItemStack)
                input(cableGtSingle, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_INPUT_HATCH_256[actualTier])
                EUt(VA[tier].toLong())
                duration(15 * SECOND)
            }

            // 256A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(1)
                input(HULL[tier])
                input(lens, Diamond)
                inputs(CraftingComponent.EMITTER.getIngredient(tier) as ItemStack)
                inputs(CraftingComponent.PUMP.getIngredient(tier) as ItemStack)
                input(cableGtSingle, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_OUTPUT_HATCH_256[actualTier])
                EUt(VA[tier].toLong())
                duration(15 * SECOND)
            }

            // 1024A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(2)
                input(HULL[tier])
                input(lens, Diamond, 2)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(2))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(2))
                input(cableGtDouble, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_INPUT_HATCH_1024[actualTier])
                EUt(VA[tier])
                duration(30 * SECOND)
            }

            // 1024A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(2)
                input(HULL[tier])
                input(lens, Diamond, 2)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(2))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(2))
                input(cableGtDouble, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_OUTPUT_HATCH_1024[actualTier])
                EUt(VA[tier])
                duration(30 * SECOND)
            }

            // 4096A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(2)
                input(HULL[tier])
                input(lens, Diamond, 4)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(4))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(4))
                input(cableGtQuadruple, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_INPUT_HATCH_4096[actualTier])
                EUt(VA[tier])
                duration(1 * MINUTE)
            }

            // 4096A Large Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(2)
                input(HULL[tier])
                input(lens, Diamond, 4)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(4))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(4))
                input(cableGtQuadruple, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_OUTPUT_HATCH_4096[actualTier])
                EUt(VA[tier])
                duration(1 * MINUTE)
            }
        }
        // Advanced laser hatches consists of 16384A-1048576A, these recipes is added for these parts.
        for (tier in IV..OpV)
        {
            val actualTier = tier - IV // Because laser hatch start at IV stage.
            // 16384A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(4)
                input(HULL[tier])
                input(lens, Diamond, 8)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(8))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(8))
                input(cableGtOctal, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_INPUT_HATCH_16384[actualTier])
                EUt(VA[tier])
                duration(2 * MINUTE)
            }

            // 16384A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(4)
                input(HULL[tier])
                input(lens, Diamond, 8)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(8))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(8))
                input(cableGtOctal, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_OUTPUT_HATCH_16384[actualTier])
                EUt(VA[tier])
                duration(2 * MINUTE)
            }

            // 65536A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(5)
                input(HULL[tier])
                input(lens, Diamond, 16)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(16))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(16))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_INPUT_HATCH_65536[actualTier])
                EUt(VA[tier])
                duration(4 * MINUTE)
            }

            // 65536A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(5)
                input(HULL[tier])
                input(lens, Diamond, 16)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(16))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(16))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 4)
                output(LASER_OUTPUT_HATCH_65536[actualTier])
                EUt(VA[tier])
                duration(4 * MINUTE)
            }

            // 262144A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(6)
                input(HULL[tier])
                input(lens, Diamond, 32)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(32))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(32))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 8)
                output(LASER_INPUT_HATCH_262144[actualTier])
                EUt(VA[tier])
                duration(8 * MINUTE)
            }

            // 262144A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(6)
                input(HULL[tier])
                input(lens, Diamond, 32)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(32))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(32))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 8)
                output(LASER_OUTPUT_HATCH_262144[actualTier])
                EUt(VA[tier])
                duration(8 * MINUTE)
            }

            // 1048576A Laser Target Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(7)
                input(HULL[tier])
                input(lens, Diamond, 64)
                inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 16)
                output(LASER_INPUT_HATCH_1048576[actualTier])
                EUt(VA[tier])
                duration(16 * MINUTE)
            }

            // 1048576A Laser Source Hatch
            ASSEMBLER_RECIPES.addRecipe {
                circuitMeta(7)
                input(HULL[tier])
                input(lens, Diamond, 64)
                inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 16)
                output(LASER_OUTPUT_HATCH_1048576[actualTier])
                EUt(VA[tier])
                duration(16 * MINUTE)
            }
        }
    }

    private fun voltageCoilRecipes()
    {
        // UHV Voltage Coil
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, ChromiumGermaniumTellurideMagnetic)
            input(wireFine, Taranium, 16)
            output(VOLTAGE_COIL_UHV)
            EUt(VA[UHV])
            duration(10 * SECOND)
        }

        // UEV Voltage Coil
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, ChromiumGermaniumTellurideMagnetic)
            input(wireFine, MetastableOganesson, 16)
            output(VOLTAGE_COIL_UEV)
            EUt(VA[UEV])
            duration(10 * SECOND)
        }

        // UIV Voltage Coil
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, Magnetium)
            input(wireFine, QuantumchromodynamicallyConfinedMatter, 16)
            output(VOLTAGE_COIL_UIV)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        // UXV Voltage Coil
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, Magnetium)
            input(wireFine, Mellion, 16)
            output(VOLTAGE_COIL_UXV)
            EUt(VA[UXV])
            duration(10 * SECOND)
        }

        // OpV Voltage Coil
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, Magnetium)
            input(wireFine, WhiteDwarfMatter, 16)
            output(VOLTAGE_COIL_OpV)
            EUt(VA[OpV])
            duration(10 * SECOND)
        }

        // TODO: Max Voltage Coil
    }

    private fun miningDroneRecipes()
    {
        // LV Mining Drone
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(12)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(circuit, Tier.LV)
            input(toolHeadDrill, Steel, 2)
            input(EMITTER_LV)
            input(SENSOR_LV)
            input(rotor, Steel)
            input(cableGtSingle, Tin, 2)
            fluidInputs(TinAlloy.getFluid(L * 4))
            output(MINING_DRONE_LV)
            EUt(VA[LV])
            duration(10 * SECOND)
        }

        // MV Mining Drone
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(12)
            input(frameGt, Aluminium)
            input(plate, Aluminium, 4)
            input(circuit, Tier.MV)
            input(toolHeadDrill, Aluminium, 2)
            input(EMITTER_MV)
            input(SENSOR_MV)
            input(rotor, Aluminium)
            input(cableGtSingle, Copper, 2)
            fluidInputs(Kanthal.getFluid(L * 4))
            output(MINING_DRONE_MV)
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        // HV Mining Drone
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(12)
            input(frameGt, StainlessSteel)
            input(plate, StainlessSteel, 6)
            input(circuit, Tier.HV)
            input(toolHeadDrill, StainlessSteel, 4)
            input(EMITTER_HV)
            input(SENSOR_HV)
            input(rotor, StainlessSteel, 2)
            input(cableGtSingle, Gold, 2)
            fluidInputs(Nichrome.getFluid(L * 4))
            output(MINING_DRONE_HV)
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // EV Mining Drone
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(12)
            input(frameGt, Titanium)
            input(plate, Titanium, 6)
            input(circuit, Tier.EV)
            input(toolHeadDrill, Titanium, 4)
            input(EMITTER_EV)
            input(SENSOR_EV)
            input(rotor, Titanium, 2)
            input(cableGtSingle, Aluminium, 2)
            fluidInputs(TungstenCarbide.getFluid(L * 4))
            output(MINING_DRONE_EV)
            EUt(VA[EV])
            duration(10 * SECOND)
        }

        // IV Mining Drone
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(12)
            input(frameGt, TungstenSteel)
            input(plate, TungstenSteel, 8)
            input(circuit, Tier.IV)
            input(toolHeadDrill, TungstenSteel, 6)
            input(EMITTER_IV)
            input(SENSOR_IV)
            input(rotor, TungstenSteel, 4)
            input(cableGtSingle, Platinum, 2)
            fluidInputs(Ultimet.getFluid(L * 4))
            output(MINING_DRONE_IV)
            EUt(VA[IV])
            duration(10 * SECOND)
        }
    }

    private fun miscItemsRecipes()
    {
        // Magnetron
        ASSEMBLER_RECIPES.addRecipe {
            input(ring, BerylliumOxide, 64)
            input(ring, BerylliumOxide, 64)
            input(plate, HSLASteel, 6)
            input(VACUUM_TUBE)
            output(MAGNETRON)
            EUt(VA[IV])
            duration(30 * SECOND)
        }

        // Advanced recipe for Neutron Reflector.
        ASSEMBLER_RECIPES.addRecipe {
            input(plate, Osmiridium)
            input(plateDouble, BerylliumOxide, 4)
            input(plateDouble, TitaniumTungstenCarbide, 2)
            fluidInputs(TinAlloy.getFluid(L * 16))
            output(NEUTRON_REFLECTOR, 4)
            EUt(VA[EV])
            duration(40 * SECOND)
        }

        // Ultimate recipe for Neutron Reflector.
        ASSEMBLER_RECIPES.addRecipe {
            input(plate, Pikyonium64B)
            input(plateDouble, TantalumCarbide, 4)
            input(plateDouble, TitanSteel, 2)
            fluidInputs(TinAlloy.getFluid(L * 8))
            output(NEUTRON_REFLECTOR, 16)
            EUt(VA[LuV])
            duration(20 * SECOND)
        }

        // Perfect recipe for Neutron Reflector.
        ASSEMBLER_RECIPES.addRecipe {
            input(plate, Lafium)
            input(plateDouble, TantalumHafniumSeaborgiumCarbide, 4)
            input(plateDouble, HDCS, 2)
            fluidInputs(TinAlloy.getFluid(L * 4))
            output(NEUTRON_REFLECTOR, 64)
            EUt(VA[UV])
            duration(10 * SECOND)
        }

        // Advanced recipes for Optical Pipe.
        ASSEMBLER_RECIPES.addRecipe {
            input(wireFine, GSTGlass, 8)
            input(foil, SterlingSilver, 8)
            fluidInputs(Polybenzimidazole.getFluid(L))
            outputs(MetaBlocks.OPTICAL_PIPES[0].getStack(16))
            EUt(VA[ZPM])
            duration(5 * SECOND)
            cleanroom()
        }

        ASSEMBLER_RECIPES.addRecipe {
            input(wireFine, Polymethylmethacrylate, 8)
            input(foil, Germanium, 8)
            fluidInputs(ChromaticGlass.getFluid(L))
            outputs(MetaBlocks.OPTICAL_PIPES[0].getStack(64))
            EUt(VA[UHV])
            duration(5 * SECOND)
            cleanroom()
        }

        // Advanced recipes for Laser Pipe.
        ASSEMBLER_RECIPES.addRecipe {
            inputs(GlassCasing.ZBLAN.stack)
            input(foil, NaquadahAlloy, 2)
            fluidInputs(Polybenzimidazole.getFluid(L))
            outputs(MetaBlocks.LASER_PIPES[0].getStack(16))
            EUt(VA[ZPM])
            duration(5 * SECOND)
            cleanroom()
        }

        ASSEMBLER_RECIPES.addRecipe {
            inputs(GlassCasing.ERBIUM_ZBLAN.stack)
            input(foil, EnrichedNaquadahAlloy, 2)
            fluidInputs(ChromaticGlass.getFluid(L))
            outputs(MetaBlocks.LASER_PIPES[0].getStack(64))
            EUt(VA[UHV])
            duration(5 * SECOND)
            cleanroom()
        }

        ASSEMBLER_RECIPES.addRecipe {
            inputs(GlassCasing.PRASEODYMIUM_ZBLAN.stack)
            input(foil, EnrichedNaquadahAlloy, 2)
            fluidInputs(ChromaticGlass.getFluid(L))
            outputs(MetaBlocks.LASER_PIPES[0].getStack(64))
            EUt(VA[UHV])
            duration(5 * SECOND)
            cleanroom()
        }
    }

    private fun vanillaChangingRecipes()
    {
        // Elytra (vanilla)
        ASSEMBLER_RECIPES.addRecipe {
            input(plate, Polytetrafluoroethylene, 2)
            input(plate, StainlessSteel)
            input(ring, StainlessSteel, 2)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(ELYTRA)
            EUt(VH[HV])
            duration(10 * SECOND)
        }

        // Torch
        ASSEMBLER_RECIPES.addRecipe {
            input(gem, Lignite)
            input(stick, Wood)
            outputs(TORCH.getStack(4))
            EUt(1)
            duration(5 * SECOND)
        }

        ASSEMBLER_RECIPES.addRecipe {
            input(dust, Lignite)
            input(stick, Wood)
            outputs(TORCH.getStack(4))
            EUt(1)
            duration(5 * SECOND)
        }
    }

    // @formatter:on

}