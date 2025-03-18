package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IMultipleRecipeMaps;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.registry.MTERegistry;
import gregtech.api.mui.GregTechGuiTransferHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.category.ICategoryOverride;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.machines.IScannerRecipeMap;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.worldgen.config.BedrockFluidDepositDefinition;
import gregtech.api.worldgen.config.OreDepositDefinition;
import gregtech.api.worldgen.config.WorldGenRegistry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.JustEnoughItemsModule;
import gregtech.integration.jei.basic.GTFluidVeinInfo;
import gregtech.integration.jei.basic.GTOreInfo;
import gregtech.integration.jei.basic.MaterialTree;
import gregtech.integration.jei.basic.OreByProduct;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import gregtech.integration.jei.recipe.FacadeRegistryPlugin;
import gregtech.integration.jei.recipe.GTRecipeWrapper;
import gregtech.integration.jei.recipe.IntCircuitCategory;
import gregtech.integration.jei.recipe.IntCircuitRecipeWrapper;
import gregtech.integration.jei.utils.ModularUIGuiHandler;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.config.Constants;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = JustEnoughItemsModule.class, remap = false)
public abstract class JustEnoughItemsModuleMixin
{

    @Shadow
    public static IIngredientRegistry ingredientRegistry;

    /**
     * @author Magic_Sweepy
     * @reason Include a comparator sorting for all MetaTileEntities, let all additional mod
     *         MetaTileEntities (in its {@code MTERegistry}) still later be added in original
     *         module of GregTech.
     */
    @Overwrite
    public void register(@NotNull IModRegistry registry)
    {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(IntCircuitRecipeWrapper.create(), IntCircuitCategory.UID);
        MultiblockInfoCategory.registerRecipes(registry);
        registry.addRecipeRegistryPlugin(new FacadeRegistryPlugin());

        // register transfer handler for all categories, but not for the crafting station
        ModularUIGuiHandler modularUIGuiHandler = new ModularUIGuiHandler(jeiHelpers.recipeTransferHandlerHelper());
        modularUIGuiHandler.blacklistCategory(
                IntCircuitCategory.UID,
                GTValues.MODID + ":material_tree",
                VanillaRecipeCategoryUid.INFORMATION,
                VanillaRecipeCategoryUid.FUEL);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(modularUIGuiHandler,
                Constants.UNIVERSAL_RECIPE_TRANSFER_UID);

        // register transfer handler for crafting recipes
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(new GregTechGuiTransferHandler(
                jeiHelpers.recipeTransferHandlerHelper()), VanillaRecipeCategoryUid.CRAFTING);

        registry.addAdvancedGuiHandlers(modularUIGuiHandler);
        registry.addGhostIngredientHandler(modularUIGuiHandler.getGuiContainerClass(), modularUIGuiHandler);

        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            if (recipeMap.getRecipeMapUI().isJEIVisible()) {
                for (Map.Entry<GTRecipeCategory, List<Recipe>> entry : recipeMap.getRecipesByCategory().entrySet()) {
                    Stream<Recipe> recipeStream = entry.getValue().stream()
                            .filter(recipe -> !recipe.isHidden() && recipe.hasValidInputsForDisplay());

                    if (recipeMap.getSmallRecipeMap() != null) {
                        Collection<Recipe> smallRecipes = recipeMap.getSmallRecipeMap().getRecipeList();
                        recipeStream = recipeStream.filter(recipe -> !smallRecipes.contains(recipe));
                    }

                    if (recipeMap instanceof IScannerRecipeMap)
                    {
                        IScannerRecipeMap scannerMap = (IScannerRecipeMap) recipeMap;
                        List<Recipe> scannerRecipes = scannerMap.getRepresentativeRecipes();
                        if (!scannerRecipes.isEmpty()) {
                            registry.addRecipes(scannerRecipes.stream()
                                    .map(r -> new GTRecipeWrapper(recipeMap, r))
                                    .collect(Collectors.toList()), entry.getKey().getUniqueID());
                        }
                    }

                    registry.addRecipes(recipeStream.map(r -> new GTRecipeWrapper(recipeMap, r))
                                    .collect(Collectors.toList()),
                            entry.getKey().getUniqueID());
                }
            }
        }

        for (MetaTileEntity metaTileEntity : gtlitecore$getSortedMTEs())
        {
            assert metaTileEntity != null;
            if (metaTileEntity instanceof ICategoryOverride && ((ICategoryOverride) metaTileEntity).shouldOverride())
            {
                ICategoryOverride override = (ICategoryOverride) metaTileEntity;
                for (RecipeMap<?> recipeMap : override.getJEIRecipeMapCategoryOverrides())
                {
                    registerRecipeMapCatalyst(registry, recipeMap, metaTileEntity);
                }
                if (override.getJEICategoryOverrides().length != 0)
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), override.getJEICategoryOverrides());
                if (override.shouldReplace()) continue;
            }

            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null)
            {
                IControllable workableCapability = metaTileEntity
                        .getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);

                if (workableCapability instanceof ICategoryOverride && ((ICategoryOverride) workableCapability).shouldOverride())
                {
                    ICategoryOverride override = (ICategoryOverride) workableCapability;
                    for (RecipeMap<?> recipeMap : override.getJEIRecipeMapCategoryOverrides())
                    {
                        registerRecipeMapCatalyst(registry, recipeMap, metaTileEntity);
                    }
                    if (override.getJEICategoryOverrides().length != 0)
                        registry.addRecipeCatalyst(metaTileEntity.getStackForm(),
                                override.getJEICategoryOverrides());
                    if (override.shouldReplace()) continue;
                }

                if (workableCapability instanceof AbstractRecipeLogic)
                {
                    AbstractRecipeLogic logic = (AbstractRecipeLogic) workableCapability;
                    if (metaTileEntity instanceof IMultipleRecipeMaps)
                    {
                        for (RecipeMap<?> recipeMap : ((IMultipleRecipeMaps) metaTileEntity).getAvailableRecipeMaps())
                        {
                            registerRecipeMapCatalyst(registry, recipeMap, metaTileEntity);
                        }
                    } else if (logic.getRecipeMap() != null)
                    {
                        registerRecipeMapCatalyst(registry, logic.getRecipeMap(), metaTileEntity);
                    }
                }
            }
        }

        List<OreByProduct> oreByproductList = new ArrayList<>();
        List<MaterialTree> materialTreeList = new ArrayList<>();
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials())
        {
            if (material.hasProperty(PropertyKey.ORE))
            {
                oreByproductList.add(new OreByProduct(material));
            }
            if (material.hasProperty(PropertyKey.DUST))
            {
                materialTreeList.add(new MaterialTree(material));
            }
            if (material.hasFlag(GENERATE_BOLT_SCREW) && material.hasFlag(GENERATE_RING) &&
                    material.hasProperty(PropertyKey.TOOL))
            {
                registry.addIngredientInfo(ToolItems.TOOLBELT.get(material), VanillaTypes.ITEM,
                        "item.gt.tool.toolbelt.tooltip", "item.gt.tool.toolbelt.paint", "item.gt.tool.toolbelt.dye",
                        "item.gt.tool.toolbelt.maintenance");
            }
        }

        String oreByProductId = GTValues.MODID + ":" + "ore_by_product";
        registry.addRecipes(oreByproductList, oreByProductId);
        MetaTileEntity[][] machineLists = {
                MetaTileEntities.MACERATOR,
                MetaTileEntities.ORE_WASHER,
                MetaTileEntities.CENTRIFUGE,
                MetaTileEntities.THERMAL_CENTRIFUGE,
                MetaTileEntities.CHEMICAL_BATH,
                MetaTileEntities.ELECTROMAGNETIC_SEPARATOR,
                MetaTileEntities.SIFTER
        };
        for (MetaTileEntity[] machine : machineLists)
        {
            if (machine.length < GTValues.LV + 1 || machine[GTValues.LV] == null) continue;
            registry.addRecipeCatalyst(machine[GTValues.LV].getStackForm(), oreByProductId);
        }

        // Material Tree
        registry.addRecipes(materialTreeList, GTValues.MODID + ":" + "material_tree");

        // Ore Veins
        List<OreDepositDefinition> oreVeins = WorldGenRegistry.getOreDeposits();
        List<GTOreInfo> oreInfoList = new ArrayList<>();
        for (OreDepositDefinition vein : oreVeins) {
            oreInfoList.add(new GTOreInfo(vein));
        }

        String oreSpawnID = GTValues.MODID + ":" + "ore_spawn_location";
        registry.addRecipes(oreInfoList, oreSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_LV.getStackForm(), oreSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_HV.getStackForm(), oreSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_LUV.getStackForm(), oreSpawnID);
        // Ore Veins End

        // Fluid Veins
        List<BedrockFluidDepositDefinition> fluidVeins = WorldGenRegistry.getBedrockVeinDeposits();
        List<GTFluidVeinInfo> fluidVeinInfos = new ArrayList<>();
        for (BedrockFluidDepositDefinition fluidVein : fluidVeins)
        {
            fluidVeinInfos.add(new GTFluidVeinInfo(fluidVein));
        }

        String fluidVeinSpawnID = GTValues.MODID + ":" + "fluid_spawn_location";
        registry.addRecipes(fluidVeinInfos, fluidVeinSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_HV.getStackForm(), fluidVeinSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_LUV.getStackForm(), fluidVeinSpawnID);
        // Fluid Veins End

        ingredientRegistry = registry.getIngredientRegistry();
        for (int i = 0; i <= IntCircuitIngredient.CIRCUIT_MAX; i++)
        {
            registry.addIngredientInfo(IntCircuitIngredient.getIntegratedCircuit(i), VanillaTypes.ITEM,
                    "metaitem.circuit.integrated.jei_description");
        }

        registry.addRecipeCatalyst(MetaTileEntities.WORKBENCH.getStackForm(), VanillaRecipeCategoryUid.CRAFTING);

        for (MetaTileEntity machine : MetaTileEntities.CANNER)
        {
            if (machine == null) continue;
            registry.addIngredientInfo(machine.getStackForm(), VanillaTypes.ITEM,
                    "gregtech.machine.canner.jei_description");
        }

        // Multiblock info page registration
        MultiblockInfoCategory.REGISTER.forEach(mte -> {
            String[] desc = mte.getDescription();
            if (desc.length > 0) {
                registry.addIngredientInfo(mte.getStackForm(), VanillaTypes.ITEM, mte.getDescription());
            }
        });
        registry.addIngredientInfo(new ItemStack(MetaBlocks.BRITTLE_CHARCOAL), VanillaTypes.ITEM,
                I18n.format("tile.brittle_charcoal.tooltip.1", I18n.format("tile.brittle_charcoal.tooltip.2")));

        // Refresh Ore Ingredients Cache
        GTRecipeOreInput.refreshStackCache();

    }

    /**
     * Sorted MetaTileEntities in all {@code MTERegistry}, let additional mod registry
     * still late with GregTech {@code MTERegistry}.
     *
     * @return Total {@code MTERegistry} and it contained MetaTileEntities.
     */
    @Unique
    private List<MetaTileEntity> gtlitecore$getSortedMTEs()
    {
        List<MetaTileEntity> sortedMTEs = new ArrayList<>();
        for (MTERegistry mteRegistry : GregTechAPI.mteManager.getRegistries())
        {
            for (ResourceLocation mteId : mteRegistry.getKeys())
            {
                MetaTileEntity mte = mteRegistry.getObject(mteId);
                if (mte != null)
                    sortedMTEs.add(mte);
            }
        }

        sortedMTEs.sort((a, b) -> {
            boolean a1 = a.metaTileEntityId.getNamespace().equals(GTValues.MODID);
            boolean b1 = b.metaTileEntityId.getNamespace().equals(GTValues.MODID);

            if (a1 && !b1) return -1;
            if (!a1 && b1) return 1;

            return a.metaTileEntityId.getNamespace().compareTo(b.metaTileEntityId.getNamespace());
        });
        return sortedMTEs;
    }

    @Shadow
    protected abstract void registerRecipeMapCatalyst(IModRegistry registry, RecipeMap<?> recipeMap, MetaTileEntity metaTileEntity);

}
