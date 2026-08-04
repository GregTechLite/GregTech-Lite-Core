package gregtechlite.gtlitecore.api.module

import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLInterModComms
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent
import net.minecraftforge.fml.common.event.FMLServerStartedEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent
import org.apache.logging.log4j.Logger

interface CustomModule
{
    /**
     * What other modules this module depends on.
     *
     * For example, `ResourceLocation("gtlitecore", "module_name")` represents a dependency on the
     * module `module_name` in the container `gtlitecore`.
     */
    fun getDependencyUids(): Set<ResourceLocation> = emptySet()

    // region FML Life cycle Events

    // Construction Event means events will be loaded when Mod is starting to loaded.
    fun construction(event: FMLConstructionEvent) {}

    // Pre-Initialization Event means it will "Run before anything else".
    fun preInit(event: FMLPreInitializationEvent) {}

    // Initialization Event means it will "Do your mod setup",
    // you should build whatever data structures you care about.
    fun init(event: FMLInitializationEvent) {}

    // Post-Initialization event means it will "Handle interaction with other mods",
    // you can complete your setup based on this.
    fun postInit(event: FMLPostInitializationEvent) {}

    // Load Complete Event means events will be loaded when Mod is finish loaded.
    fun loadComplete(event: FMLLoadCompleteEvent) {}

    // Server About To Start Event means events will be loaded before Server started.
    fun serverAboutToStart(event: FMLServerAboutToStartEvent) {}

    // Server Starting Event means events will be loaded when Server is starting.
    fun serverStarting(event: FMLServerStartingEvent) {}

    // Server Started Event means events will be loaded when Server is started.
    fun serverStarted(event: FMLServerStartedEvent) {}

    // Server Stopping Event means events will be loaded when Server is stopping.
    fun serverStopping(event: FMLServerStoppingEvent) {}

    // Server Stopped Event means events will be loaded when Server is stopped.
    fun serverStopped(event: FMLServerStoppedEvent) {}

    // endregion

    /**
     * Register packets using packet handling API here.
     */
    fun registerPackets() {}

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers **must** be `static`.
     *
     * @return A list of classes to subscribe to the Forge Event Bus.
     */
    fun getEventBusSubscribers(): List<Class<*>> = emptyList()

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers **must** be `static`.
     *
     * @return A list of classes to subscribe to the Forge Terrain Gen Bus.
     */
    fun getTerrainGenBusSubscribers(): List<Class<*>> = emptyList()

    /**
     * The class itself gets subscribed, instead of a class instance, so event
     * handlers **must** be `static`.
     *
     * @return A list of classes to subscribe to the Forge Ore Gen Bus.
     */
    fun getOreGenBusSubscribers(): List<Class<*>> = emptyList()

    /**
     * @param message The message to process.
     * @return        If the message was processed, stopping all other modules from
     *                processing it.
     */
    fun processIMC(message: FMLInterModComms.IMCMessage): Boolean = false

    /**
     * @return A logger to use for this module.
     */
    fun getLogger(): Logger
}
