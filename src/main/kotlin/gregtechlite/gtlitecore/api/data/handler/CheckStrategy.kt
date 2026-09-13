package gregtechlite.gtlitecore.api.data.handler

sealed class CheckStrategy<in T>
{
    object Equals : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = prev == cur
    }

    object Identity : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = prev === cur
    }

    object AlwaysUpdate : CheckStrategy<Any?>()
    {
        override fun matches(prev: Any?, cur: Any?) = false
    }

    abstract fun matches(prev: T, cur: T): Boolean
}