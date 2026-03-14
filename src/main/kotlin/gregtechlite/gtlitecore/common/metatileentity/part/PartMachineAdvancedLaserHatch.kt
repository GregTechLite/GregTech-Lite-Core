package gregtechlite.gtlitecore.common.metatileentity.part

import gregtech.api.capability.ILaserContainer
import gregtech.api.metatileentity.IDataInfoProvider
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityLaserHatch
import net.minecraft.util.ResourceLocation

class PartMachineAdvancedLaserHatch(id: ResourceLocation, tier: Int, amperage: Int, isExport: Boolean)
    : MetaTileEntityLaserHatch(id, isExport, tier, amperage), IMultiblockAbilityPart<ILaserContainer>, IDataInfoProvider
