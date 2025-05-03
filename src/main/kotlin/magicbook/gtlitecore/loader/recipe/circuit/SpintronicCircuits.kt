package magicbook.gtlitecore.loader.recipe.circuit

@Suppress("MISSING_DEPENDENCY_CLASS")
class SpintronicCircuits
{

    companion object
    {

        fun init()
        {
            circuitBoardRecipes()
            smdRecipes()
            circuitRecipes()
        }

        private fun circuitBoardRecipes()
        {

        }

        private fun smdRecipes()
        {
            // Spintronic SMD Transistor

            // Spintronic SMD Resistor

            // Spintronic SMD Capacitor

            // Spintronic SMD Diode

            // Spintronic SMD Inductor
        }

        private fun circuitRecipes()
        {
            // Spintronic Processor

            // Spintronic Assembly

            // Spintronic Computer

            // ASSEMBLY_LINE_RECIPES.recipeBuilder() // template
            //     .input(PERFECT_CIRCUIT_BOARD)
            //     .input(OPTICAL_PROCESSOR_UV, 4)
            //     .input(OPTICAL_SMD_CAPACITOR, 16)
            //     .input(OPTICAL_SMD_INDUCTOR, 16)
            //     .input(ADVANCED_SYSTEM_ON_CHIP, 32)
            //     .input(OPTICAL_FIBER, 16)
            //     .input("foilAnySyntheticRubber", 32)
            //     .fluidInputs(SolderingAlloy.getFluid(L * 4))
            //     .output(OPTICAL_ASSEMBLY_UHV, 3)
            //     .EUt(VA[UHV].toLong())
            //     .duration(20 * SECOND)
            //     .stationResearch { r ->
            //         r.researchStack(OPTICAL_PROCESSOR_UV.stackForm)
            //             .EUt(VA[UHV].toLong())
            //             .CWUt(32)
            //     }
            //     .buildAndRegister()

            // Spintronic Mainframe

        }

    }

}