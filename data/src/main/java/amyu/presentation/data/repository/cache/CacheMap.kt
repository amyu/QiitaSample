package amyu.presentation.data.repository.cache

import amyu.share.extensions.isSameDay
import org.threeten.bp.Clock
import org.threeten.bp.Instant


class CacheMap<in K, V> private constructor(private val clock: Clock) {
    class Provider(private val clock: Clock) {
        fun <K, V> create(): CacheMap<K, V> = CacheMap(clock)
    }

    private val map: MutableMap<K, Pair<V, Instant>> = mutableMapOf()

    operator fun set(key: K, value: V): V {
        map.put(key, value to clock.instant())
        return value
    }

    operator fun get(key: K): V? = map[key]?.let { (value, lastUpdateAtInstant) ->
        if (clock.instant().isSameDay(lastUpdateAtInstant)) {
            value
        } else {
            null
        }
    }

    fun getAll(): List<V> = map.map { it.value.first }

    fun clear() {
        map.clear()
    }
}