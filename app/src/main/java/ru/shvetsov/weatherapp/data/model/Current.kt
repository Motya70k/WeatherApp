package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val temp: Double,
    val windSpeed: Double,
    val humidity: Double,
    val condition: Condition
)
