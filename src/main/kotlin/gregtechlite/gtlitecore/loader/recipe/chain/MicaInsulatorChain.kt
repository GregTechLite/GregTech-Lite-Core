package gregtechlite.gtlitecore.loader.recipe.chain

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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Latex
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lizardite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phlogopite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_PULP

/**
 * Optional material list in the Mica Insulator Processing:
 *
 * | Mica Material                 | Gel Material | Ore Fiber Material      |
 * |-------------------------------|--------------|-------------------------|
 * | Mica KAl2(AlSi3O10)F2         | Latex        | Asbestos Mg3Si2H4O9     |
 * | Biotite KMg3Al2(AlSi3O10)F2   | Resin        | Lizardite Mg3Si2O5(OH)4 |
 * | Lepidolite KLi3Al4F2O10       | Raw Rubber   |                         |
 * | Muscovite KAl2(AlSi3O10)(OH)2 | Sticky Resin |                         |
 * | Phlogopite KMg3(AlSi3O10)F2   |              |                         |
 */
internal object MicaInsulatorChain
{

    // @formatter:off

    fun init()
    {

        // Mica Pulp
        for (mica in arrayOf(
            OreDictUnifier.get(dust, Mica, 3),
            OreDictUnifier.get(dust, Biotite, 3),
            OreDictUnifier.get(dust, Lepidolite, 3),
            OreDictUnifier.get(dust, Muscovite, 3),
            OreDictUnifier.get(dust, Phlogopite, 3)))
        {
            for (gel in arrayOf(
                OreDictUnifier.get(dust, Latex),
                OreDictUnifier.get(dust, Resin),
                OreDictUnifier.get(dust, RawRubber, 2),
                STICKY_RESIN.stackForm))
            {
                MIXER_RECIPES.addRecipe {
                    circuitMeta(2)
                    inputs(mica)
                    inputs(gel)
                    output(MICA_PULP, 4)
                    EUt(V[ULV])
                    duration(10 * SECOND)
                    buildAndRegister()
                }
            }
        }

        // Mica Pulp -> Mica Plate
        for (oreFiber in arrayOf(
            OreDictUnifier.get(dust, Asbestos),
            OreDictUnifier.get(dust, Lizardite)))
        {
            FORMING_PRESS_RECIPES.addRecipe {
                notConsumable(SHAPE_MOLD_PLATE)
                input(MICA_PULP, 4)
                inputs(oreFiber)
                output(MICA_PLATE, 4)
                EUt(28) // LV
                duration(5 * SECOND)
            }
        }

        // Mica Plate -> Mica Insulator Plate
        ALLOY_SMELTER_RECIPES.addRecipe {
            input(MICA_PLATE, 2)
            input(dust, Quartzite)
            output(MICA_INSULATOR_PLATE, 2)
            EUt(15) // LV
            duration(10 * SECOND)
        }

        ALLOY_SMELTER_RECIPES.addRecipe {
            input(MICA_PLATE, 4)
            input(dust, SiliconDioxide)
            output(MICA_INSULATOR_PLATE, 4)
            EUt(15) // LV
            duration(20 * SECOND)
        }

        ALLOY_SMELTER_RECIPES.addRecipe {
            input(MICA_PLATE, 4)
            input(dust, NetherQuartz)
            output(MICA_INSULATOR_PLATE, 4)
            EUt(15) // LV
            duration(20 * SECOND)
        }

        ALLOY_SMELTER_RECIPES.addRecipe {
            input(MICA_PLATE, 4)
            input(dust, CertusQuartz)
            output(MICA_INSULATOR_PLATE, 4)
            EUt(15) // LV
            duration(20 * SECOND)
        }

        // Mica Insulator Plate -> Mica Insulator Foil
        BENDER_RECIPES.addRecipe {
            circuitMeta(1)
            input(MICA_INSULATOR_PLATE)
            output(MICA_INSULATOR_FOIL, 4)
            EUt(VA[LV])
            duration(5 * SECOND)
        }
    }

    // @formatter:on

}