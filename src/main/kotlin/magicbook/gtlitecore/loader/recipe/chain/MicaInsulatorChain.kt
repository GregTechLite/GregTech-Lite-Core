package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Asbestos
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Lepidolite
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.NetherQuartz
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.RawRubber
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtech.common.items.MetaItems.STICKY_RESIN
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Latex
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resin
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_INSULATOR_FOIL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_INSULATOR_PLATE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_PLATE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_PULP

@Suppress("MISSING_DEPENDENCY_CLASS")
class MicaInsulatorChain
{

    companion object
    {

        fun init()
        {

            // Mica pulp.

            for (micaStack in arrayOf(
                OreDictUnifier.get(dust, Mica, 3),
                OreDictUnifier.get(dust, Biotite, 3),
                OreDictUnifier.get(dust, Lepidolite, 3),
                OreDictUnifier.get(dust, Muscovite, 3)))
            {
                for (gelStack in arrayOf(
                    OreDictUnifier.get(dust, Latex),
                    OreDictUnifier.get(dust, Resin),
                    OreDictUnifier.get(dust, RawRubber, 2),
                    STICKY_RESIN.stackForm))
                {
                    MIXER_RECIPES.recipeBuilder()
                        .circuitMeta(2)
                        .inputs(micaStack)
                        .inputs(gelStack)
                        .output(MICA_PULP, 4)
                        .EUt(V[ULV])
                        .duration(10 * SECOND)
                        .buildAndRegister()
                }
            }

            // Mica pulp -> mica plate.
            for (oreFiberStack in arrayOf(
                OreDictUnifier.get(dust, Asbestos),
                OreDictUnifier.get(dust, Lizardite)))
            {
                FORMING_PRESS_RECIPES.recipeBuilder()
                    .notConsumable(SHAPE_MOLD_PLATE)
                    .input(MICA_PULP, 4)
                    .inputs(oreFiberStack)
                    .output(MICA_PLATE, 4)
                    .EUt(28) // LV
                    .duration(5 * SECOND)
                    .buildAndRegister()
            }

            // Mica plate -> Mica insulator plate.
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(MICA_PLATE, 2)
                .input(dust, Quartzite, 1)
                .output(MICA_INSULATOR_PLATE, 2)
                .EUt(15) // LV
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(MICA_PLATE, 4)
                .input(dust, SiliconDioxide)
                .output(MICA_INSULATOR_PLATE, 4)
                .EUt(15)
                .duration(20 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(MICA_PLATE, 4)
                .input(dust, NetherQuartz)
                .output(MICA_INSULATOR_PLATE, 4)
                .EUt(15)
                .duration(20 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(MICA_PLATE, 4)
                .input(dust, CertusQuartz)
                .output(MICA_INSULATOR_PLATE, 4)
                .EUt(15)
                .duration(20 * SECOND)
                .buildAndRegister()

            // Mica insulator plate -> Mica insulator foil.
            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MICA_INSULATOR_PLATE)
                .output(MICA_INSULATOR_FOIL, 4)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // TODO Advanced recipes of mica insulators.

        }

    }

}