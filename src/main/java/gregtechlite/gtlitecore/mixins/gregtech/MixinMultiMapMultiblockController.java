package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.widgets.CycleButtonWidget;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.LocalizationUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// TODO FIXME Remove this mixins when CEu fixes this problem.
@Mixin(value = MultiMapMultiblockController.class, remap = false)
public abstract class MixinMultiMapMultiblockController extends RecipeMapMultiblockController
{

    public MixinMultiMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap)
    {
        super(metaTileEntityId, recipeMap);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    protected MultiblockUIFactory createUIFactory()
    {
        return super.createUIFactory()
                .createFlexButton((guiData, syncManager) -> {
                    RecipeMap<?>[] recipeMaps = getAvailableRecipeMaps();
                    if (ArrayUtils.getLength(recipeMaps) <= 1)
                        return null;

                    IntSyncValue activeMapIndex = new IntSyncValue(this::getRecipeMapIndex, this::setRecipeMapIndex);

                    return new CycleButtonWidget()
                            .overlay(GTGuiTextures.BUTTON_MULTI_MAP)
                            .background(GTGuiTextures.BUTTON)
                            .disableHoverBackground()
                            .value(activeMapIndex)
                            .length(recipeMaps.length)
                            .tooltipBuilder(tooltip -> {
                                RecipeMap<?> map = recipeMaps[activeMapIndex.getIntValue()];

                                tooltip.add(LocalizationUtils.format("gregtech.multiblock.multiple_recipemaps.header")
                                        + " " + LocalizationUtils.format(map.getTranslationKey()));

                                // IKey recipeMapKey = KeyUtil.lang(map.getTranslationKey());
                                // tooltip.addLine(KeyUtil.lang("gregtech.multiblock.multiple_recipemaps.value") + recipeMapKey);
                            });
                });
    }

    @Shadow
    public abstract RecipeMap<?>[] getAvailableRecipeMaps();

    @Shadow
    public abstract int getRecipeMapIndex();

    @Shadow
    public abstract void setRecipeMapIndex(int index);

}
