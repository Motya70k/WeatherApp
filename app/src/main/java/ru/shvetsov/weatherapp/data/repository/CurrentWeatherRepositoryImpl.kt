package ru.shvetsov.weatherapp.data.repository

import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.domain.repository.CurrentWeatherRepository
import ru.shvetsov.weatherapp.domain.resource.Resource

class CurrentWeatherRepositoryImpl(
    private val remoteDataSource: RemoteWeatherDataSource
): CurrentWeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): Resource<WeatherModel> {
        return try {
            Resource.Success(
                data = remoteDataSource.getCurrentWeather(latitude, longitude)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown error occurred."
            )
        }
    }
}