@file:JvmName("PayloadsKt")
package gregtechlite.gtlitecore.api.network.payload

import gregtechlite.gtlitecore.api.LOGGER
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing

// region Shortcut

fun emptyPayload(): TypedPayload<*> = EmptyPayload

fun intPayload(value: Int): TypedPayload<*> = IntPayload(value)
fun longPayload(value: Long): TypedPayload<*> = LongPayload(value)
fun floatPayload(value: Float): TypedPayload<*> = FloatPayload(value)
fun doublePayload(value: Double): TypedPayload<*> = DoublePayload(value)
fun bytePayload(value: Byte): TypedPayload<*> = BytePayload(value)
fun shortPayload(value: Short): TypedPayload<*> = ShortPayload(value)
fun booleanPayload(value: Boolean): TypedPayload<*> = BooleanPayload(value)
fun charPayload(value: Char): TypedPayload<*> = CharPayload(value)
fun stringPayload(value: String): TypedPayload<*> = StringPayload(value)

fun Int.payload(): TypedPayload<*> = intPayload(this)
fun Long.payload(): TypedPayload<*> = longPayload(this)
fun Float.payload(): TypedPayload<*> = floatPayload(this)
fun Double.payload(): TypedPayload<*> = doublePayload(this)
fun Byte.payload(): TypedPayload<*> = bytePayload(this)
fun Short.payload(): TypedPayload<*> = shortPayload(this)
fun Boolean.payload(): TypedPayload<*> = booleanPayload(this)
fun Char.payload(): TypedPayload<*> = charPayload(this)
fun String.payload(): TypedPayload<*> = stringPayload(this)

fun nbtPayload(value: NBTBase): TypedPayload<*> = NBTTagPayload(value)
fun facingPayload(value: EnumFacing): TypedPayload<*> = EnumFacingPayload(value)

inline fun <reified T : NBTBase> T.payload(): TypedPayload<*> = nbtPayload(this)
fun EnumFacing.payload(): TypedPayload<*> = facingPayload(this)

// endregion

fun isSupportedType(t: Class<*>): Boolean = isInt(t) || isLong(t) || isFloat(t) || isDouble(t) || isByte(t) || isShort(t)
        || isBoolean(t) || isChar(t) || isString(t) || isNBT(t) || isFacing(t)

fun createEmptyPayload(fieldType: Class<*>): TypedPayload<*>? = when
{
    isInt(fieldType)     -> IntPayload()
    isLong(fieldType)    -> LongPayload()
    isFloat(fieldType)   -> FloatPayload()
    isDouble(fieldType)  -> DoublePayload()
    isByte(fieldType)    -> BytePayload()
    isShort(fieldType)   -> ShortPayload()
    isBoolean(fieldType) -> BooleanPayload()
    isChar(fieldType)    -> CharPayload()
    isString(fieldType)  -> StringPayload()
    isNBT(fieldType)     -> NBTTagPayload()
    isFacing(fieldType)  -> EnumFacingPayload()
    else                 -> null
}

fun fromPayload(fieldType: Class<*>, payload: TypedPayload<*>): Any?
{
    val _payload = payload.payload
    return when
    {
        NBTTagCompound::class.java == fieldType -> _payload as? NBTTagCompound
        else -> _payload
    }
}

fun toPayload(fieldType: Class<*>, value: Any?): TypedPayload<*> = when
{
    isInt(fieldType)     -> IntPayload(value as? Int ?: 0)
    isLong(fieldType)    -> LongPayload(value as? Long ?: 0L)
    isFloat(fieldType)   -> FloatPayload(value as? Float ?: 0f)
    isDouble(fieldType)  -> DoublePayload(value as? Double ?: 0.0)
    isByte(fieldType)    -> BytePayload(value as? Byte ?: 0)
    isShort(fieldType)   -> ShortPayload(value as? Short ?: 0)
    isBoolean(fieldType) -> BooleanPayload(value as? Boolean ?: false)
    isChar(fieldType)    -> CharPayload(value as? Char ?: '\u0000')
    isString(fieldType)  -> StringPayload(value as? String ?: "")
    isNBT(fieldType)     -> NBTTagPayload(value as? NBTBase)
    isFacing(fieldType)  -> EnumFacingPayload(value as? EnumFacing ?: EnumFacing.UP)
    else ->
    {
        LOGGER.warn("Managed unsupported field type {}; skipping syncdata field.", fieldType)
        EmptyPayload
    }
}


// TODO: Check out here when we change this part to Kotlin primitive type.
private fun isInt(t: Class<*>) = t == Integer.TYPE || t == Int::class.java
private fun isLong(t: Class<*>) = t == java.lang.Long.TYPE || t == Long::class.java
private fun isFloat(t: Class<*>) = t == java.lang.Float.TYPE || t == Float::class.java
private fun isDouble(t: Class<*>) = t == java.lang.Double.TYPE || t == Double::class.java
private fun isByte(t: Class<*>) = t == java.lang.Byte.TYPE || t == Byte::class.java
private fun isShort(t: Class<*>) = t == java.lang.Short.TYPE || t == Short::class.java
private fun isBoolean(t: Class<*>) = t == java.lang.Boolean.TYPE || t == Boolean::class.java
private fun isChar(t: Class<*>) = t == Character.TYPE || t == Char::class.java
private fun isString(t: Class<*>) = t == String::class.java
private fun isNBT(t: Class<*>) = NBTBase::class.java.isAssignableFrom(t)
private fun isFacing(t: Class<*>) = t == EnumFacing::class.java