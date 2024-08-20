package ru.shvetsov.weatherapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.shvetsov.weatherapp.presentation.state.WeatherState

@Composable
fun WeatherScreen(weatherState: WeatherState) {

    Column {
        when {
            weatherState.isLoading -> {
                CircularProgressIndicator()
            }
            weatherState.error != null -> {
                Text(text = "Error: ${weatherState.error}")
            }
            weatherState.weatherModel != null -> {
                Text(text = "City: ${weatherState.weatherModel.city}")
                Text(text = "Temperature: ${weatherState.weatherModel.temp}°C")
                Text(text = "Condition: ${weatherState.weatherModel.condition}")
            }
            else -> {
                Text(text = "No data available")
            }
        }
    }
}

