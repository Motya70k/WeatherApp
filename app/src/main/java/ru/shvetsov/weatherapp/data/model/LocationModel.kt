package ru.shvetsov.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(
    val name: String,
    val country: String,
    val localtime: String
)
