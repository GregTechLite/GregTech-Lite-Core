package gregtechlite.gtlitecore.mixins.gregtech;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = OreDictUnifier.class, remap = false)
public abstract class MixinOreDictUnifier
{
    /**
     * Skips the prefix lookup which shadows a prefix and material pair.
     * <p>
     * A stone type prefix name may be identical to an existing ore dictionary name of the default
     * stone type, e.g. the {@code oreQuartzite} stone type prefix and the {@code ore} + {@code Quartzite}
     * plain quartzite ore. In that case the exact name match leaves the material unresolved, so the
     * caller never falls back to its {@code prefixName} splitting, which results in the ore being
     * neither unified nor processed at all.
     *
     * @param oreDictName The registered ore dictionary name.
     * @param methodOp    The original {@code getPrefix} call in the {@code onItemRegistration} method.
     * @return            {@code null} to let the caller split the name into prefix and material, or the
     *                    original prefix lookup result.
     */
    @WrapOperation(method = "onItemRegistration",
                   at = @At(value = "INVOKE",
                            target = "Lgregtech/api/unification/ore/OrePrefix;getPrefix(Ljava/lang/String;)Lgregtech/api/unification/ore/OrePrefix;",
                            ordinal = 0))
    private static @Nullable OrePrefix ignoreShadowingPrefix(String oreDictName, Operation<@Nullable OrePrefix> methodOp)
    {
        OrePrefix orePrefix = methodOp.call(oreDictName);
        if (orePrefix != null && !orePrefix.isSelfReferencing)
            return null;
        return orePrefix;
    }
}
