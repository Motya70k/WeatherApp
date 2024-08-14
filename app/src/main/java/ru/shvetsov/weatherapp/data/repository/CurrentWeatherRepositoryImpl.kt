package ru.shvetsov.weatherapp.data.repository

import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.domain.repository.CurrentWeatherRepository

class CurrentWeatherRepositoryImpl(
    private val remoteDataSource: RemoteWeatherDataSource
): CurrentWeatherRepository {

    override suspend fun getCurrentWeather(city: String): WeatherModel? {
        return remoteDataSource.getCurrentWeather(city)
    }
}