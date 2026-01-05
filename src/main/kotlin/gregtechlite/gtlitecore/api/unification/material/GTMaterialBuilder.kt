package gregtechlite.gtlitecore.api.unification.material

import com.morphismmc.morphismlib.util.I18nUtil
import gregtech.api.unification.Element
import gregtech.api.unification.Elements
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlag
import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.util.ResourceLocation

object GTMaterialBuilder
{

    // region Material Builder

    /**
     * Add a GTCEu format material with its properties.
     *
     * @param id      The internal id of the `materialRegistry` in the mod.
     * @param name    The name of the material, will be used as translation key and ore dictionary.
     * @param builder The Material Builder DSL, it is the instead of GTCEu [Material.Builder].
     */
    @JvmStatic
    fun addMaterial(id: Int, name: String, builder: Material.Builder.() -> Unit): Material
            = addMaterial(id, MOD_ID, name, builder)

    /**
     * Add a GTCEu format material with its properties.
     *
     * If the material required custom namespace location, then should use this method.
     *
     * @param id      The internal id of the `materialRegistry` in the mod.
     * @param modid   The namespace mod id which the material in.
     * @param name    The name of the material, will be used as translation key and ore dictionary.
     * @param builder The Material Builder DSL, it is the instead of GTCEu [Material.Builder].
     */
    @JvmStatic
    fun addMaterial(id: Int, modid: String, name: String, builder: Material.Builder.() -> Unit): Material
            = addMaterial(id, ResourceLocation(modid, name), builder)

    /**
     * Add a GTCEu format material with its properties.
     *
     * If the material required custom namespace location, then should use this method.
     *
     * @param id       The internal id of the `materialRegistry` in the mod.
     * @param location The [ResourceLocation] of the mod, if the material should in this mod, then used its location.
     * @param builder  The Material Builder DSL, it is the instead of GTCEu [Material.Builder].
     */
    @JvmStatic
    fun addMaterial(id: Int, location: ResourceLocation, builder: Material.Builder.() -> Unit): Material
            = Material.Builder(id, location).apply(builder).build()

    // endregion

    // region Material Flags Builder

    /**
     * Add a GTCEu format material flag with its properties.
     *
     * @param name    The name of the material flag.
     * @param builder The Material Flag Builder DSL, itt is the instead of GTCEu [MaterialFlag.Builder].
     */
    @JvmStatic
    fun addMaterialFlag(name: String, builder: MaterialFlag.Builder.() -> Unit): MaterialFlag
            = MaterialFlag.Builder(name).apply(builder).build()

    // endregion

    // region Element Builder

    @JvmStatic
    fun addElement(name: String, builder: ElementBuilder.() -> Unit): Element
            = ElementBuilder(name).apply(builder).build()

    @JvmStatic
    fun addElement(name: String, symbol: String, builder: ElementBuilder.() -> Unit): Element
            = ElementBuilder(name, symbol).apply(builder).build()

    class ElementBuilder(private var name: String? = null,
                         private var symbol: String? = null)
    {

        var protons: Long = 0L
        var neutrons: Long = 0L
        var halfLifeTime: Double = -1.0
        var decayTo: String? = null
        var isIsotope: Boolean = false

        constructor(name: String? = null) : this(name, null)

        fun atomicProp(protons: Long, neutrons: Long): ElementBuilder
        {
            this.protons = protons
            this.neutrons = neutrons
            return this
        }

        fun decayable(decayTo: String, halfLifeTime: Double = -1.0): ElementBuilder
        {
            this.decayTo = decayTo
            this.halfLifeTime = halfLifeTime
            return this
        }

        fun decayable(decayTo: Material, halfLifeTime: Double = -1.0): ElementBuilder
        {
            this.decayTo = decayTo.chemicalFormula
            this.halfLifeTime = halfLifeTime
            return this
        }

        fun isotope(): ElementBuilder
        {
            this.isIsotope = true
            return this
        }

        fun description(translationKey: String, defaultKey: String = translationKey): ElementBuilder
        {
            this.symbol = I18nUtil.format(translationKey, defaultKey)
            return this
        }

        fun build(): Element
        {
            return Elements.add(protons, neutrons, halfLifeTime, decayTo, name, symbol, isIsotope)
        }

    }

    // endregion

}


