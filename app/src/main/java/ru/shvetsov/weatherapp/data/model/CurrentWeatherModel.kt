package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherModel(
    val city: String,
    val country: String,
    val localTime: String,
    val temp: Int,
    val condition: String,
    val humidity: Double,
    val windSpeed: Double,
    val icon: String
)