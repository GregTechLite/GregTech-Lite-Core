package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP

internal object CircuitAssemblerRecipes
{

    // @formatter:off

    fun init()
    {

        // Data Module
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                RANDOM_ACCESS_MEMORY.getStackForm(32),
                NOR_MEMORY_CHIP.getStackForm(64),
                NAND_MEMORY_CHIP.getStackForm(64),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 32)),
            arrayOf(SolderingAlloy.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                OreDictUnifier.get(circuit, Tier.ZPM, 2),
                RANDOM_ACCESS_MEMORY.getStackForm(32),
                NOR_MEMORY_CHIP.getStackForm(64),
                NAND_MEMORY_CHIP.getStackForm(64),
                OreDictUnifier.get(wireFine, YttriumBariumCuprate, 32)),
            arrayOf(Tin.getFluid(L * 2)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(WETWARE_CIRCUIT_BOARD)
            .input(circuit, Tier.ZPM, 2)
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

    // @formatter:on

}