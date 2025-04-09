package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP

@Suppress("MISSING_DEPENDENCY_CLASS")
class CircuitAssemblerRecipes
{

    companion object
    {

        fun init()
        {

            // Data Module
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    RANDOM_ACCESS_MEMORY.getStackForm(32),
                    NOR_MEMORY_CHIP.getStackForm(64),
                    NAND_MEMORY_CHIP.getStackForm(64),
                    OreDictUnifier.get(wireFine, YttriumBariumCuprate, 32)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                    OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM, 2),
                    RANDOM_ACCESS_MEMORY.getStackForm(32),
                    NOR_MEMORY_CHIP.getStackForm(64),
                    NAND_MEMORY_CHIP.getStackForm(64),
                    OreDictUnifier.get(wireFine, YttriumBariumCuprate, 32)),
                arrayOf(Tin.getFluid(2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(ADVANCED_RAM_CHIP, 32)
                .input(NOR_MEMORY_CHIP, 64)
                .input(NAND_MEMORY_CHIP, 64)
                .input(wireFine, YttriumBariumCuprate, 32)
                .output(TOOL_DATA_MODULE)
                .EUt(38400) // ZPM
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .solderMultiplier(2)
                .buildAndRegister()

        }

    }

}