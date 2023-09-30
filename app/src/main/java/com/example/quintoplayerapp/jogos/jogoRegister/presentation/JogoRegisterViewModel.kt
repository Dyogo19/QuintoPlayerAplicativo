package com.example.quintoplayerapp.jogos.jogoRegister.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.jogos.jogo.data.JogoRequest
import com.example.quintoplayerapp.jogos.jogo.data.repository.JogoRepository
import com.example.quintoplayerapp.jogos.jogo.presentation.JogoViewState
import kotlinx.coroutines.launch

class JogoRegisterViewModel : ViewModel() {

    private val viewState = MutableLiveData<JogoViewState>()
    private val repository by lazy { JogoRepository() }
    val state: LiveData<JogoViewState> = viewState

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateInputs(
        descricao: String?, enderecoDoJogo: String?, dataEHorario: String
    ) {


        viewState.value = JogoViewState.ShowLoading

        if (descricao.isNullOrBlank() && dataEHorario.toString()
                .isNullOrBlank()
        ) {
            viewState.value = JogoViewState.ShowMessageError
            return
        }

        if (descricao.isNullOrBlank()) {
            viewState.value = JogoViewState.ShowDescricaoError
            return
        }
        if (enderecoDoJogo.isNullOrBlank()) {
            viewState.value = JogoViewState.ShowLocalError
            return
        }

        if (dataEHorario.toString().isNullOrBlank()) {
            viewState.value = JogoViewState.ShowDataError
            return
        }



        createJogo(descricao, enderecoDoJogo, dataEHorario.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createJogo(
        descricao: String?, enderecoDoJogo: String?, dataEHorario: String?
    ) {
        viewModelScope.launch {
            try {
                val jogo = JogoRequest(
                    descricao = descricao ?: "",
                    enderecoDoJogo = enderecoDoJogo ?: "",
                    dataEHorario = dataEHorario ?: ""
                )
                val response = repository.createJogo(jogo)

                if (response) {
                    viewState.value = JogoViewState.ShowHomeScreen
                } else {
                    viewState.value = JogoViewState.ShowMessageError
                }
            } catch (e: Exception) {
                viewState.value = JogoViewState.ShowMessageError
            }
        }
    }
}
