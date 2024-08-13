package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    val city: String,
    val country: String,
    val temp: Double,
    val condition: String,
    val humidity: Double,
    val windSpeed: Double,
    val code: Int
)