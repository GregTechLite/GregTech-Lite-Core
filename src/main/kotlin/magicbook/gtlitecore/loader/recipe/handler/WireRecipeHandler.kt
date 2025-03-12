package magicbook.gtlitecore.loader.recipe.handler

import com.google.common.collect.ImmutableMap
import gregtech.api.GTValues.EV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.M
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.WireProperties
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.util.GTUtility
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

// Callback original registrate of wire processing handler and post new
// processing handler from this recipe handler container.
@Suppress("MISSING_DEPENDENCY_CLASS")
class WireRecipeHandler
{

    companion object
    {
        private val INSULATION_AMOUNT = ImmutableMap.of(
            OrePrefix.cableGtSingle, 1,
            OrePrefix.cableGtDouble, 1,
            OrePrefix.cableGtQuadruple, 2,
            OrePrefix.cableGtOctal, 3,
            OrePrefix.cableGtHex, 5)

        fun init()
        {
            OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering)
            OrePrefix.wireGtDouble.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering)
            OrePrefix.wireGtQuadruple.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering)
            OrePrefix.wireGtOctal.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering)
            OrePrefix.wireGtHex.addProcessingHandler(PropertyKey.WIRE, this::generateCableCovering)
        }

        /**
         * Transformed from [gregtech.loaders.recipe.handlers.WireRecipeHandler.generateCableCovering].
         */
        private fun generateCableCovering(wirePrefix: OrePrefix, material: Material, properties: WireProperties)
        {
            // Do Kt Reflect to make original class's generateManualRecipe() function public.
            val targetMethod = gregtech.loaders.recipe.handlers.WireRecipeHandler::class
                    .declaredFunctions.find { it.name == "generateManualRecipe" }
                        ?: throw IllegalArgumentException("Method 'generateManualRecipe()' is not found in target class [gregtech.loaders.recipe.handlers.WireRecipeHandler]")
            targetMethod.isAccessible = true

            // Superconductors have no cables, so exit early lazy.
            if (properties.isSuperconductor)
                return

            val cableAmount = ((wirePrefix.getMaterialAmount(material) * 2) / M).toInt()
            val cablePrefix = OrePrefix.getPrefix("cable" + wirePrefix.name().substring(4))
            val voltageTier = GTUtility.getTierByVoltage(properties.voltage.toLong()).toInt()
            val insulationAmount = INSULATION_AMOUNT[cablePrefix]!!

            // Generate hand-crafting recipes for ULV and LV cables.
            if (voltageTier <= LV)
                targetMethod.call(wirePrefix, material, cablePrefix, cableAmount)
            // Rubber recipe (for ULV-EV cables)
            if (voltageTier <= EV)
            {
                val builder = GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()
                    .input(wirePrefix, material)
                    .fluidInputs(Materials.Rubber.getFluid(L * insulationAmount))
                    .output(cablePrefix, material)
                    .EUt(VA[ULV].toLong())
                    .duration(5 * SECOND)
                // Apply PVC foil for EV cables.
                if (voltageTier == EV)
                    builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount)
                builder.buildAndRegister()
            }
            // Synthetic Rubber recipe (for EV-UV cables).
            if (voltageTier <= UV)
            {
                // Silicone Rubber recipes.
                var builder = GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()
                    .input(wirePrefix, material)
                    .output(cablePrefix, material)
                    .EUt(VA[ULV].toLong())
                    .duration(5 * SECOND)
                // Apply PVC foil for EV or above cables.
                if (voltageTier >= EV)
                    builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount)
                // Apply PPS foil for LuV or above cables.
                if (voltageTier >= LuV)
                    builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount)
                builder.fluidInputs(SiliconeRubber.getFluid(L * insulationAmount / 2))
                    .buildAndRegister()

                // Styrene Butadiene Rubber recipes.
                builder = GTLiteRecipeMaps.LAMINATOR_RECIPES.recipeBuilder()
                    .input(wirePrefix, material)
                    .output(cablePrefix, material)
                    .EUt(VA[ULV].toLong())
                    .duration(5 * SECOND)
                // Apply PVC foil for EV or above cables.
                if (voltageTier >= EV)
                    builder.input(OrePrefix.foil, Materials.PolyvinylChloride, insulationAmount)
                // Apply PPS foil for LuV or above cables.
                if (voltageTier >= LuV)
                    builder.input(OrePrefix.foil, Materials.PolyphenyleneSulfide, insulationAmount)
                builder.fluidInputs(Materials.StyreneButadieneRubber.getFluid(L * insulationAmount / 4))
                    .buildAndRegister()
            }
            // TODO Advanced Synthetic Rubber (for UV-OpV cables).
            if (voltageTier <= OpV)
            {

            }
        }
    }

}