package gregtechlite.gtlitecore.core.network;

import io.netty.buffer.Unpooled;
import gregtechlite.gtlitecore.api.GTLiteAPI;
import gregtechlite.gtlitecore.api.module.ModuleStage;
import gregtechlite.gtlitecore.api.network.ClientExecutor;
import gregtechlite.gtlitecore.api.network.NetworkHandler;
import gregtechlite.gtlitecore.api.network.NetworkPacket;
import gregtechlite.gtlitecore.api.network.ServerExecutor;
import gregtechlite.gtlitecore.api.GTLiteLog;
import gregtechlite.gtlitecore.core.CoreModule;
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
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@Internal
public final class NetworkHandlerImpl implements NetworkHandler
{

    private static final NetworkHandlerImpl INSTANCE = new NetworkHandlerImpl();

    private final FMLEventChannel channel;
    private final PacketHandler packetHandler;

    private NetworkHandlerImpl()
    {
        this.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(MOD_ID);
        this.channel.register(this);
        this.packetHandler = PacketHandler.getInstance();
    }

    public static NetworkHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void registerPacket(Class<? extends NetworkPacket> packetClass)
    {
        if (GTLiteAPI.moduleManager.hasPassedStage(ModuleStage.PRE_INIT))
        {
            CoreModule.logger.error("Could not register packet {} as packet registration has ended!",
                    packetClass.getName());
            return;
        }

        boolean hasServerExecutor = ServerExecutor.class.isAssignableFrom(packetClass);
        boolean hasClientExecutor = ClientExecutor.class.isAssignableFrom(packetClass);
        if (hasServerExecutor && hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet {}, as it is both a Server and Client executor! Only one allowed. Skipping...",
                    packetClass.getName());
            return;
        }
        if (!hasServerExecutor && !hasClientExecutor)
        {
            CoreModule.logger.error("Could not register packet {}, as it does not have an executor! Must have either IServerExecutor OR IClientExecutor. Skipping...",
                    packetClass.getName());
            return;
        }
        packetHandler.registerPacket(packetClass);
    }

    @Override
    public void sendToAll(NetworkPacket packet)
    {
        this.channel.sendToAll(toFMLPacket(packet));
    }

    @Override
    public void sendTo(NetworkPacket packet, EntityPlayerMP player)
    {
        this.channel.sendTo(toFMLPacket(packet), player);
    }

    @Override
    public void sendToAllAround(NetworkPacket packet, NetworkRegistry.TargetPoint point)
    {
        this.channel.sendToAllAround(toFMLPacket(packet), point);
    }

    @Override
    public void sendToAllTracking(NetworkPacket packet, NetworkRegistry.TargetPoint point)
    {
        this.channel.sendToAllTracking(toFMLPacket(packet), point);
    }

    @Override
    public void sendToAllTracking(NetworkPacket packet, Entity entity)
    {
        this.channel.sendToAllTracking(toFMLPacket(packet), entity);
    }

    @Override
    public void sendToDimension(NetworkPacket packet, int dimensionId)
    {
        this.channel.sendToDimension(toFMLPacket(packet), dimensionId);
    }

    @Override
    public void sendToServer(NetworkPacket packet)
    {
        this.channel.sendToServer(toFMLPacket(packet));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientPacket(@NotNull FMLNetworkEvent.ClientCustomPacketEvent event) throws Exception
    {
        NetworkPacket packet = toGTPacket(event.getPacket());
        if (ClientExecutor.class.isAssignableFrom(packet.getClass()))
        {
            ClientExecutor executor = (ClientExecutor) packet;
            NetHandlerPlayClient handler = (NetHandlerPlayClient) event.getHandler();
            IThreadListener threadListener = FMLCommonHandler.instance().getWorldThread(handler);
            if (threadListener.isCallingFromMinecraftThread())
            {
                executor.executeClient(handler);
            }
            else
            {
                threadListener.addScheduledTask(() -> executor.executeClient(handler));
            }
        }
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.@NotNull ServerCustomPacketEvent event) throws Exception
    {
        NetworkPacket packet = toGTPacket(event.getPacket());
        if (ServerExecutor.class.isAssignableFrom(packet.getClass()))
        {
            ServerExecutor executor = (ServerExecutor) packet;
            NetHandlerPlayServer handler = (NetHandlerPlayServer) event.getHandler();
            IThreadListener threadListener = FMLCommonHandler.instance().getWorldThread(handler);
            if (threadListener.isCallingFromMinecraftThread())
            {
                executor.executeServer(handler);
            }
            else
            {
                threadListener.addScheduledTask(() -> executor.executeServer(handler));
            }
        }
    }

    @NotNull
    private FMLProxyPacket toFMLPacket(@NotNull NetworkPacket packet)
    {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeVarInt(packetHandler.getPacketId(packet.getClass()));
        packet.encode(buf);
        return new FMLProxyPacket(buf, MOD_ID);
    }

    @NotNull
    private NetworkPacket toGTPacket(@NotNull FMLProxyPacket proxyPacket) throws NoSuchMethodException,
                                                                                 InvocationTargetException,
                                                                                 InstantiationException,
                                                                                 IllegalAccessException
    {
        PacketBuffer payload = (PacketBuffer) proxyPacket.payload();
        Class<? extends NetworkPacket> clazz = packetHandler.getPacketClass(payload.readVarInt());
        NetworkPacket packet = clazz.getConstructor().newInstance();
        packet.decode(payload);
        if (payload.readableBytes() != 0)
        {
            GTLiteLog.logger.error("NetworkHandler failed to finish reading packet with class {} and {} bytes remaining",
                    clazz.getName(), payload.readableBytes());
        }
        return packet;
    }

}
