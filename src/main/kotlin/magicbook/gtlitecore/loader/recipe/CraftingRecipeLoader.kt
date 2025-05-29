package magicbook.gtlitecore.loader.recipe

import gregtech.api.GTValues
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockMachineCasing
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
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
import gregtech.common.items.MetaItems.SMART_FILTER
import gregtech.common.items.ToolItems
import gregtech.common.metatileentities.MetaTileEntities.HI_AMP_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.TRANSFORMER
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmorphousBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlackDwarfMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Creon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicBoronNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicHeterodiamond
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jade
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Periodicium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Prasiolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.fuelRodQuadruple
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockBoilerCasing01
import magicbook.gtlitecore.common.block.blocks.BlockCrucible
import magicbook.gtlitecore.common.block.blocks.BlockNuclearReactorCore01
import magicbook.gtlitecore.common.block.blocks.BlockNuclearReactorCore02
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing01
import magicbook.gtlitecore.common.block.blocks.BlockTurbineCasing02
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
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COMPONENT_GRINDER_BORON_NITRIDE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COMPONENT_GRINDER_HALKONITE_STEEL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_ADAMANTIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_COSMIC_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CREDIT_VIBRANIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAND_DUST
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
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.VOLTAGE_COIL_UXV
import magicbook.gtlitecore.common.item.GTLiteToolItems
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHROME_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DIAMOND_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.GOLD_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRIDIUM_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LEAD_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SILVER_CRATE
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TUNGSTEN_DRUM
import magicbook.gtlitecore.loader.recipe.handler.ToolRecipeHandler
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class CraftingRecipeLoader
{

    companion object
    {

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

            // Add recipes to additional drums and plastic cans.

            // Iron Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_iron",
                IRON_DRUM.stackForm, IRON_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "iron_drum", IRON_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Iron),
                'S', UnificationEntry(stickLong, Iron))

            // Copper Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_copper",
                COPPER_DRUM.stackForm, COPPER_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "copper_drum", COPPER_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Copper),
                'S', UnificationEntry(stickLong, Copper))

            // Lead Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_lead",
                LEAD_DRUM.stackForm, LEAD_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "lead_drum", LEAD_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Lead),
                'S', UnificationEntry(stickLong, Lead))

            // Chrome Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_chrome",
                CHROME_DRUM.stackForm, CHROME_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "chrome_drum", CHROME_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Chrome),
                'S', UnificationEntry(stickLong, Chrome))

            // Tungsten Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_tungsten",
                TUNGSTEN_DRUM.stackForm, TUNGSTEN_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "tungsten_drum", TUNGSTEN_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Tungsten),
                'S', UnificationEntry(stickLong, Tungsten))

            // Iridium Drum
            ModHandler.addShapelessNBTClearingRecipe("drum_nbt_iridium",
                IRIDIUM_DRUM.stackForm, IRIDIUM_DRUM.stackForm)
            ModHandler.addShapedRecipe(true, "iridium_drum", IRIDIUM_DRUM.stackForm,
                " h ", "PSP", "PSP",
                'P', UnificationEntry(plate, Iridium),
                'S', UnificationEntry(stickLong, Iridium))

            // Iron Crate
            ModHandler.addShapedRecipe(true, "iron_crate", IRON_CRATE.stackForm,
                "SPS", "PhP", "SPS",
                'P', UnificationEntry(plate, Iron),
                'S', UnificationEntry(stickLong, Iron))

            // Copper Crate
            ModHandler.addShapedRecipe(true, "copper_crate", COPPER_CRATE.stackForm,
                "SPS", "PhP", "SPS",
                'P', UnificationEntry(plate, Copper),
                'S', UnificationEntry(stickLong, Copper))

            // Silver Crate
            ModHandler.addShapedRecipe(true, "silver_crate", SILVER_CRATE.stackForm,
                "SPS", "PhP", "SPS",
                'P', UnificationEntry(plate, Silver),
                'S', UnificationEntry(stickLong, Silver))

            // Gold Crate
            ModHandler.addShapedRecipe(true, "gold_crate", GOLD_CRATE.stackForm,
                "SPS", "PhP", "SPS",
                'P', UnificationEntry(plate, Gold),
                'S', UnificationEntry(stickLong, Gold))

            // Diamond Crate
            ModHandler.addShapedRecipe(true, "diamond_crate", DIAMOND_CRATE.stackForm,
                "SPS", "PhP", "SPS",
                'P', UnificationEntry(plate, Diamond),
                'S', UnificationEntry(stickLong, Diamond))

            // Additional Quadruple/Nonuple Input Hatch converts.
            for (i in 0..3)
            {
                ModHandler.addShapedRecipe("quadruple_fluid_hatch_output_to_input_${QUADRUPLE_FLUID_IMPORT_HATCH[i]!!.tier}",
                    QUADRUPLE_FLUID_IMPORT_HATCH[i]!!.stackForm, "d", "B",
                    'B', QUADRUPLE_FLUID_EXPORT_HATCH[i]!!.stackForm)
                ModHandler.addShapedRecipe("quadruple_fluid_hatch_input_to_output_${QUADRUPLE_FLUID_EXPORT_HATCH[i]!!.tier}",
                    QUADRUPLE_FLUID_EXPORT_HATCH[i]!!.stackForm, "d", "B",
                    'B', QUADRUPLE_FLUID_IMPORT_HATCH[i]!!.stackForm)

                ModHandler.addShapedRecipe("nonuple_fluid_hatch_output_to_input_${NONUPLE_FLUID_IMPORT_HATCH[i]!!.tier}",
                    NONUPLE_FLUID_IMPORT_HATCH[i]!!.stackForm, "d", "B",
                    'B', NONUPLE_FLUID_EXPORT_HATCH[i]!!.stackForm)
                ModHandler.addShapedRecipe("nonuple_fluid_hatch_input_to_output_${NONUPLE_FLUID_EXPORT_HATCH[i]!!.tier}",
                    NONUPLE_FLUID_EXPORT_HATCH[i]!!.stackForm, "d", "B",
                    'B', NONUPLE_FLUID_IMPORT_HATCH[i]!!.stackForm)
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

            // Polybenzimidazole Pipe Casing
            ModHandler.addShapedRecipe(true, "polybenzimidazole_pipe_casing", GTLiteMetaBlocks.BOILER_CASING_01.getItemVariant(BlockBoilerCasing01.BoilerCasingType.POLYBENZIMIDAZOLE, ConfigHolder.recipes.casingsPerCraft),
                "PQP", "QFQ", "PQP",
                'P', UnificationEntry(plate, Polybenzimidazole),
                'Q', UnificationEntry(pipeNormalFluid, Polybenzimidazole),
                'F', UnificationEntry(frameGt, Polybenzimidazole))

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
                ITEM_FILTER, OreDictUnifier.get(circuit, MarkerMaterials.Tier.LV))

            // Bronze Crucible
            ModHandler.addShapedRecipe(true, "bronze_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.BRONZE_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Bronze))

            // Invar Crucible
            ModHandler.addShapedRecipe(true, "invar_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.INVAR_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Invar))

            // Quartzite Crucible
            ModHandler.addShapedRecipe(true, "quartzite_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.QUARTZ_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Quartzite))

            // Chrome Crucible
            ModHandler.addShapedRecipe(true, "chrome_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.CHROME_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Chrome))

            // Vanadium Crucible
            ModHandler.addShapedRecipe(true, "vanadium_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.VANADIUM_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Vanadium))

            // Niobium Titanium Crucible
            ModHandler.addShapedRecipe(true, "niobium_titanium_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.NIOBIUM_TITANIUM_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, NiobiumTitanium))

            // Iridium Crucible
            ModHandler.addShapedRecipe(true, "iridium_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.IRIDIUM_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Iridium))

            // Molybdenum Crucible
            ModHandler.addShapedRecipe(true, "molybdenum_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.MOLYBDENUM_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Molybdenum))

            // Tungsten Crucible
            ModHandler.addShapedRecipe(true, "tungsten_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.TUNGSTEN_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Tungsten))

            // Osmium Crucible
            ModHandler.addShapedRecipe(true, "osmium_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.OSMIUM_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Osmium))

            // Graphite Crucible
            ModHandler.addShapedRecipe(true, "graphite_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.GRAPHITE_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, Graphene))

            // Boron Nitride Crucible
            ModHandler.addShapedRecipe(true, "boron_nitride_crucible", GTLiteMetaBlocks.CRUCIBLE.getItemVariant(BlockCrucible.CrucibleType.BORON_NITRIDE_CRUCIBLE),
                "P P", "PhP", "PPP",
                'P', UnificationEntry(plate, AmorphousBoronNitride))

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

            // UEV Machine Casing
            ModHandler.addShapedRecipe(true, "casing_uev", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV),
                "PPP", "PwP", "PPP",
                'P', UnificationEntry(plate, Vibranium))

            // UIV Machine Casing
            ModHandler.addShapedRecipe(true, "casing_uiv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV),
                "PPP", "PwP", "PPP",
                'P', UnificationEntry(plate, Shirabon))

            // UXV Machine Casing
            ModHandler.addShapedRecipe(true, "casing_uxv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV),
                "PPP", "PwP", "PPP",
                'P', UnificationEntry(plate, Creon))

            // OpV Machine Casing
            ModHandler.addShapedRecipe(true, "casing_opv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV),
                "PPP", "PwP", "PPP",
                'P', UnificationEntry(plate, BlackDwarfMatter))

            // UHV Transformer
            ModHandler.addShapedRecipe(true, "transformer_uhv", TRANSFORMER[UHV].stackForm,
                "PUU", "WH ", "PUU",
                'H', HULL[UHV].stackForm,
                'P', NANO_PIC_CHIP,
                'W', UnificationEntry(cableGtSingle, Seaborgium),
                'U', UnificationEntry(cableGtSingle, Europium))

            // UEV Transformer
            ModHandler.addShapedRecipe(true, "transformer_uev", TRANSFORMER[UEV].stackForm,
                "PUU", "WH ", "PUU",
                'H', HULL[UEV].stackForm,
                'P', NANO_PIC_CHIP,
                'W', UnificationEntry(cableGtSingle, SuperheavyAlloyA),
                'U', UnificationEntry(cableGtSingle, Seaborgium))

            // UIV Transformer
            ModHandler.addShapedRecipe(true, "transformer_uiv", TRANSFORMER[UIV].stackForm,
                "PUU", "WH ", "PUU",
                'H', HULL[UIV].stackForm,
                'P', PICO_PIC_CHIP,
                'W', UnificationEntry(cableGtSingle, SuperheavyAlloyB),
                'U', UnificationEntry(cableGtSingle, SuperheavyAlloyA))

            // UXV Transformer
            ModHandler.addShapedRecipe(true, "transformer_uxv", TRANSFORMER[UXV].stackForm,
                "PUU", "WH ", "PUU",
                'H', HULL[UXV].stackForm,
                'P', PICO_PIC_CHIP,
                'W', UnificationEntry(cableGtSingle, Periodicium),
                'U', UnificationEntry(cableGtSingle, SuperheavyAlloyB))

            // UHV Hi-Amp Transformer
            ModHandler.addShapedRecipe(true, "hi_amp_transformer_uhv", HI_AMP_TRANSFORMER[UHV].stackForm,
                "CUU", "WV ", "CUU",
                'C', VOLTAGE_COIL_UHV,
                'V', TRANSFORMER[UHV].stackForm,
                'W', UnificationEntry(cableGtQuadruple, Seaborgium),
                'U', UnificationEntry(cableGtQuadruple, Europium))

            // UEV Hi-Amp Transformer
            ModHandler.addShapedRecipe(true, "hi_amp_transformer_uev", HI_AMP_TRANSFORMER[UEV].stackForm,
                "CUU", "WV ", "CUU",
                'C', VOLTAGE_COIL_UEV,
                'V', TRANSFORMER[UEV].stackForm,
                'W', UnificationEntry(cableGtQuadruple, SuperheavyAlloyA),
                'U', UnificationEntry(cableGtQuadruple, Seaborgium))

            // UIV Hi-Amp Transformer
            ModHandler.addShapedRecipe(true, "hi_amp_transformer_uiv", HI_AMP_TRANSFORMER[UIV].stackForm,
                "CUU", "WV ", "CUU",
                'C', VOLTAGE_COIL_UIV,
                'V', TRANSFORMER[UIV].stackForm,
                'W', UnificationEntry(cableGtQuadruple, SuperheavyAlloyB),
                'U', UnificationEntry(cableGtQuadruple, SuperheavyAlloyA))

            // UXV Hi-Amp Transformer
            ModHandler.addShapedRecipe(true, "hi_amp_transformer_uxv", HI_AMP_TRANSFORMER[UXV].stackForm,
                "CUU", "WV ", "CUU",
                'C', VOLTAGE_COIL_UXV,
                'V', TRANSFORMER[UXV].stackForm,
                'W', UnificationEntry(cableGtQuadruple, Periodicium),
                'U', UnificationEntry(cableGtQuadruple, SuperheavyAlloyB))

            // TODO OpV Transformers

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

            // Rhodium Plated Palladium Turbine Casing
            ModHandler.addShapedRecipe(true, "rhodium_plated_palladium_turbine_casing", GTLiteMetaBlocks.TURBINE_CASING_01.getItemVariant(BlockTurbineCasing01.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_TURBINE, ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PCP", "PwP",
                'C', MetaBlocks.TURBINE_CASING.getItemVariant(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
                'P', UnificationEntry(plate, RhodiumPlatedPalladium))

            // Rhodium Plated Palladium Gearbox Casing
            ModHandler.addShapedRecipe(true, "rhodium_plated_palladium_gearbox_casing", GTLiteMetaBlocks.TURBINE_CASING_02.getItemVariant(BlockTurbineCasing02.TurbineCasingType.RHODIUM_PLATED_PALLADIUM_GEARBOX, ConfigHolder.recipes.casingsPerCraft),
                "PhP", "GFG", "PwP",
                'P', UnificationEntry(plate, RhodiumPlatedPalladium),
                'G', UnificationEntry(gear, RhodiumPlatedPalladium),
                'F', UnificationEntry(frameGt, RhodiumPlatedPalladium))

            // Nuclear Reactor Cores
            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.thorium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.THORIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Thorium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.protactinium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.PROTACTINIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Protactinium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.uranium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.URANIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Uranium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.neptunium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.NEPTUNIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Neptunium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.plutonium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.PLUTONIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Plutonium239))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.americium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.AMERICIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Americium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.curium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.CURIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Curium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.berkelium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_01.getItemVariant(BlockNuclearReactorCore01.ReactorCoreType.BERKELIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Berkelium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.californium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_02.getItemVariant(BlockNuclearReactorCore02.ReactorCoreType.CALIFORNIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Californium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.einsteinium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_02.getItemVariant(BlockNuclearReactorCore02.ReactorCoreType.EINSTEINIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Einsteinium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.fermium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_02.getItemVariant(BlockNuclearReactorCore02.ReactorCoreType.FERMIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Fermium))

            ModHandler.addShapedRecipe(true, "nuclear_reactor_core.mendelevium", GTLiteMetaBlocks.NUCLEAR_REACTOR_CORE_02.getItemVariant(BlockNuclearReactorCore02.ReactorCoreType.MENDELEVIUM),
                "PRP", "SDS", "PRP",
                'R', UnificationEntry(fuelRodQuadruple, Graphite),
                'P', UnificationEntry(plateDense, StainlessSteel),
                'S', UnificationEntry(stickLong, StainlessSteel),
                'D', UnificationEntry(plateDense, Mendelevium))

        }

    }

}