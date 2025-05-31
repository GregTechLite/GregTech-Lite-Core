package magicbook.gtlitecore.common.metatileentity.single;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import magicbook.gtlitecore.api.capability.logic.MobExtractorRecipeLogic;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.recipe.property.MobOnTopProperty;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

import static magicbook.gtlitecore.api.utils.GTLiteValues.TICK;

public class MetaTileEntityMobExtractor extends SimpleMachineMetaTileEntity
{

    private AxisAlignedBB boundingBox;
    private EntityLivingBase entityAttackable;
    private List<Entity> entities;

    public MetaTileEntityMobExtractor(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                      ICubeRenderer renderer, int tier, boolean hasFrontFacing,
                                      Function<Integer, Integer> tankScalingFunction)
    {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityMobExtractor(this.metaTileEntityId, GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES(),
                GTLiteTextures.MOB_EXTRACTOR_OVERLAY, this.getTier(), this.hasFrontFacing(), this.getTankScalingFunction());
    }

    @Override
    protected AbstractRecipeLogic createWorkable(RecipeMap<?> recipeMap)
    {
        return new MobExtractorRecipeLogic(this, recipeMap, () -> energyContainer);
    }

    public boolean checkRecipe(@NotNull Recipe recipe)
    {
        ResourceLocation entityId = recipe.getProperty(MobOnTopProperty.INSTANCE, null);
        if (this.entities == null || this.getOffsetTimer() % 5 * TICK == 0)
            this.entities = getEntitiesInProximity();
        // Prepare to causeDamage if needed, this is still TODO.
        for (Entity entity : entities)
        {
            if (EntityList.isMatchingName(entity, entityId))
            {
                if (entity instanceof EntityLivingBase)
                    entityAttackable = (EntityLivingBase) entity;
                else
                    entityAttackable = null;
                return true;
            }
        }
        return false;
    }

    protected List<Entity> getEntitiesInProximity()
    {
        if (boundingBox == null)
            boundingBox = new AxisAlignedBB(this.getPos().up());
        return this.getWorld().getEntitiesWithinAABB(Entity.class, boundingBox);
    }

    // TODO damageEntity?

}
