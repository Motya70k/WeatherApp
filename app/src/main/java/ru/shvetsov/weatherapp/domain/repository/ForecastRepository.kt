package ru.shvetsov.weatherapp.domain.repository

import ru.shvetsov.weatherapp.data.model.ForecastModel
import ru.shvetsov.weatherapp.domain.resource.Resource

interface ForecastRepository {
    suspend fun getWeeklyForecast(latitude: Double, longitude: Double): Resource<ForecastModel>
}