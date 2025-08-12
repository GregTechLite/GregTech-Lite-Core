package gregtechlite.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.CREDIT_NEUTRONIUM
import gregtech.common.items.MetaItems.ITEM_FILTER
import gregtech.common.items.MetaItems.ORE_DICTIONARY_FILTER
import gregtech.common.items.MetaItems.SHAPE_EMPTY
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_ROD
import gregtech.common.items.MetaItems.SHAPE_MOLD_ANVIL
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtech.common.items.MetaItems.SHAPE_MOLD_BOLT
import gregtech.common.items.MetaItems.SHAPE_MOLD_BOTTLE
import gregtech.common.items.MetaItems.SHAPE_MOLD_CREDIT
import gregtech.common.items.MetaItems.SHAPE_MOLD_CYLINDER
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR_SMALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_NAME
import gregtech.common.items.MetaItems.SHAPE_MOLD_NUGGET
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtech.common.items.MetaItems.SHAPE_MOLD_RING
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROD
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROD_LONG
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROTOR
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROUND
import gregtech.common.items.MetaItems.SMART_FILTER
import gregtech.common.items.ToolItems
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aegirine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicHeterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Jade
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kovar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Prasiolite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AIR_VENT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_BUTCHERY_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_CROWBAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_EMPTY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_FILE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_HARD_HAMMER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_KNIFE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_MORTAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SAW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SCREWDRIVER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_SOFT_MALLET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WIRE_CUTTER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CASTING_MOLD_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_BORON_NITRIDE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COMPONENT_GRINDER_HALKONITE_STEEL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_ADAMANTIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_COSMIC_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CREDIT_VIBRANIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DRAIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAND_DUST
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_EXTRUDER_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_SCREW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_MOLD_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_OCTAGONAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_STRIPES
import gregtechlite.gtlitecore.common.item.GTLiteToolItems
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NONUPLE_FLUID_EXPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.NONUPLE_FLUID_IMPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUADRUPLE_FLUID_EXPORT_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.QUADRUPLE_FLUID_IMPORT_HATCH
import gregtechlite.gtlitecore.loader.recipe.handler.ToolRecipeHandler
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object CraftingRecipeLoader
{

    // @formatter:off


    fun init()
    {
        ToolRecipeHandler.registerCustomToolRecipes()

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
        ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_rod")
        ModHandler.addShapedRecipe(true, "shape_mold.rod", SHAPE_MOLD_ROD.stackForm,
            "   ", " Mh", "f  ",
            'M', SHAPE_EMPTY)

        // Shape Mold (Bolt)
        ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_bolt")
        ModHandler.addShapedRecipe(true, "shape_mold.bolt", SHAPE_MOLD_BOLT.stackForm,
            "   ", "fMh", "   ",
            'M', SHAPE_EMPTY)

        // Shape Mold (Round)
        ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_round")
        ModHandler.addShapedRecipe(true, "shape_mold.round", SHAPE_MOLD_ROUND.stackForm,
            "f  ", " Mh", "   ",
            'M', SHAPE_EMPTY)

        // Shape Mold (Screw)
        ModHandler.addShapedRecipe(true, "shape_mold.screw", SHAPE_MOLD_SCREW.stackForm,
            " f ", " Mh", "   ",
            'M', SHAPE_EMPTY)

        // Shape Mold (Ring)
        ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_ring")
        ModHandler.addShapedRecipe(true, "shape_mold.ring", SHAPE_MOLD_RING.stackForm,
            "  f", " Mh", "   ",
            'M', SHAPE_EMPTY)

        // Shape Mold (Long Rod)
        ModHandler.removeRecipeByName("${GTValues.MODID}:shape_mold_rod_long")
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
        ModHandler.addShapelessRecipe("plate_clay", OreDictUnifier.get(plate, Clay),
            GTLiteToolItems.ROLLING_PIN, ItemStack(Items.CLAY_BALL))

        // Clay stick is required to some clay items.
        ModHandler.addShapelessRecipe("stick_clay", OreDictUnifier.get(stick, Clay, 2),
            ToolItems.KNIFE, ItemStack(Items.CLAY_BALL))

        // Add recipes to graphite plate.
        ModHandler.addShapelessRecipe("plate_graphite", OreDictUnifier.get(plate, Graphite),
            GTLiteToolItems.ROLLING_PIN, OreDictUnifier.get(dust, Graphite))

        // Add recipes to graphene plate.
        ModHandler.addShapelessRecipe("plate_graphene", OreDictUnifier.get(plate, Graphene),
            GTLiteToolItems.ROLLING_PIN, OreDictUnifier.get(dust, Graphene))

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

        // Additional Quadruple/Nonuple Input Hatch converts.
        for (i in 0..3)
        {
            ModHandler.addShapedRecipe("quadruple_fluid_hatch_output_to_input_${QUADRUPLE_FLUID_IMPORT_HATCH[i].tier}",
                QUADRUPLE_FLUID_IMPORT_HATCH[i].stackForm, "d", "B",
                'B', QUADRUPLE_FLUID_EXPORT_HATCH[i].stackForm)
            ModHandler.addShapedRecipe("quadruple_fluid_hatch_input_to_output_${QUADRUPLE_FLUID_EXPORT_HATCH[i].tier}",
                QUADRUPLE_FLUID_EXPORT_HATCH[i].stackForm, "d", "B",
                'B', QUADRUPLE_FLUID_IMPORT_HATCH[i].stackForm)

            ModHandler.addShapedRecipe("nonuple_fluid_hatch_output_to_input_${NONUPLE_FLUID_IMPORT_HATCH[i].tier}",
                NONUPLE_FLUID_IMPORT_HATCH[i].stackForm, "d", "B",
                'B', NONUPLE_FLUID_EXPORT_HATCH[i].stackForm)
            ModHandler.addShapedRecipe("nonuple_fluid_hatch_input_to_output_${NONUPLE_FLUID_EXPORT_HATCH[i].tier}",
                NONUPLE_FLUID_EXPORT_HATCH[i].stackForm, "d", "B",
                'B', NONUPLE_FLUID_IMPORT_HATCH[i].stackForm)
        }

        // Make kovar dust be craftable in early game.
        ModHandler.addShapelessRecipe("dust_kovar_1", OreDictUnifier.get(dust, Kovar, 4),
            OreDictUnifier.get(dust, Iron), OreDictUnifier.get(dust, Iron),
            OreDictUnifier.get(dust, Nickel), OreDictUnifier.get(dust, Cobalt))

        ModHandler.addShapelessRecipe("dust_kovar_2", OreDictUnifier.get(dust, Kovar, 4),
            OreDictUnifier.get(dust, Invar), OreDictUnifier.get(dust, Invar),
            OreDictUnifier.get(dust, Invar), OreDictUnifier.get(dust, Cobalt))

        // Sand dust and block recipes.
        ModHandler.addShapelessRecipe("sand_dust", SAND_DUST.getStackForm(4),
            ItemStack(Blocks.SAND))

        ModHandler.addShapelessRecipe("sand_dust_to_block", ItemStack(Blocks.SAND),
            SAND_DUST.stackForm, SAND_DUST.stackForm,
            SAND_DUST.stackForm, SAND_DUST.stackForm)

        // Ore Dictionary Filter
        ModHandler.addShapedRecipe(true, "ore_dictionary_filter_forsterite", ORE_DICTIONARY_FILTER.stackForm,
            "FFF", "FPF", "FFF",
            'P', UnificationEntry(plate, Forsterite),
            'F', UnificationEntry(foil, Zinc))

        ModHandler.addShapedRecipe(true, "ore_dictionary_filter_aegirine", ORE_DICTIONARY_FILTER.stackForm,
            "FFF", "FPF", "FFF",
            'P', UnificationEntry(plate, Aegirine),
            'F', UnificationEntry(foil, Zinc))

        ModHandler.addShapedRecipe(true, "ore_dictionary_filter_jade", ORE_DICTIONARY_FILTER.stackForm,
            "FFF", "FPF", "FFF",
            'P', UnificationEntry(plate, Jade),
            'F', UnificationEntry(foil, Zinc))

        ModHandler.addShapedRecipe(true, "ore_dictionary_filter_prasiolite", ORE_DICTIONARY_FILTER.stackForm,
            "FFF", "FPF", "FFF",
            'P', UnificationEntry(plate, Prasiolite),
            'F', UnificationEntry(foil, Zinc))

        // Smart Item Filter
        ModHandler.removeRecipeByName("${GTValues.MODID}:smart_item_filter_olivine")
        ModHandler.removeRecipeByName("${GTValues.MODID}:smart_item_filter_emerald")
        ModHandler.addShapelessRecipe("smart_item_filter", SMART_FILTER.stackForm,
            ITEM_FILTER, OreDictUnifier.get(gearSmall, Zinc))

        // Boron Nitride Grinder
        ModHandler.addShapedRecipe("boron_nitride_grinder", COMPONENT_GRINDER_BORON_NITRIDE.stackForm,
            "PDP", "DGD", "PDP",
            'P', UnificationEntry(plate, CubicBoronNitride),
            'D', UnificationEntry(plateDouble, Bedrockium),
            'G', UnificationEntry(gem, CubicHeterodiamond))

        // Halkonite Steel Grinder
        ModHandler.addShapedRecipe("halkonite_steel_grinder", COMPONENT_GRINDER_HALKONITE_STEEL.stackForm,
            "PDP", "DGD", "PDP",
            'P', UnificationEntry(plate, HalkoniteSteel),
            'D', UnificationEntry(plateDouble, CosmicNeutronium),
            'G', UnificationEntry(gem, CubicSiliconNitride))



        // Adamantium Credit
        ModHandler.addShapelessRecipe("credit_adamantium_alt", CREDIT_ADAMANTIUM.stackForm,
            CREDIT_NEUTRONIUM.stackForm, CREDIT_NEUTRONIUM.stackForm, CREDIT_NEUTRONIUM.stackForm,
            CREDIT_NEUTRONIUM.stackForm, CREDIT_NEUTRONIUM.stackForm, CREDIT_NEUTRONIUM.stackForm,
            CREDIT_NEUTRONIUM.stackForm, CREDIT_NEUTRONIUM.stackForm)

        ModHandler.addShapelessRecipe("credit_neutronium", CREDIT_NEUTRONIUM.getStackForm(8),
            CREDIT_ADAMANTIUM.stackForm,)

        // Vibranium Credit
        ModHandler.addShapelessRecipe("credit_vibranium_alt", CREDIT_VIBRANIUM.stackForm,
            CREDIT_ADAMANTIUM.stackForm, CREDIT_ADAMANTIUM.stackForm, CREDIT_ADAMANTIUM.stackForm,
            CREDIT_ADAMANTIUM.stackForm, CREDIT_ADAMANTIUM.stackForm, CREDIT_ADAMANTIUM.stackForm,
            CREDIT_ADAMANTIUM.stackForm, CREDIT_ADAMANTIUM.stackForm)

        ModHandler.addShapelessRecipe("credit_vibranium", CREDIT_ADAMANTIUM.getStackForm(8),
            CREDIT_VIBRANIUM.stackForm)

        // Cosmic Neutronium Credit
        ModHandler.addShapelessRecipe("credit_cosmic_neutronium_alt", CREDIT_COSMIC_NEUTRONIUM.stackForm,
            CREDIT_VIBRANIUM.stackForm, CREDIT_VIBRANIUM.stackForm, CREDIT_VIBRANIUM.stackForm,
            CREDIT_VIBRANIUM.stackForm, CREDIT_VIBRANIUM.stackForm, CREDIT_VIBRANIUM.stackForm,
            CREDIT_VIBRANIUM.stackForm, CREDIT_VIBRANIUM.stackForm)

        ModHandler.addShapelessRecipe("credit_cosmic_neutronium", CREDIT_VIBRANIUM.getStackForm(8),
            CREDIT_COSMIC_NEUTRONIUM.stackForm)

        // Infinity Credit
        ModHandler.addShapelessRecipe("credit_infinity_alt", CREDIT_INFINITY.stackForm,
            CREDIT_COSMIC_NEUTRONIUM.stackForm, CREDIT_COSMIC_NEUTRONIUM.stackForm, CREDIT_COSMIC_NEUTRONIUM.stackForm,
            CREDIT_COSMIC_NEUTRONIUM.stackForm, CREDIT_COSMIC_NEUTRONIUM.stackForm, CREDIT_COSMIC_NEUTRONIUM.stackForm,
            CREDIT_COSMIC_NEUTRONIUM.stackForm, CREDIT_COSMIC_NEUTRONIUM.stackForm)

        ModHandler.addShapelessRecipe("credit_infinity", CREDIT_COSMIC_NEUTRONIUM.getStackForm(8),
            CREDIT_INFINITY.stackForm)

        // Air Vent
        ModHandler.addShapedRecipe(true, "air_vent", AIR_VENT.stackForm,
            "SPS", "SRS", "SwS",
            'R', UnificationEntry(rotor, Steel),
            'S', UnificationEntry(stick, Steel),
            'P', UnificationEntry(pipeSmallFluid, Bronze))

        // Drain
        ModHandler.addShapedRecipe(true, "drain", DRAIN.stackForm,
            "SSS", "BPB", "SSS",
            'B', ItemStack(Blocks.IRON_BARS),
            'P', UnificationEntry(pipeSmallFluid, Bronze),
            'S', UnificationEntry(stick, Iron))

    }

    // @formatter:on

}