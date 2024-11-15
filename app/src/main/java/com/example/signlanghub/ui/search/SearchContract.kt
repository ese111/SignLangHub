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
}

sealed interface SearchContract {
    data class State(
        val keyword: String = "",
        val searchResult: List<SearchDTO> = emptyList(),
        val uiState: UiState = UiState.Main,
        val videoProcessDialogVisible: Boolean = false,
        val videoUri: Uri? = null,
    ) : ViewState

    sealed class Event : ViewEvent {
        data class GetVideoUri(
            val uri: Uri,
        ) : Event()

        data class OnChangeKeyword(
            val keyword: String,
        ) : Event()

        data object OnClickSearch : Event()

        data object ShowVideoProcessBottomSheet : Event()

        data object DismissVideoProcessBottomSheet : Event()

        data class OnSearchVideo(
            val uri: String,
        ) : Event()
    }

    sealed class Effect : ViewSideEffect {
        data object ShowErrorToast : Effect()

        sealed class Navigation : Effect()
    }
}
