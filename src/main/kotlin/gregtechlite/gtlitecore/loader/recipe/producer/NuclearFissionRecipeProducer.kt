package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.THERMAL_CENTRIFUGE_RECIPES
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Lawrencium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nobelium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Plutonium241
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.NUCLEAR_FUELS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
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
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodEnrichedDepleted
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensityDepleted
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodDepleted
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodEnriched
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensity
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRod
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import net.minecraftforge.fluids.FluidStack

/**
 * The fission chain is: Th -> Pa -> U -> Np -> Pu -> Am -> Cm -> Bk -> Cf -> Es
 * -> Fm -> Md. Each fuelRodDepletedX centrifuging contents consists of itself, a
 * middle products and the element material before it in fission chain. For example,
 * ```
 *    Th       Pa       Np        Pu239
 *   /  \     /  \     /  \       /  \
 * U238  Pa U235  U Pu241 Pu239 Pu241 Am
 * ```
 * but also has some special situation like Uranium fission, it products U235 and U238
 * but has higher energy production than the fission reaction of Neptunium. Each fission
 * reaction products huge energy and hot coolants and consumes fuel rod and coolant.
 *
 * Total fission chain:
 * ```
 *    Th
 *   /  \
 * U238  Pa
 *      /  \
 *    U235  U
 *         / \
 *  U238/235  Np
 *           /  \
 *        Pu241 Pu239
 *               /  \
 *            Pu244 Am
 *                 /  \
 *                Lu  Cm
 *                   /  \
 *                  Hf  Bk
 *                     /  \
 *                    Re  Cf
 *                       /  \
 *                      Sc  Es
 *                         /  \
 *                        Po  Fm
 *                           /  \
 *                          At  Md
 *                             /  \
 *                            No   Lr
 * ```
 *
 * Allowed coolants and its products:
 * - Steam (SuperheatedSteam/SupercriticalSteam),
 * - SodiumPotassiumEutatic (SuperheatedSodiumPotassiumEutatic/SupercriticalSodiumPotassiumEutatic)
 * - LeadBismuthEutatic (SuperheatedSodiumPotassiumEutatic/SupercriticalSodiumPotassiumEutatic)
 * - LithiumBerylliumFluorides (SuperheatedLithiumBerylliumFluorides/SupercriticalLithiumBerylliumFluorides)
 * - LithiumSodiumPotassiumFluorides (SuperheatedLithiumSodiumPotassiumFluorides/SupercriticalLithiumSodiumPotassiumFluorides)
 *
 * Produces superheated liquids via **High Pressure Steam Turbine**, produces supercritical liquids
 * via **Supercritical Steam Turbine**.
 */
internal object NuclearFissionRecipeProducer
{

    // @formatter:off

    fun produce()
    {
        // Thorium fission.

        // fuelRodSingleThorium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(570), SuperheatedSteam.getFluid(570))
            put(SodiumPotassiumEutatic.getFluid(120), SuperheatedSodiumPotassiumEutatic.getFluid(120))
            put(LeadBismuthEutatic.getFluid(60), SuperheatedLeadBismuthEutatic.getFluid(60))
            put(LithiumBerylliumFluorides.getFluid(55), SuperheatedLithiumBerylliumFluorides.getFluid(55))
            put(LithiumSodiumPotassiumFluorides.getFluid(50), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(50))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Thorium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Thorium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[MV])
                .duration(20 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleThorium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(570 * 2), SuperheatedSteam.getFluid(570 * 2))
            put(SodiumPotassiumEutatic.getFluid(120 * 2), SuperheatedSodiumPotassiumEutatic.getFluid(120 * 2))
            put(LeadBismuthEutatic.getFluid(60 * 2), SuperheatedLeadBismuthEutatic.getFluid(60 * 2))
            put(LithiumBerylliumFluorides.getFluid(55 * 2), SuperheatedLithiumBerylliumFluorides.getFluid(55 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(50 * 2), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(50 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Thorium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Thorium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[HV] / 2L)
                .duration(80 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleThorium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(570 * 4), SuperheatedSteam.getFluid(570 * 4))
            put(SodiumPotassiumEutatic.getFluid(120 * 4), SuperheatedSodiumPotassiumEutatic.getFluid(120 * 4))
            put(LeadBismuthEutatic.getFluid(60 * 4), SuperheatedLeadBismuthEutatic.getFluid(60 * 4))
            put(LithiumBerylliumFluorides.getFluid(55 * 4), SuperheatedLithiumBerylliumFluorides.getFluid(55 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(50 * 4), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(50 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Thorium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Thorium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[HV])
                .duration(320 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleThorium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Thorium)
            .output(dust, Steel, 2)
            .output(dust, Thorium, 2)
            .output(dust, Uranium238)
            .output(dust, Protactinium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleThorium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Thorium)
            .output(dust, Steel, 4)
            .output(dust, Thorium, 4)
            .output(dust, Uranium238, 2)
            .output(dust, Protactinium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleThorium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Thorium)
            .output(dust, Steel, 8)
            .output(dust, Thorium, 8)
            .output(dust, Uranium238, 4)
            .output(dust, Protactinium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Protactinium fission.

        // fuelRodSingleProtactinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(690), SuperheatedSteam.getFluid(690))
            put(SodiumPotassiumEutatic.getFluid(240), SuperheatedSodiumPotassiumEutatic.getFluid(240))
            put(LeadBismuthEutatic.getFluid(180), SuperheatedLeadBismuthEutatic.getFluid(180))
            put(LithiumBerylliumFluorides.getFluid(175), SuperheatedLithiumBerylliumFluorides.getFluid(175))
            put(LithiumSodiumPotassiumFluorides.getFluid(170), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(170))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Protactinium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Protactinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[MV])
                .duration(30 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleProtactinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(690 * 2), SuperheatedSteam.getFluid(690 * 2))
            put(SodiumPotassiumEutatic.getFluid(240 * 2), SuperheatedSodiumPotassiumEutatic.getFluid(240 * 2))
            put(LeadBismuthEutatic.getFluid(180 * 2), SuperheatedLeadBismuthEutatic.getFluid(180 * 2))
            put(LithiumBerylliumFluorides.getFluid(175 * 2), SuperheatedLithiumBerylliumFluorides.getFluid(175 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(170 * 2), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(170 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Protactinium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Protactinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[HV] / 2L)
                .duration(120 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleProtactinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(690 * 4), SuperheatedSteam.getFluid(690 * 4))
            put(SodiumPotassiumEutatic.getFluid(240 * 4), SuperheatedSodiumPotassiumEutatic.getFluid(240 * 4))
            put(LeadBismuthEutatic.getFluid(180 * 4), SuperheatedLeadBismuthEutatic.getFluid(180 * 4))
            put(LithiumBerylliumFluorides.getFluid(175 * 4), SuperheatedLithiumBerylliumFluorides.getFluid(175 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(170 * 4), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(170 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Protactinium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Protactinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[HV])
                .duration(480 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleProtactinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Protactinium)
            .output(dust, Steel, 2)
            .output(dust, Protactinium, 2)
            .output(dust, Uranium235)
            .output(dust, Uranium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleProtactinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Protactinium)
            .output(dust, Steel, 4)
            .output(dust, Protactinium, 4)
            .output(dust, Uranium235, 2)
            .output(dust, Uranium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleProtactinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Protactinium)
            .output(dust, Steel, 8)
            .output(dust, Protactinium, 8)
            .output(dust, Uranium235, 4)
            .output(dust, Uranium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Uranium fission.

        // fuelRodSingleUranium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(870), SuperheatedSteam.getFluid(870))
            put(SodiumPotassiumEutatic.getFluid(420), SuperheatedSodiumPotassiumEutatic.getFluid(420))
            put(LeadBismuthEutatic.getFluid(360), SuperheatedLeadBismuthEutatic.getFluid(360))
            put(LithiumBerylliumFluorides.getFluid(355), SuperheatedLithiumBerylliumFluorides.getFluid(355))
            put(LithiumSodiumPotassiumFluorides.getFluid(350), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(350))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Uranium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Uranium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[HV])
                .duration(40 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleUranium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(870 * 2), SuperheatedSteam.getFluid(870 * 2))
            put(SodiumPotassiumEutatic.getFluid(420 * 2), SuperheatedSodiumPotassiumEutatic.getFluid(420 * 2))
            put(LeadBismuthEutatic.getFluid(360 * 2), SuperheatedLeadBismuthEutatic.getFluid(360 * 2))
            put(LithiumBerylliumFluorides.getFluid(355 * 2), SuperheatedLithiumBerylliumFluorides.getFluid(355 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(350 * 2), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(350 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Uranium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Uranium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[EV])
                .duration(160 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleUranium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(870 * 4), SuperheatedSteam.getFluid(870 * 4))
            put(SodiumPotassiumEutatic.getFluid(420 * 4), SuperheatedSodiumPotassiumEutatic.getFluid(420 * 4))
            put(LeadBismuthEutatic.getFluid(360 * 4), SuperheatedLeadBismuthEutatic.getFluid(360 * 4))
            put(LithiumBerylliumFluorides.getFluid(355 * 4), SuperheatedLithiumBerylliumFluorides.getFluid(355 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(350 * 4), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(350 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Uranium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Uranium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV])
                .duration(640 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleUranium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Uranium)
            .output(dust, Steel, 2)
            .output(dust, Uranium238, 2)
            .output(dust, Uranium235)
            .output(dust, Neptunium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleUranium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Uranium)
            .output(dust, Steel, 4)
            .output(dust, Uranium238, 4)
            .output(dust, Uranium235, 2)
            .output(dust, Neptunium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleUranium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Uranium)
            .output(dust, Steel, 8)
            .output(dust, Uranium238, 8)
            .output(dust, Uranium235, 4)
            .output(dust, Neptunium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Neptunium fission.

        // fuelRodSingleNeptunium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1090), SuperheatedSteam.getFluid(1090))
            put(SodiumPotassiumEutatic.getFluid(640), SuperheatedSodiumPotassiumEutatic.getFluid(640))
            put(LeadBismuthEutatic.getFluid(580), SuperheatedLeadBismuthEutatic.getFluid(580))
            put(LithiumBerylliumFluorides.getFluid(575), SuperheatedLithiumBerylliumFluorides.getFluid(575))
            put(LithiumSodiumPotassiumFluorides.getFluid(570), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(570))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Neptunium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Neptunium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[EV])
                .duration(15 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleNeptunium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1090 * 2), SuperheatedSteam.getFluid(1090 * 2))
            put(SodiumPotassiumEutatic.getFluid(640 * 2), SuperheatedSodiumPotassiumEutatic.getFluid(640 * 2))
            put(LeadBismuthEutatic.getFluid(580 * 2), SuperheatedLeadBismuthEutatic.getFluid(580 * 2))
            put(LithiumBerylliumFluorides.getFluid(575 * 2), SuperheatedLithiumBerylliumFluorides.getFluid(575 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(570 * 2), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(570 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Neptunium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Neptunium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV] / 2L)
                .duration(60 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleNeptunium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1090 * 4), SuperheatedSteam.getFluid(1090 * 4))
            put(SodiumPotassiumEutatic.getFluid(640 * 4), SuperheatedSodiumPotassiumEutatic.getFluid(640 * 4))
            put(LeadBismuthEutatic.getFluid(580 * 4), SuperheatedLeadBismuthEutatic.getFluid(580 * 4))
            put(LithiumBerylliumFluorides.getFluid(575 * 4), SuperheatedLithiumBerylliumFluorides.getFluid(575 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(570 * 4), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(570 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Neptunium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Neptunium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV])
                .duration(240 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleNeptunium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Neptunium)
            .output(dust, Steel, 2)
            .output(dust, Neptunium, 2)
            .output(dust, Plutonium241)
            .output(dust, Plutonium239)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleNeptunium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Neptunium)
            .output(dust, Steel, 4)
            .output(dust, Neptunium, 4)
            .output(dust, Plutonium241, 2)
            .output(dust, Plutonium239, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleNeptunium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Neptunium)
            .output(dust, Steel, 8)
            .output(dust, Neptunium, 8)
            .output(dust, Plutonium241, 4)
            .output(dust, Plutonium239, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Plutonium fission.

        // fuelRodSinglePlutonium239
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1370), SuperheatedSteam.getFluid(1370))
            put(SodiumPotassiumEutatic.getFluid(920), SuperheatedSodiumPotassiumEutatic.getFluid(920))
            put(LeadBismuthEutatic.getFluid(860), SuperheatedLeadBismuthEutatic.getFluid(860))
            put(LithiumBerylliumFluorides.getFluid(855), SuperheatedLithiumBerylliumFluorides.getFluid(855))
            put(LithiumSodiumPotassiumFluorides.getFluid(850), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(850))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Plutonium239)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Plutonium239)
                .fluidOutputs(hotCoolant)
                .EUt(VA[EV])
                .duration(45 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoublePlutonium239
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1370 * 2), SuperheatedSteam.getFluid(1370 * 2))
            put(SodiumPotassiumEutatic.getFluid(920 * 2), SuperheatedSodiumPotassiumEutatic.getFluid(920 * 2))
            put(LeadBismuthEutatic.getFluid(860 * 2), SuperheatedLeadBismuthEutatic.getFluid(860 * 2))
            put(LithiumBerylliumFluorides.getFluid(855 * 2), SuperheatedLithiumBerylliumFluorides.getFluid(855 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(850 * 2), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(850 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Plutonium239)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Plutonium239)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV] / 2L)
                .duration(180 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadruplePlutonium239
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1370 * 4), SuperheatedSteam.getFluid(1370 * 4))
            put(SodiumPotassiumEutatic.getFluid(920 * 4), SuperheatedSodiumPotassiumEutatic.getFluid(920 * 4))
            put(LeadBismuthEutatic.getFluid(860 * 4), SuperheatedLeadBismuthEutatic.getFluid(860 * 4))
            put(LithiumBerylliumFluorides.getFluid(855 * 4), SuperheatedLithiumBerylliumFluorides.getFluid(855 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(850 * 4), SuperheatedLithiumSodiumPotassiumFluorides.getFluid(850 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Plutonium239)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Plutonium239)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV])
                .duration(720 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSinglePlutonium239 decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Plutonium239)
            .output(dust, Steel, 2)
            .output(dust, Plutonium239, 2)
            .output(dust, Plutonium244)
            .output(dust, Americium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoublePlutonium239 decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Plutonium239)
            .output(dust, Steel, 4)
            .output(dust, Plutonium239, 4)
            .output(dust, Plutonium244, 2)
            .output(dust, Americium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadruplePlutonium239 decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Plutonium239)
            .output(dust, Steel, 8)
            .output(dust, Plutonium239, 8)
            .output(dust, Plutonium244, 4)
            .output(dust, Americium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Americium fission.

        // fuelRodSingleAmericium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1770), SupercriticalSteam.getFluid(1770))
            put(SodiumPotassiumEutatic.getFluid(1320), SupercriticalSodiumPotassiumEutatic.getFluid(1320))
            put(LeadBismuthEutatic.getFluid(1260), SupercriticalLeadBismuthEutatic.getFluid(1260))
            put(LithiumBerylliumFluorides.getFluid(1255), SupercriticalLithiumBerylliumFluorides.getFluid(1255))
            put(LithiumSodiumPotassiumFluorides.getFluid(1250), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1250))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Americium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Americium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV])
                .duration(48 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleAmericium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1770 * 2), SupercriticalSteam.getFluid(1770 * 2))
            put(SodiumPotassiumEutatic.getFluid(1320 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(1320 * 2))
            put(LeadBismuthEutatic.getFluid(1260 * 2), SupercriticalLeadBismuthEutatic.getFluid(1260 * 2))
            put(LithiumBerylliumFluorides.getFluid(1255 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(1255 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(1250 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1250 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Americium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Americium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[IV] * 2L)
                .duration(192 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleAmericium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(1770 * 4), SupercriticalSteam.getFluid(1770 * 4))
            put(SodiumPotassiumEutatic.getFluid(1320 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(1320 * 4))
            put(LeadBismuthEutatic.getFluid(1260 * 4), SupercriticalLeadBismuthEutatic.getFluid(1260 * 4))
            put(LithiumBerylliumFluorides.getFluid(1255 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(1255 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(1250 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1250 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Americium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Americium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV])
                .duration(768 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleAmericium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Americium)
            .output(dust, Steel, 2)
            .output(dust, Americium, 2)
            .output(dust, Lutetium)
            .output(dust, Curium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleAmericium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Americium)
            .output(dust, Steel, 4)
            .output(dust, Americium, 4)
            .output(dust, Lutetium, 2)
            .output(dust, Curium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleAmericium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Americium)
            .output(dust, Steel, 8)
            .output(dust, Americium, 8)
            .output(dust, Lutetium, 4)
            .output(dust, Curium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Curium fission.

        // fuelRodSingleCurium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(2250), SupercriticalSteam.getFluid(2250))
            put(SodiumPotassiumEutatic.getFluid(1800), SupercriticalSodiumPotassiumEutatic.getFluid(1800))
            put(LeadBismuthEutatic.getFluid(1740), SupercriticalLeadBismuthEutatic.getFluid(1740))
            put(LithiumBerylliumFluorides.getFluid(1735), SupercriticalLithiumBerylliumFluorides.getFluid(1735))
            put(LithiumSodiumPotassiumFluorides.getFluid(1730), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1730))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Curium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Curium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] / 2L)
                .duration(46 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleCurium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(2250 * 2), SupercriticalSteam.getFluid(2250 * 2))
            put(SodiumPotassiumEutatic.getFluid(1800 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(1800 * 2))
            put(LeadBismuthEutatic.getFluid(1740 * 2), SupercriticalLeadBismuthEutatic.getFluid(1740 * 2))
            put(LithiumBerylliumFluorides.getFluid(1735 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(1735 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(1730 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1730 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Curium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Curium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV])
                .duration(184 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleCurium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(2250 * 4), SupercriticalSteam.getFluid(2250 * 4))
            put(SodiumPotassiumEutatic.getFluid(1800 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(1800 * 4))
            put(LeadBismuthEutatic.getFluid(1740 * 4), SupercriticalLeadBismuthEutatic.getFluid(1740 * 4))
            put(LithiumBerylliumFluorides.getFluid(1735 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(1735 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(1730 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(1730 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Curium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Curium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 2L)
                .duration(736 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleCurium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Curium)
            .output(dust, Steel, 2)
            .output(dust, Curium, 2)
            .output(dust, Hafnium)
            .output(dust, Berkelium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleCurium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Curium)
            .output(dust, Steel, 4)
            .output(dust, Curium, 4)
            .output(dust, Hafnium, 2)
            .output(dust, Berkelium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleCurium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Curium)
            .output(dust, Steel, 8)
            .output(dust, Curium, 8)
            .output(dust, Hafnium, 4)
            .output(dust, Berkelium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Berkelium fission.

        // fuelRodSingleBerkelium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3000), SupercriticalSteam.getFluid(3000))
            put(SodiumPotassiumEutatic.getFluid(2550), SupercriticalSodiumPotassiumEutatic.getFluid(2550))
            put(LeadBismuthEutatic.getFluid(2490), SupercriticalLeadBismuthEutatic.getFluid(2490))
            put(LithiumBerylliumFluorides.getFluid(2485), SupercriticalLithiumBerylliumFluorides.getFluid(2485))
            put(LithiumSodiumPotassiumFluorides.getFluid(2480), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(2480))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Berkelium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Berkelium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] / 2L)
                .duration(55 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleBerkelium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3000 * 2), SupercriticalSteam.getFluid(3000 * 2))
            put(SodiumPotassiumEutatic.getFluid(2550 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(2550 * 2))
            put(LeadBismuthEutatic.getFluid(2490 * 2), SupercriticalLeadBismuthEutatic.getFluid(2490 * 2))
            put(LithiumBerylliumFluorides.getFluid(2485 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(2485 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(2480 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(2480 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Berkelium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Berkelium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV])
                .duration(220 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleBerkelium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3000 * 4), SupercriticalSteam.getFluid(3000 * 4))
            put(SodiumPotassiumEutatic.getFluid(2550 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(2550 * 4))
            put(LeadBismuthEutatic.getFluid(2490 * 4), SupercriticalLeadBismuthEutatic.getFluid(2490 * 4))
            put(LithiumBerylliumFluorides.getFluid(2485 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(2485 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(2480 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(2480 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Berkelium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Berkelium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 3L)
                .duration(880 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleBerkelium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Berkelium)
            .output(dust, Steel, 2)
            .output(dust, Berkelium, 2)
            .output(dust, Rhenium)
            .output(dust, Californium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleBerkelium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Berkelium)
            .output(dust, Steel, 4)
            .output(dust, Berkelium, 4)
            .output(dust, Rhenium, 2)
            .output(dust, Californium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleBerkelium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Berkelium)
            .output(dust, Steel, 8)
            .output(dust, Berkelium, 8)
            .output(dust, Rhenium, 4)
            .output(dust, Californium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Californium fission.

        // fuelRodSingleCalifornium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3880), SupercriticalSteam.getFluid(3880))
            put(SodiumPotassiumEutatic.getFluid(3430), SupercriticalSodiumPotassiumEutatic.getFluid(3430))
            put(LeadBismuthEutatic.getFluid(3370), SupercriticalLeadBismuthEutatic.getFluid(3370))
            put(LithiumBerylliumFluorides.getFluid(3365), SupercriticalLithiumBerylliumFluorides.getFluid(3365))
            put(LithiumSodiumPotassiumFluorides.getFluid(3360), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(3360))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Californium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Californium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV])
                .duration(64 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleCalifornium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3880 * 2), SupercriticalSteam.getFluid(3880 * 2))
            put(SodiumPotassiumEutatic.getFluid(3430 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(3430 * 2))
            put(LeadBismuthEutatic.getFluid(3370 * 2), SupercriticalLeadBismuthEutatic.getFluid(3370 * 2))
            put(LithiumBerylliumFluorides.getFluid(3365 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(3365 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(3360 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(3360 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Californium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Californium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 3L)
                .duration(256 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleCalifornium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(3880 * 4), SupercriticalSteam.getFluid(3880 * 4))
            put(SodiumPotassiumEutatic.getFluid(3430 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(3430 * 4))
            put(LeadBismuthEutatic.getFluid(3370 * 4), SupercriticalLeadBismuthEutatic.getFluid(3370 * 4))
            put(LithiumBerylliumFluorides.getFluid(3365 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(3365 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(3360 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(3360 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Californium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Californium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM])
                .duration(1024 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleCalifornium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Californium)
            .output(dust, Steel, 2)
            .output(dust, Californium, 2)
            .output(dust, Scandium)
            .output(dust, Einsteinium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleCalifornium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Californium)
            .output(dust, Steel, 4)
            .output(dust, Californium, 4)
            .output(dust, Scandium, 2)
            .output(dust, Einsteinium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleCalifornium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Californium)
            .output(dust, Steel, 8)
            .output(dust, Californium, 8)
            .output(dust, Scandium, 4)
            .output(dust, Einsteinium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Einsteinium fission.

        // fuelRodSingleEinsteinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(4840), SupercriticalSteam.getFluid(4840))
            put(SodiumPotassiumEutatic.getFluid(4390), SupercriticalSodiumPotassiumEutatic.getFluid(4390))
            put(LeadBismuthEutatic.getFluid(4330), SupercriticalLeadBismuthEutatic.getFluid(4330))
            put(LithiumBerylliumFluorides.getFluid(4325), SupercriticalLithiumBerylliumFluorides.getFluid(4325))
            put(LithiumSodiumPotassiumFluorides.getFluid(4320), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(4320))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Einsteinium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Einsteinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 2L)
                .duration(72 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleEinsteinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(4840 * 2), SupercriticalSteam.getFluid(4840 * 2))
            put(SodiumPotassiumEutatic.getFluid(4390 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(4390 * 2))
            put(LeadBismuthEutatic.getFluid(4330 * 2), SupercriticalLeadBismuthEutatic.getFluid(4330 * 2))
            put(LithiumBerylliumFluorides.getFluid(4325 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(4325 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(4320 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(4320 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Einsteinium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Einsteinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 3L)
                .duration(288 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleEinsteinium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(4840 * 4), SupercriticalSteam.getFluid(4840 * 4))
            put(SodiumPotassiumEutatic.getFluid(4390 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(4390 * 4))
            put(LeadBismuthEutatic.getFluid(4330 * 4), SupercriticalLeadBismuthEutatic.getFluid(4330 * 4))
            put(LithiumBerylliumFluorides.getFluid(4325 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(4325 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(4320 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(4320 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Einsteinium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Einsteinium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM])
                .duration(1152 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleEinsteinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Einsteinium)
            .output(dust, Steel, 2)
            .output(dust, Einsteinium, 2)
            .output(dust, Polonium)
            .output(dust, Fermium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleEinsteinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Einsteinium)
            .output(dust, Steel, 4)
            .output(dust, Einsteinium, 4)
            .output(dust, Polonium, 2)
            .output(dust, Fermium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleEinsteinium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Einsteinium)
            .output(dust, Steel, 8)
            .output(dust, Einsteinium, 8)
            .output(dust, Polonium, 4)
            .output(dust, Fermium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Fermium fission.

        // fuelRodSingleFermium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(5860), SupercriticalSteam.getFluid(5860))
            put(SodiumPotassiumEutatic.getFluid(5410), SupercriticalSodiumPotassiumEutatic.getFluid(5410))
            put(LeadBismuthEutatic.getFluid(5350), SupercriticalLeadBismuthEutatic.getFluid(5350))
            put(LithiumBerylliumFluorides.getFluid(5345), SupercriticalLithiumBerylliumFluorides.getFluid(5345))
            put(LithiumSodiumPotassiumFluorides.getFluid(5340), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(5340))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Fermium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Fermium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[LuV] * 3L)
                .duration(80 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleFermium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(5860 * 2), SupercriticalSteam.getFluid(5860 * 2))
            put(SodiumPotassiumEutatic.getFluid(5410 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(5410 * 2))
            put(LeadBismuthEutatic.getFluid(5350 * 2), SupercriticalLeadBismuthEutatic.getFluid(5350 * 2))
            put(LithiumBerylliumFluorides.getFluid(5345 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(5345 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(5340 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(5340 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Fermium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Fermium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM])
                .duration(320 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleFermium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(5860 * 4), SupercriticalSteam.getFluid(5860 * 4))
            put(SodiumPotassiumEutatic.getFluid(5410 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(5410 * 4))
            put(LeadBismuthEutatic.getFluid(5350 * 4), SupercriticalLeadBismuthEutatic.getFluid(5350 * 4))
            put(LithiumBerylliumFluorides.getFluid(5345 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(5345 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(5340 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(5340 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Fermium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Fermium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM] * 2L)
                .duration(1280 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleFermium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Fermium)
            .output(dust, Steel, 2)
            .output(dust, Fermium, 2)
            .output(dust, Astatine)
            .output(dust, Mendelevium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleFermium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Fermium)
            .output(dust, Steel, 4)
            .output(dust, Fermium, 4)
            .output(dust, Astatine, 2)
            .output(dust, Mendelevium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleFermium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Fermium)
            .output(dust, Steel, 8)
            .output(dust, Fermium, 8)
            .output(dust, Astatine, 4)
            .output(dust, Mendelevium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // ---------------------------------------------------------------------------------------------------------
        // Mendelevium fission.

        // fuelRodSingleMendelevium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(7100), SupercriticalSteam.getFluid(7100))
            put(SodiumPotassiumEutatic.getFluid(6650), SupercriticalSodiumPotassiumEutatic.getFluid(6650))
            put(LeadBismuthEutatic.getFluid(6590), SupercriticalLeadBismuthEutatic.getFluid(6590))
            put(LithiumBerylliumFluorides.getFluid(6585), SupercriticalLithiumBerylliumFluorides.getFluid(6585))
            put(LithiumSodiumPotassiumFluorides.getFluid(6580), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(6580))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRod, Mendelevium)
                .fluidInputs(coolant)
                .output(fuelRodDepleted, Mendelevium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM])
                .duration(84 * SECOND)
                .buildAndRegister()
        }

        // fuelRodDoubleMendelevium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(7100 * 2), SupercriticalSteam.getFluid(7100 * 2))
            put(SodiumPotassiumEutatic.getFluid(6650 * 2), SupercriticalSodiumPotassiumEutatic.getFluid(6650 * 2))
            put(LeadBismuthEutatic.getFluid(6590 * 2), SupercriticalLeadBismuthEutatic.getFluid(6590 * 2))
            put(LithiumBerylliumFluorides.getFluid(6585 * 2), SupercriticalLithiumBerylliumFluorides.getFluid(6585 * 2))
            put(LithiumSodiumPotassiumFluorides.getFluid(6580 * 2), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(6580 * 2))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodEnriched, Mendelevium)
                .fluidInputs(coolant)
                .output(fuelRodEnrichedDepleted, Mendelevium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[ZPM] * 3L)
                .duration(336 * SECOND)
                .buildAndRegister()
        }

        // fuelRodQuadrupleMendelevium
        mutableMapOf<FluidStack, FluidStack>().apply {
            put(Steam.getFluid(7100 * 4), SupercriticalSteam.getFluid(7100 * 4))
            put(SodiumPotassiumEutatic.getFluid(6650 * 4), SupercriticalSodiumPotassiumEutatic.getFluid(6650 * 4))
            put(LeadBismuthEutatic.getFluid(6590 * 4), SupercriticalLeadBismuthEutatic.getFluid(6590 * 4))
            put(LithiumBerylliumFluorides.getFluid(6585 * 4), SupercriticalLithiumBerylliumFluorides.getFluid(6585 * 4))
            put(LithiumSodiumPotassiumFluorides.getFluid(6580 * 4), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(6580 * 4))
        }.forEach { (coolant, hotCoolant) ->
            NUCLEAR_FUELS.recipeBuilder()
                .input(fuelRodHighDensity, Mendelevium)
                .fluidInputs(coolant)
                .output(fuelRodHighDensityDepleted, Mendelevium)
                .fluidOutputs(hotCoolant)
                .EUt(VA[UV])
                .duration(1344 * SECOND)
                .buildAndRegister()
        }

        // fuelRodSingleMendelevium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodDepleted, Mendelevium)
            .output(dust, Steel, 2)
            .output(dust, Mendelevium, 2)
            .output(dust, Nobelium)
            .output(dust, Lawrencium)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodDoubleMendelevium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodEnrichedDepleted, Mendelevium)
            .output(dust, Steel, 4)
            .output(dust, Mendelevium, 4)
            .output(dust, Nobelium, 2)
            .output(dust, Lawrencium, 2)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        // fuelRodQuadrupleMendelevium decomposition.
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder()
            .input(fuelRodHighDensityDepleted, Mendelevium)
            .output(dust, Steel, 8)
            .output(dust, Mendelevium, 8)
            .output(dust, Nobelium, 4)
            .output(dust, Lawrencium, 4)
            .EUt(VA[LV])
            .duration(1 * MINUTE)
            .buildAndRegister()

    }

    // @formatter:on

}