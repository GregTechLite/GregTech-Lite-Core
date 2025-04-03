package magicbook.gtlitecore.common.metatileentity.part;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IQuantumStorage;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityAdvancedEnergyHatch extends MetaTileEntityEnergyHatch implements IMultiblockAbilityPart<IEnergyContainer>, IQuantumStorage<IEnergyContainer>
{

    public MetaTileEntityAdvancedEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amperage, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, amperage, isExportHatch);
    }

    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems)
    {
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.ENERGY_HATCH_4A)
            if (hatch != null) subItems.add(hatch.getStackForm());
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.DYNAMO_HATCH_4A)
            if (hatch != null) subItems.add(hatch.getStackForm());
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.ENERGY_HATCH_16A)
            if (hatch != null) subItems.add(hatch.getStackForm());
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.DYNAMO_HATCH_16A)
            if (hatch != null) subItems.add(hatch.getStackForm());
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.SUBSTATION_ENERGY_HATCH_64A)
            if (hatch != null) subItems.add(hatch.getStackForm());
        for (MetaTileEntityEnergyHatch hatch : GTLiteMetaTileEntities.SUBSTATION_DYNAMO_HATCH_64A)
            if (hatch != null) subItems.add(hatch.getStackForm());
    }

}
