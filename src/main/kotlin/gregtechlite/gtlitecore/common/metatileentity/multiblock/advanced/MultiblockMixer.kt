package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockMixer(id: ResourceLocation)
    : RecipeMapMultiblockController(id, LARGE_MIXER_RECIPES)
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeMixerRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.STABALLOY.state

        private val gearboxCasingState
            get() = GTTurbineCasing.TITANIUM_GEARBOX.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockMixer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CCC ", " CCC ")
        .aisle("CCCCC", "C#G#C", " CCC ")
        .aisle("CCMCC", "C#M#C", " CCC ")
        .aisle("CCCCC", "C#G#C", " CCC ")
        .aisle(" CCC ", " CSC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(6)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(gearboxCasingState))
        .where('M', motorCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.STABALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_mixer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mixer.tooltip.2"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mixer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mixer.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeMixerRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(GTUtility.getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 8 * casingTier

    }

}
