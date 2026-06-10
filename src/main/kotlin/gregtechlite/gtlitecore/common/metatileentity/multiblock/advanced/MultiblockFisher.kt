package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.VA
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_CONTROLLABLE
import gregtech.api.capability.GregtechTileCapabilities.CAPABILITY_WORKABLE
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.metatileentity.IDataInfoProvider
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTTransferUtils
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.capability.logic.LargeFisherRecipeLogic
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandler
import kotlin.math.max

class MultiblockFisher(id: ResourceLocation) : MultiblockWithDisplayBase(id), IDataInfoProvider
{
    val logic: LargeFisherRecipeLogic = LargeFisherRecipeLogic(this)

    var energyContainer: IEnergyContainer? = null
    private var inputFluidInventory: IMultipleTankHandler? = null
    private var outputItemInventory: IItemHandler? = null

    val energyInputPerSec = energyContainer?.inputPerSec ?: 0L
    var casingTier = 0
    var parallelLimit = 0

    companion object
    {
        private val casingState = GTMetalCasing.ALUMINIUM_FROSTPROOF.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockFisher(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        initializeAbilities()
        casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        parallelLimit = if (energyContainer != null) max(1, 8 * (getTierByVoltage(energyContainer!!.inputVoltage) + 1)) else 0
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        resetTileAbilities()
        logic.invalidate()
        casingTier = 0
        parallelLimit = 0
    }

    private fun initializeAbilities()
    {
        inputFluidInventory = FluidTankList(true, getAbilities(IMPORT_FLUIDS))
        outputItemInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
        energyContainer = EnergyContainerList(getAbilities(INPUT_ENERGY))
    }

    private fun resetTileAbilities()
    {
        inputFluidInventory = FluidTankList(true)
        outputItemInventory = ItemHandlerList(listOf())
        energyContainer = EnergyContainerList(listOf())
    }

    override fun getImportFluids(): FluidTankList? = inputFluidInventory?.let { FluidTankList(true, it) }

    override fun updateFormedValid()
    {
        logic.update()
        if (!world.isRemote && logic.wasActiveAndNeedsUpdate())
        {
            logic.setWasActiveAndNeedsUpdate(false)
            logic.setActive(false)
        }
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("EEEEEEEEE", "XXXXXXXXX", "XXXXXXXXX")
        .aisle("EXXXXXXXE", "D#######D", "X#######X")
        .aisle("EXXXXXXXE", "D#######D", "X#######X")
        .aisle("EXXXXXXXE", "P#######P", "X#######X")
        .aisle("EXXXXXXXE", "D#######D", "X#######X")
        .aisle("EXXXXXXXE", "P#######P", "X#######X")
        .aisle("EXXXXXXXE", "D#######D", "X#######X")
        .aisle("EXXXXXXXE", "D#######D", "X#######X")
        .aisle("EEEEEEEEE", "XXXXSXXXX", "XXXXXXXXX")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(92)
            .or(abilities(EXPORT_ITEMS)
                .setExactLimit(1))
            .or(abilities(IMPORT_FLUIDS)
                .setMaxGlobalLimited(1))
            .or(abilities(MUFFLER_HATCH)
                .setExactLimit(1))
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1)))
        .where('E', states(casingState)
            .or(abilities(INPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setMaxGlobalLimited(3)))
        .where('D', states(secondCasingState))
        .where('P', pumpCasings())
        .where('#', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.FROST_PROOF_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_FISHER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState, translation: Matrix4, pipeline: Array<IVertexOperation>)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, frontFacing, logic.isActive, logic.isWorkingEnabled)
    }

    override fun onScrewdriverClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitResult: CuboidRayTraceResult): Boolean
    {
        if (logic.mode < 2)
            logic.mode = logic.mode + 1
        else
            logic.mode = 0
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun addToolUsages(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.toggle_mode_covers"))
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"))
        tooltip.add(I18n.format("gregtech.tool_action.crowbar"))
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_fisher.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_fisher.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_fisher.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_fisher.tooltip.4"))
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(logic.isWorkingEnabled, logic.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(if (energyContainer != null) getTierByVoltage(energyContainer!!.inputVoltage).toInt() else 0)
            .addParallelsLine(logic.outputAmount)
            .addCustom { keyManager, uiSyncer ->
                if (isStructureFormed)
                {
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.large_fisher.mode."
                            + uiSyncer.syncInt(logic.mode)))
                }
            }
            .addWorkingStatusLine()
    }

    override fun configureWarningText(builder: MultiblockUIBuilder)
    {
        super.configureWarningText(builder)
        builder.addCustom { keyManager, uiSyncer ->
            if (logic.isInventoryFull())
            {
                val warnKey = KeyUtil.lang(TextFormatting.YELLOW, "gtlitecore.machine.large_fisher.inventory_full")
                keyManager.add(warnKey)
            }
        }
    }

    override fun getDataInfo(): MutableList<ITextComponent>
    {
        val list = arrayListOf<ITextComponent>()
        list.add(TextComponentTranslation("gtlitecore.machine.large_fisher.mode." + logic.mode))
        return list
    }

    fun drainEnergy(energy: Boolean): Boolean
    {
        val container = energyContainer ?: return false
        val energyToDrain = VA[getTierByVoltage(energyContainer!!.inputVoltage).toInt()].toLong()
        val resultEnergy = container.energyStored - energyToDrain
        if (resultEnergy >= 0L && resultEnergy <= container.energyCapacity)
        {
            if (!energy) container.changeEnergy(-energyToDrain)
            return true
        }
        return false
    }

    override fun <T : Any> getCapability(capability: Capability<T>, side: EnumFacing?): T?
    {
        if (capability == CAPABILITY_WORKABLE)
            return CAPABILITY_WORKABLE.cast(logic)
        if (capability == CAPABILITY_CONTROLLABLE)
            return CAPABILITY_CONTROLLABLE.cast(logic)
        return super.getCapability(capability, side)
    }

    override fun getIsWeatherOrTerrainResistant(): Boolean = true

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        data.setInteger("parallelLimit", parallelLimit)
        return logic.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        parallelLimit = data.getInteger("parallelLimit")
        logic.readFromNBT(data)
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(parallelLimit)
        logic.writeInitialSyncData(buf)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        parallelLimit = buf.readInt()
        logic.receiveInitialSyncData(buf)
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        logic.receiveCustomData(dataId, buf)
    }

    override fun shouldShowVoidingModeButton(): Boolean = false

    /**
     * Attempts to fill output inventory with an item.
     *
     * @param stack    The [ItemStack] to insert.
     * @param simulate If `true`, only simulate the insertion without actually inserting.
     * @return         Returns `true` if the item can be fully inserted.
     */
    fun fillOutput(stack: ItemStack, simulate: Boolean): Boolean
    {
        val handler = outputItemInventory ?: return false
        return GTTransferUtils.addItemsToItemHandler(handler, simulate, listOf(stack))
    }
}
