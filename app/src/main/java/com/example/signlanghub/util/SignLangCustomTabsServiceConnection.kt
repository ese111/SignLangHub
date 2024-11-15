package com.example.signlanghub.util

import android.content.ComponentName
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession

object SignLangCustomTabsServiceConnection: CustomTabsServiceConnection() {

    private var client: CustomTabsClient? = null
    private var _customTabsSession: CustomTabsSession? = null

    val customTabsSession = _customTabsSession

    override fun onServiceDisconnected(name: ComponentName?) {
        client = null
    }

    override fun onCustomTabsServiceConnected(name: ComponentName, customTabsClient: CustomTabsClient) {
        // Pre-warming
        client = customTabsClient
        client?.warmup(0L)
        _customTabsSession = client?.newSession(null)
    }

}