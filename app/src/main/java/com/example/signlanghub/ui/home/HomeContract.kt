package com.example.signlanghub.ui.home

import com.example.signlanghub.ui.base.ViewEvent
import com.example.signlanghub.ui.base.ViewSideEffect
import com.example.signlanghub.ui.base.ViewState
import com.example.signlanghub.ui.search.SearchContract.Event

sealed interface HomeContract {
    sealed class Event: ViewEvent {
        data object OnClickBanner : Event()
    }

    data class State(
        val id: String = "",
    ): ViewState

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Localization : Navigation()
            data object Translation: Navigation()
            data object Search : Navigation()
            data object Banner : Navigation()
        }
    }
}
