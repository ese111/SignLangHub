package com.example.signlanghub.ui.search

import androidx.lifecycle.viewModelScope
import com.example.signlanghub.data.repository.SearchRepository
import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
                is SearchContract.Event.OnChangeKeyword -> {
                    setState { copy(keyword = event.keyword) }
                }

                SearchContract.Event.OnClickSearch -> {
                    getSearch()
                }

                SearchContract.Event.ShowVideoProcessBottomSheet -> {
                    setState { copy(videoProcessDialogVisible = true) }
                }

                SearchContract.Event.DismissVideoProcessBottomSheet -> {
                    setState { copy(videoProcessDialogVisible = false) }
                }

                is SearchContract.Event.GetVideoUri -> {
                    setState { copy(videoUri = event.uri) }
                }

                is SearchContract.Event.OnSearchVideo -> {
                    viewModelScope.launch {
                        val file = File(event.uri)
                        val requestFile = RequestBody.create("video/*".toMediaTypeOrNull(), file)
                        val video = MultipartBody.Part.createFormData("file", file.getName(), requestFile)
                        searchRepository
                            .postImageSearch(video)
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
        }

        private fun getSearch() {
            viewModelScope.launch {
                searchRepository
                    .getSearch(
                        keyword = viewState.value.keyword,
                    ).let { result ->
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
