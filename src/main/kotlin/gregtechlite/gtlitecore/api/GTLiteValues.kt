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

// Regular time units in the game, used in recipes by default and tickable checks.

const val TICK = 1
const val SECOND = 20 * TICK
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
