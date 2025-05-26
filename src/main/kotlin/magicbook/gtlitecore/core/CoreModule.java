package magicbook.gtlitecore.core;

import gregtech.api.GTValues;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.module.IModule;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.unification.ore.GTLiteStoneTypes;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.client.ClientEventHandlers;
import magicbook.gtlitecore.common.CommonProxy;
import magicbook.gtlitecore.common.EventHandlers;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.command.CommandMaterialComponent;
import magicbook.gtlitecore.common.cover.GTLiteCoverBehaviors;
import magicbook.gtlitecore.common.entity.GTLiteMetaEntities;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems;
import magicbook.gtlitecore.common.item.behavior.GTLiteBehaviors;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import magicbook.gtlitecore.core.advancement.AdvancementManager;
import magicbook.gtlitecore.core.advancement.trigger.AdvancementTriggers;
import magicbook.gtlitecore.core.command.CommandManager;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.core.network.NetworkHandler;
import magicbook.gtlitecore.core.sound.GTLiteSoundEvents;
import magicbook.gtlitecore.core.sound.SoundManager;
import magicbook.gtlitecore.loader.VanillaDropsLoader;
import magicbook.gtlitecore.loader.dungeon.DungeonLootLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
@Module(moduleId = GTLiteModules.MODULE_CORE,
        containerId = GTLiteValues.MODID,
        name = GTLiteValues.NAME,
        descriptions = "Core Module of GregTech Lite Core Mod.",
        isCore = true)
public class CoreModule implements IModule
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " API");

    /* -------------------------------------------------------------------------------------------------------------- */
    @SidedProxy(modId = GTLiteValues.MODID,
                clientSide = "magicbook.gtlitecore.client.ClientProxy",
                serverSide = "magicbook.gtlitecore.common.CommonProxy")
    public static CommonProxy proxy;

    public CoreModule()
    {
        // Initialized NetworkHandler, prepared to #registerPackets methods.
        GTLiteAPI.networkHandler = NetworkHandler.getInstance();
    }

    /* ----------------------------------------------- FML Life Cycle ----------------------------------------------- */
    @Override
    public void construction(FMLConstructionEvent event)
    {
        // Constructing server side event handlers in Kotlin environment.
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        // Constructing client side event handlers in Kotlin environment.
        if (GTValues.isClientSide())
            MinecraftForge.EVENT_BUS.register(new ClientEventHandlers());
        // Modified config of other mod' configs.
        GTLiteConfigModifier.init();
        // Add modid of the mod to Gregtech machine creative tabs pool,
        // this is prepared for MetaTileEntity creative tabs posting.
        GTLiteMetaTileEntities.preInit();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        // Loading AdvancementManager and AdvancedTriggers.
        GTLiteAPI.advancementManager = AdvancementManager.getInstance();
        AdvancementTriggers.register();
        // Loading SoundManager and SoundEvents.
        GTLiteAPI.soundManager = SoundManager.getInstance();
        GTLiteSoundEvents.register();
        // Loading Gregtech StoneTypes registries.
        GTLiteStoneTypes.init();
        // Loading Gregtech MetaItems/MetaBlocks registries.
        GTLiteMetaItems.init();
        GTLiteMetaOreDictItems.init();
        GTLiteMetaBlocks.init();
        // Loading API initializers and Gregtech API extensions.
        GTLiteAPI.init();
        // Loading Gregtech MetaTileEntities registries.
        GTLiteMetaTileEntities.init();
        // Loading TileEntities registries.
        GTLiteMetaEntities.init();
        // Pre-Loading proxies.
        proxy.onPreInit();
    }

    @Override
    public void registerPackets()
    {
        // ...
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        if (GTValues.isClientSide())
            GTLiteMetaBlocks.registerColors();
        DungeonLootLoader.init();
        GTLiteMetaTileEntities.postInit();
        GTLiteCoverBehaviors.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        GTLiteBehaviors.addBehaviors();
        VanillaDropsLoader.addSeedDrops();
    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event)
    {
        // ...
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event)
    {
        // Loading CommandManagers.
        CommandManager commandManager = CommandManager.getInstance();
        GTLiteAPI.commandManager = commandManager;
        commandManager.registerServerCommand(event);
        GTLiteAPI.commandManager.addCommand(new CommandMaterialComponent());
    }

    @Override
    public void serverStarted(FMLServerStartedEvent event)
    {
        // ...
    }

    @Override
    public void serverStopping(FMLServerStoppingEvent event)
    {
        // ...
    }

    @Override
    public void serverStopped(FMLServerStoppedEvent event)
    {
        // ...
    }

    /* -------------------------------------------------------------------------------------------------------------- */
    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
