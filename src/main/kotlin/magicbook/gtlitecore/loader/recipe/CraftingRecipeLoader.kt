package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.SHAPE_EMPTY
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtech.common.items.MetaItems.SHAPE_MOLD_ANVIL
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtech.common.items.MetaItems.SHAPE_MOLD_BOTTLE
import gregtech.common.items.MetaItems.SHAPE_MOLD_CREDIT
import gregtech.common.items.MetaItems.SHAPE_MOLD_CYLINDER
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR_SMALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_NAME
import gregtech.common.items.MetaItems.SHAPE_MOLD_NUGGET
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_BUTCHERY_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_CROWBAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_EMPTY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_FILE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_HARD_HAMMER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_KNIFE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_MORTAR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_ROLLING_PIN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SAW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SCREWDRIVER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_SOFT_MALLET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WIRE_CUTTER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CASTING_MOLD_WRENCH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_DRILL_HEAD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_ROUND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_EXTRUDER_TURBINE_BLADE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_BOLT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_DRILL_HEAD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_RING
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD_LONG
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROUND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_SCREW
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_TURBINE_BLADE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_FLAT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_OCTAGONAL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_STRIPES
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CraftingRecipeLoader
{

    companion object
    {

        fun init()
        {
            // Modified all recipes of original shape molds because we need to add
            // more shape molds (even more and more in future).

            // Shape Mold (Plate)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_plate")
            ModHandler.addShapedRecipe(true, "shape_mold.plate", SHAPE_MOLD_PLATE.stackForm,
                " hf", " M ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Gear)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_gear")
            ModHandler.addShapedRecipe(true,"shape_mold.gear", SHAPE_MOLD_GEAR.stackForm,
                " h ", " M ", "  f",
                'M', SHAPE_EMPTY)

            // Shape Mold (Credit)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_credit")
            ModHandler.addShapedRecipe(true, "shape_mold.credit", SHAPE_MOLD_CREDIT.stackForm,
                "   ", " M ", "hf ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Bottle)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_bottle")
            ModHandler.addShapedRecipe(true, "shape_mold.bottle", SHAPE_MOLD_BOTTLE.stackForm,
                " h ", " M ", " f ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Ingot)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_ingot")
            ModHandler.addShapedRecipe(true, "shape_mold.ingot", SHAPE_MOLD_INGOT.stackForm,
                " h ", " M ", "f  ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Ball)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_ball")
            ModHandler.addShapedRecipe(true, "shape_mold.ball", SHAPE_MOLD_BALL.stackForm,
                " h ", "fM ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Block)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_block")
            ModHandler.addShapedRecipe(true, "shape_mold.block", SHAPE_MOLD_BLOCK.stackForm,
                "fh ", " M ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Nugget)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_nugget")
            ModHandler.addShapedRecipe(true, "shape_mold.nugget", SHAPE_MOLD_NUGGET.stackForm,
                "  h", " Mf", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Cylinder)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_cylinder")
            ModHandler.addShapedRecipe(true, "shape_mold.cylinder", SHAPE_MOLD_CYLINDER.stackForm,
                "  h", "fM ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Anvil)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_anvil")
            ModHandler.addShapedRecipe(true, "shape_mold.anvil", SHAPE_MOLD_ANVIL.stackForm,
                "f h", " M ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Name)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_name")
            ModHandler.addShapedRecipe(true, "shape_mold.name", SHAPE_MOLD_NAME.stackForm,
                " fh", " M ", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Small Gear)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_gear_small")
            ModHandler.addShapedRecipe(true, "shape_mold.gear_small", SHAPE_MOLD_GEAR_SMALL.stackForm,
                "   ", " Mh", " f ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Rotor)
            ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_rotor")
            ModHandler.addShapedRecipe(true, "shape_mold.rotor", SHAPE_MOLD_ROTOR.stackForm,
                "   ", " M ", "f h",
                'M', SHAPE_EMPTY)

            // Add recipes to additional shape molds, should ensure there are not
            // any conflicts with original extruders.

            // Shape Mold (Rod)
            ModHandler.addShapedRecipe(true, "shape_mold.rod", SHAPE_MOLD_ROD.stackForm,
                "   ", " Mh", "f  ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Bolt)
            ModHandler.addShapedRecipe(true, "shape_mold.bolt", SHAPE_MOLD_BOLT.stackForm,
                "   ", "fMh", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Round)
            ModHandler.addShapedRecipe(true, "shape_mold.round", SHAPE_MOLD_ROUND.stackForm,
                "f  ", " Mh", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Screw)
            ModHandler.addShapedRecipe(true, "shape_mold.screw", SHAPE_MOLD_SCREW.stackForm,
                " f ", " Mh", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Ring)
            ModHandler.addShapedRecipe(true, "shape_mold.ring", SHAPE_MOLD_RING.stackForm,
                "  f", " Mh", "   ",
                'M', SHAPE_EMPTY)

            // Shape Mold (Long Rod)
            ModHandler.addShapedRecipe(true, "shape_mold.rod_long", SHAPE_MOLD_ROD_LONG.stackForm,
                "   ", " M ", " fh",
                'M', SHAPE_EMPTY)

            // Shape Mold (Turbine Blade)
            ModHandler.addShapedRecipe(true, "shape_mold.turbine_blade", SHAPE_MOLD_TURBINE_BLADE.stackForm,
                "   ", "fM ", "  h",
                'M', SHAPE_EMPTY)

            // Shape Mold (Drill Head)
            ModHandler.addShapedRecipe(true, "shape_mold.drill_head", SHAPE_MOLD_DRILL_HEAD.stackForm,
                "   ", " M ", " hf",
                'M', SHAPE_EMPTY)

            // Add recipes to additional shape extruders, should ensure there are not
            // any conflicts with original extruders.

            // Shape Extruder (Round)
            ModHandler.addShapedRecipe(true, "shape_extruder.round", SHAPE_EXTRUDER_ROUND.stackForm,
                "   ", " M ", "x  ",
                'M', SHAPE_EXTRUDER_ROD)

            // Shape Extruder (Turbine Blade)
            ModHandler.addShapedRecipe(true, "shape_extruder.turbine_blade", SHAPE_EXTRUDER_TURBINE_BLADE.stackForm,
                "   ", " M ", "  x",
                'M', SHAPE_EXTRUDER_PLATE)

            // Shape Extruder (Drill Head)
            ModHandler.addShapedRecipe(true, "shape_extruder.drill_head", SHAPE_EXTRUDER_DRILL_HEAD.stackForm,
                "   ", " M ", " x ",
                'M', SHAPE_EXTRUDER_INGOT)

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

            // Add recipes to casting molds, we need add clay plate recipe at first.
            ModHandler.addShapedRecipe(true, "plate_clay", OreDictUnifier.get(plate, Clay),
                "pC ",
                'C', ItemStack(Items.CLAY_BALL))

            // Casting Mold (Empty)
            ModHandler.addShapedRecipe(true, "casting_mold.empty", CASTING_MOLD_EMPTY.stackForm,
                "hf ", "PP ", "PP ",
                'P', UnificationEntry(plate, VanadiumSteel))

            // Casting Mold (Saw)
            ModHandler.addShapedRecipe(true, "casting_mold.saw", CASTING_MOLD_SAW.stackForm,
                "rs ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Hard Hammer)
            ModHandler.addShapedRecipe(true, "casting_mold.hard_hammer", CASTING_MOLD_HARD_HAMMER.stackForm,
                "rh ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Soft Mallet)
            ModHandler.addShapedRecipe(true, "casting_mold.soft_mallet", CASTING_MOLD_SOFT_MALLET.stackForm,
                " r ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Wrench)
            ModHandler.addShapedRecipe(true, "casting_mold.wrench", CASTING_MOLD_WRENCH.stackForm,
                "rw ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (File)
            ModHandler.addShapedRecipe(true, "casting_mold.file", CASTING_MOLD_FILE.stackForm,
                "rf ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Crowbar)
            ModHandler.addShapedRecipe(true, "casting_mold.crowbar", CASTING_MOLD_CROWBAR.stackForm,
                "rc ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Screwdriver)
            ModHandler.addShapedRecipe(true, "casting_mold.screwdriver", CASTING_MOLD_SCREWDRIVER.stackForm,
                "rd ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Mortar)
            ModHandler.addShapedRecipe(true, "casting_mold.mortar", CASTING_MOLD_MORTAR.stackForm,
                "rm ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Wire Cutter)
            ModHandler.addShapedRecipe(true, "casting_mold.wire_cutter", CASTING_MOLD_WIRE_CUTTER.stackForm,
                "rx ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Knife)
            ModHandler.addShapedRecipe(true, "casting_mold.knife", CASTING_MOLD_KNIFE.stackForm,
                "rk ", " P ", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Butchery Knife)
            ModHandler.addShapedRecipe(true, "casting_mold.butchery_knife", CASTING_MOLD_BUTCHERY_KNIFE.stackForm,
                "rB ", " Pk", " M ",
                'B', "toolButcheryKnife", // Safety usage before GTLiteToolItems#addToolSymbols().
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

            // Casting Mold (Rolling Pin)
            ModHandler.addShapedRecipe(true, "casting_mold.rolling_pin", CASTING_MOLD_ROLLING_PIN.stackForm,
                "rp ", " Pk", " M ",
                'P', UnificationEntry(plate, Clay),
                'M', CASTING_MOLD_EMPTY)

        }

    }

}