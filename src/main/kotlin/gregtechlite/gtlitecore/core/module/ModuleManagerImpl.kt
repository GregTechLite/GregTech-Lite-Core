package gregtechlite.gtlitecore.core.module

import com.google.common.collect.ImmutableList
import com.morphismmc.morphismlib.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.collection.openLinkedSetOf
import gregtechlite.gtlitecore.api.collection.openRefLinkedSetOf
import gregtechlite.gtlitecore.api.collection.openRefLinkedMapOf
import gregtechlite.gtlitecore.api.module.CustomModule
import gregtechlite.gtlitecore.api.module.CustomModuleContainer
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.api.module.ModuleContainer
import gregtechlite.gtlitecore.api.module.ModuleManager
import gregtechlite.gtlitecore.api.module.ModuleStage
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.discovery.ASMDataTable
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
import java.io.File
import java.util.Locale

class ModuleManagerImpl private constructor() : ModuleManager
{
    companion object
    {
        @JvmField
        internal val instance: ModuleManagerImpl = ModuleManagerImpl()

        private const val MODULE_CFG_FILE_NAME = "modules.cfg"
        private const val MODULE_CFG_CATEGORY_NAME = "modules"

        private var configFolder: File? = null
    }

    private val logger: Logger = SidedLogger("$MOD_ID-module-loader")

    private var containers = openRefLinkedMapOf<String, CustomModuleContainer>()
    private val sortedModules = linkedMapOf<ResourceLocation, CustomModule>()
    private val loadedModules = linkedSetOf<CustomModule>()

    private var currentContainer: CustomModuleContainer? = null
    private var currentStage: ModuleStage = ModuleStage.C_SETUP

    private var config: Configuration? = null

    override fun isModuleEnabled(namespace: ResourceLocation): Boolean = sortedModules.containsKey(namespace)

    fun isModuleEnabled(module: CustomModule): Boolean
    {
        val annotation = module.javaClass.getAnnotation(Module::class.java)
        val comment = getComment(module)
        val propertyKey = "${annotation.containerId}:${annotation.moduleId}"
        val property = getConfiguration().get(MODULE_CFG_CATEGORY_NAME, propertyKey, true, comment)
        return property.boolean
    }

    override fun getLoadedContainer(): CustomModuleContainer = checkNotNull(currentContainer)

    override fun getStage(): ModuleStage = currentStage

    override fun hasPassedStage(stage: ModuleStage): Boolean = currentStage.ordinal > stage.ordinal

    override fun registerContainer(container: CustomModuleContainer)
    {
        if (currentStage != ModuleStage.C_SETUP)
        {
            logger.error("Failed to register ModuleContainer '{}', as Module loading has already begun", container)
            return
        }
        containers[container.id] = container
    }

    /**
     * Set up the [ModuleManagerImpl] class.
     *
     * @param dataTable The data table containing all of the [ModuleContainer] and [Module] classes.
     * @param configDir The directory containing the configuration directory.
     */
    fun setup(dataTable: ASMDataTable, configDir: File)
    {
        // Find and register all containers registered with annotation and then sorted them by container names.
        discoverContainers(dataTable)
        containers = openRefLinkedMapOf<String, CustomModuleContainer>().also { sortedContainers ->
            for ((k, v) in containers.entries.sortedBy { it.key })
                sortedContainers[k] = v
        }

        currentStage = ModuleStage.M_SETUP
        configFolder = File(configDir, MOD_ID)

        val modules = getModules(dataTable)
        configureModules(modules)

        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Registering Event Handlers")
            for (eventClass in module.eventBusSubscribers)
                MinecraftForge.EVENT_BUS.register(eventClass)
            for (terrainGenClass in module.terrainGenBusSubscribers)
                MinecraftForge.TERRAIN_GEN_BUS.register(terrainGenClass)
            for (oreGenClass in module.oreGenBusSubscribers)
                MinecraftForge.ORE_GEN_BUS.register(oreGenClass)
        }
        currentContainer = null
    }

    // region FML Lifecycle Events

    // Construction Event means events will be loaded when Mod is starting to loaded.
    fun onConstruction(event: FMLConstructionEvent)
    {
        currentStage = ModuleStage.CONSTRUCTION
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Construction start")
            module.construction(event)
            module.logger.debug("Construction complete")
        }
    }

    // Pre-Initialization Event means it will "Run before anything else".
    fun onPreInit(event: FMLPreInitializationEvent)
    {
        currentStage = ModuleStage.PRE_INIT
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Registering packets")
            module.registerPackets()
        }
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Pre-Init start")
            module.preInit(event)
            module.logger.debug("Pre-Init complete")
        }
    }

    // Initialization Event means it will "Do your mod setup", you should build whatever data structures you care about.
    fun onInit(event: FMLInitializationEvent)
    {
        currentStage = ModuleStage.INIT
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Init start")
            module.init(event)
            module.logger.debug("Init complete")
        }
    }

    // Post-Initialization event means it will "Handle interaction with other mods", you can complete your setup based
    // on this.
    fun onPostInit(event: FMLPostInitializationEvent)
    {
        currentStage = ModuleStage.POST_INIT
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Post-Init start")
            module.postInit(event)
            module.logger.debug("Post-Init complete")
        }
    }

    // Load Complete Event means events will be loaded when Mod is finish loaded.
    fun onLoadComplete(event: FMLLoadCompleteEvent)
    {
        currentStage = ModuleStage.LOAD_COMPLETE
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Load Complete start")
            module.loadComplete(event)
            module.logger.debug("Load Complete complete")
        }
    }

    // Server About To Start Event means events will be loaded before Server started.
    fun onServerAboutToStart(event: FMLServerAboutToStartEvent)
    {
        currentStage = ModuleStage.SERVER_ABOUT_TO_START
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Server About To Start start")
            module.serverAboutToStart(event)
            module.logger.debug("Server About To Start complete")
        }
    }

    // Server Starting Event means events will be loaded when Server is starting.
    fun onServerStarting(event: FMLServerStartingEvent)
    {
        currentStage = ModuleStage.SERVER_STARTING
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Server Starting start")
            module.serverStarting(event)
            module.logger.debug("Server Starting complete")
        }
    }

    // Server Started Event means events will be loaded when Server is started.
    fun onServerStarted(event: FMLServerStartedEvent)
    {
        currentStage = ModuleStage.SERVER_STARTED
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.logger.debug("Server Started start")
            module.serverStarted(event)
            module.logger.debug("Server Started complete")
        }
    }

    // Server Stopping Event means events will be loaded when Server is stopping.
    fun onServerStopping(event: FMLServerStoppingEvent)
    {
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.serverStopping(event)
        }
    }

    // Server Stopped Event means events will be loaded when Server is stopped.
    fun onServerStopped(event: FMLServerStoppedEvent)
    {
        for (module in loadedModules)
        {
            currentContainer = containers[getContainerId(module)]
            module.serverStopped(event)
        }
    }

    // endregion

    /**
     * Forward incoming IMC messages to each loaded module.
     *
     * @param messages The messages to forward.
     */
    fun processIMC(messages: ImmutableList<FMLInterModComms.IMCMessage>)
    {
        for (message in messages)
        {
            for (module in loadedModules)
            {
                if (module.processIMC(message))
                    break
            }
        }
    }

    /**
     * @param module The module to get the comment for.
     * @return       The comment for the module correspondenced configuration.
     */
    private fun getComment(module: CustomModule): String
    {
        val annotation = module.javaClass.getAnnotation(Module::class.java)
        val dependencies = module.dependencyUids
        val modDependencies = annotation.modDependencies
        return buildString(annotation.descriptions.length) {
            append(annotation.descriptions)
            if (dependencies.isNotEmpty())
                append("\nModule Dependencies: [ ${dependencies.joinToString(", ")} ] ")
            if (modDependencies.isNotEmpty())
                append("\nMod Dependencies: [ ${modDependencies.joinToString(", ")} ]")
        }
    }

    /**
     * @return The configuration instance for module configuration.
     */
    private fun getConfiguration(): Configuration
    {
        if (config == null)
        {
            config = Configuration(File(checkNotNull(configFolder), MODULE_CFG_FILE_NAME))
        }
        return config!!
    }

    /**
     * Discover and register all [ModuleContainer]s.
     *
     * @param dataTable The table containing the [ModuleContainer] data.
     */
    private fun discoverContainers(dataTable: ASMDataTable)
    {
        val dataSet = dataTable.getAll(ModuleContainer::class.java.canonicalName)
        for (data in dataSet)
        {
            try
            {
                val clazz = Class.forName(data.className)
                if (CustomModuleContainer::class.java.isAssignableFrom(clazz))
                {
                    registerContainer(clazz.getDeclaredConstructor().newInstance() as CustomModuleContainer)
                }
                else
                {
                    logger.error("Module Container class '{}' is not an instanceof correspondenced interface", clazz.name)
                }
            }
            catch (e: ReflectiveOperationException)
            {
                logger.error("Could not initialize Module Container '{}'", data.className, e)
            }
        }
    }

    /**
     * @param module The module to get the `containerId` for.
     * @return       The container id of the module.
     */
    private fun getContainerId(module: CustomModule): String
        = module.javaClass.getAnnotation(Module::class.java).containerId

    /**
     * Configure the modules according to the module correspondenced [Configuration].
     *
     * @param modules The modules to configure.
     */
    private fun configureModules(modules: Map<String, List<CustomModule>>)
    {
        val locale = Locale.getDefault()
        Locale.setDefault(Locale.ENGLISH)

        val toLoad = openLinkedSetOf<ResourceLocation>()
        val modulesToLoad = openRefLinkedSetOf<CustomModule>()

        val config = getConfiguration()
        config.load()
        config.addCustomCategoryComment(MODULE_CFG_CATEGORY_NAME,
            "Module configuration file. Can individually enable/disable modules from the mod and its addons")

        for (container in containers.values)
        {
            val containerModules = modules[container.id] ?: continue
            val coreModule = getCoreModule(containerModules) ?: throw IllegalStateException("Could not find Core Module for Module Container ${container.id}")
            val orderedModules = listOf(coreModule) + (containerModules - coreModule)
            for (module in orderedModules)
            {
                if (!isModuleEnabled(module))
                {
                    logger.debug("Module disabled: {}", module)
                    continue
                }
                val annotation = module.javaClass.getAnnotation(Module::class.java)
                toLoad.add(ResourceLocation(container.id, annotation.moduleId))
                modulesToLoad.add(module)
            }
        }

        do
        {
            val removed = modulesToLoad.removeAll {
                val dependencies = it.dependencyUids
                val missing = !toLoad.containsAll(dependencies)
                if (missing)
                {
                    val moduleId = it.javaClass.getAnnotation(Module::class.java).moduleId
                    toLoad.remove(ResourceLocation(moduleId))
                    logger.info("Module '{}' is missing at least one of Module dependencies: '{}', skipping loading...",
                        moduleId, dependencies)
                }
                return@removeAll missing
            }
        } while (removed)

        while (true)
        {
            val module = modulesToLoad.firstOrNull { sortedModules.keys.containsAll(it.dependencyUids) } ?: break
            val annotation = module.javaClass.getAnnotation(Module::class.java)
            sortedModules[ResourceLocation(annotation.containerId, annotation.moduleId)] = module
            modulesToLoad.remove(module)
        }

        loadedModules.addAll(sortedModules.values)

        if (config.hasChanged()) config.save()
        Locale.setDefault(locale)
    }

    /**
     * @param modules The list of modules possibly containing a Core Module.
     * @return        The first found Core Module.
     */
    private fun getCoreModule(modules: List<CustomModule>): CustomModule?
    {
        for (module in modules)
        {
            val annotation = module.javaClass.getAnnotation(Module::class.java)
            if (annotation.isCore) return module
        }
        return null
    }

    /**
     * @param dataTable The data table containing the module data.
     * @return          All [CustomModule] instances in sorted order by `containerId` and `moduleId`.
     */
    @Suppress("UNCHECKED_CAST")
    private fun getInstances(dataTable: ASMDataTable): List<CustomModule>
    {
        val dataSet = dataTable.getAll(Module::class.java.canonicalName)
        val instances = arrayListOf<CustomModule>()
        for (data in dataSet)
        {
            val moduleId = data.annotationInfo["moduleId"] as String
            val modDependencies = data.annotationInfo["modDependencies"] as? List<String>
            if (modDependencies == null || modDependencies.all { Loader.isModLoaded(it) })
            {
                try
                {
                    val clazz = Class.forName(data.className)
                    if (CustomModule::class.java.isAssignableFrom(clazz))
                    {
                        instances.add(clazz.getDeclaredConstructor().newInstance() as CustomModule)
                    }
                    else
                    {
                        logger.error("Module of class '{}' with id '{}' is not an instanceof Custom Module", clazz.name, moduleId)
                    }
                }
                catch (e: ReflectiveOperationException)
                {
                    logger.error("Could not initialize Module '{}'", moduleId, e)
                }
            }
            else
            {
                logger.info("Module '{}' is missing at least one of mod dependencies: '{}', skipping loading...", moduleId, modDependencies)
            }
        }
        return instances.sortedWith(
            compareBy<CustomModule> { it.javaClass.getAnnotation(Module::class.java).containerId }
                .thenBy { it.javaClass.getAnnotation(Module::class.java).moduleId })
    }

    /**
     * @param dataTable The data table containing the module data.
     * @return          The map of `containerId` to list of associated modules sorted by `moduleId`.
     */
    private fun getModules(dataTable: ASMDataTable): MutableMap<String, MutableList<CustomModule>>
    {
        val instances = getInstances(dataTable)
        val modules = openRefLinkedMapOf<String, MutableList<CustomModule>>()
        for (module in instances)
        {
            val annotation = module.javaClass.getAnnotation(Module::class.java)
            modules.getOrPut(annotation.containerId) { ArrayList() }.add(module)
        }
        return modules
    }
}
