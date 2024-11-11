package com.example.signlanghub.ui.navigation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.signlanghub.ui.home.HomeContract
import com.example.signlanghub.ui.home.HomeViewModel
import com.example.signlanghub.ui.home.screen.HomeScreen

@Composable
internal fun HomeDestination(
    navHostController: NavHostController
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when(navigationEffect) {
                HomeContract.Effect.Navigation.Localization -> {

                }
                HomeContract.Effect.Navigation.Search -> {
                    navHostController.navigate("search")
                }
                HomeContract.Effect.Navigation.Translation -> {

                }
            }
        },
    )
}