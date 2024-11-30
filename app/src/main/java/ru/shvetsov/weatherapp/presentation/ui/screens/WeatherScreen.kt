package ru.shvetsov.weatherapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.shvetsov.weatherapp.R
import ru.shvetsov.weatherapp.data.model.ForecastDayModel
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

            else -> {
                weatherState.weatherModel?.let { weatherModel ->
                    Text(
                        text = "${weatherModel.city}, ${weatherModel.country}",
                        style = TextStyle(
                            color = colorResource(id = R.color.gray),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = weatherModel.localTime, style = TextStyle(
                            color = colorResource(id = R.color.gray),
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(150.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https:${weatherModel.icon}")
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(R.string.icon),
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${weatherModel.temp}°", style = TextStyle(
                            color = colorResource(id = R.color.gray),
                            fontSize = 100.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 35.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = weatherModel.condition, style = TextStyle(
                            color = colorResource(id = R.color.gray),
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.wind), style = TextStyle(
                                    color = colorResource(id = R.color.gray),
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.wind),
                                    contentDescription = stringResource(
                                        R.string.wind
                                    )
                                )
                                Text(
                                    text = stringResource(R.string.km_h, weatherModel.windSpeed),
                                    style = TextStyle(
                                        color = colorResource(id = R.color.gray),
                                        fontSize = 18.sp
                                    ),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.humidity), style = TextStyle(
                                    color = colorResource(id = R.color.gray),
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(7.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.humidity),
                                    contentDescription = stringResource(
                                        R.string.humidity
                                    )
                                )
                                Text(
                                    text = "${weatherModel.humidity}%",
                                    style = TextStyle(
                                        color = colorResource(id = R.color.gray),
                                        fontSize = 18.sp
                                    ),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                        }
                    }
                }
                weatherState.weeklyForecastModel?.let { weeklyForecast ->
                    Spacer(modifier = Modifier.height(70.dp))
                    LazyColumn {
                        items(weeklyForecast) { forecastDayModel ->
                            ForecastItem(forecastDayModel = forecastDayModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastItem(forecastDayModel: ForecastDayModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceAround
    ) {
        Text(text = forecastDayModel.date)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https:${forecastDayModel.day.condition.icon}")
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.weather_type)
        )
        Text(text = "${forecastDayModel.day.maxtemp_c}° / ${forecastDayModel.day.mintemp_c}°")
    }
}