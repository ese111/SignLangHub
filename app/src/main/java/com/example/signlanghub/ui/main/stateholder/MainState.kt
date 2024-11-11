package com.example.signlanghub.ui.main.stateholder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class MainState(
    val navHostController: NavHostController
) {

}

@Composable
fun rememberMainState(
    navHostController: NavHostController = rememberNavController()
) = remember(
    navHostController
) {
    MainState(
        navHostController = navHostController
    )
}