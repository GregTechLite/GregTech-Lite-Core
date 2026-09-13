package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.items.itemhandlers.GTItemStackHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputInventory
import gregtechlite.gtlitecore.api.capability.PatternedSingletonDualInputProxy
import gregtechlite.gtlitecore.api.capability.SingletonDualInputAdapter
import gregtechlite.gtlitecore.api.capability.SingletonDualInputInventory
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import java.util.Optional

class PartMachineMECraftingPatternInputMirror(id: ResourceLocation, tier: Int)
    : MetaTileEntityMultiblockNotifiablePart(id, tier, false), IMultiblockAbilityPart<IItemHandlerModifiable>,
      IControllable, PatternedSingletonDualInputProxy, IDataStickIntractable
{
    private var master: PartMachineMECraftingPatternInputHatch? = null
    private var masterX = 0
    private var masterY = 0
    private var masterZ = 0
    private var isMasterInit = false

    private val sharedItemHandler = MirrorSharedItemHandler()

    companion object
    {
        private const val MASTER_TAG = "master"
        private const val MASTER_COORD_X = "x"
        private const val MASTER_COORD_Y = "y"
        private const val MASTER_COORD_Z = "z"

        private const val MASTER_RETRY_INTERVAL = 100L
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineMECraftingPatternInputMirror(metaTileEntityId, tier)

    override fun createImportItemHandler(): IItemHandlerModifiable = GTItemStackHandler(this, 0)

    override fun createExportItemHandler(): IItemHandlerModifiable = GTItemStackHandler(this, 0)

    fun getMaster(): PartMachineMECraftingPatternInputHatch?
    {
        val current = master ?: return null
        if (current.holder?.isValid != true)
        {
            master = null
            return null
        }
        return current
    }

    fun trySetMasterFromCoord(x: Int, y: Int, z: Int): PartMachineMECraftingPatternInputHatch?
    {
        val worldIn = world ?: return null
        val tileEntity = worldIn.getTileEntity(BlockPos(x, y, z))
        if (tileEntity !is IGregTechTileEntity) return null
        if (!tileEntity.isValid) return null
        val newMaster = tileEntity.metaTileEntity as? PartMachineMECraftingPatternInputHatch ?: return null

        if (master !== newMaster)
        {
            master?.removeMirror(this)
            master = newMaster
            newMaster.addMirror(this)
            invalidateControllerRecipeCache()
        }

        masterX = x
        masterY = y
        masterZ = z
        isMasterInit = true
        return master
    }

    fun onMasterRemoved()
    {
        master = null
        invalidateControllerRecipeCache()
    }

    private fun tryLinkDataStick(player: EntityPlayer, dataStick: ItemStack): Boolean
    {
        val tag = dataStick.tagCompound ?: return false
        if (!tag.hasKey(PartMachineMECraftingPatternInputHatch.MIRROR_LINK_TAG)) return false

        val link = tag.getCompoundTag(PartMachineMECraftingPatternInputHatch.MIRROR_LINK_TAG)
        val linked = trySetMasterFromCoord(
            link.getInteger(MASTER_COORD_X),
            link.getInteger(MASTER_COORD_Y),
            link.getInteger(MASTER_COORD_Z)) != null

        player.sendStatusMessage(TextComponentTranslation(
            if (linked) "gtlitecore.machine.me_crafting_pattern_input_mirror.link_success"
            else "gtlitecore.machine.me_crafting_pattern_input_mirror.link_failed"), true)
        return true
    }

    private fun invalidateControllerRecipeCache()
    {
        val logic = (controller as? RecipeMapMultiblockController)?.recipeMapWorkable as? SingletonDualInputAdapter ?: return
        logic.clearInventoryRecipeCache()
    }

    override fun onDataStickLeftClick(player: EntityPlayer, dataStick: ItemStack)
    {
        val linkedMaster = getMaster()
        if (linkedMaster == null)
        {
            player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.me_crafting_pattern_input_mirror.link_failed"), true)
            return
        }

        NBTTagCompound().also {
            linkedMaster.writeMirrorLink(it)
            dataStick.tagCompound = it
        }

        dataStick.setTranslatableName("gtlitecore.machine.me_crafting_pattern_input_mirror.data_stick")
        player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.me_crafting_pattern_input_mirror.link_saved"), true)
    }

    override fun onDataStickRightClick(player: EntityPlayer, dataStick: ItemStack): Boolean
        = tryLinkDataStick(player, dataStick)

    override fun inventories(): Iterator<PatternedSingletonDualInputInventory>
        = getMaster()?.inventories() ?: emptyList<PatternedSingletonDualInputInventory>().iterator()

    override fun getFirstNonEmptyInventory(): Optional<SingletonDualInputInventory>
        = getMaster()?.getFirstNonEmptyInventory() ?: Optional.empty()

    override fun supportFluids(): Boolean = getMaster()?.supportFluids() ?: false

    override fun getSharedItems(): Array<ItemStack> = getMaster()?.getSharedItems() ?: emptyArray()

    override fun shouldUpdate(): Boolean = getMaster()?.shouldUpdate() ?: false

    override fun isWorkingEnabled(): Boolean = getMaster()?.isWorkingEnabled() ?: true

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        getMaster()?.setWorkingEnabled(workingEnabled)
    }

    override fun getAbilities(): List<MultiblockAbility<*>> = listOf(MultiblockAbility.IMPORT_ITEMS)

    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        if (abilityInstances.isKey(MultiblockAbility.IMPORT_ITEMS))
        {
            abilityInstances.add(sharedItemHandler)
        }
    }

    override fun update()
    {
        super.update()
        if (world == null || world.isRemote) return

        if (offsetTimer % MASTER_RETRY_INTERVAL == 0L && isMasterInit && getMaster() == null)
        {
            trySetMasterFromCoord(masterX, masterY, masterZ)
        }
    }

    override fun onRemoval()
    {
        super.onRemoval()
        getMaster()?.removeMirror(this)
    }

    override fun invalidate()
    {
        super.invalidate()
        getMaster()?.removeMirror(this)
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        if (isMasterInit)
        {
            NBTTagCompound().also {
                it.setInteger(MASTER_COORD_X, masterX)
                it.setInteger(MASTER_COORD_Y, masterY)
                it.setInteger(MASTER_COORD_Z, masterZ)
                data.setTag(MASTER_TAG, it)
            }
        }
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        if (data.hasKey(MASTER_TAG))
        {
            data.getCompoundTag(MASTER_TAG).also {
                masterX = it.getInteger(MASTER_COORD_X)
                masterY = it.getInteger(MASTER_COORD_Y)
                masterZ = it.getInteger(MASTER_COORD_Z)
            }
            isMasterInit = true
        }
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<out IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (shouldRenderOverlay())
        {
            GTLiteOverlays.ME_CRAFTING_INPUT_MIRROR_OVERLAY.renderSided(frontFacing, renderState, translation, pipeline)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_mirror.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_mirror.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.me_crafting_pattern_input_mirror.tooltip.3"))
        tooltip.add(I18n.format("gregtech.universal.enabled"))
    }

    @SideOnly(Side.CLIENT)
    override fun addToolUsages(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.wire_cutter.connect"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"))
        super.addToolUsages(stack, world, tooltip, advanced)
    }

    private inner class MirrorSharedItemHandler : IItemHandlerModifiable
    {
        private val delegate: IItemHandlerModifiable?
            get() = getMaster()?.getSharedItemHandler()

        override fun getSlots(): Int = PartMachineMECraftingPatternInputHatch.SHARED_SLOT_COUNT

        override fun getStackInSlot(slot: Int): ItemStack = delegate?.getStackInSlot(slot) ?: ItemStack.EMPTY

        override fun setStackInSlot(slot: Int, stack: ItemStack)
        {
            delegate?.setStackInSlot(slot, stack)
        }

        override fun getSlotLimit(slot: Int): Int = delegate?.getSlotLimit(slot) ?: 0

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean = delegate?.isItemValid(slot, stack) ?: false

        override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack
            = delegate?.insertItem(slot, stack, simulate) ?: stack

        override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack
            = delegate?.extractItem(slot, amount, simulate) ?: ItemStack.EMPTY
    }
}
