package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeCategories
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FUEL_ROD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BOTTLE

internal object BlackholeFormerRecipes
{
    // @formatter:off

    fun init()
    {
        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, Iron, 2)
            output(FUEL_ROD_EMPTY)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, WroughtIron, 2)
            output(FUEL_ROD_EMPTY)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, Steel, 2)
            output(FUEL_ROD_EMPTY)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, VanadiumSteel, 2)
            output(FUEL_ROD_EMPTY, 4)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, BlueSteel, 2)
            output(FUEL_ROD_EMPTY, 8)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, HSSG, 2)
            output(FUEL_ROD_EMPTY, 16)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, HSSE, 2)
            output(FUEL_ROD_EMPTY, 32)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }

        BLACKHOLE_FORMING_RECIPES.addRecipe {
            notConsumable(SHAPE_FIELD_BOTTLE)
            input(ingot, HSSS, 2)
            output(FUEL_ROD_EMPTY, 64)
            EUt(VA[MV])
            duration(5 * SECOND)
            category(GTLiteRecipeCategories.BLACKHOLE_STAMPING)
        }
    }

    // @formatter:on
}