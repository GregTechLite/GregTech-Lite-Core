@file:JvmName("LocatableTexturesKt")

package gregtechlite.gtlitecore.client.util

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
import gregtech.client.renderer.texture.cube.SidedCubeRenderer
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer
import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.util.ResourceLocation

fun simpleOverlay(location: ResourceLocation): SimpleOverlayRenderer = SimpleOverlayRenderer(location.toString())

fun simpleOverlay(path: String): SimpleOverlayRenderer = simpleOverlay(GTLiteMod.id(path))

fun simpleSidedCubeOverlay(location: ResourceLocation): SimpleSidedCubeRenderer = SimpleSidedCubeRenderer(location.toString())

fun simpleSidedCubeOverlay(path: String): SimpleSidedCubeRenderer = simpleSidedCubeOverlay(GTLiteMod.id(path))

fun sidedCubeOverlay(location: ResourceLocation): SidedCubeRenderer = SidedCubeRenderer(location.toString())

fun sidedCubeOverlay(path: String): SidedCubeRenderer = sidedCubeOverlay(GTLiteMod.id(path))

fun orientedOverlay(location: ResourceLocation): OrientedOverlayRenderer = OrientedOverlayRenderer(location.toString())

fun orientedOverlay(path: String): OrientedOverlayRenderer = orientedOverlay(GTLiteMod.id(path))