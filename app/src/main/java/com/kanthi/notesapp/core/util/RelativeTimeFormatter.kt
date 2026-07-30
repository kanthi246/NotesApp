package com.kanthi.notesapp.core.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.max

object RelativeTimeFormatter {

    fun format(epochMillis: Long, now: Long = System.currentTimeMillis()): String {
        val zone = ZoneId.systemDefault()
        val thenDate = Instant.ofEpochMilli(epochMillis).atZone(zone).toLocalDate()
        val nowDate = Instant.ofEpochMilli(now).atZone(zone).toLocalDate()
        val daysBetween = daysBetween(thenDate, nowDate)
        val elapsedMs = max(0L, now - epochMillis)

        return when {
            daysBetween == 0L -> {
                val minutes = elapsedMs / 60_000
                when {
                    minutes < 1 -> "Just now"
                    minutes < 60 -> "${minutes}m ago"
                    else -> "${minutes / 60}h ago"
                }
            }
            daysBetween == 1L -> "Yesterday"
            daysBetween in 2..6 -> Instant.ofEpochMilli(epochMillis).atZone(zone).dayOfWeek
                .getDisplayName(TextStyle.SHORT, Locale.getDefault())
            else -> DateTimeFormatter.ofPattern("MMM d", Locale.getDefault())
                .format(Instant.ofEpochMilli(epochMillis).atZone(zone))
        }
    }

    private fun daysBetween(then: LocalDate, now: LocalDate): Long =
        java.time.temporal.ChronoUnit.DAYS.between(then, now)
}
