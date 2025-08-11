package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.recipes.RecipeMaps.PRIMITIVE_BLAST_FURNACE_RECIPES
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite

internal object PrimitiveBlastFurnaceRecipes
{

    // @formatter:off

    fun init()
    {
        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(ingot, Iron)
            .input(gem, Lignite, 2)
            .output(ingot, Steel)
            .output(dustTiny, DarkAsh, 2)
            .duration(90 * SECOND)
            .buildAndRegister()

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(ingot, Iron)
            .input(dust, Lignite, 2)
            .output(ingot, Steel)
            .output(dustTiny, DarkAsh, 2)
            .duration(90 * SECOND)
            .buildAndRegister()

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(ingot, WroughtIron)
            .input(gem, Lignite, 2)
            .output(ingot, Steel)
            .output(dustTiny, DarkAsh, 2)
            .duration(40 * SECOND)
            .buildAndRegister()

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(ingot, WroughtIron)
            .input(dust, Lignite, 2)
            .output(ingot, Steel)
            .output(dustTiny, DarkAsh, 2)
            .duration(40 * SECOND)
            .buildAndRegister()

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(block, Iron)
            .input(block, Lignite, 2)
            .output(block, Steel)
            .output(dust, DarkAsh, 2)
            .duration(810 * SECOND)
            .buildAndRegister()

        PRIMITIVE_BLAST_FURNACE_RECIPES.recipeBuilder()
            .input(block, WroughtIron)
            .input(block, Lignite, 2)
            .output(block, Steel)
            .output(dust, DarkAsh, 2)
            .duration(360 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}
