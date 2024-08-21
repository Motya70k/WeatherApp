package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayModel(
    val date: String,
    val day: DayModel
)
