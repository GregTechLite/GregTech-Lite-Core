package gregtechlite.gtlitecore.mixins.appeng;

import appeng.api.config.Settings;
import appeng.api.config.YesNo;
import appeng.api.networking.IGrid;
import appeng.container.implementations.ContainerInterfaceTerminal;
import appeng.helpers.DualityInterface;
import appeng.helpers.IInterfaceHost;
import appeng.util.Platform;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtechlite.gtlitecore.mixins.hooks.MachineHostHooks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Mixin(ContainerInterfaceTerminal.class)
public abstract class MixinContainerInterfaceTerminal
{
    @SuppressWarnings("rawtypes")
    @Shadow(remap = false)
    private @Final Map diList;

    @SuppressWarnings("rawtypes")
    @Shadow(remap = false)
    private @Final Map byId;

    @Shadow(remap = false)
    private IGrid grid;

    @Shadow(remap = false)
    private NBTTagCompound data;

    @Unique
    private int gtlitecore$extraCount;

    @Unique
    private List<IInterfaceHost> gtlitecore$lastHosts;

    @Unique
    private static boolean gtlitecore$reflectionDone;

    @Unique
    private static boolean gtlitecore$reflectionReady;

    @Unique
    private static Constructor<?> gtlitecore$invTrackerConstructor;

    @Unique
    private static Field gtlitecore$invTrackerWhich;

    @Unique
    private static Field gtlitecore$invTrackerServer;

    @Unique
    private static Method gtlitecore$addItemsMethod;

    @Unique
    private static boolean gtlitecore$ensureReflection()
    {
        if (gtlitecore$reflectionDone)
            return gtlitecore$reflectionReady;
        gtlitecore$reflectionDone = true;

        try
        {
            Class<?> invTrackerClass = gtlitecore$getInvTrackerClass();

            final Constructor<?> constructor = invTrackerClass.getDeclaredConstructor(
                    DualityInterface.class, IItemHandler.class, String.class);
            constructor.setAccessible(true);

            final Field which = invTrackerClass.getDeclaredField("which");
            which.setAccessible(true);

            final Field server = invTrackerClass.getDeclaredField("server");
            server.setAccessible(true);

            final Method addItems = ContainerInterfaceTerminal.class.getDeclaredMethod("addItems",
                    NBTTagCompound.class, invTrackerClass, int.class, int.class);
            addItems.setAccessible(true);

            gtlitecore$invTrackerConstructor = constructor;
            gtlitecore$invTrackerWhich = which;
            gtlitecore$invTrackerServer = server;
            gtlitecore$addItemsMethod = addItems;
            gtlitecore$reflectionReady = true;

            return true;
        }
        catch (final Throwable t)
        {
            gtlitecore$reflectionReady = false;
            return false;
        }
    }

    @Unique
    private static @NotNull Class<?> gtlitecore$getInvTrackerClass()
    {
        Class<?> invTrackerClass = null;
        for (final Class<?> nested : ContainerInterfaceTerminal.class.getDeclaredClasses())
        {
            if ("InvTracker".equals(nested.getSimpleName()))
            {
                invTrackerClass = nested;
                break;
            }
        }

        if (invTrackerClass == null)
            throw new IllegalStateException("InvTracker nested class not found in ContainerInterfaceTerminal");

        return invTrackerClass;
    }

    @Unique
    private List<IInterfaceHost> gtlitecore$collectHosts()
    {
        return MachineHostHooks.collectHostByGrid(grid);
    }

    /**
     * @reason Registers our hatches as fake interface hosts right after the vanilla host list was (re)built.
     */
    @SuppressWarnings("unchecked")
    @Inject(method = "regenList", remap = false,
            at = @At("TAIL"))
    private void appendExtraHosts(NBTTagCompound data, CallbackInfo ci)
    {
        final List<IInterfaceHost> hosts = gtlitecore$collectHosts();
        gtlitecore$lastHosts = hosts;
        gtlitecore$extraCount = 0;

        if (hosts.isEmpty()) return;


        if (!gtlitecore$ensureReflection()) return;

        int addedCount = 0;
        for (final IInterfaceHost host : hosts)
        {
            try
            {
                final DualityInterface dual = host.getInterfaceDuality();
                if (dual.getConfigManager().getSetting(Settings.INTERFACE_TERMINAL) != YesNo.YES)
                {
                    continue;
                }

                final Object tracker = gtlitecore$invTrackerConstructor.newInstance(dual, dual.getPatterns(), dual.getTermName());
                final long which = gtlitecore$invTrackerWhich.getLong(tracker);
                final IItemHandler server = (IItemHandler) gtlitecore$invTrackerServer.get(tracker);

                diList.put(host, tracker);
                byId.put(which, tracker);
                gtlitecore$addItemsMethod.invoke(this, data, tracker, 0, server.getSlots());

                addedCount++;
            }
            catch (final Throwable t)
            {
                throw new IllegalStateException("Failed to register an extra interface host", t);
            }
        }

        gtlitecore$extraCount = addedCount;
    }

    /**
     * @reason Forces a full {@code regenList} whenever the set of our hatches on the grid changed (added, removed
     *         or reordered). Vanilla per-slot pattern diffs are handled for free by the vanilla {@code else} branch,
     *         which iterates {@code diList} including our entries.
     */
    @Inject(method = "detectAndSendChanges",
            at = @At("HEAD"))
    private void ensureRegen(CallbackInfo ci)
    {
        if (grid == null || !Platform.isServer()) return;

        final List<IInterfaceHost> hosts = gtlitecore$collectHosts();
        if (!MachineHostHooks.areHostsEqual(hosts, gtlitecore$lastHosts))
        {
            gtlitecore$lastHosts = hosts;
            regenList(data);
        }
    }

    /**
     * @reason Vanilla {@code total} only counts {@code TileInterface} and {@code PartInterface} machines,
     *         so our extra entries would otherwise make {@code total != this.diList.size()} true every tick
     *         and force a regen.
     */
    @ModifyExpressionValue(method = "detectAndSendChanges",
                           at = @At(value = "INVOKE",
                                    target = "Ljava/util/Map;size()I",
                                    remap = false))
    private int adjustSize(int original)
    {
        return original - gtlitecore$extraCount;
    }

    @Shadow(remap = false)
    private void regenList(NBTTagCompound data) {}
}
