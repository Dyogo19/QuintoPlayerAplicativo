package com.example.quintoplayerapp.jogos.jogoRegister.view

import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel

sealed class JogoViewStateList {

    data class ShowHomeScreen(val list: List<JogoModel>) : JogoViewStateList()

    object ShowEmptyList : JogoViewStateList()

    object ShowLoading : JogoViewStateList()


}