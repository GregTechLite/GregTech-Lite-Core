package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.util.TextFormattingUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.math.BigInteger;

@Mixin(value = TextFormattingUtil.class, remap = false)
public class MixinTextFormattingUtil {

    @Shadow
    private static BigInteger[] metricBigSuffixValues;

    @Shadow
    private static char[] metricSuffixChars;

    /**
     * @author rainy
     * @reason Fix incorrect fractional digits: upstream uses fixed-offset substring(4)
     *         to extract decimal part, which is wrong for every input since the metric
     *         suffix system guarantees 1-3 integer digits (not 4). Compute remainder
     *         after division instead.
     */
    @Overwrite
    public static String formatBigIntToCompactString(BigInteger value, int precision) {
        if (BigInteger.ZERO.equals(value) || value.abs().compareTo(BigInteger.TEN.pow(precision)) < 0) {
            return value.toString();
        }

        StringBuilder stb = new StringBuilder();
        if (value.signum() == -1) {
            stb.append('-');
            value = value.abs();
        }

        int c = 0;
        while (c < metricBigSuffixValues.length && value.compareTo(metricBigSuffixValues[c]) >= 0) {
            c++;
        }

        BigInteger suffix = metricBigSuffixValues[c - 1];
        BigInteger integerPart = value.divide(suffix);
        BigInteger remainder = value.remainder(suffix);
        BigInteger decimalDivisor = suffix.divide(BigInteger.TEN.pow(precision - 3));

        int digits = precision - 3;
        stb.append(integerPart).append('.');
        stb.append(String.format("%0" + digits + "d", remainder.divide(decimalDivisor).intValue()));
        stb.append(metricSuffixChars[c - 1]);
        return stb.toString();
    }
}
