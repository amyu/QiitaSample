package amyu.share.extensions

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId


fun Instant.isSameDay(checkInstant: Instant): Boolean {
    val localDate1 = LocalDateTime.ofInstant(this, ZoneId.systemDefault()).toLocalDate()
    val localDate2 = LocalDateTime.ofInstant(checkInstant, ZoneId.systemDefault()).toLocalDate()
    return localDate1.isEqual(localDate2)
}