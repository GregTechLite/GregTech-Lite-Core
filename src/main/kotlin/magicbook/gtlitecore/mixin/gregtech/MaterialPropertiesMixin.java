package magicbook.gtlitecore.mixin.gregtech;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

/**
 * Feature: Let {@code MaterialProperty} can be overriden at addon mod.
 * <p>
 * This class make {@code MaterialProperties} can be overriden, if material has {@code DustProperty},
 * then this class allowed to overriden it with a {@code IngotProperty} or {@code GemProperty}.
 *
 * @author Magic_Sweepy
 */
@Mixin(value = MaterialProperties.class, remap = false)
public abstract class MaterialPropertiesMixin
{

    @Shadow
    @Final
    private Map<PropertyKey<? extends IMaterialProperty>, IMaterialProperty> propertyMap;

    /**
     * @author Magic_Sweepy
     * @reason Change MaterialProperties can be overriden with bottom to top.
     */
    @Inject(method = "setProperty",
            at = @At("HEAD"),
            cancellable = true)
    public <T extends IMaterialProperty> void setProperty(PropertyKey<T> key, IMaterialProperty value,
                                                          CallbackInfo ci)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("Material Property must not be null!");
        }
        else if (hasProperty(key))
        {
            // If a material has DustProperty but you want to set its MaterialProperties
            // to IngotProperty or GemProperty, then remove DustProperty and add the newest
            // one for setProperty() method calling and the newest MaterialProperties.
            if ((key.equals(PropertyKey.INGOT) || key.equals(PropertyKey.GEM))
                    && hasProperty(PropertyKey.DUST))
            {
                propertyMap.remove(PropertyKey.DUST);
                propertyMap.put(key, value);
            }
            else
            {
                throw new IllegalArgumentException("Material Property " + key
                        + " already registered!");
            }
        }
        else
        {
            propertyMap.put(key, value);
            propertyMap.remove(PropertyKey.EMPTY);
        }
        ci.cancel();
    }

    @Shadow
    public abstract <T extends IMaterialProperty> boolean hasProperty(PropertyKey<T> key);

}
