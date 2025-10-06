@file:JvmName("GTLiteValues")

package gregtechlite.gtlitecore.api

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
 * Regular time units in the game, used in recipes by default and tickable checks.
 */
const val TICK = 1

/**
 * Regular time units in the game, used in recipes by default and tickable checks.
 *
 * 1 sec = 20 tick
 */
const val SECOND = 20 * TICK

/**
 * Regular time units in the game, used in recipes by default and tickable checks.
 *
 * 1 min = 60 sec
 */
const val MINUTE = 60 * SECOND

/**
 * Regular time units in the game, used in recipes by default and tickable checks.
 *
 * 1 hr = 60 min
 */
const val HOUR = 60 * MINUTE

/**
 * Represent a Unit of Steam, based on the conversion radio of Steam and (Distilled) Water,
 * calculated by its Fluid Heater recipes (6:960) and Turbine recipes (4:640).
 */
const val SU = 160
