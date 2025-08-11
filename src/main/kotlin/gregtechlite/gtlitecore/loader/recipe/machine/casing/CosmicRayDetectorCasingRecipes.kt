package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing

internal object CosmicRayDetectorCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Reflective Surface Casing
        ModHandler.addShapedRecipe(true, "reflective_surface_casing", MultiblockCasing.REFLECTIVE_SURFACE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft * 4),
            "EEE", "RQR", "SFS",
            'E', EMITTER_UHV,
            'R', NEUTRON_REFLECTOR,
            'F', UnificationEntry(frameGt, HDCS),
            'Q', MetalCasing.TALONITE.stack,
            'S', UnificationEntry(screw, Pikyonium64B))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(frameGt, HDCS)
            .inputs(MetalCasing.TALONITE.stack)
            .input(EMITTER_UHV, 3)
            .input(NEUTRON_REFLECTOR, 2)
            .input(screw, Pikyonium64B, 2)
            .outputs(MultiblockCasing.REFLECTIVE_SURFACE_CASING.getStack(ConfigHolder.recipes.casingsPerCraft * 4))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}