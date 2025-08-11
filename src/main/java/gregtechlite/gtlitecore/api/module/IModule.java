package gregtechlite.gtlitecore.api.module;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface IModule
{
    /**
     * What other modules this module depends on.
     * <p>
     * For example, {@code new ResourceLocation("gtlitecore", "module_name")}
     * represents a dependency on the module "module_name" in the container "gtlitecore".
     */
    default Set<ResourceLocation> getDependencyUids()
    {
        return Collections.emptySet();
    }

    /* -------------------------------- FML Life cycle Events -------------------------------- */
    // Construction Event means events will be loaded when Mod is starting to loaded.
    default void construction(FMLConstructionEvent event) {}

    // Pre-Initialization Event means it will "Run before anything else".
    default void preInit(FMLPreInitializationEvent event) {}

    // Initialization Event means it will "Do your mod setup",
    // you should build whatever data structures you care about.
    default void init(FMLInitializationEvent event) {}

    // Post-Initialization event means it will "Handle interaction with other mods",
    // you can complete your setup based on this.
    default void postInit(FMLPostInitializationEvent event) {}

    // Load Complete Event means events will be loaded when Mod is finish loaded.
    default void loadComplete(FMLLoadCompleteEvent event) {}

    // Server About To Start Event means events will be loaded before Server started.
    default void serverAboutToStart(FMLServerAboutToStartEvent event) {}

    // Server Starting Event means events will be loaded when Server is starting.
    default void serverStarting(FMLServerStartingEvent event) {}

    // Server Started Event means events will be loaded when Server is started.
    default void serverStarted(FMLServerStartedEvent event) {}

    // Server Stopping Event means events will be loaded when Server is stopping.
    default void serverStopping(FMLServerStoppingEvent event) {}

    // Server Stopped Event means events will be loaded when Server is stopped.
    default void serverStopped(FMLServerStoppedEvent event) {}
    /* --------------------------------------------------------------------------------------- */

    /**
     * Register packets using packet handling API here.
     */
    default void registerPackets() {}

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers <strong>must</strong> be {@code static}.
     *
     * @return A list of classes to subscribe to the Forge Event Bus.
     */
    @NotNull
    default List<Class<?>> getEventBusSubscribers()
    {
        return Collections.emptyList();
    }

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers <strong>must</strong> be {@code static}.
     *
     * @return A list of classes to subscribe to the Forge Terrain Gen Bus.
     */
    @NotNull
    default List<Class<?>> getTerrainGenBusSubscribers()
    {
        return Collections.emptyList();
    }

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers <strong>must</strong> be {@code static}.
     *
     * @return A list of classes to subscribe to the Forge Ore Gen Bus.
     */
    @NotNull
    default List<Class<?>> getOreGenBusSubscribers()
    {
        return Collections.emptyList();
    }

    /**
     * @param message The message to process.
     * @return        If the message was processed, stopping all other modules from
     *                processing it.
     */
    default boolean processIMC(FMLInterModComms.IMCMessage message)
    {
        return false;
    }

    /**
     * @return A logger to use for this module.
     */
    @NotNull
    Logger getLogger();

}
