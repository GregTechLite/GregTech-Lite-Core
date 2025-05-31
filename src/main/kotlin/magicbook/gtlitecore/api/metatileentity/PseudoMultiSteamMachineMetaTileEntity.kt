package magicbook.gtlitecore.api.metatileentity

import codechicken.lib.raytracer.CuboidRayTraceResult
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.client.renderer.ICubeRenderer
import lombok.Getter
import lombok.Setter
import magicbook.gtlitecore.api.capability.logic.PseudoMultiSteamRecipeLogic
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicator
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation

open class PseudoMultiSteamMachineMetaTileEntity(metaTileEntityId: ResourceLocation?, recipeMap: RecipeMap<*>?, progressBarIndicator: SteamProgressBarIndicator?, renderer: ICubeRenderer?, isBrickedCasing: Boolean, isHighPressure: Boolean) : SimpleSteamMachineMetaTileEntity(metaTileEntityId, recipeMap, progressBarIndicator, renderer, isBrickedCasing, isHighPressure)
{

    var targetBlockState: IBlockState? = null

    init
    {
        workableHandler = PseudoMultiSteamRecipeLogic(this, recipeMap, isHighPressure, steamFluidTank, 1.0)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = PseudoMultiSteamMachineMetaTileEntity(metaTileEntityId,
        workableHandler.recipeMap, progressBarIndicator, renderer, isBrickedCasing, isHighPressure)

    override fun onLoad()
    {
        super.onLoad()
        checkAdjacentBlocks()
    }

    override fun onPlacement(placer: EntityLivingBase?)
    {
        super.onPlacement(placer)
        checkAdjacentBlocks()
    }

    override fun onNeighborChanged()
    {
        super.onNeighborChanged()
        checkAdjacentBlocks()
    }

    override fun onWrenchClick(playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitResult: CuboidRayTraceResult): Boolean
    {
        val wrenchClickSucceeded = super.onWrenchClick(playerIn, hand, facing, hitResult)
        if (wrenchClickSucceeded)
            checkAdjacentBlocks()
        return wrenchClickSucceeded
    }

    override fun getIsWeatherOrTerrainResistant() = true

    fun checkAdjacentBlocks()
    {
        if (world == null || world.isRemote)
        {
            targetBlockState = null
            return
        }
        // The traditional "back" side of this type of MetaTileEntity is actually treated
        // as its front for recipe purposes, making wrench movement feel as though you are
        // holding onto or manipulating the back side to point the MetaTileEntity.
        targetBlockState = world.getBlockState(pos.offset(getFrontFacing().opposite))
    }

}
