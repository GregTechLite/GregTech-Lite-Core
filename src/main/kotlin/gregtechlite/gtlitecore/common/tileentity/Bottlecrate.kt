package gregtechlite.gtlitecore.common.tileentity

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.Constants


class Bottlecrate: TileEntity() {

    private var inventory = Array<ItemStack>(9){ ItemStack.EMPTY }
    private var dirtySlots = mutableSetOf<Int>()

    fun getStackInSlot(slot:Int): ItemStack{
        return inventory[slot]
    }

    fun setStackInSlot(slot:Int,item: ItemStack){
        inventory[slot] = item
        markDirtySlot(slot)
        markDirty()
    }


    fun insertItem(item: ItemStack): Boolean{

        for (i in 0..8){
            if(getStackInSlot(i).isEmpty){
                setStackInSlot(i,item)
                return true
            }
        }
        return false
    }

    fun getInventory(): Array<ItemStack>{
        return inventory
    }

    fun markDirtySlot(slot:Int){
        if ((0<=slot) and (slot<9)){
            dirtySlots.add(slot)
        }
    }

    fun readDirtySlotsFromNBT(compound: NBTTagCompound){
        val list = compound.getTagList("DirtySlots", Constants.NBT.TAG_COMPOUND)
        for ( i in 0 until list.tagCount()){
            val slotTag = list.getCompoundTagAt(i)
            val slot = slotTag.getByte("Slot").toInt()
            if ((0<=slot) and (slot<9)){
                inventory[slot] = ItemStack(slotTag)
            }
        }
    }

    fun writeDirtySlotsToNBT(compound: NBTTagCompound): NBTTagCompound{
        val l = NBTTagList()
        dirtySlots.forEach {index->
            val slotTag = NBTTagCompound()
            slotTag.setByte("Slot",index.toByte())
            inventory[index].writeToNBT(slotTag)
            l.appendTag(slotTag)
        }
        compound.setTag("DirtySlots",l)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
        inventory.fill(ItemStack.EMPTY)

        val list = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND)
        for (i in 0..<list.tagCount()) {
            val slotTag = list.getCompoundTagAt(i)
            val slot = slotTag.getByte("Slot").toInt()
            inventory[slot] = ItemStack(slotTag)
        }
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(compound)
        val list = NBTTagList()
        inventory.forEachIndexed {i,slot->
            if (!slot.isEmpty ) {
                val slotTag = NBTTagCompound()
                slotTag.setByte("Slot", i.toByte())
                slot.writeToNBT(slotTag)
                list.appendTag(slotTag)
            }
        }
        compound.setTag("Items",list)
        return compound
    }
    override fun getUpdatePacket(): SPacketUpdateTileEntity? {
        val compound = NBTTagCompound()

        return if (dirtySlots.isEmpty()){
            SPacketUpdateTileEntity(pos, 0, updateTag)
        }else{
            writeDirtySlotsToNBT(compound)
            dirtySlots.clear()
            SPacketUpdateTileEntity(pos, 0, compound)
        }
    }

    override fun getUpdateTag(): NBTTagCompound {
        return writeToNBT(NBTTagCompound())
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        if (pkt.nbtCompound.hasKey("DirtySlots")){
            readDirtySlotsFromNBT(pkt.nbtCompound)
        }else{
            readFromNBT(pkt.nbtCompound)
        }
    }

}