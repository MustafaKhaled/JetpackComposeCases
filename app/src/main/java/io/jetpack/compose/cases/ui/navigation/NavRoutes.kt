package io.jetpack.compose.cases.ui.navigation

/**
 * Created by mustafakhaled on 23,June,2022
 */

sealed class NavRoutes(val route: String) {
    object SplashScreen: NavRoutes("splash_screen")
    object CasesListScreen: NavRoutes("cases_list_screen")
    object RequestPermissionScreen: NavRoutes("request_permission_screen")
    object OtpScreen: NavRoutes("otp_screen")
}