package hu.ait.weatherapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.ait.weatherapp.network.WeatherAPI
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

//I think this is redundant w WeatherAPIService??
//unless this is strictly a hilt thing?

@Module
@InstallIn(SingletonComponent::class)
object WeatherAPIModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")// server address
            .addConverterFactory(Json{ ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()) )
            //.addConverterFactory(ScalarsConverterFactory.create()) // row strings results
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherAPI(retrofit: Retrofit): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }
}