package hu.ait.weatherapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.weatherapp.data.WeatherResult

@Composable
fun WeatherApiScreen(
    weatherViewModel: WeatherViewModel = viewModel()
) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            weatherViewModel.getWeather("49324e494e65ef3e90e3497c07badf50")
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
        //icon
        Text(text = "Place: ${weatherResults.name}")
        Text(text = "Current temperature: ${weatherResults.main?.temp}")
        Text(text = "Feels like: ${weatherResults.main?.feels_like}")
        Text(text = "Temp min: ${weatherResults.main?.temp_min}")
        Text(text = "Temp max: ${weatherResults.main?.temp_max}")
        Text(text = "${weatherResults.weather?.get(0)?.description}")
        Text(text = "Humidity: ${weatherResults.main?.humidity}")
    }
}

//(current temperature, description, weather icon, min and max temperature, humidity, sunrise and sunset, etc.)