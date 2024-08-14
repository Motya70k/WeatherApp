package ru.shvetsov.weatherapp.presentation.state

import ru.shvetsov.weatherapp.data.model.WeatherModel

data class WeatherState(
    val weatherModel: WeatherModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
