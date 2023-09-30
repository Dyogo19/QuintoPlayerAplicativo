package com.example.quintoplayerapp.perfil.avaliacaoRegister.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoRequest
import com.example.quintoplayerapp.perfil.avaliacao.data.repository.AvaliacaoRepository
import com.example.quintoplayerapp.perfil.avaliacao.presentation.model.AvaliacaoViewState
import kotlinx.coroutines.launch


class AvaliacaoRegisterViewModel : ViewModel() {

    private val viewState = MutableLiveData<AvaliacaoViewState>()
    private val repository by lazy { AvaliacaoRepository() }
    val state: LiveData<AvaliacaoViewState> = viewState

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateInputs(
        avaliacao: String, nota: Int, idUsuario: Int
    ) {


        viewState.value = AvaliacaoViewState.ShowLoading

        if (avaliacao.isNullOrBlank() && nota.toString().isNullOrBlank()
        ) {
            viewState.value = AvaliacaoViewState.ShowMessageError
            return
        }

        if (avaliacao.isNullOrBlank()) {
            viewState.value = AvaliacaoViewState.ShowAvaliacaoError
            return
        }

        if (nota.toString().isNullOrBlank()) {
            viewState.value = AvaliacaoViewState.ShowNotaError
            return
        }



        createAvaliacao(avaliacao = avaliacao, nota = nota, idUsuario = idUsuario)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAvaliacao(
        avaliacao: String, nota: Int, idUsuario : Int
    ) {
        viewModelScope.launch {
            try {
                val avaliacao = AvaliacaoRequest(
                    avaliacao = avaliacao,
                    nota = nota,
                    idUsuarioDestinatario = idUsuario
                )
                val response = repository.createAvaliacao(avaliacao)

                if (response) {
                    viewState.value = AvaliacaoViewState.ShowHomeScreen
                } else {
                    viewState.value = AvaliacaoViewState.ShowMessageError
                }
            } catch (e: Exception) {
                viewState.value = AvaliacaoViewState.ShowMessageError
            }
        }
    }
}