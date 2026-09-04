package gregtechlite.gtlitecore.common.metatileentity.multiblock.storage

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.IControllable
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.metatileentity.IVoidable.VoidingMode
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.capability.handler.QuantumStorageHandler
import gregtechlite.gtlitecore.api.metatileentity.sync.MetaTileEntitySyncer
import gregtechlite.gtlitecore.api.metatileentity.sync.SyncedMetaTileEntity
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.common.block.variant.QuantumStorageUnit
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.longValue
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.quantumStorageUnits
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.math.BigInteger

class MultiblockQuantumChest(id: ResourceLocation) : MultiblockWithDisplayBase(id), IControllable, SyncedMetaTileEntity
{
    override val syncer: MetaTileEntitySyncer = MetaTileEntitySyncer(this)

    private var storage: QuantumStorageHandler<ItemStack> = QuantumStorageHandler(0, BigInteger.ZERO,
        isSameType = { a, b -> a.isItemEqual(b) && ItemStack.areItemStackTagsEqual(a, b) }, // TODO: It's that too strict?
        writeType = { tag, stack ->
            val nbt = NBTTagCompound()
            stack.writeToNBT(nbt)
            tag.setTag("item", nbt)
        },
        readType = { tag ->
            val stack = ItemStack(tag.getCompoundTag("item")).also { it.setCount(1) }
            return@QuantumStorageHandler stack
        })

    private var storageSync by syncer.syncedNBT(NBTTagCompound())
    private var isStorageDirty: Boolean = false

    private lateinit var importItems: ItemHandlerList
    private lateinit var exportItems: ItemHandlerList

    private var _isWorkingEnabled: Boolean = true
    private var shouldImport: Boolean = false
    private var shouldExport: Boolean = false

    companion object
    {
        private val casingState = GTComputerCasing.COMPUTER_CASING.state
        private val secondCasingState = GTComputerCasing.COMPUTER_HEAT_VENT.state
        private val glassCasingState = GTGlassCasing.FUSION_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockQuantumChest(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        initializeAbilities()

        val blockStats = readBlockStats(context)
        storage.rebuild(blockStats.distinctSlots, blockStats.totalCapacity)
        markStorageDirty()

        shouldImport = importItems.slots > 0
        shouldExport = exportItems.slots > 0

        LOGGER.info("Formed Large Quantum Chest: [distinctSlots=${blockStats.distinctSlots}, capacity=${blockStats.totalCapacity}]")
    }

    private fun readBlockStats(context: PatternMatchContext): QuantumStorageUnit
    {
        val attribute = context.getAttributeOrDefault(GTLiteAPI.QUANTUM_STORAGE_UNIT_TIER, 1)
        return QuantumStorageUnit.entries[(attribute - 1).coerceIn(0, QuantumStorageUnit.entries.size - 1)]
    }

    override fun invalidateStructure()
    {
        resetTileAbilities()
        super.invalidateStructure()
    }

    private fun initializeAbilities()
    {
        importItems = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        exportItems = ItemHandlerList(getAbilities(EXPORT_ITEMS))
    }

    private fun resetTileAbilities()
    {
        importItems = ItemHandlerList(emptyList())
        exportItems = ItemHandlerList(emptyList())
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("HHHHH", "HCCCH", "HCCCH", "HCCCH", "HHHHH")
        .aisle("GGGGG", "GUUUG", "GUUUG", "GUUUG", "GGGGG").setRepeatable(1, 15)
        .aisle("HHHHH", "HCCCH", "HCSCH", "HCCCH", "HHHHH")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(IMPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_ITEMS)
                    .setPreviewCount(1))) // TODO: Access Hatch ability support.
        .where('H', states(secondCasingState))
        .where('G', states(glassCasingState))
        .where('U', quantumStorageUnits()
            .or(air()))
        .build()

    // @formatter:on

    override fun updateFormedValid()
    {
        if (!world.isRemote && _isWorkingEnabled && offsetTimer % (1 * SECOND) == 0L)
        {
            if (shouldImport) importItems()
            if (shouldExport) exportItems()
        }
        if (isStorageDirty)
        {
            storageSync = storage.serialize()
            isStorageDirty = false
        }
        syncer.flushChanges()
    }

    fun insertItemStack(stack: ItemStack, amount: BigInteger, simulate: Boolean): BigInteger
    {
        val newStack = stack.copy()
        val insertableStack = storage.maxInsertable(newStack)
        var acceptedStack = minOf(amount, insertableStack)

        if (voidingMode == VoidingMode.VOID_ITEMS.ordinal || voidingMode == VoidingMode.VOID_BOTH.ordinal)
            acceptedStack = amount

        if (!simulate && acceptedStack.signum() > 0)
        {
            storage.insert(newStack, acceptedStack.min(insertableStack))
            markStorageDirty()
        }
        return acceptedStack
    }

    fun extractItemStack(stack: ItemStack, amount: BigInteger, simulate: Boolean): BigInteger
    {
        val newStack = stack.copy()
        val removedStack = minOf(amount, storage.currentAmount(newStack))
        if (!simulate && removedStack.signum() > 0)
        {
            storage.extract(newStack, removedStack)
            markStorageDirty()
        }
        return removedStack
    }

    private fun importItems()
    {
        for (slot in 0 until importItems.slots)
        {
            val stack = importItems.getStackInSlot(slot)
            if (stack.isEmpty) continue

            val acceptedStack = storage.insert(stack.copy(), BigInteger.valueOf(stack.count.toLong()))
            if (acceptedStack.signum() > 0)
            {
                importItems.extractItem(slot, acceptedStack.intValueExact(), false)
                markStorageDirty()
            }
            else if (voidingMode == VoidingMode.VOID_ITEMS.ordinal || voidingMode == VoidingMode.VOID_BOTH.ordinal)
            {
                importItems.extractItem(slot, stack.count, false)
            }
        }
    }

    private fun exportItems()
    {
        val stacks = storage.entries().map { it.key }
        for (stack in stacks)
        {
            val availableStack = storage.currentAmount(stack)
            if (availableStack.signum() <= 0) continue

            var stackToMove = minOf(availableStack, BigInteger.valueOf(Integer.MAX_VALUE.toLong()))
            var movedStack = BigInteger.ZERO

            for (slot in 0 until exportItems.slots)
            {
                if (stackToMove.signum() <= 0) break

                val stackToInsert = minOf(stackToMove, BigInteger.valueOf(64L)).toInt()
                val insertStack = stack.copy(stackToInsert)

                val leftoverStack = exportItems.insertItem(slot, insertStack, false)
                val filledStack = stackToInsert - leftoverStack.count
                if (filledStack > 0)
                {
                    stackToMove = stackToMove.subtract(BigInteger.valueOf(filledStack.toLong()))
                    movedStack = movedStack.add(BigInteger.valueOf(filledStack.toLong()))
                }
            }

            if (movedStack.signum() > 0)
            {
                storage.extract(stack, movedStack)
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
                                                "gtlitecore.machine.large_quantum_chest.total_capacity", totalCapacity))

                    val stored = KeyUtil.number(TextFormatting.YELLOW, syncer.syncLong { storage.totalStored().longValue() })
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.large_quantum_chest.stored", stored))

                    val distinctSlots = KeyUtil.number(TextFormatting.BLUE, syncer.syncInt { storage.distinctSlots() }.toLong())
                    val maxDistinctSlots = KeyUtil.number(TextFormatting.BLUE, syncer.syncInt { storage.maxDistinct }.toLong())
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY,
                                                "gtlitecore.machine.large_quantum_chest.slots", distinctSlots, maxDistinctSlots))
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
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced) // TODO: Description.
    }
}
