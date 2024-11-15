package com.example.signlanghub.ui.main.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.signlanghub.ui.navigation.MainGraph
import com.example.signlanghub.ui.main.stateholder.rememberMainState

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier
) {
    val mainState = rememberMainState()

    MainGraph(
        modifier = modifier,
        navHostController = mainState.navHostController,
    )
}