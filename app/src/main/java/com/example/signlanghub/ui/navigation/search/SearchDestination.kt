package com.example.signlanghub.ui.navigation.search

import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.signlanghub.ui.search.SearchContract
import com.example.signlanghub.ui.search.SearchViewModel
import com.example.signlanghub.ui.search.screen.SearchScreen
import com.example.signlanghub.util.SignLangCustomTabsServiceConnection

@Composable
fun SearchDestination(navController: NavController) {
    val viewModel = hiltViewModel<SearchViewModel>()

    val context = LocalContext.current
    val customTabsServiceConnection = remember { SignLangCustomTabsServiceConnection }

    val customTabsIntent = remember {
        CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", customTabsServiceConnection)
        CustomTabsIntent.Builder(customTabsServiceConnection.customTabsSession)
            // .setToolbarColor(color)
            .setShowTitle(false)
            .build()
    }

    SearchScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when(navigationEffect) {
                SearchContract.Effect.Navigation.Banner -> {
                    customTabsIntent.launchUrl(context, Uri.parse("https://lifeplanhd.kr/article/notice/79268919-b00c-4a55-bad8-73f5d6f1ebb2"))
                }

                SearchContract.Effect.Navigation.PopBackStack -> {
                    navController.navigateUp()
                }
            }
        },
    )
}
