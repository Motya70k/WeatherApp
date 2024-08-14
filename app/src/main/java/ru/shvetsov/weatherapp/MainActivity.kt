package ru.shvetsov.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import ru.shvetsov.weatherapp.presentation.ui.screens.WeatherScreen
import ru.shvetsov.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    WeatherScreen()
                }
            }
        }
    }
}
