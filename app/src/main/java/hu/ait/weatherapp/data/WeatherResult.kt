package hu.ait.weatherapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//how do you know which things you'll need???

@Serializable
data class WeatherResult(
//    @SerialName("date")
//    val date: String? = null,
    @SerialName("success")
    val success: Boolean? = null,
//    @SerialName("timestamp")
//    val timestamp: Int? = null,
    @SerialName("current.temp")
    val temp: Int? = null,
)