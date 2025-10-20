package gregtechlite.gtlitecore.client.renderer.handler

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

    @JvmField
    protected var renderEntity: EntityLivingBase? = null
    @JvmField
    protected var world: World? = null
    @JvmField
    protected var entityPos: BlockPos? = null

    override fun isAmbientOcclusion(): Boolean = true

    override fun isGui3d(): Boolean = false

    override fun getOverrides(): ItemOverrideList
    {
        return EntityCachingOverrideList { entity, world ->
            this.renderEntity = entity
            this.world = world
            if (entity != null) this.entityPos = entity.position
        }
    }

    override fun getTransforms(): IModelState? = this.state

    override fun onEntityTick(item: EntityItem?)
    {
        this.entityPos = item?.position
    }

    private class EntityCachingOverrideList(private val callback: EntityCallback) : ItemOverrideList(listOf())
    {

        override fun handleItemState(originalModel: IBakedModel,
                                     stack: ItemStack,
                                     world: World?,
                                     entity: EntityLivingBase?): IBakedModel
        {
            this.callback.onEntityStuffs(entity, world)
            return super.handleItemState(originalModel, stack, world, entity)
        }

    }

}