package ru.shvetsov.weatherapp.presentation.intent

sealed class WeatherIntent {
    data class LoadWeather(val city: String) : WeatherIntent()
    data class RefreshWeather(val city: String) : WeatherIntent()
}