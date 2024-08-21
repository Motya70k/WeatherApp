package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DayModel(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: ConditionModel
)
