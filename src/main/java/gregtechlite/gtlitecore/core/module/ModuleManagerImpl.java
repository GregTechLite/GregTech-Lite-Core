package gregtechlite.gtlitecore.core.module;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.morphismmc.morphismlib.util.SidedLogger;
import gregtechlite.gtlitecore.api.module.CustomModule;
import gregtechlite.gtlitecore.api.module.CustomModuleContainer;
import gregtechlite.gtlitecore.api.module.ModuleManager;
import gregtechlite.gtlitecore.api.module.Module;
import gregtechlite.gtlitecore.api.module.ModuleContainer;
import gregtechlite.gtlitecore.api.module.ModuleStage;
import it.unimi.dsi.fastutil.objects.Object2ReferenceLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
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
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@Internal
public final class ModuleManagerImpl implements ModuleManager
{

    public static final ModuleManagerImpl instance = new ModuleManagerImpl();

    private final Logger logger = new SidedLogger(MOD_ID + "-module-loader");

    // Module configuration file infos in default configuration folder.
    private static final String MODULE_CFG_FILE_NAME = "modules.cfg";
    private static final String MODULE_CFG_CATEGORY_NAME = "modules";
    private static File configFolder;

    // Stored cache of modules and module containers.
    private Map<String, CustomModuleContainer> containers = new LinkedHashMap<>();
    private final Map<ResourceLocation, CustomModule> sortedModules = new LinkedHashMap<>();
    private final Set<CustomModule> loadedModules = new LinkedHashSet<>();

    // Current module container and stage.
    private CustomModuleContainer currentContainer;
    private ModuleStage currentStage = ModuleStage.C_SETUP;

    private Configuration config;

    public ModuleManagerImpl getInstance()
    {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isModuleEnabled(@NotNull ResourceLocation namespace)
    {
        return sortedModules.containsKey(namespace);
    }

    public boolean isModuleEnabled(@NotNull CustomModule module)
    {
        Module annotation = module.getClass().getAnnotation(Module.class);
        String comment = getComment(module);
        String propertyKey = annotation.containerId() + ":" + annotation.moduleId();
        Property property = getConfiguration().get(MODULE_CFG_CATEGORY_NAME, propertyKey, true, comment);
        return property.getBoolean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomModuleContainer getLoadedContainer()
    {
        return currentContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModuleStage getStage()
    {
        return currentStage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPassedStage(@NotNull ModuleStage stage)
    {
        return currentStage.ordinal() > stage.ordinal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerContainer(@NotNull CustomModuleContainer container)
    {
        if (currentStage != ModuleStage.C_SETUP)
        {
            logger.error("Failed to register ModuleContainer '{}', as Module loading has already begun", container);
            return;
        }
        Preconditions.checkNotNull(container);
        containers.put(container.getId(), container);
    }

    /**
     * Set up the {@code ModuleManager} class.
     *
     * @param dataTable The data table containing all of the {@code ModuleContainer} and {@code Module} classes.
     * @param configDir The directory containing the configuration directory.
     */
    public void setup(@NotNull ASMDataTable dataTable, @NotNull File configDir)
    {
        // Find and register all containers registered with annotation and then sorted them by container names.
        discoverContainers(dataTable);
        containers = containers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a,
                        Object2ReferenceLinkedOpenHashMap::new));

        currentStage = ModuleStage.M_SETUP;
        configFolder = new File(configDir, MOD_ID);

        Map<String, List<CustomModule>> modules = getModules(dataTable);
        configureModules(modules);

        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Registering Event Handlers");
            for (Class<?> eventClass : module.getEventBusSubscribers())
                MinecraftForge.EVENT_BUS.register(eventClass);
            for (Class<?> terrainGenClass : module.getTerrainGenBusSubscribers())
                MinecraftForge.TERRAIN_GEN_BUS.register(terrainGenClass);
            for (Class<?> oreGenClass : module.getOreGenBusSubscribers())
                MinecraftForge.ORE_GEN_BUS.register(oreGenClass);
        }
        currentContainer = null;
    }

    // region FML Lifecycle Events

    // Construction Event means events will be loaded when Mod is starting to loaded.
    public void onConstruction(@NotNull FMLConstructionEvent event)
    {
        currentStage = ModuleStage.CONSTRUCTION;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Construction start");
            module.construction(event);
            module.getLogger().debug("Construction complete");
        }
    }

    // Pre-Initialization Event means it will "Run before anything else".
    public void onPreInit(@NotNull FMLPreInitializationEvent event)
    {
        currentStage = ModuleStage.PRE_INIT;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Registering packets");
            module.registerPackets();
        }
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Pre-Init start");
            module.preInit(event);
            module.getLogger().debug("Pre-Init complete");
        }
    }

    // Initialization Event means it will "Do your mod setup", you should build whatever data structures you care about.
    public void onInit(@NotNull FMLInitializationEvent event)
    {
        currentStage = ModuleStage.INIT;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Init start");
            module.init(event);
            module.getLogger().debug("Init complete");
        }
    }

    // Post-Initialization event means it will "Handle interaction with other mods", you can complete your setup based
    // on this.
    public void onPostInit(@NotNull FMLPostInitializationEvent event)
    {
        currentStage = ModuleStage.POST_INIT;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Post-Init start");
            module.postInit(event);
            module.getLogger().debug("Post-Init complete");
        }
    }

    // Load Complete Event means events will be loaded when Mod is finish loaded.
    public void onLoadComplete(@NotNull FMLLoadCompleteEvent event)
    {
        currentStage = ModuleStage.LOAD_COMPLETE;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Load Complete start");
            module.loadComplete(event);
            module.getLogger().debug("Load Complete complete");
        }
    }

    // Server About To Start Event means events will be loaded before Server started.
    public void onServerAboutToStart(@NotNull FMLServerAboutToStartEvent event)
    {
        currentStage = ModuleStage.SERVER_ABOUT_TO_START;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Server About To Start start");
            module.serverAboutToStart(event);
            module.getLogger().debug("Server About To Start complete");
        }
    }

    // Server Starting Event means events will be loaded when Server is starting.
    public void onServerStarting(@NotNull FMLServerStartingEvent event)
    {
        currentStage = ModuleStage.SERVER_STARTING;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Server Starting start");
            module.serverStarting(event);
            module.getLogger().debug("Server Starting complete");
        }
    }

    // Server Started Event means events will be loaded when Server is started.
    public void onServerStarted(@NotNull FMLServerStartedEvent event)
    {
        currentStage = ModuleStage.SERVER_STARTED;
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Server Started start");
            module.serverStarted(event);
            module.getLogger().debug("Server Started complete");
        }
    }

    // Server Stopping Event means events will be loaded when Server is stopping.
    public void onServerStopping(@NotNull FMLServerStoppingEvent event)
    {
        for (CustomModule module : loadedModules)
        {
            currentContainer = containers.get(getContainerId(module));
            module.serverStopping(event);
        }
    }

    // Server Stopped Event means events will be loaded when Server is stopped.
    public void onServerStopped(@NotNull FMLServerStoppedEvent event)
    {
        for (CustomModule module : loadedModules)
        {
            this.currentContainer = containers.get(getContainerId(module));
            module.serverStopped(event);
        }
    }

    // endregion

    /**
     * Forward incoming IMC messages to each loaded module.
     *
     * @param messages The messages to forward.
     */
    public void processIMC(@Unmodifiable @NotNull ImmutableList<FMLInterModComms.IMCMessage> messages)
    {
        for (FMLInterModComms.IMCMessage message : messages)
        {
            for (CustomModule module : loadedModules)
            {
                if (module.processIMC(message))
                    break;
            }
        }
    }

    /**
     * @param module The module to get the comment for.
     * @return       The comment for the module correspondenced configuration.
     */
    private String getComment(@NotNull CustomModule module)
    {
        Module annotation = module.getClass().getAnnotation(Module.class);

        String comment = annotation.descriptions();
        Set<ResourceLocation> dependencies = module.getDependencyUids();
        if (!dependencies.isEmpty())
        {
            Iterator<ResourceLocation> iterator = dependencies.iterator();
            StringBuilder stringBuilder = new StringBuilder(comment);
            stringBuilder.append("\n");
            stringBuilder.append("Module Dependencies: [ ");
            stringBuilder.append(iterator.next());
            while (iterator.hasNext())
            {
                stringBuilder.append(", ").append(iterator.next());
            }
            stringBuilder.append(" ] ");
            comment = stringBuilder.toString();
        }
        String[] modDependencies = annotation.modDependencies();
        if (modDependencies != null && modDependencies.length > 0)
        {
            Iterator<String> iterator = Arrays.stream(modDependencies).iterator();
            StringBuilder stringBuilder = new StringBuilder(comment);
            stringBuilder.append("\n");
            stringBuilder.append("Mod Dependencies: [ ");
            stringBuilder.append(iterator.next());
            while (iterator.hasNext())
            {
                stringBuilder.append(", ").append(iterator.next());
            }
            stringBuilder.append(" ]");
            comment = stringBuilder.toString();
        }
        return comment;
    }

    /**
     * @return The configuration instance for module configuration.
     */
    @NotNull
    private Configuration getConfiguration()
    {
        if (config == null)
        {
            config = new Configuration(new File(configFolder, MODULE_CFG_FILE_NAME));
        }
        return config;
    }

    /**
     * Discover and register all {@code ModuleContainer}s.
     *
     * @param dataTable The table containing the {@code ModuleContainer} data.
     */
    private void discoverContainers(@NotNull ASMDataTable dataTable)
    {
        Set<ASMDataTable.ASMData> dataSet = dataTable.getAll(ModuleContainer.class.getCanonicalName());
        for (ASMDataTable.ASMData data : dataSet)
        {
            try
            {
                Class<?> clazz = Class.forName(data.getClassName());
                if (CustomModuleContainer.class.isAssignableFrom(clazz))
                {
                    registerContainer((CustomModuleContainer) clazz.newInstance());
                }
                else
                {
                    logger.error("Module Container class '{}' is not an instanceof correspondenced interface", clazz.getName());
                }
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception)
            {
                logger.error("Could not initialize Module Container '{}'", data.getClassName(), exception);
            }
        }
    }

    /**
     * @param module The module to get the {@code containerId} for.
     * @return       The container id of the module.
     */
    @NotNull
    private static String getContainerId(@NotNull CustomModule module)
    {
        Module annotation = module.getClass().getAnnotation(Module.class);
        return annotation.containerId();
    }

    /**
     * Configure the modules according to the module correspondenced {@link Configuration}.
     *
     * @param modules The modules to configure.
     */
    private void configureModules(Map<String, List<CustomModule>> modules)
    {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        Set<ResourceLocation> toLoad = new ObjectLinkedOpenHashSet<>();
        Set<CustomModule> modulesToLoad = new ReferenceLinkedOpenHashSet<>();

        Configuration config = getConfiguration();
        config.load();
        config.addCustomCategoryComment(MODULE_CFG_CATEGORY_NAME, "Module configuration file. "
                + "Can individually enable/disable modules from the mod and its addons");

        for (CustomModuleContainer container : containers.values())
        {
            String containerId = container.getId();
            List<CustomModule> containerModules = modules.get(containerId);
            CustomModule coreModule = getCoreModule(containerModules);
            if (coreModule == null)
            {
                throw new IllegalStateException("Could not find Core Module for Module Container " + containerId);
            }
            else
            {
                containerModules.remove(coreModule);
                containerModules.add(0, coreModule);
            }

            // Remove disabled modules and gather potential modules to load.
            Iterator<CustomModule> iterator = containerModules.iterator();
            while (iterator.hasNext())
            {
                CustomModule module = iterator.next();
                if (!isModuleEnabled(module))
                {
                    iterator.remove();
                    logger.debug("Module disabled: {}", module);
                    continue;
                }

                Module annotation = module.getClass().getAnnotation(Module.class);
                toLoad.add(new ResourceLocation(containerId, annotation.moduleId()));
                modulesToLoad.add(module);
            }
        }

        // Check any module dependencies.
        Iterator<CustomModule> iterator;
        boolean changed;
        do
        {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext())
            {
                CustomModule module = iterator.next();

                // Check module dependencies.
                Set<ResourceLocation> dependencies = module.getDependencyUids();
                if (!toLoad.containsAll(dependencies))
                {
                    iterator.remove();
                    changed = true;

                    Module annotation = module.getClass().getAnnotation(Module.class);
                    String moduleId = annotation.moduleId();
                    toLoad.remove(new ResourceLocation(moduleId));
                    logger.info("Module '{}' is missing at least one of Module dependencies: '{}', skipping loading...", moduleId, dependencies);
                }
            }
        } while (changed);

        // Sort modules by their module dependencies.
        do
        {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext())
            {
                CustomModule module = iterator.next();
                if (sortedModules.keySet().containsAll(module.getDependencyUids()))
                {
                    iterator.remove();

                    Module annotation = module.getClass().getAnnotation(Module.class);
                    sortedModules.put(new ResourceLocation(annotation.containerId(), annotation.moduleId()), module);
                    changed = true;
                    break;
                }
            }
        } while (changed);

        loadedModules.addAll(sortedModules.values());

        if (config.hasChanged())
        {
            config.save();
        }

        Locale.setDefault(locale);
    }

    /**
     * @param modules The list of modules possibly containing a Core Module.
     * @return        The first found Core Module.
     */
    @Nullable
    private static CustomModule getCoreModule(@NotNull List<CustomModule> modules)
    {
        for (CustomModule module : modules)
        {
            Module annotation = module.getClass().getAnnotation(Module.class);
            if (annotation.isCore())
            {
                return module;
            }
        }
        return null;
    }

    /**
     * @param dataTable The data table containing the module data.
     * @return          All {@link CustomModule} instances in sorted order by {@code containerId} and {@code moduleId}.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    private List<CustomModule> getInstances(@NotNull ASMDataTable dataTable)
    {
        Set<ASMDataTable.ASMData> dataSet = dataTable.getAll(Module.class.getCanonicalName());
        List<CustomModule> instances = new ArrayList<>();
        for (ASMDataTable.ASMData data : dataSet)
        {
            String moduleId = (String) data.getAnnotationInfo().get("moduleId");
            List<String> modDependencies = (List<String>) data.getAnnotationInfo().get("modDependencies");
            if (modDependencies == null || modDependencies.stream().allMatch(Loader::isModLoaded))
            {
                try
                {
                    Class<?> clazz = Class.forName(data.getClassName());
                    if (CustomModule.class.isAssignableFrom(clazz))
                    {
                        instances.add((CustomModule) clazz.getConstructor().newInstance());
                    }
                    else
                    {
                        logger.error("Module of class '{}' with id '{}' is not an instanceof Custom Module", clazz.getName(), moduleId);
                    }
                }
                catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException
                       | InvocationTargetException exception)
                {
                    logger.error("Could not initialize Module '{}'", moduleId, exception);
                }
            }
            else
            {
                logger.info("Module '{}' is missing at least one of mod dependencies: '{}', skipping loading...", moduleId, modDependencies);
            }
        }
        return instances.stream()
                .sorted(Comparator.comparing((module) -> module.getClass().getAnnotation(Module.class),
                        Comparator.comparing(Module::containerId).thenComparing(Module::moduleId)))
                .collect(Collectors.toList());
    }

    /**
     * @param dataTable The data table containing the module data.
     * @return          The map of {@code containerId} to list of associated modules sorted by {@code moduleId}.
     */
    @NotNull
    private Map<String, List<CustomModule>> getModules(@NotNull ASMDataTable dataTable)
    {
        List<CustomModule> instances = getInstances(dataTable);
        Map<String, List<CustomModule>> modules = new Object2ReferenceLinkedOpenHashMap<>();
        for (CustomModule module : instances)
        {
            Module annotation = module.getClass().getAnnotation(Module.class);
            modules.computeIfAbsent(annotation.containerId(), k -> new ArrayList<>()).add(module);
        }
        return modules;
    }

}
