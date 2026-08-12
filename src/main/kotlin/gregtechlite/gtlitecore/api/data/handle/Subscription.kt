package gregtechlite.gtlitecore.api.data.handle

class Subscription(private val unsubscribe: () -> Unit) : AutoCloseable
{
    override fun close() { unsubscribe() }
}
