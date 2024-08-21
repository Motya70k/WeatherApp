package ru.shvetsov.weatherapp.domain.usecase

import ru.shvetsov.weatherapp.data.model.CurrentWeatherModel
import ru.shvetsov.weatherapp.domain.repository.CurrentWeatherRepository
import ru.shvetsov.weatherapp.domain.resource.Resource

class GetCurrentWeatherUseCase(private val repository: CurrentWeatherRepository) {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Resource<CurrentWeatherModel> {
        return repository.getCurrentWeather(latitude, longitude)
    }
}