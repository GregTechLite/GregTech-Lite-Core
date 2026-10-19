package gregtechlite.gtlitecore.mixins.hooks

import java.math.BigInteger

@Hook
object TextFormattingHooks
{
    /**
     * Formats a [BigInteger] to a compact string with a metric suffix.
     *
     * @param value0                The value to format.
     * @param precision             The number of significant digits to keep.
     * @param metricBigSuffixValues The suffix thresholds, read from the target class.
     * @param metricSuffixChars     The suffix characters, read from the target class.
     */
    @JvmStatic
    fun formatBigIntToCompactString(value0: BigInteger, precision: Int,
                                    metricBigSuffixValues: Array<BigInteger>,
                                    metricSuffixChars: CharArray): String
    {
        if (value0 == BigInteger.ZERO || value0.abs() < BigInteger.TEN.pow(precision))
            return value0.toString()

        val negative = value0.signum() == -1
        val value = if (negative) value0.abs() else value0

        var suffixIndex = 0
        while (suffixIndex < metricBigSuffixValues.size && value >= metricBigSuffixValues[suffixIndex])
            suffixIndex++

        val suffix = metricBigSuffixValues[suffixIndex - 1]
        val decimalDigits = precision - 3
        val decimal = value.remainder(suffix)
            .divide(suffix.divide(BigInteger.TEN.pow(decimalDigits)))
            .toInt()

        return buildString {
            if (negative) append('-')
            append(value.divide(suffix))
            append('.')
            append(decimal.toString().padStart(decimalDigits, '0'))
            append(metricSuffixChars[suffixIndex - 1])
        }
    }
}
