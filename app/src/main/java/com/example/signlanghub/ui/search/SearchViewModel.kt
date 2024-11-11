package com.example.signlanghub.ui.search

import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

): BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    override fun setInitialState(): SearchContract.State {
       return SearchContract.State()
    }

    override fun handleEvents(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.OnChangeKeyword -> {
                setState { copy(keyword = event.keyword) }
            }
        }
    }

}