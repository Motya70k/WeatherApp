package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastModel(
    val forecastday: List<ForecastDayModel>
)
