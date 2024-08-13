package ru.shvetsov.weatherapp.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.shvetsov.weatherapp.data.model.WeatherApiResponse
import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.data.remote.KtorClient.client
import ru.shvetsov.weatherapp.utils.Constants


class RemoteWeatherDataSource {
    suspend fun getCurrentWeather(city: String): WeatherModel? {
        return try {
            val response: WeatherApiResponse = client.get("http://api.weatherapi.com/v1/current.json?key=2cfe7e74051549298d190704241308&q=$city&aqi=no").body()
            Log.d("Response", "${response.current.temp}")
            WeatherModel(
                city = response.location.name,
                country = response.location.country,
                temp = response.current.temp,
                condition = response.current.condition.weatherType,
                humidity = response.current.humidity,
                windSpeed = response.current.windSpeed,
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
            })
        }
    }
}