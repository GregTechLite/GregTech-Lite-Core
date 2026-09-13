package gregtechlite.gtlitecore.client.renderer.handler.item

import codechicken.lib.render.item.IItemRenderer
import gregtechlite.gtlitecore.client.renderer.EntityCallback
import gregtechlite.gtlitecore.client.renderer.EntityItemTickCallback
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.block.model.ItemOverrideList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.model.IModelState

abstract class PerspectiveAwareItemRenderer protected constructor(private val state: IModelState?) : IItemRenderer, EntityItemTickCallback
{
    protected var renderEntity: EntityLivingBase? = null
    protected var world: World? = null
    protected var entityPos: BlockPos? = null

    override fun isAmbientOcclusion(): Boolean = true

    override fun isGui3d(): Boolean = false

    override fun getOverrides(): ItemOverrideList = EntityCachingOverrideList { entity, worldIn ->
        renderEntity = entity
        world = worldIn
        entity?.let { entityPos = entity.position }
    }

    override fun getTransforms(): IModelState? = state

    override fun onEntityTick(item: EntityItem?)
    {
        entityPos = item?.position
    }

    private class EntityCachingOverrideList(private val callback: EntityCallback) : ItemOverrideList(listOf())
    {
        override fun handleItemState(originalModel: IBakedModel,
                                     stack: ItemStack,
                                     world: World?,
                                     entity: EntityLivingBase?): IBakedModel
        {
            callback.onEntityStuffs(entity, world)
            return super.handleItemState(originalModel, stack, world, entity)
        }
    }
}