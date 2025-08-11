package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockFireboxCasing
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFireboxCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockBurnerReactor(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(BURNER_REACTOR_RECIPES, ROASTER_RECIPES))
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeBurnerReactorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.INCOLOY_MA813.state

        private val pipeCasingState
            get() = GTBoilerCasing.TITANIUM_PIPE.state

        private val fireboxCasingState
            get() = GTFireboxCasing.TITANIUM_FIREBOX.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockBurnerReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
        this.replaceFireboxAsActive(false)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("     ", "     ", " P P ", " P P ", " P P ")
        .aisle("F   F", "FBBBF", "XPXPX", "XXXXX", " P P ")
        .aisle("     ", "XBBBX", "XPNPX", "XPMPX", " P P ")
        .aisle("F   F", "FBBBF", "XXSXX", "XXXXX", "     ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(14)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('P', states(pipeCasingState))
        .where('B', states(fireboxCasingState))
        .where('F', frames(IncoloyMA813))
        .where('M', abilities(MUFFLER_HATCH))
        .where('N', motorCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.INCOLOY_MA813_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_BURNER_REACTOR_OVERLAY

    override fun onRemoval()
    {
        super.onRemoval()
        if (!world.isRemote && isStructureFormed)
        {
            // Replaced Firebox Casing textures as active textures.
            this.replaceFireboxAsActive(false)
        }
    }

    fun replaceFireboxAsActive(isActive: Boolean)
    {
        val centerPos = pos.offset(getFrontFacing().opposite).down()
        for (x in -1..1)
        {
            for (z in -1..1)
            {
                val blockPos = centerPos.add(x, 0, z)
                var blockState = world.getBlockState(blockPos)
                if (blockState.getBlock() is BlockFireboxCasing)
                {
                    blockState = (blockState as IExtendedBlockState).withProperty(BlockFireboxCasing.ACTIVE, isActive)
                    world.setBlockState(blockPos, blockState)
                }
            }
        }
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeBurnerReactorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
