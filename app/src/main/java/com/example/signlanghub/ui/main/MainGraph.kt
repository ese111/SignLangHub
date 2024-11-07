package com.example.signlanghub.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signlanghub.ui.login.screen.LoginScreen
import com.example.signlanghub.ui.splash.screen.SplashScreen

@Composable
internal fun MainGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen()
        }

        composable("login") {
            LoginScreen()
        }
    }
}


