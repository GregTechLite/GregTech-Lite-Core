package gregtechlite.gtlitecore.common.metatileentity.part

import gregtech.api.capability.IControllable
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.IFluidTank

class PartMachineAdvancedMultiFluidHatch(id: ResourceLocation, tier: Int, numSlots: Int, isExportHatch: Boolean)
    : MetaTileEntityMultiFluidHatch(id, tier, numSlots, isExportHatch), IMultiblockAbilityPart<IFluidTank>, IControllable
