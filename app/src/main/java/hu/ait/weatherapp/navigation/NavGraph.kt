package hu.ait.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.ait.weatherapp.ui.screen.CitesScreen
import hu.ait.weatherapp.ui.screen.WeatherApiScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            CitesScreen(
                onNavigateToWeatherScreen = {
                    // navigate to the main messages screen
                    navController.navigate(Screen.WeatherAPI.route)
                },
            )
        }
        composable(Screen.WeatherAPI.route) {
            WeatherApiScreen()
        }
    }
}


