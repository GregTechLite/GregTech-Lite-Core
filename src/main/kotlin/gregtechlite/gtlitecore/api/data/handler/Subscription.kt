package gregtechlite.gtlitecore.api.data.handler

class Subscription(private val unsubscribe: () -> Unit) : AutoCloseable
{
    override fun close() { unsubscribe() }
}
