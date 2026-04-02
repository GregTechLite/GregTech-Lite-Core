package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.M
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Material
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.sheetedFrame
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.wallGt

internal object HalkoniteSteelChain
{

    // @formatter:off

    private const val DURATION_PER_M = 8 * SECOND

    fun init()
    {
        // region Resolve Conflicts

        // Resolve recipe conflicts between bedrockium 3:2 ingot to plate with bedrockium ingot to halkonite steel ingot.
        FORGE_HAMMER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Bedrockium, 3))
        MATTER_RESHAPING_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Bedrockium, 3))
        FORGE_HAMMER_RECIPES.addRecipe {
            circuitMeta(1)
            input(ingot, Bedrockium, 3)
            output(plate, Bedrockium, 2)
            EUt(VA[LV])
            duration(4 * SECOND + 18 * TICK)
        }

        // Resolve recipe conflicts between bedrockium stick to long stick with bedrockium stick to halkonite steel stick.
        FORGE_HAMMER_RECIPES.removeRecipe(OreDictUnifier.get(stick, Bedrockium, 2))
        MATTER_RESHAPING_RECIPES.removeRecipe(OreDictUnifier.get(stick, Bedrockium, 2))
        FORGE_HAMMER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, Bedrockium, 2)
            output(stickLong, Bedrockium)
            EUt(VA[LV])
            duration(4 * SECOND + 18 * TICK)
        }

        // endregion

        addPrefixRecipe(ingot, 2)
        addPrefixRecipe(block)
        addPrefixRecipe(plate)
        addPrefixRecipe(plateDouble)
        addPrefixRecipe(plateDense)
        addPrefixRecipe(stick, 2)
        addPrefixRecipe(stickLong)
        addPrefixRecipe(spring)
        addPrefixRecipe(springSmall)
        addPrefixRecipe(foil)
        addPrefixRecipe(bolt)
        addPrefixRecipe(screw)

        addPrefixRecipe(wireGtSingle, 1)
        addPrefixRecipe(wireGtDouble, 1)
        addPrefixRecipe(wireGtQuadruple, 1)
        addPrefixRecipe(wireGtOctal, 1)
        addPrefixRecipe(wireGtHex, 1)

        addPrefixRecipe(cableGtSingle, 2, PolytetramethyleneGlycolRubber)
        addPrefixRecipe(cableGtSingle, 2, PolyphosphonitrileFluoroRubber)
        addPrefixRecipe(cableGtDouble, 2, PolytetramethyleneGlycolRubber)
        addPrefixRecipe(cableGtDouble, 2, PolyphosphonitrileFluoroRubber)
        addPrefixRecipe(cableGtQuadruple, 2, PolytetramethyleneGlycolRubber)
        addPrefixRecipe(cableGtQuadruple, 2, PolyphosphonitrileFluoroRubber)
        addPrefixRecipe(cableGtOctal, 2, PolytetramethyleneGlycolRubber)
        addPrefixRecipe(cableGtOctal, 2, PolyphosphonitrileFluoroRubber)
        addPrefixRecipe(cableGtHex, 2, PolytetramethyleneGlycolRubber)
        addPrefixRecipe(cableGtHex, 2, PolyphosphonitrileFluoroRubber)

        addPrefixRecipe(frameGt)
        addPrefixRecipe(wallGt)
        addPrefixRecipe(sheetedFrame)
    }

    private fun addPrefixRecipe(prefix: OrePrefix, circuit: Int? = null, adhesiveFluid: Material? = null)
    {
        val builder = MATTER_RESHAPING_RECIPES.recipeBuilder()

        val materialAmount = prefix.getMaterialAmount(Bedrockium)
        val fluidAmount = materialAmountToFluid(materialAmount)
        val duration = materialAmountToDuration(materialAmount)

        if (circuit != null)
        {
            check(circuit in 0..32) { "Circuit meta $circuit is out of range" }
            builder.circuitMeta(circuit)
        }

        builder.input(prefix, Bedrockium)
            .fluidInputs(HalkoniteSteel.getFluid(fluidAmount))

        if (adhesiveFluid != null)
        {
            builder.fluidInputs(adhesiveFluid.getFluid(fluidAmount))
        }

        builder.output(prefix, HalkoniteSteel)
            .EUt(VA[UEV])
            .duration(duration)
            .buildAndRegister()
    }

    private fun materialAmountToFluid(amount: Long): Int = (amount * L / M).toInt()

    private fun materialAmountToDuration(amount: Long): Int = (amount * DURATION_PER_M / M).toInt()

    // @formatter:on

}
