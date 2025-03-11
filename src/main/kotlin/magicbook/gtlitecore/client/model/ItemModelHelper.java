package magicbook.gtlitecore.client.model;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ItemModelHelper
{

    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Block block)
    {
        StreamEx.of(block.getBlockState().getValidStates())
                .filter(state -> block.getRegistryName() != null)
                .mapToEntry(state -> new ModelResourceLocation(
                        Objects.requireNonNull(block.getRegistryName()),
                        StreamEx.of(state.getProperties().entrySet())
                                .sortedBy(entry -> entry.getKey().getName())
                                .map(entry -> entry.getKey().getName() + "="
                                        + getPropertyName(entry.getKey(), entry.getValue()))
                                .toListAndThen(props -> props.isEmpty() ? "normal" : String.join(",", props))
                ))
                .forKeyValue((state, modelResourceLocation) -> ModelLoader.setCustomModelResourceLocation(
                        Item.getItemFromBlock(block), block.getMetaFromState(state), modelResourceLocation
                ));
    }

    @SuppressWarnings("unchecked")
    protected static <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value)
    {
        return property.getName((T) value);
    }

}
