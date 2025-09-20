package gregtechlite.gtlitecore.common.metatileentity.single

import gregtech.api.capability.impl.AbstractRecipeLogic
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.capability.logic.MobExtractorRecipeLogic
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.property.MobOnTopProperty
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import java.util.function.Function

class MachineMobExtractor(metaTileEntityId: ResourceLocation, recipeMap: RecipeMap<*>?,
                          renderer: ICubeRenderer?, tier: Int, hasFrontFacing: Boolean,
                          tankScalingFunction: Function<Int?, Int?>?) : SimpleMachineMetaTileEntity(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction)
{

    private var boundingBox: AxisAlignedBB? = null
    private var entityAttackable: EntityLivingBase? = null
    private var entities: MutableList<Entity>? = null
    private val entitiesInProximity: MutableList<Entity>
        get()
        {
            if (boundingBox == null)
                boundingBox = AxisAlignedBB(pos.up())
            return world.getEntitiesWithinAABB(Entity::class.java, boundingBox!!)
        }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MachineMobExtractor(metaTileEntityId, MOB_EXTRACTOR_RECIPES, GTLiteOverlays.MOB_EXTRACTOR_OVERLAY,
                                                                                             tier, hasFrontFacing(), tankScalingFunction)

    override fun createWorkable(recipeMap: RecipeMap<*>): AbstractRecipeLogic = MobExtractorRecipeLogic(this, recipeMap) { energyContainer }

    fun checkRecipe(recipe: Recipe): Boolean
    {
        val entityId = recipe.getProperty(MobOnTopProperty, null)
        if (entities == null || offsetTimer % 5 * TICK == 0L)
            entities = entitiesInProximity

        for (entity in entities!!)
        {
            if (EntityList.isMatchingName(entity, entityId!!))
            {
                entityAttackable = entity as? EntityLivingBase
                return true
            }
        }
        return false
    }

}
