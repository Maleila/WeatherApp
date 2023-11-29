package hu.ait.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                onNavigateToWeatherScreen = { q ->
                    navController.navigate("weatherapi/$q")
                },
            )
        }
        composable("weatherapi/{q}",
            arguments = listOf(
                navArgument("q") { type = NavType.StringType }
            )) {
            val q = it.arguments?.getString("q")
            if (q != null) {
                WeatherApiScreen(q)
            }
        }
    }
}

