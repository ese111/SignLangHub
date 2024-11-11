package com.example.signlanghub.ui.login.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.signlanghub.ui.base.SIDE_EFFECTS_KEY
import com.example.signlanghub.ui.login.LoginContract
import com.example.signlanghub.ui.login.content.LoginContent
import kotlinx.coroutines.flow.Flow

@Composable
internal fun LoginScreen(
    state: LoginContract.State,
    effectFlow: Flow<LoginContract.Effect>?,
    onEventSent: (event: LoginContract.Event) -> Unit,
    onNavigationRequested: (LoginContract.Effect.Navigation) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is LoginContract.Effect.Navigation.Home -> onNavigationRequested(effect)
                is LoginContract.Effect.ShowErrorToast -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginContent(
        state = state,
        onEvent = onEventSent,
    )
}