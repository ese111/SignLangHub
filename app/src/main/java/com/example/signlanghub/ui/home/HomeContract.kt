package com.example.signlanghub.ui.home

import com.example.signlanghub.ui.base.ViewEvent
import com.example.signlanghub.ui.base.ViewSideEffect
import com.example.signlanghub.ui.base.ViewState

sealed interface HomeContract {
    sealed class Event: ViewEvent {

    }

    data class State(
        val id: String = "",
    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Localization : Navigation()
            data object Translation: Navigation()
            data object Search : Navigation()
        }
    }
}
