package gregtechlite.gtlitecore.api.unification.material.builder

import crafttweaker.annotations.ZenRegister
import gregtech.api.unification.material.Material
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.StoneType
import net.minecraft.block.SoundType
import net.minecraft.block.state.IBlockState
import org.jetbrains.annotations.Contract
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

/**
 * Builder for GTCEu [StoneType].
 *
 * Supported CraftTweaker operands in ZrS scripts with this class.
 *
 * @zenClass mods.gregtech.ore.StoneTypeBuilder
 */
@ZenClass("mods.gregtech.ore.StoneTypeBuilder")
@ZenRegister
class StoneTypeBuilder(private val id: Int, private val name: String)
{

    companion object
    {

        @ZenMethod
        @Contract("_, _ -> new")
        @JvmStatic
        fun builder(id: Int, name: String) = StoneTypeBuilder(id, name)
    }

    private var soundType: SoundType? = null
    private var processingPrefix: OrePrefix? = null
    private var material: Material? = null
    private var stone: (() -> IBlockState)? = null
    private var predicate: ((IBlockState) -> Boolean)? = null
    private var shouldBeDroppedAsItem: Boolean = false

    fun sound(soundType: SoundType): StoneTypeBuilder
    {
        this.soundType = soundType
        return this
    }

    fun prefix(processingPrefix: OrePrefix): StoneTypeBuilder
    {
        this.processingPrefix = processingPrefix
        return this
    }

    fun material(mat: Material): StoneTypeBuilder
    {
        this.material = mat
        return this
    }

    fun state(stone: () -> IBlockState): StoneTypeBuilder
    {
        this.stone = stone
        return this
    }

    fun state(stone: IBlockState): StoneTypeBuilder
    {
        this.stone = { stone }
        return this
    }

    fun condition(predicate: (IBlockState) -> Boolean): StoneTypeBuilder
    {
        this.predicate = predicate
        return this
    }

    fun condition(predicate: Boolean): StoneTypeBuilder
    {
        this.predicate = { predicate }
        return this
    }

    fun dropAsItem(): StoneTypeBuilder
    {
        this.shouldBeDroppedAsItem = true
        return this
    }

    @ZenMethod
    fun build(): StoneType = StoneType(id, name, soundType, processingPrefix, material, stone, predicate, shouldBeDroppedAsItem)

}