package gregtechlite.gtlitecore.api.network.sync

import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.network.payload.isSupportedType
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

class ManagedFieldHolder private constructor(val ownerClass: Class<*>)
{
    val keys: Array<ManagedFieldKey>
    val syncKeys: Array<ManagedFieldKey>
    val persistKeys: Array<ManagedFieldKey>
    private val byName: Map<String, ManagedFieldKey>

    init
    {
        val found = collectManagedKeys(ownerClass)

        keys = found.toTypedArray()
        syncKeys = found.filter { it.isSync }.toTypedArray()
        persistKeys = found.filter { it.isPersist }.toTypedArray()
        byName = found.associateBy { it.name }
    }

    companion object
    {
        private val cache = ConcurrentHashMap<Class<*>, ManagedFieldHolder>()

        @JvmStatic
        fun get(clazz: Class<*>): ManagedFieldHolder = cache.computeIfAbsent(clazz) { ManagedFieldHolder(it) }
    }

    fun getKeyByName(name: String): ManagedFieldKey? = byName[name]

    private fun collectManagedKeys(clazz: Class<*>): List<ManagedFieldKey>
    {
        val targets = arrayListOf<Class<*>>()
        var current: Class<*>? = clazz
        while (current != null && current != Any::class.java)
        {
            targets.add(current)
            current = current.superclass
        }

        val result = linkedMapOf<String, ManagedFieldKey>()
        for (target in targets.asReversed())
        {
            for (field in target.declaredFields)
            {
                if (Modifier.isStatic(field.modifiers) || field.isSynthetic) continue
                if (!field.isAnnotationPresent(Persisted::class.java)
                    && !field.isAnnotationPresent(DescSynced::class.java)) continue

                if (!isSupportedType(field.type))
                {
                    LOGGER.warn("Managed skipping managed field {} on {}: unsupported type {}.",
                                field.name, clazz.simpleName, field.type)
                    continue
                }
                if (result.containsKey(field.name))
                {
                    LOGGER.warn("Managed duplicate managed field {} on {}; ignoring the one declared on {}.",
                                field.name, clazz.simpleName, target.simpleName)
                    continue
                }
                result[field.name] = buildKey(field)
            }
        }
        return result.values.toList()
    }

    private fun buildKey(field: Field): ManagedFieldKey
    {
        field.isAccessible = true
        val isPersist = field.isAnnotationPresent(Persisted::class.java)
        val isSync = field.isAnnotationPresent(DescSynced::class.java)
        val listener = field.getAnnotation(UpdateListener::class.java)
        return ManagedFieldKey(name = field.name,
                               isPersist = isPersist,
                               isSync = isSync,
                               field = field,
                               persistentKey = field.name,
                               updateListenerMethod = listener?.name)
    }
}