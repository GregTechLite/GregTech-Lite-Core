package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.M
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.PolyphenyleneSulfide
import gregtech.api.unification.material.Materials.PolyvinylChloride
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.StyreneButadieneRubber
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.WireProperties
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LAMINATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicFabric
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Omnium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible
import gregtech.loaders.recipe.handlers.WireRecipeHandler as GTWireRecipeHandler

object WireRecipeHandler
{

    // @formatter:off

    private val INSULATION_AMOUNT = mapOf(cableGtSingle to 1,
                                          cableGtDouble to 1,
                                          cableGtQuadruple to 2,
                                          cableGtOctal to 3,
                                          cableGtHex to 5)

    fun init()
    {
        wireGtSingle.addProcessingHandler(PropertyKey.WIRE, ::generateCableCovering)
        wireGtDouble.addProcessingHandler(PropertyKey.WIRE, ::generateCableCovering)
        wireGtQuadruple.addProcessingHandler(PropertyKey.WIRE, ::generateCableCovering)
        wireGtOctal.addProcessingHandler(PropertyKey.WIRE, ::generateCableCovering)
        wireGtHex.addProcessingHandler(PropertyKey.WIRE, ::generateCableCovering)
    }

    /**
     * @see gregtech.loaders.recipe.handlers.WireRecipeHandler.generateCableCovering
     */
    private fun generateCableCovering(wirePrefix: OrePrefix, material: Material, properties: WireProperties)
    {
        // Let common generation method be accessible and prepare to call for below recipe generations.
        val generateManualRecipe = GTWireRecipeHandler::class
            .declaredFunctions.find { it.name == "generateManualRecipe" }
            ?: throw IllegalArgumentException("Method 'generateManualRecipe' is not found in target class")

        generateManualRecipe.isAccessible = true

        // All superconductors does not have cable forms, so skip them.
        if (properties.isSuperconductor)
            return

        val cableAmount = ((wirePrefix.getMaterialAmount(material) * 2) / M).toInt()
        val cablePrefix = OrePrefix.getPrefix("cable" + wirePrefix.name().substring(4))
        val voltageTier = GTUtility.getTierByVoltage(properties.voltage.toLong()).toInt()
        val insulationAmount = INSULATION_AMOUNT[cablePrefix]!!

        // Generate hand-crafting recipes for ULV and LV cables.
        if (voltageTier <= LV)
            generateManualRecipe.call(wirePrefix, material, cablePrefix, cableAmount)

        // Rubber recipe (for ULV-EV cables)
        if (voltageTier <= EV)
        {
            val builder = LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .fluidInputs(Rubber.getFluid(L * insulationAmount))
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)

            // Apply PVC foil for EV cables.
            if (voltageTier == EV)
                builder.input(foil, PolyvinylChloride, insulationAmount)

            builder.buildAndRegister()
        }

        // Synthetic Rubber recipe (for EV-UV cables).
        if (voltageTier <= UV)
        {
            // Silicone Rubber recipes.
            var builder = LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)

            // Apply PVC foil for EV or above cables.
            if (voltageTier >= EV)
                builder.input(foil, PolyvinylChloride, insulationAmount)

            // Apply PPS foil for LuV or above cables.
            if (voltageTier >= LuV)
                builder.input(foil, PolyphenyleneSulfide, insulationAmount)

            builder.fluidInputs(SiliconeRubber.getFluid(L * insulationAmount / 2))
                .buildAndRegister()

            // Styrene Butadiene Rubber recipes.
            builder = LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)

            // Apply PVC foil for EV or above cables.
            if (voltageTier >= EV)
                builder.input(foil, PolyvinylChloride, insulationAmount)

            // Apply PPS foil for LuV or above cables.
            if (voltageTier >= LuV)
                builder.input(foil, PolyphenyleneSulfide, insulationAmount)

            builder.fluidInputs(StyreneButadieneRubber.getFluid(L * insulationAmount / 4))
                .buildAndRegister()
        }

        // Advanced Synthetic Rubber recipe (for UHV-OpV cables)
        if (voltageTier <= OpV)
        {
            // PTMEG Rubber recipes.
            var builder = LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)

            // Apply PVC foil for EV or above cables.
            if (voltageTier >= EV)
                builder.input(foil, PolyvinylChloride, insulationAmount)

            // Apply PPS foil for LuV or above cables.
            if (voltageTier >= LuV)
                builder.input(foil, PolyphenyleneSulfide, insulationAmount)

            // Apply PEEK foil for UV or above cables.
            if (voltageTier >= UV)
                builder.input(foil, Polyetheretherketone, insulationAmount)

            // Apply Zylon foil for UEV or above cables.
            if (voltageTier >= UEV)
                builder.input(foil, Zylon, insulationAmount)

            builder.fluidInputs(PolytetramethyleneGlycolRubber.getFluid(L * insulationAmount / 2))
                .buildAndRegister()

            // PPF Rubber recipes.
            builder = LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)

            // Apply PVC foil for EV or above cables.
            if (voltageTier >= EV)
                builder.input(foil, PolyvinylChloride, insulationAmount)

            // Apply PPS foil for LuV or above cables.
            if (voltageTier >= LuV)
                builder.input(foil, PolyphenyleneSulfide, insulationAmount)

            // Apply PEEK foil for UV or above cables.
            if (voltageTier >= UV)
                builder.input(foil, Polyetheretherketone, insulationAmount)

            // Apply Zylon foil for UEV or above cables.
            if (voltageTier >= UEV)
                builder.input(foil, Zylon, insulationAmount)

            builder.fluidInputs(PolyphosphonitrileFluoroRubber.getFluid(L * insulationAmount / 4))
                .buildAndRegister()
        }

        // Final recipes for the endgame (for MAX cables).
        if (voltageTier == MAX)
        {
            LAMINATOR_RECIPES.recipeBuilder()
                .input(wirePrefix, material)
                .input(foil, PolyvinylChloride, insulationAmount)
                .input(foil, PolyphenyleneSulfide, insulationAmount)
                .input(foil, Polyetheretherketone, insulationAmount)
                .input(foil, Zylon, insulationAmount)
                .input(POLYMER_INSULATOR_FOIL, insulationAmount)
                .fluidInputs(CosmicFabric.getFluid(L * insulationAmount / 2))
                .fluidInputs(Omnium.getFluid(L * insulationAmount / 2))
                .output(cablePrefix, material)
                .EUt(VA[ULV])
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

    // @formatter:on

}