package com.example.signlanghub.ui.login

import com.example.signlanghub.ui.base.ViewEvent
import com.example.signlanghub.ui.base.ViewSideEffect
import com.example.signlanghub.ui.base.ViewState

sealed interface LoginContract {
    sealed class Event: ViewEvent {
        data object OnClickLogin : Event()
        data class OnChangeId(val id: String) : Event()
        data class OnChangePassword(val password: String) : Event()
    }

    data class State(
        val id: String = "",
        val password: String = "",
    ): ViewState

    sealed class Effect: ViewSideEffect {
        data class ShowErrorToast(val msg: String) : Effect()
        sealed class Navigation : Effect() {
            data object Home : Navigation()
        }
    }
}