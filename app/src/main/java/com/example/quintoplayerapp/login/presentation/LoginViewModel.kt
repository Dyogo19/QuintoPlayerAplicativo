package com.example.quintoplayerapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.login.data.LoginRequest
import com.example.quintoplayerapp.login.domain.LoginUseCase
import com.example.quintoplayerapp.login.presentation.model.LoginViewState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val useCase by lazy { LoginUseCase() }
    private val viewState = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState> = viewState


    fun validateInputs(email: String, senha: String) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        var patternSenha = Pattern.compile("^.{4,}$")

        var matcherEmail = patternEmail.matcher(email)
        var matcherSenha = patternSenha.matcher(senha)


        viewState.value = LoginViewState.ShowLoading


        if (email.isNullOrBlank() && senha.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowEmailErrorMessage
            return
        }

        if (senha.isNullOrBlank()) {
            viewState.value = LoginViewState.ShowPasswordErrorMessage
            return
        }
        if (!matcherEmail.matches()) {
            viewState.value = LoginViewState.ShowEmailErrorMessage
            return
        }

        if (!matcherSenha.matches()) {
            viewState.value = LoginViewState.ShowPasswordErrorMessage
            return
        }

        fetchLogin(email = email, senha = senha)

    }


    private fun fetchLogin(email: String, senha: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(email , senha)
            val isSuccess = useCase.login(loginRequest)
            if (isSuccess) {
                viewState.value = LoginViewState.ShowHomeScreen
            } else {
                viewState.value = LoginViewState.ShowErrorMessage
            }
        }
    }
}