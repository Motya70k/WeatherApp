package ru.shvetsov.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.shvetsov.weatherapp.domain.location.LocationTracker
import ru.shvetsov.weatherapp.domain.resource.Resource
import ru.shvetsov.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import ru.shvetsov.weatherapp.presentation.state.WeatherState


class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
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
                when(val result = getCurrentWeatherUseCase.getCurrentWeather(43.6, 39.73)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherModel = result.data,
                            isLoading = false,
                            error = null
                        )
                        Log.d("WeatherViewModel", "loadWeatherInfo: ${location.latitude} ${location.longitude}")
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherModel = null,
                            isLoading = false,
                            error = result.message
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
    private val locationTracker: LocationTracker
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(getCurrentWeatherUseCase, locationTracker) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}