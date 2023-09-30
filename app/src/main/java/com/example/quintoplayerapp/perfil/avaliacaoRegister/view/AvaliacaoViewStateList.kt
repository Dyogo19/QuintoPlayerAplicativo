package com.example.quintoplayerapp.perfil.avaliacaoRegister.view

import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel

sealed class AvaliacaoViewStateList {

    data class ShowHomeScreen(val list: List<AvaliacaoModel>) : AvaliacaoViewStateList()

    object ShowEmptyList : AvaliacaoViewStateList()

    object ShowLoading : AvaliacaoViewStateList()


}