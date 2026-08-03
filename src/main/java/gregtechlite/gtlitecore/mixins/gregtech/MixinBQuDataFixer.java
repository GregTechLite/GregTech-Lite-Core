package gregtechlite.gtlitecore.mixins.gregtech;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import gregtech.integration.bq.BQuDataFixer;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2905")
@Mixin(value = BQuDataFixer.class, remap = false)
public abstract class MixinBQuDataFixer
{
    @ModifyExpressionValue(
            method = "applyDataFix",
            at = @At(value = "INVOKE",
                     target = "Lcom/google/gson/JsonObject;get(Ljava/lang/String;)Lcom/google/gson/JsonElement;",
                     ordinal = 3,
                     remap = false))
    private static JsonElement gtlitecore$nullSafeGetDamage(JsonElement original)
    {
        return original != null ? original : new JsonPrimitive((short) 0);
    }
}
