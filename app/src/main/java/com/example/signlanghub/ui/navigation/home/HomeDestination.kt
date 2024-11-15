package com.example.signlanghub.ui.navigation.home

import android.net.Uri
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.signlanghub.BuildConfig
import com.example.signlanghub.ui.home.HomeContract
import com.example.signlanghub.ui.home.HomeViewModel
import com.example.signlanghub.ui.home.screen.HomeScreen
import com.example.signlanghub.util.SignLangCustomTabsServiceConnection

@Composable
internal fun HomeDestination(
    navHostController: NavHostController,
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val context = LocalContext.current
    val customTabsServiceConnection = remember { SignLangCustomTabsServiceConnection }

    val customTabsIntent = remember {
        CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", customTabsServiceConnection)
        CustomTabsIntent.Builder(customTabsServiceConnection.customTabsSession)
            // .setToolbarColor(color)
            .setShowTitle(false)
            .build()
    }

    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                HomeContract.Effect.Navigation.Localization -> {
                    customTabsIntent.launchUrl(context, Uri.parse(BuildConfig.LOCALIZATION_URL))
                }
                HomeContract.Effect.Navigation.Search -> {
                    navHostController.navigate("search")
                }
                HomeContract.Effect.Navigation.Translation -> {
                    customTabsIntent.launchUrl(context, Uri.parse(BuildConfig.TRANSLATION_URL))
                }
                HomeContract.Effect.Navigation.Banner -> {
                    customTabsIntent.launchUrl(context, Uri.parse("https://lifeplanhd.kr/article/notice/60d11f9f-55c9-49bd-8a56-54c588ab0db3"))
                }
            }
        },
    )
}
