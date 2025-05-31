package magicbook.gtlitecore.common.metatileentity.single

import gregtech.api.capability.impl.AbstractRecipeLogic
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMap
import gregtech.client.renderer.ICubeRenderer
import magicbook.gtlitecore.api.capability.logic.MobExtractorRecipeLogic
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOB_EXTRACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.property.MobOnTopProperty
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import java.util.function.Function
import java.util.function.Supplier

class MetaTileEntityMobExtractor(metaTileEntityId: ResourceLocation, recipeMap: RecipeMap<*>?,
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
            return world.getEntitiesWithinAABB(Entity::class.java, boundingBox)
        }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityMobExtractor(metaTileEntityId, MOB_EXTRACTOR_RECIPES, GTLiteTextures.MOB_EXTRACTOR_OVERLAY,
        tier, hasFrontFacing(), tankScalingFunction)

    override fun createWorkable(recipeMap: RecipeMap<*>): AbstractRecipeLogic = MobExtractorRecipeLogic(this, recipeMap, Supplier { energyContainer })

    fun checkRecipe(recipe: Recipe): Boolean
    {
        val entityId = recipe.getProperty<ResourceLocation>(MobOnTopProperty.INSTANCE, null)
        if (entities == null || offsetTimer % 5 * TICK == 0L)
            entities = entitiesInProximity

        for (entity in entities!!)
        {
            if (EntityList.isMatchingName(entity, entityId))
            {
                entityAttackable = entity as? EntityLivingBase
                return true
            }
        }
        return false
    }

}
