package io.jetpack.compose.cases.ui.navigation

/**
 * Created by mustafakhaled on 23,June,2022
 */

sealed class NavRoutes(val route: String) {
    object SplashScreen: NavRoutes("splash_screen")
}