package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.capability.impl.NotifiableItemStackHandler
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.extension.toItem
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.DRILLING_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import kotlin.math.max

class MultiblockBedrockDrillingRig(id: ResourceLocation) : RecipeMapMultiblockController(id, DRILLING_RECIPES)
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
        private val casingState = MetalCasing.TRINAQUADALLOY.state
        private val secondCasingState = GTMetalCasing.STEEL_SOLID.state
        private val gearboxCasingState = GTTurbineCasing.TUNGSTENSTEEL_GEARBOX.state
        private val thirdCasingState = MultiblockCasing.DRILL_HEAD.state
        private val fourthCasingState = GTMultiblockCasing.GRATE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockBedrockDrillingRig(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        tier = minOf(pistonCasingTier, motorCasingTier)

        // Transformed targetBlock from blockPos in inputInventory.
        targetBlock?.let {
            inputInventory.setStackInSlot(0, world.getBlockState(it).toItem())
        }
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        motorCasingTier = 0
        targetBlock = null
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

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.bedrock_drilling_rig.tooltip.1",
                               "gtlitecore.machine.bedrock_drilling_rig.tooltip.2",
                               "gtlitecore.machine.bedrock_drilling_rig.tooltip.3")
            addOverclockInfo(OverclockMode.PERFECT)
            addParallelInfo(UpgradeMode.PISTON_CASING, 16)
            addDurationInfo(UpgradeMode.MOTOR_CASING, 250)
            addEnergyInfo(20)
        }
    }

    override fun canBeDistinct() = false

    override fun hasMufflerMechanics() = true

    private inner class BedrockDrillingRigWorkableHandler(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
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

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -20%
            ocResult.setEut(max(1, (ocResult.eut() * 0.8).toLong()))

            // +250% / motor casing tier | D' = D / (1 + 2.5 * (T - 1.0)) = D / (2.5 * T - 1.5), where k = 2.5
            if (motorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.5 * motorCasingTier - 1.5)).toInt()))
        }

        override fun getParallelLimit(): Int = 16 * pistonCasingTier

    }

}
