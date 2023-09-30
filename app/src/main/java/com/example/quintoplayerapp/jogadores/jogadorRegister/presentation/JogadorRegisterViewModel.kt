package com.example.quintoplayerapp.jogadores.jogadorRegister.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorRequest
import com.example.quintoplayerapp.jogadores.jogador.data.repository.JogadorRepository
import com.example.quintoplayerapp.jogadores.jogador.presentation.JogadorViewState
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class JogadorRegisterViewModel : ViewModel() {

    private val viewState = MutableLiveData<JogadorViewState>()
    private val repository by lazy { JogadorRepository() }
    val state: LiveData<JogadorViewState> = viewState
    @RequiresApi(Build.VERSION_CODES.O)
    fun validateInputs(
        nomeJogador: String?, dataEHoraLivrePraJogar: String?
    ) {


        viewState.value = JogadorViewState.ShowLoading

        if (nomeJogador.isNullOrBlank() && dataEHoraLivrePraJogar.toString().isNullOrBlank()
        ) {
            viewState.value = JogadorViewState.ShowMessageError
            return
        }

        if (nomeJogador.isNullOrBlank()) {
            viewState.value = JogadorViewState.ShowNomeError
            return
        }

        if (dataEHoraLivrePraJogar.toString().isNullOrBlank()) {
            viewState.value = JogadorViewState.ShowDataError
            return
        }



        createJogador(nomeJogador, dataEHoraLivrePraJogar.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createJogador(
        nomeJogador: String?, dataEHoraLivrePraJogar: String?
    ) {
        viewModelScope.launch {
            try {
                val jogador = JogadorRequest(
                    nomeJogador = nomeJogador ?: "",
                    dataEHoraLivrePraJogar = dataEHoraLivrePraJogar?: "")
                val response = repository.createJogador(jogador)

                if (response) {
                    viewState.value = JogadorViewState.ShowHomeScreen
                } else {
                    viewState.value = JogadorViewState.ShowMessageError
                }
            } catch (e: Exception) {
                viewState.value = JogadorViewState.ShowMessageError
            }
        }
    }
}
