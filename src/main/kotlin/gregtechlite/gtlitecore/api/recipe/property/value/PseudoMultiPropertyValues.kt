package gregtechlite.gtlitecore.api.recipe.property.value

import net.minecraft.block.state.IBlockState

class PseudoMultiPropertyValues(val blockGroupName: String,
                                val validBlockStates: List<IBlockState>)
{

    constructor(blockGroupName: String,
                vararg validBlockStates: IBlockState) : this(blockGroupName, listOf(*validBlockStates))

}