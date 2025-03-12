package magicbook.gtlitecore.common.metatileentity

import gregtech.api.GTValues
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.util.GTUtility
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.client.GTLiteTextures

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteMetaTileEntities
{

    companion object
    {

        // ======================================= GREGTECH METATILEENTITIES ID LIST =======================================

        // - Gregtech (Vanilla)                           1     - 1999
        // - Gregicality (Multiblocks & Science)          2000  - 3999
        // - Integration Modules of GregTech              4000  - 4499
        // - Gregic Addition CEu                          4500  - 8499
        // - GregTech Food Option                         8500  - 8999
        // - HtmlTech                                     9000  - 9499
        // - PCM's Ore Addon                              9500  - 9999
        // - GCM                                          10000 - 10099
        // - MechTech                                     10100 - 10499
        // - MultiblockTweaker                            10500 - 10999
        // - Gregtech Lite Core (THIS)                    14000 - 20000
        // - Integration Modules of Gregtech Lite Core    20001 - 25000
        // - CraftTweaker (MultiblockTweaker)             32000 - 32767

        // =================================================================================================================

        // Single machines.
        val POLISHER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val SLICER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val TOOL_CASTER = arrayOfNulls<SimpleMachineMetaTileEntity>(5)
        val LOOM = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val LAMINATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)

        // Multiblock machines.

        @JvmStatic
        fun init()
        {
            // 14001-14015: Polisher
            MetaTileEntities.registerSimpleMetaTileEntity(POLISHER, 14003, // 14001-14002 for Steam Machines.
                "polisher", GTLiteRecipeMaps.POLISHER_RECIPES,
                GTLiteTextures.POLISHER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 14016-14030: Slicer
            MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 14018, // 14016-14017 for Steam Machines.
                "slicer", GTLiteRecipeMaps.SLICER_RECIPES,
                GTLiteTextures.SLICER_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.hvCappedTankSizeFunction)

            // 14031-14045: Tool Caster
            TOOL_CASTER[0] = MetaTileEntities.registerMetaTileEntity(14033,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.lv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.LV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[1] = MetaTileEntities.registerMetaTileEntity(14034,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.mv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.MV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[2] = MetaTileEntities.registerMetaTileEntity(14035,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.hv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.HV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[3] = MetaTileEntities.registerMetaTileEntity(14036,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.ev"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.EV, true, GTUtility.defaultTankSizeFunction))

            TOOL_CASTER[4] = MetaTileEntities.registerMetaTileEntity(14037,
                SimpleMachineMetaTileEntity(GTLiteUtility.gtliteId("tool_caster.iv"),
                    GTLiteRecipeMaps.TOOL_CASTER_RECIPES, GTLiteTextures.TOOL_CASTER_OVERLAY,
                    GTValues.IV, true, GTUtility.defaultTankSizeFunction))

            // 14046-14060: Loom
            MetaTileEntities.registerSimpleMetaTileEntity(LOOM, 14048, // 14046-14047 for Steam Machines.
                "loom", GTLiteRecipeMaps.LOOM_RECIPES,
                GTLiteTextures.LOOM_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14061-14075: Laminator
            MetaTileEntities.registerSimpleMetaTileEntity(LAMINATOR, 14063, // 14061-14062 for Steam Machines.
                "laminator", GTLiteRecipeMaps.LAMINATOR_RECIPES,
                GTLiteTextures.LAMINATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.largeTankSizeFunction)

        }

    }

}