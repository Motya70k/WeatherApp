package ru.shvetsov.weatherapp.data.repository

import ru.shvetsov.weatherapp.data.model.ForecastModel
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.domain.repository.ForecastRepository
import ru.shvetsov.weatherapp.domain.resource.Resource

class ForecastRepositoryImpl(
    private val remoteDataSource: RemoteWeatherDataSource
) : ForecastRepository {
    override suspend fun getWeeklyForecast(
        latitude: Double,
        longitude: Double
    ): Resource<ForecastModel> {
        return try {
            Resource.Success(
                data = remoteDataSource.getWeeklyForecast(latitude, longitude)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown error occurred."
            )
        }
    }
}