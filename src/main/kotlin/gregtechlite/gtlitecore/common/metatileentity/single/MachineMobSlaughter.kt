package gregtechlite.gtlitecore.common.metatileentity.single

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.screen.UISettings
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import gregtech.api.GTValues.VNF
import gregtech.api.capability.GregtechDataCodes.IS_WORKING
import gregtech.api.metatileentity.TieredMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.mui.GTGuis
import gregtech.api.unification.material.Materials.NitrousOxide
import gregtech.common.mui.widget.GTFluidSlot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.entity.GTLiteDamageSources
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MachineMobSlaughter(id: ResourceLocation, tier: Int) : TieredMetaTileEntity(id, tier)
{
    companion object
    {
        private const val EU_PER_KILL = 2
        private const val DAMAGE_PER_KILL = 40f

        private const val AREA_RADIUS = 4
        private const val MOBS_PER_CYCLE = 4

        private const val MAX_N2O_BONUS = 12

        var LOOTING_USED = 0
    }

    private val fluidTank = FluidTank(tier * 4000)
    private var isWorking = false
    private var areaCenterPos: BlockPos? = null
    private var areaBoundingBox: AxisAlignedBB? = null

    private val mobsPerCycle: Int
        get()
        {
            val maxByEnergy = (energyContainer.energyStored / energyConsumedPerKill).toInt()
            val fluid = fluidTank.fluid ?: return MOBS_PER_CYCLE.coerceAtMost(maxByEnergy)
            if (!fluid.isFluidEqual(NitrousOxide.getFluid(1))) {
                return MOBS_PER_CYCLE.coerceAtMost(maxByEnergy)
            }
            val bonus = fluid.amount.coerceAtMost(MAX_N2O_BONUS)
            return (bonus + MOBS_PER_CYCLE).coerceAtMost(maxByEnergy)
        }

    private val energyConsumedPerKill: Long
        get() = EU_PER_KILL.toLong() shl ((tier - 1) * 2)

    init
    {
        initializeInventory()
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity) = MachineMobSlaughter(metaTileEntityId, tier)

    override fun update() {
        super.update()
        if (world.isRemote) return
        val canWork = energyContainer.energyStored >= energyConsumedPerKill && isBlockRedstonePowered
        if (canWork != isWorking)
        {
            isWorking = canWork
            writeCustomData(IS_WORKING) { it.writeBoolean(canWork) }
        }
        if (offsetTimer % SECOND != 0L || !canWork) return
        processMobs()
    }

    private fun processMobs()
    {
        val boundingBox = areaBoundingBox ?: run {
            areaCenterPos = pos.offset(frontFacing, 5)
            AxisAlignedBB(areaCenterPos!!).grow(AREA_RADIUS.toDouble(), 0.0, AREA_RADIUS.toDouble())
                .also { areaBoundingBox = it }
        }

        val mobs = world.getEntitiesWithinAABB(EntityLivingBase::class.java, boundingBox)
        if (mobs.isEmpty()) return

        val killCount = mobsPerCycle.coerceAtMost(mobs.size)
        for (i in 0 until killCount)
        {
            LOOTING_USED = tier - 1
            mobs[i].attackEntityFrom(GTLiteDamageSources.MOB_SLAUGHTERING, DAMAGE_PER_KILL)
            if (i >= MOBS_PER_CYCLE)
            {
                fluidTank.drain(1, true)
            }
            energyContainer.removeEnergy(energyConsumedPerKill)
        }
    }

    @Suppress("UnstableApiUsage")
    override fun usesMui2(): Boolean = true

    override fun buildUI(guiData: PosGuiData, panelSyncManager: PanelSyncManager, settings: UISettings): ModularPanel
    {
        val fluidSyncHandler = GTFluidSlot.sync(fluidTank).accessibility(true, true)
        return GTGuis.createPanel(this, 170, 146)
            .child(IKey.lang(metaFullName).asWidget().pos(5, 5))
            .child(GTFluidSlot().pos(76, 22).size(18).syncHandler(fluidSyncHandler))
            .child(SlotGroupWidget.playerInventory(true))
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeBoolean(isWorking)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        isWorking = buf.readBoolean()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == IS_WORKING)
        {
            isWorking = buf.readBoolean()
            holder.scheduleRenderUpdate()
        }
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.universal.tooltip.max_voltage_in", energyContainer.inputVoltage, VNF[tier]))
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.energyCapacity))
        tooltip.add(I18n.format("gregtech.universal.tooltip.requires_redstone"))
        tooltip.add(I18n.format("gtlitecore.machine.mob_slaughter.tooltip.1", tier - 1))
        tooltip.add(I18n.format("gtlitecore.machine.mob_slaughter.tooltip.2", energyConsumedPerKill))
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?) {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteOverlays.MOB_SLAUGHTER_OVERLAY.renderOrientedState(renderState, translation, pipeline, Cuboid6.full,
             frontFacing, isWorking, true)
    }
}
