package com.example.signlanghub.ui.search

import androidx.lifecycle.viewModelScope
import com.example.signlanghub.data.repository.SearchRepository
import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val searchRepository: SearchRepository,
    ) : BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {
        override fun setInitialState(): SearchContract.State = SearchContract.State()

        override fun handleEvents(event: SearchContract.Event) {
            when (event) {
                is SearchContract.Event.OnChangeKeyword -> setState { copy(keyword = event.keyword) }

                SearchContract.Event.OnClickSearch -> getSearch()

                SearchContract.Event.ShowVideoProcessDialog -> setState { copy(videoProcessDialogVisible = true) }

                SearchContract.Event.DismissVideoProcessDialog -> setState { copy(videoProcessDialogVisible = false) }

                is SearchContract.Event.GetVideoUri ->
                    setState {
                        copy(
                            videoUri = event.uri,
                        )
                    }

                is SearchContract.Event.OnSearchVideo -> searchVideo(event.uri)

                SearchContract.Event.DismissVideoCheckDialog -> setState { copy(isVideoCheckDialogVisible = false) }

                SearchContract.Event.ShowVideoCheckDialog -> setState { copy(isVideoCheckDialogVisible = true) }
            }
        }

        private fun searchVideo(uri: String) {
            viewModelScope.launch {
                val file = File(uri)
                val requestFile = file.asRequestBody("video/*".toMediaTypeOrNull())
                val video = MultipartBody.Part.createFormData("file", file.getName(), requestFile)
                searchRepository
                    .postImageSearch(video)
                    .onStart {
                        setState { copy(uiState = UiState.Loading) }
                    }.collect { result ->
                        result
                            .onSuccess { response ->
                                if (response.isEmpty()) {
                                    setState {
                                        copy(uiState = UiState.NothingResult)
                                    }
                                } else {
                                    setState {
                                        copy(
                                            searchResult = response,
                                            uiState = UiState.SearchResult,
                                        )
                                    }
                                }
                            }.onFailure {
                                Timber.e(it)
                                setEffect { SearchContract.Effect.ShowErrorToast }
                            }
                    }
            }
        }

        private fun getSearch() {
            viewModelScope.launch {
                searchRepository
                    .getSearch(
                        keyword = viewState.value.keyword.trim(),
                    ).onStart {
                        setState { copy(uiState = UiState.Loading) }
                    }.collect { result ->
                        result
                            .onSuccess { response ->
                                if (response.isEmpty()) {
                                    setState {
                                        copy(uiState = UiState.NothingResult)
                                    }
                                } else {
                                    setState {
                                        copy(
                                            searchResult = response,
                                            uiState = UiState.SearchResult,
                                        )
                                    }
                                }
                            }.onFailure {
                                setEffect { SearchContract.Effect.ShowErrorToast }
                            }
                    }
            }
        }
    }
