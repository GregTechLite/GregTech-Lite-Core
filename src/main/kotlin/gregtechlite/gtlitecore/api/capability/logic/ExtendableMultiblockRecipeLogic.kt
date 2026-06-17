package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalStructureManager
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.RecipeMapExtendableMultiblock

open class ExtendableMultiblockRecipeLogic<T: RecipeMapExtendableMultiblock<T>>(controller: RecipeMapExtendableMultiblock<T>,
                                                                                protected val manager: AdditionalStructureManager<T>
)
    : MultiblockRecipeLogic(controller)
{}