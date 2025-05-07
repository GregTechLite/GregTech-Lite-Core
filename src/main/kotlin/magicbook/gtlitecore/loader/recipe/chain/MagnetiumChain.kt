package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HadronicResonantGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Magnetium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class MagnetiumChain
{

    companion object
    {

        // TODO Other components' recipes?
        fun init()
        {
            // dustMagnetium
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, Europium)
                .fluidInputs(HadronicResonantGas.getFluid(250))
                .output(dust, Magnetium)
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ingotMagnetium
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(ingot, Europium)
                .fluidInputs(HadronicResonantGas.getFluid(250))
                .output(ingot, Magnetium)
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // stickMagnetium
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(stick, Europium)
                .fluidInputs(HadronicResonantGas.getFluid(125))
                .output(stick, Magnetium)
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // stickLongMagnetium
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(stickLong, Europium)
                .fluidInputs(HadronicResonantGas.getFluid(250))
                .output(stickLong, Magnetium)
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // blockMagnetium
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(block, Europium)
                .fluidInputs(HadronicResonantGas.getFluid(250 * 9))
                .output(block, Magnetium)
                .EUt(VA[UIV].toLong())
                .duration(10 * 9 * SECOND)
                .buildAndRegister()
        }

    }

}