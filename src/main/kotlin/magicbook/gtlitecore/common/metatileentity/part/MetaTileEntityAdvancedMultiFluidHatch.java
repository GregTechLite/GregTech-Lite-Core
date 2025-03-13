package magicbook.gtlitecore.common.metatileentity.part;

import gregtech.api.capability.IControllable;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;

public class MetaTileEntityAdvancedMultiFluidHatch extends MetaTileEntityMultiFluidHatch implements IMultiblockAbilityPart<IFluidTank>, IControllable
{

    public MetaTileEntityAdvancedMultiFluidHatch(ResourceLocation metaTileEntityId, int tier, int numSlots, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, numSlots, isExportHatch);
    }

    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems)
    {
        for (MetaTileEntityMultiFluidHatch hatch : GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_IMPORT_HATCH())
        {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityMultiFluidHatch hatch : GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_EXPORT_HATCH())
        {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityMultiFluidHatch hatch : GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_IMPORT_HATCH())
        {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityMultiFluidHatch hatch : GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_EXPORT_HATCH())
        {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
    }
}
