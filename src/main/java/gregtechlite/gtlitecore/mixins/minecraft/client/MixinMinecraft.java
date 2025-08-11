package gregtechlite.gtlitecore.mixins.minecraft.client;

import gregtechlite.gtlitecore.GTLiteMod;
import gregtechlite.gtlitecore.api.translation.CommonI18n;
import gregtechlite.gtlitecore.api.GTLiteLog;
import gregtechlite.gtlitecore.client.util.IconLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 * Feature: Open a confirmation Gui when player want to exit game to ensure they want to do it; add modpack logo to the
 * vanilla game windows.
 * <p>
 * Like same function contents in <a href="https://github.com/GTNewHorizons/NewHorizonsCoreMod">NewHorizonsCoreMod</a>,
 * we shadowed the volatile param {@code running} to control the game thread on/off (in NHCore, devs used {@code shutdown}
 * shadow and injected {@code shutdown} method, we used shadow param and overwrite {@code shutdown} method).
 * <p>
 * This mod is on <a href="https://github.com/GTNewHorizons/NewHorizonsCoreMod/blob/master/LICENSE">GNU LGPL-3.0</a> License,
 * thanks for original authors of these codes, this class is just a high-version port of these codes.
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{

    @Shadow
    volatile boolean running;

    @Final
    @Shadow
    public DefaultResourcePack defaultResourcePack;

    @Unique
    private boolean gtlitecore$isCloseRequested;

    @Unique
    private boolean gtlitecore$waitingDialogQuit;

    @Unique
    private boolean gtlitecore$loadedModpackIcon;

    /**
     * @author Magic_Sweepy
     * @reason Replace original {@code shutdown()} from a simply boolean predicating to
     *         a confirmation checking with a new panel via Swing Gui.
     */
    @Overwrite
    public void shutdown()
    {
        if (gtlitecore$isCloseRequested)
            return;
        if (!gtlitecore$waitingDialogQuit)
        {
            gtlitecore$waitingDialogQuit = true;
            new Thread(() -> {
                final JFrame frame = new JFrame();
                frame.setAlwaysOnTop(true);
                final URL resourceURL = IconLoader.class.getClassLoader()
                        .getResource("assets/gtlitecore/textures/icons/logo.png");
                final ImageIcon imageIcon = resourceURL == null ? null : new ImageIcon(resourceURL);
                final int result = JOptionPane.showConfirmDialog(frame,
                        CommonI18n.format("gtlitecore.tooltip.quit_message", "Are you sure you want to exit the game?"),
                        CommonI18n.format("gtlitecore.tooltip.modpack_name", "GregTech Lite"),
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon);
                if (result == JOptionPane.YES_OPTION)
                {
                    gtlitecore$isCloseRequested = true;
                    running = false;
                }
                this.gtlitecore$waitingDialogQuit = false;
            }).start();
        }
    }

    /**
     * @author Magic_Sweepy
     * @reason Change vanilla window icon to the modpack icon.
     */
    @Overwrite
    private void setWindowIcon()
    {
        gtlitecore$loadedModpackIcon = IconLoader.setCustomIcon("assets/gtlitecore/textures/icons/logo.png");
        Util.EnumOS osType = gtlitecore$loadedModpackIcon ? Util.EnumOS.OSX : Util.getOSType();
        if (osType != Util.EnumOS.OSX)
        {
            InputStream inputStream = null, inputStream1 = null;
            try
            {
                inputStream = defaultResourcePack.getInputStreamAssets(GTLiteMod.id("icons/logo.png"));
                inputStream1 = defaultResourcePack.getInputStreamAssets(GTLiteMod.id("icons/logo.png"));
                if (inputStream != null && inputStream1 != null)
                    Display.setIcon(new ByteBuffer[] { readImageToBuffer(inputStream), readImageToBuffer(inputStream1) });
            }
            catch (IOException exception)
            {
                GTLiteLog.logger.error("Cannot set icons with invalid input stream assets", exception);
            }
            finally {
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(inputStream1);
            }

        }
    }

    @Shadow
    private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException
    {
        throw new AssertionError();
    }

}
