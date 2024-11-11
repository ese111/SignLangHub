package com.example.signlanghub.ui.login

import com.example.signlanghub.data.repository.LoginRepository
import com.example.signlanghub.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    override fun setInitialState(): LoginContract.State {
        return LoginContract.State()
    }

    override fun handleEvents(event: LoginContract.Event) {
        when(event) {
            is LoginContract.Event.OnClickLogin -> {
                loginRepository.login(viewState.value.id, viewState.value.password).onSuccess {
                    setEffect { LoginContract.Effect.Navigation.Home }
                }.onFailure {
                    setEffect { LoginContract.Effect.ShowErrorToast("로그인에 실패하였습니다.") }
                }
            }

            is LoginContract.Event.OnChangeId -> {
                setState {
                    copy(id = event.id)
                }
            }

            is LoginContract.Event.OnChangePassword -> {
                setState {
                    copy(password = event.password)
                }
            }
        }
    }

}