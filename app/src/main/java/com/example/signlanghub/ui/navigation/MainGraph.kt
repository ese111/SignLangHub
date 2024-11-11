package com.example.signlanghub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signlanghub.ui.home.screen.HomeScreen
import com.example.signlanghub.ui.login.screen.LoginScreen
import com.example.signlanghub.ui.navigation.home.HomeDestination
import com.example.signlanghub.ui.navigation.login.LoginDestination
import com.example.signlanghub.ui.navigation.search.SearchDestination
import com.example.signlanghub.ui.splash.screen.SplashScreen

@Composable
internal fun MainGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                navHostController = navHostController
            )
        }

        composable("login") {
            LoginDestination(navHostController)
        }

        composable("home") {
            HomeDestination(navHostController)
        }

        composable("search") {
            SearchDestination(navHostController)
        }
    }
}


