package com.example.signlanghub.ui.search

import android.net.Uri
import com.example.signlanghub.data.model.SearchDTO
import com.example.signlanghub.ui.base.ViewEvent
import com.example.signlanghub.ui.base.ViewSideEffect
import com.example.signlanghub.ui.base.ViewState

sealed class UiState {
    data object Main : UiState()

    data object SearchResult : UiState()

    data object NothingResult : UiState()

    data object Loading : UiState()
}

sealed interface SearchContract {
    data class State(
        val keyword: String = "",
        val searchResult: List<SearchDTO> = emptyList(),
        val uiState: UiState = UiState.Main,
        val videoProcessDialogVisible: Boolean = false,
        val videoUri: Uri? = null,
        val isVideoCheckDialogVisible: Boolean = false,
    ) : ViewState

    sealed class Event : ViewEvent {
        data class GetVideoUri(
            val uri: Uri,
        ) : Event()

        data class OnChangeKeyword(
            val keyword: String,
        ) : Event()

        data object OnClickSearch : Event()

        data object ShowVideoProcessDialog : Event()

        data object DismissVideoProcessDialog : Event()

        data class OnSearchVideo(
            val uri: String,
        ) : Event()

        data object DismissVideoCheckDialog : Event()

        data object ShowVideoCheckDialog : Event()
    }

    sealed class Effect : ViewSideEffect {
        data object ShowErrorToast : Effect()

        sealed class Navigation : Effect() {
            data object Banner : Navigation()

            data object PopBackStack : Navigation()
        }
    }
}
