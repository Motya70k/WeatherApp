package ru.shvetsov.weatherapp.data.response

import kotlinx.serialization.Serializable
import ru.shvetsov.weatherapp.data.model.CurrentModel
import ru.shvetsov.weatherapp.data.model.LocationModel

@Serializable
data class CurrentWeatherApiResponse(
    val location: LocationModel,
    val current: CurrentModel
)
