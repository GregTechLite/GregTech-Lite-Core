package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.registry.IMaterialRegistryManager;
import gregtech.api.worldgen.config.OreConfigUtils;
import gregtechlite.gtlitecore.api.GTLiteValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = OreConfigUtils.class, remap = false)
public abstract class MixinOreConfigUtils
{

    /**
     * Adds fallback material searching from <tt>gtlitecore</tt> namespace.
     *
     * @param instance The target class which be mixined.
     * @param name     The name of the material.
     * @param methodOp The original method {@code getMaterial} call in {@code getMaterialByName} method.
     * @return         The searching material in <tt>gregtech</tt> namespace, or the fallback material (if existed)
     *                 in <tt>gtlitecore</tt> namespace.
     */
    @WrapOperation(method = "getMaterialByName",
                   at = @At(value = "INVOKE",
                            target = "Lgregtech/api/unification/material/registry/IMaterialRegistryManager;getMaterial(Ljava/lang/String;)Lgregtech/api/unification/material/Material;"))
    private static Material fallbackMaterialSearching(IMaterialRegistryManager instance,
                                                      String name,
                                                      Operation<Material> methodOp)
    {
        Material material = methodOp.call(instance, name);
        if (material != null)
        {
            return material;
        }

        // If material is empty in gregtech namespace, then searching fallback material in gtlitecore namespace.
        return GregTechAPI.materialManager.getRegistry(GTLiteValues.MOD_ID).getAllMaterials().stream()
                .filter(mat -> mat.getName().equals(name))
                .findAny().orElse(null);
    }

}
