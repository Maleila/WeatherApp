package hu.ait.weatherapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.ait.weatherapp.R
import hu.ait.weatherapp.data.WeatherResult

@Composable
fun WeatherApiScreen(
    q: String,
    weatherViewModel: WeatherViewModel = viewModel()
) {
    LaunchedEffect(true) {
        weatherViewModel.getWeather("49324e494e65ef3e90e3497c07badf50", q)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        when (weatherViewModel.weatherUiState) {
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> ResultScreen((weatherViewModel.weatherUiState as WeatherUiState.Success).weatherResults)
            is WeatherUiState.Error -> Text(text = stringResource(R.string.weatherResultErrorTxt))
        }
        Button(modifier = Modifier.padding(15.dp),
            onClick = {
            weatherViewModel.getWeather("49324e494e65ef3e90e3497c07badf50", q)
        }) {
            Text(text = stringResource(R.string.refreshBttn))
        }
    }
}

@Composable
fun ResultScreen(weatherResults: WeatherResult) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(weatherResults != null) {
            Text(text = "${weatherResults.name}")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://openweathermap.org/img/w/${
                        weatherResults.weather?.get(0)?.icon
                    }.png")
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.iconContentDesc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(text = stringResource(R.string.currentTemp, weatherResults.main?.temp.toString()) + stringResource(
                R.string.degreesF))
            Text(text = stringResource(R.string.feelsLike, weatherResults.main?.feels_like.toString()) + stringResource(
                R.string.degreesF))
            Text(text = stringResource(R.string.high, weatherResults.main?.temp_max.toString()) + stringResource(
                R.string.degreesF) + " " + stringResource(R.string.low, weatherResults.main?.temp_min.toString()) + stringResource(R.string.degreesF))
            Text(text = "${weatherResults.weather?.get(0)?.description}")
            Text(text = stringResource(R.string.humidity, weatherResults.main?.humidity.toString()) + stringResource(
                R.string.percent))
        } else {
            Text(text = stringResource(R.string.weatherResultErrorTxt))
        }
    }
}