package ru.shvetsov.weatherapp.domain.repository

import ru.shvetsov.weatherapp.data.model.CurrentWeatherModel
import ru.shvetsov.weatherapp.domain.resource.Resource

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Resource<CurrentWeatherModel>
}