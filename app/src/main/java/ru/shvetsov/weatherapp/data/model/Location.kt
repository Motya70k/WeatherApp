package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val country: String,
    val localTime: String
)
