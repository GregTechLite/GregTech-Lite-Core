package gregtechlite.gtlitecore.core

import gregtechlite.magicbook.util.SidedLogger
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.MOD_NAME
import gregtechlite.gtlitecore.api.module.IModule
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.api.unification.ore.GTLiteStoneTypes
import gregtechlite.gtlitecore.client.event.ClientEventHandlers
import gregtechlite.gtlitecore.common.CommonProxy
import gregtechlite.gtlitecore.common.EventHandlers
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import gregtechlite.gtlitecore.common.command.CommandMaterialComponent
import gregtechlite.gtlitecore.common.cover.GTLiteCoverBehaviors
import gregtechlite.gtlitecore.common.entity.GTLiteMetaEntities
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems
import gregtechlite.gtlitecore.common.item.behavior.GTLiteBehaviors
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import gregtechlite.gtlitecore.core.advancement.AdvancementManager
import gregtechlite.gtlitecore.core.advancement.AdvancementTriggers
import gregtechlite.gtlitecore.core.command.CommandManager
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_CORE
import gregtechlite.gtlitecore.core.network.NetworkHandler
import gregtechlite.gtlitecore.core.sound.GTLiteSoundEvents
import gregtechlite.gtlitecore.core.sound.SoundManager
import gregtechlite.gtlitecore.loader.VanillaDropsLoader
import gregtechlite.gtlitecore.loader.dungeon.DungeonLootLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartedEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent
import org.apache.logging.log4j.Logger

@Module(moduleId = MODULE_CORE,
        containerId = MOD_ID,
        name = MOD_NAME,
        descriptions = "Core Module of GregTech Lite Core Mod.",
        isCore = true)
internal class CoreModule : IModule
{

    init
    {
        GTLiteAPI.networkHandler = NetworkHandler.getInstance()
    }

    companion object
    {
        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-api")

        @SidedProxy(modId = MOD_ID,
                    clientSide = "gregtechlite.gtlitecore.client.ClientProxy",
                    serverSide = "gregtechlite.gtlitecore.common.CommonProxy")
        lateinit var proxy: CommonProxy
    }
    
    // region FML Lifecycle Events

    override fun construction(event: FMLConstructionEvent)
    {
        logger.debug("Starting to construct EventHandlers of the mod")
        MinecraftForge.EVENT_BUS.register(EventHandlers)
        MinecraftForge.EVENT_BUS.register(ClientEventHandlers)

        logger.debug("Modifying configurations of GregTech mod")
        GTLiteConfigModifier.init()

        logger.debug("Adding custom CreativeTabs of the mod to GregTech machines")
        GTLiteMetaTileEntities.preInit()
    }
    
    override fun preInit(event: FMLPreInitializationEvent)
    {

        logger.debug("Starting to construct Advancements and its AdvancementTriggers")
        GTLiteAPI.advancementManager = AdvancementManager.getInstance()
        AdvancementTriggers.register()

        logger.debug("Loading SoundEvents and Sound files")
        GTLiteAPI.soundManager = SoundManager.getInstance()
        GTLiteSoundEvents.register()

        logger.debug("Loading all MetaItems and MetaOreDictItems")
        GTLiteMetaItems.init()
        GTLiteMetaOreDictItems.init()

        logger.debug("Loading all MetaBlocks and MetaBlock Adapters")
        GTLiteMetaBlocks.init()
        GTLiteMetaBlocks.setFireInfos()

        logger.debug("Adding custom StoneType of the mod to GregTech")
        GTLiteStoneTypes.init()

        logger.debug("Loading BlockAttribute Contexts")
        GTLiteAPI.init()

        logger.debug("Loading all MetaTileEntities and MetaEntities")
        GTLiteMetaTileEntities.init()
        GTLiteMetaEntities.init()

        logger.debug("Starting to load Pre-Load contents by Proxies")
        proxy.onPreInit()
    }
    
    override fun init(event: FMLInitializationEvent)
    {
        logger.debug("Registering Block(Item)ColorHandler for Meta(Item)Blocks")
        GTLiteMetaBlocks.registerColors()

        logger.debug("Adding the mod contents to vanilla Dungeon Looting Table")
        DungeonLootLoader.init()

        logger.debug("Adding Cover Behaviours to MetaItems")
        GTLiteCoverBehaviors.init()

        logger.debug("Starting to load Load contents by Proxies")
        proxy.onInit()
    }
    
    override fun postInit(event: FMLPostInitializationEvent)
    {
        logger.debug("Adding other Behaviours to MetaItems")
        GTLiteBehaviors.addBehaviors()

        logger.debug("Adding the mod contents to vanilla Grass Drops Table")
        VanillaDropsLoader.addSeedDrops()
    }
    
    override fun loadComplete(event: FMLLoadCompleteEvent)
    {
        // ...
    }
    
    override fun serverStarting(event: FMLServerStartingEvent)
    {
        logger.debug("Starting to construct Commands of the mod")
        val commandManager = CommandManager.getInstance()
        GTLiteAPI.commandManager = commandManager
        commandManager.registerServerCommand(event)
        GTLiteAPI.commandManager.addCommand(CommandMaterialComponent())
    }
    
    override fun serverStarted(event: FMLServerStartedEvent)
    {
        // ...
    }
    
    override fun serverStopping(event: FMLServerStoppingEvent)
    {
        // ...
    }
    
    override fun serverStopped(event: FMLServerStoppedEvent)
    {
        // ...
    }

    override fun registerPackets()
    {
        // ...
    }

    // endregion

    override fun getLogger(): Logger = Companion.logger

}
