package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentModel(
    val temp_c: Double,
    val wind_kph: Double,
    val humidity: Double,
    val condition: ConditionModel
)
