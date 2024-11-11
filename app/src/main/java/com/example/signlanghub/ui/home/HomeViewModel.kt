package com.example.signlanghub.ui.home

import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    override fun setInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvents(event: HomeContract.Event) {

    }
}