package magicbook.gtlitecore.integration.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.google.common.collect.ImmutableList;
import lombok.NoArgsConstructor;
import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.integration.IntegrationSubModule;
import net.minecraftforge.fml.common.Optional;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Optional.Interface(modid = "groovyscript",
                    iface = "com.cleanroommc.groovyscript.api.GroovyPlugin",
                    striprefs = true)
@NoArgsConstructor
@Module(moduleId = GTLiteModules.MODULE_GRS,
        containerId = GTLiteValues.MODID,
        modDependencies = { "groovyscript" },
        name = "GregTech Lite GroovyScript Module",
        descriptions = "GroovyScript (GrS) Module of GregTech Lite Core Mod.")
public class GroovyScriptModule extends IntegrationSubModule implements GroovyPlugin
{

    private static GroovyContainer<?> grsContainer;

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers()
    {
        return ImmutableList.of(GroovyScriptModule.class);
    }

    @Optional.Method(modid = "groovyscript")
    @Override
    public void onCompatLoaded(GroovyContainer<?> container)
    {
        grsContainer = container;
        // ...
    }

    @NotNull
    @Override
    public String getModId()
    {
        return GTLiteValues.MODID;
    }

    @NotNull
    @Override
    public String getContainerName()
    {
        return GTLiteValues.NAME;
    }


}
