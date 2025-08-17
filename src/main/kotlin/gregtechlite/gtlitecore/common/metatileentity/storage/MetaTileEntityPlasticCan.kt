package gregtechlite.gtlitecore.common.metatileentity.storage

import codechicken.lib.colour.ColourRGBA
import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.GregtechDataCodes
import gregtech.api.capability.IPropertyFluidFilter
import gregtech.api.capability.impl.FilteredFluidHandler
import gregtech.api.capability.impl.GTFluidHandlerItemStack
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.util.GTUtility
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.add
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.commons.lang3.tuple.Pair
import java.io.IOException

class MetaTileEntityPlasticCan : MetaTileEntity
{

    private val fluidFilter: IPropertyFluidFilter<*>?
    private val color: Int
    private val tankSize: Int
    private var fluidTank: FilteredFluidHandler? = null
    private var isAutoOutput = false

    constructor(id: ResourceLocation,
                material: Material,
                tankSize: Int) : super(id)
    {
        val filter: IPropertyFluidFilter<*> = material.getProperty(PropertyKey.FLUID_PIPE)
        requireNotNull(filter) {
            "Material '$material' requires FluidPipeProperty for Plastic Cans"
        }
        this.fluidFilter = filter

        this.color = material.materialRGB
        this.tankSize = tankSize
        this.initializeInventory()
    }

    constructor(id: ResourceLocation,
                fluidFilter: IPropertyFluidFilter<*>,
                color: Int,
                tankSize: Int) : super(id)
    {
        this.fluidFilter = fluidFilter
        this.color = color
        this.tankSize = tankSize
        this.initializeInventory()
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity
    {
        return MetaTileEntityPlasticCan(this.metaTileEntityId, this.fluidFilter!!, this.color, this.tankSize)
    }

    override fun getHarvestTool(): String = "wrench"

    override fun hasFrontFacing(): Boolean = false

    override fun initializeInventory()
    {
        if (fluidFilter != null)
        {
            super.initializeInventory()
            fluidTank = (FilteredFluidHandler(tankSize)).setFilter(fluidFilter)
            fluidInventory = fluidTank
        }
    }

    override fun initFromItemStackData(itemStack: NBTTagCompound)
    {
        super.initFromItemStackData(itemStack)
        if (itemStack.hasKey("Fluid", 10))
        {
            val fluidStack = FluidStack.loadFluidStackFromNBT(itemStack.getCompoundTag("Fluid"))
            fluidTank!!.setFluid(fluidStack)
        }
    }

    override fun writeItemStackData(itemStack: NBTTagCompound)
    {
        super.writeItemStackData(itemStack)
        val fluidStack = fluidTank!!.getFluid()
        if (fluidStack != null && fluidStack.amount > 0)
        {
            val tagCompound = NBTTagCompound()
            fluidStack.writeToNBT(tagCompound)
            itemStack.setTag("Fluid", tagCompound)
        }
    }

    override fun initItemStackCapabilities(itemStack: ItemStack): ICapabilityProvider
    {
        return (GTFluidHandlerItemStack(itemStack, tankSize)).setFilter(fluidTank!!.filter)
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        val fluidStack = fluidTank!!.getFluid()
        buf.writeBoolean(fluidStack != null)
        if (fluidStack != null)
        {
            val tagCompound = NBTTagCompound()
            fluidStack.writeToNBT(tagCompound)
            buf.writeCompoundTag(tagCompound)
        }

        buf.writeBoolean(isAutoOutput)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        var fluidStack: FluidStack? = null
        if (buf.readBoolean())
        {
            try
            {
                val tagCompound = buf.readCompoundTag()
                fluidStack = FluidStack.loadFluidStackFromNBT(tagCompound)
            }
            catch (_: IOException)
            {
                // ...
            }
        }

        fluidTank!!.setFluid(fluidStack)
        isAutoOutput = buf.readBoolean()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GregtechDataCodes.UPDATE_AUTO_OUTPUT)
        {
            isAutoOutput = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }

    override fun update()
    {
        super.update()
        if (!world.isRemote && isAutoOutput && offsetTimer % (5 * TICK) == 0L)
        {
            pushFluidsIntoNearbyHandlers(*arrayOf(EnumFacing.DOWN))
        }
    }

    override fun onRightClick(playerIn: EntityPlayer,
                              hand: EnumHand,
                              facing: EnumFacing?,
                              hitResult: CuboidRayTraceResult?): Boolean
    {
        return if (!playerIn.getHeldItem(hand)
            .hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
        {
            super.onRightClick(playerIn, hand, facing, hitResult)
        }
        else
        {
            world.isRemote || !playerIn.isSneaking
                    && FluidUtil.interactWithFluidHandler(playerIn, hand, fluidTank!!)
        }
    }

    override fun onScrewdriverClick(playerIn: EntityPlayer,
                                    hand: EnumHand?,
                                    wrenchSide: EnumFacing?,
                                    hitResult: CuboidRayTraceResult?): Boolean
    {
        if (!playerIn.isSneaking)
        {
            if (world.isRemote)
            {
                this.scheduleRenderUpdate()
                return true
            }
            else
            {
                playerIn.sendStatusMessage(TextComponentTranslation(
                    "gregtech.machine.drum." + (if (isAutoOutput) "disable" else "enable") + "_output"), true)
                toggleOutput()
                return true
            }
        }
        else
        {
            return super.onScrewdriverClick(playerIn, hand, wrenchSide, hitResult)
        }
    }

    private fun toggleOutput()
    {
        isAutoOutput = !isAutoOutput
        if (!world.isRemote)
        {
            notifyBlockUpdate()
            writeCustomData(GregtechDataCodes.UPDATE_AUTO_OUTPUT) { it.writeBoolean(isAutoOutput) }
            markDirty()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getParticleTexture(): Pair<TextureAtlasSprite?, Int?>
    {
        val color = GTUtility.convertOpaqueRGBA_CLtoRGB(
            ColourRGBA.multiply(GTUtility.convertRGBtoOpaqueRGBA_CL(color),
                                GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColorForRendering)))
        return Pair.of(GTLiteTextures.PLASTIC_CAN.particleTexture, color)
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        val multiplier = ColourMultiplier(
            ColourRGBA.multiply(GTUtility.convertRGBtoOpaqueRGBA_CL(color),
                                GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColorForRendering)))
        GTLiteTextures.PLASTIC_CAN.render(renderState, translation, pipeline?.add(multiplier), frontFacing)
        GTLiteTextures.PLASTIC_CAN_OVERLAY.render(renderState, translation, pipeline)
        if (this.isAutoOutput)
            Textures.STEAM_VENT_OVERLAY.renderSided(EnumFacing.DOWN, renderState, translation, pipeline)
    }

    override fun getDefaultPaintingColor(): Int = 16777215

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack,
                                player: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.universal.tooltip.fluid_storage_capacity", this.tankSize))

        this.fluidFilter!!.appendTooltips(tooltip, true, true)

        if (TooltipHelper.isShiftDown())
        {
            tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
            tooltip.add(I18n.format("gregtech.tool_action.screwdriver.auto_output_down"))
            tooltip.add(I18n.format("gregtech.tool_action.crowbar"))
        }
        val tagCompound = stack.tagCompound
        if (tagCompound != null && tagCompound.hasKey("Fluid", 10))
        {
            val fluidStack = FluidStack.loadFluidStackFromNBT(tagCompound.getCompoundTag("Fluid"))
            if (fluidStack == null)
                return

            tooltip.add(I18n.format("gregtech.machine.fluid_tank.fluid",
                                    fluidStack.amount, fluidStack.fluid.getLocalizedName(fluidStack)))
        }
    }

    override fun showToolUsages(): Boolean = false

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setTag("FluidInventory", (fluidInventory as FluidTank).writeToNBT(NBTTagCompound()))
        data.setBoolean("AutoOutput", isAutoOutput)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        (fluidInventory as FluidTank).readFromNBT(data.getCompoundTag("FluidInventory"))
        isAutoOutput = data.getBoolean("AutoOutput")
    }

    override fun shouldSerializeInventories(): Boolean = false

}
