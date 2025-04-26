package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.BLACKLIGHT
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WoodsGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class WoodsGlassChain
{

    companion object
    {

        fun init()
        {
            // Ba + O -> BaO
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Barium)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, BariumOxide, 2)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Ba(OH)2 -> BaO + H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, BariumHydroxide, 5)
                .output(dust, BariumOxide, 2)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(1 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Na2CO3 + SiO2 + BaO + NiO -> (Na2CO3)6(SiO2)3(BaO)2(NiO)2
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, SodaAsh, 6)
                .input(dust, SiliconDioxide, 3)
                .input(dust, BariumOxide, 2)
                .input(dust, Garnierite, 2)
                .output(dust, WoodsGlass, 13)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // Blacklight
            ModHandler.removeRecipeByName("${GTValues.MODID}:blacklight")
            ModHandler.addShapedRecipe(true, "blacklight", BLACKLIGHT.stackForm,
                "SPS", "QEQ", "XPW",
                'P', UnificationEntry(plate, TungstenCarbide),
                'Q', UnificationEntry(plate, WoodsGlass),
                'E', UnificationEntry(spring, Europium),
                'S', UnificationEntry(screw, TungstenCarbide),
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', UnificationEntry(cableGtSingle, Platinum))

        }

    }

}