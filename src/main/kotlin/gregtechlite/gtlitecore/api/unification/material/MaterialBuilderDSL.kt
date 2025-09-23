@file:JvmName("MaterialBuilderDSL")
package gregtechlite.gtlitecore.api.unification.material

import gregtech.api.unification.material.Material
import gregtechlite.gtlitecore.api.MOD_ID
import net.minecraft.util.ResourceLocation

fun addMaterial(id: Int, name: String, builder: Material.Builder.() -> Unit): Material
    = addMaterial(id, MOD_ID, name, builder)

fun addMaterial(id: Int, modid: String, name: String, builder: Material.Builder.() -> Unit): Material
    = addMaterial(id, ResourceLocation(modid, name), builder)

fun addMaterial(id: Int, location: ResourceLocation, builder: Material.Builder.() -> Unit): Material
    = Material.Builder(id, location).apply(builder).build()
