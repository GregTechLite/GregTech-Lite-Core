package gregtechlite.gtlitecore.common.metatileentity.part

import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.IQuantumStorage
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation

class PartMachineAdvancedEnergyHatch(metaTileEntityId: ResourceLocation?, tier: Int, amperage: Int, isExportHatch: Boolean) : MetaTileEntityEnergyHatch(metaTileEntityId, tier, amperage, isExportHatch), IMultiblockAbilityPart<IEnergyContainer>, IQuantumStorage<IEnergyContainer>
{

    override fun getSubItems(creativeTab: CreativeTabs, subItems: NonNullList<ItemStack>)
    {
        for (hatch in GTLiteMetaTileEntities.ENERGY_HATCH_4A)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.DYNAMO_HATCH_4A)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.ENERGY_HATCH_16A)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.DYNAMO_HATCH_16A)
        {
            subItems.add(hatch.stackForm)
        }

        for (hatch in GTLiteMetaTileEntities.SUBSTATION_ENERGY_HATCH_64A)
        {
            subItems.add(hatch.stackForm)
        }
        for (hatch in GTLiteMetaTileEntities.SUBSTATION_DYNAMO_HATCH_64A)
        {
            subItems.add(hatch.stackForm)
        }
    }

}
