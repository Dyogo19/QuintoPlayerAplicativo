package com.example.quintoplayerapp.jogadores.jogador.presentation

sealed class JogadorViewState {

    object ShowHomeScreen : JogadorViewState()
    object ShowLoading : JogadorViewState()
    object ShowDataError : JogadorViewState()
    object ShowNomeError : JogadorViewState()
    object ShowMessageError : JogadorViewState()




}