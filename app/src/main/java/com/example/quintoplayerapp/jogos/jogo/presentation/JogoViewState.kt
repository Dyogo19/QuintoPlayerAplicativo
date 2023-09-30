package com.example.quintoplayerapp.jogos.jogo.presentation

sealed class JogoViewState {

    object ShowHomeScreen : JogoViewState()
    object ShowLoading : JogoViewState()
    object ShowDataError : JogoViewState()
    object ShowLocalError : JogoViewState()
    object ShowDescricaoError : JogoViewState()
    object ShowMessageError : JogoViewState()




}