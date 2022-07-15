package io.jetpack.compose.cases.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.jetpack.compose.cases.ui.casesList.CasesListScreen
import io.jetpack.compose.cases.ui.casesList.casesList
import io.jetpack.compose.cases.ui.splashscreen.SplashScreen
import io.jetpack.compose.permissions.SinglePermissionScreen

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
            val cases = casesList
            CasesListScreen(navController, cases)
        }
        composable(route = NavRoutes.RequestPermissionScreen.route) {
            SinglePermissionScreen(navController)
        }
    }
}