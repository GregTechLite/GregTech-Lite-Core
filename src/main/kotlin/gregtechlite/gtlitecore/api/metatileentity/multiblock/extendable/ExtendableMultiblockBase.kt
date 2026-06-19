package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import com.cleanroommc.modularui.widgets.ButtonWidget
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation

abstract class ExtendableMultiblockBase<T: ExtendableMultiblockBase<T>>(metaTileEntityId: ResourceLocation)
    : MultiblockWithDisplayBase(metaTileEntityId), IWorkable, IControllable, IDataStickIntractable, ExtendableMultiblock<T>
{
    override var additionalStructureManager: AdditionalStructureManager<T> = AdditionalStructureManager(this)
    override val maintenanceProblem: Byte = maintenanceProblems

    override fun <A> getAbilities(ability: MultiblockAbility<A>): List<A>
    {
        val abilities = super.getAbilities(ability).toMutableList()
        abilities.addAll(additionalStructureManager.getAbilities(ability))
        return abilities
    }

    override fun createUIFactory(): MultiblockUIFactory?
    {
        return super.createUIFactory()
            .createFlexButton { _, guiSyncManager ->
                guiSyncManager.registerSyncedAction("refresh_structure_pattern") { reinitializeStructurePattern() }
                return@createFlexButton ButtonWidget()
                    .background(GTLiteMuiTextures.BUTTON_REFRESH_STRUCTURE_PATTERN)
                    .disableHoverBackground()
                    .onMousePressed {
                        guiSyncManager.callSyncedAction("refresh_structure_pattern")
                        true
                    }
                    .tooltip { tooltip ->
                        tooltip.addLine(KeyUtil.lang("gtlitecore.machine.space_elevator.refresh_structure_pattern"))
                    }
            }
    }

    override fun writeToNBT(data: NBTTagCompound?): NBTTagCompound?
    {
        data?.setTag("Additional", additionalStructureManager.serialize())
        return super.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound?)
    {
        super.readFromNBT(data)
        data?.getCompoundTag("Additional")?.let {
            additionalStructureManager.deserialize(it)
                .map { pos -> GTUtility.getMetaTileEntity(world, pos) }
                .filterIsInstance<AdditionalMultiblockBase<T>>()
                .forEach { additionalMultiblockBase -> additionalMultiblockBase.connect(this) }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onDataStickLeftClick(player: EntityPlayer, stack: ItemStack)
    {
        val tag = stack.tagCompound ?: return
        if (tag.hasKey("AdditionalPos"))
        {
            val posTag = tag.getCompoundTag("AdditionalPos")
            val targetPos = BlockPos(posTag.getInteger("X"), posTag.getInteger("Y"), posTag.getInteger("Z"))

            val mte = GTUtility.getMetaTileEntity(world, targetPos)
            if (mte is AdditionalMultiblockBase<*>)
            {
                (mte as AdditionalMultiblockBase<T>).connect(this)
                player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.additional_structure.connected"), true)
            }
            else
            {
                player.sendStatusMessage(TextComponentTranslation("gtlitecore.machine.additional_structure.not_found"), true)
            }
        }
    }

    override fun onDataStickRightClick(p0: EntityPlayer?, p1: ItemStack?): Boolean = false

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeCompoundTag(additionalStructureManager.serialize())
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        buf.readCompoundTag()?.let {
            additionalStructureManager.deserialize(it)
                .map { pos -> GTUtility.getMetaTileEntity(world, pos) }
                .filterIsInstance<AdditionalMultiblockBase<T>>()
                .forEach { additionalMultiblockBase -> additionalMultiblockBase.connect(this) }
        }
    }
}