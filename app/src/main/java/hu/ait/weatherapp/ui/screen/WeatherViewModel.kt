package hu.ait.weatherapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.ait.weatherapp.data.WeatherResult
import hu.ait.weatherapp.network.WeatherAPI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface WeatherUiState {
    data class Success(val weatherResults: WeatherResult) : WeatherUiState
    object Error : WeatherUiState
    object Loading : WeatherUiState
}
class WeatherViewModel(): ViewModel() {

    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)

    init {
        getWeather("cbb0b0b9a1d9bc8b0fbff1d332b10379")
    }

    fun getWeather(accessKey: String) {
        weatherUiState = WeatherUiState.Loading
        viewModelScope.launch {
            weatherUiState = try {
                val result = WeatherAPI.retrofitService.getWeather(
                    accessKey, "47.0", "19.0"
                )
                WeatherUiState.Success(result)
            } catch (e: IOException) {
                WeatherUiState.Error
            } catch (e: HttpException) {
                WeatherUiState.Error
            }
        }
    }
}