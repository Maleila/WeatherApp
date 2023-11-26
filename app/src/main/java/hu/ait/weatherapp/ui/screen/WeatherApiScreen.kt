package hu.ait.weatherapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.ait.weatherapp.data.WeatherResult

@Composable
fun WeatherApiScreen(
    q: String,
    weatherViewModel: WeatherViewModel = viewModel()
) {

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            weatherViewModel.getWeather("49324e494e65ef3e90e3497c07badf50", q)
        }) {
            Text(text = "Refresh")
        }
        when (weatherViewModel.weatherUiState) {
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> ResultScreen((weatherViewModel.weatherUiState as WeatherUiState.Success).weatherResults)
            is WeatherUiState.Error -> Text(text = "Error...")
        }
    }
}

@Composable
fun ResultScreen(weatherResults: WeatherResult) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "WEATHER")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://openweathermap.org/img/w/${
                    weatherResults.weather?.get(0)?.icon
                }.png")
                .crossfade(true)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )
        Text(text = "Place: ${weatherResults.name}")
        Text(text = "Current temperature: ${weatherResults.main?.temp}")
        Text(text = "Feels like: ${weatherResults.main?.feels_like}")
        Text(text = "Temp min: ${weatherResults.main?.temp_min}")
        Text(text = "Temp max: ${weatherResults.main?.temp_max}")
        Text(text = "${weatherResults.weather?.get(0)?.description}")
        Text(text = "Humidity: ${weatherResults.main?.humidity}")
    }
}