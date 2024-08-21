package ru.shvetsov.weatherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shvetsov.weatherapp.domain.location.LocationTracker
import ru.shvetsov.weatherapp.domain.resource.Resource
import ru.shvetsov.weatherapp.domain.usecase.ForecastUseCase
import ru.shvetsov.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import ru.shvetsov.weatherapp.presentation.state.WeatherState

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val forecastUseCase: ForecastUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                val currentWeather = getCurrentWeatherUseCase.getCurrentWeather(location.latitude, location.longitude)
                val weeklyForecast = forecastUseCase.getWeeklyForecast(location.latitude, location.longitude)
                state = when {
                    currentWeather is Resource.Success && weeklyForecast is Resource.Success -> {
                        state.copy(
                            weatherModel = currentWeather.data,
                            weeklyForecastModel = weeklyForecast.data?.forecastday,
                            isLoading = false,
                            error = null
                        )
                    }
                    currentWeather is Resource.Error -> {
                        state.copy(
                            weatherModel = null,
                            isLoading = false,
                            error = currentWeather.message
                        )
                    }
                    weeklyForecast is Resource.Error -> {
                        state.copy(
                            weeklyForecastModel = null,
                            isLoading = false,
                            error = weeklyForecast.message
                        )
                    }
                    else -> {
                        state.copy(
                            isLoading = false,
                            error = "Unknown error"
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}

class WeatherViewModelFactory(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val forecastUseCase: ForecastUseCase,
    private val locationTracker: LocationTracker
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(getCurrentWeatherUseCase, forecastUseCase, locationTracker) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}