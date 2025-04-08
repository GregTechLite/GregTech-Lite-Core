package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Bacteria
import gregtech.api.unification.material.Materials.BacterialSludge
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.NEURO_PROCESSOR
import gregtech.common.items.MetaItems.NEUTRONIUM_WAFER
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BrevibacteriumFlavum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CupriavidusNecator
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTRecipeUtility

@Suppress("MISSING_DEPENDENCY_CLASS")
class WetwareCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            circuitComponentsRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {

        }

        private fun circuitComponentsRecipes()
        {
            // Stem Cells
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(OreDictUnifier.get(dust, Osmiridium)),
                arrayOf(SterileGrowthMedium.getFluid(500),
                    Bacteria.getFluid(500)))

            for (bacteria in arrayOf(BrevibacteriumFlavum,
                CupriavidusNecator))
            {
                CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                    .input(dust, bacteria)
                    .fluidInputs(SterileGrowthMedium.getFluid(100))
                    .fluidInputs(Bacteria.getFluid(500))
                    .output(STEM_CELLS, 64)
                    .fluidOutputs(BacterialSludge.getFluid(500))
                    .EUt(VA[LuV].toLong())
                    .duration(15 * SECOND)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .buildAndRegister()
            }

            // Neuro Process
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(WETWARE_CIRCUIT_BOARD.stackForm,
                    STEM_CELLS.getStackForm(16),
                    OreDictUnifier.get(pipeSmallFluid, Polybenzimidazole, 8),
                    OreDictUnifier.get(plate, Electrum, 8),
                    OreDictUnifier.get(foil, SiliconeRubber, 16),
                    OreDictUnifier.get(bolt, HSSE, 8)),
                arrayOf(SterileGrowthMedium.getFluid(250)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD)
                .input(STEM_CELLS, 8)
                .input(pipeTinyFluid, Polybenzimidazole, 2)
                .input(plate, Electrum, 4)
                .input(foil, SiliconeRubber, 16)
                .input(bolt, HSSE, 8)
                .fluidInputs(SterileGrowthMedium.getFluid(250))
                .output(NEURO_PROCESSOR, 2)
                .EUt(80000) // ZPM
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

        private fun circuitRecipes()
        {

        }

    }

}