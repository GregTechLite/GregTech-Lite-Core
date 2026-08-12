package gregtechlite.gtlitecore.api.metatileentity

import java.util.concurrent.ConcurrentHashMap

object MetaTileEntitySyncBatcher
{
    private val batchers = ConcurrentHashMap<Int, MetaTileEntitySyncBatcher>()
    private val dirty = ConcurrentHashMap.newKeySet<MetaTileEntitySync>()

    fun get(dimension: Int): MetaTileEntitySyncBatcher = batchers.getOrPut(dimension) { MetaTileEntitySyncBatcher }

    fun markDirty(sync: MetaTileEntitySync)
    {
        dirty.add(sync)
    }

    fun flush()
    {
        if (dirty.isEmpty()) return
        val it = dirty.iterator()
        while (it.hasNext())
        {
            it.next().flushChanges()
            it.remove()
        }
    }
}
