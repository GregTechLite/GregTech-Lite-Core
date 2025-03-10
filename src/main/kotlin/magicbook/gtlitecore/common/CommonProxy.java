package magicbook.gtlitecore.common;

import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = GTLiteValues.MODID)
public class CommonProxy
{

    public void preLoad()
    {
        GTLiteRecipeMaps.postRecipeMaps();
    }

    @SubscribeEvent
    public static void registerItems(@NotNull RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Items...");
        GTLiteMetaItems.register();
    }

}
