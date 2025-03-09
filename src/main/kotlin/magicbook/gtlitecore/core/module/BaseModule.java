package magicbook.gtlitecore.core.module;

import lombok.NoArgsConstructor;
import magicbook.gtlitecore.api.module.IModule;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
public abstract class BaseModule implements IModule
{

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public Set<ResourceLocation> getDependencyUids()
    {
        return Collections.singleton(GTLiteUtility.gtliteId("core"));
    }

}