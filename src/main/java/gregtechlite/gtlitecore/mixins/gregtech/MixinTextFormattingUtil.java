package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.util.TextFormattingUtil;
import gregtechlite.gtlitecore.mixins.hooks.TextFormattingHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.math.BigInteger;

@Mixin(value = TextFormattingUtil.class, remap = false)
public class MixinTextFormattingUtil
{
    @Final
    @Shadow
    private static BigInteger[] metricBigSuffixValues;

    @Final
    @Shadow
    private static char[] metricSuffixChars;

    /**
     * @author RainyYC
     * @reason Fix incorrect fractional digits: upstream uses fixed-offset substring(4) to extract the decimal
     *         part, which is wrong for every input since the metric suffix system guarantees 1-3 integer digits
     *         (not 4). Compute remainder after division instead.
     */
    @Overwrite
    public static String formatBigIntToCompactString(BigInteger value, int precision)
    {
        return TextFormattingHooks.formatBigIntToCompactString(value, precision,
                metricBigSuffixValues, metricSuffixChars);
    }
}
