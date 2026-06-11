package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.GTValues.VA
import gregtech.api.capability.GregtechDataCodes.WORKABLE_ACTIVE
import gregtech.api.capability.GregtechDataCodes.WORKING_ENABLED
import gregtech.api.capability.IControllable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.IMaintenance
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced.MultiblockFisher
import kotlin.math.sqrt
import net.minecraft.init.Blocks
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.world.WorldServer
import net.minecraft.world.storage.loot.LootContext
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

class LargeFisherRecipeLogic(private val mte: MultiblockFisher) : IWorkable, IControllable
{
    private var progressTime = 0
    private var maxProgress = 0

    var outputAmount = 0
    private var lootTable = ""
    var mode = 0

    private var isActive = false
    private var isWorkingEnabled = true
    private var wasActiveAndNeedsUpdate = false

    private var isInventoryFull = false
    private var isEnergyNotEnough = false
    private val hasMaintenance: Boolean = ConfigHolder.machines.enableMaintenance
            && (mte as? IMaintenance)?.hasMaintenanceMechanics() ?: false

    companion object
    {
        const val MAX_PROGRESS = 1 * SECOND // TODO: Configurable.
    }

    init
    {
        this.maxProgress = MAX_PROGRESS
    }

    fun checkWater(): Boolean
    {
        val currentDirectionX = 4
        val currentDirectionZ = 4
        val offsetLowerX = -4
        val offsetUpperX = 4
        val offsetLowerZ = -4
        val offsetUpperZ = 4

        val directionX = mte.frontFacing.opposite.xOffset * currentDirectionX
        val directionZ = mte.frontFacing.opposite.zOffset * currentDirectionZ

        var amount = 0

        for (i in offsetLowerX + 1 until offsetUpperX)
        {
            for (j in offsetLowerZ + 1 until offsetUpperZ)
            {
                for (k in 0..1)
                {
                    val waterCheckPos = mte.pos.add(directionX + i, k, directionZ + j)
                    var block = mte.world.getBlockState(waterCheckPos).block

                    // If block is not water, try to fill it by consuming input fluid
                    if (block != Blocks.WATER && block != Blocks.FLOWING_WATER)
                    {
                        if (mte.importFluids != null)
                        {
                            if (depleteInput(FluidStack(FluidRegistry.WATER, 1000)))
                                mte.world.setBlockState(waterCheckPos, Blocks.WATER.defaultState)
                        }
                    }

                    block = mte.world.getBlockState(mte.pos.add(directionX + i, k, directionZ + j)).block
                    if (block == Blocks.WATER || block == Blocks.FLOWING_WATER)
                    {
                        amount++
                    }
                }
            }
        }
        return amount >= 60
    }

    private fun depleteInput(fluid: FluidStack): Boolean
    {
        val inputTank = mte.importFluids ?: return false
        if (fluid.isFluidStackIdentical(inputTank.drain(fluid, false)))
        {
            inputTank.drain(fluid, true)
            return true
        }
        return false
    }

    fun update()
    {
        if (mte.world.isRemote) return
        if (!checkWater()) return
        if (hasMaintenance && ((mte as? IMaintenance)?.numMaintenanceProblems ?: 0) > 5) return
        if (!isWorkingEnabled) return
        if (!checkCanFishing()) return

        // Drain energy
        if (!isInventoryFull)
        {
            consumeEnergy(false)
            if (!isActive) setActive(true)
        }
        else
        {
            if (isActive) setActive(false)
            return
        }

        // Increase progress
        progressTime++
        if (progressTime % MAX_PROGRESS != 0) return
        progressTime = 0

        val world = mte.world
        var l = world.rand.nextInt(outputAmount)
        while (l < outputAmount)
        {
            val lootTable = world.lootTableManager.getLootTableFromLocation(ResourceLocation(getLootTable()))
            val lootCtx = LootContext.Builder(world as WorldServer).build()
            val stacks = lootTable.generateLootForPools(world.rand, lootCtx)
            for (stack in stacks)
            {
                if (mte.fillOutput(stack, true))
                {
                    mte.fillOutput(stack, false)
                }
                else
                {
                    isInventoryFull = true
                    setActive(false)
                    wasActiveAndNeedsUpdate = true
                }
                l++
            }
        }
    }

    fun getLootTable(): String
    {
        val p = sqrt(mte.parallelLimit.toDouble()).toInt().coerceAtLeast(1)
        when (mode)
        {
            0 ->
            {
                outputAmount = mte.casingTier * (6 + p)
                lootTable = "gameplay/fishing/fish"
            }
            1 ->
            {
                outputAmount = mte.casingTier * (4 + p * 2 / 3)
                lootTable = "gameplay/fishing/junk"
            }
            2 ->
            {
                outputAmount = mte.casingTier * (2 + p / 3)
                lootTable = "gameplay/fishing/treasure"
            }
            else ->
            {
                mode = 0
            }
        }
        return lootTable
    }

    private fun checkCanFishing(): Boolean
    {
        if (!consumeEnergy(true))
        {
            if (progressTime >= 2)
            {
                progressTime = if (ConfigHolder.machines.recipeProgressLowEnergy) 1 else 1.coerceAtLeast(progressTime - 2)
                isEnergyNotEnough = true
            }
            return false
        }

        if (isEnergyNotEnough && mte.energyInputPerSec > 19L * VA[getTierByVoltage(mte.energyContainer!!.inputVoltage).toInt()])
        {
            isEnergyNotEnough = false
        }

        val world = mte.world
        val lootTable = world.lootTableManager.getLootTableFromLocation(ResourceLocation(getLootTable()))
        val lootCtx = LootContext.Builder(world as WorldServer).build()
        val stacks = lootTable.generateLootForPools(world.rand, lootCtx)
        for (stack in stacks)
        {
            if (mte.fillOutput(stack, true))
            {
                isInventoryFull = false
                return true
            }
        }
        isInventoryFull = true

        if (isActive)
        {
            setActive(false)
            wasActiveAndNeedsUpdate = true
        }
        return false
    }

    private fun consumeEnergy(energy: Boolean): Boolean
    {
        return mte.drainEnergy(energy)
    }

    override fun getProgress(): Int = progressTime

    override fun getMaxProgress(): Int = maxProgress

    fun setMaxProgress(maxProgress: Int)
    {
        this.maxProgress = maxProgress
    }

    fun invalidate()
    {
        progressTime = 0
        maxProgress = MAX_PROGRESS
        setActive(false)
    }

    override fun isActive(): Boolean = isActive

    fun setActive(active: Boolean)
    {
        if (isActive != active)
        {
            isActive = active
            mte.markDirty()
            if (mte.world != null && !mte.world.isRemote)
            {
                mte.writeCustomData(WORKABLE_ACTIVE) { it.writeBoolean(active) }
            }
        }
    }

    fun isWorking(): Boolean = isActive && !isEnergyNotEnough && isWorkingEnabled

    fun getProgressPercent(): Double = progressTime * 1.0 / MAX_PROGRESS

    override fun setWorkingEnabled(isWorkingEnabled: Boolean)
    {
        if (this.isWorkingEnabled != isWorkingEnabled)
        {
            this.isWorkingEnabled = isWorkingEnabled
            mte.markDirty()
            if (mte.world != null && !mte.world.isRemote)
            {
                mte.writeCustomData(WORKING_ENABLED) { it.writeBoolean(isWorkingEnabled) }
            }
        }
    }

    override fun isWorkingEnabled(): Boolean = isWorkingEnabled

    fun isInventoryFull(): Boolean = isInventoryFull

    fun wasActiveAndNeedsUpdate(): Boolean = wasActiveAndNeedsUpdate

    fun setWasActiveAndNeedsUpdate(wasActiveAndNeedsUpdate: Boolean)
    {
        this.wasActiveAndNeedsUpdate = wasActiveAndNeedsUpdate
    }

    fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        data.setBoolean("isActive", isActive)
        data.setBoolean("isWorkingEnabled", isWorkingEnabled)
        data.setBoolean("wasActiveAndNeedsUpdate", wasActiveAndNeedsUpdate)
        data.setInteger("progressTime", progressTime)
        data.setInteger("maxProgress", maxProgress)
        data.setBoolean("isInventoryFull", isInventoryFull)
        data.setInteger("mode", mode)
        return data
    }

    fun readFromNBT(data: NBTTagCompound)
    {
        isActive = data.getBoolean("isActive")
        isWorkingEnabled = data.getBoolean("isWorkingEnabled")
        wasActiveAndNeedsUpdate = data.getBoolean("wasActiveAndNeedsUpdate")
        progressTime = data.getInteger("progressTime")
        maxProgress = data.getInteger("maxProgress")
        isInventoryFull = data.getBoolean("isInventoryFull")
        mode = data.getInteger("mode")
    }

    fun writeInitialSyncData(buf: PacketBuffer)
    {
        buf.writeBoolean(isActive)
        buf.writeBoolean(isWorkingEnabled)
        buf.writeBoolean(wasActiveAndNeedsUpdate)
        buf.writeInt(progressTime)
        buf.writeInt(maxProgress)
        buf.writeBoolean(isInventoryFull)
    }

    fun receiveInitialSyncData(buf: PacketBuffer)
    {
        setActive(buf.readBoolean())
        setWorkingEnabled(buf.readBoolean())
        setWasActiveAndNeedsUpdate(buf.readBoolean())
        progressTime = buf.readInt()
        maxProgress = buf.readInt()
        isInventoryFull = buf.readBoolean()
    }

    fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        if (dataId == WORKABLE_ACTIVE)
        {
            isActive = buf.readBoolean()
            mte.scheduleRenderUpdate()
        }
        else if (dataId == WORKING_ENABLED)
        {
            isWorkingEnabled = buf.readBoolean()
            mte.scheduleRenderUpdate()
        }
    }
}
