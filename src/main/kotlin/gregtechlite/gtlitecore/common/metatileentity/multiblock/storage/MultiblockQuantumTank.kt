package gregtechlite.gtlitecore.common.metatileentity.multiblock.storage

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.IControllable
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.metatileentity.IVoidable.VoidingMode
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.KeyUtil
import gregtech.api.util.RelativeDirection.RIGHT
import gregtech.api.util.RelativeDirection.UP
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.capability.handler.QuantumStorageHandler
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.readBlockCount
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.quantumStorageUnits
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.longValue
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.math.BigInteger

class MultiblockQuantumTank(id: ResourceLocation) : MultiblockWithDisplayBase(id), IControllable, SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    private var storage: QuantumStorageHandler<FluidStack> = QuantumStorageHandler(0, BigInteger.ZERO,
        isSameType = { a, b -> a.isFluidEqual(b) },
        writeType = { tag, fluid ->
            val nbt = NBTTagCompound()
            fluid.writeToNBT(nbt)
            tag.setTag("fluid", nbt)
        },
        readType = { tag ->
            FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluid")).also { it?.amount = 1 }!!
        })

    private var storageSync by syncer.syncedNBT(NBTTagCompound())
    private var isStorageDirty: Boolean = false

    private lateinit var importFluids: FluidTankList
    private lateinit var exportFluids: FluidTankList

    private var _isWorkingEnabled: Boolean = true
    private var shouldImport: Boolean = false
    private var shouldExport: Boolean = false

    companion object
    {
        private val casingState = GTComputerCasing.COMPUTER_CASING.state
        private val secondCasingState = GTComputerCasing.COMPUTER_HEAT_VENT.state
        private val glassCasingState = GTGlassCasing.FUSION_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockQuantumTank(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        initializeAbilities()

        val counts = readBlockCount(context)
        storage.rebuild(counts.distinctSlots, counts.totalCapacity)
        markStorageDirty()

        shouldImport = importFluids.tanks > 0
        shouldExport = exportFluids.tanks > 0
    }

    override fun invalidateStructure()
    {
        resetTileAbilities()
        super.invalidateStructure()
    }

    private fun initializeAbilities()
    {
        importFluids = FluidTankList(true, getAbilities(IMPORT_FLUIDS))
        exportFluids = FluidTankList(true, getAbilities(EXPORT_FLUIDS))
    }

    private fun resetTileAbilities()
    {
        importFluids = FluidTankList(true)
        exportFluids = FluidTankList(true)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RIGHT, FRONT, UP)
        .aisle("     ", " CCC ", " CCC ", " CCC ", "     ")
        .aisle("HCSCH", "HCCCH", "HCCCH", "HCCCH", "HCCCH")
        .aisle("GGGGG", "GUUUG", "GUUUG", "GUUUG", "GGGGG").setRepeatable(1, 15)
        .aisle("HCCCH", "HCCCH", "HCCCH", "HCCCH", "HCCCH")
        .aisle("     ", " CCC ", " CCC ", " CCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_FLUIDS)
                    .setPreviewCount(1))
            .or(metaTileEntities(GTLiteMetaTileEntities.QUANTUM_ACCESS_HATCH)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1)))
        .where('H', states(secondCasingState))
        .where('G', states(glassCasingState))
        .where('U', quantumStorageUnits()
            .or(air()))
        .where(' ', any())
        .build()

    // @formatter:on

    override fun updateFormedValid()
    {
        if (!world.isRemote && _isWorkingEnabled && offsetTimer % (1 * SECOND) == 0L)
        {
            if (shouldImport) importFluids()
            if (shouldExport) exportFluids()
        }
        if (isStorageDirty)
        {
            storageSync = storage.serialize()
            isStorageDirty = false
        }
        syncer.flushChanges()
    }

    fun fluidStorage(): QuantumStorageHandler<FluidStack> = storage

    fun insertFluid(fluid: FluidStack, amount: BigInteger, simulate: Boolean): BigInteger
    {
        val newFluid = fluid.copy()
        val insertable = storage.maxInsertable(newFluid)
        var accepted = minOf(amount, insertable)

        if (voidingMode == VoidingMode.VOID_FLUIDS.ordinal || voidingMode == VoidingMode.VOID_BOTH.ordinal)
            accepted = amount

        if (!simulate && accepted.signum() > 0)
        {
            storage.insert(newFluid, accepted.min(insertable))
            markStorageDirty()
        }
        return accepted
    }

    fun extractFluid(fluid: FluidStack, amount: BigInteger, simulate: Boolean): BigInteger
    {
        val newFluid = fluid.copy()
        val removed = minOf(amount, storage.currentAmount(newFluid))
        if (!simulate && removed.signum() > 0)
        {
            storage.extract(newFluid, removed)
            markStorageDirty()
        }
        return removed
    }

    private fun importFluids()
    {
        for (tank in importFluids.fluidTanks)
        {
            val fluid = tank.fluid ?: continue

            val accepted = storage.insert(fluid.copy(), BigInteger.valueOf(fluid.amount.toLong()))
            if (accepted.signum() > 0)
            {
                tank.drain(accepted.intValueExact(), true)
                markStorageDirty()
            }
            else if (voidingMode == VoidingMode.VOID_FLUIDS.ordinal || voidingMode == VoidingMode.VOID_BOTH.ordinal)
            {
                tank.drain(tank.fluid?.amount ?: 0, true)
            }
        }
    }

    private fun exportFluids()
    {
        val fluids = storage.entries().map { it.key }
        for (fluid in fluids)
        {
            val available = storage.currentAmount(fluid)
            if (available.signum() <= 0) continue

            var fluidToMove = minOf(available, BigInteger.valueOf(Integer.MAX_VALUE.toLong()))
            var moved = BigInteger.ZERO

            for (tank in exportFluids.fluidTanks)
            {
                if (fluidToMove.signum() <= 0) break

                val inside = tank.fluid
                if (inside != null && !inside.isFluidEqual(fluid)) continue

                val space = (tank.capacity - (inside?.amount ?: 0)).toLong()
                if (space <= 0) continue

                val toFill = minOf(space.toBigInteger(), fluidToMove).toInt()
                val filled = tank.fill(FluidStack(fluid, toFill), true)
                if (filled > 0)
                {
                    fluidToMove = fluidToMove.subtract(BigInteger.valueOf(filled.toLong()))
                    moved = moved.add(BigInteger.valueOf(filled.toLong()))
                }
            }

            if (moved.signum() > 0)
            {
                storage.extract(fluid, moved)
                markStorageDirty()
            }
        }
    }

    private fun markStorageDirty()
    {
        isStorageDirty = true
        markDirty()
    }

    override fun <T> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability == CAPABILITY_CONTROLLABLE)
            return CAPABILITY_CONTROLLABLE.cast(this)
        return super.getCapability(capability, side)
    }

    override fun isWorkingEnabled(): Boolean = _isWorkingEnabled

    override fun setWorkingEnabled(isWorkingAllowed: Boolean)
    {
        _isWorkingEnabled = isWorkingAllowed
        if (!world.isRemote) markDirty()
    }

    override fun isActive(): Boolean = _isWorkingEnabled && isStructureFormed

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        storage.deserialize(storageSync)
    }

    override fun shouldShowVoidingModeButton() = true

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(_isWorkingEnabled, isActive)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    val totalCapacity = KeyUtil.number(TextFormatting.GREEN, syncer.syncLong { storage.totalCapacity.longValue() })
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.large_quantum_tank.total_capacity", totalCapacity))

                    val stored = KeyUtil.number(TextFormatting.YELLOW, syncer.syncLong { storage.totalStored().longValue() })
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.large_quantum_tank.stored", stored))

                    val distinctSlots = KeyUtil.number(TextFormatting.BLUE, syncer.syncInt { storage.distinctSlots() }.toLong())
                    val maxDistinctSlots = KeyUtil.number(TextFormatting.BLUE, syncer.syncInt { storage.maxDistinct }.toLong())
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.large_quantum_tank.slots", distinctSlots, maxDistinctSlots))
                }
            }
            .addWorkingStatusLine()
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.COMPUTER_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.RESEARCH_STATION_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        frontOverlay.renderOrientedState(renderState, translation, pipeline, frontFacing, isActive, _isWorkingEnabled)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: net.minecraft.item.ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_quantum_tank.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_quantum_tank.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_quantum_tank.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_quantum_tank.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.large_quantum_tank.tooltip.5"))
    }
}
