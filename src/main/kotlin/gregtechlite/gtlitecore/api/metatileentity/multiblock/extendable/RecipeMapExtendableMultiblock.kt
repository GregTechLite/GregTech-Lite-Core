package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable

import com.cleanroommc.modularui.widgets.ButtonWidget
import gregtech.api.capability.IControllable
import gregtech.api.capability.IDataStickIntractable
import gregtech.api.capability.IWorkable
import gregtech.api.metatileentity.multiblock.MultiblockAbility
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.GTUtility
import gregtech.api.util.KeyUtil
import gregtechlite.gtlitecore.api.capability.logic.ExtendableMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation

abstract class RecipeMapExtendableMultiblock<T : RecipeMapExtendableMultiblock<T>>(id: ResourceLocation, recipeMap: RecipeMap<*>)
    : RecipeMapMultiblockController(id, recipeMap), IWorkable, IControllable, IDataStickIntractable, ExtendableMultiblock<T>
{
    override var additionalStructureManager: AdditionalStructureManager<T> = AdditionalStructureManager(this)

    override val maintenanceProblem: Byte = maintenanceProblems

    init
    {
        recipeMapWorkable = ExtendableMultiblockRecipeLogic(this, additionalStructureManager)
    }

    override fun <A> getAbilities(ability: MultiblockAbility<A>): List<A>
    {
        val abilities = super.getAbilities(ability).toMutableList()
        abilities.addAll(additionalStructureManager.getAbilities(ability))
        return abilities
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

    override fun onDataStickRightClick(player: EntityPlayer, stack: ItemStack): Boolean = false

    override fun getProgress(): Int = recipeMapWorkable.progress

    override fun getMaxProgress(): Int = recipeMapWorkable.maxProgress

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