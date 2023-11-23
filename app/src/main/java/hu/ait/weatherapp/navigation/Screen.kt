package hu.ait.weatherapp.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
//    object NasaMarsAPI : Screen("nasamars")
//    object MoneyRatesAPI : Screen("moneyrates")
}