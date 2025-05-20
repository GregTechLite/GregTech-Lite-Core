package magicbook.gtlitecore.api.gui.adapter;

import com.cleanroommc.modularui.utils.serialization.IByteBufAdapter;
import com.cleanroommc.modularui.utils.serialization.IByteBufDeserializer;
import com.cleanroommc.modularui.utils.serialization.IByteBufSerializer;
import com.cleanroommc.modularui.utils.serialization.IEquals;
import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Add hooks for {@link com.cleanroommc.modularui.utils.serialization.ByteBufAdapters}.
 * <p>
 * This class add {@code BigInteger} supported for {@code ByteBufAdapter}.
 */
public class GTByteBufAdapters
{

    public static final IByteBufAdapter<BigInteger> BIG_INT = makeAdapter(
            buffer -> new BigInteger(buffer.readByteArray()),
            (buffer, value) -> buffer.writeByteArray(value.toByteArray()));

    public static <T> IByteBufAdapter<T> makeAdapter(@NotNull IByteBufDeserializer<T> deserializer,
                                                     @NotNull IByteBufSerializer<T> serializer)
    {
        return makeAdapter(deserializer, serializer, IEquals.defaultTester());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> IByteBufAdapter<T> makeAdapter(@NotNull IByteBufDeserializer<T> deserializer,
                                                     @NotNull IByteBufSerializer<T> serializer,
                                                     @Nullable IEquals<T> equals)
    {
        final IEquals<T> tester = equals != null ? equals : IEquals.defaultTester();
        return new IByteBufAdapter() {

            @Override
            public T deserialize(PacketBuffer buffer) throws IOException
            {
                return deserializer.deserialize(buffer);
            }

            @Override
            public void serialize(PacketBuffer buffer, Object u) throws IOException
            {
                serializer.serialize(buffer, (T) u);
            }

            @Override
            public boolean areEqual(@NotNull Object t1, @NotNull Object t2)
            {
                return tester.areEqual((T) t1, (T) t2);
            }

        };

    }

}