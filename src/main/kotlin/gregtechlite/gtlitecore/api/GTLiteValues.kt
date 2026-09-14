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

// region Constants

@Deprecated(message = "Use Int#tick extension")
const val TICK = 1

@Deprecated(message = "Use Int#sec extension")
const val SECOND = 20 * TICK

@Deprecated(message = "Use Int#min extension")
const val MINUTE = 60 * SECOND

/**
 * Tick time unit for regular recipes, progress and counter.
 */
val Int.t
    get() = this * 1

/**
 * Second time unit for regular recipes, progress and counter.
 */
val Int.s
    get() = this * 20.t

/**
 * Minute time unit for regular recipes, progress and counter.
 */
val Int.min
    get() = this * 60.s

/**
 * Hour time unit for regular recipes, progress and counter.
 */
val Int.hr
    get() = this * 60.min

/**
 * Represent a Unit of Steam, based on the conversion radio of Steam and (Distilled) Water,
 * calculated by its Fluid Heater recipes (6:960) and Turbine recipes (4:640).
 */
const val SU = 160

// endregion