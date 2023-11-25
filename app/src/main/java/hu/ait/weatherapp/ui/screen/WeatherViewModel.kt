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
        getWeather("49324e494e65ef3e90e3497c07badf50")
    }

    fun getWeather(accessKey: String) {
        weatherUiState = WeatherUiState.Loading
        viewModelScope.launch {
//            weatherUiState = try {
//                val result = WeatherAPI.retrofitService.getWeather(
//                    "47.00", "19.00", accessKey)
//                WeatherUiState.Success(result)
//            } catch (e: IOException) {
//                WeatherUiState.Error
//            } catch (e: HttpException) {
//                WeatherUiState.Error
//            }


                val result = WeatherAPI.retrofitService.getWeather(
                    "47.00", "19.00", accessKey, "imperial")
                weatherUiState = WeatherUiState.Success(result)

        }
    }
}