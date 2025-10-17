package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Actinium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Caesium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.NUCLEAR_FUELS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MOX
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Plutonium244
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSteam
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheatedSteam
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRod
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodDepleted
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodEnriched
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodEnrichedDepleted
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensity
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensityDepleted
import net.minecraftforge.fluids.FluidStack

internal object NuclearFissionRecipeProducer
{

    // @formatter:off
    fun produce()
    {
        //    Th
        //   /  \
        // U238  Pa
        createFission(Thorium, MV, 20 * SECOND, 570)
        depletedRodRecycling(Thorium, Protactinium, Uranium238)

        //    Pa
        //   /  \
        // U235  U
        createFission(Protactinium, MV, 30 * SECOND, 690)
        depletedRodRecycling(Protactinium, Uranium, Uranium235)

        //    U
        //   / \
        // Ac   Np
        createFission(Uranium, HV, 40 * SECOND, 870)
        depletedRodRecycling(Uranium, Neptunium, Actinium)

        //     Np
        //    /  \
        // Pu241 Pu239
        createFission(Neptunium, EV, 15 * SECOND, 1090)
        depletedRodRecycling(Neptunium, Plutonium239, Plutonium241)

        //   Pu239
        //   /   \
        // Pu244  Am
        createFission(Plutonium239, EV, 35 * SECOND, 1370)
        depletedRodRecycling(Plutonium239, Americium, Plutonium244)

        //   Am
        //  /  \
        // Lu  Cm
        createFission(Americium, IV, 48 * SECOND, 1770, true)
        depletedRodRecycling(Americium, Curium, Lutetium)

        //   Cm
        //  /  \
        // Hf   Bk
        createFission(Curium, IV, 55 * SECOND, 2250, true)
        depletedRodRecycling(Curium, Berkelium, Hafnium)

        //   Bk
        //  /  \
        // Re   Cf
        createFission(Berkelium, LuV, 1 * MINUTE + 12 * SECOND, 3000, true)
        depletedRodRecycling(Berkelium, Californium, Rhenium)

        //   Cf
        //  /  \
        // Es  Sc
        createFission(Californium, LuV, 1 * MINUTE + 28 * SECOND, 3880, true)
        depletedRodRecycling(Californium, Einsteinium, Scandium)

        //   Es
        //  /  \
        // Po  Fm
        createFission(Einsteinium, LuV, 2 * MINUTE, 4840, true)
        depletedRodRecycling(Einsteinium, Fermium, Polonium)

        //   Fm
        //  /  \
        // At  Md
        createFission(Fermium, ZPM, 1 * MINUTE + 56 * SECOND, 5660, true)
        depletedRodRecycling(Fermium, Mendelevium, Astatine)

        //   Md
        //  /  \
        // Lr  No
        createFission(Mendelevium, ZPM, 2 * MINUTE + 25 * SECOND, 6450, true)
        depletedRodRecycling(Mendelevium, Nobelium, Lawrencium)

        //    Nq
        //   /  \
        // U235 Nq+
        createFission(Naquadah, EV, 1 * MINUTE + 30 * SECOND, 980)
        depletedRodRecycling(Naquadah, NaquadahEnriched, Uranium235)

        //  Nq+
        //  /  \
        // Cs  *Nq*
        createFission(NaquadahEnriched, IV, 2 * MINUTE, 1660)
        depletedRodRecycling(NaquadahEnriched, Naquadria, Caesium)

        //  *Nq*
        //  /  \
        // Ke   Ra
        createFission(Naquadria, LuV, 2 * MINUTE + 30 * SECOND, 2450)
        depletedRodRecycling(Naquadria, Trinium, Radium)

        //    MOX
        //   /   \
        // Pu239 U235
        createFission(MOX, IV, 45 * SECOND, 1050)
        depletedRodRecycling(MOX, Uranium235, Plutonium239)
    }

    /**
     * @param fissionElement  The fission fuel rod material.
     * @param baseVoltageTier The base energy outputs tier of all fissioning (for common fuel rod).
     * @param baseDuration    The base duration of all fissioning (for common fuel rod).
     * @param coolantAmount   The base coolant amount of all fissioning (for common fuel rod).
     * @param isHeavyElement  If the [fissionElement] is heavy element, then the hot coolant will be supercritical liquid.
     */
    @Suppress("LocalVariableName")
    private fun createFission(fissionElement: Material, baseVoltageTier: Int, baseDuration: Int, coolantAmount: Int, isHeavyElement: Boolean = false)
    {

        /**
         * @param amount The coolant amount which is mutable.
         */
        fun generateCoolants(amount: Int) : Map<FluidStack, FluidStack>
        {
            val divisor = mapOf(1 to 4.75, 2 to 9.5, 3 to 10.5, 4 to 11.5)
            return buildMap {
                val hotCoolant = if (isHeavyElement) ::SupercriticalSteam else ::SuperheatedSteam
                put(Steam.getFluid(amount), hotCoolant.get().getFluid(amount))

                listOf(::SodiumPotassiumEutatic, ::LeadBismuthEutatic, ::LithiumBerylliumFluorides, ::LithiumSodiumPotassiumFluorides)
                    .forEachIndexed { index, fluid ->
                        val _divisor = divisor[index + 1]!!
                        val _amount = (amount / _divisor).coerceAtLeast(1.0)
                        val _hotCoolant = if (isHeavyElement)
                        {
                            when (fluid)
                            {
                                ::SodiumPotassiumEutatic -> :: SupercriticalSodiumPotassiumEutatic
                                ::LeadBismuthEutatic -> ::SupercriticalLeadBismuthEutatic
                                ::LithiumBerylliumFluorides -> ::SupercriticalLithiumBerylliumFluorides
                                else -> ::SupercriticalLithiumSodiumPotassiumFluorides
                            }
                        }
                        else
                        {
                            when (fluid)
                            {
                                ::SodiumPotassiumEutatic -> ::SuperheatedSodiumPotassiumEutatic
                                ::LeadBismuthEutatic -> ::SuperheatedLeadBismuthEutatic
                                ::LithiumBerylliumFluorides -> ::SuperheatedLithiumBerylliumFluorides
                                else -> ::SuperheatedLithiumSodiumPotassiumFluorides
                            }
                        }

                        put(fluid.get().getFluid(_amount), _hotCoolant.get().getFluid(_amount))
                    }
            }
        }

        // Common Fuel Rod
        generateCoolants(coolantAmount).forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, fissionElement)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, fissionElement)
                .fluidOutputs(hotCoolant)
                .EUt(VA[baseVoltageTier])
                .duration(baseDuration)
                .buildAndRegister()
        }

        // Enriched Fuel Rod
        generateCoolants(coolantAmount * 2).forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, fissionElement)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, fissionElement)
                .fluidOutputs(hotCoolant)
                .EUt(VA[baseVoltageTier] * 5)
                .duration(baseDuration)
                .buildAndRegister()
        }

        // High Density Fuel Rod
        generateCoolants(coolantAmount * 4).forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, fissionElement)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, fissionElement)
                .fluidOutputs(hotCoolant)
                .EUt(VA[baseVoltageTier] * 8)
                .duration(baseDuration * 1.5)
                .buildAndRegister()
        }

    }

    private fun depletedRodRecycling(fissionElement: Material, nextFissionElement: Material, byproductElement: Material)
    {
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, fissionElement)
            .output(dust, Steel, 2)
            .output(dust, fissionElement, 2)
            .output(dust, byproductElement)
            .output(dust, nextFissionElement)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, fissionElement)
            .output(dust, Steel, 4)
            .output(dust, fissionElement, 4)
            .output(dust, byproductElement, 2)
            .output(dust, nextFissionElement, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, fissionElement)
            .output(dust, Steel, 8)
            .output(dust, fissionElement, 8)
            .output(dust, byproductElement, 4)
            .output(dust, nextFissionElement, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()
    }

    // @formatter:on

}