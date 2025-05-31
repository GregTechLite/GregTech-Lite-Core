package magicbook.gtlitecore.api.metatileentity

import codechicken.lib.raytracer.CuboidRayTraceResult
import gregtech.api.capability.impl.AbstractRecipeLogic
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.client.renderer.ICubeRenderer
import magicbook.gtlitecore.api.capability.logic.PseudoMultiRecipeLogic
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import java.util.function.Function

open class PseudoMultiMachineMetaTileEntity(metaTileEntityId: ResourceLocation?, recipeMap: RecipeMap<*>?, renderer: ICubeRenderer?, tier: Int, hasFrontFacing: Boolean, tankScalingFunction: Function<Int, Int>) : SimpleMachineMetaTileEntity(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction)
{

    var targetBlockState: IBlockState? = null

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = PseudoMultiMachineMetaTileEntity(metaTileEntityId,
        workable.recipeMap, renderer, tier, hasFrontFacing(), tankScalingFunction)

    override fun createWorkable(recipeMap: RecipeMap<*>?): AbstractRecipeLogic = PseudoMultiRecipeLogic(this, recipeMap, { energyContainer })

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
