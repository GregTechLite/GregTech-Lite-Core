package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.spring
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.common.block.variant.component.ProcessorCasing

internal object ProcessorCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val processorCasings = arrayOf(ProcessorCasing.LV.stack, ProcessorCasing.MV.stack, ProcessorCasing.HV.stack,
                                       ProcessorCasing.EV.stack, ProcessorCasing.IV.stack, ProcessorCasing.LuV.stack,
                                       ProcessorCasing.ZPM.stack, ProcessorCasing.UV.stack, ProcessorCasing.UHV.stack,
                                       ProcessorCasing.UEV.stack, ProcessorCasing.UIV.stack, ProcessorCasing.UXV.stack,
                                       ProcessorCasing.OpV.stack, ProcessorCasing.MAX.stack)

        // For my testing, used GTLiteUtility#getCircuitByTier() will caused
        // circuit cannot be circuitX, so...

        // LV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.LV)
            outputs(processorCasings[0])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // MV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.MV)
            outputs(processorCasings[1])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // HV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.HV)
            outputs(processorCasings[2])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // EV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.EV)
            outputs(processorCasings[3])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // IV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.IV)
            outputs(processorCasings[4])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // LuV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.LuV)
            outputs(processorCasings[5])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // ZPM Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.ZPM)
            outputs(processorCasings[6])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // UV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.UV)
            outputs(processorCasings[7])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // UHV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.UHV)
            outputs(processorCasings[8])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // UEV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.UEV)
            outputs(processorCasings[9])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // UIV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.UIV)
            outputs(processorCasings[10])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // UXV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.UXV)
            outputs(processorCasings[11])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // OpV Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.OpV)
            outputs(processorCasings[12])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }

        // MAX Processor Casing
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(9)
            input(frameGt, Steel)
            input(plate, Steel, 4)
            input(spring, Steel, 2)
            input(circuit, Tier.MAX)
            outputs(processorCasings[13])
            EUt(VH[LV])
            duration(2 * SECOND + 10 * TICK)
        }
    }

    // @formatter:on

}