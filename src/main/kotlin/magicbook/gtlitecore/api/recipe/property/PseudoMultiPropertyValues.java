package magicbook.gtlitecore.api.recipe.property;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.block.state.IBlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class PseudoMultiPropertyValues
{

    public ArrayList<IBlockState> validBlockStates;

    public final String blockGroupName;

    public PseudoMultiPropertyValues(@NotNull String blockGroupName,
                                     @NotNull IBlockState... validBlockStates)
    {
        this.validBlockStates = new ArrayList<>(Arrays.asList(validBlockStates));
        this.blockGroupName = blockGroupName;
    }

    public PseudoMultiPropertyValues(@NotNull String blockGroupName,
                                     @NotNull ArrayList<IBlockState> validBlockStates)
    {
        this.validBlockStates = validBlockStates;
        this.blockGroupName = blockGroupName;
    }

}