package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.worldgen.config.OreConfigUtils;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import one.util.streamex.StreamEx;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = OreConfigUtils.class, remap = false)
public abstract class OreConfigUtilsMixin
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
            return StreamEx.of(GregTechAPI.materialManager.getRegistry(GTLiteValues.MODID).getAllMaterials())
                    .filter(mat -> mat.getName().equals(name))
                    .toList().get(0);
        }
        else
        {
            throw new IllegalArgumentException("Material with name " + name + " not found!");
        }
    }

}
