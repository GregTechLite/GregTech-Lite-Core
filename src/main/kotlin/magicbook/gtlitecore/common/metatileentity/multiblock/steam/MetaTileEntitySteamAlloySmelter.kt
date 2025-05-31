package magicbook.gtlitecore.common.metatileentity.multiblock.steam

import gregtech.api.capability.impl.SteamMultiWorkable
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapSteamMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.client.particle.VanillaParticleEffects
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockFireboxCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MetaTileEntitySteamAlloySmelter(metaTileEntityId: ResourceLocation) : RecipeMapSteamMultiblockController(metaTileEntityId, ALLOY_SMELTER_RECIPES, CONVERSION_RATE)
{

    init
    {
        recipeMapWorkable = SteamMultiWorkable(this, CONVERSION_RATE)
        recipeMapWorkable.parallelLimit = 8
    }

    companion object
    {
        private val casingState
            get() = if (ConfigHolder.machines.steelSteamMultiblocks) MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID) else MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS)

        private val fireboxCasingState
            get() = if (ConfigHolder.machines.steelSteamMultiblocks) MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.STEEL_FIREBOX) else MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.BRONZE_FIREBOX)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntitySteamAlloySmelter(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "FFF", "FFF", "CCC")
        .aisle("CCC", "F#F", "F#F", "CCC")
        .aisle("CSC", "FFF", "FFF", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities()))
        .where('F', states(fireboxCasingState))
        .where('#', air())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = if (ConfigHolder.machines.steelSteamMultiblocks) Textures.SOLID_STEEL_CASING else Textures.BRONZE_PLATED_BRICKS

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.ALLOY_SMELTER_OVERLAY

    override fun hasMaintenanceMechanics() = false

    override fun getItemOutputLimit() = 1

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gregtech.multiblock.steam_.duration_modifier"))
        tooltip.add(I18n.format("gregtech.universal.tooltip.parallel", 8))
        tooltip.add(TooltipHelper.BLINKING_ORANGE.toString() + I18n.format("gregtech.multiblock.require_steam_parts"))
    }

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick()
    {
        if (isActive) VanillaParticleEffects.defaultFrontEffect(this, 0.4F, EnumParticleTypes.SMOKE_NORMAL)
    }

}