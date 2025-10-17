package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.GTValues.M
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Rubber
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.WireProperties
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LOOM_RECIPES
import kotlin.math.pow

@Suppress("unused")
object WireCombinationHandler
{

    // @formatter:off

    private val WIRE_DOUBLING_ORDER = arrayOf(wireGtSingle,
                                              wireGtDouble,
                                              wireGtQuadruple,
                                              wireGtOctal,
                                              wireGtHex)

    private val cableToWireMap = mapOf(cableGtSingle to wireGtSingle,
                                       cableGtDouble to wireGtDouble,
                                       cableGtQuadruple to wireGtQuadruple,
                                       cableGtOctal to wireGtOctal,
                                       cableGtHex to wireGtHex)

    fun init()
    {
        wireGtSingle.addProcessingHandler(PropertyKey.WIRE, ::processWireCompression)

        for (wirePrefix in WIRE_DOUBLING_ORDER)
            wirePrefix.addProcessingHandler(PropertyKey.WIRE, ::generateWireCombiningRecipe)

        for (cablePrefix in cableToWireMap.keys)
            cablePrefix.addProcessingHandler(PropertyKey.WIRE, ::processCableStripping)
    }

    /**
     * @see gregtech.loaders.recipe.handlers.WireCombiningHandler.processWireCompression
     */
    private fun processWireCompression(wirePrefix: OrePrefix, material: Material, properties: WireProperties)
    {
        for (startTier in 0 until 4)
        {
            for (i in 1 until 5 - startTier)
            {
                LOOM_RECIPES.recipeBuilder()
                    .circuitMeta(2.0.pow(i).toInt())
                    .inputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[startTier], material, 1 shl i))
                    .outputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[startTier + i], material, 1))
                    .EUt(12) // LV
                    .duration(10 * TICK)
                    .buildAndRegister()
            }
        }

        for (i in 1 until 5)
        {
            LOOM_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[i], material, 1))
                .outputs(OreDictUnifier.get(WIRE_DOUBLING_ORDER[0], material, 2.0.pow(i).toInt()))
                .EUt(12) // LV
                .duration(10 * TICK)
                .buildAndRegister()
        }

    }

    /**
     * @see gregtech.loaders.recipe.handlers.WireCombiningHandler.generateWireCombiningRecipe
     */
    private fun generateWireCombiningRecipe(wirePrefix: OrePrefix, material: Material, property: WireProperties)
    {
        val wireIndex = WIRE_DOUBLING_ORDER.indexOf(wirePrefix)
        if (wireIndex < WIRE_DOUBLING_ORDER.size - 1)
        {
            ModHandler.addShapelessRecipe(String.format("%s_wire_%s_doubling", material, wirePrefix),
                OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex + 1], material),
                UnificationEntry(wirePrefix, material),
                UnificationEntry(wirePrefix, material))
        }

        if (wireIndex > 0)
        {
            ModHandler.addShapelessRecipe(String.format("%s_wire_%s_splitting", material, wirePrefix),
                OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex - 1], material, 2),
                UnificationEntry(wirePrefix, material))
        }

        if (wireIndex < 3)
        {
            ModHandler.addShapelessRecipe(String.format("%s_wire_%s_quadrupling", material, wirePrefix),
                OreDictUnifier.get(WIRE_DOUBLING_ORDER[wireIndex + 2], material),
                UnificationEntry(wirePrefix, material),
                UnificationEntry(wirePrefix, material),
                UnificationEntry(wirePrefix, material),
                UnificationEntry(wirePrefix, material))
        }
    }

    /**
     * @see gregtech.loaders.recipe.handlers.WireCombiningHandler.processCableStripping
     */
    private fun processCableStripping(prefix: OrePrefix, material: Material, property: WireProperties)
    {
        PACKER_RECIPES.recipeBuilder()
            .input(prefix, material)
            .output(cableToWireMap[prefix], material)
            .output(plate, Rubber, (prefix.secondaryMaterials[0].amount / M).toInt())
            .EUt(VA[ULV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}