@file:JvmName("MaterialBuilderDSL")
package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.unification.material.Material
import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.util.ResourceLocation

fun matCreator(id: Int, name: String, builder: Material.Builder.() -> Unit): Material
    = matCreator(id, MOD_ID, name, builder)

fun matCreator(id: Int, modid: String, name: String, builder: Material.Builder.() -> Unit): Material
    = matCreator(id, ResourceLocation(modid, name), builder)

fun matCreator(id: Int, location: ResourceLocation, builder: Material.Builder.() -> Unit): Material
    = Material.Builder(id, location).apply(builder).build()
