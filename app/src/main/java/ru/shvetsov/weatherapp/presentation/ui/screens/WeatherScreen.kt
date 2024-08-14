package ru.shvetsov.weatherapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.data.repository.CurrentWeatherRepositoryImpl
import ru.shvetsov.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import ru.shvetsov.weatherapp.presentation.intent.WeatherIntent
import ru.shvetsov.weatherapp.presentation.viewmodel.WeatherViewModel
import ru.shvetsov.weatherapp.presentation.viewmodel.WeatherViewModelFactory

@Composable
fun WeatherScreen() {
    val remoteDataSource = remember { RemoteWeatherDataSource() }
    val repository = remember { CurrentWeatherRepositoryImpl(remoteDataSource) }
    val useCase = remember { GetCurrentWeatherUseCase(repository) }
    val viewModelFactory = remember { WeatherViewModelFactory(useCase) }
    val viewModel: WeatherViewModel = viewModel(factory = viewModelFactory)
    val state by viewModel.state.collectAsState()

    Column {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error != null -> {
                Text(text = "Error: ${state.error}")
            }
            state.weatherModel != null -> {
                Text(text = "City: ${state.weatherModel!!.city}")
                Text(text = "Temperature: ${state.weatherModel!!.temp}°C")
                Text(text = "Condition: ${state.weatherModel!!.condition}")
            }
            else -> {
                Text(text = "No data available")
            }
        }

        Button(onClick = { viewModel.intents.trySend(WeatherIntent.RefreshWeather("Moscow")) }) {
            Text(text = "Refresh")
        }
    }
}

