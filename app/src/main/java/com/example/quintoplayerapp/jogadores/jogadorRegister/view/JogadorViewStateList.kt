package com.example.quintoplayerapp.jogadores.jogadorRegister.view

import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel

sealed class JogadorViewStateList {

    data class ShowHomeScreen(val list: List<JogadorModel>) : JogadorViewStateList()

    object ShowEmptyList : JogadorViewStateList()

    object ShowLoading : JogadorViewStateList()


}