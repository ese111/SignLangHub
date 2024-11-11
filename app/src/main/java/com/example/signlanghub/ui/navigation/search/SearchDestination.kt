package com.example.signlanghub.ui.navigation.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signlanghub.ui.login.LoginContract
import com.example.signlanghub.ui.login.LoginViewModel
import com.example.signlanghub.ui.login.screen.LoginScreen
import com.example.signlanghub.ui.search.SearchViewModel
import com.example.signlanghub.ui.search.screen.SearchScreen

@Composable
fun SearchDestination(
    navController: NavController
) {
    val viewModel = hiltViewModel<SearchViewModel>()

    SearchScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->

        },
    )
}