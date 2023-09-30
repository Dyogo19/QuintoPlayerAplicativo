package com.example.quintoplayerapp.perfil.avaliacao.presentation.model

sealed class AvaliacaoViewState {

    object ShowHomeScreen : AvaliacaoViewState()
    object ShowLoading : AvaliacaoViewState()
    object ShowNotaError : AvaliacaoViewState()
    object ShowAvaliacaoError : AvaliacaoViewState()
    object ShowMessageError : AvaliacaoViewState()

}