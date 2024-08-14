package ru.shvetsov.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import ru.shvetsov.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import ru.shvetsov.weatherapp.presentation.intent.WeatherIntent
import ru.shvetsov.weatherapp.presentation.state.WeatherState

class WeatherViewModel(private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase) : ViewModel() {

    val intents = Channel<WeatherIntent>(Channel.UNLIMITED)
    private val mutableState = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = mutableState

    init {
        viewModelScope.launch {
            loadWeather("London")
        }
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                when (intent) {
                    is WeatherIntent.LoadWeather -> loadWeather(intent.city)
                    is WeatherIntent.RefreshWeather -> loadWeather(intent.city)
                }
            }
        }
    }

    private suspend fun loadWeather(city: String) {
        mutableState.value = mutableState.value.copy(isLoading = true)
        try {
            val weather = getCurrentWeatherUseCase.getCurrentWeather(city)
            mutableState.value = mutableState.value.copy(weatherModel = weather, isLoading = false)
        } catch (e: Exception) {
            mutableState.value = mutableState.value.copy(error = e.message, isLoading = false)
        }
    }
}

class WeatherViewModelFactory(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(getCurrentWeatherUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
