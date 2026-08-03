package gregtechlite.gtlitecore.api.collection

class LazyValue<T>(private val delegate: () -> T)
{
    @Volatile
    private var cachedValue: T? = null

    fun invalidate()
    {
        this.cachedValue = null
    }

    fun get() = cachedValue ?: synchronized(this) {
        cachedValue ?: delegate().also { cachedValue = it }
    }
}