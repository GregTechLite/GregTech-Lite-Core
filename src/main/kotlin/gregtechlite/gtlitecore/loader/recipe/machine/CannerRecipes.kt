package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.pipeHugeFluid
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.pipeSmallFluid
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.turbineBlade
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trinaquadalloy
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOW_DENSITY_STRUCTURE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BLOCK
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BOLT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_BOTTLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_CELL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_DRILL_HEAD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_FRAME
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_GEAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_GEAR_SMALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_INGOT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_HUGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_LARGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_NORMAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_SMALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PIPE_TINY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PLATE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PLATE_DENSE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_PLATE_DOUBLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_RING
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROD_LONG
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_ROUND
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_SCREW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_TURBINE_BLADE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_DOUBLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_FINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_HEX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_OCTAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SHAPE_FIELD_WIRE_QUADRUPLE
import net.minecraft.init.Blocks.END_PORTAL_FRAME
import net.minecraft.init.Blocks.END_STONE
import net.minecraft.init.Items.ENDER_EYE

internal object CannerRecipes
{
    // @formatter:off

    fun init()
    {
        // End Portal Frame.
        CANNER_RECIPES.addRecipe {
            inputs(END_STONE)
            inputs(ENDER_EYE)
            outputs(END_PORTAL_FRAME)
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Shape Force Fields
        addShapeFieldRecipe(SHAPE_FIELD_PLATE         , plate          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_ROD           , stick          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_BOLT          , bolt           , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_RING          , ring           , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_CELL          , nugget         , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_INGOT         , ingot          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_WIRE          , wireGtSingle   , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_PIPE_TINY     , pipeTinyFluid  , FieldType.Pipe       )
        addShapeFieldRecipe(SHAPE_FIELD_PIPE_SMALL    , pipeSmallFluid , FieldType.Pipe       )
        addShapeFieldRecipe(SHAPE_FIELD_PIPE_NORMAL   , pipeNormalFluid, FieldType.Pipe       )
        addShapeFieldRecipe(SHAPE_FIELD_PIPE_LARGE    , pipeLargeFluid , FieldType.Pipe       )
        addShapeFieldRecipe(SHAPE_FIELD_PIPE_HUGE     , pipeHugeFluid  , FieldType.Pipe       )
        addShapeFieldRecipe(SHAPE_FIELD_BLOCK         , block          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_GEAR          , gear           , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_BOTTLE        , dust           , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_FOIL          , foil           , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_GEAR_SMALL    , gearSmall      , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_ROD_LONG      , stickLong      , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_ROTOR         , rotor          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_ROUND         , round          , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_TURBINE_BLADE , turbineBlade   , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_DRILL_HEAD    , toolHeadDrill  , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_FRAME         , frameGt        , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_WIRE_DOUBLE   , wireGtDouble   , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_WIRE_QUADRUPLE, wireGtQuadruple, FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_WIRE_OCTAL    , wireGtOctal    , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_WIRE_HEX      , wireGtHex      , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_WIRE_FINE     , wireFine       , FieldType.CableOrTool)
        addShapeFieldRecipe(SHAPE_FIELD_PLATE_DOUBLE  , plateDouble    , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_PLATE_DENSE   , plateDense     , FieldType.Common     )
        addShapeFieldRecipe(SHAPE_FIELD_SCREW         , screw          , FieldType.Common     )
    }

    private fun addShapeFieldRecipe(fieldItem: MetaItem<*>.MetaValueItem, prefix: OrePrefix,
                                     fieldType: FieldType)
    {
        when (fieldType)
        {
            FieldType.Common ->
            {
                CANNER_RECIPES.addRecipe {
                    input(LOW_DENSITY_STRUCTURE)
                    input(prefix, QuantumAlloy)
                    output(fieldItem)
                    EUt(VA[ULV])
                    duration(1 * SECOND)
                }
            }
            FieldType.CableOrTool ->
            {
                CANNER_RECIPES.addRecipe {
                    input(LOW_DENSITY_STRUCTURE)
                    input(prefix, EnrichedNaquadahAlloy)
                    output(fieldItem)
                    EUt(VA[ULV])
                    duration(1 * SECOND)
                }
            }
            FieldType.Pipe ->
            {
                CANNER_RECIPES.addRecipe {
                    input(LOW_DENSITY_STRUCTURE)
                    input(prefix, Trinaquadalloy)
                    output(fieldItem)
                    EUt(VA[ULV])
                    duration(1 * SECOND)
                }
            }
        }

    }

    private enum class FieldType { Common, CableOrTool, Pipe }

    // @formatter:on
}