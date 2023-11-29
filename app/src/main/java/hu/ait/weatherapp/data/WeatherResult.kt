package hu.ait.weatherapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//how do you know which things you'll need???

@Serializable
data class WeatherResult(
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("weather")
    val weather: List<Weather?>? = null,
    @SerialName("base")
    val base: String? = null,
    @SerialName("main")
    val main: Main? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("wind")
    val wind: Wind? = null,
    @SerialName("rain")
    val rain: Rain? = null,
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("cod")
    val cod: Int? = null,
)

@Serializable
data class Coord(
    @SerialName("lon")
    val lon: Float? = null,
    @SerialName("lat")
    val lat: Float? = null
)

@Serializable
data class Weather(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("main")
    val main: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("icon")
    val icon: String? = null
)

@Serializable
data class Main(
    @SerialName("temp")
    val temp: Float? = null,
    @SerialName("feels_like")
    val feels_like: Float? = null,
    @SerialName("temp_min")
    val temp_min: Float? = null,
    @SerialName("temp_max")
    val temp_max: Float? = null,
    @SerialName("pressure")
    val pressure: Int? = null,
    @SerialName("humidity")
    val humidity: Int? = null,
    @SerialName("sea_level")
    val sea_level: Int? = null,
    @SerialName("grnd_level")
    val grnd_level: Int? = null
)

@Serializable
data class Wind(
    @SerialName("speed")
    val speed: Float? = null,
    @SerialName("deg")
    val deg: Int? = null,
    @SerialName("gust")
    val gust: Float? = null,

)

@Serializable
data class Rain(
    @SerialName("1h")
    val one_hour: Float? = null
)

@Serializable
data class Clouds(
    @SerialName("all")
    val all: Int? = null
)

@Serializable
data class Sys(
    @SerialName("type")
    val type: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("sunrise")
    val sunrise: Int? = null,
    @SerialName("sunset")
    val sunset: Int? = null
)