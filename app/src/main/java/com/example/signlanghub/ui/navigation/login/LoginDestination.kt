package com.example.signlanghub.ui.navigation.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signlanghub.ui.login.LoginContract
import com.example.signlanghub.ui.login.LoginViewModel
import com.example.signlanghub.ui.login.screen.LoginScreen

@Composable
fun LoginDestination(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewModel>()

    LoginScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is LoginContract.Effect.Navigation.Home) {
                navController.navigate("home")
            }
        },
    )
}