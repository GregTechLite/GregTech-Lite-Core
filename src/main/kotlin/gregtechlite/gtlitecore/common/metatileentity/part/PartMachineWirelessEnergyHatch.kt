package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VNF
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.wireless.WirelessRole
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class PartMachineWirelessEnergyHatch(id: ResourceLocation, tier: Int, initialAmperage: Int = 2)
    : PartMachineWirelessHatch(id, tier, initialAmperage)
{
    override val role: WirelessRole = WirelessRole.INPUT
    override val bufferCapacityMultiplier: Int = 1

    override fun initializeEnergyContainer()
    {
        energyContainer = object : EnergyContainerHandler(this, V[tier] * 20L * amperage, V[tier], amperage.toLong(), 0L, 0L)
        {
            override fun getEnergyCapacity(): Long = V[tier] * 20L * amperage

            override fun getInputVoltage(): Long = V[tier]

            override fun getInputAmperage(): Long = amperage.toLong()

            override fun changeEnergy(energyToAdd: Long): Long
            {
                val oldEnergyStored = energyStored
                val cap = getEnergyCapacity()
                val newEnergyStored = if (energyToAdd > 0 && cap - oldEnergyStored < energyToAdd) cap
                        else (oldEnergyStored + energyToAdd).coerceAtLeast(0)
                setEnergyStored(newEnergyStored)
                return newEnergyStored - oldEnergyStored
            }
        }
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = PartMachineWirelessEnergyHatch(metaTileEntityId, tier, amperage)

    override fun getAbility(): MultiblockAbility<IEnergyContainer> = MultiblockAbility.INPUT_ENERGY

    override fun registerAbilities(abilityInstances: AbilityInstances)
    {
        abilityInstances.add(energyContainer)
    }

    override fun update()
    {
        super.update()
        if (world.isRemote) return

        updateWireless()
        if (offsetTimer % (1 * SECOND) == 0L)
        {
            val holder = wirelessHolder ?: return
            if (holder.buffer > 0)
            {
                val canAccept = energyContainer.energyCanBeInserted
                if (canAccept > 0)
                {
                    val toTransfer = minOf(holder.buffer, canAccept)
                    energyContainer.addEnergy(toTransfer)
                    holder.removeEnergy(toTransfer)
                }
            }
        }
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (shouldRenderOverlay())
        {
            GTLiteOverlays.WIRELESS_ENERGY_HATCH_OVERLAY.renderSided(getFrontFacing(), renderState, translation, pipeline)
        }
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_energy_hatch.tooltip.6"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", V[tier], VNF[tier]))
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_in_till", getMaxAmperage()))
    }
}
