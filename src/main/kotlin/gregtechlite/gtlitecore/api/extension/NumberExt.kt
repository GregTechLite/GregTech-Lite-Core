package gregtechlite.gtlitecore.api.extension

import java.math.BigInteger

fun Int.square(): Int = this * this

fun BigInteger.longValue(): Long = min(BigInteger.valueOf(Long.MAX_VALUE)).longValueExact()