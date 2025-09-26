package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.worldgen.config.OreConfigUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.stream.Collectors;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@Mixin(value = OreConfigUtils.class, remap = false)
public abstract class MixinOreConfigUtils
{

    /**
     * @author Magic_Sweepy
     * @reason Allowed WorldGenRegistry scanning material in different MaterialRegistry.
     */
    @Overwrite
    public static Material getMaterialByName(String name)
    {
        Material material = GregTechAPI.materialManager.getMaterial(name);
        if (material != null && material.hasProperty(PropertyKey.ORE))
        {
            return material;
        }
        else if (material == null)
        {
            return GregTechAPI.materialManager.getRegistry(MOD_ID).getAllMaterials().stream()
                    .filter(mat -> mat.getName().equals(name))
                    .collect(Collectors.toList()).get(0);
        }
        else
        {
            throw new IllegalArgumentException("Material with name " + name + " not found!");
        }
    }

}
