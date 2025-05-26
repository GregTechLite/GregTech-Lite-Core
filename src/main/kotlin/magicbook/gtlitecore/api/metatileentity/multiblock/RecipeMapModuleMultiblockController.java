package magicbook.gtlitecore.api.metatileentity.multiblock;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import magicbook.gtlitecore.api.capability.IModuleProvider;
import magicbook.gtlitecore.api.capability.IModuleReceiver;
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;

public abstract class RecipeMapModuleMultiblockController extends RecipeMapMultiblockController implements IModuleReceiver
{

    protected final int tier;
    protected final int moduleTier;
    protected final int minCasingTier;

    protected IModuleProvider moduleProvider;

    protected final long energyConsumed;

    /**
     * @param tier          The voltage tier of this mte.
     * @param moduleTier    The inner tier of the module.
     * @param minCasingTier The minimum casing tier of this module required, this is useful for some
     *                      tiered status predicate.
     */
    public RecipeMapModuleMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                               int tier, int moduleTier, int minCasingTier)
    {
        super(metaTileEntityId, recipeMap);
        this.tier = tier;
        this.moduleTier = moduleTier;
        this.minCasingTier = minCasingTier;
        this.energyConsumed = (long) (Math.pow(4, this.tier + 2) / 2);
        this.energyContainer = new EnergyContainerHandler(this,
                (long) (160_008_000L * Math.pow(4, this.tier - 9)), this.energyConsumed,
                1, 0, 0);
    }

    @Override
    public void checkStructurePattern()
    {
        if (getModuleProvider() != null)
        {
            if (getModuleProvider().getCasingTier() >= minCasingTier)
                super.checkStructurePattern();
        }
    }

    @Override
    protected abstract void initializeAbilities();

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        setModuleProvider(null);
    }

    @NotNull
    @Override
    protected abstract BlockPattern createStructurePattern();

    @Override
    public IEnergyContainer getEnergyContainer()
    {
        if (getModuleProvider() == null || getModuleProvider().getSubEnergyContainer() == null)
        {
            return new EnergyContainerHandler(this, 0, 0, 0, 0, 0);
        }
        else
        {
            return this.energyContainer;
        }
    }

    @Override
    public void sentWorkingDisabled()
    {
        this.recipeMapWorkable.setWorkingEnabled(false);
    }

    @Override
    public void sentWorkingEnabled()
    {
        this.recipeMapWorkable.setWorkingEnabled(true);
    }

    @Override
    public String getDisplayCountName()
    {
        return getMetaName() + ".display_count";
    }

    @Override
    protected void updateFormedValid()
    {
        super.updateFormedValid();
        if (getOffsetTimer() % SECOND == 0 && getModuleProvider() != null)
        {
            if (this.energyContainer.getEnergyCapacity() != this.energyContainer.getEnergyStored()
                    && getModuleProvider().getSubEnergyContainer().getEnergyStored() > this.energyConsumed * SECOND)
            {
                long simulate = this.energyContainer.getEnergyCapacity() - this.energyContainer.getEnergyStored();
                getModuleProvider().getSubEnergyContainer().removeEnergy(simulate);
                this.energyContainer.addEnergy(simulate);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public abstract ICubeRenderer getBaseTexture(IMultiblockPart sourcePart);

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected abstract ICubeRenderer getFrontOverlay();

    @NotNull
    @Override
    protected Widget getFlexButton(int x, int y, int width, int height)
    {
        return new ClickButtonWidget(173, 125, 18, 18, "", data -> reinitializeStructurePattern())
                .setButtonTexture(GTLiteGuiTextures.BUTTON_REFRESH_STRUCTURE_PATTERN)
                .setTooltipText("gtlitecore.machine.space_elevator.refresh_structure_pattern");
    }

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    @Override
    public IModuleProvider getModuleProvider()
    {
        return this.moduleProvider;
    }

    @Override
    public void setModuleProvider(IModuleProvider provider)
    {
        this.moduleProvider = provider;
    }

}
