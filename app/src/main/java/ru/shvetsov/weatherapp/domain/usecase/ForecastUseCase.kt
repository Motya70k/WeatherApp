package ru.shvetsov.weatherapp.domain.usecase

import ru.shvetsov.weatherapp.data.model.ForecastModel
import ru.shvetsov.weatherapp.domain.repository.ForecastRepository
import ru.shvetsov.weatherapp.domain.resource.Resource

class ForecastUseCase(private val repository: ForecastRepository) {
    suspend fun getWeeklyForecast(latitude: Double, longitude: Double): Resource<ForecastModel> {
        return repository.getWeeklyForecast(latitude, longitude)
    }
}