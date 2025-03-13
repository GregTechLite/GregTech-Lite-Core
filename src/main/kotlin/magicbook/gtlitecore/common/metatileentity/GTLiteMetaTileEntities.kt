package magicbook.gtlitecore.common.metatileentity

import gregtech.api.GTValues
import gregtech.api.capability.impl.PropertyFluidFilter
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.unification.material.Materials
import gregtech.api.util.GTUtility
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.common.metatileentities.storage.MetaTileEntityDrum
import magicbook.gtlitecore.api.gui.SteamProgressBarIndicator
import magicbook.gtlitecore.api.gui.SteamProgressBarIndicators
import magicbook.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity
import magicbook.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity
import magicbook.gtlitecore.api.metatileentity.SimpleSteamMachineMetaTileEntity
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.metatileentity.multiblock.MetaTileEntityCoagulationTank
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySapCollector
import magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySteamSapCollector
import magicbook.gtlitecore.common.metatileentity.storage.MetaTileEntityPlasticCan

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
        val CHEMICAL_DEHYDRATOR = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_VULCANIZING_PRESS = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VULCANIZING_PRESS = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_VACUUM_CHAMBER = arrayOfNulls<SimpleSteamMachineMetaTileEntity>(2)
        val VACUUM_CHAMBER = arrayOfNulls<SimpleMachineMetaTileEntity>(GTValues.V.size - 1)
        val STEAM_SAP_COLLECTOR = arrayOfNulls<PseudoMultiSteamMachineMetaTileEntity>(2)
        val SAP_COLLECTOR = arrayOfNulls<PseudoMultiMachineMetaTileEntity>(5)

        lateinit var IRON_DRUM: MetaTileEntityDrum
        lateinit var COPPER_DRUM: MetaTileEntityDrum
        lateinit var LEAD_DRUM: MetaTileEntityDrum
        lateinit var CHROME_DRUM: MetaTileEntityDrum
        lateinit var TUNGSTEN_DRUM: MetaTileEntityDrum
        lateinit var IRIDIUM_DRUM: MetaTileEntityDrum
        // TODO More higher drum like Naquadah, Duranium and Neutronium?
        lateinit var PE_CAN: MetaTileEntityPlasticCan
        lateinit var PTFE_CAN: MetaTileEntityPlasticCan
        lateinit var PBI_CAN: MetaTileEntityPlasticCan

        lateinit var COAGULATION_TANK: MetaTileEntityCoagulationTank

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

            // 14076-14090: Chemical Dehydrator
            MetaTileEntities.registerSimpleMetaTileEntity(CHEMICAL_DEHYDRATOR, 14078, // 14076-14077 for Steam Machines.
                "chemical_dehydrator", GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES,
                GTLiteTextures.CHEMICAL_DEHYDRATOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.defaultTankSizeFunction)

            // 14091-14105: Vulcanizing Press
            registerSteamMetaTileEntity(STEAM_VULCANIZING_PRESS, 14091,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                SteamProgressBarIndicators.ARROW_MULTIPLE,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true)

            MetaTileEntities.registerSimpleMetaTileEntity(VULCANIZING_PRESS, 14093,
                "vulcanizing_press", GTLiteRecipeMaps.VULCANIZATION_RECIPES,
                GTLiteTextures.VULCANIZING_PRESS_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14106-14120: Vacuum Chamber
            registerSteamMetaTileEntity(STEAM_VACUUM_CHAMBER, 14106,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                SteamProgressBarIndicators.COMPRESS,
                Textures.GAS_COLLECTOR_OVERLAY, false)

            MetaTileEntities.registerSimpleMetaTileEntity(VACUUM_CHAMBER, 14108,
                "vacuum_chamber", GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES,
                Textures.GAS_COLLECTOR_OVERLAY, true,
                GTLiteUtility::gtliteId, GTUtility.genericGeneratorTankSizeFunction)

            // 14121-14135: Sap Collector
            STEAM_SAP_COLLECTOR[0] = MetaTileEntities.registerMetaTileEntity(14121,
                MetaTileEntitySteamSapCollector(GTLiteUtility.gtliteId("sap_collector.bronze"), false))

            STEAM_SAP_COLLECTOR[1] = MetaTileEntities.registerMetaTileEntity(14122,
                MetaTileEntitySteamSapCollector(GTLiteUtility.gtliteId("sap_collector.steel"), true))

            SAP_COLLECTOR[0] = MetaTileEntities.registerMetaTileEntity(14123,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.lv"), GTValues.LV))

            SAP_COLLECTOR[1] = MetaTileEntities.registerMetaTileEntity(14124,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.mv"), GTValues.MV))

            SAP_COLLECTOR[2] = MetaTileEntities.registerMetaTileEntity(14125,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.hv"), GTValues.HV))

            SAP_COLLECTOR[3] = MetaTileEntities.registerMetaTileEntity(14126,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.ev"), GTValues.EV))

            SAP_COLLECTOR[4] = MetaTileEntities.registerMetaTileEntity(14127,
                MetaTileEntitySapCollector(GTLiteUtility.gtliteId("sap_collector.iv"), GTValues.IV))

            // ...

            // 15001-15050: Drums and Crates.
            IRON_DRUM = MetaTileEntities.registerMetaTileEntity(15001,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.iron"),
                    PropertyFluidFilter(1811, true, true, false, false),
                    false, Materials.Iron.materialRGB, 12000))

            COPPER_DRUM = MetaTileEntities.registerMetaTileEntity(15002,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.copper"), Materials.Copper, 16000))

            LEAD_DRUM = MetaTileEntities.registerMetaTileEntity(15003,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.lead"), Materials.Lead, 24000))

            CHROME_DRUM = MetaTileEntities.registerMetaTileEntity(15004,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.chrome"), Materials.Chrome, 96000))

            TUNGSTEN_DRUM = MetaTileEntities.registerMetaTileEntity(15005,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.tungsten"), Materials.Tungsten, 768000))

            IRIDIUM_DRUM = MetaTileEntities.registerMetaTileEntity(15006,
                MetaTileEntityDrum(GTLiteUtility.gtliteId("drum.iridium"), Materials.Iridium, 1536000))

            // ...

            PE_CAN = MetaTileEntities.registerMetaTileEntity(15015,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polyethylene"), Materials.Polyethylene, 64000))

            PTFE_CAN = MetaTileEntities.registerMetaTileEntity(15016,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polytetrafluoroethylene"), Materials.Polytetrafluoroethylene, 128000))

            PBI_CAN = MetaTileEntities.registerMetaTileEntity(15017,
                MetaTileEntityPlasticCan(GTLiteUtility.gtliteId("plastic_can.polybenzimidazole"), Materials.Polybenzimidazole, 256000))

            // ...

            // 15051-15060: I/O hatch proxies.

            // ...

            // 18001-... Multiblocks
            COAGULATION_TANK = MetaTileEntities.registerMetaTileEntity(18001,
                MetaTileEntityCoagulationTank(GTLiteUtility.gtliteId("coagulation_tank")))

        }

        private fun registerSteamMetaTileEntity(machines: Array<SimpleSteamMachineMetaTileEntity?>, startId: Int,
                                                name: String, recipeMap: RecipeMap<*>,
                                                progressBarIndicator: SteamProgressBarIndicator,
                                                texture: ICubeRenderer, isBricked: Boolean)
        {
            machines[0] = MetaTileEntities.registerMetaTileEntity(startId,
                SimpleSteamMachineMetaTileEntity(GTLiteUtility.gtliteId(String.format("%s.bronze", name)),
                    recipeMap, progressBarIndicator, texture, isBricked, false))
            machines[1] = MetaTileEntities.registerMetaTileEntity(startId + 1,
                SimpleSteamMachineMetaTileEntity(GTLiteUtility.gtliteId(String.format("%s.steel", name)),
                    recipeMap, progressBarIndicator, texture, isBricked, true))
        }

    }

}