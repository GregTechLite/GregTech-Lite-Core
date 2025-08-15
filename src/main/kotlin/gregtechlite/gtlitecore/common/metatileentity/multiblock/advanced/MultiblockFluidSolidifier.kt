package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LAMINATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOOL_CASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VULCANIZATION_RECIPES
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockFluidSolidifier(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(FLUID_SOLIDFICATION_RECIPES, TOOL_CASTER_RECIPES,
                                               LAMINATOR_RECIPES, VULCANIZATION_RECIPES))
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeFluidSolidifierRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.STEEL_SOLID.state

        private val pipeCasingState
            get() = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockFluidSolidifier(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "CPC", "CCC")
        .aisle("CCC", "CPC", "CCC")
        .aisle("QQQ", "QSQ", "QQQ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(2)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.FLUID_SOLIDIFIER_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.1"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_fluid_solidifier.tooltip.3"))
    }

    override fun canBeDistinct() = true

    private inner class LargeFluidSolidifierRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(casingTier))).toInt())
        }

        override fun getParallelLimit() = 4 * getTierByVoltage(maxVoltage)

    }

}
