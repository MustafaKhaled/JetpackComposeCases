package io.jetpack.compose.cases.ui.casesList

import io.jetpack.compose.cases.ui.navigation.NavRoutes

/**
 * Created by mustafakhaled on 14,July,2022
 */
val casesList = listOf(
    CaseItem("Title 1", "Mustafa", NavRoutes.SplashScreen.route),
)


data class CaseItem(val title: String, val author: String, val navigation: String)