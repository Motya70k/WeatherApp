package ru.shvetsov.weatherapp.domain.repository

import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.domain.resource.Resource

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Resource<WeatherModel>
}