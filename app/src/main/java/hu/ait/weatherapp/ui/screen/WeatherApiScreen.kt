package hu.ait.weatherapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.weatherapp.data.WeatherResult

@Composable
fun WeatherApiScreen(
    weatherViewModel: WeatherViewModel = viewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            weatherViewModel.getWeather("cbb0b0b9a1d9bc8b0fbff1d332b10379")
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
    Column() {
        Text(text = "Right now")
        Text(text = "Temperature: ${weatherResults.temp}")
        //Text(text = "HUF: ${moneyRates.rates?.hUF}")

    }
}