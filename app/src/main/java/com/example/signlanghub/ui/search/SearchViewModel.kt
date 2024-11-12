package com.example.signlanghub.ui.search

import androidx.lifecycle.viewModelScope
import com.example.signlanghub.data.repository.SearchRepository
import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        }
    }
