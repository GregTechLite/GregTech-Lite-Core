@file:JvmName("GTLiteValues")

package gregtechlite.gtlitecore.api

import com.morphismmc.morphismlib.util.SidedLogger

/**
 * The unique id of the mod, should be used every usage in the mod.
 */
const val MOD_ID = GTLiteTags.MOD_ID

/**
 * The human-readable name of the mod, should be used every usage in the mod.
 */
const val MOD_NAME = GTLiteTags.MOD_NAME

/**
 * The internal version number of the mod, it is unmodifiable as default.
 */
const val MOD_VERSION = GTLiteTags.MOD_VERSION

/**
 * The logger for the global mod level, for module, please use its corresponding logger.
 */
@JvmField
val LOGGER = SidedLogger(MOD_ID)

@Deprecated(message = "Use Int#tick extension")
const val TICK = 1

val Int.t
    get() = this * 1

@Deprecated(message = "Use Int#sec extension")
const val SECOND = 20 * TICK

val Int.s // 1s = 20t
    get() = this * 20.t

@Deprecated(message = "Use Int#min extension")
const val MINUTE = 60 * SECOND

val Int.min // 1min = 60s
    get() = this * 60.s

@Deprecated(message = "Use Int#hour extension")
const val HOUR = 60 * MINUTE

val Int.hr // 1hr = 60min
    get() = this * 60.min

/**
 * Represent a Unit of Steam, based on the conversion radio of Steam and (Distilled) Water,
 * calculated by its Fluid Heater recipes (6:960) and Turbine recipes (4:640).
 */
const val SU = 160
