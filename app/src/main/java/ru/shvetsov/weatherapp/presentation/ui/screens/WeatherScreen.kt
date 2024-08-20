package ru.shvetsov.weatherapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.shvetsov.weatherapp.R
import ru.shvetsov.weatherapp.presentation.state.WeatherState

@Composable
fun WeatherScreen(weatherState: WeatherState) {

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.light_gray))
            .padding(top = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            weatherState.isLoading -> {
                CircularProgressIndicator()
            }

            weatherState.error != null -> {
                Text(text = weatherState.error)
            }

            weatherState.weatherModel != null -> {
                Text(
                    text = "${weatherState.weatherModel.city}, ${weatherState.weatherModel.country}",
                    style = TextStyle(
                        color = colorResource(id = R.color.gray),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = weatherState.weatherModel.localTime, style = TextStyle(
                        color = colorResource(id = R.color.gray),
                        fontSize = 10.sp
                    )
                )
                Spacer(modifier = Modifier.height(150.dp))
                Image(
                    painter = painterResource(id = R.drawable.sunny),
                    contentDescription = "Sunny"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${weatherState.weatherModel.temp}°", style = TextStyle(
                        color = colorResource(id = R.color.gray),
                        fontSize = 100.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = weatherState.weatherModel.condition, style = TextStyle(
                        color = colorResource(id = R.color.gray),
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}
