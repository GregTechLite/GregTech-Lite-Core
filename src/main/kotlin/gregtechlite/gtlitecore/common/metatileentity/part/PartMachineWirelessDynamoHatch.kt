package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.AbilityInstances
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.wireless.WirelessRole
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class PartMachineWirelessDynamoHatch(
    id: ResourceLocation,
    tier: Int,
    initialAmperage: Int = 2
) : PartMachineWirelessHatch(id, tier, initialAmperage) {

    override val role: WirelessRole = WirelessRole.OUTPUT
    override val bufferCapacityMultiplier: Int = 1

    override fun initializeEnergyContainer() {
        energyContainer = object : EnergyContainerHandler(
            this,
            GTValues.V[tier] * 64L * amperage,
            0L, 0L,
            GTValues.V[tier],
            amperage.toLong()
        ) {
            override fun getEnergyCapacity(): Long = GTValues.V[tier] * 64L * amperage
            override fun getOutputVoltage(): Long = GTValues.V[tier]
            override fun getOutputAmperage(): Long = amperage.toLong()

            override fun changeEnergy(energyToAdd: Long): Long {
                val oldEnergyStored = energyStored
                val cap = getEnergyCapacity()
                val newEnergyStored = if (energyToAdd > 0 && cap - oldEnergyStored < energyToAdd) cap
                    else (oldEnergyStored + energyToAdd).coerceAtLeast(0)
                setEnergyStored(newEnergyStored)
                return newEnergyStored - oldEnergyStored
            }
        }
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity {
        return PartMachineWirelessDynamoHatch(metaTileEntityId, getTier(), amperage)
    }

    override fun getAbility(): MultiblockAbility<IEnergyContainer>? {
        return MultiblockAbility.OUTPUT_ENERGY
    }

    override fun registerAbilities(abilityInstances: AbilityInstances) {
        abilityInstances.add(energyContainer)
    }

    override fun update() {
        super.update()
        if (world.isRemote) return

        updateWireless()

        // Transfer energy from machine to holder buffer every 5 ticks
        if (offsetTimer % 5 == 0L) {
            val holder = wirelessHolder ?: return
            val available = energyContainer.energyStored
            if (available > 0) {
                val canStore = bufferCapacity - holder.buffer
                if (canStore > 0) {
                    val toTransfer = minOf(available, canStore)
                    energyContainer.removeEnergy(toTransfer)
                    val actualAdded = holder.addEnergy(toTransfer)
                    if (actualAdded < toTransfer) {
                        energyContainer.addEnergy(toTransfer - actualAdded)
                    }
                }
            }
        }
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?) {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (shouldRenderOverlay()) {
            Textures.ENERGY_OUT.renderSided(getFrontFacing(), renderState, translation, pipeline)
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean) {
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.screwdriver_info"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_out", GTValues.V[getTier()], GTValues.VNF[getTier()]))
        tooltip.add(I18n.format("gregtech.universal.tooltip.amperage_out_till", getMaxAmperage()))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.configurable"))
    }
}
