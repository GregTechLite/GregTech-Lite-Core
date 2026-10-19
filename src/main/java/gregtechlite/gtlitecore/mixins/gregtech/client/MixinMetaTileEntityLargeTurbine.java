package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeTurbine;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = MetaTileEntityLargeTurbine.class, remap = false)
public abstract class MixinMetaTileEntityLargeTurbine
{
    /**
     * Add autofill support hint for player, the upstream tooltip lines are kept untouched.
     *
     * @author Magic_Sweepy
     */
    @Inject(method = "addInformation(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Ljava/util/List;Z)V",
            at = @At("TAIL"))
    private void gtlitecore$addAutofillTooltip(ItemStack stack, @Nullable World world, List<String> tooltip,
                                               boolean advanced, CallbackInfo callbackInfo)
    {
        tooltip.add(I18n.format("gtlitecore.machine.large_turbine.autofill"));
    }
}
