package ru.shvetsov.weatherapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.shvetsov.weatherapp.data.response.CurrentWeatherApiResponse
import ru.shvetsov.weatherapp.data.model.CurrentWeatherModel
import ru.shvetsov.weatherapp.data.model.ForecastModel
import ru.shvetsov.weatherapp.data.remote.KtorClient.client
import ru.shvetsov.weatherapp.data.response.ForecastApiResponse
import ru.shvetsov.weatherapp.utils.constants.Constants
import ru.shvetsov.weatherapp.utils.date.formatDate
import ru.shvetsov.weatherapp.utils.date.formatDateTime
import java.time.LocalDate
import kotlin.math.roundToInt


class RemoteWeatherDataSource {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherModel? {
        return try {
            val response: CurrentWeatherApiResponse = client.get("http://api.weatherapi.com/v1/current.json") {
                parameter("key", Constants.Key.API_KEY)
                parameter("q", latitude)
                parameter("q", longitude)
                parameter("aqi", "no")
            }.body()
            CurrentWeatherModel(
                city = response.location.name,
                country = response.location.country,
                localTime = formatDateTime(response.location.localtime),
                temp = response.current.temp_c.roundToInt(),
                condition = response.current.condition.text,
                humidity = response.current.humidity,
                windSpeed = response.current.wind_kph,
                icon = response.current.condition.icon
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getWeeklyForecast(latitude: Double, longitude: Double): ForecastModel? {
        return try {
            val response: ForecastApiResponse = client.get("http://api.weatherapi.com/v1/forecast.json") {
                parameter("key", Constants.Key.API_KEY)
                parameter("q", latitude)
                parameter("q", longitude)
                parameter("days", 7)
                parameter("aqi", "no")
                parameter("alerts", "no")
            }.body()

            val filteredForecastDays = response.forecast.forecastday.filter { forecastDayModel ->
                val today = LocalDate.now()
                val forecastDate = LocalDate.parse(forecastDayModel.date)
                forecastDate.isAfter(today)
            }.map {forecastDayModel ->
                val formattedDate = formatDate(forecastDayModel.date)
                forecastDayModel.copy(date = formattedDate)
            }
            ForecastModel (
                forecastday = filteredForecastDays
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

object KtorClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}