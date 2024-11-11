package com.example.signlanghub.ui.search

import com.example.signlanghub.ui.base.ViewEvent
import com.example.signlanghub.ui.base.ViewSideEffect
import com.example.signlanghub.ui.base.ViewState

sealed interface SearchContract {
    data class State(
        val keyword: String = "",
    ): ViewState

    sealed class Event: ViewEvent {
        data class OnChangeKeyword(val keyword: String) : Event()
    }

    sealed class Effect: ViewSideEffect {
        sealed class Navigation : Effect() {
        }
    }
}