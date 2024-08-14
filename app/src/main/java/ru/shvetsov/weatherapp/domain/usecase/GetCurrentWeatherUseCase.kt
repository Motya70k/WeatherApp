package ru.shvetsov.weatherapp.domain.usecase

import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.domain.repository.CurrentWeatherRepository

class GetCurrentWeatherUseCase(private val repository: CurrentWeatherRepository) {

    suspend fun getCurrentWeather(city: String): WeatherModel? {
        return repository.getCurrentWeather(city)
    }
}