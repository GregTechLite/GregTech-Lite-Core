package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.WireRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WireRecipeHandler.class, remap = false)
public abstract class MixinWireRecipeHandler
{

    /**
     * Disabled {@code generateCableCovering()} for all wireGt orePrefixes.
     * <p>
     * Only generate wireGtSingle recipes, this method used to generate wiremill recipes.
     *
     * @see gregtechlite.gtlitecore.loader.recipe.handler.WireRecipeHandler
     */
    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void stopInit(CallbackInfo ci)
    {
        OrePrefix.wireGtSingle.addProcessingHandler(PropertyKey.WIRE, WireRecipeHandler::processWireSingle);
        ci.cancel();
    }

}
