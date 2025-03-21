package magicbook.gtlitecore.integration.top;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.integration.IntegrationSubModule;
import magicbook.gtlitecore.integration.top.provider.DelegatorInfoProvider;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Module(moduleId = GTLiteModules.MODULE_TOP,
        containerId = GTLiteValues.MODID,
        modDependencies = { "theoneprobe" },
        name = "GregTech Lite TOP Module",
        descriptions = "The One Probe (TOP) Module of GregTech Lite Core Mod.")
public class TheOneProbeModule extends IntegrationSubModule
{

    @Override
    public void init(FMLInitializationEvent event)
    {
        ITheOneProbe top = TheOneProbe.theOneProbeImp;
        top.registerProvider(new DelegatorInfoProvider());
    }

}
