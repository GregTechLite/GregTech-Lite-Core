package gregtechlite.gtlitecore.common.metatileentity.multiblock

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.ULV
import gregtech.api.capability.FeCompat.insertEu
import gregtech.api.capability.GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM
import gregtech.api.capability.GregtechDataCodes.WORKABLE_ACTIVE
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IElectricItem
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.util.GTTransferUtils.insertItem
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import gregtech.api.util.RelativeDirection.*
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.item.GTLiteToolHelper
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max
import kotlin.math.min

class MultiblockEnergyInfuser(id: ResourceLocation) : MultiblockWithDisplayBase(id), IControllable
{

    private var inputInventory: IItemHandlerModifiable? = null
    private var outputInventory: IItemHandlerModifiable? = null
    private var inputFluidInventory: IMultipleTankHandler? = null
    
    private var energyContainer: IEnergyContainer? = null
    
    private var isActive = false
    private var isWorkingEnabled = false

    companion object
    {
        private val casingState
            get() = GTComputerCasing.HIGH_POWER_CASING.state
        private val secondCasingState
            get() = ScienceCasing.MOLECULAR_CASING.state
        private val coilState
            get() = ScienceCasing.MOLECULAR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockEnergyInfuser(metaTileEntityId)
    
    override fun formStructure(context: PatternMatchContext?)
    {
        super.formStructure(context)
        initializeAbilities()
    }
    
    private fun initializeAbilities()
    {
        this.inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        this.outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
        this.inputFluidInventory = FluidTankList(true, getAbilities(IMPORT_FLUIDS))
        
        val inputEnergy = getAbilities(INPUT_ENERGY)
        this.energyContainer = EnergyContainerList(inputEnergy)
    }
    
    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "MMM", "DDD", "MMM", "CCC")
        .aisle("CCC", "MDM", "DDD", "MDM", "CCC")
        .aisle("CCC", "MMM", "DSD", "MMM", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(4)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0))
            .or(abilities(IMPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setPreviewCount(0)))
        .where('D', states(secondCasingState))
        .where('M', states(coilState))
        .build()
    
    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        return if (sourcePart == null) GTLiteTextures.MOLECULAR_CASING else Textures.HIGH_POWER_CASING
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.DATA_BANK_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        this.frontOverlay.renderOrientedState(renderState, translation, pipeline,
            getFrontFacing(), isActive(), isWorkingEnabled())
    }

    override fun getMatchingShapes(): List<MultiblockShapeInfo?>?
    {
        val shapeInfos = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("CEC", "MMM", "DDD", "MMM", "CCC")
            .aisle("CCC", "MDM", "DDD", "MDM", "CCC")
            .aisle("ICJ", "MMM", "DSD", "MMM", "CCC")
            .where('S', GTLiteMetaTileEntities.ENERGY_INFUSER, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', secondCasingState)
            .where('M', coilState)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .build()
        shapeInfos.add(builder)
        return shapeInfos
    }
    
    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.3"))
    }
    
    override fun updateFormedValid()
    {
        if (!isWorkingEnabled || world == null || world.isRemote) return
        
        var itemProcessed = false
        for (i in 0 ..< inputInventory!!.getSlots())
        {
            var energyAvailable = energyContainer!!.energyStored
            if (energyAvailable < 1) break
            
            var stackInSlot = inputInventory!!.getStackInSlot(i)
            if (stackInSlot.isEmpty) continue
            
            val isCharged = isItemFullyCharged(stackInSlot)
            val isRepaired: Boolean = GTLiteToolHelper.isItemHasFullDurability(stackInSlot)
            
            if (isCharged && isRepaired)
            {
                stackInSlot = inputInventory!!.extractItem(i, 1, true)
                if (outputInventory!!.getSlots() > 0
                    && insertItem(outputInventory, stackInSlot, true).isEmpty)
                {
                    stackInSlot = inputInventory!!.extractItem(i, 1, false)
                    insertItem(outputInventory, stackInSlot, false)
                }
                else if (voidingMode == 1 || voidingMode == 3)
                {
                    inputInventory!!.extractItem(i, 1, false)
                }
            }
            
            if (!isCharged)
            {
                val energyUsed = chargeItem(stackInSlot, energyAvailable)
                if (!itemProcessed) itemProcessed = energyUsed > 0
            }
            
            if (!isRepaired && inputFluidInventory!!.tanks > 0)
            {
                energyAvailable = energyContainer!!.energyStored
                val toRepair = min(stackInSlot.getItemDamage(), GTLiteConfigHolder.machine.energyInfuser.maxRepairedDamagePerWorking)
                val powerCost = toRepair.toLong() * GTLiteConfigHolder.machine.energyInfuser.energyConsumedPerDurability
                val toDrain = FluidStack(UUMatter.fluid, toRepair * GTLiteConfigHolder.machine.energyInfuser.uuMatterConsumedPerDurability)
                if (energyAvailable > powerCost
                    && inputFluidInventory!!.drain(toDrain, false) != null)
                {
                    stackInSlot.setItemDamage(max(stackInSlot.getItemDamage() - toRepair, 0))
                    inputFluidInventory!!.drain(toDrain, true)
                    energyContainer!!.removeEnergy(powerCost)
                    itemProcessed = true
                }
            }
        }
        
        if (itemProcessed != isActive())
        {
            setActive(itemProcessed)
        }
    }
    
    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled)
        return data
    }
    
    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled")
    }
    
    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(this.isWorkingEnabled)
        buf.writeBoolean(this.isActive)
    }
    
    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        this.isWorkingEnabled = buf.readBoolean()
        this.isActive = buf.readBoolean()
    }
    
    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == WORKABLE_ACTIVE)
        {
            this.isActive = buf.readBoolean()
            scheduleRenderUpdate()
        } else if (dataId == WORKING_ENABLED)
        {
            this.isWorkingEnabled = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }
    
    override fun isActive() = super.isActive() && isActive
    
    fun setActive(active: Boolean)
    {
        this.isActive = active
        markDirty()
        writeCustomData(WORKABLE_ACTIVE) { buf: PacketBuffer -> buf.writeBoolean(this.isActive) }
    }
    
    override fun isWorkingEnabled() = this.isWorkingEnabled

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        this.isWorkingEnabled = workingEnabled
        markDirty()
        writeCustomData(WORKING_ENABLED) { buf: PacketBuffer -> buf.writeBoolean(this.isWorkingEnabled) }
    }
    
    override fun hasMaintenanceMechanics() = false
    
    private fun chargeItem(stack: ItemStack, energy: Long): Long
    {
        val energyAvailable = min(energy, this.energyContainer!!.inputVoltage * this.energyContainer!!.inputAmperage)
        if (stack.hasCapability(CAPABILITY_ELECTRIC_ITEM, null))  // GTEU
        {
            val item = stack.getCapability(CAPABILITY_ELECTRIC_ITEM, null)
            if (item == null) return 0
            return item.charge(energyAvailable, getFloorTierByVoltage(this.energyContainer!!.inputVoltage).toInt(), true, false)
        }
        else if (stack.hasCapability(ENERGY, null))  // FE/EU/RF
        {
            val storage = stack.getCapability(ENERGY, null)
            if (storage == null) return 0
            return insertEu(storage, energyAvailable)
        }
        return 0
    }
    
    private fun isItemFullyCharged(stack: ItemStack): Boolean
    {
        if (stack.hasCapability(CAPABILITY_ELECTRIC_ITEM, null))  // GTEU
        {
            val item: IElectricItem? = stack.getCapability(CAPABILITY_ELECTRIC_ITEM, null)
            if (item == null || !item.chargeable()) return true
            return item.charge >= item.maxCharge
        } else if (stack.hasCapability(ENERGY, null))  // FE/EU/RF
        {
            val storage = stack.getCapability(ENERGY, null)
            if (storage == null || !storage.canReceive()) return true
            return storage.getEnergyStored() >= storage.getMaxEnergyStored()
        }
        return true
    }
    
}
