package hu.ait.weatherapp.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object WeatherAPI : Screen("weatherapi")
}