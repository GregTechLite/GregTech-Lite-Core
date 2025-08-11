package gregtechlite.gtlitecore.common.metatileentity.storage

import codechicken.lib.colour.ColourRGBA
import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.unification.material.Material
import gregtech.api.util.GTUtility
import gregtechlite.gtlitecore.api.metatileentity.MetaTileEntityDelegator
import gregtechlite.gtlitecore.client.renderer.texture.custom.ExtenderRenderer
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import org.apache.commons.lang3.tuple.Pair
import org.jetbrains.annotations.Nullable

class MetaTileEntityExtender(metaTileEntityId: ResourceLocation,
                             capabilityFilter: (Capability<*>) -> Boolean,
                             private val renderer: ExtenderRenderer,
                             baseColor: Int) : MetaTileEntityDelegator(metaTileEntityId, capabilityFilter, baseColor)
{

    private var inputFacing: EnumFacing? = null

    constructor(metaTileEntityId: ResourceLocation,
                capFilter: (Capability<*>) -> Boolean,
                renderer: ExtenderRenderer,
                baseMaterial: Material) : this(metaTileEntityId, capFilter, renderer, baseMaterial.materialRGB)

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityExtender(metaTileEntityId, capabilityFilter, renderer, baseColor)

    override fun getDelegatingFacing(facing: EnumFacing?): EnumFacing?
        = if (facing === getFrontFacing()) inputFacing else getFrontFacing()

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        renderer.render(renderState, translation, pipeline, getFrontFacing(), inputFacing)
    }

    override fun getParticleTexture(): Pair<TextureAtlasSprite?, Int?>
    {
        val color = GTUtility.convertOpaqueRGBA_CLtoRGB(ColourRGBA.multiply(
            GTUtility.convertRGBtoOpaqueRGBA_CL(baseColor),
            GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColorForRendering)))
        return Pair.of(renderer.getParticleTexture(), color)
    }

    override fun onWrenchClick(playerIn: EntityPlayer,
                               hand: EnumHand?,
                               facing: EnumFacing,
                               hitResult: CuboidRayTraceResult?): Boolean
    {
        if (!playerIn.isSneaking)
        {
            if (getInputFacing() === facing || facing === getFrontFacing())
                return false
            if (!world.isRemote)
                setInputFacing(facing)
            return true
        }
        return super.onWrenchClick(playerIn, hand, facing, hitResult)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setInteger("InputFacing", getInputFacing().index)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        inputFacing = EnumFacing.VALUES[data.getInteger("InputFacing")]
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeByte(getInputFacing().index)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        inputFacing = EnumFacing.VALUES[buf.readByte().toInt()]
    }

    override fun receiveCustomData(dataId: Int,  buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.UPDATE_OUTPUT_FACING)
        {
            inputFacing = EnumFacing.VALUES[buf.readByte().toInt()]
            scheduleRenderUpdate()
        }
    }

    override fun isValidFrontFacing(facing: EnumFacing): Boolean
        = facing !== getFrontFacing() && facing !== inputFacing

    override fun setFrontFacing(frontFacing: EnumFacing)
    {
        super.setFrontFacing(frontFacing)
        if (inputFacing == null)
        {
            // Set initial input facing as opposite to output (front).
            setInputFacing(frontFacing.opposite)
        }
    }

    fun getInputFacing(): EnumFacing
    {
        return inputFacing ?: EnumFacing.SOUTH
    }

    fun setInputFacing(inputFacing: EnumFacing)
    {
        this.inputFacing = inputFacing
        if (!world.isRemote)
        {
            notifyBlockUpdate()
            writeCustomData(GregtechDataCodes.UPDATE_OUTPUT_FACING) { buf: PacketBuffer -> buf.writeByte(inputFacing.index) }
            markDirty()
        }
    }

    override fun needsSneakToRotate() = true

    override fun addToolUsages(stack: ItemStack?,
                               @Nullable world: World?,
                               tooltip: MutableList<String?>,
                               advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

}