package com.example.quintoplayerapp.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.login.data.UserRequest
import com.example.quintoplayerapp.login.domain.UserUseCase
import com.example.quintoplayerapp.profile.model.ProfileViewState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ProfileViewModel : ViewModel() {
    private val useCase by lazy { UserUseCase() }
    private val viewState = MutableLiveData<ProfileViewState>()
    val state: LiveData<ProfileViewState> = viewState


    fun validateInputsRegistrer(
        email: String,
        senha: String,
        nome: String,
        numeroDeCelular: String
    ) {
        var patternEmail = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
        var patternSenha = Pattern.compile("^.{4,}$")


        var matcherEmail = patternEmail.matcher(email)
        var matcherSenha = patternSenha.matcher(senha)


        viewState.value = ProfileViewState.ShowLoading


        if (email.isNullOrBlank() && senha.isNullOrBlank() && nome.isNullOrBlank() && numeroDeCelular.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowErrorMessage
            return
        }

        if (email.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowEmailErrorMessage
            return
        }

        if (senha.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowPasswordErrorMessage
            return
        }


        if (!matcherEmail.matches()) {
            viewState.value = ProfileViewState.ShowEmailErrorMessage
            return
        }

        if (!matcherSenha.matches()) {
            viewState.value = ProfileViewState.ShowPasswordErrorMessage
            return
        }

        if (nome.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowNameErrorMessage
        }

        if (numeroDeCelular.isNullOrBlank()) {
            viewState.value = ProfileViewState.ShowNumberErrorMessage
        }


        fetchRegister(
            email = email,
            senha = senha,
            nome = nome,
            numeroDeCelular = numeroDeCelular
        )


    }

    private fun fetchRegister(
        email: String,
        senha: String,
        nome: String,
        numeroDeCelular: String
    ) {
        viewModelScope.launch {
            val userRequest = UserRequest(email, senha, nome, numeroDeCelular)
            val isSuccess = useCase.createUser(
                nome = nome,
                email = email,
                senha = senha,
                numeroDeCelular = numeroDeCelular
            )
            if (isSuccess) {
                viewState.value = ProfileViewState.ShowSuccesCreate
            } else {
                viewState.value = ProfileViewState.ShowErrorMessage
            }
        }
    }
}
