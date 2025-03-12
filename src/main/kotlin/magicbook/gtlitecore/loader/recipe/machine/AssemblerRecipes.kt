package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.stickLong
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHROME_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRIDIUM_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LEAD_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TUNGSTEN_DRUM

class AssemblerRecipes
{

    companion object
    {

        fun init()
        {
            // Iron Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iron, 2)
                .input(plate, Iron, 4)
                .output(IRON_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Copper Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Copper, 2)
                .input(plate, Copper, 4)
                .output(COPPER_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Lead Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Lead, 2)
                .input(plate, Lead, 4)
                .output(LEAD_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Chrome Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Chrome, 2)
                .input(plate, Chrome, 4)
                .output(CHROME_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tungsten Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Tungsten, 2)
                .input(plate, Tungsten, 4)
                .output(TUNGSTEN_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Iridium Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iridium, 2)
                .input(plate, Iridium, 4)
                .output(IRIDIUM_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}