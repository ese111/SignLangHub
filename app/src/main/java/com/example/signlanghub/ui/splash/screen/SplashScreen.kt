package com.example.signlanghub.ui.splash.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.signlanghub.ui.splash.content.SplashContent
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    navHostController: NavHostController
) {
    LaunchedEffect(true) {
        delay(500)
        navHostController.navigate("login")
    }
    SplashContent()
}

