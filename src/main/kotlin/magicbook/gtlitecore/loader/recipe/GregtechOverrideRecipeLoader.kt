package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VN
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockCleanroomCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_IV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_IV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_IV
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getComponentCableByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getConveyorByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getConveyorStackByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getMotorByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getMotorStackByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getPipeByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getPumpByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getPumpStackByTier
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getComponentMaterialByTier
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import java.util.*

@Suppress("MISSING_DEPENDENCY_CLASS")
class GregtechOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {

            // Let ingotIron can smelt to ingotWroughtIron just like nuggetIron -> nuggetWroughtIron.
            ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Iron),
                OreDictUnifier.get(ingot, WroughtIron))

            // Down-tier Clay electrolysis to provide another choice of aluminium,
            // cryolite and ruby juice and this is three methods to get aluminium at LV stage.
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

            // Reduce time spent of water electrolysis from 75s to 15s.
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

            // Another correspondenced recipes please see: AluminiumSodiumProcessing,
            // this is a conflict resolved of these recipes.

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
            for (i in LV .. EV)
            {
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_silicone_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_${VN[i].lowercase(Locale.getDefault())}_styrene_butadiene_rubber")
            }
            ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_iv_silicone_rubber")
            ModHandler.removeRecipeByName("${GTValues.MODID}:conveyor_module_iv_styrene_butadiene_rubber")

            for (i in LV .. EV)
            {
                ModHandler.addShapedRecipe(true, "conveyor_module.${VN[i].lowercase(Locale.getDefault())}", getConveyorStackByTier(i),
                    "PPP", "MCM", "PPP",
                    'M', getMotorByTier(i),
                    'P', "plateAnyRubber",
                    'C', UnificationEntry(cableGtSingle, getComponentCableByTier(i)))
            }

            ModHandler.addShapedRecipe(true, "conveyor_module.iv", CONVEYOR_MODULE_IV.stackForm,
                "PPP", "MCM", "PPP",
                'M', ELECTRIC_MOTOR_IV,
                'P', "plateAnySyntheticRubber",
                'C', UnificationEntry(cableGtSingle, Tungsten))

            for (i in LV .. EV)
            {
                for (rubber in arrayOf(Rubber, SiliconeRubber, StyreneButadieneRubber))
                {
                    GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                        arrayOf(GTLiteUtility.copy(getMotorStackByTier(i), 2),
                            OreDictUnifier.get(cableGtSingle, getComponentCableByTier(i)),
                            IntCircuitIngredient.getIntegratedCircuit(1)),
                        arrayOf(rubber.getFluid(L * 6)))
                }
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(cableGtSingle, getComponentCableByTier(i))
                    .inputs(GTLiteUtility.copy(getMotorStackByTier(i), 2))
                    .input("plateAnyRubber", 6)
                    .output(getConveyorByTier(i))
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

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
            for (i in LV .. EV)
            {
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_silicone_rubber")
                ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_${VN[i].lowercase()}_styrene_butadiene_rubber")
            }
            ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_iv_silicone_rubber")
            ModHandler.removeRecipeByName("${GTValues.MODID}:electric_pump_iv_styrene_butadiene_rubber")

            for (i in LV .. EV)
            {
                ModHandler.addShapedRecipe(true, "electric_pump.${VN[i].lowercase()}", getPumpStackByTier(i),
                    "STR", "hPw", "RMC",
                    'S', UnificationEntry(screw, getComponentMaterialByTier(i)),
                    'T', UnificationEntry(rotor, getComponentMaterialByTier(i)),
                    'R', "ringAnyRubber",
                    'P', UnificationEntry(pipeNormalFluid, getPipeByTier(i)),
                    'M', getMotorByTier(i),
                    'C', UnificationEntry(cableGtSingle, getComponentCableByTier(i)))
            }
            ModHandler.addShapedRecipe(true, "electric_pump.iv", ELECTRIC_PUMP_IV.stackForm,
                "STR", "hPw", "RMC",
                'S', UnificationEntry(screw, TungstenSteel),
                'T', UnificationEntry(rotor, TungstenSteel),
                'R', "ringAnySyntheticRubber",
                'P', UnificationEntry(pipeNormalFluid, TungstenSteel),
                'M', ELECTRIC_MOTOR_IV,
                'C', UnificationEntry(cableGtSingle, Tungsten))

            for (i in LV .. EV)
            {
                for (rubber in arrayOf(Rubber, SiliconeRubber, StyreneButadieneRubber))
                {
                    GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                        *arrayOf(OreDictUnifier.get(cableGtSingle, getComponentCableByTier(i)),
                            OreDictUnifier.get(pipeNormalFluid, getPipeByTier(i)),
                            OreDictUnifier.get(screw, getComponentMaterialByTier(i)),
                            OreDictUnifier.get(rotor, getComponentMaterialByTier(i)),
                            OreDictUnifier.get(ring, rubber, 2),
                            getMotorStackByTier(i)))
                }

                ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, getComponentCableByTier(i))
                    .input(pipeNormalFluid, getPipeByTier(i))
                    .input(screw, getComponentMaterialByTier(i))
                    .input(rotor, getComponentMaterialByTier(i))
                    .input("ringAnyRubber", 2)
                    .input(getMotorByTier(i))
                    .output(getPumpByTier(i))
                    .EUt(VA[LV].toLong())
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

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

        }

    }

}