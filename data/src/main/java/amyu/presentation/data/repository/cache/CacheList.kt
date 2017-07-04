package amyu.presentation.data.repository.cache

import amyu.share.extensions.isSameDay
import org.threeten.bp.Clock
import org.threeten.bp.Instant


class CacheList<T> private constructor(private val clock: Clock) {
    class Provider(private val clock: Clock) {
        fun <T> create(): CacheList<T> = CacheList(clock)
    }

    val isAvailableCache: Boolean
        get() = lastUpdateAtInstant?.isSameDay(clock.instant()) ?: false

    private var lastUpdateAtInstant: Instant? = null

    var data: List<T>? = null
        get() = field.takeIf { isAvailableCache }
        set(value) {
            field = value
            lastUpdateAtInstant = clock.instant()
        }

    fun clear() {
        data = null
    }
}