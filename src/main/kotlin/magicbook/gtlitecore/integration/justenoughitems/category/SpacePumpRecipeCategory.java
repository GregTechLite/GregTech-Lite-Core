package magicbook.gtlitecore.integration.justenoughitems.category;

import gregtech.api.gui.GuiTextures;
import gregtech.api.util.GTStringUtils;
import gregtech.integration.jei.basic.BasicRecipeCategory;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.integration.justenoughitems.info.SpacePumpRecipeWrapper;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public final class SpacePumpRecipeCategory extends BasicRecipeCategory<SpacePumpRecipeWrapper, SpacePumpRecipeWrapper>
{

    private static final int SLOT_CENTER = 79;
    private static final int TEXT_START_X = 5;
    private static final int START_POS_Y = 40;

    private int planetId;
    private int fluidId;
    private FluidStack fluid;
    private int fluidAmount;

    private final IDrawable slot;

    private int planetIdLength;
    private int fluidIdLength;
    private int fluidAmountLength;

    public SpacePumpRecipeCategory(IGuiHelper guiHelper)
    {
        super("space_pump_module", "gtlitecore.jei.space_pump_module.name",
                guiHelper.createBlankDrawable(176, 166), guiHelper);
        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18)
                .setTextureSize(18, 18)
                .build();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout,
                          SpacePumpRecipeWrapper recipeWrapper,
                          IIngredients ingredients)
    {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        fluidStackGroup.init(0, true, SLOT_CENTER,
                19, 16, 16,
                1, false, null);
        fluidStackGroup.set(ingredients);

        this.planetId = recipeWrapper.getPlanetId();
        this.fluidId = recipeWrapper.getFluidId();
        this.fluid = recipeWrapper.getFluid();
        this.fluidAmount = recipeWrapper.getFluid().amount;
    }

    @Override
    public void drawExtras(Minecraft mc)
    {
        GTStringUtils.drawCenteredStringWithCutoff(fluid.getLocalizedName(), mc.fontRenderer, 176);
        this.slot.draw(mc, SLOT_CENTER - 1, 18);

        String planetIdName = I18n.format("gtlitecore.jei.space_pump_module.planet_id", this.planetId);
        this.planetIdLength = mc.fontRenderer.getStringWidth(planetIdName);
        mc.fontRenderer.drawString(planetIdName, TEXT_START_X, START_POS_Y, 0x111111);

        String fluidIdName = I18n.format("gtlitecore.jei.space_pump_module.fluid_id", this.fluidId);
        this.fluidIdLength = mc.fontRenderer.getStringWidth(fluidIdName);
        mc.fontRenderer.drawString(fluidIdName, TEXT_START_X, START_POS_Y + FONT_HEIGHT + 1, 0x111111);

        String fluidAmountName = I18n.format("gtlitecore.jei.space_pump_module.fluid_amount", this.fluidAmount);
        this.fluidAmountLength = mc.fontRenderer.getStringWidth(fluidAmountName);
        mc.fontRenderer.drawString(fluidAmountName, TEXT_START_X, START_POS_Y + FONT_HEIGHT * 2 + 1, 0x111111);
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        return Collections.emptyList();
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(SpacePumpRecipeWrapper recipeWrapper)
    {
        return recipeWrapper;
    }

    @Override
    public String getModName()
    {
        return GTLiteValues.MODID;
    }

}
