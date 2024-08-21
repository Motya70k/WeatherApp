package ru.shvetsov.weatherapp.data.response

import kotlinx.serialization.Serializable
import ru.shvetsov.weatherapp.data.model.ForecastModel

@Serializable
data class ForecastApiResponse (
    val forecast: ForecastModel
)