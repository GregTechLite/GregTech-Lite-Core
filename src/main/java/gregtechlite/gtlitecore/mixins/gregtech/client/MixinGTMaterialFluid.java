package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.api.fluids.GTFluid;
import gregtech.api.unification.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = GTFluid.GTMaterialFluid.class, remap = false)
public abstract class MixinGTMaterialFluid
{

    @Shadow
    @Final
    private Material material;

    @Shadow
    @Final
    private String translationKey;

    /**
     * @reason Let GTCEu fluids be searchable in JEI. TODO When CEu fixes this problem then remove this.
     * @author Magic_Sweepy
     */
    @SideOnly(Side.CLIENT)
    @Overwrite
    public String getLocalizedName(FluidStack stack)
    {
        String localizedName;
        String customMaterialTranslation = "fluid." + material.getUnlocalizedName();

        if (I18n.hasKey(customMaterialTranslation))
        {
            localizedName = I18n.format(customMaterialTranslation);
        }
        else
        {
            localizedName = I18n.format(material.getUnlocalizedName());
        }

        if (translationKey != null)
        {
            return I18n.format(translationKey, localizedName);
        }
        return localizedName;
    }

}
