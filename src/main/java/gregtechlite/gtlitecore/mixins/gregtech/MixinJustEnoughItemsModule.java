package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.integration.jei.JustEnoughItemsModule;
import gregtechlite.gtlitecore.api.GTLiteValues;
import gregtechlite.gtlitecore.mixins.hooks.JEIRecipeGroup;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import mezz.jei.api.IModRegistry;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Comparator;

@Mixin(value = JustEnoughItemsModule.class, remap = false)
public abstract class MixinJustEnoughItemsModule
{
    @ModifyExpressionValue(method = "register",
                           at = @At(value = "INVOKE",
                                    target = "Lgregtech/api/metatileentity/registry/MTEManager;getRegistries()Ljava/util/Collection;"))
    private Collection<MTERegistry> gtlitecore$sortMTERegistries(Collection<MTERegistry> original)
    {
        ObjectList<MTERegistry> registries = new ObjectArrayList<>(original);
        registries.sort(Comparator.comparingInt((MTERegistry registry)
                -> registry.getModid().equals(GTValues.MODID) ? 0 : 1).thenComparing(MTERegistry::getModid));
        return registries;
    }

    @Inject(method = "register",
            at = @At("RETURN"))
    private void gtlitecore$registerRecipeGroups(IModRegistry registry, CallbackInfo ci)
    {
        if (Loader.isModLoaded(GTLiteValues.MOD_ID))
        {
            JEIRecipeGroup.recipeGroups.forEach(registry::addRecipeCatalyst);
        }
    }
}
