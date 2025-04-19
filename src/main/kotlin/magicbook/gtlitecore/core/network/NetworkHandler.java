package magicbook.gtlitecore.core.network;

import io.netty.buffer.Unpooled;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.module.ModuleStage;
import magicbook.gtlitecore.api.network.IClientExecutor;
import magicbook.gtlitecore.api.network.INetworkHandler;
import magicbook.gtlitecore.api.network.INetworkPacket;
import magicbook.gtlitecore.api.network.IServerExecutor;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.CoreModule;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class NetworkHandler implements INetworkHandler
{

    private static final NetworkHandler INSTANCE = new NetworkHandler();

    private final FMLEventChannel channel;
    private final PacketHandler packetHandler;

    private NetworkHandler()
    {
        this.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(GTLiteValues.MODID);
        this.channel.register(this);
        this.packetHandler = PacketHandler.getInstance();
    }

    public static INetworkHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void registerPacket(Class<? extends INetworkPacket> packetClass)
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register packet {} as packet registration has ended!", packetClass.getName());
            return;
        }

        boolean hasServerExecutor = IServerExecutor.class.isAssignableFrom(packetClass);
        boolean hasClientExecutor = IClientExecutor.class.isAssignableFrom(packetClass);
        if (hasServerExecutor && hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet {}, as it is both a Server and Client executor! Only one allowed. Skipping...", packetClass.getName());
            return;
        }
        if (!hasServerExecutor && !hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet {}, as it does not have an executor! Must have either IServerExecutor OR IClientExecutor. Skipping...", packetClass.getName());
            return;
        }
        packetHandler.registerPacket(packetClass);
    }

    @Override
    public void sendToAll(INetworkPacket packet)
    {
        this.channel.sendToAll(toFMLPacket(packet));
    }

    @Override
    public void sendTo(INetworkPacket packet, EntityPlayerMP player)
    {
        this.channel.sendTo(toFMLPacket(packet), player);
    }

    @Override
    public void sendToAllAround(INetworkPacket packet, NetworkRegistry.TargetPoint point)
    {
        this.channel.sendToAllAround(toFMLPacket(packet), point);
    }

    @Override
    public void sendToAllTracking(INetworkPacket packet, NetworkRegistry.TargetPoint point)
    {
        this.channel.sendToAllTracking(toFMLPacket(packet), point);
    }

    @Override
    public void sendToAllTracking(INetworkPacket packet, Entity entity)
    {
        this.channel.sendToAllTracking(toFMLPacket(packet), entity);
    }

    @Override
    public void sendToDimension(INetworkPacket packet, int dimensionId)
    {
        this.channel.sendToDimension(toFMLPacket(packet), dimensionId);
    }

    @Override
    public void sendToServer(INetworkPacket packet)
    {
        this.channel.sendToServer(toFMLPacket(packet));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientPacket(@NotNull FMLNetworkEvent.ClientCustomPacketEvent event) throws Exception
    {
        INetworkPacket packet = toGTPacket(event.getPacket());
        if (IClientExecutor.class.isAssignableFrom(packet.getClass()))
        {
            IClientExecutor executor = (IClientExecutor) packet;
            NetHandlerPlayClient handler = (NetHandlerPlayClient) event.getHandler();
            IThreadListener threadListener = FMLCommonHandler.instance().getWorldThread(handler);
            if (threadListener.isCallingFromMinecraftThread())
                executor.executeClient(handler);
            else
                threadListener.addScheduledTask(() -> executor.executeClient(handler));
        }
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.@NotNull ServerCustomPacketEvent event) throws Exception
    {
        INetworkPacket packet = toGTPacket(event.getPacket());
        if (IServerExecutor.class.isAssignableFrom(packet.getClass()))
        {
            IServerExecutor executor = (IServerExecutor) packet;
            NetHandlerPlayServer handler = (NetHandlerPlayServer) event.getHandler();
            IThreadListener threadListener = FMLCommonHandler.instance().getWorldThread(handler);
            if (threadListener.isCallingFromMinecraftThread())
                executor.executeServer(handler);
            else
                threadListener.addScheduledTask(() -> executor.executeServer(handler));
        }
    }

    @NotNull
    private FMLProxyPacket toFMLPacket(@NotNull INetworkPacket packet)
    {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeVarInt(packetHandler.getPacketId(packet.getClass()));
        packet.encode(buf);
        return new FMLProxyPacket(buf, GTLiteValues.MODID);
    }

    @NotNull
    private INetworkPacket toGTPacket(@NotNull FMLProxyPacket proxyPacket) throws NoSuchMethodException, InvocationTargetException,
                                                                                           InstantiationException, IllegalAccessException
    {
        PacketBuffer payload = (PacketBuffer) proxyPacket.payload();
        Class<? extends INetworkPacket> clazz = packetHandler.getPacketClass(payload.readVarInt());
        INetworkPacket packet = clazz.getConstructor().newInstance();
        packet.decode(payload);
        if (payload.readableBytes() != 0)
            GTLiteLog.logger.error("NetworkHandler failed to finish reading packet with class {} and {} bytes remaining", clazz.getName(), payload.readableBytes());
        return packet;
    }

}
