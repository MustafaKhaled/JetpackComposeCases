package io.jetpack.compose.cases.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.jetpack.compose.cases.ui.casesList.CasesListScreen
import io.jetpack.compose.splashscreen.SplashScreen

/**
 * Created by mustafakhaled on 23,June,2022
 */

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.SplashScreen.route) {
        composable(route = NavRoutes.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = NavRoutes.CasesListScreen.route) {
            CasesListScreen(navController)
        }
    }
}