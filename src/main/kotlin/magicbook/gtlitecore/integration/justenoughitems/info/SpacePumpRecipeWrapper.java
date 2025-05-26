package magicbook.gtlitecore.integration.justenoughitems.info;

import lombok.Getter;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ParametersAreNonnullByDefault
public final class SpacePumpRecipeWrapper implements IRecipeWrapper
{

    private final int planetId;
    private final int fluidId;
    private final FluidStack fluid;
    private final List<List<FluidStack>> fluids = new ArrayList<>();

    public SpacePumpRecipeWrapper(int planetId, int fluidId, FluidStack fluid)
    {
        this.planetId = planetId;
        this.fluidId = fluidId;
        this.fluid = fluid;
        this.fluids.add(Collections.singletonList(fluid));
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(VanillaTypes.FLUID, fluids);
        ingredients.setOutputLists(VanillaTypes.FLUID, fluids);
    }

}
