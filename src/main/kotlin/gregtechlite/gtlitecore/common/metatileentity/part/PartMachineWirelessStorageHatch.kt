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
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates
import gregtechlite.gtlitecore.api.wireless.WirelessRole
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class PartMachineWirelessStorageHatch(
    id: ResourceLocation,
    tier: Int,
    initialAmperage: Int = 2
) : PartMachineWirelessHatch(id, tier, initialAmperage) {

    override val role: WirelessRole = WirelessRole.STORAGE
    override val bufferCapacityMultiplier: Int = 2

    init {
        MultiblockAbility.registerMultiblockAbility(TraceabilityPredicates.WIRELESS_ENERGY_STORAGE, this)
    }

    override fun initializeEnergyContainer() {
        energyContainer = object : EnergyContainerHandler(
            this,
            GTValues.V[tier] * 64L * amperage,
            GTValues.V[tier], amperage.toLong(),
            GTValues.V[tier], amperage.toLong()
        ) {
            override fun getEnergyStored(): Long {
                val holder = wirelessHolder ?: return 0L
                val halfFull = bufferCapacity / 2
                return if (holder.buffer > halfFull) holder.buffer - halfFull else 0L
            }

            override fun getEnergyCapacity(): Long {
                val holder = wirelessHolder ?: return 0L
                val halfFull = bufferCapacity / 2
                // Push mode: capacity = stored → substation sees no free space → won't fill
                // Pull mode: capacity = deficit → substation fills exactly to halfFull
                return if (holder.buffer >= halfFull) getEnergyStored()
                    else halfFull - holder.buffer
            }

            override fun changeEnergy(energyToAdd: Long): Long {
                val holder = wirelessHolder ?: return 0L
                return if (energyToAdd >= 0) {
                    holder.addEnergy(energyToAdd)
                } else {
                    -holder.removeEnergy(-energyToAdd)
                }
            }

            private fun isPushMode(): Boolean {
                val holder = wirelessHolder ?: return false
                return holder.buffer >= bufferCapacity / 2
            }

            override fun getInputVoltage(): Long =
                if (!isPushMode()) GTValues.V[tier].toLong() else 0L
            override fun getInputAmperage(): Long =
                if (!isPushMode()) amperage.toLong() else 0L
            override fun getOutputVoltage(): Long =
                if (isPushMode()) GTValues.V[tier].toLong() else 0L
            override fun getOutputAmperage(): Long =
                if (isPushMode()) amperage.toLong() else 0L
        }
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?): MetaTileEntity {
        return PartMachineWirelessStorageHatch(metaTileEntityId, getTier(), amperage)
    }

    override fun getAbility(): MultiblockAbility<IEnergyContainer>? = null

    override fun getAbilities(): List<MultiblockAbility<*>> {
        return listOf(TraceabilityPredicates.WIRELESS_ENERGY_STORAGE)
    }

    override fun registerAbilities(abilityInstances: AbilityInstances) {
        abilityInstances.add(energyContainer)
    }

    override fun update() {
        super.update()
        if (world.isRemote) return

        updateWireless()
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?) {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        if (shouldRenderOverlay()) {
            Textures.ENERGY_IN.renderSided(getFrontFacing(), renderState, translation, pipeline)
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean) {
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.screwdriver_info"))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.interactive_voltage", GTValues.V[getTier()], GTValues.VNF[getTier()]))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.interactive_amperage", getMaxAmperage()))
        tooltip.add(I18n.format("gtlitecore.machine.wireless_hatch.configurable"))
    }
}
