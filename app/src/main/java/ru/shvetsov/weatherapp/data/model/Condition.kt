package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val weatherType: String,
    val code: Int
)
