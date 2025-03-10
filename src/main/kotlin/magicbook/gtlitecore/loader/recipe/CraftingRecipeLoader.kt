package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_FLAT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_OCTAGONAL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_STRIPES

@Suppress("MISSING_DEPENDENCY_CLASS")
class CraftingRecipeLoader
{

    companion object
    {

        fun init()
        {
            // Slicer Blade (Flat)
            ModHandler.addShapedRecipe(true, "slicer_blade.flat", SLICER_BLADE_FLAT.stackForm,
                "hPS", " M ", "fPs",
                'P', UnificationEntry(plate, Iron),
                'S', UnificationEntry(screw, Iron),
                'M', SHAPE_EXTRUDER_BLOCK)

            // Slicer Blade (Strips)
            ModHandler.addShapedRecipe(true, "slicer_blade.stripes", SLICER_BLADE_STRIPES.stackForm,
                "hPS", "PMP", "fPs",
                'P', UnificationEntry(plate, Iron),
                'S', UnificationEntry(screw, Iron),
                'M', SHAPE_EXTRUDER_BLOCK)

            // Slicer Blade (Octagonal)
            ModHandler.addShapedRecipe(true, "slicer_blade.octagonal", SLICER_BLADE_OCTAGONAL.stackForm,
                "PhP", "fMS", "PsP",
                'P', UnificationEntry(plate, Iron),
                'S', UnificationEntry(screw, Iron),
                'M', SHAPE_EXTRUDER_BLOCK)

        }

    }

}