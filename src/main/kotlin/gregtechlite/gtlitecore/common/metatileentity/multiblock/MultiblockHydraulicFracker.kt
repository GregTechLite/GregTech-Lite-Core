package gregtechlite.gtlitecore.common.metatileentity.multiblock

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.VA
import gregtech.api.capability.GregtechDataCodes.WORKABLE_ACTIVE
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_WORKABLE
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.IWorkable
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.metatileentity.ITieredMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.api.worldgen.bedrockFluids.BedrockFluidVeinHandler
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockHydraulicFracker(id: ResourceLocation,
                                 private val tier: Int) : MultiblockWithDisplayBase(id), IWorkable, ITieredMetaTileEntity
{

    companion object
    {
        private const val FLUID_USE_AMOUNT = 1000
        private const val MAX_PROGRESS = 5 * SECOND

        private val casingState: IBlockState
            get() = MetalCasing.WATERTIGHT_STEEL.state

        private val secondCasingState: IBlockState
            get() = GTMetalCasing.STAINLESS_CLEAN.state

        private val pipeCasingState: IBlockState
            get() = GTBoilerCasing.STEEL_PIPE.state
    }

    private var inputFluidInventory: IMultipleTankHandler? = null
    private var energyContainer: IEnergyContainer? = null

    private var progressTime = 0

    private var isActive: Boolean = true
    private var isWorkingEnabled: Boolean = true
    private var wasActiveAndNeedsUpdate: Boolean = false
    private var hasNotEnoughEnergy: Boolean = false

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockHydraulicFracker(metaTileEntityId, this.tier)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        initializeAbilities()
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        resetTileAbilities()
    }

    private fun initializeAbilities()
    {
        this.inputFluidInventory = FluidTankList(true, getAbilities(IMPORT_FLUIDS))
        this.energyContainer = EnergyContainerList(getAbilities(INPUT_ENERGY))
    }

    private fun resetTileAbilities()
    {
        this.inputFluidInventory = FluidTankList(true)
        this.energyContainer = EnergyContainerList(arrayListOf())
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("F    F", "F    F", "F  CCC", "F  CCC", "F  CCC", "FFFCCC", "   CCC", "   CCC")
        .aisle("    P ", "    P ", "   CPC", "   CPC", "   CPC", "F  CPC", " PPPPC", "   CCC")
        .aisle("      ", "      ", "   CCC", "XXXCCC", "XXXCCC", "XXXCCC", " P CCC", "   CCC")
        .aisle("      ", "      ", "     F", "XXXX  ", "X##X  ", "XXXX F", " P    ", "      ")
        .aisle("      ", "      ", "     F", "XXXX  ", "XP#X  ", "XPXX F", " P    ", "      ")
        .aisle("F    F", "F    F", "F    F", "XXXX F", "XSXX F", "XXXXFF", "      ", "      ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .or(autoAbilities())
            .or(abilities(INPUT_ENERGY)
                    .setExactLimit(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)))
        .where('C', states(secondCasingState))
        .where('F', frames(GTLiteMaterials.HSLASteel))
        .where('P', states(pipeCasingState))
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.WATERTIGHT_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_BREWERY_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing, isActive, isWorkingEnabled)
    }

    override fun updateFormedValid()
    {
        if (!world.isRemote)
        {
            updateLogic()
            if (this.wasActiveAndNeedsUpdate)
            {
                this.wasActiveAndNeedsUpdate = false
                setActive(false)
            }
        }
    }

    private fun updateLogic()
    {
        // If we have no fluid, try to get a new one and stop if we still have no fluid after.
        if (!replenishVein(true)) return

        // Do nothing if working is disabled.
        if (!isWorkingEnabled()) return

        // Check if fracking is possible.
        if (!checkCanDrain()) return

        // Actually drain the energy.
        drainEnergy(false)

        // Since energy is being consumed the fracker is now active.
        if (!isActive())
            setActive(true)

        // Increase progress.
        progressTime++
        if (progressTime % MAX_PROGRESS != 0) return
        progressTime = 0

        // Actually drain the fluid.
        if (drainTanks(FLUID_USE_AMOUNT, true))
        {
            drainTanks(FLUID_USE_AMOUNT, false)
            replenishVein(false)
        }
    }

    /**
     * @return true if the fracker is able to drain, else false
     */
    private fun checkCanDrain(): Boolean
    {
        if (!drainEnergy(true))
        {
            if (progressTime >= (2 * TICK))
            {
                if (ConfigHolder.machines.recipeProgressLowEnergy)
                    this.progressTime = 1 * TICK
                else
                    this.progressTime = max(1 * TICK, progressTime - 2 * TICK)

                this.hasNotEnoughEnergy = true
            }
            return false
        }
        else if (drainTanks(FLUID_USE_AMOUNT, true))
        {
            return true
        }

        if (this.hasNotEnoughEnergy && getEnergyInputPerSecond() > 19L * VA[tier])
        {
            this.hasNotEnoughEnergy = false
        }

        if (isActive())
        {
            setActive(false)
            this.wasActiveAndNeedsUpdate = true
        }
        return false
    }

    fun drainEnergy(simulate: Boolean): Boolean
    {
        val energyToDrain = VA[tier].toLong()
        val resultEnergy = energyContainer!!.energyStored - energyToDrain
        if (resultEnergy >= 0L && resultEnergy <= energyContainer!!.energyCapacity)
        {
            if (!simulate)
                energyContainer!!.changeEnergy(-energyToDrain)
            return true
        }
        return false
    }

    fun getEnergyInputPerSecond(): Long = energyContainer!!.inputPerSec

    @Suppress("SameParameterValue")
    private fun drainTanks(amount: Int, simulate: Boolean): Boolean
    {
        val stack = this.inputFluidInventory!!.drain(amount, !simulate)
        return stack != null && stack.isFluidEqual(GTLiteMaterials.FracturingFluid.getFluid(1)) && stack.amount == FLUID_USE_AMOUNT
    }

    private fun replenishVein(simulate: Boolean): Boolean
    {
        val entry = BedrockFluidVeinHandler.getFluidVeinWorldEntry(world, pos.x / 16, pos.z / 16)
        if (entry == null) return false

        val definition = entry.definition
        if (definition == null) return false

        val amount = entry.operationsRemaining + definition.depletionAmount
        if (amount <= BedrockFluidVeinHandler.MAXIMUM_VEIN_OPERATIONS)
        {
            if (simulate) return true
            entry.operationsRemaining = amount
        }

        return true
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.addEnergyUsageLine(this.energyContainer)
            .addWorkingStatusLine()
            .addProgressLine(progress, maxProgress)
            .addLowPowerLine(this.hasNotEnoughEnergy)
    }

    override fun addInformation(stack: ItemStack?,
                                world: World?,
                                tooltip: MutableList<String?>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.hydraulic_fracker.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.hydraulic_fracker.tooltip.2", formatNumbers(VA[tier])))
    }

    override fun getProgress(): Int = this.progressTime

    override fun getMaxProgress(): Int = MAX_PROGRESS

    override fun isWorkingEnabled(): Boolean = this.isWorkingEnabled

    override fun setWorkingEnabled(workingStatus: Boolean)
    {
        if (this.isWorkingEnabled != workingStatus)
        {
            this.isWorkingEnabled = workingStatus
            markDirty()
            if (world != null && !world.isRemote)
                writeCustomData(WORKING_ENABLED) { it.writeBoolean(this.isWorkingEnabled) }
        }
    }

    override fun isActive(): Boolean = super.isActive() && this.isActive

    fun setActive(active: Boolean)
    {
        if (this.isActive != active)
        {
            this.isActive = active
            markDirty()
            if (world != null && !world.isRemote)
                writeCustomData(WORKABLE_ACTIVE) { it.writeBoolean(this.isActive) }
        }
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound?
    {
        super.writeToNBT(data)
        data.setBoolean("isActive", this.isActive)
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled)
        data.setBoolean("wasActiveAndNeedsUpdate", this.wasActiveAndNeedsUpdate)
        data.setInteger("progressTime", progressTime)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        this.isActive = data.getBoolean("isActive")
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled")
        this.wasActiveAndNeedsUpdate = data.getBoolean("wasActiveAndNeedsUpdate")
        this.progressTime = data.getInteger("progressTime")
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(this.isActive)
        buf.writeBoolean(this.isWorkingEnabled)
        buf.writeBoolean(this.wasActiveAndNeedsUpdate)
        buf.writeInt(this.progressTime)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        this.isActive = buf.readBoolean()
        this.isWorkingEnabled = buf.readBoolean()
        this.wasActiveAndNeedsUpdate = buf.readBoolean()
        this.progressTime = buf.readInt()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == WORKABLE_ACTIVE)
        {
            this.isActive = buf.readBoolean()
            scheduleRenderUpdate()
        }
        else if (dataId == WORKING_ENABLED)
        {
            this.isWorkingEnabled = buf.readBoolean()
            scheduleRenderUpdate()
        }
    }

    override fun <T : Any?> getCapability(capability: Capability<T?>, side: EnumFacing?): T?
    {
        if (capability == CAPABILITY_WORKABLE)
            return CAPABILITY_WORKABLE.cast(this)
        if (capability == CAPABILITY_CONTROLLABLE)
            return CAPABILITY_CONTROLLABLE.cast(this)
        return super.getCapability(capability, side)
    }

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun shouldShowVoidingModeButton(): Boolean = false

    override fun getTier(): Int = this.tier

}