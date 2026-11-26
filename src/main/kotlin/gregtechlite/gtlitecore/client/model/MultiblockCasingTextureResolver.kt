package gregtechlite.gtlitecore.client.model

import com.morphismmc.morphismlib.client.Games
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.ReTexturableCasingRegistry
import gregtechlite.gtlitecore.api.block.ReTexturableCasing
import gregtechlite.gtlitecore.api.collection.intOpenHashMapOf
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.collection.openHashSetOf
import gregtechlite.gtlitecore.api.s
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("unused")
@EventBusSubscriber(modid = MOD_ID, value = [Side.CLIENT])
@SideOnly(Side.CLIENT)
object MultiblockCasingTextureResolver
{
    private var tickCounter = 0

    private val resolvedCasings = intOpenHashMapOf<HashMap<BlockPos, Map<BlockPos, String>>>()

    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent)
    {
        if (event.phase != TickEvent.Phase.END) return
        if (++tickCounter < 2.s) return
        tickCounter = 0

        val world = Games.mc().world
        if (world == null)
        {
            resolvedCasings.clear()
            return
        }

        if (!GTLiteConfigHolder.client.useMultiblockCasingTextures) return

        val dimension = world.provider.dimension
        val controllers = resolvedCasings.getOrPut(dimension) { HashMap() }
        updateCasings(world, controllers)

        val casings = openHashMapOf<BlockPos, String>()
        controllers.values.forEach(casings::putAll)

        val changed = ReTexturableCasingRegistry.replaceAll(dimension, casings) ?: return
        changed.forEach { world.markBlockRangeForRenderUpdate(it, it) }
    }

    private fun updateCasings(world: World, controllers: MutableMap<BlockPos, Map<BlockPos, String>>)
    {
        val formedControllers = openHashSetOf<BlockPos>()

        val tileEntities = world.tickableTileEntities
        for (idx in tileEntities.indices)
        {
            val controller = (tileEntities[idx] as? IGregTechTileEntity)?.metaTileEntity
                as? MultiblockControllerBase ?: continue
            if (!controller.isStructureFormed) continue

            val controllerPos = controller.pos
            formedControllers.add(controllerPos)

            if (controllers.containsKey(controllerPos)) continue
            resolveCasings(controller, world)?.let { controllers[controllerPos] = it }
        }

        controllers.keys.retainAll(formedControllers)
    }

    private fun resolveCasings(controller: MultiblockControllerBase,
                               world: World): Map<BlockPos, String>?
    {
        val texture = runCatching { controller.getBaseTexture(null).particleSprite.iconName }.getOrNull()
            ?: return null
        val pattern = controller.structurePattern ?: run {
            controller.reinitializeStructurePattern()
            controller.structurePattern
        } ?: return null
        if (pattern.checkPatternFastAt(world, controller.pos, controller.frontFacing.opposite,
                controller.upwardsFacing, controller.allowsFlip()) == null) return null

        return pattern.cache.long2ObjectEntrySet()
            .filter { it.value.blockState.block is ReTexturableCasing }
            .associate { BlockPos.fromLong(it.longKey) to texture }
    }

    @SubscribeEvent
    fun onWorldUnload(event: WorldEvent.Unload)
    {
        if (!event.world.isRemote) return
        ReTexturableCasingRegistry.clear()
        resolvedCasings.remove(event.world.provider.dimension)
    }
}
