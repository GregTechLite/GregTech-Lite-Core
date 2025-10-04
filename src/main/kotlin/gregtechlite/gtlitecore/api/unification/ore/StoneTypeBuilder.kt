package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Material
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.StoneType
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState

class StoneTypeBuilder(private val id: Int, private val name: String)
{

    companion object
    {

        @JvmStatic
        fun addStoneType(id: Int, name: String, dsl: StoneTypeBuilder.() -> Unit): StoneType
        {
            return StoneTypeBuilder(id, name).apply(dsl).build()
        }

    }

    var sound: SoundType? = null
    var prefix: OrePrefix? = null
    var material: Material? = null
    var state: (() -> IBlockState)? = null
    var predicate: ((IBlockState) -> Boolean)? = null
    var shouldBeDroppedAsItem: Boolean = false

    fun build(): StoneType
    {
        return StoneType(id, name, sound, prefix, material, state, predicate, shouldBeDroppedAsItem)
    }

}