package gregtechlite.gtlitecore.api.metatileentity

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import com.morphismmc.morphismlib.util.Unchecks
import gregtech.api.capability.GregtechCapabilities
import gregtech.api.capability.IEnergyContainer
import gregtech.api.gui.ModularUI
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.MetaTileEntityHolder
import gregtech.api.util.GTUtility.convertRGBtoOpaqueRGBA_CL
import gregtech.client.renderer.texture.Textures
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
import gregtechlite.gtlitecore.api.capability.Delegator
import gregtechlite.gtlitecore.api.extension.add
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler
import org.apache.commons.lang3.tuple.Pair

abstract class MetaTileEntityDelegator(metaTileEntityId: ResourceLocation,
                                       protected val capabilityFilter: (Capability<*>) -> Boolean,
                                       protected val baseColor: Int) : MetaTileEntity(metaTileEntityId), Delegator
{

    override fun <T> getCapability(capability: Capability<T?>, side: EnumFacing?): T?
    {
        val delegatedCapability = getDelegatedCapability<T?>(capability, side)
        return delegatedCapability ?: getDefaultCapability<T?>(capability, side)
    }

    protected fun <T> getDefaultCapability(capability: Capability<T?>, side: EnumFacing?): T?
    {
        return if (side != null && capabilityFilter(capability)
            && DefaultCapabilities.hasCapability(capability)
        )
            DefaultCapabilities.getCapability<T?>(capability)
        else
            super.getCapability<T?>(capability, side)
    }

    protected fun <T> getDelegatedCapability(capability: Capability<T?>?, side: EnumFacing?): T?
    {
        if (capability == null || !capabilityFilter(capability) || side == null) return null
        val delegatingFacing = getDelegatingFacing(side)
        if (delegatingFacing == null) return null
        val te = world.getTileEntity(pos.offset(delegatingFacing))
        if (te == null || (te is MetaTileEntityHolder && te.getMetaTileEntity() is Delegator)) return null
        return te.getCapability(capability, delegatingFacing.opposite)
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        val colouredPipeline = pipeline?.add(ColourMultiplier(
            convertRGBtoOpaqueRGBA_CL(this.paintingColorForRendering)))
        for (facing in EnumFacing.entries)
            Textures.renderFace(renderState, translation, colouredPipeline, facing,
            Cuboid6.full, this.baseTexture, BlockRenderLayer.CUTOUT_MIPPED)
    }

    @get:SideOnly(Side.CLIENT)
    protected val baseTexture: TextureAtlasSprite?
        get() = Textures.PIPE_SIDE

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.delegator.tooltip.non_recursion"))
    }

    @SideOnly(Side.CLIENT)
    override fun getParticleTexture(): Pair<TextureAtlasSprite?, Int?>
    {
        return Pair.of(this.baseTexture, paintingColorForRendering)
    }

    override fun getDefaultPaintingColor(): Int = this.baseColor

    override fun openGUIOnRightClick(): Boolean = false

    @Deprecated("Deprecated in Java")
    override fun createUI(entityPlayer: EntityPlayer?): ModularUI? = null

    object DefaultCapabilities
    {

        private val DEFAULT_CAPABILITIES = Object2ObjectArrayMap<Capability<*>?, Any?>()

        init
        {
            // Item
            addCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(object : ItemStackHandler(1)
                {

                    override fun insertItem(slot: Int,
                                            stack: ItemStack,
                                            simulate: Boolean): ItemStack = stack

                    override fun extractItem(slot: Int,
                                             amount: Int,
                                             simulate: Boolean): ItemStack = ItemStack.EMPTY

                })
            )

            // Fluid
            addCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
                CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(object : FluidTank(10000)
                {

                    override fun fill(resource: FluidStack?, doFill: Boolean): Int = 0

                    override fun drainInternal(maxDrain: Int, doDrain: Boolean): FluidStack? = null

                })
            )

            // GTEU
            addCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER,
                GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(IEnergyContainer.DEFAULT))
        }

        fun hasCapability(capability: Capability<*>): Boolean
        {
            return DEFAULT_CAPABILITIES.containsKey(capability)
        }

        fun <T> getCapability(capability: Capability<T?>): T?
        {
            return DEFAULT_CAPABILITIES.getOrDefault(capability, null)?.let { Unchecks.cast(it) }
        }

        fun <T> addCapability(capability: Capability<T?>, value: T)
        {
            DEFAULT_CAPABILITIES.put(capability, capability.cast(value))
        }

    }

}
