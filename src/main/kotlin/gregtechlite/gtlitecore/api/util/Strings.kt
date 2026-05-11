@file:JvmName("StringBuildersKt")
package gregtechlite.gtlitecore.api.util

import org.apache.commons.lang3.builder.ToStringBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun buildToString(container: Any, builderAction: ToStringBuilder.() -> Unit): String {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return ToStringBuilder(container).apply(builderAction).toString()
}