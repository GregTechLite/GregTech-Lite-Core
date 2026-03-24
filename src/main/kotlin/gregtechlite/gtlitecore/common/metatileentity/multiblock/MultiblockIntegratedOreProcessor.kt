package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.capability.logic.IntegratedOreProcessorRecipeLogic
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

// TODO: FIXME When use ME Advanced Stocking Item Import Bus, item can insert to IOP but not consumed.
class MultiblockIntegratedOreProcessor(id: ResourceLocation)
    : RecipeMapMultiblockController(id, INTEGRATED_ORE_PROCESSOR_RECIPES)
{

    init
    {
        this.recipeMapWorkable = IntegratedOreProcessorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.STEEL_SOLID.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): MetaTileEntity
        = MultiblockIntegratedOreProcessor(metaTileEntityId)

    // TODO: Actual structure pattern
    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("XXX", "XXX", "XXX")
        .aisle("XXX", "X X", "XXX")
        .aisle("XXX", "XSX", "XXX")
        .where('X', states(casingState)
            .or(autoAbilities()))
        .where('S', selfPredicate())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.DISTILLATION_TOWER_OVERLAY

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

}
