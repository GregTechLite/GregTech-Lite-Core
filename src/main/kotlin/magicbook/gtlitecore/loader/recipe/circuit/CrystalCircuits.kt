package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.L
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR

@Suppress("MISSING_DEPENDENCY_CLASS")
class CrystalCircuits
{

    companion object
    {

        fun init()
        {
            // IV Crystal Processor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(6),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(6),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                    OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                    OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
                .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
                .input(ADVANCED_SMD_CAPACITOR, 4)
                .input(ADVANCED_SMD_TRANSISTOR, 4)
                .input(wireFine, NiobiumTitanium, 8)
                .output(CRYSTAL_PROCESSOR_IV, 4)
                .EUt(9600) // LuV
                .duration(10 * SECOND)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
                .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
                .input(GOOWARE_SMD_CAPACITOR)
                .input(GOOWARE_SMD_TRANSISTOR)
                .input(wireFine, NiobiumTitanium, 8)
                .output(CRYSTAL_PROCESSOR_IV, 4)
                .EUt(9600) // LuV
                .duration(5 * SECOND)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_SYSTEM_ON_CHIP)
                .input(wireFine, NiobiumTitanium, 8)
                .input(bolt, YttriumBariumCuprate, 8)
                .output(CRYSTAL_PROCESSOR_IV, 8)
                .EUt(86000) // ZPM
                .duration(2 * SECOND + 10 * TICK)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // LuV Crystal Processor Assembly

            // ZPM Crystal Supercomputer

            // UV Crystal Mainframe

        }

    }

}