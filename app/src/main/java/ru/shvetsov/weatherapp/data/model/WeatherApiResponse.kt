package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val location: Location,
    val current: Current
)
