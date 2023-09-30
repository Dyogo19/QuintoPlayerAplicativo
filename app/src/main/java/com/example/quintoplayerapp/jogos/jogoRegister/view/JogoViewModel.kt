package com.example.quintoplayerapp.jogos.jogoRegister.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.jogos.jogo.data.domain.JogoUseCase
import kotlinx.coroutines.launch

class JogoViewModel : ViewModel() {

    private val useCase by lazy { JogoUseCase() }

    private val viewState = MutableLiveData<JogoViewStateList>()
    val state: LiveData<JogoViewStateList> = viewState

    fun listJogo() {
        viewModelScope.launch {
            viewState.value = JogoViewStateList.ShowLoading
            val list = useCase.getJogos()

            if (list.isEmpty()) {
                viewState.value = JogoViewStateList.ShowEmptyList
            } else {
                viewState.value = JogoViewStateList.ShowHomeScreen(list)
            }
        }
    }

}