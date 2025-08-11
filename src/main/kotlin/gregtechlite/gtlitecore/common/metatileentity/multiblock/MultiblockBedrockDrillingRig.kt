package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.*
import gregtech.api.recipes.Recipe
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.extension.toItem
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.DRILLING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.floor
import kotlin.math.pow

class MultiblockBedrockDrillingRig(id: ResourceLocation)
    : RecipeMapMultiblockController(id, DRILLING_RECIPES)
{

    private var pistonCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    // Target block at drill head block in multiblock structure bottom.
    private var targetBlock: BlockPos? = null

    init
    {
        recipeMapWorkable = BedrockDrillingRigWorkableHandler(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.TRINAQUADALLOY.state

        private val secondCasingState
            get() = GTMetalCasing.STEEL_SOLID.state

        private val gearboxCasingState
            get() = GTTurbineCasing.TUNGSTENSTEEL_GEARBOX.state

        private val thirdCasingState
            get() = MultiblockCasing.DRILL_HEAD.state

        private val fourthCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockBedrockDrillingRig(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        this.motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        this.tier = minOf(pistonCasingTier, motorCasingTier)

        // Transformed targetBlock from blockPos in inputInventory.
        targetBlock?.let {
            inputInventory.setStackInSlot(0, world.getBlockState(it).toItem())
        }
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pistonCasingTier = 0
        this.motorCasingTier = 0
        this.targetBlock = null
        // inputInventory.setStackInSlot(0, ItemStack.EMPTY)
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        inputInventory = NotifiableItemStackHandler(this, 1, this, false)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
        .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
        .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CVC  ", "  CVC  ", "X BBB X")
        .aisle("   R   ", "X  D  X", "  COC  ", "  CQC  ", "  CGC  ", "  VGV  ", "  VGV  ", "XFBBBFX")
        .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CSC  ", "  CVC  ", "X BBB X")
        .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
        .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
        .where('S', selfPredicate())
        .where('X', states(casingState))
        .where('B', states(secondCasingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, false, true, false, true, true)))
        .where('C', states(secondCasingState))
        .where('D', states(thirdCasingState))
        .where('F', frames(HSLASteel))
        .where('G', states(gearboxCasingState))
        .where('V', states(fourthCasingState))
        .where('O', pistonCasings())
        .where('Q', motorCasings())
        .where('R', blockPredicate())
        .where(' ', any())
        .build()

    // @formatter:on

    private fun blockPredicate() = TraceabilityPredicate { blockWorldState: BlockWorldState ->
        targetBlock = blockWorldState.pos
        if (isStructureFormed)
        {
            inputInventory.setStackInSlot(0, world.getBlockState(targetBlock!!).toItem())
        }
        return@TraceabilityPredicate true
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.6"))
    }

    override fun canBeDistinct() = false

    override fun hasMufflerMechanics() = true

    private inner class BedrockDrillingRigWorkableHandler(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte, true)
    {

        override fun getMetaTileEntity() = super.getMetaTileEntity() as MultiblockBedrockDrillingRig

        override fun setupAndConsumeRecipeInputs(recipe: Recipe, importInventory: IItemHandlerModifiable): Recipe?
        {
            if (!recipe.inputs[0].isNonConsumable)
            {
                val mte = getMetaTileEntity()
                checkNotNull(mte)
                mte.world.destroyBlock(mte.targetBlock!!, false)
            }
            return super.setupAndConsumeRecipeInputs(recipe, importInventory)
        }

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(motorCasingTier)).toInt())
        }

        override fun getParallelLimit(): Int = 16 * pistonCasingTier

    }

}
