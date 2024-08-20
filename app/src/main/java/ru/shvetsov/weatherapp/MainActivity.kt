package ru.shvetsov.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.google.android.gms.location.LocationServices
import ru.shvetsov.weatherapp.data.location.DefaultLocationTracker
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.data.repository.CurrentWeatherRepositoryImpl
import ru.shvetsov.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import ru.shvetsov.weatherapp.presentation.ui.screens.WeatherScreen
import ru.shvetsov.weatherapp.presentation.ui.theme.WeatherAppTheme
import ru.shvetsov.weatherapp.presentation.viewmodel.WeatherViewModel
import ru.shvetsov.weatherapp.presentation.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(
            getCurrentWeatherUseCase = GetCurrentWeatherUseCase(CurrentWeatherRepositoryImpl(
                RemoteWeatherDataSource()
            )),
            locationTracker = DefaultLocationTracker(
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this),
                application = application
            )
        )
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WeatherScreen(weatherState = viewModel.state)
                }
            }
        }
    }
}
