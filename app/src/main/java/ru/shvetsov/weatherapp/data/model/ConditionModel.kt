package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConditionModel(
    val text: String,
    val icon: String
)
