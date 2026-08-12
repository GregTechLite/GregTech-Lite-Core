package gregtechlite.gtlitecore.api.data.handle

sealed class CheckStrategy<in T>
{
    companion object
    {
        val EQUALS: CheckStrategy<Any?> = EqualsStrategy
        val IDENTITY: CheckStrategy<Any?> = IdentityStrategy
        val ALWAYS_UPDATE: CheckStrategy<Any?> = AlwaysUpdateStrategy
    }

    abstract fun matches(prev: T, cur: T): Boolean
}

private object EqualsStrategy : CheckStrategy<Any?>()
{
    override fun matches(prev: Any?, cur: Any?) = prev == cur
}

private object IdentityStrategy : CheckStrategy<Any?>()
{
    override fun matches(prev: Any?, cur: Any?) = prev === cur
}

private object AlwaysUpdateStrategy : CheckStrategy<Any?>()
{
    override fun matches(prev: Any?, cur: Any?) = false
}
