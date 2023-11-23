package hu.ait.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.ait.weatherapp.ui.screen.CitesScreen

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
//                onNasaMarsAPISelected = {
//                    // navigate to the main messages screen
//                    navController.navigate(Screen.NasaMarsAPI.route)
//                },
//                onMoneyAPISelected = {
//                    navController.navigate(Screen.MoneyRatesAPI.route)
//                }
            )
        }
//        composable(Screen.NasaMarsAPI.route) {
//            NasaMarsApiScreen()
//        }
//        composable(Screen.MoneyRatesAPI.route) {
//            MoneyApiScreen()
//        }
    }
}


