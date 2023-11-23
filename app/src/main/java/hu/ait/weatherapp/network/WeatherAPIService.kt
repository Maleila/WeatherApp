package hu.ait.weatherapp.network

import hu.ait.weatherapp.data.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET(".api/latest") // the get request needs the path
//setting up the Query Parameters
    suspend fun getRates(@Query("access_key") accessKey:String): WeatherResult

}