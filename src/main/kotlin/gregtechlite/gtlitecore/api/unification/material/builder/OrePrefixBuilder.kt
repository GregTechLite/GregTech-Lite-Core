package gregtechlite.gtlitecore.api.unification.material.builder

import crafttweaker.annotations.ZenRegister
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialIconType
import gregtech.api.unification.ore.OrePrefix
import org.jetbrains.annotations.Contract
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod
import java.util.function.Predicate
import java.util.function.Function

/**
 * Builder fo GTCEu [OrePrefix].
 *
 * Supported CraftTweaker operands in ZrS scripts with this class.
 *
 * @zenClass mods.gregtech.ore.OrePrefixBuilder
 */
@ZenClass("mods.gregtech.ore.OrePrefixBuilder")
@ZenRegister
class OrePrefixBuilder(private val name: String)
{

    companion object
    {
        @ZenMethod
        @Contract("_ -> new")
        @JvmStatic
        fun builder(name: String): OrePrefixBuilder = OrePrefixBuilder(name)
    }

    private var materialAmount: Long = -1
    private var material: Material? = null
    private var materialIconType: MaterialIconType? = null
    private var flags: Long = 0
    private var condition: Predicate<Material>? = null
    private var tooltips: Function<Material, List<String>>? = null
    
    fun materialAmount(amount: Long): OrePrefixBuilder
    {
        this.materialAmount = amount
        return this
    }
    
    fun material(material: Material?): OrePrefixBuilder
    {
        this.material = material
        return this
    }
    
    fun iconType(materialIconType: MaterialIconType?): OrePrefixBuilder
    {
        this.materialIconType = materialIconType
        return this
    }

    fun enableUnification(): OrePrefixBuilder
    {
        this.flags = OrePrefix.Flags.ENABLE_UNIFICATION
        return this
    }
    
    fun selfReferencing(): OrePrefixBuilder
    {
        this.flags = OrePrefix.Flags.SELF_REFERENCING
        return this
    }
    
    fun condition(condition: Predicate<Material>?): OrePrefixBuilder
    {
        this.condition = condition
        return this
    }
    
    fun tooltipBuilder(tooltips: Function<Material, List<String>>?): OrePrefixBuilder
    {
        this.tooltips = tooltips
        return this
    }
    
    @ZenMethod
    fun build(): OrePrefix
    {
        return if (tooltips != null)
        {
            OrePrefix(name, materialAmount, material, materialIconType, flags, condition, tooltips)
        }
        else
        {
            OrePrefix(name, materialAmount, material, materialIconType, flags, condition)
        }
    }

}