package ru.shvetsov.weatherapp.utils.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDate(date: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("E, MMM d")
    return LocalDate.parse(date, inputFormatter).format(outputFormatter)
}

fun formatDateTime(dateTime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val outputFormatter = DateTimeFormatter.ofPattern("E, MMM d HH:mm")
    return LocalDateTime.parse(dateTime, inputFormatter).format(outputFormatter)
}