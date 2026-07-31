package gregtechlite.gtlitecore.api.extension

import org.apache.commons.lang3.builder.ToStringBuilder

fun Any.buildToString(action: ToStringBuilder.() -> Unit): String = ToStringBuilder(this).apply(action).toString()
