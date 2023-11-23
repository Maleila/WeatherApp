package hu.ait.weatherapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.ait.weatherapp.data.WeatherResult
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// http://data.fixer.io/api/latest?access_key=969c37b5a73f8cb2d12c081dcbdc35e6

private const val BASE_URL =
    "http://data.fixer.io/" //is this what you modify w the right API?

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
    @GET("api/latest")
    suspend fun getRates(@Query("access_key") access_key: String): WeatherResult
}