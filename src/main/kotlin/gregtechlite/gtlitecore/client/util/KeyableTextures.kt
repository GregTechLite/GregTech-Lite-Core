@file:JvmName("KeyableTextures")

package gregtechlite.gtlitecore.client.util

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer
import gregtech.client.renderer.texture.cube.SidedCubeRenderer
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer
import gregtechlite.gtlitecore.GTLiteMod
import net.minecraft.util.ResourceLocation

fun simpleOverlay(location: ResourceLocation): SimpleOverlayRenderer
{
    return SimpleOverlayRenderer(location.toString())
}

fun simpleOverlay(path: String): SimpleOverlayRenderer
{
    return simpleOverlay(GTLiteMod.id(path))
}

fun simpleSidedCubeOverlay(location: ResourceLocation): SimpleSidedCubeRenderer
{
    return SimpleSidedCubeRenderer(location.toString())
}

fun simpleSidedCubeOverlay(path: String): SimpleSidedCubeRenderer
{
    return simpleSidedCubeOverlay(GTLiteMod.id(path))
}

fun sidedCubeOverlay(location: ResourceLocation): SidedCubeRenderer
{
    return SidedCubeRenderer(location.toString())
}

fun sidedCubeOverlay(path: String): SidedCubeRenderer
{
    return sidedCubeOverlay(GTLiteMod.id(path))
}

fun orientedOverlay(location: ResourceLocation): OrientedOverlayRenderer
{
    return OrientedOverlayRenderer(location.toString())
}

fun orientedOverlay(path: String): OrientedOverlayRenderer
{
    return orientedOverlay(GTLiteMod.id(path))
}