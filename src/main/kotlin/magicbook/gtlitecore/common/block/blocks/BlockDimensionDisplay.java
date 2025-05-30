package magicbook.gtlitecore.common.block.blocks;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@Getter
public class BlockDimensionDisplay extends Block
{

    private final String dimension;

    public BlockDimensionDisplay(String dimension)
    {
        super(Material.ROCK);
        this.dimension = dimension;
    }

}
