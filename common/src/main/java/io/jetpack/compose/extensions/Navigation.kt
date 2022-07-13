package io.jetpack.compose.extensions

import androidx.navigation.NavController

/**
 * Created by mustafakhaled on 13,July,2022
 */

fun NavController.navigateNextScreen(route: String, popUpScreen: String? = null) {
    navigate(route = route) {
        if (popUpScreen != null) {
            popUpTo(popUpScreen) {
                inclusive = true
            }
        }
    }
}