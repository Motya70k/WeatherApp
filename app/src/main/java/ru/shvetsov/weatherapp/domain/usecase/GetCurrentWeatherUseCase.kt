package ru.shvetsov.weatherapp.domain.usecase

import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.domain.repository.CurrentWeatherRepository
import ru.shvetsov.weatherapp.domain.resource.Resource

class GetCurrentWeatherUseCase(private val repository: CurrentWeatherRepository) {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Resource<WeatherModel> {
        return repository.getCurrentWeather(latitude, longitude)
    }
}