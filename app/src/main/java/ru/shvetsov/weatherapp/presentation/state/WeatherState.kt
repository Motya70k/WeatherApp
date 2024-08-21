package ru.shvetsov.weatherapp.presentation.state

import ru.shvetsov.weatherapp.data.model.CurrentWeatherModel
import ru.shvetsov.weatherapp.data.model.ForecastDayModel

data class WeatherState(
    val weatherModel: CurrentWeatherModel? = null,
    val weeklyForecastModel: List<ForecastDayModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
