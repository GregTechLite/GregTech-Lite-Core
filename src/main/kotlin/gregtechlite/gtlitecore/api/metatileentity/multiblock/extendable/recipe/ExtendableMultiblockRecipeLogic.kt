package gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.recipe

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalStructureManager

open class ExtendableMultiblockRecipeLogic<T: RecipeMapExtendableMultiblock<T>>(controller: RecipeMapExtendableMultiblock<T>,
                                                                                protected val manager: AdditionalStructureManager<T>)
    : MultiblockRecipeLogic(controller)
{}