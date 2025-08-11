package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockAutoclave(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(AUTOCLAVE_RECIPES, VACUUM_CHAMBER_RECIPES))
{

    private var pumpCasingTier = 0
    private var pistonCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = LargeAutoclaveRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.TUNGSTENSTEEL_ROBUST.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state

        private val glassState
            get() = GTGlassCasing.TEMPERED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAutoclave(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        this.pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        this.tier = minOf(pumpCasingTier, pistonCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.pistonCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CGC ", " CGC ", " CGC ", " CCC ")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle("CCQCC", "G#B#G", "G#P#G", "G#B#G", "CCCCC")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle(" CCC ", " CSC ", " CGC ", " CGC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('B', states(pipeCasingState))
        .where('G', states(glassState))
        .where('P', pumpCasings())
        .where('Q', pistonCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.ROBUST_TUNGSTENSTEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_AUTOCLAVE_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeAutoclaveRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
