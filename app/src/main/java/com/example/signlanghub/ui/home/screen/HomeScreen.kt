package com.example.signlanghub.ui.home.screen

import androidx.compose.runtime.Composable
import com.example.signlanghub.ui.home.HomeContract
import com.example.signlanghub.ui.home.content.HomeContent
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    state: HomeContract.State,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (HomeContract.Effect.Navigation) -> Unit
) {
    HomeContent(
        state = state,
        onEvent = onEventSent,
        onNavigationRequested = onNavigationRequested
    )
}