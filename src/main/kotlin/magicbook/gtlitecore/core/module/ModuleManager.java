package magicbook.gtlitecore.core.module;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import magicbook.gtlitecore.api.module.IModule;
import magicbook.gtlitecore.api.module.IModuleContainer;
import magicbook.gtlitecore.api.module.IModuleManager;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.module.ModuleContainer;
import magicbook.gtlitecore.api.module.ModuleStage;
import magicbook.gtlitecore.api.utils.GTLiteValues;
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
import one.util.streamex.MoreCollectors;
import one.util.streamex.StreamEx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ModuleManager implements IModuleManager
{

    @Getter
    public static final ModuleManager instance = new ModuleManager();

    private final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " Module Loader");

    // Module configuration file infos in default configuration folder.
    private static final String MODULE_CFG_FILE_NAME = "modules.cfg";
    private static final String MODULE_CFG_CATEGORY_NAME = "modules";
    private static File configFolder;

    // Stored cache of modules and module containers.
    private Map<String, IModuleContainer> containers = new LinkedHashMap<>();
    private final Map<ResourceLocation, IModule> sortedModules = new LinkedHashMap<>();
    private final Set<IModule> loadedModules = new LinkedHashSet<>();

    // Current module container and stage.
    private IModuleContainer currentContainer;
    private ModuleStage currentStage = ModuleStage.C_SETUP;

    private Configuration config;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isModuleEnabled(ResourceLocation namespace)
    {
        return this.sortedModules.containsKey(namespace);
    }

    public boolean isModuleEnabled(IModule module)
    {
        Module annotation = module.getClass().getAnnotation(Module.class);
        String comment = this.getComment(module);
        String propertyKey = annotation.containerId() + ":" + annotation.moduleId();
        Property property = this.getConfiguration().get(MODULE_CFG_CATEGORY_NAME,
                propertyKey, true, comment);
        return property.getBoolean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IModuleContainer getLoadedContainer()
    {
        return this.currentContainer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModuleStage getStage()
    {
        return this.currentStage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPassedStage(ModuleStage stage)
    {
        return this.currentStage.ordinal() > stage.ordinal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerContainer(IModuleContainer container)
    {
        if (this.currentStage != ModuleStage.C_SETUP)
        {
            this.logger.error("Failed to register ModuleContainer {},"
                    + " as Module loading has already begun", container);
            return;
        }
        Preconditions.checkNotNull(container);
        this.containers.put(container.getId(), container);
    }

    public void setup(ASMDataTable dataTable, File configDir)
    {
        // Find and register all containers registered with annotation and then sorted
        // them by container names.
        this.discoverContainers(dataTable);
        this.containers = StreamEx.of(this.containers.entrySet())
                .sorted(Map.Entry.comparingByKey())
                .groupingBy(Map.Entry::getKey, LinkedHashMap::new,
                        MoreCollectors.mapping(Map.Entry::getValue, Collectors.toList()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().get(0),
                        (k, v) -> k, LinkedHashMap::new));

        this.currentStage = ModuleStage.M_SETUP;
        configFolder = new File(configDir, GTLiteValues.MODID);

        Map<String, List<IModule>> modules = this.getModules(dataTable);
        this.configureModules(modules);
        StreamEx.of(this.loadedModules)
                .peek(module -> this.currentContainer = this.containers.get(getContainerId(module)))
                .peek(module -> module.getLogger().debug("Registering Event Handlers"))
                .forEach(module -> {
                    StreamEx.of(module.getEventBusSubscribers())
                            .forEach(MinecraftForge.EVENT_BUS::register);
                    StreamEx.of(module.getTerrainGenBusSubscribers())
                            .forEach(MinecraftForge.TERRAIN_GEN_BUS::register);
                    StreamEx.of(module.getOreGenBusSubscribers())
                            .forEach(MinecraftForge.ORE_GEN_BUS::register);
                });
    }

    /* -------------------------------- FML Life cycle Events -------------------------------- */
    // Construction Event means events will be loaded when Mod is starting to loaded.
    public void onConstruction(FMLConstructionEvent event)
    {
        this.currentStage = ModuleStage.CONSTRUCTION;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = containers.get(getContainerId(module));
            module.getLogger().debug("Construction start");
            module.construction(event);
            module.getLogger().debug("Construction complete");
        }
    }

    // Pre-Initialization Event means it will "Run before anything else".
    public void onPreInit(FMLPreInitializationEvent event)
    {
        this.currentStage = ModuleStage.PRE_INIT;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Registering packets");
            module.registerPackets();
        }
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Pre-Init start");
            module.preInit(event);
            module.getLogger().debug("Pre-Init complete");
        }
    }

    // Initialization Event means it will "Do your mod setup",
    // you should build whatever data structures you care about.
    public void onInit(FMLInitializationEvent event)
    {
        this.currentStage = ModuleStage.INIT;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Init start");
            module.init(event);
            module.getLogger().debug("Init complete");
        }
    }

    // Post-Initialization event means it will "Handle interaction with other mods",
    // you can complete your setup based on this.
    public void onPostInit(FMLPostInitializationEvent event)
    {
        this.currentStage = ModuleStage.POST_INIT;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Post-Init start");
            module.postInit(event);
            module.getLogger().debug("Post-Init complete");
        }
    }

    // Load Complete Event means events will be loaded when Mod is finish loaded.
    public void onLoadComplete(FMLLoadCompleteEvent event)
    {
        currentStage = ModuleStage.LOAD_COMPLETE;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Load Complete start");
            module.loadComplete(event);
            module.getLogger().debug("Load Complete complete");
        }
    }

    // Server About To Start Event means events will be loaded before Server started.
    public void onServerAboutToStart(FMLServerAboutToStartEvent event)
    {
        this.currentStage = ModuleStage.SERVER_ABOUT_TO_START;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Server About To Start start");
            module.serverAboutToStart(event);
            module.getLogger().debug("Server About To Start complete");
        }
    }

    // Server Starting Event means events will be loaded when Server is starting.
    public void onServerStarting(FMLServerStartingEvent event)
    {
        this.currentStage = ModuleStage.SERVER_STARTING;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Server Starting start");
            module.serverStarting(event);
            module.getLogger().debug("Server Starting complete");
        }
    }

    // Server Started Event means events will be loaded when Server is started.
    public void onServerStarted(FMLServerStartedEvent event)
    {
        this.currentStage = ModuleStage.SERVER_STARTED;
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.getLogger().debug("Server Started start");
            module.serverStarted(event);
            module.getLogger().debug("Server Started complete");
        }
    }

    // Server Stopping Event means events will be loaded when Server is stopping.
    public void onServerStopping(FMLServerStoppingEvent event)
    {
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.serverStopping(event);
        }
    }

    // Server Stopped Event means events will be loaded when Server is stopped.
    public void onServerStopped(FMLServerStoppedEvent event)
    {
        for (IModule module : this.loadedModules)
        {
            this.currentContainer = this.containers.get(getContainerId(module));
            module.serverStopped(event);
        }
    }
    /* --------------------------------------------------------------------------------------- */
    public void processIMC(ImmutableList<FMLInterModComms.IMCMessage> messages)
    {
        for (FMLInterModComms.IMCMessage message : messages)
        {
            for (IModule module : loadedModules)
            {
                if (module.processIMC(message))
                    break;
            }
        }
    }

    private String getComment(IModule module)
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
            Iterator<String> iterator = StreamEx.of(modDependencies).iterator();
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

    private Configuration getConfiguration()
    {
        if (this.config == null)
        {
            this.config = new Configuration(new File(configFolder, MODULE_CFG_FILE_NAME));
        }
        return this.config;
    }

    private void discoverContainers(ASMDataTable dataTable)
    {
        String containerName = ModuleContainer.class.getCanonicalName();
        Set<ASMDataTable.ASMData> dataSet = dataTable.getAll(containerName);
        for (ASMDataTable.ASMData data : dataSet)
        {
            try
            {
                Class<?> clazz = Class.forName(data.getClassName());
                this.registerContainer((IModuleContainer) clazz.newInstance());
            }
            catch (ClassNotFoundException
                   | IllegalAccessException
                   | InstantiationException exception)
            {
                this.logger.error("Could not initialize ModuleContainer " + data.getClassName(), exception);
            }
        }
    }

    private static String getContainerId(IModule module)
    {
        Module annotation = module.getClass().getAnnotation(Module.class);
        return annotation.containerId();
    }

    private void configureModules(Map<String, List<IModule>> modules)
    {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        Set<ResourceLocation> toLoad = new LinkedHashSet<>();
        Set<IModule> modulesToLoad = new LinkedHashSet<>();

        Configuration config = getConfiguration();
        config.load();
        config.addCustomCategoryComment(MODULE_CFG_CATEGORY_NAME, "Module configuration file. "
                + "Can individually enable/disable modules from GregTech Lite Core.");

        for (IModuleContainer container : containers.values())
        {
            String containerId = container.getId();
            List<IModule> containerModules = modules.get(containerId);
            IModule coreModule = getCoreModule(containerModules);
            if (coreModule == null)
            {
                throw new IllegalStateException("Could not find CoreModule for ModuleContainer " + containerId);
            }
            else
            {
                containerModules.remove(coreModule);
                containerModules.add(0, coreModule);
            }
            // Remove disabled modules and gather potential modules to load.
            Iterator<IModule> iterator = containerModules.iterator();
            while (iterator.hasNext())
            {
                IModule module = iterator.next();
                if (!this.isModuleEnabled(module))
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

        Iterator<IModule> iterator;
        boolean changed;
        do
        {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext())
            {
                IModule module = iterator.next();
                Set<ResourceLocation> dependencies = module.getDependencyUids();
                if (!toLoad.containsAll(dependencies))
                {
                    iterator.remove();
                    changed = true;
                    Module annotation = module.getClass().getAnnotation(Module.class);
                    String moduleId = annotation.moduleId();
                    toLoad.remove(new ResourceLocation(moduleId));
                    logger.info("Module {} is missing at least one of Module dependencies: {}, skipping loading...", moduleId, dependencies);
                }
            }
        } while (changed);

        do
        {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext())
            {
                IModule module = iterator.next();
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

        this.loadedModules.addAll(this.sortedModules.values());

        if (config.hasChanged())
        {
            config.save();
        }

        Locale.setDefault(locale);
    }

    private static IModule getCoreModule(List<IModule> modules)
    {
        for (IModule module : modules)
        {
            Module annotation = module.getClass().getAnnotation(Module.class);
            if (annotation.isCore())
            {
                return module;
            }
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    private List<IModule> getInstances(ASMDataTable dataTable)
    {
        return StreamEx.of(dataTable.getAll(Module.class.getCanonicalName()))
                .map(data -> {
                    String moduleId = (String) data.getAnnotationInfo().get("moduleID");
                    List<String> modDependencies = (List<String>) data.getAnnotationInfo()
                            .get("modDependencies");
                    return new AbstractMap.SimpleEntry<>(data,
                            new AbstractMap.SimpleEntry<>(moduleId, modDependencies));
                })
                .filter(entry -> {
                    List<String> modDependencies = entry.getValue().getValue();
                    return modDependencies == null || modDependencies.stream()
                            .allMatch(Loader::isModLoaded);
                })
                .map(entry -> {
                    ASMDataTable.ASMData data = entry.getKey();
                    String moduleId = entry.getValue().getKey();
                    try
                    {
                        Class<?> clazz = Class.forName(data.getClassName());
                        return (IModule) clazz.newInstance();
                    }
                    catch (ClassNotFoundException
                           | IllegalAccessException
                           | InstantiationException exception)
                    {
                        this.logger.error("Could not initialize Module " + moduleId, exception);
                        return null;
                    }
                })
                .nonNull()
                .sorted((a, b) -> {
                    Module x = a.getClass().getAnnotation(Module.class);
                    Module y = b.getClass().getAnnotation(Module.class);
                    return (x.containerId() + ":" + x.moduleId())
                            .compareTo(y.containerId() + ":" + y.moduleId());
                })
                .toList();
    }

    private Map<String, List<IModule>> getModules(ASMDataTable dataTable)
    {
        return StreamEx.of(this.getInstances(dataTable))
                .groupingBy(module -> {
                    Module annotation = module.getClass().getAnnotation(Module.class);
                    return annotation.containerId();
                }, LinkedHashMap::new, Collectors.toList());
    }

}
