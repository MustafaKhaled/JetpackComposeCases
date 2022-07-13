package io.jetpack.compose.cases.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.jetpack.compose.cases.R.*
import io.jetpack.compose.cases.ui.navigation.NavRoutes
import io.jetpack.compose.extensions.navigateNextScreen
import kotlinx.coroutines.delay

/**
 * Created by mustafakhaled on 23,June,2022
 */

@Composable
fun SplashScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 32.dp)
            .fillMaxSize()
    ) {
        HideStatusBar()
        Row(
            modifier = Modifier
                .padding()
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(mipmap.ic_launcher_foreground),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Cases",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    LaunchedEffect(true) {
        delay(3000)
        navController?.navigateNextScreen(NavRoutes.CasesListScreen.route)
    }
}

@Composable
fun HideStatusBar() {
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
    systemUiController.isNavigationBarVisible = false
    systemUiController.isSystemBarsVisible = false

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SplashScreen()
}