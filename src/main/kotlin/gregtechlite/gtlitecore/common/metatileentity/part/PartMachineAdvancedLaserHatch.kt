package gregtechlite.gtlitecore.common.metatileentity.part

import gregtech.api.capability.ILaserContainer
import gregtech.api.metatileentity.IDataInfoProvider
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityLaserHatch
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation

class PartMachineAdvancedLaserHatch(metaTileEntityId: ResourceLocation?, tier: Int, amperage: Int, isExport: Boolean) : MetaTileEntityLaserHatch(metaTileEntityId, isExport, tier, amperage), IMultiblockAbilityPart<ILaserContainer>, IDataInfoProvider
{

    override fun getSubItems(creativeTab: CreativeTabs, subItems: NonNullList<ItemStack>)
    {
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_16384)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_16384)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_65536)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_65536)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_262144)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_262144)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_1048576)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_1048576)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_4194304)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_4194304)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_INPUT_HATCH_16777216)
        {
            subItems.add(bus.stackForm)
        }
        for (bus in GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_16777216)
        {
            subItems.add(bus.stackForm)
        }
    }

}
