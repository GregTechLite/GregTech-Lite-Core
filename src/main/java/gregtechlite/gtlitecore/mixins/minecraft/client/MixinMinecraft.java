package gregtechlite.gtlitecore.mixins.minecraft.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtechlite.gtlitecore.GTLiteMod;
import gregtechlite.gtlitecore.client.util.IconLoader;
import gregtechlite.gtlitecore.mixins.hooks.I18nHooks;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;

/**
 * Open a confirmation GUI when player want to exit game to ensure they want to do it.
 */
@SuppressWarnings("all")
@Implemented(at = "https://github.com/GTNewHorizons/NewHorizonsCoreMod/pull/729")
@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
    @Unique
    private boolean gtlitecore$isCloseRequested;

    @Unique
    private boolean gtlitecore$waitingDialogQuit;

    @Unique
    private boolean gtlitecore$loadedModpackIcon;

    /**
     * @author Magic_Sweepy
     * @reason Ask for confirmation before actually shutting down.
     */
    @Inject(method = "shutdown",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$confirmShutdown(CallbackInfo callbackInfo)
    {
        if (gtlitecore$isCloseRequested)
            return; // already confirmed, let the upstream body set "running" to false

        callbackInfo.cancel();
        if (gtlitecore$waitingDialogQuit)
            return;

        gtlitecore$waitingDialogQuit = true;
        new Thread(() ->
        {
            final JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);

            final URL resourceURL = IconLoader.class.getClassLoader()
                    .getResource("assets/gtlitecore/textures/icons/logo.png");
            final ImageIcon imageIcon = resourceURL == null ? null : new ImageIcon(resourceURL);

            final int result = JOptionPane.showConfirmDialog(frame,
                    I18nHooks.format(
                            "gtlitecore.tooltip.quit_message",
                            "Are you sure you want to exit the game?"),
                    I18nHooks.format(
                            "gtlitecore.tooltip.modpack_name",
                            "GregTech Lite"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon);

            if (result == JOptionPane.YES_OPTION)
            {
                gtlitecore$isCloseRequested = true;
                ((Minecraft) (Object) this).shutdown();
            }

            gtlitecore$waitingDialogQuit = false;
        }).start();
    }

    /**
     * @author Magic_Sweepy
     * @reason Change vanilla window icon to the modpack icon.
     */
    @Inject(method = "setWindowIcon", at = @At("HEAD"))
    private void gtlitecore$loadModpackIcon(CallbackInfo callbackInfo)
    {
        gtlitecore$loadedModpackIcon = IconLoader.setCustomIcon("assets/gtlitecore/textures/icons/logo.png");
    }

    /**
     * @author Magic_Sweepy
     * @reason When the icon was already applied by {@link IconLoader}, pretend we are on OSX so the upstream method
     *         skips its own {@code Display.setIcon} call, exactly like the previous overwrite did.
     */
    @ModifyExpressionValue(method = "setWindowIcon",
                           at = @At(value = "INVOKE",
                                    target = "Lnet/minecraft/util/Util;getOSType()Lnet/minecraft/util/Util$EnumOS;"))
    private Util.EnumOS gtlitecore$skipVanillaIconWhenLoaded(Util.EnumOS original)
    {
        return gtlitecore$loadedModpackIcon ? Util.EnumOS.OSX : original;
    }

    /**
     * @author Magic_Sweepy
     * @reason Feed the modpack logo to both vanilla icon streams.
     */
    @WrapOperation(method = "setWindowIcon",
                   at = @At(value = "INVOKE",
                            target = "Lnet/minecraft/client/resources/DefaultResourcePack;getInputStreamAssets(Lnet/minecraft/util/ResourceLocation;)Ljava/io/InputStream;"))
    private InputStream gtlitecore$modpackIconStream(DefaultResourcePack pack, ResourceLocation location,
                                                     Operation<InputStream> original)
    {
        return original.call(pack, GTLiteMod.id("icons/logo.png"));
    }
}
