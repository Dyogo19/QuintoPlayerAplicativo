package com.example.quintoplayerapp.perfil.avaliacaoRegister.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quintoplayerapp.perfil.avaliacao.data.domain.AvaliacaoUseCase
import kotlinx.coroutines.launch

class AvaliacaoViewModel : ViewModel() {

    private val useCase by lazy { AvaliacaoUseCase() }

    private val viewState = MutableLiveData<AvaliacaoViewStateList>()
    val state: LiveData<AvaliacaoViewStateList> = viewState

    fun listAvaliacao() {
        viewModelScope.launch {
            viewState.value = AvaliacaoViewStateList.ShowLoading
            val list = useCase.getAvaliacoes()

            if (list.isEmpty()) {
                viewState.value = AvaliacaoViewStateList.ShowEmptyList
            } else {
                viewState.value = AvaliacaoViewStateList.ShowHomeScreen(list)
            }
        }
    }

}