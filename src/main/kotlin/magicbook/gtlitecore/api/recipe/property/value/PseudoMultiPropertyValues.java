package magicbook.gtlitecore.api.recipe.property.value;

import net.minecraft.block.state.IBlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class PseudoMultiPropertyValues {

    public ArrayList<IBlockState> validBlockStates;

    public final String blockGroupName;

    public PseudoMultiPropertyValues(@NotNull String blockGroupName,
                                     @NotNull IBlockState... validBlockStates) {
        this.validBlockStates = new ArrayList<>(Arrays.asList(validBlockStates));
        this.blockGroupName = blockGroupName;
    }

    public PseudoMultiPropertyValues(@NotNull String blockGroupName,
                                     @NotNull ArrayList<IBlockState> validBlockStates) {
        this.validBlockStates = validBlockStates;
        this.blockGroupName = blockGroupName;
    }

    public ArrayList<IBlockState> getValidBlockStates()
    {
        return this.validBlockStates;
    }

    public String getBlockGroupName()
    {
        return this.blockGroupName;
    }

    public void setValidBlockStates(ArrayList<IBlockState> validBlockStates)
    {
        this.validBlockStates = validBlockStates;
    }

}