package gregtechlite.gtlitecore.common.metatileentity.part

import gregtech.api.capability.IControllable
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.IFluidTank

class PartMachineAdvancedMultiFluidHatch(metaTileEntityId: ResourceLocation?, tier: Int, numSlots: Int, isExportHatch: Boolean) : MetaTileEntityMultiFluidHatch(metaTileEntityId, tier, numSlots, isExportHatch), IMultiblockAbilityPart<IFluidTank>, IControllable
{

    override fun getSubItems(creativeTab: CreativeTabs, subItems: NonNullList<ItemStack>)
    {
        for (hatch in GTLiteMetaTileEntities.QUADRUPLE_FLUID_IMPORT_HATCH)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.QUADRUPLE_FLUID_EXPORT_HATCH)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.NONUPLE_FLUID_IMPORT_HATCH)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.NONUPLE_FLUID_EXPORT_HATCH)
        {
            subItems.add(hatch.stackForm)
        }
    }

}
