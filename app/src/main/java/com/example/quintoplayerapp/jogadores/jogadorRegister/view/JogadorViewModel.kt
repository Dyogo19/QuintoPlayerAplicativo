package com.example.quintoplayerapp.jogadores.jogadorRegister.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.jogadores.jogador.data.domain.JogadorUseCase
import kotlinx.coroutines.launch

class JogadorViewModel : ViewModel() {

    private val useCase by lazy { JogadorUseCase() }

    private val viewState = MutableLiveData<JogadorViewStateList>()
    val state: LiveData<JogadorViewStateList> = viewState

    fun listJogador() {
        viewModelScope.launch {
            viewState.value = JogadorViewStateList.ShowLoading
            val list = useCase.getJogadores()

            if (list.isEmpty()) {
                viewState.value = JogadorViewStateList.ShowEmptyList
            } else {
                viewState.value = JogadorViewStateList.ShowHomeScreen(list)
            }
        }
    }

}