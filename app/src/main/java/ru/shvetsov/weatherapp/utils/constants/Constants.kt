package ru.shvetsov.weatherapp.utils.constants

class Constants {

    companion object {
        const val URL_CURRENT_WEATHER = "http://api.weatherapi.com/v1/current.json"
        const val URL_WEATHER_FORECAST = "http://api.weatherapi.com/v1/forecast.json"
        const val UNKNOWN_ERROR = "Unknown error"
        const val PERMISSION_ERROR = "Couldn't retrieve location. Make sure to grant permission and enable GPS"
    }
}