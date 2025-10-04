package gregtechlite.gtlitecore.api.unification.material

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
    fun addMaterialFlag(name: String, builder: MaterialFlag.Builder.() -> Unit): MaterialFlag
            = MaterialFlag.Builder(name).apply(builder).build()

    // endregion

}


