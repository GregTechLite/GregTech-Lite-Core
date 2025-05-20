package magicbook.gtlitecore.api.metatileentity.multiblock;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.capability.IModuleProvider;
import magicbook.gtlitecore.api.capability.IModuleReceiver;
import magicbook.gtlitecore.api.gui.GTLiteMuiTextures;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIBuilder;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;

public abstract class RecipeMapModuleMultiblockController extends RecipeMapMultiblockController implements IModuleReceiver, IControllable
{

    private final int tier;
    private final int moduleTier;
    private final int minCasingTier;

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
            return energyContainer;
        }
    }

    @Override
    public void sentWorkingDisabled()
    {
        recipeMapWorkable.setWorkingEnabled(false);
    }

    @Override
    public void sentWorkingEnabled()
    {
        recipeMapWorkable.setWorkingEnabled(true);
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
            if (energyContainer.getEnergyCapacity() != energyContainer.getEnergyStored()
                    && getModuleProvider().getSubEnergyContainer().getEnergyStored() > energyConsumed * SECOND)
            {
                long simulate = energyContainer.getEnergyCapacity() - energyContainer.getEnergyStored();
                getModuleProvider().getSubEnergyContainer().removeEnergy(simulate);
                energyContainer.addEnergy(simulate);
            }
        }
        else if (getModuleProvider() == null)
        {
            recipeMapWorkable.setWorkingEnabled(false);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public abstract ICubeRenderer getBaseTexture(IMultiblockPart sourcePart);

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected abstract ICubeRenderer getFrontOverlay();

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    @Override
    public boolean isWorkingEnabled()
    {
        return recipeMapWorkable.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled)
    {
        recipeMapWorkable.setWorkingEnabled(workingEnabled);
    }

    @Override
    public IModuleProvider getModuleProvider()
    {
        return moduleProvider;
    }

    @Override
    public void setModuleProvider(IModuleProvider provider)
    {
        this.moduleProvider = provider;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public boolean usesMui2()
    {
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager guiSyncManager)
    {
        return new MultiblockUIFactory(this)
                .configureDisplayText(this::configureDisplayText)
                .configureWarningText(this::configureWarningText)
                .configureErrorText(this::configureErrorText)
                .buildUI(guiData, guiSyncManager, GTLiteMuiTextures.DISPLAY);
    }

    protected void configureDisplayText(MultiblockUIBuilder builder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addEnergyUsageLine(getEnergyContainer())
                .addEnergyTierLine(GTUtility.getTierByVoltage(recipeMapWorkable.getMaxVoltage()))
                .addParallelsLine(recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(recipeMapWorkable.getProgress(), recipeMapWorkable.getMaxProgress());
    }

    protected void configureWarningText(MultiblockUIBuilder builder)
    {
        builder.addLowPowerLine(recipeMapWorkable.isHasNotEnoughEnergy());
        builder.addMaintenanceProblemLines(getMaintenanceProblems(), true);
    }

    protected void configureErrorText(MultiblockUIBuilder builder)
    {
        builder.structureFormed(isStructureFormed());
        if (hasMufflerMechanics())
            builder.addMufflerObstructedLine(!isMufflerFaceFree());
        if (hasMaintenanceMechanics())
            builder.addMaintenanceProblemLines(getMaintenanceProblems(), false);
    }

}
