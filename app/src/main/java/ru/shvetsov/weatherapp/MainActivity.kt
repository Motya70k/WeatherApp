package ru.shvetsov.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import ru.shvetsov.weatherapp.data.model.WeatherModel
import ru.shvetsov.weatherapp.data.remote.RemoteWeatherDataSource
import ru.shvetsov.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val scope = rememberCoroutineScope()
                var text by remember { mutableStateOf("Loading") }
                LaunchedEffect(true) {
                    scope.launch {
                        try {
                            val weatherModel: WeatherModel? = RemoteWeatherDataSource().getCurrentWeather("Moscow")
                            text = weatherModel?.temp.toString()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                Greeting(text = text)
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}