package gregtechlite.gtlitecore.api.unification.ore

import gregtech.api.unification.material.Material
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.StoneType
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState

class StoneTypeDSL(private val id: Int, private val name: String)
{

    companion object
    {

        fun of(id: Int, name: String, dsl: StoneTypeDSL.() -> Unit): StoneType
        {
            return StoneTypeDSL(id, name).apply(dsl).build()
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