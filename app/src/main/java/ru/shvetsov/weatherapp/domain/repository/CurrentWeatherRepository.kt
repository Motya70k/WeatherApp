package ru.shvetsov.weatherapp.domain.repository

import ru.shvetsov.weatherapp.data.model.WeatherModel

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(city: String): WeatherModel?
}