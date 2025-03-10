package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static gregtech.api.GTValues.HV;
import static gregtech.api.unification.material.info.MaterialFlags.MORTAR_GRINDABLE;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public abstract class MaterialRecipeHandlerMixin
{

    @Shadow
    @Final
    private static List<OrePrefix> GEM_ORDER;

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void callbackRegistrate(CallbackInfo ci)
    {
        // OrePrefix.ingot.addProcessingHandler(PropertyKey.INGOT, MaterialRecipeHandler::processIngot);
        OrePrefix.nugget.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processNugget);

        // OrePrefix.block.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processBlock);
        OrePrefix.frameGt.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processFrame);

        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processDust);
        OrePrefix.dustSmall.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processSmallDust);
        OrePrefix.dustTiny.addProcessingHandler(PropertyKey.DUST, MaterialRecipeHandler::processTinyDust);

        for (int i = 0; i < GEM_ORDER.size(); i++)
        {
            OrePrefix gemPrefix = GEM_ORDER.get(i);
            OrePrefix prevGemPrefix = i == 0 ? null : GEM_ORDER.get(i - 1);
            gemPrefix.addProcessingHandler(PropertyKey.GEM,
                    (p, material, property) -> processGemConversion(p, prevGemPrefix, material));
        }

        ci.cancel();
    }

    @Shadow
    public static void processGemConversion(OrePrefix gemPrefix,
                                            @Nullable OrePrefix prevPrefix,
                                            Material material)
    {
        long materialAmount = gemPrefix.getMaterialAmount(material);
        ItemStack crushedStack = OreDictUnifier.getDust(material, materialAmount);
        int workingTier = material.getWorkingTier();

        if (material.hasFlag(MORTAR_GRINDABLE) && workingTier <= HV)
        {
            ModHandler.addShapedRecipe(String.format("gem_to_dust_%s_%s", material, gemPrefix), crushedStack,
                    "X", "m", 'X', new UnificationEntry(gemPrefix, material));
        }

        ItemStack prevStack = prevPrefix == null ? ItemStack.EMPTY
                : OreDictUnifier.get(prevPrefix, material, 2);
        if (!prevStack.isEmpty())
        {
            ModHandler.addShapelessRecipe(String.format("gem_to_gem_%s_%s", prevPrefix, material), prevStack,
                    "h", new UnificationEntry(gemPrefix, material));

            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .input(gemPrefix, material)
                    .outputs(prevStack)
                    .duration(20)
                    .EUt(16)
                    .buildAndRegister();

            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .inputs(prevStack)
                    .notConsumable(OrePrefix.craftingLens, MarkerMaterials.Color.White)
                    .output(gemPrefix, material)
                    .duration(300)
                    .EUt(GTUtility.scaleVoltage(240, workingTier))
                    .buildAndRegister();
        }
    }

}
