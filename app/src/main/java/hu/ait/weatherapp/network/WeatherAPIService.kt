package hu.ait.weatherapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.ait.weatherapp.data.WeatherResult
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// http://data.fixer.io/api/latest?access_key=969c37b5a73f8cb2d12c081dcbdc35e6

//https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}

private const val BASE_URL =
    "https://api.openweathermap.org/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WeatherAPI {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("lat") lat: String,
                           @Query("lon") lon: String,
                           @Query("appid") appid: String,
                           @Query("units") units: String): WeatherResult
}