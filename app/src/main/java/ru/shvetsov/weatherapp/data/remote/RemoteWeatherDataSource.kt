package ru.shvetsov.weatherapp.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.shvetsov.weatherapp.data.model.WeatherApiResponse
import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.data.remote.KtorClient.client
import ru.shvetsov.weatherapp.utils.Constants
import kotlin.math.roundToInt


class RemoteWeatherDataSource {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): WeatherModel? {
        return try {
            val response: WeatherApiResponse = client.get("http://api.weatherapi.com/v1/current.json") {
                parameter("key", Constants.Key.API_KEY)
                parameter("q", latitude)
                parameter("q", longitude)
                parameter("aqi", "no")
            }.body()
            WeatherModel(
                city = response.location.name,
                country = response.location.country,
                localTime = response.location.localtime,
                temp = response.current.temp_c.roundToInt(),
                condition = response.current.condition.text,
                humidity = response.current.humidity,
                windSpeed = response.current.wind_kph,
                code = response.current.condition.code
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